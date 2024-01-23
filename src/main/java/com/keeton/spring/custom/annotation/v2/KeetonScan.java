package com.keeton.spring.custom.annotation.v2;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import(KeetonScannerRegistrar.class)
public @interface KeetonScan {

    String[] value() default {};
}
