package com.cts.portal.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cts.portal.dto.AccountSummary;
import com.cts.portal.dto.Customer;
import com.cts.portal.dto.LoginUser;
import com.cts.portal.dto.Operation;
import com.cts.portal.dto.Statement;
import com.cts.portal.dto.StatementQuery;
import com.cts.portal.dto.Transaction;
import com.cts.portal.dto.TransactionStatus;
import com.cts.portal.dto.Transfer;
import com.cts.portal.model.BankUser;
import com.cts.portal.security.SecurityService;
import com.cts.portal.service.CustomerService;
import com.cts.portal.service.TransactionService;

@Controller
@SessionAttributes({ "user", "accounts", "bearerToken" })
public class HomeController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private SecurityService securityService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(LocalDate.class, new CustomDateEditor(dateFormat, false));
	}
	
	@ModelAttribute("bearerToken")
	public String getBearerToken(@ModelAttribute("user") BankUser user, Principal principal) {
		LoginUser login = new LoginUser();
		login.setUsername(user.getEmail());
		login.setPassword(user.getPassword());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		String token = this.securityService.getToken(login);
		if (token == null) {
			throw new BadCredentialsException("No user registered as " + login.getUsername());
		}
		return "Bearer " + token;
	}

	@GetMapping("/dashboard")
	public String customerHomepageAction(ModelMap model, @ModelAttribute("user") BankUser user, @ModelAttribute("bearerToken") String bearerToken) {
		Customer customer = this.customerService.getCustomer(user.getCustomerFirstName(), bearerToken);
		model.addAttribute("customer", customer);
		return "dashboard";
	}

	@GetMapping("/operation")
	public String getOperationAction(ModelMap model, Operation operation, Transfer transfer,
			@ModelAttribute("accounts") List<AccountSummary> accounts) {
		model.addAttribute("accounts", accounts);
		return "operation";
	}

	@GetMapping("/transactions")
	public String getTransactionAction(ModelMap model, @ModelAttribute("user") BankUser user, @ModelAttribute("bearerToken") String bearerToken) {
		Customer customer = this.customerService.getCustomer(user.getCustomerFirstName(), bearerToken);
		List<Transaction> transactions = this.transactionService.getTransactions(customer.getCustomerId());
		model.addAttribute("transactions", transactions);
		return "list-transaction";
	}

	@GetMapping("/statements")
	public String getStatementsAction(ModelMap model, StatementQuery query,
			@ModelAttribute("accounts") List<AccountSummary> accounts) {
		model.addAttribute("accounts", accounts);
		List<Statement> statements = this.transactionService.getStatements(accounts.get(0).getAccountId(), YearMonth.now().atDay(1), LocalDate.now());
		query = new StatementQuery();
		model.addAttribute("query", query);
		model.addAttribute("statements", statements);
		return "list-statement";
	}

	@PostMapping("/statements")
	public String postStatementsAction(ModelMap model, @Valid StatementQuery query, BindingResult result,
			@ModelAttribute("accounts") List<AccountSummary> accounts) {
		if (!result.hasErrors()) {
			if (query.getEndDate().isEmpty() || query.getStartDate().isEmpty()) {
				List<Statement> statements = this.transactionService.getStatements(query.getAccountId(), YearMonth.now().atDay(1), LocalDate.now());
				model.addAttribute("statements", statements);
			} else {
				LocalDate startDate = LocalDate.parse(query.getStartDate());
				LocalDate endDate = LocalDate.parse(query.getEndDate());
				List<Statement> statements = this.transactionService.getStatements(query.getAccountId(), startDate, endDate);
				model.addAttribute("statements", statements);
			}
			model.addAttribute("query", query);
		}
		model.addAttribute("accounts", accounts);
		return "list-statement";
	}

	@PostMapping("/operation")
	public String postOperation(ModelMap model, @Valid Operation operation, BindingResult result, Transfer transfer,
			RedirectAttributes attributes,  @ModelAttribute("bearerToken") String bearerToken) {
		if (!result.hasErrors()) {
			TransactionStatus status = this.transactionService.postOperation(operation, bearerToken);
			if (status != null) {
				attributes.addFlashAttribute("status", status);
				return "redirect:/dashboard";
			}
			model.addAttribute("status", "ko");
		}
		return "operation";
	}

	@PostMapping("/transfer")
	public String postTransfer(ModelMap model, @Valid Transfer transfer, BindingResult result, Operation operation,
			RedirectAttributes attributes,  @ModelAttribute("bearerToken") String bearerToken) {
		if (!result.hasErrors()) {
			TransactionStatus status = this.transactionService.postTransfer(transfer, bearerToken);
			if (status != null) {
				attributes.addFlashAttribute("status", status);
				return "redirect:/dashboard";
			}
			model.addAttribute("status", "ko");
		}
		return "operation";
	}

	@ModelAttribute("user")
	public BankUser getCustomer(Principal userPrincipal) {
		return this.customerService.getUser(userPrincipal.getName());
	}

	@ModelAttribute("accounts")
	public List<AccountSummary> getAccountsNo(ModelMap model, @ModelAttribute("user") BankUser user, @ModelAttribute("bearerToken") String bearerToken) {
		Customer customer = this.customerService.getCustomer(user.getCustomerFirstName(), bearerToken);
		return customer.getAccountsSummary();
	}
}
