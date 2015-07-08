package io.github.gefangshuai.wfinal.flash.core;

import com.jfinal.core.Controller;

import javax.servlet.http.HttpSession;

/**
 * flash 消息设置工具类
 * Created by gefangshuai on 2015-03-22-0022.
 */
public class FlashMessageUtils {

    public static void setSuccessMessage(Controller controller, String message){
        controller.getSession().setAttribute(FlashMessage.FLASH_SUCCESS, message);
    }

    public static void setInfoMessage(Controller controller, String message){
        controller.getSession().setAttribute(FlashMessage.FLASH_INFO, message);
    }

    public static void setErrorMessage(Controller controller, String message){
        controller.getSession().setAttribute(FlashMessage.FLASH_ERROR, message);
    }

    public static void setWarningMessage(Controller controller, String message){
        controller.getSession().setAttribute(FlashMessage.FLASH_WARNING, message);
    }

    /**
     * 清楚session中的所有Flash消息
     * @param session
     */
    public static void clearAll(HttpSession session){
        session.removeAttribute(FlashMessage.FLASH_SUCCESS);
        session.removeAttribute(FlashMessage.FLASH_WARNING);
        session.removeAttribute(FlashMessage.FLASH_INFO);
        session.removeAttribute(FlashMessage.FLASH_ERROR);
    }

}
