package io.thoughtware.teston.common;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.security.logging.model.Logging;
import io.thoughtware.security.logging.model.LoggingType;
import io.thoughtware.security.logging.service.LoggingByTempService;
import io.thoughtware.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

/**
 * 日志公共方法
 */
@Service
public class LogUnit {

    @Value("${base.url:null}")
    String baseUrl;

    @Autowired
    LoggingByTempService opLogByTemplService;

    public void log(String type, String module, Map<String,String> map){

        Logging log = new Logging();
        LoggingType opLogType = new LoggingType();
        opLogType.setId(type);
        opLogType.setBgroup("teston");
        log.setActionType(opLogType);

        User user = new User();
        user.setId( LoginContext.getLoginId());
        log.setUser(user);

        log.setModule(module);
        log.setAction(map.get("name"));

        log.setLink(map.get("link"));
        log.setBgroup("teston");
        log.setData(JSONObject.toJSONString(map));
        log.setBaseUrl(baseUrl);

        opLogByTemplService.createLog(log);
    }
}
