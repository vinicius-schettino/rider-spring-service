package com.rider.driver;
import java.util.Collection;
import com.rider.driver.security.JwtToGrantedAuthoritiesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }


    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        http
//            .csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/**")))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(new AntPathRequestMatcher("/drivers", HttpMethod.GET.name())).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/drivers", HttpMethod.POST.name())).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/drivers/**", HttpMethod.GET.name())).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/drivers/**", HttpMethod.PUT.name())).hasRole("admin")
                .requestMatchers(new AntPathRequestMatcher("/drivers/start/**")).hasRole("admin")
                .requestMatchers(new AntPathRequestMatcher("/drivers/stop/**")).hasRole("admin")
                .requestMatchers(new AntPathRequestMatcher("/drivers/accept/**")).hasRole("admin")
                .requestMatchers(new AntPathRequestMatcher("/drivers/busy/**")).hasRole("admin")
                .anyRequest().authenticated()
        );
        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));
        return http.build();
    }

    private JwtAuthenticationConverter grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(userAuthoritiesMapperForKeycloak());
        return jwtAuthenticationConverter;
    }


    @Bean
    public Converter<Jwt, Collection<GrantedAuthority>> userAuthoritiesMapperForKeycloak() {
        return new JwtToGrantedAuthoritiesConverter();
    }
}
