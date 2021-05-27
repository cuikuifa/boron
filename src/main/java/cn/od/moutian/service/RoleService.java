package cn.od.moutian.service;

import cn.od.moutian.core.service.Service;
import cn.od.moutian.model.Role;

import java.util.List;

/**
 * @author 233moutian
 * @date 2018/08/13
 * 角色业务接口
 */
public interface RoleService extends Service<Role> {
    /**
     * 获取所有角色以及对应的权限
     *
     * @return 角色可控资源列表
     */
    List<Role> findAllRoleWithPermission();
    /*
    * 代替上面那个方法啊,用于修复上面那个方法出现的bug
    * */
    List<Role> getAllRoleWithPermission();
}
