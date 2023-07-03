package com.swp.vnhistory.security.jwt;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.swp.vnhistory.security.userpincal.UserPrinciple;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	private static final org.jboss.logging.Logger logger = LoggerFactory.logger(JwtTokenProvider.class);
	private String jwtSecret = "khoindse170017@fpt.edu.vn";
	private int jwtExpiration = 86400; // thoi gian token
	private int refreshTokenExpiration = 86400 * 30; // thoi gian het han refresh token (30 days)

	// TẠO RA TOKEN CHO USER
	public String createToken(Authentication authentication) {
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

	}

	// TẠO RA REFRESH TOKEN
	public String createRefreshToken(Authentication authentication) {
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + refreshTokenExpiration * 1000);

		return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	// VALIDATE TOKEN
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
			// VALID TOKEN

		} catch (SignatureException e) {
			// TODO: handle exception
			logger.error("Invalid JWT signature -> Message: {}", e);
		} catch (ExpiredJwtException e) {
			// TODO: handle exception
			logger.error("Expired JWT token -> Message: {}", e);
		} catch (UnsupportedJwtException e) {
			// TODO: handle exception
			logger.error("Unsupported JWT token -> Message: {}", e);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			logger.error("JWT claims string is empty -> Message: {}", e);
		} catch (MalformedJwtException e) {
			// TODO: handle exception
			logger.error("The invalid token format -> Message: {}", e);
		}
		return false;
	}

	// GET USER NAME FROM TOKEN
	public String getUserNameFromToken(String token) {
		String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		return userName;
	}

	// GET ROLES || AUTHORITIES FROM TOKEN
	public List<String> getRolesFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		// chỗ này có thể là chỗ để chỉnh lại role
		String roles = (String) claims.get("roles");
		return Arrays.stream(roles.split(",")).map(String::trim).collect(Collectors.toList());
	}
}
