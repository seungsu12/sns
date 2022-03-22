package code.sns.controller;


import code.sns.auth.PrincipalDetail;
import code.sns.domain.User;
import code.sns.domain.dto.response.FollowResponseDto;
import code.sns.domain.dto.response.PostResponseDto;
import code.sns.domain.dto.response.UserProfileDto;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.repository.user.UserRepository;
import code.sns.service.FollowService;
import code.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
@Slf4j
public class indexController {

    private final PostService postService;
    private final FollowService followService;
    private final UserRepository userRepository;

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

    @GetMapping("/profile")
    public String profile(Authentication authentication,Model model) {
        Long id = authCheck(authentication);
        
        Pageable pageable = Pageable.ofSize(3);
        UserProfileDto profile = userRepository.getProfile(id);
        List<String> toFollowImg = userRepository.getToFollowImg(id);
        List<String> fromFollowImg = userRepository.getFromFollowImg(id);
        List<PostResponseDto> posts = postService.getPostsById(id, pageable);

        model.addAttribute("user",profile);
        model.addAttribute("toFollow",toFollowImg);
        model.addAttribute("fromFollow",fromFollowImg);
        model.addAttribute("posts",posts);
        return "profile";
    }



    private Long authCheck(Authentication authentication) {
        if (authentication == null) {
            throw new CustomException(ErrorCode.FORBIDDEN_USER);
        }
        PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
        return  principal.getId();
    }
}
