package com.lincheng.study.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;

/**
 * @author lincheng5
 * @description: Bean工具类
 * @date 2021/7/28 23:52
 */
public final class BeanUtils {

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        /* 1.源对象与目标对象都不能为空 */
        if (target == null || source == null) {
            return;
        }

        /* 2.深度拷贝 */
        List<String> ignoreProperties = new ArrayList<>();
        ignoreProperties.addAll(Arrays.asList(getNullPropertyNames(source)));
        ignoreProperties.add("objectType");
        org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties.toArray(new String[ignoreProperties.size()]));

    }

}
