package com.donald.dao;

import com.donald.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Donald
 * @data 22/04/2020 22:31
 */
public interface UserDao extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
