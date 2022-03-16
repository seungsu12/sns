package code.sns.controller;

import code.sns.service.CommentService;
import code.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;


    @GetMapping("/post/{id}")
    public void getPostById(@PathVariable("id")Long id, Model model) {
        model.addAttribute("post",postService.getPostById(id));
        commentService.getCommentById(id);

    }
}
