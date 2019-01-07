
package com.hisense.smarthome.sdk.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于将复合类型对象转成XML/JSON时的tag识别
 * 
 * @author Merry.Zhao
 * @since 5.1
 */
@Target({
        ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ComplexDescription {
    /**
     * 返回注解配置的值
     * 
     * @return
     */
    String value();
}
