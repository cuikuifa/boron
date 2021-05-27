package cn.od.moutian.service.impl;

import cn.od.moutian.mapper.PermissionMapper;
import cn.od.moutian.model.Permission;
import cn.od.moutian.service.PermissionService;
import com.alibaba.fastjson.JSONObject;
import cn.od.moutian.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 233moutian
 * @date 2018/08/13
 * 后台权限业务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends AbstractService<Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<JSONObject> findAllResourcePermission() {
        return this.permissionMapper.findAllResourcePermission();
    }
}
