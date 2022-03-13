package code.sns.domain;


import javax.persistence.*;

@Entity
public class Follow {


    @GeneratedValue
    @Id
    @Column(name ="follow_id")
    private Long id;

    private Long user_id;

    private Long follow_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    private User user;
}
