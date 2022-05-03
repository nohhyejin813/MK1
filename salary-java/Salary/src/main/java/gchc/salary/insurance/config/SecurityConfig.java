package gchc.salary.insurance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
//	@Override
//	protected void configure(HttpSecurity security) throws Exception{
//		security.authorizeRequests().antMatchers("/").permitAll(); 				// 모두가 접근가능
//		security.csrf().disable();
//		security.formLogin().disable();
//	}
//	
//	@Bean
//	public PasswordEncoder passEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}

	
	   @Bean
	   public PasswordEncoder getPasswordEncoder() {
	      return new BCryptPasswordEncoder();
	   }

	   @Override
	   protected void configure(HttpSecurity http) throws Exception {
	      http.cors().disable()
	         .csrf().disable()
	         .formLogin().disable()
	         .headers().frameOptions().disable();
	   }
	
	
}
