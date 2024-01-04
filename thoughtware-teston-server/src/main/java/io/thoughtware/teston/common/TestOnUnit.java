package io.thoughtware.teston.common;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.message.message.model.SendMessageNotice;
import io.thoughtware.message.message.service.SendMessageNoticeService;
import io.thoughtware.security.logging.model.Logging;
import io.thoughtware.security.logging.model.LoggingType;
import io.thoughtware.security.logging.service.LoggingByTempService;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import io.thoughtware.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 公共方法
 */
@Service
public class TestOnUnit {

    @Autowired
    UserService userService;

    @Value("${base.url:null}")
    String baseUrl;

    @Autowired
    LoggingByTempService opLogByTemplService;

    @Autowired
    SendMessageNoticeService sendMessageNoticeService;

    /**
     * 日志
     * @param type
     * @param module
     * @param map
     */
    public void log(String type, String module, Map<String,String> map){

        Logging log = new Logging();
        LoggingType opLogType = new LoggingType();
        opLogType.setId(type);
        opLogType.setBgroup("teston");
        log.setActionType(opLogType);

        User user = new User();
        user.setId(LoginContext.getLoginId());
        log.setUser(user);

        log.setModule(module);
        log.setAction(map.get("name"));

        log.setLink(map.get("link"));
        log.setBgroup("teston");
        log.setData(JSONObject.toJSONString(map));
        log.setBaseUrl(baseUrl);

        opLogByTemplService.createLog(log);
    }

    public void message(Map<String, String> map){
        SendMessageNotice sendMessageNotice = new SendMessageNotice();
        String jsonString = JSONObject.toJSONString(map);

        sendMessageNotice.setSendId(LoginContext.getLoginId());
        sendMessageNotice.setSiteData(jsonString);
        sendMessageNotice.setEmailData(jsonString);
        sendMessageNotice.setDingdingData(jsonString);
        sendMessageNotice.setQywechatData(jsonString);

        sendMessageNotice.setId("MESSAGE_NOTICE_ID");
        sendMessageNotice.setBaseUrl(baseUrl);

        sendMessageNotice.setLink(map.get("link"));
        sendMessageNotice.setAction(map.get("name"));

        sendMessageNoticeService.sendMessageNotice(sendMessageNotice);
    }

    /**
     * 获取当前用户的信息
     * @return
     */
    public User getUser(){
        String userId = LoginContext.getLoginId();

        return userService.findUser(userId);
    }


    /**
     * 日期转换成cron表达式
     * @param date 时间 00:00格式
     * @param day 星期几
     * @return cron
     */
    public static String weekCron (String date,int day){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date parse ;
        try {
            parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new ApplicationException("时间格式转换错误，错误时间："+date);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("mm");
        String mm = dateFormat.format(parse);

        SimpleDateFormat format = new SimpleDateFormat("HH");
        String hh = format.format(parse);
        String week = String.valueOf(day);

        //获取
        long time;
        long time1;
        try {
            time = simpleDateFormat.parse(date).getTime();
            time1 = simpleDateFormat.parse(date(5)).getTime();
        } catch (ParseException e) {
            throw new ApplicationException("时间格式转换错误，错误时间："+date);
        }

        if (day == week() && time > time1){
            week = "*" ;
        }

        return "0"+ " "+mm+" "+ hh+" "+"? *"+" "+ week;
    }

    /**
     * 返回系统时间
     * @param type 时间类型 1.(yyyy-MM-dd HH:mm:ss) 2.(yyyy-MM-dd) 3.(HH:mm:ss) 4.([format]) 5.(HH:mm)
     * @return 时间
     */
    public static String date(int type){
        switch (type) {
            case 2 -> {
                return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            }
            case 3 -> {
                return new SimpleDateFormat("HH:mm:ss").format(new Date());
            }
            case 4 -> {
                String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                return "[" + format + "]" + "  ";
            }
            case 5 -> {
                return new SimpleDateFormat("HH:mm").format(new Date());
            }
            case 6 -> {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
            }
            default -> {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            }
        }
    }


    /**
     * 返回今天星期几
     * @return 1: 周一 7:周天
     */
    public static int week() {
        Calendar calendar=Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (i == 0){
            return 7;
        }
        return i;
    }

    /**
     * cron 表达式转换成日期
     * @param cron 表达式
     * @return 日期
     */
    public static Map<String, String > cronWeek(String cron) {
        String[] s = cron.split(" ");
        String date = s[2]+":"+s[1];
        String s1 = s[5];
        int day = 0;
        if (!s1.equals("*")){
            day = Integer.parseInt(s[5]);
        }

        String[] weekDays = {"今天", "周一", "周二", "周三", "周四", "周五", "周六","周日"};
        String time = weekDays[day];

        Map<String, String > map = new HashMap<>();
        map.put("cron",time);
        map.put("time",date);
        map.put("weekTime",weekTime(cron));
        return map;
    }

    /**
     * 周几装换成具体日期
     * @param cron 表达式
     * @return 日期
     */
    public static String weekTime(String cron){
        String[] s = cron.split(" ");
        String time = s[2]+":"+s[1];
        int day = 0;
        if (!s[5].equals("*")){
            day = Integer.parseInt(s[5]);
        }

        int week = week();
        if (day != 0 && day > week){
            day = day - week;
        }else if (day != 0 && day < week){
            day = day + week;
        }else if (day != 0){
            day = 7;
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DATE, day);
        String format = simpleDateFormat.format(calendar.getTime());
        return format+" "+time+":00";
    }


}
