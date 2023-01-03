package how_are_you_project.how_are_you.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import how_are_you_project.how_are_you.board.domain.Board;
import how_are_you_project.how_are_you.order.domain.Order;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;

    @NotEmpty
    @Column(nullable = false, length = 20)
    private String userName;

    @JsonIgnore
    @NotEmpty
    private String password; //노출되면 안되기 때문

    @NotEmpty
    @Column(nullable = false, length =20)
    private String name;

    @NotEmpty
    @Column(nullable = false, length =20)
    private String birth;

    @NotEmpty
    @Column(nullable = false, length =50)
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
