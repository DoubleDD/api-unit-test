package com.keeton.spring.custom.annotation.v2;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class KeetonScannerRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata
                .getAnnotationAttributes(KeetonScan.class.getName()));//获取@Import所标注的注解信息
        KeetonScanner scanner = new KeetonScanner(registry);
        // AnnotationAttributes有获取各种注解信息的方法
        scanner.doScan(annoAttrs.getStringArray("value"));
    }
}
