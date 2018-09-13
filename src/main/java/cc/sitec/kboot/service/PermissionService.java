package cc.sitec.kboot.service;

import cc.sitec.kboot.common.ServiceException;
import cc.sitec.kboot.model.mapper.PermissionMapper;
import cc.sitec.kboot.model.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : cc.sitec.kboot.service</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年08月07日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    /**
     * @return
     */
    public List<Permission> list() {
        Map<Long, Permission> permissionMap = permissionMapper.list().stream().collect(Collectors.toMap(Permission::getId, Function.identity()));
        List<Permission> permissions = new ArrayList<>();
        buildPermissionTree(permissionMap, permissions);
        return permissions;
    }

    /**
     * 获取所有可用权限
     *
     * @return
     */
    public List<Permission> getEnabledPermission() {
        return permissionMapper.getEnabledPermission();
    }

    /**
     * @param id
     * @param enable
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateEnable(Long id, Boolean enable) {
        int effRow = permissionMapper.updateEnable(id, enable);
        if (effRow == 0) {
            throw new ServiceException("数据未更新！");
        }
    }

    /**
     * @param permission
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Permission permission) {
        int effRow;
        if (permission.getId() == null) {
            effRow = permissionMapper.insert(permission);
        } else {
            effRow = permissionMapper.update(permission);
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
        int effRow = permissionMapper.deleteById(id);
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
    public Permission getById(Long id) {
        return permissionMapper.getById(id);
    }

    /**
     * 获取权限列表
     *
     * @param roleId
     * @return
     */
    public List<Permission> getRolePermissionList(Long roleId) {
        Map<Long, Permission> permissionMap = permissionMapper.getEnabledAndRolePermissionList(roleId).stream().collect(Collectors.toMap(Permission::getId, Function.identity()));
        List<Permission> permissions = new ArrayList<>();
        buildPermissionTree(permissionMap, permissions);
        return permissions;
    }

    private void buildPermissionTree(Map<Long, Permission> permissionMap, List<Permission> permissions) {
        permissionMap.forEach((aLong, permission) -> {
            if (permission.getParentId() == null) {
                permissions.add(permission);
            } else {
                Permission parent = permissionMap.get(permission.getParentId());
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(permission);
            }
        });
    }
}
