package com.exam.manage.asystem.service;

import com.exam.manage.asystem.dao.SysMenusMapper;
import com.exam.manage.asystem.entity.SysMenus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SysMenusService {

    @Autowired
    private SysMenusMapper sysMenusMapper;
    public SysMenus selectByMenuPath(String menuPath) {
        return sysMenusMapper.selectByMenuPath(menuPath);
    }

    public List<SysMenus> findAll() {
        return sysMenusMapper.findAll();
    }
    public void save(SysMenus sysMenus) {
        if (StringUtils.isEmpty(sysMenus.getMenuId())){
             sysMenusMapper.insert(sysMenus);
        }else{
             sysMenusMapper.updateByPrimaryKey(sysMenus);
        }

    }

}
