package com.example.project.Service;

import com.example.project.Entity.Comment;
import com.example.project.Exception.ResourceNotFoundException;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();
    Comment getComment(Long id) throws ResourceNotFoundException;
    Comment createComment(Comment comment) throws ResourceNotFoundException;
    void deleteCommentById(Long id);
    Comment updateCommentById(Long id, Comment comment) throws ResourceNotFoundException;
}
