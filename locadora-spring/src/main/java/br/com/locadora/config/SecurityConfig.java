package br.com.locadora.config;

import static br.com.locadora.config.SecurityConstants.SIGN_UP_URL;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import br.com.locadora.config.JWTAuthenticationFilter;
import br.com.locadora.config.JWTAuthorizationFilter;
import br.com.locadora.abstractmodel.Usuario;
import br.com.locadora.service.CustomUserDetailService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //Usado para pré autorização
public class SecurityConfig<T extends Usuario, ID extends Serializable> extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
				.and().csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, SIGN_UP_URL).permitAll()
				.antMatchers("/*/public/**").permitAll()
				.antMatchers("/*/protected/**").hasRole("CLIENTE")
				.antMatchers("/*/shared/**").hasAnyRole("CLIENTE","FUNCIONARIO","NENHUMA")
				.antMatchers("/*/protected-share/**").hasAnyRole("FUNCIONARIO","CLIENTE")
				.antMatchers("/*/private/**").hasRole("FUNCIONARIO")
				.antMatchers("/*/admin/**").hasRole("ADMIN")
				.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
