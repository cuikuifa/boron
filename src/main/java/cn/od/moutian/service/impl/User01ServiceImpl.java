package cn.od.moutian.service.impl;

import cn.od.moutian.core.service.AbstractService;
import cn.od.moutian.mapper.User01Mapper;
import cn.od.moutian.model.User01;
import cn.od.moutian.service.User01Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 233moutian
 * @date 2018/12/22
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class User01ServiceImpl extends AbstractService<User01> implements User01Service {
    @Resource
    private User01Mapper user01Mapper;

    @Override
    public int countAll() {
        return this.user01Mapper.selectCount(null);
    }
}
