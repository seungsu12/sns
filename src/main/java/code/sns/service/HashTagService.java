package code.sns.service;

import code.sns.domain.HashTag;
import code.sns.repository.HashTag.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private final HashTagRepository hashTagRepository;

    public void IsExistHash(List<String> hashes) {

//        for (String hash : hashes) {
//            hashTagRepository.findByTagName (hash).isEmpty (()->{
//                hashTagRepository.save (new HashTag (hash));
//            });
//
        }

    }
}
