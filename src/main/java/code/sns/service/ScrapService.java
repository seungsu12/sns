package code.sns.service;

import code.sns.domain.Post;
import code.sns.domain.Scrap;
import code.sns.domain.User;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.repository.post.PostRepository;
import code.sns.repository.scrap.ScrapRepository;
import code.sns.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ScrapRepository scrapRepository;

    public void createScrap(Long userId, Long postId) {

        User user = userRepository.findById (userId).orElseThrow (() -> new CustomException (ErrorCode.NOT_FOUND_USER));
        Post post = postRepository.findById (postId).orElseThrow (() -> new CustomException (ErrorCode.NOT_FOUND_POST));
        scrapRepository.save (Scrap.builder ().user (user).post (post).build ());
    }

    public void deleteScrap(Long userId, Long postId) {
        User user = userRepository.findById (userId).orElseThrow (() -> new CustomException (ErrorCode.NOT_FOUND_USER));
        Post post = postRepository.findById (postId).orElseThrow (() -> new CustomException (ErrorCode.NOT_FOUND_POST));
        scrapRepository.deleteByUserAndPost(user,post);
    }
}
