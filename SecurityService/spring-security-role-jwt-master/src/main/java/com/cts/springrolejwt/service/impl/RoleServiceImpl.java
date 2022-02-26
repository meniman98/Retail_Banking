package com.cts.springrolejwt.service.impl;

import com.cts.springrolejwt.dao.RoleDao;
import com.cts.springrolejwt.model.Role;
import com.cts.springrolejwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Role findByName(String name) {
		Role role = roleDao.findRoleByName(name);
		return role;
	}
}
