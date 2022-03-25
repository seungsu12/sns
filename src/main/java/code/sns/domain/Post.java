package code.sns.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","context","user"})
public class Post extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "post_id")
    private Long id;

    private String context;

    @Embedded
    private UploadFile uploadFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    List<Scrap> scraps = new ArrayList<>();

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    List<PostHash> postHashes = new ArrayList<> ();

    public static Post createPost(String context,UploadFile uploadFile,User user) {
        Post post = new Post();
        post.context = context;
        post.uploadFile = uploadFile;
        post.changeUser(user);
        return post;
    }

    public void changeUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }
}
