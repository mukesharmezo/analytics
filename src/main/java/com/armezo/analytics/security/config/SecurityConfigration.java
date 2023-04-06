package com.armezo.analytics.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private SecurityRequestFilter requestFilter;
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.authorizeRequests()
				.antMatchers(HttpMethod.GET,"/*").permitAll()
				.antMatchers("/demographic/saveDemographic").authenticated()
				.antMatchers("/task/saveTaskwise").authenticated()
			//	.antMatchers(HttpMethod.POST,"/*").authenticated()
				//.antMatchers("/user/login").permitAll()
				//.antMatchers("/user/saveUser").permitAll()
				// .antMatchers("/swagger-ui/index.html").permitAll()
				 //.antMatchers("/swagger-ui/**").permitAll()
//				 .antMatchers("/swagger-ui/index.html").permitAll()
//			.antMatchers("/demographic/saveDemographic").permitAll()
//			.antMatchers("/task/saveTaskwise").permitAll()
//			.antMatchers("/dashboard/analytics").permitAll()
				.anyRequest().permitAll().and().exceptionHandling().authenticationEntryPoint(entryPoint);
		httpSecurity.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
