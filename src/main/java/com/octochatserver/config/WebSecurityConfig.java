package com.octochatserver.config;

import com.octochatserver.filter.JWTAuthenticationFilter;
import com.octochatserver.filter.JWTAuthorizationFilter;
import com.octochatserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static com.octochatserver.config.AppConfig.REGISTER_URL;
import static com.octochatserver.entity.UserEntity.WRONG_CREDENTIALS;
import static com.octochatserver.entity.UserEntity.UNAUTHORIZED;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${octo.chat.endpoint}")
    private String chatEndpoint;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .cors()

            .and()
            .exceptionHandling()
            .defaultAuthenticationEntryPointFor(
                (request, response, exception) -> {
                    response.setContentType("html/text");
                    response.setStatus(HttpStatus.BAD_REQUEST.value());
                    response.getWriter().print(WRONG_CREDENTIALS);
                },
                new AntPathRequestMatcher("/login")
            )
            .defaultAuthenticationEntryPointFor(
                (request, response, exception) -> {
                    response.setContentType("html/text");
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.getWriter().print(UNAUTHORIZED);
                },
                new AntPathRequestMatcher("/user/**")
            )

            .and()
            .addFilter(new JWTAuthenticationFilter(userService, authenticationManager()))
            .addFilter(new JWTAuthorizationFilter(authenticationManager()))
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, REGISTER_URL).permitAll()
            .antMatchers(chatEndpoint + "/**").permitAll()
            .anyRequest().authenticated();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
