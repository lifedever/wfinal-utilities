package io.github.gefangshuai.wfinal.security.proxy;


import io.github.gefangshuai.wfinal.security.core.Subject;

/**
 * 登录验证代理类，用户需要根据需求自行实现登录验证
 * <br/>
 * Created by gefangshuai on 2015/7/20.
 */
public interface LoginValidateProxy{
    /**
     * 内部实现验证，然后将Subject进行封装返回
     * @return
     */
    public Subject loginCheck();
}
