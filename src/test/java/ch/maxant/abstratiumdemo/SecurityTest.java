package ch.maxant.abstratiumdemo;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityTest {

    // https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html => scroll down a bit
    void testSecurity() {
        // given
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication =
                new TestingAuthenticationToken("username", "password", "ROLE_USER");
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        // when... TODO
        // then... TODO

    }
}
