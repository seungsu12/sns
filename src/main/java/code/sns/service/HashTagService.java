package code.sns.service;

import code.sns.domain.HashTag;
import code.sns.repository.hashTag.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HashTagService {

    private final HashTagRepository hashTagRepository;

    public List<HashTag> IsExistHash(List<String> hashes) {
        List<HashTag> hashTagList = new ArrayList<> ();


        for (String hash : hashes) {
            HashTag hashTag = hashTagRepository.findByTagName(hash).orElse(null);

            if(hashTag !=null){
                hashTagList.add(hashTag);
                continue;
            }
            hashTagList.add(hashTagRepository.save(new HashTag(hash)));
        }

        return  hashTagList;

    }
}
