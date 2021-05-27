package cn.od.moutian.service;

import cn.od.moutian.core.service.Service;
import cn.od.moutian.model.User;
import cn.od.moutian.model.UserRole;

/**
 * @author 233moutian
 * @date 2018/08/13
 * 用户角色业务接口
 */
public interface UserRoleService extends Service<UserRole> {
    /**
     * 更新用户角色
     *
     * @param user 用户
     */
    void updateUserRole(User user);
}
