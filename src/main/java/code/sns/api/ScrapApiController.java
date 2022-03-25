package code.sns.api;

import code.sns.auth.PrincipalDetail;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ScrapApiController {

    private final ScrapService scrapService;

    @PostMapping("/scrap/{postId}")
    public ResponseEntity createScrap(@PathVariable("postId")Long postId , Authentication authentication) {
        Long userId = authCheck (authentication);
        scrapService.createScrap(userId,postId);
        return ResponseEntity.status (HttpStatus.OK).body ("ok");
    }

    @DeleteMapping("/scarp/{postId}")
    public ResponseEntity deleteScrap(@PathVariable("postId")Long postId , Authentication authentication) {

        Long userId = authCheck (authentication);
        scrapService.deleteScrap(userId,postId);
        return ResponseEntity.status (HttpStatus.OK).body ("ok");
    }

    private Long authCheck(Authentication authentication) {
        if (authentication == null) {
            throw new CustomException (ErrorCode.FORBIDDEN_USER,"권한이 없습니다.");
        }
        PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
        return  principal.getId();
    }
}
