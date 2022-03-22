package code.sns.controller;


import code.sns.auth.PrincipalDetail;
import code.sns.domain.dto.response.FollowResponseDto;
import code.sns.domain.dto.response.PostResponseDto;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.service.FollowService;
import code.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class indexController {

    private final PostService postService;
    private final FollowService followService;

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/edit-profile")
    public String edit_profile() {
        return "edit-profile";
    }

    @GetMapping("/explore")
    public String explore() {
        return "explore";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

    @GetMapping("/")
    public String profile(Model model,Authentication authentication) {

        List<FollowResponseDto> followList;
        List<PostResponseDto> posts;

        if(authentication==null){
                followList = followService.getBasicList();
        }else{
            Long id = authCheck(authentication);
            followList =followService.getFollowList(id);
        }
          posts =postService.getPosts();


        model.addAttribute("followList",followList);
        model.addAttribute("posts",posts);
        return "index";
    }

    @GetMapping("/tags")
    public String tags() {
        return "tags";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/logout")
    public void logout() {
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/test")
    public String test(Authentication authentication) {

        PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
        log.info("principal {}", principal.getUsername());
        log.info("principal {}", principal.getId());


        return "test";
    }
    private Long authCheck(Authentication authentication) {
        if (authentication == null) {
            throw new CustomException(ErrorCode.FORBIDDEN_USER);
        }
        PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
        return  principal.getId();
    }
}
