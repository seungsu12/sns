package code.sns.repository.poshHash;

import java.util.List;

public interface CustomPostHashRepository {
    List<String> getHashFromPost(Long postId);
}
