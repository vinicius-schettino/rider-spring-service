package com.rider.driver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/**")))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(new AntPathRequestMatcher("/drivers", HttpMethod.GET.name())).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/drivers/**", HttpMethod.GET.name())).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/drivers/**", HttpMethod.POST.name())).hasRole("driver")
                .requestMatchers(new AntPathRequestMatcher("/drivers/start/**")).hasRole("driver")
                .requestMatchers(new AntPathRequestMatcher("/drivers/stop/**")).hasRole("driver")
                .requestMatchers(new AntPathRequestMatcher("/drivers/accept/**")).hasRole("driver")
                .requestMatchers(new AntPathRequestMatcher("/drivers/busy/**")).hasRole("driver")
                .anyRequest().authenticated()
        );
        return http.build();
    }
}
