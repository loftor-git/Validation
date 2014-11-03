package com.loftor.validation;

import com.loftor.validation.config.IValidateConfig;
import com.loftor.validation.config.pojo.Configuration;
import com.loftor.validation.config.pojo.Field;
import com.loftor.validation.config.pojo.Group;
import com.loftor.validation.config.pojo.Rule;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 基础验证服务
 *
 * @author jimmysong
 */
public class BasicValidateService implements IValidateService {
    private static final Logger logger = Logger.getLogger(BasicValidateService.class);

    public void init() {

        //读取配置
        this.configuration = config.readConfiguration();

        //设定已经被初始化
        this.isInited = true;
    }

    @SuppressWarnings("rawtypes")
    public ValidateResult validate(Object object, String groupName) {

        //判断是否被初始化
        if (!this.isInited) this.init();

        ValidateResult results = new ValidateResult();
        if (object == null) {
            return results;
        }

        Group group = this.configuration.getGroup(groupName);
        if (group == null) {
            return results;
        }

        Map<String, IValidator> validators = this.configuration.getValidators();
        if (validators == null || validators.isEmpty()) return results;

        List<Field> fields = group.getFields();
        if (fields == null || fields.isEmpty()) return results;
        Iterator<Field> fs = fields.iterator();
        while (fs.hasNext()) {
            Field field = fs.next();
            String fieldName = field.getName();
            Object value = null;
            Class type = null;
            List<Rule> rules = field.getRules();
            if (rules == null || rules.isEmpty()) continue;
            try {
                value = PropertyUtils.getProperty(object, fieldName);
                type = value.getClass();
            } catch (Exception e) {
                logger.warn(e.getMessage());
            } finally {
                Iterator<Rule> rs = rules.iterator();
                while (rs.hasNext()) {
                    Rule rule = rs.next();
                    String ruleName = rule.getName();
                    IValidator validator = validators.get(ruleName);
                    if (validator == null) continue;
                    boolean r = validator.execute(object, type, value, rule);
                    if (!r) {
                        results.put(fieldName, rule.getMessage());
                        break;
                    }
                }
            }
        }
        return results;
    }

    private Configuration configuration;
    private IValidateConfig config;
    private boolean isInited;

    public BasicValidateService() {

    }

    public BasicValidateService(IValidateConfig config) {

        this.setConfig(config);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public IValidateConfig getConfig() {
        return config;
    }

    public void setConfig(IValidateConfig config) {
        this.config = config;
    }

}
