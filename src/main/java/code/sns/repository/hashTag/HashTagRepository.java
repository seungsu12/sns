package code.sns.repository.hashTag;

import code.sns.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HashTagRepository extends JpaRepository<HashTag,Long> {

    Optional<HashTag> findByTagName(String hash);
    List<HashTag> findByTagNameIn(List<String> tagNameList);
}
