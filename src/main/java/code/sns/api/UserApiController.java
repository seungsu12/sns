package code.sns.api;


import code.sns.domain.User;
import code.sns.domain.dto.request.UserRequestDto;
import code.sns.domain.dto.response.UserProfileDto;
import code.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {


    private final UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity login(@RequestBody Map<String, Object> map) {
        log.info("login 실행");
        return ResponseEntity.status(HttpStatus.OK).body("로그인");
    }


    @PostMapping("/user/signup")
    public ResponseEntity signUp(@RequestBody @Valid UserRequestDto requestDto) throws IOException {

        userService.join(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body("회원가입완료");
    }

    @PutMapping("/api/user")
    public ResponseEntity updateUser(@RequestBody @Valid UserRequestDto requestDto) {

        User user = userService.updateUser(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body("user");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        User findUser = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(findUser);
    }

    @GetMapping("/user/profile/{id}")
    public ResponseEntity getProfile(@PathVariable("id") Long id) {

        UserProfileDto profile = userService.getProfile(id);
        return ResponseEntity.status(HttpStatus.OK).body(profile);
    }

    @GetMapping("/user/profile/tofollow/{id}")
    public ResponseEntity getToFollowImg(@PathVariable("id") Long id) {

        List<String> result = userService.getToFollowImg(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
