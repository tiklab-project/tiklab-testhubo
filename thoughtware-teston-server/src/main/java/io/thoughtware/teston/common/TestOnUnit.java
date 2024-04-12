package io.thoughtware.teston.common;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.message.message.model.SendMessageNotice;
import io.thoughtware.message.message.service.SendMessageNoticeService;
import io.thoughtware.security.logging.logging.model.Logging;
import io.thoughtware.security.logging.logging.model.LoggingType;
import io.thoughtware.security.logging.logging.service.LoggingByTempService;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import io.thoughtware.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
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


    public static String weekCron(String date,int timeDay){

        int year ;
        int month ;
        int day ;
        String hour ;
        String minute ;

        boolean isToday = false;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date parse ;
        try {
            parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new ApplicationException("时间格式转换错误，错误时间：" + date);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("mm");
        minute = dateFormat.format(parse);

        SimpleDateFormat format = new SimpleDateFormat("HH");
        hour = format.format(parse);

        // 判断是否为今天
        int nowWeek = week();
        if( timeDay == nowWeek){
            LocalTime currentTime = LocalTime.now();
            if (currentTime.getHour() == Integer.parseInt(hour) ){
                if (currentTime.getMinute() < Integer.parseInt(minute) ){
                    isToday = true;
                }
            } else if (currentTime.getHour() < Integer.parseInt(hour)){
                isToday = true;
            }
        }

        if (isToday){
            // 获取今天的日期
            LocalDate currentDate = LocalDate.now();
            // 获取年、月和日
            year = currentDate.getYear();
            month = currentDate.getMonthValue();
            day = currentDate.getDayOfMonth();
        }else {
            Map<String, Integer> weekDetails ;
            if (timeDay <= nowWeek ){
                weekDetails = findNextWeekTime(timeDay);
            }else {
                weekDetails = findNowWeekTime(timeDay);
            }

            year = weekDetails.get("year");
            month = weekDetails.get("month");
            day = weekDetails.get("day");
        }

        return "00 " + minute + " " + hour + " " + day + " " + month + " ? " + year;
    }

    /**
     * 获取下周的日期
     * @param week 周几
     * @return 下周的日期
     */
    public static Map<String, Integer> findNextWeekTime(int week) {

        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 找到下一个周一的日期
        LocalDate nextMonday = currentDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        LocalDate nextDate = nextMonday.plusDays(week-1);

        // 获取年、月、日和星期几
        int year = nextDate.getYear();
        int month = nextDate.getMonthValue();
        int day = nextDate.getDayOfMonth();
        int dayOfWeek = nextDate.getDayOfWeek().getValue();
        Map<String,Integer> weekMap = new HashMap<>();
        weekMap.put("year", nextDate.getYear());
        weekMap.put("month", nextDate.getMonthValue());
        weekMap.put("day", nextDate.getDayOfMonth());
        return weekMap;
    }

    /**
     * 获取本周的日期
     * @param week 周几
     * @return 下周的日期
     */
    public static Map<String, Integer> findNowWeekTime(int week) {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();

        // 获取传入数字对应的DayOfWeek
        DayOfWeek targetDayOfWeek = DayOfWeek.of(week);

        // 如果传入的是星期六或星期天，获取当前周的相应星期的日期
        LocalDate nowDate = (week == 6 || week == 7) ?
                currentDate.with(TemporalAdjusters.nextOrSame(targetDayOfWeek)) :
                currentDate.with(TemporalAdjusters.next(targetDayOfWeek));

        Map<String,Integer> weekMap = new HashMap<>();
        weekMap.put("year", nowDate.getYear());
        weekMap.put("month", nowDate.getMonthValue());
        weekMap.put("day", nowDate.getDayOfMonth());
        return weekMap;
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

        LocalDate localDate = LocalDate.of(Integer.parseInt(s[6]), Integer.parseInt(s[4]), Integer.parseInt(s[3]));
        int value = localDate.getDayOfWeek().getValue();
        String[] weekDays = {"今天","周一", "周二", "周三", "周四", "周五", "周六","周日"};

        String weekTime = weekTime(cron);

        Date chengeDate = StringChengeDate(weekTime);

        // 获取当前日期
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDateTime tomorrowMidnight = LocalDateTime.of(tomorrow, LocalTime.MIDNIGHT);
        Date dates = Date.from(tomorrowMidnight.atZone(ZoneId.systemDefault()).toInstant());
        if (new Date().getTime() < chengeDate.getTime() && chengeDate.getTime()< dates.getTime()){
            value = 0;
        }

        String time = weekDays[value];

        Map<String, String > map = new HashMap<>();
        map.put("cron",time);
        map.put("time",date);
        map.put("weekTime",weekTime);
        return map;
    }

    /**
     * 字符串转换成时间
     * @param time 时间字符串
     * @return 时间
     */
    public static Date StringChengeDate(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date targetTime;
        try {
            targetTime = sdf.parse(time);
        } catch (ParseException e) {
            throw new ApplicationException("时间转换失败，不是yyyy-MM-dd HH:mm:ss格式:"+time);
        }
        return targetTime;
    }

    /**
     * 周几转换成具体日期
     * @param cron 表达式
     * @return 日期
     */
    public static String weekTime(String cron){
        String[] s = cron.split(" ");
        // 0 27 16 1 4 ? 2023
        return s[6] + "-" + s[4]+ "-" + s[3] +" "+ s[2] + ":"+ s[1] +":00";
    }


}
