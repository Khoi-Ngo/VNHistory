package com.swp.vnhistory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.swp.vnhistory.security.jwt.JwtEntryPoint;
import com.swp.vnhistory.security.jwt.JwtTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//tiêm hết jwt

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	private JwtEntryPoint jwtEntryPoint;

	// khởi tạo Bean thì nó vào quản lý của Spring và có thể @Autowired tiêm khắp
	// nơi
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();

	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		// mặc dù password đã encode rồi nhưng mà authen vẫn lấy ra được password của
		// mình
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// KHÔNG QUÉT ĐƯỢC ENTRYPOINT VÌ JWTENTRYPOINT PHẢI ĐƯỢC @Component || @Service
	// ... để xác định là dạng @Bean
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable().authorizeRequests()
//	        .antMatchers("/admin/**").hasRole("ADMIN")
//	        .antMatchers("/editor/**").hasRole("EDITOR")
//	  ===================================================      
				.antMatchers("/**").permitAll().anyRequest().authenticated()
//	  =====================================================
//	        .anyRequest().permitAll()
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")

				.and().exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
