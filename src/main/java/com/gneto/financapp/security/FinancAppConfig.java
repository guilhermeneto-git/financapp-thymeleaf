package com.gneto.financapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class FinancAppConfig {

    // To ignore authentication and authorization managers, thus allowing access for all of requests.
    // it's temporary.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/**"));
    }

    /*@Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, password, active from users where user_id=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer.requestMatchers(HttpMethod.GET, "/financapp/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/financapp/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/financapp/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/financapp/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/financapp/users/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/financapp/categories").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/financapp/categories/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/financapp/categories").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/financapp/categories").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/financapp/categories/**").hasRole("ADMIN")
        );

        // use HTTP Basic Authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(crsf -> crsf.disable());

        return http.build();
    }*/

}
