package code.sns.api;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowApiController {

    @PostMapping("/follow")
    public ResponseEntity follow() {

        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
