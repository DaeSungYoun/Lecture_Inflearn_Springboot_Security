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


- JWT
    - Json Web Token은 웹 애플리케이션에서 사용자 인증과 권한 부여를 처리하기 위한 인증 방식 중 하나이다
    - 특징
        - Stateless : JWT는 서버에 상태를 저장하지 않으므로, 각각의 요청에 대해 인증을 검증하고 처리할 수 있습니다. 이는 서버 측 부하를 줄이고 확장성을 높일 수 있다
        - Security : JWT는 서명이 포함된 토큰으로, 인증된 사용자만이 접근할 수 있는 리소스에 대한 권한을 부여합니다. 서명에는 비밀키가 사용되기 때문에 JWT가 변조되거나 위조 될 수 없습니다
        - Portable : JWT는 텍스트 기반의 형식을 사용하기 떄문에, 서로 다른 시스템 간에 쉽게 전송할 수 있습니다. JWT는 클라이언트 측에서도 저장될 수 있으므로, 서버에서 토큰을 저장하고 관리할 필요가 없습니다.
        - Flexible : JWT는 클레임(Claim)이라는 페이로드(Payload)를 포함할 수 있으며, 클레임은 토큰에 대한 추가 정보를 제공할 수 있습니다. 이를 통해 JWT는 인증 및 권한 관리 외에도 사용자 프로필, 광고추적, 소셜 로그인 등의 다양한 용도로 사용될 수 있습니다

        