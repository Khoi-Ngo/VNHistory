package com.swp.vnhistory.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
//TÌM KIẾM TOKEN THÔNG QUA REQUEST
@Component
public class JwtTokenFilter extends OncePerRequestFilter{

	private static final org.jboss.logging.Logger logger = LoggerFactory.logger(JwtTokenFilter.class);
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String token = getJwt(request);
			//NẾU LẤY ĐƯỢC TOKEN VÀ SỬ DỤNG JWTPROVIDER MÌNH ĐÃ TẠO GỌI ĐẾN VALIDATETOKEN MÌNH ĐÃ TẠO
			if(token != null && jwtTokenProvider.validateToken(token)) {
				String username = jwtTokenProvider.getUserNameFromToken(token);
				
				//THÔNG QUA UserDetails
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				//lOAD LÊN TẦNG USERDETAILS
				
				//KHỞI TẠO AUTHOR
				UsernamePasswordAuthenticationToken authenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//ĐÍNH LÊN WEB CỦA MÌNH
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);//GỌI CONTEXT TRONG SECURITY CORE RA -> SET AUTHEN
				//DÙNG ĐỂ XÁC NHẬN 1 LỚP BẢO MẬT CỦA LỚP SECURITY.
				
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Cannot set user authentication -> Message: {}", e);
		}
		filterChain.doFilter(request, response);
		
	}
	
	private String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if(authHeader != null && authHeader.startsWith("Bearer")) {
			return authHeader.replace("Bearer", "").trim();
			
		}
		return null;
		
	}
	
}
