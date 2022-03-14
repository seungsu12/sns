package code.sns.api;


import code.sns.domain.dto.PostResponseDto;
import code.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @PostMapping


    @GetMapping("/posts")
    public ResponseEntity getPosts() {

        List<PostResponseDto> posts = postService.getPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

}
