package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.demo.oauth.CustomOAuth2UserService;
import com.example.demo.oauth.OAuth2LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure (HttpSecurity httpSecurity) throws Exception {
		httpSecurity.antMatcher("/**").authorizeRequests().antMatchers("/").permitAll()
		.anyRequest().authenticated().and().oauth2Login().userInfoEndpoint().userService(oAuth2UserService).and().successHandler(oAuth2LoginSuccessHandler);
	}

	
	@Autowired
	private CustomOAuth2UserService oAuth2UserService;
	
	@Autowired
	OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
}
