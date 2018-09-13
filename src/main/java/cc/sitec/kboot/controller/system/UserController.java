package cc.sitec.kboot.controller.system;

import cc.sitec.kboot.common.JsonResult;
import cc.sitec.kboot.model.pojo.User;
import cc.sitec.kboot.service.RoleService;
import cc.sitec.kboot.service.UserService;
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
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("/list")
    public JsonResult list() {
        return JsonResult.success(userService.list());
    }

    @GetMapping("/get")
    public JsonResult get(Long id) {
        return JsonResult.success(userService.getById(id));
    }

    @PostMapping("/updateEnable")
    public JsonResult updateEnable(Long id, Boolean enable) {
        userService.updateEnable(id, enable);
        return JsonResult.success();
    }

    @PostMapping("/save")
    public JsonResult save(@Valid User user) {
        userService.saveOrUpdate(user);
        return JsonResult.success();
    }

    @GetMapping("/delete")
    public JsonResult delete(Long id) {
        userService.deleteById(id);
        return JsonResult.success();
    }

    @GetMapping("/roleList")
    public JsonResult roleList() {
        return JsonResult.success(roleService.getRoleListByEnable(true));
    }

    @GetMapping("/resetPassword")
    public JsonResult resetPassword(Long id) {
        userService.resetPassword(id);
        return JsonResult.success();
    }
}
