package cc.sitec.kboot.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : 权限资源</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年8月02日</li>
 * <li>@author      : keeley <></li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Data
public class Permission {

    private Long id;

    private Long parentId;

    @NotNull(message = "资源名称不能为空")
    private String name;

    private String icon;

    @NotNull(message = "资源标识不能为空")
    private String permissionKey;

    /**
     * 权限类型
     */
    private Type type;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 资源
     */
    private String resource;

    private Boolean enable = false;

    private String description;

    private Integer weight = 0;

    private List<Permission> children;

    @JsonProperty("text")
    public String getText() {
        return this.name;
    }

    @JsonProperty("typeAlias")
    public String getTypeAlias() {
        return this.type.display;
    }

    private Boolean granted = false;

    @JsonProperty("checkState")
    public String getCheckState() {
        return this.granted ? "checked" : "";
    }

    @JsonProperty("iconCls")
    public String getIconCls() {
        return this.icon;
    }

    /**
     * 权限类型枚举
     */
    public enum Type {
        CATEGORY("分类"),
        MENU("菜单"),
        FUNCTION("功能"),
        BLOCK("区域");

        private String display;

        Type(String display) {
            this.display = display;
        }

        public String display() {
            return display;
        }

        @Override
        public String toString() {
            return this.display + "[" + this.name() + "]";
        }
    }
}
