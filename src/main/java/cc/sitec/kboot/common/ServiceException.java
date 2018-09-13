package cc.sitec.kboot.common;

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
public class ServiceException extends RuntimeException {

    public ServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ServiceException(String message) {
        super(message);
    }
}
