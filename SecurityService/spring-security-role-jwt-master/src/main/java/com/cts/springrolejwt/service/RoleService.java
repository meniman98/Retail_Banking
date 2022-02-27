package com.cts.springrolejwt.service;

import com.cts.springrolejwt.model.Role;

public interface RoleService {
    Role findByName(String name);
}
