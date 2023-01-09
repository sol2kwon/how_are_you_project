package how_are_you_project.how_are_you.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;


@Getter
@AllArgsConstructor
public class JoinMemberDto {
    @NotEmpty(message = "회원 아이디는 필수 입니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    private String loginPassword;

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    @NotEmpty(message = "생년월일은 필수 입니다.")
    private String birth;

    @NotEmpty(message = "이메일은 필수 입니다.")
    private String email;
}
