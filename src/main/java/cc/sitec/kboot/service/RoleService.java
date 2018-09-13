package cc.sitec.kboot.service;

import cc.sitec.kboot.common.ServiceException;
import cc.sitec.kboot.model.mapper.RoleMapper;
import cc.sitec.kboot.model.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    /**
     * @return
     */
    public List<Role> list() {
        return roleMapper.list();
    }

    /**
     * @param roleId
     * @param enable
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateEnable(Long roleId, Boolean enable) {
        int effRow = roleMapper.updateEnable(roleId, enable);
        if (effRow == 0) {
            throw new ServiceException("数据未更新！");
        }
    }

    /**
     * @param role
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Role role) {
        int effRow;
        if (role.getId() == null) {
            effRow = roleMapper.insert(role);
        } else {
            effRow = roleMapper.update(role);
        }

        if (effRow == 0) {
            throw new ServiceException("数据保存失败！");
        }
    }

    /**
     * 删除资源
     *
     * @param roleId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long roleId) {
        int effRow = roleMapper.deleteById(roleId);
        if (effRow == 0) {
            throw new ServiceException("没有找到要删除的资源！");
        }
    }

    /**
     * 根据 Id 获取原始数据
     *
     * @param roleId
     * @return
     */
    public Role getById(Long roleId) {
        return roleMapper.getById(roleId);
    }

    /**
     * 根据状态获取角色列表
     *
     * @param enable
     * @return
     */
    public List<Role> getRoleListByEnable(boolean enable) {
        return roleMapper.getRoleListByEnable(enable);
    }

    /**
     * 角色授权
     *
     * @param roleId
     * @param pids
     */
    @Transactional(rollbackFor = Exception.class)
    public void grant(Long roleId, Long[] pids) {
        roleMapper.removeRelation(roleId);

        List<Map<String, Long>> relations = new ArrayList<>();
        Arrays.asList(pids).forEach(aLong -> {
            Map<String, Long> relation = new HashMap<>(pids.length);
            relation.put("role_id", roleId);
            relation.put("permission_id", aLong);
            relations.add(relation);
        });
        roleMapper.createRelation(relations);
    }
}
