package com.keeton.spring.custom.annotation;

import java.util.Map;

@Keeton
public interface TestMapper {
    Map<String, Object> selectByPrimaryKey(String name, Integer gender);

}
