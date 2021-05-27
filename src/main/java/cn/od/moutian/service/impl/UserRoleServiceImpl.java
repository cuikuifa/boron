package cn.od.moutian.service.impl;

import cn.od.moutian.mapper.UserRoleMapper;
import cn.od.moutian.model.User;
import cn.od.moutian.model.UserRole;
import cn.od.moutian.service.UserRoleService;
import cn.od.moutian.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;

/**
 * @author 233moutian
 * @date 2018/08/13
 * 用户角色业务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl extends AbstractService<UserRole> implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void updateUserRole(final User user) {
        final Condition condition = new Condition(UserRole.class);
        condition.createCriteria().andCondition("user_id = ", user.getId());
        final UserRole userRole = new UserRole()
                .setUserId(user.getId())
                .setRoleId(user.getRoleId());
        this.userRoleMapper.updateByConditionSelective(userRole, condition);
    }
}
