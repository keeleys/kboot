package cc.sitec.kboot.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : 系统用户</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年8月02日</li>
 * <li>@author      : keeley <></li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Data
public class User {

    private Long id;

    @NotNull(message = "帐号不能为空")
    @Length(min = 3, message = "帐号长度不能小于3")
    @Pattern(regexp = "^\\w+$", message = "帐号只能包含字母数字或下划线")
    private String account;

    @JsonIgnore
    private String password;

    @NotNull(message = "姓名不能为空")
    private String userName;

    @NotNull
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误")
    private String tel;

    private String avatar;

    private Boolean enable = false;

    private List<Role> roles;
}
