package com.keeton.spring.custom.annotation;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.FactoryBean;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyFactoryBean<T> implements FactoryBean<T> {
    private final ThreadLocal<SimpleDateFormat> dateTimeFormatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    private final Class<T>                      interfaceType;

    public MyFactoryBean(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
    }

    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class[]{interfaceType},
                createProxy(interfaceType));
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceType;
    }


    private InvocationHandler createProxy(Class<T> interfaceType) {
        return (proxy, method, args) -> {
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            }

            // 返回值类型
            Class<?> returnType        = method.getReturnType();
            Type     genericReturnType = method.getGenericReturnType();

            System.out.println("returnType = " + returnType);


            // 处理sql
            handlerSql(interfaceType, method, args, returnType);

            return new HashMap<>();
        };
    }

    private void handlerSql(Class interfaceType, @NotNull Method method, Object[] args, Class returnType) {
        MappedStatement mappedStatement = getMappedStatement(interfaceType, method.getName());
        BoundSql        boundSql        = mappedStatement.getBoundSql(args);

        Map<String, Object> kv  = kv(method, args);
        String              sql = formatSql(configuration, boundSql, kv);

        System.out.println("sql = " + sql);

        // http


    }

    private Map<String, Object> kv(@NotNull Method method, Object[] args) {
        TreeMap<String, Object> paramMap       = new TreeMap<>();
        Parameter[]             parameters     = method.getParameters();
        int                     parameterCount = method.getParameterCount();
        for (int i = 0; i < parameterCount; i++) {
            String key   = parameters[i].getName();
            Object value = args[i];
            paramMap.put(key, value);
        }
        return paramMap;
    }

    Configuration configuration = new Configuration();

    private MappedStatement getMappedStatement(Class interfaceType, String methodName) {

        String      resource = getXmlPath(interfaceType);
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XMLMapperBuilder builder = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
        builder.parse();

        return configuration.getMappedStatement(methodName);
    }

    private String getXmlPath(Class interfaceType) {
        String baseDir = "com/keeton/spring/custom/annotation/";
        return baseDir + interfaceType.getSimpleName() + ".xml";
    }


    private String formatSql(Configuration configuration, BoundSql boundSql, Map<String, Object> args) {
        String sql = boundSql.getSql();
        sql = beautifySql(sql);
        Object                 parameterObject     = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings   = boundSql.getParameterMappings();
        TypeHandlerRegistry    typeHandlerRegistry = configuration.getTypeHandlerRegistry();

        List<String> parameters = new ArrayList<>();
        if (parameterMappings != null) {
            MetaObject metaObject = args == null ? null : configuration.newMetaObject(args);
            for (ParameterMapping parameterMapping : parameterMappings) {
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    //  参数值
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    //  获取参数名称
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        // 获取参数值
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        // 如果是单个值则直接赋值
                        value = parameterObject;
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }

                    if (value instanceof Number) {
                        parameters.add(String.valueOf(value));
                    } else {
                        StringBuilder builder = new StringBuilder();
                        builder.append("'");
                        if (value instanceof Date) {
                            builder.append(dateTimeFormatter.get().format((Date) value));
                        } else if (value instanceof String) {
                            builder.append(value);
                        }
                        builder.append("'");
                        parameters.add(builder.toString());
                    }
                }
            }
        }

        for (String value : parameters) {
            sql = sql.replaceFirst("\\?", value);
        }
        return sql;
    }

    public static String beautifySql(String sql) {
        sql = sql.replaceAll("[\\s\n ]+", " ");
        return sql;
    }
}
