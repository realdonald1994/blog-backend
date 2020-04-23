package com.donald.dao;

import com.donald.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Donald
 * @data 23/04/2020 11:11
 */
public interface TypeDao extends JpaRepository<Type,Long> {
    Type findByName(String name);
}
