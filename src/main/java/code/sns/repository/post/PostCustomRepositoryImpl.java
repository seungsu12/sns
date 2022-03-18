package code.sns.repository.post;

import code.sns.domain.dto.response.PostResponseDto;

import code.sns.domain.dto.response.QPostResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static code.sns.domain.QPost.post;
import static code.sns.domain.QUser.user;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<PostResponseDto> findByIdDto(Long id) {

        return Optional.ofNullable(queryFactory.select(new QPostResponseDto(
                        user.id,
                        post.id,
                        user.profile_img,
                        user.username,
                        user.nickname,
                        post.context,
                        post.uploadFile.storeFileName,
                        post.created_at,
                        post.postLikes.size(),
                        post.comments.size()
                )).from(post)
                .leftJoin(post.user, user)
                .where(post.id.eq(id))
                .fetchOne());
    }

    @Override
    public List<PostResponseDto> getPosts() {
        List<PostResponseDto> result = queryFactory.select(new QPostResponseDto(
                        user.id,
                        post.id,
                        user.profile_img,
                        user.username,
                        user.nickname,
                        post.context,
                        post.uploadFile.storeFileName,
                        post.created_at,
                        post.postLikes.size(),
                    post.comments.size()
                )).from(post)
                .leftJoin(post.user, user)
                .fetch();

        return result;
    }
}
