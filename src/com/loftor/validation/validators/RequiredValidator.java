package com.loftor.validation.validators;

import com.loftor.validation.IValidator;
import com.loftor.validation.config.pojo.Rule;
import org.apache.commons.lang.ClassUtils;

/**
 * 必填处理
 *
 * @author jimmysong
 */
public class RequiredValidator implements IValidator {


    public boolean execute(Object context, Class type, Object value, Rule rule) {
        if (value == null) return false;
        if (ClassUtils.isAssignable(type, Object[].class))
            return value != null && ((Object[]) value).length > 0;
        else if (type == String.class)
            return value != null && ((String) value).trim().length() > 0;
        return true;
    }

}
