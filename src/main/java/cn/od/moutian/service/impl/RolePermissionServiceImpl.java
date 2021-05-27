package cn.od.moutian.service.impl;

import cn.od.moutian.model.RolePermission;
import cn.od.moutian.service.RolePermissionService;
import cn.od.moutian.core.service.AbstractService;
import cn.od.moutian.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 233moutian
 * @date 2018/08/13
 * 角色权限业务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RolePermissionServiceImpl extends AbstractService<RolePermission> implements RolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;

}
