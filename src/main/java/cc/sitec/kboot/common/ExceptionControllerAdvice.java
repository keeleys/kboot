package cc.sitec.kboot.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : 全局异常处理器</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年08月08日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public JsonResult bindExceptionHandler(BindException ex) {
        StringBuffer sb = new StringBuffer();
        ex.getFieldErrors().forEach(fieldError -> sb.append(fieldError.getDefaultMessage()).append(","));
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        log.error("参数绑定异常", ex);
        return JsonResult.error(sb.toString());
    }

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public JsonResult serviceExceptionExceptionHandler(ServiceException ex) {
        log.error("系统业务异常", ex);
        return JsonResult.error(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(DataAccessException.class)
    public JsonResult serviceExceptionExceptionHandler(DataAccessException ex) {
        log.error("触发数据只读异常", ex);
        return JsonResult.error("禁止操作此资源！");
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResult serviceExceptionExceptionHandler(Exception ex) {
        log.error("系统未知异常", ex);
        return JsonResult.error("系统异常,请联系管理员！");
    }
}
