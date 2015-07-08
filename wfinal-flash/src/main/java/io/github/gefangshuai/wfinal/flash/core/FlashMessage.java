package io.github.gefangshuai.wfinal.flash.core;

/**
 * 消息类，定义了四个页面预定义参数
 * <br/>
 * <p><code>flash_success</code> 成功消息</p>
 * <p><code>flash_info</code> 信息消息</p>
 * <p><code>flash_warning</code> 警告消息</p>
 * <p><code>flash_error</code> 错误消息</p>
 * Created by gefangshuai on 2015-03-22-0022.
 */
public class FlashMessage {
    public static final String FLASH_SUCCESS = "flash_success";
    public static final String FLASH_INFO = "flash_info";
    public static final String FLASH_WARNING = "flash_warning";
    public static final String FLASH_ERROR = "flash_error";

    private static String customMessage;

    public static String getCustomMessage() {
        return customMessage;
    }

    public static void setCustomMessage(String customMessage) {
        FlashMessage.customMessage = customMessage;
    }
}
