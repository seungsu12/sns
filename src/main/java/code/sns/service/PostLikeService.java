package code.sns.service;

import code.sns.domain.Post;
import code.sns.domain.PostLike;
import code.sns.domain.User;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.repository.like.PostLikeRepository;
import code.sns.repository.post.PostRepository;
import code.sns.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public boolean postLike(Long userId, Long postId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.FORBIDDEN_USER));
        Post post = postRepository.findById(postId).orElseThrow(()-> new CustomException(ErrorCode.BAD_REQUEST_POST));

        if (existLike(user, post)) {
            postLikeRepository.save(new PostLike(user,post));
            return true;
        }
        postLikeRepository.deleteByUserAndPost(user,post);
        return false;
    }

    private boolean existLike(User user, Post post) {
        return postLikeRepository.findByUserAndPost(user,post).isEmpty();
    }
}
