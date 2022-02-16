package com.ECA.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MySecurity extends WebSecurityConfigurerAdapter {

    /*
    ROLE----->high level overview --->Normal:READ
    Authority-->permission-->READ
    Admin-->READ,WRITE,Update
     */







    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       /* //   no password encrypt ---> this use a password as text
       auth.inMemoryAuthentication().withUser("ram").password("123").roles("Admin");
        auth.inMemoryAuthentication().withUser("Dev").password("123").roles("AM");*/

        /*  password encrypt ---> this use a password as encrypt */
        auth.inMemoryAuthentication().withUser("ram").password(this.passwordEncoder().encode("123")).roles("admin");
        auth.inMemoryAuthentication().withUser("Dev").password(this.passwordEncoder().encode("123")).roles("AM");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/eca").permitAll().anyRequest().authenticated().and().
               httpBasic();
    }
    /* no password encrypt ---> this use a password as text */

  /*  @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }*/

    /*  password encrypt ---> this use a password as encrypt */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
