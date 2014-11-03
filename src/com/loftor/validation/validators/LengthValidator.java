package com.loftor.validation.validators;

import com.loftor.validation.IValidator;
import com.loftor.validation.config.pojo.Rule;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 字符长度区间验证器
 */
public class LengthValidator implements IValidator {
    private static final Logger logger = Logger.getLogger(LengthValidator.class);

    public boolean execute(Object context, Class type, Object value, Rule rule) {
        try {
            if (value == null || (type == String.class && StringUtils.isEmpty((String) value))) return true;

            if (type != String.class) {
                return false;
            }

            int length = ((String) value).trim().length();
            String minString = rule.getParameter("min");
            int min = StringUtils.isNumeric(minString) ? Integer.parseInt(minString) : -1;
            String maxString = rule.getParameter("max");
            int max = StringUtils.isNumeric(maxString) ? Integer.parseInt(maxString) : -1;

            boolean isMin = true;
            boolean isMax = true;

            if (min != -1) {
                isMin = length >= min;
            }

            if (max != -1) {
                isMax = length <= max;
            }
            return isMin && isMax;
        } catch (Exception e) {
            return false;
        }

    }

}
