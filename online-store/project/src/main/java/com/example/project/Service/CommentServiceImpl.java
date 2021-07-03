package com.example.project.Service;

import com.example.project.Entity.Comment;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getComment(Long id) throws ResourceNotFoundException {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find comment with id ", id));
    }

    @Override
    public Comment createComment(Comment comment) throws ResourceNotFoundException {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment updateCommentById(Long id, Comment comment) throws ResourceNotFoundException {
        return commentRepository.findById(id)
                .map(newComment -> {
                    newComment.setText(comment.getText());
                    return commentRepository.save(newComment);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find comment with id ", id));
    }
}
