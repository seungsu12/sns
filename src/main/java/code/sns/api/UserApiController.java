package code.sns.api;


import code.sns.domain.User;
import code.sns.domain.dto.request.UserRequestDto;
import code.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserApiController {


    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody @Valid UserRequestDto requestDto) {

        userService.join(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body("회원가입완료");
    }

    @PutMapping("/user/{id}")
    public ResponseEntity updateUser(@RequestBody UserRequestDto requestDto) {

        User user = userService.updateUser(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body("user");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        User findUser = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(findUser);
    }
}
