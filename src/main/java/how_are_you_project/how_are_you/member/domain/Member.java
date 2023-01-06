package how_are_you_project.how_are_you.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import how_are_you_project.how_are_you.board.domain.Board;
import how_are_you_project.how_are_you.order.domain.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;

    private String userName;

    @JsonIgnore
    private String password; //노출되면 안되기 때문

    private String name;

    private String birth;

    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "member")//읽기전용
    private List<Order> orders = new ArrayList<>();


//
//    public List<Role> roles;

//    @JsonIgnore
//    @OneToMany(mappedBy = "board")//읽기전용
//    private List<Board> feelingList = new ArrayList<>();


}
