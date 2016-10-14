package com.charles.web.controller.user;

import java.io.IOException;
import java.io.Reader;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.charles.dal.utils.SqlHelper;

public class Test {
	public static void main(String[] args) throws IOException {
		System.out.println(new Random().nextInt(160523434));
	}
}
