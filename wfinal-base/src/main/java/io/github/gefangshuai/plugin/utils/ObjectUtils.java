/*
 * Copyright (c) 2014-2015, gefangshuai (lifedever@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 	you may not use this file except in compliance with the License.
 * 	You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.gefangshuai.plugin.utils;

import java.lang.reflect.Method;

/**
 * Created by fangshuai on 2014-12-09-0009.
 */
public class ObjectUtils {

    /**
     * 根据指定类型，返回字符串所转化成的具体类型的值
     * @param value
     * @param clazz
     * @return
     */
    public static Object getRealTypeValue(String value, Class clazz) {
        try {
            Method method = clazz.getMethod("valueOf", String.class);
            return method.invoke(clazz, value);
        } catch (Exception e) { // 没有这个方法，直接返回String
            return value==null? null : value.toString();
        }
    }
}
