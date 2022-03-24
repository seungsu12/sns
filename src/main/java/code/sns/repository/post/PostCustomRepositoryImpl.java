package code.sns.repository.post;

import code.sns.domain.QPostLike;
import code.sns.domain.dto.response.PostResponseDto;

import code.sns.domain.dto.response.PostResponseLoginDto;
import code.sns.domain.dto.response.QPostResponseDto;
import code.sns.domain.dto.response.QPostResponseLoginDto;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static code.sns.domain.QFollow.follow;
import static code.sns.domain.QPost.post;
import static code.sns.domain.QPostLike.postLike;
import static code.sns.domain.QUser.user;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<PostResponseDto> findByIdDto(Long postId) {

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
                .where(post.id.eq(postId))
                .orderBy (post.created_at.desc ())
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
                .orderBy (post.created_at.desc ())
                .fetch();

        return result;
    }
    @Override
    public Page<PostResponseLoginDto> getPostsByUserId(Long userId, Pageable pageable) {
        List<PostResponseLoginDto> fetch = queryFactory.select(new QPostResponseLoginDto(
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
                .where(user.id.eq(userId))
                .orderBy(post.created_at.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(fetch,pageable, fetch.size());
    }

    @Override
    public Page<PostResponseLoginDto> getPostsLogin(Long id, Pageable pageable) {
       return null;
    }
}
