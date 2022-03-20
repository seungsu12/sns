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

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User toFollow;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User fromFollow;
//
//
//
//    @Builder
//    public Follow(User toFollow, User fromFollow) {
//        this.toFollow = toFollow;
//        this.fromFollow = fromFollow;
//    }
}
