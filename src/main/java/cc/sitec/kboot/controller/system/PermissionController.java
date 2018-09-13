package cc.sitec.kboot.controller.system;

import cc.sitec.kboot.common.JsonResult;
import cc.sitec.kboot.model.pojo.Permission;
import cc.sitec.kboot.service.PermissionService;
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
 * <li>Description :  权限资源管理</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年08月07日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@RestController
@RequestMapping("/system/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @GetMapping("/list")
    public JsonResult list() {
        return JsonResult.success(permissionService.list());
    }

    @GetMapping("/get")
    public JsonResult get(Long id) {
        return JsonResult.success(permissionService.getById(id));
    }

    @PostMapping("/updateEnable")
    public JsonResult updateEnable(Long id, Boolean enable) {
        permissionService.updateEnable(id, enable);
        return JsonResult.success();
    }

    @PostMapping("/save")
    public JsonResult save(@Valid Permission permission) {
        permissionService.saveOrUpdate(permission);
        return JsonResult.success();
    }

    @GetMapping("/delete")
    public JsonResult delete(Long id) {
        permissionService.deleteById(id);
        return JsonResult.success();
    }
}
