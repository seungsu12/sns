package code.sns.repository.HashTag;

import code.sns.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashTagRepository extends JpaRepository<HashTag,Long> {

    Optional<HashTag> findByTagName(String hash);
}
