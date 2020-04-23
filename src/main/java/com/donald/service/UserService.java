package com.donald.service;

import com.donald.pojo.User;

/**
 * @author Donald
 * @data 22/04/2020 22:29
 */
public interface UserService {

    User checkUser(String username, String password);
}
