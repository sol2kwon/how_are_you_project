package how_are_you_project.how_are_you.question.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import how_are_you_project.how_are_you.member.domain.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "member_question")
@Getter
public class MemberQuestion {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "member_question_id")
    private Long memberQuestionId;

    @Column(name = "member_question_date")
    private LocalDate memberQuestionDate;

    @Column(name = "member_answer")
    private String memberAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

}
