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

    public static void redirectSuccessMessage(Controller controller, String url, String message) {
        setSuccessMessage(controller, message);
        controller.redirect(url);
    }

    /**
     * 设置信息消息
     * @param controller
     * @param message
     */
    public static void setInfoMessage(Controller controller, String message){
        controller.setSessionAttr(FlashMessage.FLASH_INFO, message);
    }

    public static void redirectInfoMessage(Controller controller, String url, String message) {
        setInfoMessage(controller, message);
        controller.redirect(url);
    }

    /**
     * 设置错误消息
     * @param controller
     * @param message
     */
    public static void setErrorMessage(Controller controller, String message){
        controller.setSessionAttr(FlashMessage.FLASH_ERROR, message);
    }

    public static void redirectErrorMessage(Controller controller, String url, String message) {
        setErrorMessage(controller, message);
        controller.redirect(url);
    }

    /**
     * 设置警告消息
     * @param controller
     * @param message
     */
    public static void setWarningMessage(Controller controller, String message){
        controller.setSessionAttr(FlashMessage.FLASH_WARNING, message);
    }

    public static void redirectWarningMessage(Controller controller, String url, String message) {
        setWarningMessage(controller, message);
        controller.redirect(url);
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

    public static void redirect(Controller controller, String url, String attribute, String message) {
        setMessage(controller, attribute, message);
        controller.redirect(url);
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
