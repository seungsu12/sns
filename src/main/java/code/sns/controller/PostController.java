package code.sns.controller;

import code.sns.service.CommentService;
import code.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;


}
