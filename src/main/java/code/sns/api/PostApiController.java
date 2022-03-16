package code.sns.api;


import code.sns.domain.dto.PostRequestDto;
import code.sns.domain.dto.PostResponseDto;
import code.sns.service.CommentService;
import code.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/post")
    public ResponseEntity createPost(@ModelAttribute PostRequestDto requestDto) throws IOException {

        postService.createPost(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/post/{id}")
    public ResponseEntity getPostById(@PathVariable("id")Long id, Model model) {
      return ResponseEntity.status(HttpStatus.OK).body(postService.getPostById(id));

    }


    @GetMapping("/posts")
    public ResponseEntity getPosts() {

        List<PostResponseDto> posts = postService.getPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

}
