package how_are_you_project.how_are_you.board.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "board_id")
    private long id;

    @NotEmpty
    @Column(nullable = false, length = 50)
    private String boardName;

    @NotEmpty
    @Column(nullable = false, length =1000)
    private String content;

    @NotEmpty
    @Column
    private LocalDateTime date;

}
