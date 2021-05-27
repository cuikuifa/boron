package cn.od.moutian.mapper;

import cn.od.moutian.core.mapper.MyMapper;
import cn.od.moutian.model.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 * DAO层，数据库访问类
 * */
@Mapper
public interface RolePermissionMapper extends MyMapper<RolePermission> {
    /**
     * 保存角色以及对应的权限ID
     *
     * @param roleId           角色ID
     * @param permissionIdList 权限ID列表
     */
    void saveRolePermission(@Param("roleId") Long roleId,
                            @Param("permissionIdList") List<Integer> permissionIdList);
}