package io.github.gefangshuai.wfinal.base.utils;

/**
 * 枚举工具类
 * @author gefangshuai
 *         email: gefangshuai@163.com
 *         webSite: http://wincn.net
 *         weibo: http://weibo.com/gefangshuai | @LifeDever
 *         createDate: 2015/8/12.
 */
public final class EnumKit {

    /**
     * 根据ordinal获取枚举值
     * @param enumClass
     * @param ordinal
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> E getEnum(final Class<E> enumClass, int ordinal) {
        return enumClass.getEnumConstants()[ordinal];
    }
}
