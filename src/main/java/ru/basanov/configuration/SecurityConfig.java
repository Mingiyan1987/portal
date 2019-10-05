package ru.basanov.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
         .usersByUsernameQuery("select a.login, password, true from author where login=?")
         .authoritiesByUsernameQuery("select a.login, r.name from role r JON author" + "a ON r.id = a.role_id WHERE a.login=?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/article/*/edit").hasAuthority("admin")
                .antMatchers(HttpMethod.GET, "/article/add").hasAnyAuthority("admin", "user")
                    .and().formLogin().loginPage("/login").loginProcessingUrl("/login")
                .usernameParameter("login").passwordParameter("password").failureUrl("/loginfailed")
                    .and().logout().logoutSuccessUrl("/");
    }
}
