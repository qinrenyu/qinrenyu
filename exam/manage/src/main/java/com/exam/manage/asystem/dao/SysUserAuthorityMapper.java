package com.exam.manage.asystem.dao;

import com.exam.manage.asystem.entity.SysUserAuthority;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserAuthorityMapper {
    int deleteByPrimaryKey(String userAuthorityId);

    int insert(SysUserAuthority record);

    int insertSelective(SysUserAuthority record);

    SysUserAuthority selectByPrimaryKey(String userAuthorityId);

    int updateByPrimaryKeySelective(SysUserAuthority record);

    int updateByPrimaryKey(SysUserAuthority record);
}