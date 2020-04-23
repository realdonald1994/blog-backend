package com.donald.service;

import com.donald.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Donald
 * @data 23/04/2020 11:02
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id,Type type);

    void deleteType(Long id);

    Type getTypeByName(String name);
}
