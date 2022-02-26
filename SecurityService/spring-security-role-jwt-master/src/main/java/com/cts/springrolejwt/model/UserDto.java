package com.cts.springrolejwt.model;

import lombok.Data;

@Data
public class UserDto {
    
    private String username;
    private String password;

    public User getUserFromDto(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        
        return user;
    }
    
}