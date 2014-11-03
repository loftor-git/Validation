package com.loftor.validation.validators;

import com.loftor.validation.IValidator;
import com.loftor.validation.config.pojo.Rule;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 正则匹配
 */
public class MatchValidator implements IValidator {

    private Map<String, String> regexList;

    public MatchValidator() {
        regexList = new HashMap<String, String>();
        regexList.put("email", "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        regexList.put("url", "[a-zA-z]+://[^\\s]*");
        regexList.put("qq", "[1-9][0-9]{4,}");
        regexList.put("ip", "\\d+\\.\\d+\\.\\d+\\.\\d+");
        regexList.put("mobile", "1[3458]{1}\\d{9}");
        regexList.put("alpha", "([a-z])+");
        regexList.put("alpha_num", "([a-z0-9])+");
        regexList.put("slug", "([-a-z0-9_-])+");
        regexList.put("date", "^(\\d{4})-(\\d{2})-(\\d{2})( (\\d{2}):(\\d{2}):(\\d{2}))?$");
        regexList.put("id_card","\\d{15}|\\d{18}");
        regexList.put("postcode","[1-9]\\d{5}(?!\\d)");
        regexList.put("is_chinese","^[\\u4e00-\\u9fa5]{0,}$");

    }


    public boolean execute(Object context, Class type, Object value, Rule rule) {
        if (value == null || (type == String.class && StringUtils.isEmpty((String) value))) return true;
        String regex = rule.getParameter("target");
        if (regexList.containsKey(regex)) {
            regex = regexList.get(regex);
        } else {
            return true;
        }
        return Pattern.matches(regex, (String) value);
    }
}
