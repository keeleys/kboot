package cc.sitec.kboot.service;

import cc.sitec.kboot.common.ServiceException;
import cc.sitec.kboot.model.mapper.PermissionMapper;
import cc.sitec.kboot.model.mapper.UserMapper;
import cc.sitec.kboot.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : cc.sitec.kboot.service</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年08月08日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Value("${system.super.user.id}")
    private Long superId;

    /**
     * @return
     */
    public List<User> list() {
        return userMapper.list();
    }

    /**
     * @param id
     * @param enable
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateEnable(Long id, Boolean enable) {
        int effRow = userMapper.updateEnable(id, enable);
        if (effRow == 0) {
            throw new ServiceException("数据未更新！");
        }
    }

    /**
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(User user) {
        int effRow;
        if (user.getId() == null) {
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            effRow = userMapper.insert(user);
        } else {
            effRow = userMapper.update(user);
        }

        //删除原有关联
        userMapper.removeRelation(user.getId());

        if (user.getRoles() != null) {
            List<Map<String, Long>> relations = new ArrayList<>();
            user.getRoles().forEach(role -> {
                Map<String, Long> relation = new HashMap<>(user.getRoles().size());
                relation.put("role_id", role.getId());
                relation.put("user_id", user.getId());
                relations.add(relation);
            });
            //建立新关联
            userMapper.createRelation(relations);
        }

        if (effRow == 0) {
            throw new ServiceException("数据保存失败！");
        }
    }

    /**
     * 删除资源
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        if (superId.equals(id)) {
            throw new ServiceException("超级管理员不能删除！！！");
        }

        int effRow = userMapper.deleteById(id);
        if (effRow == 0) {
            throw new ServiceException("没有找到要删除的资源！");
        }
    }

    /**
     * 根据 Id 获取原始数据
     *
     * @param id
     * @return
     */
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    /**
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(Long userId) {
        int effRow = userMapper.updatePassword(userId, DigestUtils.md5DigestAsHex("666666".getBytes()));
        if (effRow == 0) {
            throw new ServiceException("密码未重置！");
        }
    }

    public User getUserByAccount(String account) {
        User user = userMapper.getUserByAccount(account);
        if (user == null) {
            throw new ServiceException("帐号或密码错误！！");
        }
        //获取角色权限
        user.getRoles().forEach(role -> role.setPermissions(permissionMapper.getPermissionByRole(role.getId())));
        return user;
    }
}
