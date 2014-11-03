package com.loftor.validation.validators;

import com.loftor.validation.IValidator;
import com.loftor.validation.config.pojo.Rule;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * 正则匹配
 *
 * @author jimmysong
 */
public class RegexValidator implements IValidator {


    public boolean execute(Object context, Class type, Object value, Rule rule) {
        if (value == null || (type == String.class && StringUtils.isEmpty((String) value))) return true;
        String regex = rule.getParameter("target");
        if (StringUtils.isBlank(regex)) return true;
        return Pattern.matches(regex, (String) value);
    }
}
