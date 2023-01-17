package how_are_you_project.how_are_you.dto.question;

import how_are_you_project.how_are_you.member.domain.Member;
import how_are_you_project.how_are_you.question.domain.Question;
import lombok.*;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberQuestionDto {


    private String answer;

    private Long memberId; //memberId

    private Long questionId ; //questionId

    private LocalDate nowDate;


}
