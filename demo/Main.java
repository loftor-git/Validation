import com.loftor.validation.BasicValidateService;
import com.loftor.validation.IValidateService;
import com.loftor.validation.ValidateResult;
import com.loftor.validation.config.BasicValidateConfig;
import com.loftor.validation.config.IValidateConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Loftor on 2014/11/3.
 */
public class Main {
    public static void main(String args[]) {

        /**
         * Validation.FO的配置资源
         */
        // 验证器配置，系统默认配置
        String validatorsXML = "resources/validators.xml";
        // 规则配置
        String rulesXML = "resources/rule.xml";

        /**
         * 实例化配置对象
         */
        IValidateConfig config =new BasicValidateConfig(validatorsXML, rulesXML);
        /**
         * 实例化验证服务层
         */
        IValidateService validateService = new BasicValidateService(config);

        Map<String,String> map = new HashMap<String, String>();

        User user = new User();
        user.setUsername("admin");
        user.setPassword("12345");

        /**
         * 执行验证
         */
        ValidateResult validateResult = validateService.validate(user, "demo.validate");
        // 输出结果
        if(validateResult.isPass()) {
            System.out.println("验证成功");
        } else {
            System.out.println("验证失败，结果如下");
            System.out.println(validateResult.getMessages());
        }
    }
}
