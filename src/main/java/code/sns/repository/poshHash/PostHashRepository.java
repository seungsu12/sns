package code.sns.repository.poshHash;

import code.sns.domain.PostHash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostHashRepository extends JpaRepository<PostHash,Long>,CustomPostHashRepository {
}
