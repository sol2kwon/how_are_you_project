package how_are_you_project.how_are_you.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class LoginMemberDto {
    @NotEmpty(message = "회원 아이디는 필수 입니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    private String loginPassword;




}
