package code.sns.controller;


import code.sns.domain.dto.response.PostResponseDto;
import code.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class indexController {

    private final PostService postService;

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

    @GetMapping("explore")
    public String explore() {
        return "explore";
    }

    @GetMapping("faq")
    public String faq() {
        return "faq";
    }

    @GetMapping("/")
    public String profile(Model model) {
        List<PostResponseDto> posts = postService.getPosts();
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
}
