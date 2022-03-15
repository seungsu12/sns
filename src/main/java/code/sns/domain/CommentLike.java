package code.sns.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class CommentLike {

    @Id
    @GeneratedValue
    @Column(name = "commentlike_id")
    private Long id;



}
