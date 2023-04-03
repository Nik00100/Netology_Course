package ru.kirillov.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("sportsman").password("{noop}sportsman").authorities("participate")
                .and()
                .withUser("stuff").password("{noop}stuff").authorities("participate", "update")
                .and()
                .withUser("admin").password("{noop}admin").authorities("participate", "update", "delete");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/signup").hasAuthority("participate")
                .and()
                .authorizeRequests().antMatchers("/update").hasAuthority("update")
                .and()
                .authorizeRequests().antMatchers("/delete").hasAuthority("delete")
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}
