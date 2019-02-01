package com.octochatserver.config;

import com.octochatserver.filter.JWTAuthenticationFilter;
import com.octochatserver.filter.JWTAuthorizationFilter;
import com.octochatserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;

import static com.octochatserver.config.AppConfig.REGISTER_URL;
import static com.octochatserver.entity.UserEntity.WRONG_CREDENTIALS;

@Configuration
@EnableWebMvc
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
            .authenticationEntryPoint(
                (request, response, exception) -> {
                    response.setContentType("html/text");
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().print(WRONG_CREDENTIALS);
                }
            )

            .and()
            .addFilter(new JWTAuthenticationFilter(userService, authenticationManager()))
            .addFilter(new JWTAuthorizationFilter(authenticationManager()))
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, REGISTER_URL).permitAll()
            .antMatchers(chatEndpoint + "/**").authenticated()
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

        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
