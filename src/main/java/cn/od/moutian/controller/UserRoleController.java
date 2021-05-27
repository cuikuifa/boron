package cn.od.moutian.controller;

import cn.od.moutian.core.response.Result;
import cn.od.moutian.core.response.ResultGenerator;
import cn.od.moutian.model.User;
import cn.od.moutian.service.UserRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @author Zoctan
 * @date 2018/06/09
 * 后台用户角色控制器
 */
@ApiIgnore
@RestController
@RequestMapping("/user/role")
public class UserRoleController {
    @Resource
    private UserRoleService userRoleService;

    @PreAuthorize("hasAuthority('role:update')")
    @PutMapping
    public Result updateUserRole(@RequestBody final User user) {
        this.userRoleService.updateUserRole(user);
        return ResultGenerator.genOkResult();
    }
}
