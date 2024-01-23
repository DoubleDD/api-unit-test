package com.keeton.spring.custom.annotation.v2;

import com.keeton.spring.custom.annotation.Keeton;
import com.keeton.spring.custom.annotation.MyFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

public class KeetonScanner extends ClassPathBeanDefinitionScanner {
    public KeetonScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
        addIncludeFilter(new AnnotationTypeFilter(Keeton.class));
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            BeanDefinition definition    = beanDefinitionHolder.getBeanDefinition();
            String         beanClassName = definition.getBeanClassName();
            definition.setBeanClassName(MyFactoryBean.class.getName());
            try {
                definition.getConstructorArgumentValues().addGenericArgumentValue(Class.forName(beanClassName));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return beanDefinitionHolders;
    }


    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isIndependent() && beanDefinition.getMetadata().isInterface();
    }
}