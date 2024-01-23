package com.keeton.spring.custom.annotation;

import com.keeton.spring.custom.annotation.v2.KeetonScan;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;
import java.lang.reflect.Method;
@KeetonScan("com.keeton.spring.custom.annotation")
@SpringBootApplication
public class DefaultProxyCreatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(DefaultProxyCreatorApplication.class, args);
    }
}

interface Test2Service {
    /**
     * 被标记的方法
     */
    @Tx
    void a();

    void b();

    void c();
}



@Service
class Test2ServiceImpl implements Test2Service {

    @Override
    public void a() {
        System.out.println("test2 method a");
    }

    /**
     * 被标记的方法
     */
    @Tx
    @Override
    public void b() {
        System.out.println("test2 method b");
    }

    @Override
    public void c() {
        System.out.println("test2 method c");
    }
}


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@interface Tx {
}

class TxInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String name = invocation.getMethod().getName();
        System.out.println(String.format("------------%s: before----------", name));
        Object object = invocation.proceed();
        System.out.println(String.format("------------%s: after----------", name));
        return object;
    }
}

class TxMethodPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {
    /**
     * 拦截规则：
     * 1: 接口类名上有 @Tx 注解
     * 2: 接口方法名上有 @Tx 注解
     *
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return methodCanPass(method) || classCanPass(method.getDeclaringClass());
    }

    private boolean methodCanPass(Method method) {
        return method.isAnnotationPresent(Tx.class);
    }

    private boolean classCanPass(Class<?> clazz) {
        return clazz.isAnnotationPresent(Tx.class);
    }
}

@Configuration
class AopConfig {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        // 这个类就是自动代理创建器，能够自动的为每个bean生成代理
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public TxMethodPointcutAdvisor methodPointcutAdvisor(TxInterceptor txInterceptor) {
        TxMethodPointcutAdvisor advisor = new TxMethodPointcutAdvisor();
        advisor.setAdvice(txInterceptor);
        return advisor;
    }

    @Bean
    public TxInterceptor methodInterceptor() {
        return new TxInterceptor();
    }
}
