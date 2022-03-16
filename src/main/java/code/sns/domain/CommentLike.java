package code.sns.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentlike_id")
    private Long id;



}
