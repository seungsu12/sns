package code.sns.repository;


import code.sns.domain.Comment;
import code.sns.domain.QComment;
import code.sns.domain.QUser;
import code.sns.domain.dto.CommentResponseDto;
import code.sns.domain.dto.QCommentResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static code.sns.domain.QComment.comment;
import static code.sns.domain.QUser.user;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentJpaRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Transactional
    public void createComment(Comment comment) {
        em.persist(comment);
    }

    public List<CommentResponseDto> getCommentByIdDto(Long id) {

        List<CommentResponseDto> result
                = queryFactory.select(new QCommentResponseDto(
                        comment.id,
                        user.id,
                        user.username,
                        comment.context,
                        user.profile_img
                )).from(comment)
                .leftJoin(comment.user, user)
                .where(comment.post.id.eq(id))
                .fetch();
        return result;
    }
}
