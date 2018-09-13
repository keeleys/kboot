package cc.sitec.kboot.model.mapper;

import cc.sitec.kboot.model.pojo.Permission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : 权限资源</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年08月02日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
public interface PermissionMapper extends Mapper<Permission> {
    /**
     * 权限列表
     *
     * @return
     */
    List<Permission> list();

    /**
     * 更新资源可用状态
     *
     * @param id
     * @param enable
     * @return
     */
    int updateEnable(@Param("id") Long id, @Param("enable") Boolean enable);

    /**
     * 新增权限资源
     *
     * @param permission
     * @return
     */
    int insert(Permission permission);

    /**
     * 修改权限资源
     *
     * @param permission
     * @return
     */
    int update(Permission permission);

    /**
     * 根据 Id 删除权限资源
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据 Id 获取原始数据
     *
     * @param id
     * @return
     */
    Permission getById(Long id);

    /**
     * 获取权限列并标记角色已有权限
     *
     * @param roleId
     * @return
     */
    List<Permission> getEnabledAndRolePermissionList(Long roleId);

    /**
     * 获取所有可用权限
     *
     * @return
     */
    List<Permission> getEnabledPermission();

    /**
     * 获取角色所有可用权限
     *
     * @return
     */
    List<Permission> getPermissionByRole(Long roleId);
}
