package com.charles.common.test;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.charles.dal.utils.SqlHelper;

public class SqlHelperTest {
	public static void main(String[] args) {
//		ApplicationContext factory=new ClassPathXmlApplicationContext("classpath:persistence.xml"); 
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("D:/gitwork/charles/charles-dal/src/main/resources/META-INF/spring/persistence.xml");
		String resource = "D:/gitwork/charles/charles-dal/src/main/resources/META-INF/spring/persistence.xml";    
//		Reader reader = Resources.getResourceAsReader(resource);   
		SqlSession session = (SqlSession) ctx.getBean("sqlSession");
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); 
//		SqlSession session = sqlSessionFactory.openSession();
		String sqlStr = SqlHelper.getNamespaceSql(session, "com.charles.dal.dao.MemberDAO.queryMemberByUsername");
		System.out.println(sqlStr);
	}
}
