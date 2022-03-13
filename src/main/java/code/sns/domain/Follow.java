package code.sns.domain;


import javax.persistence.*;

@Entity
public class Follow {


    @GeneratedValue
    @Id
    @Column(name ="follow_id")
    private Long id;

    private Long follower;

    private Long followed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
