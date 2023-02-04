package how_are_you_project.how_are_you.dto.question;

import lombok.*;


@Getter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class SearchMemberQuestionReqDto {

    private Long memberId; //memberId
    private String startDate ;
    private String endDate ;
    private String text ;

}
