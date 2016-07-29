package com.concrete.authentication.utils;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import static org.junit.Assert.*;

import com.concrete.authentication.service.PasswordService;

public class UtilCryptTest {
	
	private PasswordService service = new PasswordService(BCrypt.gensalt());
	
	@Test
	public void test_crypt_password() {
		
		String passwordInitial = "senha1";
		
		String passwordFinal1 = service.crypt(passwordInitial);
		String passwordFinal2 = service.crypt(passwordInitial);
		
		assertEquals(passwordFinal1, passwordFinal2);
		assertNotEquals(passwordInitial, passwordFinal1);
		
	}
	

}
