package code.sns.api;


import code.sns.auth.PrincipalDetail;
import code.sns.domain.UploadFile;
import code.sns.domain.dto.request.PostRequestDto;
import code.sns.domain.dto.response.PostResponseDto;
import code.sns.domain.dto.response.PostResponseLoginDto;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.service.CommentService;
import code.sns.service.PostService;
import code.sns.upload.FileStore;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostApiController {

    private final PostService postService;
    private final CommentService commentService;

    @ApiOperation(value = "피드 생성", notes = "정볼를 받아서 피드생성")
    @PostMapping("/post")
    public ResponseEntity createPost(@ModelAttribute PostRequestDto requestDto,Authentication authentication) throws IOException {

        Long userId = authCheck(authentication);
        log.info("file {}",requestDto.getFile());
        log.info("context {}",requestDto.getContext());

        requestDto.setUser_id(userId);

        postService.createPost(requestDto);


        return ResponseEntity.status(HttpStatus.OK).body("");


    }



    @GetMapping("/api/post/{postId}")
    public ResponseEntity findById(@PathVariable("postId") Long id) {
        PostResponseDto dto = postService.getPostById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @GetMapping("/posts")
    public ResponseEntity getPosts() {

        List<PostResponseDto> posts = postService.getPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @DeleteMapping("/post/delete")
    public ResponseEntity deletePost(@RequestBody Map<String,Long> map, Authentication authentication) {

        Long loginUserId = authCheck(authentication);
        Long userId = map.get("userId");
        ownerCheck(loginUserId,userId);
        log.info("userId {}",userId);
        log.info("postId {}",map.get("postId"));
        postService.deleteById(map.get("postId"));
        return ResponseEntity.status(HttpStatus.OK).body("삭제완료");

    }

    @GetMapping("/api/login/posts")
    public ResponseEntity getPostsLogin() {
        List<PostResponseLoginDto> result = postService.getPostsLogin(1L, Pageable.ofSize(3));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    private Long authCheck(Authentication authentication) {
        if (authentication == null) {
            throw new CustomException(ErrorCode.FORBIDDEN_USER,"권한이 없습니다.");
        }
        PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
        return  principal.getId();
    }

    private void ownerCheck(Long loginId, Long postUserId) {
        if (!loginId.equals(postUserId)) {
            throw new CustomException(ErrorCode.FORBIDDEN_AUTHORITY);
        }
    }
}
