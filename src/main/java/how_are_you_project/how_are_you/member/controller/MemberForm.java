package how_are_you_project.how_are_you.member.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "회원 아이디는 필수 입니다.")
    private String userName;

    @NotEmpty(message = "회원 비밀번호는 필수 입니다.")
    private String password;

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    @NotEmpty(message = "회원 생년월일은 필수 입니다.")
    private String birth;

    @NotEmpty(message = "회원 이메일은 필수 입니다.")
    private String email;
}
