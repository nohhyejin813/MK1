package gchc.salary.insurance.jwt.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import gchc.salary.insurance.domain.SalesUser;


@Component
@Slf4j
@Service

public class JwtService {
	
	@Value("${jwt.salt}")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;

	/**
     * 로그인 성공 시 사용자 정보를 기반으로 JWTToken을 생성해서 반환한다.
     * 
     */
	public String create(final SalesUser saleUser) {
		log.trace("time : {}",expireMin);
		final JwtBuilder builder = Jwts.builder();
		builder.setHeaderParam("typ", "JWT");
		
		builder.setSubject("로그인토큰")
        		.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
        		.claim("SalesUser", saleUser).claim("second", "추가로 태우고 싶은거????");
		
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		// 마지막 직렬화
		final String jwt = builder.compact();
		log.debug("토큰 발행 : {}",jwt);
		return jwt;
        		
	}
	
	public void checkValid(final String jwt) {
		log.trace("토큰 점검 : {}",jwt);
	}
    
	public Map<String,Object> get(final String jwt) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		} catch (final Exception e) {
			throw new RuntimeException();
		}
		
		
		log.trace("claims : {}",claims);
		
		return claims.getBody();
		
	}

}
