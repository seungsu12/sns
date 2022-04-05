package code.sns.api;

import code.sns.config.auth.AuthUtil;
import code.sns.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScrapApiController {

    private final ScrapService scrapService;

    @PostMapping("/scrap/{postId}")
    public ResponseEntity createScrap(@PathVariable("postId")Long postId , Authentication authentication) {
        Long userId = AuthUtil.getAuthenticationUserId ();
        boolean existScrap = scrapService.createScrap(userId, postId);
        return ResponseEntity.status (HttpStatus.OK).body(existScrap);
    }

    @DeleteMapping("/scarp/{postId}")
    public ResponseEntity deleteScrap(@PathVariable("postId")Long postId , Authentication authentication) {

        Long userId = AuthUtil.getAuthenticationUserId ();
        
        scrapService.deleteScrap(userId,postId);
        return ResponseEntity.status (HttpStatus.OK).body ("ok");
    }


}
