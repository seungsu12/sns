package code.sns.repository.comment;

import code.sns.domain.dto.CommentResponseDto;
import code.sns.domain.dto.QCommentResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static code.sns.domain.QComment.comment;
import static code.sns.domain.QUser.user;

@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
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
