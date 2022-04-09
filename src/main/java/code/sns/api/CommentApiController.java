package code.sns.api;


import code.sns.config.auth.AuthUtil;
import code.sns.domain.dto.request.CommentRequestDto;
import code.sns.domain.dto.response.CommentResponseDto;
import code.sns.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity createComment(@RequestBody CommentRequestDto requestDto) {
        Long userId = AuthUtil.getAuthenticationUserId();
        requestDto.setUserId(userId);
        CommentResponseDto comment = commentService.createComment(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @GetMapping("/api/comment/{postId}")
    public List<CommentResponseDto> getCommentsByPostId(@PathVariable("postId")Long postId){

        int page = 0;
        int size =5;
        List<CommentResponseDto> result = commentService.getCommentById(postId, PageRequest.of(page,size));

        return result;
    }
}
