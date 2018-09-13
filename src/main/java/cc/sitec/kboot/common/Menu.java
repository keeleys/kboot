package cc.sitec.kboot.common;

import lombok.Data;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : cc.sitec.kboot.common</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年08月09日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Data
public class Menu {
    private Long id;

    private String text;

    private String state = "open";

    private Long parentId;

    private String href;

    private String icon;
}
