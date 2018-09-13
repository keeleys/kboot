package cc.sitec.kboot.controller;

import cc.sitec.kboot.common.JsonResult;
import cc.sitec.kboot.common.Menu;
import cc.sitec.kboot.common.ServiceException;
import cc.sitec.kboot.model.pojo.Permission;
import cc.sitec.kboot.model.pojo.Role;
import cc.sitec.kboot.model.pojo.User;
import cc.sitec.kboot.service.PermissionService;
import cc.sitec.kboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : kboot</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年07月27日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Slf4j
@Controller
@RequestMapping("/")
public class KbootController {

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @Value("${system.super.user.id}")
    private Long superId;

    @Value("${system.avatar.dir}")
    private String avatarDir;

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("login")
    @ResponseBody
    public JsonResult login(@RequestParam String account, @RequestParam String password, HttpSession session) {
        User user = userService.getUserByAccount(account);
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            throw new ServiceException("帐号或密码错误！");
        }
        List<Permission> permissions;
        //判断是否是超级管理员
        if (Objects.equals(superId, user.getId())) {
            permissions = permissionService.getEnabledPermission();
        } else {
            //获取用户菜单
            List<Role> roles = user.getRoles();
            permissions = new ArrayList<>();
            roles.forEach(role -> permissions.addAll(role.getPermissions()));
        }

        //存储菜单
        TreeSet<Permission> menus = new TreeSet<>((o1, o2) -> {
            if (Objects.equals(o1.getWeight(), o2.getWeight())) {
                return -1;
            }
            return o1.getWeight() - o2.getWeight();
        });

        //存储权限key
        Set<String> keys = new HashSet<>();
        //所有有权限访问的请求
        Set<String> urls = new HashSet<>();

        permissions.forEach(permission -> {
            if (permission.getEnable()) {
                if (permission.getType().equals(Permission.Type.MENU) || permission.getType().equals(Permission.Type.CATEGORY)) {
                    //是菜单
                    menus.add(permission);
                    urls.add(permission.getPath());
                }
                //获取用户拥有的权限
                keys.add(permission.getPermissionKey());

                urls.addAll(Arrays.asList(permission.getResource().split(",")));
            }
        });

        //树形数据转换
        List<Menu> menuList = new ArrayList<>();
        menus.forEach(permission -> {
            Menu m = new Menu();
            m.setId(permission.getId());
            m.setIcon(permission.getIcon());
            m.setText(permission.getName());
            m.setHref(permission.getPath());
            m.setParentId(permission.getParentId());
            menuList.add(m);
        });

        session.setAttribute("user", user);
        session.setAttribute("menus", menuList);
        session.setAttribute("keys", keys);
        session.setAttribute("urls", urls);
        return JsonResult.success();
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/init")
    @ResponseBody
    public JsonResult menus(@SessionAttribute("menus") List<Menu> menuList, @SessionAttribute("keys") Set<String> keys, @SessionAttribute("user") User user) {
        Map<String, Object> data = new HashMap<>(2);
        data.put("menus", menuList);
        data.put("keys", keys);
        data.put("user", user);
        return JsonResult.success(data);
    }

    @RequestMapping("/reject")
    public String reject() {
        return "reject";
    }

    /**
     * 上传头像
     *
     * @return
     */
    @PostMapping("/uploadAvatar")
    @ResponseBody
    public JsonResult uploadAvatar(@RequestParam("file") MultipartFile file, @SessionAttribute("user") User user, HttpSession session) throws IOException {
        if (StringUtils.isEmpty(avatarDir)) {
            avatarDir = System.getProperty("user.dir");
        }
        File root = new File(FilenameUtils.concat(avatarDir, "avatar"));
        if (!root.exists()) {
            root.mkdirs();
        }
        String fileName = "user" + user.getId() + System.currentTimeMillis() + ".jpg";
        file.transferTo(new File(root, fileName));
        //删除原有头像
        if (!StringUtils.isEmpty(user.getAvatar())) {
            File s = new File(FilenameUtils.concat(avatarDir, user.getAvatar()));
            if (s.exists()) {
                FileUtils.forceDelete(s);
            }
        }
        user.setAvatar("avatar/" + fileName);
        userService.saveOrUpdate(user);
        session.setAttribute("user", user);
        return JsonResult.success(user);
    }

    @GetMapping("/avatar/{src}")
    public ResponseEntity<byte[]> avatar(@PathVariable("src") String src) {
        if (StringUtils.isEmpty(avatarDir)) {
            avatarDir = System.getProperty("user.dir");
        }
        File file = new File(FilenameUtils.concat(avatarDir, "avatar/" + src));
        ResponseEntity re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (file.exists()) {
            try {
                re = new ResponseEntity<>(FileUtils.readFileToByteArray(file), HttpStatus.OK);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return re;
    }

    /**
     * 修改个人资料
     *
     * @param tel
     * @param userName
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/profile/save")
    @ResponseBody
    public JsonResult saveProfile(String tel, String userName, @SessionAttribute("user") User user, HttpSession session) {
        user.setTel(tel);
        user.setUserName(userName);
        userService.saveOrUpdate(user);
        session.setAttribute("user", user);
        return JsonResult.success(user);
    }
}
