package cn.od.moutian.service;

import cn.od.moutian.core.service.Service;
import cn.od.moutian.model.Permission;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author 233moutian
 * @date 2018/08/13
 * 权限资源业务接口
 */
public interface PermissionService extends Service<Permission> {
    /**
     * 找到所有权限可控资源
     *
     * @return Json对象列表
     */
    List<JSONObject> findAllResourcePermission();
}
