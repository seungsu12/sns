package code.sns.api;


import code.sns.domain.dto.request.CommentRequestDto;
import code.sns.domain.dto.response.CommentResponseDto;
import code.sns.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/comment/{post_id}/{user_id}")
    public ResponseEntity createComment(@RequestBody CommentRequestDto requestDto) {

        commentService.createComment(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/api/comment/{postId}")
    public List<CommentResponseDto> getCommentsByPostId(@PathVariable("postId")Long postId, Model model){
        Pageable pageable = Pageable.ofSize (5);
        List<CommentResponseDto> result = commentService.getCommentById(postId,pageable);
        model.addAttribute ("comments",result);
        log.info ("comments {}",result);
        return result;
    }
}
