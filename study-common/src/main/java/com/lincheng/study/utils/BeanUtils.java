package com.lincheng.study.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.swing.text.html.Option;
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
        List<String> ignoreProperties = new ArrayList<>(Arrays.asList(getNullPropertyNames(source)));
        ignoreProperties.add("objectType");
        org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties.toArray(new String[ignoreProperties.size()]));

    }


    public static void copyList(List sourceList, List targetList, Class<?> targetClazz) {
        if (CollectionUtils.isNotEmpty(sourceList) && Optional.ofNullable(targetList).isPresent()) {
            sourceList.forEach(source -> {
                try {
                    Object target = targetClazz.newInstance();
                    org.springframework.beans.BeanUtils.copyProperties(source, target);
                    targetList.add(target);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    public static void copyListIgnoreNull(List sourceList, List targetList, Class<?> targetClazz) {
        if (CollectionUtils.isNotEmpty(sourceList) && Optional.ofNullable(targetList).isPresent()) {
            sourceList.forEach(source -> {
                try {
                    Object target = targetClazz.newInstance();
                    List<String> ignoreProperties = new ArrayList<>(Arrays.asList(getNullPropertyNames(source)));
                    ignoreProperties.add("objectType");
                    org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties.toArray(new String[ignoreProperties.size()]));
                    targetList.add(target);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
    }


}
