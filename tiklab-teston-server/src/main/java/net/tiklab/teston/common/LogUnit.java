package net.tiklab.teston.common;

import com.alibaba.fastjson.JSONObject;
import net.tiklab.security.logging.model.Logging;
import net.tiklab.security.logging.model.LoggingType;
import net.tiklab.security.logging.service.LoggingByTemplService;
import net.tiklab.user.user.model.User;
import net.tiklab.utils.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

import static net.tiklab.teston.common.MessageTemplateConstant.LOG_TEMPLATE_ID;

/**
 * 日志公共方法
 */
@Service
public class LogUnit {

    @Value("${base.url:null}")
    String baseUrl;

    @Autowired
    LoggingByTemplService opLogByTemplService;

    public void log(String type, String module, Map<String,String> map){

        User user = new User();
        user.setId( LoginContext.getLoginId());

        Logging log = new Logging();
        LoggingType opLogType = new LoggingType();
        opLogType.setId(type);
        opLogType.setBgroup("teston");

        log.setActionType(opLogType);
        log.setModule(module);
        log.setLoggingTemplateId(LOG_TEMPLATE_ID);
        log.setTimestamp(new Timestamp(System.currentTimeMillis()));
        log.setUser(user);
        log.setBgroup("teston");
        log.setContent(JSONObject.toJSONString(map));
        log.setBaseUrl(baseUrl);

        String contentStr = map.get("user")+map.get("actionType")+map.get("name");
        log.setAbstractContent(contentStr);

        opLogByTemplService.createLog(log);
    }
}
