package code.sns.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class HashTag {

    @Id
    @Column(name = "hashTag_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tagName;

    public HashTag(String tagName) {
        this.tagName = tagName;
    }

    @OneToMany(mappedBy = "hashTag")
    List<PostHash> postHashes = new ArrayList<> ();
}
