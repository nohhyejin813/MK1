package gchc.salary.insurance;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordEncoderTest {
	
	@Test
	public void testEncodePassword() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		PasswordEncoder passwordEncoder = new StandardPasswordEncoder();
		String rawPassword="duke2020";
		
		String encodePasswrod = passwordEncoder.encode(rawPassword);
		
		System.out.println("encode Passwrod : "+ encodePasswrod);
		
		boolean matched = passwordEncoder.matches("duke2020", encodePasswrod);
		
		assertTrue(matched);
	}

}
