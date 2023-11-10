package io.tiklab.teston.common;

import org.springframework.stereotype.Service;

@Service
public class MagicValue {

    //步骤断言的类型：变量，元素
    public String ASSERT_TYPE_VARIABLE = "variable";
    public String ASSERT_TYPE_ELEMENT = "element";

    //值断言的比较
    public Integer EQUAL = 1;
    public Integer NOT_EQUAL = 2;
    public Integer LESS_THAN = 3;
    public Integer LESS_THAN_OR_EQUAL = 4;
    public Integer GREATER_THAN = 5;
    public Integer GREATER_THAN_OR_EQUAL = 6;

    //元素断言 类型
    //期望值
    public Integer EXPECT = 1;
    //元素存在
    public Integer ELEMENT_EXIST = 2;
    //元素不存在
    public Integer ELEMENT_NOT_EXIST = 3;

}
