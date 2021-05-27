package cn.od.moutian.service.impl;

import cn.od.moutian.model.RolePermission;
import cn.od.moutian.service.RoleService;
import cn.od.moutian.core.service.AbstractService;
import cn.od.moutian.mapper.RoleMapper;
import cn.od.moutian.mapper.RolePermissionMapper;
import cn.od.moutian.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 233moutian
 * @date 2018/08/13
 * 角色业务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    /*
    * 弃用此方法,因为sql是一条搞定的,查出来的条数不对
    * */
    @Override
    public List<Role> findAllRoleWithPermission() {
        return this.roleMapper.findAllRoleWithPermission();
    }

    @Override
    public List<Role> getAllRoleWithPermission() {
        List<Role> list = this.roleMapper.findAllRole();
        for (Role role : list) {
            role.setResourceList(roleMapper.findAllRolePermission(role.getId()));
        }
        return list;
    }


    @Override
    public void save(final Role role) {
        this.roleMapper.insert(role);
        this.rolePermissionMapper.saveRolePermission(role.getId(), role.getPermissionIdList());
    }

    @Override
    public void update(final Role role) {
        // 删掉所有权限，再添加回去
        final Condition condition = new Condition(RolePermission.class);
        condition.createCriteria().andCondition("role_id = ", role.getId());
        this.rolePermissionMapper.deleteByCondition(condition);
        this.rolePermissionMapper.saveRolePermission(role.getId(), role.getPermissionIdList());
    }
}
