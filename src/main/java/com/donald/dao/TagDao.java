package com.donald.dao;

import com.donald.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Donald
 * @data 23/04/2020 14:19
 */
public interface TagDao extends JpaRepository<Tag,Long> {

    Tag findByName(String name);
}
