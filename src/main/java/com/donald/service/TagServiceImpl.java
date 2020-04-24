package com.donald.service;

import com.donald.NotFoundException;
import com.donald.dao.TagDao;
import com.donald.pojo.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Donald
 * @data 23/04/2020 14:23
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
       return tagDao.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        Optional<Tag> optional = tagDao.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagDao.findAll(pageable);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Optional<Tag> optional = tagDao.findById(id);
        if(!optional.isPresent()){
            throw new NotFoundException("tag is not exist");
        }
        Tag t = optional.get();
        BeanUtils.copyProperties(tag,t);
        return tagDao.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagDao.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.findByName(name);
    }
}
