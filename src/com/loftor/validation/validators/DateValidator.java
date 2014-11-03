package com.loftor.validation.validators;

import com.loftor.validation.IValidator;
import com.loftor.validation.config.pojo.Rule;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期区间验证器
 *
 * @author jimmysong
 */
public class DateValidator implements IValidator {
    private static final Logger logger = Logger.getLogger(DateValidator.class);

    @SuppressWarnings("rawtypes")
    public boolean execute(Object context, Class type, Object value, Rule rule) {
        try {
            if (value == null || (type == String.class && StringUtils.isEmpty((String) value))) return true;
            if (type != Date.class && type != String.class) return false;
            String format = rule.getParameter("format");
            if (StringUtils.isBlank(format)) {
                format = "yyyy-MM-dd";
            }
            DateFormat dateFormat = new SimpleDateFormat(format);
            Date date;
            if (type == Date.class) {
                date = (Date) value;
            } else {
                date = dateFormat.parse((String) value);
            }

            String beginString = rule.getParameter("begin");
            Date beginDate = !StringUtils.isBlank(beginString) ? dateFormat.parse(beginString) : null;
            String endString = rule.getParameter("end");
            Date endDate = !StringUtils.isBlank(endString) ? dateFormat.parse(endString) : null;

            boolean flag1 = true;
            boolean flag2 = true;

            if (beginDate != null) {
                flag1 = date.after(beginDate);
            }

            if (endString != null) {
                flag2 = date.before(endDate);
            }

            return flag1 && flag2;
        } catch (Exception e) {
            return false;
        }
    }

}
