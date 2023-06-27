package LD_Caffe.ld_caffe.configuration;

import LD_Caffe.ld_caffe.service.UserService;
import LD_Caffe.ld_caffe.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("AUTHORIZATION = {}", authorization);

        //token 안보내거나 유효하지 않음
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("AUTHORIZATION 이 유효하지 않습니다.");
            filterChain.doFilter(request, response);
            return;
        }  // 여기서 블락

        //token 꺼내기
        String token = authorization.split(" ")[1];

        //토큰이 만료되었는지 검증
        if (JwtUtil.isExpired(token, secretKey)) {
            log.error("Token 이 만료되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }// 여기서 블락

        //userId 토큰에서 꺼내기
        String userId = JwtUtil.getUserId(token, secretKey);
        log.info("userId = {}", userId);

        //권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority("USER")));
        //Detail 을 넣어준다.
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}