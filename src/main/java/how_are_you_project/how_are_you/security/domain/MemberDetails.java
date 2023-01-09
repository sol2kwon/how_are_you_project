package how_are_you_project.how_are_you.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.p6spy.engine.common.P6Util;
import how_are_you_project.how_are_you.member.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class MemberDetails implements UserDetails {
    private final String loginId;

    @JsonIgnore
    private final String loginPassword;

    private final String name;

    private final String birth;

    private final String email;


    public static MemberDetails build(Member member){
        List<GrantedAuthority> authorities = member.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
        return new MemberDetails(member.getLoginId(),member.getLoginPassword(),member.getName(),member.getBirth(),member.getEmail(),authorities);
    }


    //계정이 갖고있는 권한 목록을 리턴한다.
    private final Collection<? extends GrantedAuthority> authorities;
    //계정의 비밀번호를 리턴한다.
    @Override
    public String getPassword() {
        return null;
    }
    //계정의 이름을 리턴한다.
    @Override
    public String getUsername() {
        return null;
    }
    //계정이 만료되지 않았는 지 리턴한다. (true: 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    //계정이 잠겨있지 않았는 지 리턴한다. (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    //비밀번호가 만료되지 않았는 지 리턴한다. (true: 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    // 계정이 활성화(사용가능)인 지 리턴한다. (true: 활성화)
    @Override
    public boolean isEnabled() {
        return false;
    }
}
