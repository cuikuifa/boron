package cn.od.moutian.mapper;

import cn.od.moutian.core.mapper.MyMapper;
import cn.od.moutian.model.Permission;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 * DAO层，对应Permission实体，数据库访问类
 * */
@Mapper
public interface PermissionMapper extends MyMapper<Permission> {
    /**
     * 找到所有权限可控资源
     *
     * @return Json对象列表
     */
    List<JSONObject> findAllResourcePermission();

    /**
     * 获取所有权限代码
     *
     * @return 代码列表
     */
    @Select("SELECT p.code FROM `permission` p")
    List<String> findAllCode();
}