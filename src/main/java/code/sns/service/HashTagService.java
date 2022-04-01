package code.sns.service;

import code.sns.domain.HashTag;
import code.sns.repository.HashTag.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private final HashTagRepository hashTagRepository;

    public void IsExistHash(List<String> hashes) {
        List<String> hashIdList = new ArrayList<> ();

        List<HashTag> hashTagList = hashTagRepository.findByTagNameIn(hashes);


        for(HashTag hashTag : hashTagList){
            String hashTagName = hashTag.getTagName();

            //리스트와 리스트를 비교해서 없는 데이터만 따로 빼기
            //뺀 String hashtag 리스트 데이터를 for 문으로 객체를 생성
            //생성된 객체 리스트를 저장
        }


        for (String hash : hashes) {
            hashTagRepository.findByTagName (hash).ifPresent (h ->{
//                    hashIdList.add (h.getId ());
            });


        }

    }

//    public List<String> getHashTags(int size) {
//        hashTagRepository.getHashTags
//    }
}
