package code.sns.api;


import code.sns.config.HashTagConfig;
import code.sns.domain.Item;
import code.sns.domain.Weather;
import code.sns.domain.dto.response.CommentResponseDto;
import code.sns.domain.dto.response.PostResponseDto;
import code.sns.domain.dto.response.UserBirthDto;
import code.sns.repository.HashTag.HashTagRepository;
import code.sns.repository.like.PostLikeRepository;
import code.sns.service.CommentService;
import code.sns.service.HashTagService;
import code.sns.service.PostService;
import code.sns.service.UserService;
import code.sns.weather.WeatherApi;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestApiController {

    private final HashTagService hashTagService;
    private final PostService postService;
    private final CommentService commentService;
    private final WeatherApi weatherApi;
    private final UserService userService;

    @GetMapping("/birth")
    public ResponseEntity birth() {

        List<UserBirthDto> birthPeople = userService.getBirthPeople(PageRequest.of(0,3));

        return ResponseEntity.status(HttpStatus.OK).body(birthPeople);
    }

    @GetMapping("/weather")
    public ResponseEntity weather() throws IOException, ParseException {

        Weather weather = weatherApi.getWeather();

        return ResponseEntity.status(HttpStatus.OK).body(weather);
    }

    @GetMapping("/comments")
    public List<CommentResponseDto> comment() {

        List<CommentResponseDto> commentById = commentService.getCommentById(3L, PageRequest.of(0, 5));
        return commentById;
    }

    @GetMapping("/hash")
    public ResponseEntity hash(@RequestBody String text) {

        List<String> hashes = HashTagConfig.extractionHash (text);
        hashTagService.IsExistHash (hashes);

        return ResponseEntity.status (HttpStatus.OK).body (null);
    }

    @GetMapping("/postLike/{userId}/")
    public ResponseEntity IsPostLike(@PathVariable("userId")Long userId , @RequestBody Map<String,String> map){
        int size = Integer.valueOf(map.get("size"));
        int page = Integer.valueOf(map.get("page"));
        List<PostResponseDto> followPost = postService.getFollowPost(userId, PageRequest.of(page,size));


        return ResponseEntity.status(HttpStatus.OK).body(followPost);
    }

    @PostMapping("/upload")
    public ResponseEntity upload(@ModelAttribute Item item) throws IOException {

        MultipartFile file = item.getFile();

        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        String path = "/Users/seungsu/front/";
        String realPath = path + file.getOriginalFilename();

        file.transferTo(new File(realPath));
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @GetMapping("/download")
    public ResponseEntity<Object> download() throws Exception{
        String path = "/Users/seungsu/front/당근.jpeg";

        Path filePath = Paths.get(path);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
