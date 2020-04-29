package com.donald.service;

import com.donald.pojo.Comment;

import java.util.List;

/**
 * @author Donald
 * @data 29/04/2020 11:38
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
