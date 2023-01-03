package how_are_you_project.how_are_you.aop.service;

import how_are_you_project.how_are_you.aop.domain.Auth;
import how_are_you_project.how_are_you.member.domain.Member;

import how_are_you_project.how_are_you.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements UserDetailsService {
    private final MemberRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = Optional.ofNullable(repository.findByUsername(username))
                .orElseThrow(()->new UsernameNotFoundException(username+"에 해당하는 객체가 존재하지 않습니다."));
        return Auth.build(member.get());
    }
}
