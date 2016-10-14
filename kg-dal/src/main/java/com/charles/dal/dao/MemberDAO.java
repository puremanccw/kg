package com.charles.dal.dao;

import com.charles.dal.dataobject.MemberDO;

public interface MemberDAO {
	
	public MemberDO queryMemberByUsername(String username);
	
	public Long queryCount();
}
