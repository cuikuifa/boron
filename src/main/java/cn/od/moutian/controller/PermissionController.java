package cn.od.moutian.controller;

import cn.od.moutian.core.response.Result;
import cn.od.moutian.core.response.ResultGenerator;
import cn.od.moutian.model.Permission;
import cn.od.moutian.service.PermissionService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zoctan
 * @date 2018/06/09
 * 权限控制器
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @PreAuthorize("hasAuthority('role:list')")
    @GetMapping
    @ApiIgnore
    public Result listResourcePermission(@RequestParam(defaultValue = "0") final Integer page,
                                         @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<JSONObject> list = this.permissionService.findAllResourcePermission();
        //noinspection unchecked
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @ApiIgnore
    @PreAuthorize("hasAuthority('permission:list')")
    @GetMapping("listPermission")
    public Result listPermission(@RequestParam(defaultValue = "0") final Integer page,
                                         @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<Permission> list = this.permissionService.findAll();
        //noinspection unchecked
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PreAuthorize("hasAuthority('permission:add')")
    @PostMapping
    @ApiOperation(value = "增加权限资源", notes = "根据Permission对象创建权限资源")
    @ApiImplicitParam(name = "Permission", value = "权限资源详细实体permission", required = true, dataType = "Permission")
    public Result add(@RequestBody final Permission permission) {
        this.permissionService.save(permission);
        return ResultGenerator.genOkResult();
    }

    @ApiIgnore
    @PreAuthorize("hasAuthority('permission:delete')")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.permissionService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @ApiIgnore
    @PreAuthorize("hasAuthority('permission:update')")
    @PutMapping
    public Result update(@RequestBody final Permission permission) {
        this.permissionService.update(permission);
        return ResultGenerator.genOkResult();
    }

}
