package com.charles.test.dao;

import org.junit.Before;

import org.junit.Test;

import com.charles.dal.dao.RecruitDOMapper;
import com.charles.dal.dataobject.RecruitDO;

public class RecruitDaoTest extends BaseDaoTest {
	
	RecruitDOMapper recruitDOMapper = null;
	
	@Before
	public void before() {
		recruitDOMapper = (RecruitDOMapper) getBean("recruitDOMapper");
	}
	
	@Test
	public void testSelect() {
		Integer recruitid = 550;
		RecruitDO recruit = recruitDOMapper.selectByPrimaryKey(recruitid);
		System.out.println(recruit);
	}
}
