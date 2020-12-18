package com.exam.manage.asystem.dao;

import com.exam.manage.asystem.entity.SysAuthorityMenu;
import com.exam.manage.asystem.entity.SysMenus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SysAuthorityMenuMapper {
    int deleteByPrimaryKey(String authorityMenuId);

    int insert(SysAuthorityMenu record);

    int insertSelective(SysAuthorityMenu record);

    SysAuthorityMenu selectByPrimaryKey(String authorityMenuId);

    int updateByPrimaryKeySelective(SysAuthorityMenu record);
    List<String> findAuthoritysByMenuId(@Param("menuId")String menuId);
    List<SysMenus> findMenuListByAuthorityId(@Param("authorityId")String authorityId);

    int updateByPrimaryKey(SysAuthorityMenu record);
}