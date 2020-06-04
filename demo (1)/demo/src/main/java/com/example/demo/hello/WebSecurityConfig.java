package com.example.demo.hello;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                antMatchers("/","login").permitAll()
                .anyRequest().authenticated();

        http.
                formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll();
    }

//    @Configuration
//    public static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter{
//        @Override
//        public void init(AuthenticationManagerBuilder auth) throws Exception {
//
//            auth
//                    .inMemoryAuthentication()
//                    .withUser("user").password("{noop}password").roles("USER").
//                    and().
//                    withUser("admin").password("{noop}password").roles("ADMIN");
//        }
//
//    }

    @Autowired
    UserService userService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( userService);
    }
}
