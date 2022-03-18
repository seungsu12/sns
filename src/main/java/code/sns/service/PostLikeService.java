package code.sns.service;

import code.sns.domain.Post;
import code.sns.domain.PostLike;
import code.sns.domain.User;
import code.sns.repository.PostLikeRepository;
import code.sns.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public boolean addLike(User user, Long post_id) {

        Post post = postRepository.findById(post_id).orElseThrow();

        if (isNotAlreadyLike(user, post)) {
            postLikeRepository.save(new PostLike(user,post));
            return true;
        }
        return false;
    }

    private boolean isNotAlreadyLike(User user, Post post) {
        return postLikeRepository.findByUserAndPost(user,post).isEmpty();
    }
}
