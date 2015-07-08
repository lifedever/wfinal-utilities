package io.github.gefangshuai.wfinal.flash.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import io.github.gefangshuai.wfinal.flash.core.FlashMessage;
import io.github.gefangshuai.wfinal.flash.core.FlashMessageUtils;

import javax.servlet.http.HttpSession;

/**
 * flash 消息提示拦截器
 * Created by gefangshuai on 2015-03-22-0022.
 */
public class FlashMessageInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation ai) {
        HttpSession session = ai.getController().getSession();
        String flash_success_message = (String) session.getAttribute(FlashMessage.FLASH_SUCCESS);
        String flash_warnging_message = (String) session.getAttribute(FlashMessage.FLASH_WARNING);
        String flash_info_message = (String) session.getAttribute(FlashMessage.FLASH_INFO);
        String flash_error_message = (String) session.getAttribute(FlashMessage.FLASH_ERROR);

        ai.getController().setAttr(FlashMessage.FLASH_SUCCESS, flash_success_message);
        ai.getController().setAttr(FlashMessage.FLASH_WARNING, flash_warnging_message);
        ai.getController().setAttr(FlashMessage.FLASH_INFO, flash_info_message);
        ai.getController().setAttr(FlashMessage.FLASH_ERROR, flash_error_message);

        FlashMessageUtils.clearAll(session);
        ai.invoke();
    }
}
