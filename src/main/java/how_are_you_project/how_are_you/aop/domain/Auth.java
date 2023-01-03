package how_are_you_project.how_are_you.aop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import how_are_you_project.how_are_you.member.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class Auth implements UserDetails {
    private final long id;
    private final String userName;
    @JsonIgnore
    private final String password; //노출되면 안되기 때문
    private final String name;
    private final String birth;
    private final String email;

//    public static Auth build(Member member){
//        List<GrantedAuthority> authorities = member.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
//                .collect(Collectors.toList());
//        return new Auth(member.getId(),member.getUserName(),member.getPassword(), member.getName(), member.getEmail(),authorities);
//    }

    private  final Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}