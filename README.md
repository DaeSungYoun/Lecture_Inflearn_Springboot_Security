# Lecture_Inflearn_Springboot_Security
인프런 - 스프링부트 시큐리티 &amp; JWT 강의(최주호)

- 스프링부트 시큐리티 2강 - 시큐리티 설정
    - WebSecurityConfigurerAdapter deprecated
        - 기존에는 WebSecurityConfigurerAdapter를 상속받아 설정을 오버라이딩 하여 사용했지만 deprecated로 인해 SecurityFilterChain를 Bean으로 등록하여 사용하도록 권장함
- 스프링부트 시큐리티 4강 - 시큐리티 로그인
    - loadUserByUsername
        - 시큐리티 설정에서 loginProcessingUrl()을 설정하고
        - 로그인 url에 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행됨
        - loadUserByUsername(String `username`)에서 파라미터명 `username`은 UI의 `username`과 동일하게 맞춰줘야 정상 동작함, 만약 UI의 username이 `username2`로 되어 있다면 http.authorizeRequests()에서 .usernameParameter("`username2`")으로 추가 설정해야 정상 동작함