package code.sns.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","email","username","profile","birth","nickname"})
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    private String username;

    private String profile;

    private String profile_img;

    private LocalDate birth;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user")
    List<Follow> follows = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    List<Comment> comments =new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public static User JoinUser(String email, String password, String username, LocalDate birth, String nickname,Gender gender) {
        User user = new User();
        user.email = email;
        user.password = password;
        user.username =username;
        user.nickname = nickname;
        user.birth = birth;
        user.gender = gender;
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
