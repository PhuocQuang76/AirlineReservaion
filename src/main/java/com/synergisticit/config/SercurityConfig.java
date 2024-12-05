package com.synergisticit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SercurityConfig {



    @Autowired
    AuthenticationSuccessHandler authSuccessHandler;

    @Autowired
    AccessDeniedHandler accessDeniedHandler;

//    @Bean
//    UserDetailsManager userDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Autowired
    UserDetailsService userDetailsService;


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //http.authorizeHttpRequests().anyRequest().permitAll(); //bypasses all the http security.

        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/", "/home","/userForm").permitAll().and()
                .exceptionHandling().accessDeniedPage("/accessDeniedPage").and()
                .authorizeRequests()
                .requestMatchers("/driverForm","/documentForm","/claimForm").hasAnyAuthority("User","Admin").and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home").permitAll().and()
                .logout()
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();

        return http.build();
    }
}

//jdbc template
//hibernate session factory