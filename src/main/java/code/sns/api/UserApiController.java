package code.sns.api;


import code.sns.domain.User;
import code.sns.domain.dto.UserRequestDto;
import code.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserApiController {


    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody @Valid UserRequestDto requestDto) {

        userService.join(requestDto);


        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity findById(@PathVariable("id")Long id) {

        User findUser = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(findUser);
    }
}
