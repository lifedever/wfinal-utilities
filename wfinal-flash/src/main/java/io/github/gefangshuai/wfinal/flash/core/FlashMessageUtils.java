package io.github.gefangshuai.wfinal.flash.core;

import com.jfinal.core.Controller;

import javax.servlet.http.HttpSession;

/**
 * flash 消息设置工具类
 * Created by gefangshuai on 2015-03-22-0022.
 */
public class FlashMessageUtils {

    /**
     * 设置成功返回的消息
     * @param controller
     * @param message
     */
    public static void setSuccessMessage(Controller controller, String message){
        controller.setSessionAttr(FlashMessage.FLASH_SUCCESS, message);
    }

    /**
     * 设置信息消息
     * @param controller
     * @param message
     */
    public static void setInfoMessage(Controller controller, String message){
        controller.setSessionAttr(FlashMessage.FLASH_INFO, message);
    }

    /**
     * 设置错误消息
     * @param controller
     * @param message
     */
    public static void setErrorMessage(Controller controller, String message){
        controller.setSessionAttr(FlashMessage.FLASH_ERROR, message);
    }

    /**
     * 设置警告消息
     * @param controller
     * @param message
     */
    public static void setWarningMessage(Controller controller, String message){
        controller.setSessionAttr(FlashMessage.FLASH_WARNING, message);
    }

    /**
     * 设置自定义消息
     * @param controller
     * @param attribute
     * @param message
     */
    public static void setMessage(Controller controller, String attribute, String message) {
        FlashMessage.setCustomMessage(attribute);
        controller.setSessionAttr(attribute, message);
    }

    /**
     * 清楚session中的所有Flash消息
     * @param controller
     */
    public static void clearAll(Controller controller){
        controller.removeSessionAttr(FlashMessage.FLASH_SUCCESS);
        controller.removeSessionAttr(FlashMessage.FLASH_WARNING);
        controller.removeSessionAttr(FlashMessage.FLASH_INFO);
        controller.removeSessionAttr(FlashMessage.FLASH_ERROR);
        controller.removeSessionAttr(FlashMessage.getCustomMessage());
    }

}
