package com.charles.dal.dao;

import com.charles.dal.dataobject.RecruitDO;


public interface RecruitDOMapper {
    int deleteByPrimaryKey(Integer recruitid);

    int insert(RecruitDO record);

    int insertSelective(RecruitDO record);

    RecruitDO selectByPrimaryKey(Integer recruitid);

    int updateByPrimaryKeySelective(RecruitDO record);

    int updateByPrimaryKey(RecruitDO record);
}