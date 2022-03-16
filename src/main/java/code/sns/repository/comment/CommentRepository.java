package code.sns.repository.comment;

import code.sns.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long>,CommentCustomRepository {
}
