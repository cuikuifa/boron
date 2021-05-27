package cn.od.moutian.mapper;

import cn.od.moutian.core.mapper.MyMapper;
import cn.od.moutian.model.Resource;
import cn.od.moutian.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * DAO层，对应Role实体，数据库访问类
 * */
@Mapper
public interface RoleMapper extends MyMapper<Role> {
    /**
     * 获取所有角色以及对应的权限
     *
     * @return 角色可控资源列表
     */
    List<Role> findAllRoleWithPermission();
    List<Role> findAllRole();
    List<Resource> findAllRolePermission(Long rId);

}