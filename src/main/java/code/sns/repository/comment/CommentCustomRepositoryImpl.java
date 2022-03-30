package code.sns.repository.comment;

import code.sns.domain.dto.response.CommentResponseDto;

import code.sns.domain.dto.response.QCommentResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static code.sns.domain.QComment.comment;
import static code.sns.domain.QUser.user;

@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentResponseDto> getCommentByIdDto(Long postId, Pageable pageable) {
        List<CommentResponseDto> result
                = queryFactory.select(new QCommentResponseDto(
                        comment.id,
                        user.id,
                        user.username,
                        comment.context,
                        user.profile_img,
                        comment.created_at
                )).from(comment)
                .leftJoin(comment.user,user)
                .where(comment.post.id.eq(postId))
                .orderBy (comment.created_at.asc())
                .limit (pageable.getPageSize ())
                .offset (pageable.getOffset ())
                .fetch();
        return result;
    }

    @Override
    public CommentResponseDto getCommentById(Long id) {
        return queryFactory.select(new QCommentResponseDto(
                        comment.id,
                        user.id,
                        user.username,
                        comment.context,
                        user.profile_img,
                        comment.created_at
                )).from(comment)
                .leftJoin(comment.user,user)
                .where(comment.id.eq(id))
                .fetchOne();
    }
}
