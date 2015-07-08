package io.github.gefangshuai.wfinal.flash.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import io.github.gefangshuai.wfinal.flash.core.FlashMessage;
import io.github.gefangshuai.wfinal.flash.core.FlashMessageUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * flash 消息提示拦截器
 * Created by gefangshuai on 2015-03-22-0022.
 */
public class FlashMessageInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation ai) {
        Controller controller = ai.getController();
        String flash_success_message = controller.getSessionAttr(FlashMessage.FLASH_SUCCESS);
        String flash_warning_message = controller.getSessionAttr(FlashMessage.FLASH_WARNING);
        String flash_info_message = controller.getSessionAttr(FlashMessage.FLASH_INFO);
        String flash_error_message = controller.getSessionAttr(FlashMessage.FLASH_ERROR);

        ai.getController().setAttr(FlashMessage.FLASH_SUCCESS, flash_success_message);
        ai.getController().setAttr(FlashMessage.FLASH_WARNING, flash_warning_message);
        ai.getController().setAttr(FlashMessage.FLASH_INFO, flash_info_message);
        ai.getController().setAttr(FlashMessage.FLASH_ERROR, flash_error_message);

        if (StringUtils.isNotBlank(FlashMessage.getCustomMessage())) {
            String flash_custom_message = controller.getSessionAttr(FlashMessage.getCustomMessage());
            ai.getController().setAttr(FlashMessage.getCustomMessage(), flash_custom_message);
        }

        FlashMessageUtils.clearAll(controller);
        ai.invoke();
    }
}
