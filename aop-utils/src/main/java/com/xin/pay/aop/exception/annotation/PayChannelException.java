package com.xin.pay.aop.exception.annotation;

import com.xin.pay.aop.exception.enums.ReturnDataFormatEnum;
import com.xin.pay.aop.exception.enums.ReturnModeEnum;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PayChannelException {

    ReturnDataFormatEnum returnDataFormat() default ReturnDataFormatEnum.JSON;

    ReturnModeEnum returnMode() default ReturnModeEnum.HTTP;

    String respCodeFieldName() default "";

}
