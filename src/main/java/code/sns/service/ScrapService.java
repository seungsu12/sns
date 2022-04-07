package code.sns.service;

import code.sns.config.stomp.NoticeManger;
import code.sns.config.stomp.NoticeMessage;
import code.sns.domain.Post;
import code.sns.domain.Scrap;
import code.sns.domain.User;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.repository.post.PostRepository;
import code.sns.repository.scrap.ScrapRepository;
import code.sns.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.transform.trait.Traits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScrapService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ScrapRepository scrapRepository;
    private final NoticeManger noticeManger;

    public boolean createScrap(Long userId, Long postId) {

        User user = userRepository.findById (userId).orElseThrow (() -> new CustomException (ErrorCode.NOT_FOUND_USER));
        Post post = postRepository.findById (postId).orElseThrow (() -> new CustomException (ErrorCode.NOT_FOUND_POST));

        if (!existsScrap(user, post)) {
            scrapRepository.save (Scrap.builder ().user (user).post (post).build ());
            Long fromId = post.getUser().getId();
            noticeManger.sendNotice(fromId, NoticeMessage.createMessage(user.getUsername(),user.getProfile_img(), NoticeMessage.Type.scrap));
            return true;
        }

        scrapRepository.deleteByUserAndPost(user,post);
        return false;
    }

    public void deleteScrap(Long userId, Long postId) {
        User user = userRepository.findById (userId).orElseThrow (() -> new CustomException (ErrorCode.NOT_FOUND_USER));
        Post post = postRepository.findById (postId).orElseThrow (() -> new CustomException (ErrorCode.NOT_FOUND_POST));
        scrapRepository.deleteByUserAndPost(user,post);
    }

    private boolean existsScrap(User user, Post post) {
        return scrapRepository.existsByUserAndPost(user,post);
    }
}
