package code.sns.api;


import code.sns.domain.dto.CommentRequestDto;
import code.sns.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/comment/{post_id}/{user_id}")
    public ResponseEntity createComment(@RequestBody CommentRequestDto requestDto) {

        commentService.createComment(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
