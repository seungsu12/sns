package code.sns.service;

import code.sns.domain.dto.PostResponseDto;
import code.sns.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;

    public List<PostResponseDto> getPosts() {

        return postJpaRepository.getPosts();
    }
}
