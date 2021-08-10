package com.appliboard.appliboard.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    /// THIS IS NOT COMPLETE, WILL NEED TO REFACTOR THE URL PATTERNS
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
<<<<<<< HEAD
                .defaultSuccessUrl("/jobApplications")
=======
                .defaultSuccessUrl("/home")
>>>>>>> 0881aebc8bee2cd7d046bd7e2108c44211413390
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/posts/create",

//                        "/notes/create",
//                        "/notes/index",
                       

                        "/ads/create",
                        "/profile"
//                        "/jobApplications",
//                        "/jobApplications/{id}/delete",
//                        "/jobApplications/create",
//                        "/jobApplications/edit"
                        )

                .authenticated()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/home",
                        "/ads",
                        "/posts",
                        "/posts/{id}",
                        "/ads/{id}",
                        "/notes/create",
                        "/notes/index",
                        "/register",
                        "/js/**", // had to add this to not restrict scripts
                        "/css/**", // had to add this to not restrict stylesheets
                        "/img/**")
                        
                .permitAll()
                .anyRequest().authenticated();
    }
}
