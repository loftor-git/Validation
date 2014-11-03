package com.loftor.validation.validators;

import com.loftor.validation.IValidator;
import com.loftor.validation.config.pojo.Rule;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 相等匹配
 *
 * @author jimmysong
 */
public class EqualsValidator implements IValidator {

    public boolean execute(Object context, Class type, Object value, Rule rule) {
        try {
            if (value == null || (type == String.class && StringUtils.isEmpty((String) value))) return true;
            String toName = rule.getParameter("target");
            Object toValue;
            if (StringUtils.isBlank(toName)) {
                toValue = rule.getParameter("value");
            } else {
                toValue = PropertyUtils.getProperty(context, toName);
            }
            return value.equals(toValue);
        } catch (Exception e) {
            return false;
        }
    }

}
