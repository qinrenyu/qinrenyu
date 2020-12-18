package com.exam.manage.asystem.dao;

import com.exam.manage.asystem.entity.SysMenus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SysMenusMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(SysMenus record);

    int insertSelective(SysMenus record);

    SysMenus selectByMenuPath(String menuPath);

    int updateByPrimaryKeySelective(SysMenus record);
    List<SysMenus> findAll();
    List<SysMenus> findByParentIdsLike(@Param("parentIds")String parentIds);

    int updateByPrimaryKey(SysMenus record);
}