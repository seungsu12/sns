package code.sns.api;

import code.sns.auth.PrincipalDetail;
import code.sns.config.util.AuthUtil;
import code.sns.domain.dto.response.FollowResponseDto;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FollowApiController {

    private final FollowService followService;

    @PostMapping("/follow/{toId}")
    public ResponseEntity follow(@PathVariable("toId")Long aLong,Authentication authentication) {
        Long loginId = AuthUtil.getAuthenticationUserId();

        followService.follow(aLong,loginId);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/follow/list/{id}")
    public List<FollowResponseDto> getUnFollowList(@PathVariable("id")Long id) {

        List<FollowResponseDto> list =followService.getUnFollowList(id);

        return list;
    }

}
