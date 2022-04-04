package code.sns.domain;


import code.sns.domain.enums.Gender;
import code.sns.domain.enums.Job;
import code.sns.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @ColumnDefault("basic.jpg")
    private String profile_img;

    private LocalDate birth;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Job job;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonManagedReference
    @OneToMany(mappedBy = "toFollow")
    List<Follow> toFollower = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "fromFollow")
    List<Follow> fromFollower = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    List<Comment> comments =new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Scrap> scraps = new ArrayList<>();


    @Builder
    public static User JoinUser(String email, String password, String username, LocalDate birth, String nickname,Gender gender,Job job,Role role) {
        User user = new User();
        user.email = email;
        user.password = password;
        user.username =username;
        user.nickname = nickname;
        user.birth = birth;
        user.gender = gender;
        user.role =role;
        user.job = job;
        user.profile_img="basic.jpg";
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
