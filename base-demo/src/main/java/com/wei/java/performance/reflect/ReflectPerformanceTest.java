package com.wei.java.performance.reflect;

import com.wei.java.util.SystemOutUtil;

import java.lang.reflect.Method;

/**
 * 反射性能测试
 *
 * @author buhuan.wang
 * @since 2021/4/24
 */
public class ReflectPerformanceTest {
    private int testNum = 100000;

    public static void test() {
        ReflectPerformanceTest reflectPerformanceTest = new ReflectPerformanceTest();
        reflectPerformanceTest.testByReflect();
        reflectPerformanceTest.testByReflectCache();
        reflectPerformanceTest.testByDirectSet();
    }

    public void testByReflect() {
        long starttime = System.currentTimeMillis();
        SystemOutUtil.println("testByReflect start.");
        UserDTO userDTO = new UserDTO();
        for (int i = 0; i < testNum; i++) {
            Class<?> userDTOClass = userDTO.getClass();
            try {
                Method method = userDTOClass.getMethod("setName", String.class);

                method.setAccessible(true);
                method.invoke(userDTO, String.valueOf(i));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SystemOutUtil.println(userDTO.getName());
        long endtime = System.currentTimeMillis();
        SystemOutUtil.println("testByReflect end with cost: " + (endtime - starttime));
    }


    public void testByReflectCache() {
        long starttime = System.currentTimeMillis();
        SystemOutUtil.println("testByReflectCache start.");
        UserDTO userDTO = new UserDTO();
        Class<?> userDTOClass = userDTO.getClass();
        try {
            Method method = userDTOClass.getMethod("setName", String.class);
            method.setAccessible(true);
            for (int i = 0; i < testNum; i++) {
                method.invoke(userDTO, String.valueOf(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        SystemOutUtil.println(userDTO.getName());
        long endtime = System.currentTimeMillis();
        SystemOutUtil.println("testByReflectCache end with cost: " + (endtime - starttime));
    }

    public void testByDirectSet() {
        long starttime = System.currentTimeMillis();
        SystemOutUtil.println("testByDirectSet start.");
        UserDTO userDTO = new UserDTO();
        for (int i = 0; i < testNum; i++) {
            userDTO.setName(String.valueOf(i));
        }
        SystemOutUtil.println(userDTO.getName());
        long endtime = System.currentTimeMillis();
        SystemOutUtil.println("testByDirectSet end with cost: " + (endtime - starttime));
    }
}
