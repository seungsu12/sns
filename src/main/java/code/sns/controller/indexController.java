package code.sns.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

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
    public String profile() {
        return "profile";
    }

    @GetMapping("/tags")
    public String tags() {
        return "tags";
    }
}
