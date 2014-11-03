package com.loftor.validation;

import java.util.HashMap;

/**
 * 验证结果
 */
public class ValidateResult extends HashMap<String, String> {
    /**
     * 判断是否验证通过
     *
     * @return
     */
    public boolean isPass() {
        return this.size() == 0;
    }

    /**
     * 获取第一条错误信息
     *
     * @return
     */
    public String getMessage() {
        for (String key : keySet()) {
            return get(key);
        }
        return "";
    }


    /**
     * 获取错误信息数组
     *
     * @return
     */
    public String[] getMessages() {
        String[] messages = new String[values().size()];
        Object[] objects = values().toArray();
        for (int i = 0; i < objects.length; i++) {
            messages[i] = (String) objects[i];
        }
        return messages;
    }
}
