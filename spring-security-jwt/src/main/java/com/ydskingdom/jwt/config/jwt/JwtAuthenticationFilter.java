package com.ydskingdom.jwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydskingdom.jwt.auth.PrincipalDetails;
import com.ydskingdom.jwt.dto.LoginRequestDto;
import com.ydskingdom.jwt.model.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationManager authenticationManager1) {
		this.authenticationManager = authenticationManager1;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		System.out.println("로그인 시도중");

		ObjectMapper objectMapper = new ObjectMapper();
		LoginRequestDto loginRequestDto = null;
		try {
			// request에 있는 username과 password를 파싱해서 자바 Object로 받기
			loginRequestDto = objectMapper.readValue(request.getInputStream(), LoginRequestDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(loginRequestDto);

		// 유저네임패스워드 토큰 생성
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());
		System.out.println("JwtAuthenticationFilter : 토큰생성완료");

		// authenticate() 함수가 호출 되면 인증 프로바이더가 유저 디테일 서비스의
		// loadUserByUsername(토큰의 첫번째 파라메터) 를 호출하고
		// UserDetails를 리턴받아서 토큰의 두번째 파라메터(credential)과
		// UserDetails(DB값)의 getPassword()함수로 비교해서 동일하면
		// Authentication 객체를 만들어서 필터체인으로 리턴해준다.

		// Tip: 인증 프로바이더의 디폴트 서비스는 UserDetailsService 타입
		// Tip: 인증 프로바이더의 디폴트 암호화 방식은 BCryptPasswordEncoder
		// 결론은 인증 프로바이더에게 알려줄 필요가 없음.
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		PrincipalDetails principalDetailis = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("Authentication : "+principalDetailis.getUser().getUsername());
		return authentication;
	}

	// attemptAuthentication 실행 후 인증이 정상적으로 되면 successfulAuthentication이 실행됨
	// JWT Token 생성해서 response에 담아주기
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		System.out.println("successfulAuthentication()");
		PrincipalDetails principalDetailis = (PrincipalDetails) authResult.getPrincipal();

		String jwtToken = JWT.create()
				.withSubject("cos토큰")
				.withExpiresAt(new Date(System.currentTimeMillis()+(60000*10)))
				.withClaim("id", principalDetailis.getUser().getId())
				.withClaim("username", principalDetailis.getUser().getUsername())
				.sign(Algorithm.HMAC512("cos"));

		response.addHeader("Authorization", "Bearer " + jwtToken);
	}
}
