package code.sns.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @GeneratedValue
    @Id
    @Column(name = "post_id")
    private Long id;


    private String context;

    private String imgPath;

    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Post createPost(String context,String imgPath,User user) {
        Post post = new Post();
        post.context = context;
        post.imgPath = imgPath;
        post.changeUser(user);
        return post;
    }

    public void changeUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }
}
