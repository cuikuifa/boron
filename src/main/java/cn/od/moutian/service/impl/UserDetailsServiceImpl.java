package cn.od.moutian.service.impl;

import cn.od.moutian.model.User;
import cn.od.moutian.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 233moutian
 * @date 2018/08/13
 * 用户细节业务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final User user = this.userService.findDetailByUsername(username);
        // 权限
        final List<SimpleGrantedAuthority> authorities =
                user.getPermissionCodeList().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        // 角色
        authorities.add(new SimpleGrantedAuthority(user.getRoleName()));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
