package com.example.project.Controller;


import com.example.project.Entity.Comment;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id) throws ResourceNotFoundException {
        return commentService.getComment(id);
    }

    @PutMapping("/{id}")
    public Comment updateCommentById(@PathVariable Long id, @RequestBody Comment comment) throws ResourceNotFoundException {
        return commentService.updateCommentById(id, comment);
    }

    @DeleteMapping("/{id}")
    public void deleteCommentById(@PathVariable Long id) {
        commentService.deleteCommentById(id);
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) throws ResourceNotFoundException {
        return commentService.createComment(comment);
    }
}
