package com.arcesi.cliniquemedical.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.arcesi.cliniquemedical.security.enums.AppUserRole;

import static com.arcesi.cliniquemedical.security.enums.Permission.ADMIN_CREATE;
import static com.arcesi.cliniquemedical.security.enums.Permission.ADMIN_UPDATE;
import static com.arcesi.cliniquemedical.security.enums.Permission.ADMIN_READ;
import static com.arcesi.cliniquemedical.security.enums.Permission.ADMIN_DELETE;

import static com.arcesi.cliniquemedical.security.enums.Permission.PATIENT_READ;
import static com.arcesi.cliniquemedical.security.enums.Permission.PATIENT_UPDATE;
import static com.arcesi.cliniquemedical.security.enums.Permission.PATIENT_DELETE;
import static com.arcesi.cliniquemedical.security.enums.Permission.PATIENT_CREATE;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;
  String[] companiesEndpoints = {"/companies", "/companies/*"};

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf((csrf)->csrf.disable())
	        .authorizeHttpRequests((auth)->auth.requestMatchers(
	        		"/api/v1/auth/**",
	                "/v2/api-docs",
	                "/v3/api-docs",
	                "/v3/api-docs/**",
	                "/swagger-resources",
	                "/swagger-resources/**",
	                "/configuration/ui",
	                "/configuration/security",
	                "/swagger-ui/**",
	                "/webjars/**",
	                "/swagger-ui.html"
	        ).permitAll()
	       )
	        
	        .authorizeHttpRequests((auth)->auth.requestMatchers("/api/v1/admin/**").hasAnyAuthority(AppUserRole.ADMINISTRATEUR.getId(),AppUserRole.PATIENT.getId())

	  	          
	    	        .requestMatchers(GET, "/api/v1/admin/**").hasAnyAuthority(ADMIN_READ.name(),PATIENT_READ.name())
	    	        //.requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
	    	        //.requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
	    	        //.requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())


	    	       // .requestMatchers("/api/v1/admin/**").hasAuthority(AppUserRole.ADMINISTRATEUR.getId())

	    	       // .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_READ.name())
	    	       // .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_CREATE.name())
	    	       // .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_UPDATE.name())
	    	        //.requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_DELETE.name())

                    .anyRequest()
                    .denyAll()
	        		
	        )


	          
	        
	        
	          .sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authenticationProvider(authenticationProvider)
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	        .logout((logout)->logout
	        		.logoutUrl("/api/v1/auth/logout")
	        		.addLogoutHandler(logoutHandler) 
	        		.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
	        		);

	    return http.build();
  }
}
