package com.cts.springrolejwt.service;

import com.cts.springrolejwt.model.User;
import com.cts.springrolejwt.model.UserDto;

import java.util.List;

public interface UserService {
	User save(UserDto user);

	List<User> findAll();

	User findOne(String username);
}
