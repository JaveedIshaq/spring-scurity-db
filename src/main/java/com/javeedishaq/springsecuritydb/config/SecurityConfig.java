package com.javeedishaq.springsecuritydb.config;

import com.javeedishaq.springsecuritydb.repository.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Autowired
    MyUserRepo myUserRepo;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
       httpSecurity
               .authorizeHttpRequests(auth -> auth.requestMatchers("/helloAdmin")
                       .hasRole("ADMIN")
                       .anyRequest()
                       .authenticated())
               .formLogin(formLogin -> formLogin.permitAll())
               .logout( lgo -> lgo.permitAll());

       return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService (){
        UserDetailsService userD = (username) -> myUserRepo.findByUsername(username)
                .map(myUser -> User.builder()
                        .username(myUser.getUsername())
                        .password(myUser.getPassword())
                        .roles(myUser.getRoles().split(",")).build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found Exception"));
    return  userD;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
