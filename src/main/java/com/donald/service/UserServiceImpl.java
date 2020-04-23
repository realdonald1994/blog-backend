package com.donald.service;

import com.donald.dao.UserDao;
import com.donald.pojo.User;
import com.donald.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Donald
 * @data 22/04/2020 22:30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.findByUsernameAndPassword(username, MD5Util.code(password));
    }
}
