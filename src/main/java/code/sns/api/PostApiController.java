package code.sns.api;


import code.sns.domain.dto.request.PostRequestDto;
import code.sns.domain.dto.response.PostResponseDto;
import code.sns.service.CommentService;
import code.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/api/post/{id}")
    public ResponseEntity findById(@PathVariable("id")Long id) {
        PostResponseDto dto = postService.getPostById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @GetMapping("/posts")
    public ResponseEntity getPosts() {

        List<PostResponseDto> posts = postService.getPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

}
