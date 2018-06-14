package com.xin.pay.exception.aop.annotation;

import com.xin.pay.exception.aop.enums.ReturnDataFormatEnum;
import com.xin.pay.exception.aop.enums.ReturnModeEnum;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PayChannelException {

    ReturnDataFormatEnum returnDataFormat() default ReturnDataFormatEnum.JSON;

    ReturnModeEnum returnMode() default ReturnModeEnum.HTTP;

    String respCodeFieldName() default "";

}
