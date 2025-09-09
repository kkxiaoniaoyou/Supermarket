package com.cbz.example.utils;

import java.lang.reflect.Field;

/**
 * 自定义的关于Bean的工具类
 */
public class MyBeanUtil {

    private static final String SERIAL_VERSION_UID = "serialVersionUID";

    /**
     * 获取一个对象中非空属性的数量
     *
     * @param obj
     * @return
     */
    public static int countNonNullProperties(Object obj) {
        if (obj == null) {
            return 0;
        }

        int count = 0;

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.getName().equals(SERIAL_VERSION_UID)) {
                continue;
            }
            field.setAccessible(true);

            try {
                Object value = field.get(obj);

                if (value != null) {
                    count++;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return count;
    }
}
