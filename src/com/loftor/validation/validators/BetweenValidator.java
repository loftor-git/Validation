package com.loftor.validation.validators;

import com.loftor.validation.IValidator;
import com.loftor.validation.config.pojo.Rule;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 数字大小区间验证器
 *
 * @author jimmysong
 */
public class BetweenValidator implements IValidator {
    private static final Logger logger = Logger.getLogger(BetweenValidator.class);

    @SuppressWarnings("rawtypes")
    public boolean execute(Object context, Class type, Object value, Rule rule) {
        try {
            if (value == null || (type == String.class && StringUtils.isEmpty((String) value))) return true;
            Double toValue = Double.parseDouble(value.toString());
            String minString = rule.getParameter("min");
            Double min = StringUtils.isNumeric(minString) ? Double.parseDouble(minString) : null;
            String maxString = rule.getParameter("max");
            Double max = StringUtils.isNumeric(maxString) ? Double.parseDouble(maxString) : null;

            boolean isMin = true;
            boolean isMax = true;

            if (min != null) {
                isMin = toValue >= min;
            }

            if (max != null) {
                isMax = toValue <= max;
            }

            return isMin && isMax;
        } catch (Exception e) {
            return false;
        }
    }

}
