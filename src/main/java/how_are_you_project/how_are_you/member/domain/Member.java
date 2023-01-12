package how_are_you_project.how_are_you.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import how_are_you_project.how_are_you.question.domain.MemberQuestion;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "login_password")
    private String loginPassword;

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    private String birth;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "member")//읽기전용
    private List<MemberQuestion> memberQuestion = new ArrayList<>();

    @Builder
    public Member(String loginId, String loginPassword, String name, String birth, String email, List<Role>roles) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.roles = roles;
    }


    @ElementCollection(fetch = FetchType.EAGER) //?eager 확인
    public List <Role> roles;

//    @JsonIgnore
//    @OneToMany(mappedBy = "board")//읽기전용
//    private List<Board> feelingList = new ArrayList<>();


}
