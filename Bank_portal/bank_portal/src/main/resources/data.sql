
-- DEFAULTS ROLES 
INSERT INTO `authority` (`id`, `name`) VALUES
(1, 'ROLE_EMPLOYEE'),
(2, 'ROLE_CUSTOMER'); 

-- DEFAULT BANK EMPLOYEE
INSERT INTO `bank_user` (`id`, `customer_id`, `email`, `password`, `role`, `customer_first_name`) VALUES
(1, NULL, 'pod2@bank.com', '$2a$10$13CxbuEZnqoNI5YPXGRIy.z2eUhvhrKumjqLKdq3lUuPkh3Eqtl.W', 'EMPLOYEE', NULL);
INSERT INTO `bank_user_authorities` (`bank_user_id`, `authorities_id`) VALUES
(1, 1);
