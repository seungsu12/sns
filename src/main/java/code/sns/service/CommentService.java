package code.sns.service;


import code.sns.domain.Comment;
import code.sns.domain.Post;
import code.sns.domain.User;
import code.sns.domain.dto.CommentRequestDto;
import code.sns.exception.NotFoundObjectException;
import code.sns.repository.CommentJpaRepository;
import code.sns.repository.PostJpaRepository;
import code.sns.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentJpaRepository commentJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final PostJpaRepository postJpaRepository;


    public void createComment(CommentRequestDto requestDto) {

        User user = userJpaRepository.findById(requestDto.getUser_id())
                .orElseThrow(()->new NotFoundObjectException("해당 유저가 없습니다."));
        Post post = postJpaRepository.findById(requestDto.getPost_id())
                .orElseThrow(()->new NotFoundObjectException("해당 게시물이 없습니다."));

        Comment comment = Comment.builder()
                .context(requestDto.getContext())
                .post(post)
                .user(user).build();

        commentJpaRepository.createComment(comment);

    }
}
