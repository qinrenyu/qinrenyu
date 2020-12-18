package com.exam.manage.asystem.dao;

import com.exam.manage.asystem.entity.SysAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysAuthorityMapper {
    int deleteByPrimaryKey(String authorityId);

    int insert(SysAuthority record);

    int insertSelective(SysAuthority record);

    SysAuthority selectByPrimaryKey(String authorityId);

    int updateByPrimaryKeySelective(SysAuthority record);
    List<SysAuthority> findAll();
    List<SysAuthority> findByUserId(@Param("userId") String userId);
    int updateByPrimaryKey(SysAuthority record);
}