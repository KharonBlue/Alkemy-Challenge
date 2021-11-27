
package com.alkemy.disney.security;

import com.alkemy.disney.service.ServicioUsuario;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Kharon
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

    private final ServicioUsuario servicioUsuario;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()  
                .antMatchers("/auth/register/**").permitAll() //Autorizo a que todos tengan acceso al registro y login
                .antMatchers("/auth/login").permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin()
                .loginProcessingUrl("/auth/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder); //Asigo bCryptPasswordEncoder como medio de seguridad
        provider.setUserDetailsService(servicioUsuario); //Uso el servicioUsuario para la autenticacion
        return provider;
    }
}
