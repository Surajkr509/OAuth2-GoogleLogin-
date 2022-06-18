package com.example.demo.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.model.AuthenticationProvider;
import com.example.demo.model.Customers;
import com.example.demo.service.CustomerService;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	
	@Autowired
	private CustomerService customerService;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
//		CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
//		String email = oAuth2User.getEmail();
		DefaultOidcUser oAuth2User = (DefaultOidcUser) authentication.getPrincipal();
		String email = oAuth2User.getAttribute("email");
		String name = oAuth2User.getAttribute("name");
		System.err.println("CompleteDetails " +oAuth2User);
		System.err.println("Customer Email :" +email);
		System.err.println("Customer Name :" +name);
		Customers customer = customerService.getCustomerByEmail(email);
		if(customer == null) {
			customerService.createNewCustomerAfterOAuthLoginSuccess(email,name,AuthenticationProvider.GOOGLE);
		} else {
			customerService.updateCustomerAfterOAuthLoginSuccess(customer,name,AuthenticationProvider.GOOGLE);
		}
		
		
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	

}
