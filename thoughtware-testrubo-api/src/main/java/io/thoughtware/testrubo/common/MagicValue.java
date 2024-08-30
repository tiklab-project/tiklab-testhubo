package io.thoughtware.testrubo.common;

public class MagicValue {



    // api method
    public static final String API_METHOD_TYPE_GET = "get";
    public static final String API_METHOD_TYPE_POST = "post";
//    public static final String API_METHOD_TYPE_PUT = "PUT";
//    public static final String API_METHOD_TYPE_DELETE = "DELETE";


    //步骤断言的类型：变量，元素
    public static final String ASSERT_TYPE_VARIABLE = "variable";
    public static final String ASSERT_TYPE_ELEMENT = "element";

    //值断言的比较
    public static final int EQUAL = 1;
    public static final int NOT_EQUAL = 2;
    public static final int LESS_THAN = 3;
    public static final int LESS_THAN_OR_EQUAL = 4;
    public static final int GREATER_THAN = 5;
    public static final int GREATER_THAN_OR_EQUAL = 6;

    //元素断言 类型
    //期望值
    public static final int EXPECT = 1;
    //元素存在
    public static final int ELEMENT_EXIST = 2;
    //元素不存在
    public static final int ELEMENT_NOT_EXIST = 3;

    //用例类型
    public static final String CASE_TYPE_API_UNIT = "api-unit";
    public static final String CASE_TYPE_API_SCENE = "api-scene";
    public static final String CASE_TYPE_API_PERFORM = "api-perform";
    public static final String CASE_TYPE_APP = "app-scene";
    public static final String CASE_TYPE_WEB = "web-scene";
    public static final String CASE_TYPE_FUNCTION = "function";
    public static final String CASE_TYPE_IF = "if";
    public static final String TEST_PLAN = "test-plan";

    public static final String TEST_API_PERFORM_STOP = "api-perform-stop";

    //测试计划类型
    // 功能
    public static final String TEST_PLAN_TYPE_FUNCTION = "function";
    // 自动化
    public static final String TEST_PLAN_TYPE_AUTO = "auto";

    //测试类型
    //接口 单元/场景
    public static final String TEST_TYPE_API = "api";
    //功能
    public static final String TEST_TYPE_FUNCTION = "function";
    //ui  web/app
    public static final String TEST_TYPE_UI = "ui";
    //性能
    public static final String TEST_TYPE_PERFORM = "perform";


    //测试步骤中的if判断关系，并且
    public static final String IF_RELATION_AND = "and";
    //测试步骤中的if判断关系，或者
    public static final String IF_RELATION_OR = "or";

    //app 测试平台
    public static final String PLATFORM_ANDROID = "Android";
    public static final String PLATFORM_IOS= "Ios";


    //测试状态
    //开始执行
    public static final String TEST_STATUS_START = "start";
    //执行成功
    public static final String TEST_STATUS_SUCCESS= "success";
    //执行失败
    public static final String TEST_STATUS_FAIL = "fail";
    //执行完成
    public static final String TEST_STATUS_COMPLETE = "complete";


    public static final String AGENT_DEFAULT = "agent-default_localhost";
}
