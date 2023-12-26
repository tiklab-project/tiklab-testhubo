package io.thoughtware.teston.common;

public class MagicValue {

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

    //类型
    public static final String CASE_TYPE_API_UNIT = "api-unit";
    public static final String CASE_TYPE_API_SCENE = "api-scene";
    public static final String CASE_TYPE_API_PERFORM = "api-perform";
    public static final String CASE_TYPE_APP = "app-scene";
    public static final String CASE_TYPE_WEB = "web-scene";
    public static final String CASE_TYPE_FUNCTION = "function";
    public static final String CASE_TYPE_IF = "if";
    public static final String TEST_PLAN = "test-plan";



}
