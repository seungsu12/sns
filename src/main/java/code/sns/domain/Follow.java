package code.sns.domain;


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

    @ManyToOne
    @JoinColumn(name = "toFollower")
    private User toFollow;

    @ManyToOne
    @JoinColumn(name = "fromFollower")
    private User fromFollow;



    @Builder
    public Follow(User toFollow, User fromFollow) {
        this.toFollow = toFollow;
        this.fromFollow = fromFollow;
    }
}
