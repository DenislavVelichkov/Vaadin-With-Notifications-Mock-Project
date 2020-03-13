package com.example.application.backend.config.security;


import java.util.Arrays;

import com.example.application.backend.common.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final String LOGIN_PROCESSING_URL = "/login";
  private static final String LOGIN_FAILURE_URL = "/login?error";
  private static final String LOGIN_URL = "/login";
  private static final String LOGOUT_SUCCESS_URL = "/";


  private final UserDetailsService userService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public SecurityConfiguration(UserDetailsService userService,
                               PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    super.configure(auth);
    auth.userDetailsService(this.userService).passwordEncoder(this.passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable()
        .requestCache().requestCache(new CustomRequestCache())
        .and()
        .authorizeRequests()
        .antMatchers("/register").permitAll()
        .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
        .anyRequest().authenticated()
        .and().formLogin()
        .failureForwardUrl(LOGIN_FAILURE_URL)
        .loginPage(LOGIN_URL).permitAll()
        .loginProcessingUrl(LOGIN_PROCESSING_URL)
        .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(
        "/VAADIN/**",
        "/favicon.ico",
        "/robots.txt",
        "/manifest.webmanifest",
        "/sw.js",
        "/offline-page.html",
        "/frontend/**",
        "/webjars/**",
        "/frontend-es5/**", "/frontend-es6/**");
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
