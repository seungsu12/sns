package code.sns.service;

import code.sns.repository.poshHash.PostHashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostHashService {

    private final PostHashRepository postHashRepository;


    public Iterator<String> getHashTagList(List<Long> postIdList) {
        List<String> result = new ArrayList<>();
        for (Long postId : postIdList) {
            result.add(postHashRepository.getHashFromPost(postId).stream().
                    map(s -> s.toString()).collect(Collectors.joining()));


        }

        return result.iterator();
    }
}
