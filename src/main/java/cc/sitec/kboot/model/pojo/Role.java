package cc.sitec.kboot.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description :  系统角色</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年8月02日</li>
 * <li>@author      : keeley <></li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Data
public class Role {

    private Long id;

    @NotNull(message = "角色名称不能为空")
    private String roleName;

    @NotNull(message = "角色标识不能为空")
    private String roleKey;

    private Boolean enable = false;

    @Length(max = 256, message = "描述过长")
    private String description;

    @JsonIgnore
    private List<Permission> permissions;
}
