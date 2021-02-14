package com.example.backend.config;

import com.example.backend.constant.UserPermission;
import com.example.backend.constant.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/v1/**").hasRole(UserRole.USER.name())
                .antMatchers(HttpMethod.GET, "/management/v1/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.ADMINTRAINEE.name())
                .antMatchers(HttpMethod.POST, "/management/v1/**").hasAuthority(UserPermission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/management/v1/**").hasAuthority(UserPermission.COURSE_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                // .httpBasic();
                .formLogin();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
                .username("hoangdo")
                .password(passwordEncoder.encode("hoangdo"))
                // .roles(UserRole.USER.name())
                .authorities(UserRole.USER.getGrantedAuthorities())
                .build();
        UserDetails adminDetails = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                // .roles(UserRole.ADMIN.name())
                .authorities(UserRole.ADMIN.getGrantedAuthorities())
                .build();
        UserDetails adminTraineeDetails = User.builder()
                .username("admintrainee")
                .password(passwordEncoder.encode("password"))
                // .roles(UserRole.ADMINTRAINEE.name())
                .authorities(UserRole.ADMINTRAINEE.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(userDetails, adminDetails, adminTraineeDetails);
    }
}
