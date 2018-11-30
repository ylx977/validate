package com.fuzamei;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidateApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	StringEncryptor encryptor;

	@Test
	public void getPass() {
		String url = encryptor.encrypt("jdbc:mysql://172.16.100.14:3306/biginfo?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true&useSSL=false");
		String name = encryptor.encrypt("root");
		String password = encryptor.encrypt("W%e>=&QhO87p");
		System.out.println(url+"----------------");
		System.out.println(name+"----------------");
		System.out.println(password+"----------------");
		Assert.assertTrue(name.length() > 0);
		Assert.assertTrue(password.length() > 0);
	}

}
