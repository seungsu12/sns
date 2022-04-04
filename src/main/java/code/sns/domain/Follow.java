package code.sns.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Follow {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name ="follow_id")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toFollower")
    private User toFollow;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fromFollower")
    private User fromFollow;



    @Builder
    public Follow(User toFollow, User fromFollow) {
        this.toFollow = toFollow;
        this.fromFollow = fromFollow;
    }
}
