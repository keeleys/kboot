package cc.sitec.kboot.controller.system;

import cc.sitec.kboot.common.JsonResult;
import cc.sitec.kboot.model.pojo.Role;
import cc.sitec.kboot.service.PermissionService;
import cc.sitec.kboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : 角色管理</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年08月08日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @GetMapping("/list")
    public JsonResult list() {
        return JsonResult.success(roleService.list());
    }

    @GetMapping("/get")
    public JsonResult get(Long id) {
        return JsonResult.success(roleService.getById(id));
    }

    @PostMapping("/updateEnable")
    public JsonResult updateEnable(Long id, Boolean enable) {
        roleService.updateEnable(id, enable);
        return JsonResult.success();
    }

    @PostMapping("/save")
    public JsonResult save(@Valid Role role) {
        roleService.saveOrUpdate(role);
        return JsonResult.success();
    }

    @PostMapping("/grant")
    public JsonResult grant(Long id, Long[] pids) {
        roleService.grant(id, pids);
        return JsonResult.success();
    }

    @GetMapping("/delete")
    public JsonResult delete(Long id) {
        roleService.deleteById(id);
        return JsonResult.success();
    }

    @GetMapping("/permissionList")
    public JsonResult permissionList(Long id) {
        return JsonResult.success(permissionService.getRolePermissionList(id));
    }
}
