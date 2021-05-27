package cn.od.moutian.mapper;

import cn.od.moutian.core.mapper.MyMapper;
import cn.od.moutian.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

/*
 * DAO层数据库访问类
 * */
@Mapper
public interface UserRoleMapper extends MyMapper<UserRole> {
}