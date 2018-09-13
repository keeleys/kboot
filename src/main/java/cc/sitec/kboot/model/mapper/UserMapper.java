package cc.sitec.kboot.model.mapper;

import cc.sitec.kboot.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description :  系统用户</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年08月02日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {
    /**
     * 权限列表
     *
     * @return
     */
    List<User> list();

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
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 修改角色
     *
     * @param user
     * @return
     */
    int update(User user);

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
    User getById(Long id);

    /**
     * 删除角色关联
     *
     * @param id
     * @return
     */
    int removeRelation(Long id);

    /**
     * 建立角色关联
     *
     * @param relations
     * @return
     */
    int createRelation(List<Map<String, Long>> relations);

    /**
     * 重置密码
     *
     * @param userId
     * @param newPassword
     * @return
     */
    int updatePassword(@Param("userId") Long userId, @Param("newPassword") String newPassword);

    /**
     * 根据帐号获取用户信息
     *
     * @param account
     * @return
     */
    User getUserByAccount(@Param("account") String account);
}
