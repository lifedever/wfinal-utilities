package io.github.gefangshuai.wfinal.security.plugin;

import com.jfinal.ext.kit.JfinalKit;
import com.jfinal.log.Logger;
import com.jfinal.plugin.IPlugin;
import io.github.gefangshuai.wfinal.security.core.SecurityRule;
import io.github.gefangshuai.wfinal.security.interceptor.SecurityInterceptor;

/**
 * Created by gefangshuai on 2015/7/17.
 */
public class SecurityPlugin implements IPlugin {
    private final Logger log = Logger.getLogger(getClass());
    private SecurityRule securityRule;

    public SecurityPlugin() {

    }

    public SecurityRule getSecurityRule() {
        return securityRule;
    }

    public void setSecurityRule(SecurityRule securityRule) {
        this.securityRule = securityRule;
    }

    @Override
    public boolean start() {
        log.debug("----init Security Plugin!----");
        JfinalKit.getInterceptors().addGlobalActionInterceptor(new SecurityInterceptor());
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }


}
