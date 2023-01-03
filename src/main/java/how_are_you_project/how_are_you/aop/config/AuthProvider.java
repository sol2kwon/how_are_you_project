package how_are_you_project.how_are_you.aop.config;

import how_are_you_project.how_are_you.aop.service.AuthServiceImpl;
import how_are_you_project.how_are_you.member.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Log //아이피 다 찍어야함
//프로바이더는 AOP가 제공하는
public class AuthProvider implements AuthenticationProvider {
    private final AuthServiceImpl service;

    @Value("${security.jwt.token.security-key:secret-key}")
    private String securitykey;

    @Value("${security.jwt.token.expiration-length:3600000}")
    private  long validityInMs = 3600000;//1h

    @PostConstruct
    protected void init(){
        securitykey = Base64.getEncoder().encodeToString(securitykey.getBytes());
        log.info("securitykey"+securitykey);
    }
    public String createToken(String username, List<Role> roles){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth",roles.stream().map(s->new SimpleGrantedAuthority(s.getAuthority()))
                .filter(Objects::nonNull).collect(Collectors.toSet()));
        Date now = new Date();
        Date validity = new Date(now.getTime()+validityInMs);
        return Jwts.builder().setClaims(claims).setIssuedAt(now)
                .setExpiration(validity).signWith(SignatureAlgorithm.HS256,securitykey)
                .compact();
    }
    public Authentication getAuthenticate(String token){
        UserDetails auth = service.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(auth.getAuthorities(),"",auth.getAuthorities());

    }
    public String getUsername(String token){
        return Jwts.parser().setSigningKey(securitykey).parseClaimsJws(token).getBody().getSubject();

    }
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");//리액트한테 Authorization 보내면 리액트는 set으로 받음
        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    public boolean validateToken(String token) throws Exception{
        try{
            Jwts.parser().setSigningKey(securitykey).parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException e) {
            throw new Exception();
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
