package code.sns.api;

import code.sns.auth.PrincipalDetail;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostLikeApiController {

    private final PostLikeService postLikeService;

    @PostMapping("/api/postLike/{postId}")
    public ResponseEntity postLike(@PathVariable("postId")Long postId, Authentication authentication) {
        Long userId = authCheck(authentication);
        boolean existLike = postLikeService.postLike(userId, postId);

        return ResponseEntity.status(HttpStatus.OK).body(existLike);
    }


    private Long authCheck(Authentication authentication) {
        if (authentication == null) {
            throw new CustomException(ErrorCode.FORBIDDEN_USER);
        }
        PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
        return  principal.getId();
    }
}
