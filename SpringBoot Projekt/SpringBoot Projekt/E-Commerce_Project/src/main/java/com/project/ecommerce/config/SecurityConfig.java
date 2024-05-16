package com.project.ecommerce.config;

import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.project.ecommerce.dao.UserRepository;
import com.project.ecommerce.model.jpa.Site_User;
import com.project.ecommerce.security.JwtAuthenticationEntryPoint;
import com.project.ecommerce.security.JwtAuthenticationFilter;
import com.project.ecommerce.service.Site_User_Service;

import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
@ComponentScan({"com.project.ecommerce.security"})
public class SecurityConfig {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private  AuthenticationManager authenticationManager;
	
	@Autowired
	private  UserDetailsService userDetailsService;
	
	
	@Autowired
	private  AuthenticationConfiguration authenticationConfiguration;
	//private final JwtUtils jwtUtils;
	
	@Autowired
	private JwtAuthenticationEntryPoint handler;
	
	@Autowired
	private Site_User_Service userService;
	//private UserService userService;
	
	//@Autowired
	//private AuthEntryPointJwt unauthorizedHandler;
	
	

	
	
	@Autowired
    public SecurityConfig(@Lazy UserDetailsService userDetailsService, 
    		@Lazy JwtAuthenticationEntryPoint handler,
    		@Lazy UserRepository repository) {
        this.userDetailsService = userDetailsService;
        this.handler = handler;
        this.repository = repository;
    }
    
   
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {	
    	
    	
    	
    	
    	
     	httpSecurity
    	.cors(cors->cors.disable())
    	.csrf(csrf -> csrf.disable())
    	.exceptionHandling(exc->exc.disable())//.authenticationManager((AuthenticationManager) handler)
    	.sessionManagement(sess->sess.disable())// .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	.authorizeHttpRequests(authorize -> authorize
    			   .requestMatchers(HttpMethod.GET,"/").permitAll()
    			   .requestMatchers(HttpMethod.GET,"/**").permitAll()
    			   .requestMatchers(HttpMethod.GET,"/site_user/**").permitAll()
                   .requestMatchers(HttpMethod.GET,"/get_products").permitAll()
                   .requestMatchers(HttpMethod.DELETE,"/site_user/{userId}").permitAll()
                   .requestMatchers(HttpMethod.PUT,"/site_user/{Id}").permitAll()
                   .requestMatchers(HttpMethod.POST,"/site_user/Edit_User/{edit_kind}").permitAll()
                   //.requestMatchers(HttpMethod.DELETE,"/site_user/{userId}").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated());
   			
    	
    	return httpSecurity.build();
    }
    
    
	
	
	




	@Bean
	public UserDetailsService userDetailsService() {
		
		System.out.println("Userdetailsservice");
		
		
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
				
				Site_User opt = repository.findByVorname(username);
				//System.out.println(username + " Username");
				
				//com.project.questapp.entities.User opt = null;
				
				//opt.setUserName(username);
				//opt.setPassword(username);
				
				
				//System.out.println(" getusername " + opt.getPassword() + " " + opt.getVorname());
				List<Site_User> l = Arrays.asList(opt);	
		
				UserDetails user = User.withUsername(opt.getVorname()).password(opt.getPassword()).authorities("USER").build();
		        return user;
		//return (UserDetails) (l.stream().collect(Collectors.toSet()));
				
				//return null;
	}
		};
		
		
}	
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	
	@Bean(name="passwordEncoder")
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	 public CorsFilter corsFilter() {
	    	System.out.println("corsfilter");
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOrigin("*");
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("OPTIONS");
	        config.addAllowedMethod("HEAD");
	        config.addAllowedMethod("GET");
	        config.addAllowedMethod("PUT");
	        config.addAllowedMethod("POST");
	        config.addAllowedMethod("DELETE");
	        config.addAllowedMethod("PATCH");
	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);
	    }
	 
	    @Bean
	    public JwtAuthenticationFilter jwtAuthenticationFilter() {
	    	System.out.println("filter");
	    	return new JwtAuthenticationFilter();
	    }   
}