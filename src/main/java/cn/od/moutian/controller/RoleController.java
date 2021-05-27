package cn.od.moutian.controller;

import cn.od.moutian.core.response.Result;
import cn.od.moutian.core.response.ResultGenerator;
import cn.od.moutian.model.Role;
import cn.od.moutian.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zoctan
 * @date 2018/06/09
 * 后台角色控制器
 */
@ApiIgnore
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PreAuthorize("hasAuthority('role:add')")
    @PostMapping
    public Result add(@RequestBody final Role role) {
        this.roleService.save(role);
        return ResultGenerator.genOkResult();
    }

    @PreAuthorize("hasAuthority('role:delete')")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.roleService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PreAuthorize("hasAuthority('role:update')")
    @PutMapping
    public Result update(@RequestBody final Role role) {
        this.roleService.update(role);
        return ResultGenerator.genOkResult();
    }

    @PreAuthorize("hasAuthority('role:list')")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<Role> list = this.roleService.getAllRoleWithPermission();
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
