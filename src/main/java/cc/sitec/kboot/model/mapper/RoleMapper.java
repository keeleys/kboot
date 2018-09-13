package cc.sitec.kboot.model.mapper;

import cc.sitec.kboot.model.pojo.Permission;
import cc.sitec.kboot.model.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description :  角色</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年08月02日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */

public interface RoleMapper extends tk.mybatis.mapper.common.Mapper<Role> {
    /**
     * 权限列表
     *
     * @return
     */
    List<Role> list();

    /**
     * 更新角色可用状态
     *
     * @param id
     * @param enable
     * @return
     */
    int updateEnable(@Param("id") Long id, @Param("enable") Boolean enable);

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    int insert(Role role);

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    int update(Role role);

    /**
     * 根据 Id 删除角色
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
    Role getById(Long id);

    /**
     * 根据角色状态获取角色列表
     *
     * @param enable
     * @return
     */
    List<Role> getRoleListByEnable(@Param("enable") boolean enable);

    /**
     * 删除角色授权
     *
     * @param roleId
     * @return
     */
    int removeRelation(Long roleId);

    /**
     * 建立角色授权
     *
     * @param relations
     * @return
     */
    int createRelation(List<Map<String, Long>> relations);
}
