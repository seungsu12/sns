package code.sns.domain;


import code.sns.domain.enums.Gender;
import code.sns.domain.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","email","username","profile","birth","nickname","role"})
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    private String username;

    private String profile;

    private String profile_img;

    private LocalDate birth;

    private String nickname;

    private String job;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    @OneToMany(mappedBy = "toFollow")
    List<Follow> toFollower = new ArrayList<>();

    @OneToMany(mappedBy = "fromFollow")
    List<Follow> fromFollower = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Comment> comments =new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();



    @Builder
    public static User JoinUser(String email, String password, String username, LocalDate birth, String nickname,Gender gender,Role role) {
        User user = new User();
        user.email = email;
        user.password = password;
        user.username =username;
        user.nickname = nickname;
        user.birth = birth;
        user.gender = gender;
        user.role =role;
        return user;
    }

    @Builder
    public User(String email, String password, String username, String profile, String profile_img, LocalDate birth, String nickname, Gender gender) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.profile = profile;
        this.profile_img = profile_img;
        this.birth = birth;
        this.nickname = nickname;
        this.gender = gender;
    }


}
