package cc.sitec.kboot.common;

import lombok.Data;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : cc.sitec.kboot.common</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年08月07日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Data
public class JsonResult {
    private boolean success = true;
    private String msg;
    private Object data;

    public JsonResult() {

    }

    public JsonResult(String msg) {
        this.msg = msg;
    }

    public static JsonResult success() {
        return new JsonResult();
    }

    public static JsonResult success(String msg) {
        return new JsonResult(msg);
    }

    public static JsonResult success(Object data) {
        JsonResult jr = new JsonResult();
        jr.setData(data);
        return jr;
    }

    public static JsonResult error() {
        JsonResult jr = new JsonResult();
        jr.setSuccess(false);
        return jr;
    }

    public static JsonResult error(String msg) {
        JsonResult jr = new JsonResult(msg);
        jr.setSuccess(false);
        return jr;
    }
}
