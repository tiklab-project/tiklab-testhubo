-- ---------------------------
-- 仓库表
-- ----------------------------
CREATE TABLE teston_repository(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        description VARCHAR(64),
        visibility int,
        icon_url VARCHAR(128),
        user_id VARCHAR(32)
);

-- ---------------------------
-- 仓库最近表
-- ----------------------------
CREATE TABLE teston_repository_recent(
    id VARCHAR(32) PRIMARY KEY,
    repository_id VARCHAR(32) NOT NULL,
    user_id VARCHAR(32),
    update_time TIMESTAMP
);

-- ---------------------------
-- 仓库关注表
-- ----------------------------
CREATE TABLE teston_repository_follow(
        id VARCHAR(32) PRIMARY KEY,
        repository_id VARCHAR(32) NOT NULL,
        user_id VARCHAR(32),
        create_time TIMESTAMP
);

-- ---------------------------
-- agent配置表
-- ----------------------------
CREATE TABLE teston_agent_config(
        id VARCHAR(32) PRIMARY KEY,
        repository_id VARCHAR(32),
        name VARCHAR(64),
        url VARCHAR(256),
        status int,
        number int,
        create_time TIMESTAMP
);

-- ---------------------------
-- 模块表
-- ----------------------------
CREATE TABLE teston_category(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        repository_id VARCHAR(32),
        parent_category_id VARCHAR(32),
        sort int,
        description VARCHAR(64)
);

-- ---------------------------
-- 用例表
-- ----------------------------
CREATE TABLE teston_testcase(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        category_id varchar (32),
        repository_id varchar(32),
        test_type VARCHAR (32),
        case_type varchar(32),
        create_user VARCHAR (32),
        update_user VARCHAR (32),
        create_time timestamp,
        update_time timestamp,
        description VARCHAR(64),
        workItem_id VARCHAR(32),
        sort int
);

-- ---------------------------
-- api 单元用例表
-- ----------------------------
CREATE TABLE teston_api_unit(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32),
        path VARCHAR(256) NOT NULL,
        method_type VARCHAR (64)
);

-- ---------------------------
-- api 单元用例
-- 请求头表
-- ----------------------------
CREATE TABLE teston_api_request_header(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

-- ---------------------------
-- api 单元用例
-- query参数表
-- ----------------------------
CREATE TABLE teston_api_query(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
--         data_type VARCHAR(32) NOT NULL,
--         required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

-- ---------------------------
-- api 单元用例
-- 请求体类型表
-- ----------------------------
CREATE TABLE teston_api_request_body(
        id VARCHAR(32),
        api_unit_id VARCHAR(32),
        body_type VARCHAR(32)
);

-- ---------------------------
-- api 单元用例
-- form-data表
-- ----------------------------
CREATE TABLE teston_api_form(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
--         required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

-- ---------------------------
-- api 单元用例
-- form-url表
-- ----------------------------
CREATE TABLE teston_api_form_urlencoded(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
--         required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

-- ---------------------------
-- api 单元用例
-- json表
-- ----------------------------
CREATE TABLE teston_api_json(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int,
        parent_id VARCHAR(32)
);

-- ---------------------------
-- api 单元用例
-- raw表
-- ----------------------------
CREATE TABLE teston_api_raw(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        raw VARCHAR(2048) NOT NULL,
        type VARCHAR(32) NOT NULL
);

-- ---------------------------
-- api 单元用例
-- 响应头表
-- ----------------------------
CREATE TABLE teston_api_response_header(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

-- ---------------------------
-- api 单元用例
-- 响应体类型表
-- ----------------------------
CREATE TABLE teston_api_response_body(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32),
        result_type VARCHAR(32)
);

-- ---------------------------
-- api 单元用例
-- 响应json表
-- ----------------------------
CREATE TABLE teston_api_response_json(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        property_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int,
        parent_id VARCHAR(32)
);

-- ---------------------------
-- api 单元用例
-- 响应raw表
-- ----------------------------
CREATE TABLE teston_api_response_raw(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id  VARCHAR(32) NOT NULL,
        raw VARCHAR(2048) NOT NULL,
        type VARCHAR(32) NOT NULL
);

-- ---------------------------
-- api 单元用例
-- 前置脚本
-- ----------------------------
CREATE TABLE teston_api_after_script(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        script VARCHAR(2048)
);

-- ---------------------------
-- api 单元用例
-- 后置脚本
-- ----------------------------
CREATE TABLE teston_api_pre_script(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        script VARCHAR(2048)
);

-- ---------------------------
-- api 单元用例
-- 断言
-- ----------------------------
CREATE TABLE teston_api_assert(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        source int,
        property_name VARCHAR(64),
        data_type VARCHAR(32),
        comparator VARCHAR(32),
        value VARCHAR(128) NOT NULL,
        sort int
);

-- ---------------------------
-- api 单元用例
-- 断言实例
-- ----------------------------
CREATE TABLE teston_api_assert_instance(
        id VARCHAR(32) PRIMARY KEY,
        instance_id VARCHAR(32) NOT NULL,
        source int,
        property_name VARCHAR(64),
        data_type VARCHAR(32),
        comparator VARCHAR(32) NOT NULL,
        value VARCHAR(128),
        result int
);

-- ---------------------------
-- api 单元用例实例 中间表关联
-- ----------------------------
CREATE TABLE teston_api_unit_instance_bind(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32),
        api_unit_instance_id VARCHAR(32),
        create_time timestamp
);

-- ---------------------------
-- api 单元用例实例
-- ----------------------------
CREATE TABLE teston_api_unit_instance(
        id VARCHAR(32) PRIMARY KEY,
        status_code varchar (32),
        api_unit_id varchar (32),
        result int,
        execute_number int,
        err_message varchar (2048),
        elapsed_time double precision,
        create_user VARCHAR(32),
        create_time timestamp
);

-- ---------------------------
-- api 单元用例
-- 请求实例
-- ----------------------------
CREATE TABLE teston_api_request_instance(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_instance_id VARCHAR(32),
        request_url VARCHAR(2048),
        request_type varchar (32),
        request_header VARCHAR(2048),
        request_param VARCHAR(2048)
);

-- ---------------------------
-- api 单元用例
-- 响应实例
-- ----------------------------
CREATE TABLE teston_api_response_instance(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_instance_id VARCHAR(32),
        response_header VARCHAR(2048),
        response_body VARCHAR(2048)
);

-- ---------------------------
-- api 场景用例
-- ----------------------------
CREATE TABLE teston_api_scene(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32)
);

-- ---------------------------
-- api 场景用例
-- 场景下关联的单元用例
-- ----------------------------
CREATE TABLE teston_api_scene_step(
        id VARCHAR(32) PRIMARY KEY,
        api_scene_id VARCHAR(32),
        api_unit_id VARCHAR(32),
        create_time timestamp
);

-- ---------------------------
-- api 场景用例
-- 场景实例
-- ----------------------------
CREATE TABLE teston_api_scene_instance(
        id VARCHAR(32) PRIMARY KEY,
        api_scene_id varchar (32),
        result varchar (8),
        execute_number int,
        test_number integer,
        pass_number integer,
        fail_number integer,
        pass_rate varchar (32),
        elapsed_time integer,
        create_user VARCHAR(32),
        create_time timestamp
);

-- ---------------------------
-- api 场景用例
-- 场景实例下的步骤实例的中间关联表
-- ----------------------------
CREATE TABLE teston_api_scene_step_instance_bind(
        id VARCHAR(32) PRIMARY KEY,
        api_scene_instance_id VARCHAR(32),
        api_unit_instance_id VARCHAR(32)
);

-- ---------------------------
-- api 性能测试用例
-- ----------------------------
CREATE TABLE teston_api_perfcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32),
        thread_count integer,
        execute_type integer,
        execute_count integer
);

-- ---------------------------
-- api 性能测试用例
-- 性能测试用例下关联的场景用例
-- ----------------------------
CREATE TABLE teston_api_perf_step(
        id VARCHAR(32) PRIMARY KEY,
        api_scene_id VARCHAR(32),
        api_perf_id VARCHAR(32),
        create_time timestamp
);

-- ---------------------------
-- api 性能测试用例
-- 实例历史
-- ----------------------------
CREATE TABLE teston_api_perf_instance(
        id VARCHAR(32) PRIMARY KEY,
        api_perf_id VARCHAR(32),
        pass_rate varchar(8),
        error_rate varchar(8),
        pass_num integer,
        fail_num integer,
        total integer,
        execute_number int,
        result integer,
        create_time timestamp
);

-- ---------------------------
-- web 场景用例
-- ----------------------------
CREATE TABLE teston_web_scene(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32)
);

-- ---------------------------
-- web 场景用例
-- 场景用例下的步骤
-- ----------------------------
CREATE TABLE teston_web_scene_step(
        id VARCHAR(32) PRIMARY KEY,
        web_scene_id VARCHAR(32),
        name VARCHAR(256),
        location  VARCHAR(32),
        location_value VARCHAR(256),
        action_type varchar(256),
        step_action varchar(64),
        parameter varchar (256),
        expected_result VARCHAR (128),
        create_time timestamp,
        sort int
);

-- ---------------------------
-- web 场景用例
-- 场景实例
-- ----------------------------
CREATE TABLE teston_web_scene_instance(
        id VARCHAR(32) PRIMARY KEY,
        web_scene_id varchar (32),
        result integer,
        execute_number int,
        step_num integer,
        pass_num integer,
        fail_num integer,
        pass_rate varchar(8),
        total_duration double precision,
        create_time timestamp
);

-- ---------------------------
-- web 场景用例
-- 场景用例下的步骤实例
-- ----------------------------
CREATE TABLE teston_web_scene_instance_step(
    id VARCHAR(32) PRIMARY KEY,
    location_value VARCHAR(256),
    action_type varchar(256),
    step_action varchar(64),
    parameter varchar (256),
    result VARCHAR (128),
    web_scene_instance_id varchar (32),
    duration double precision,
    location  VARCHAR(32)
);

-- ---------------------------
-- web 性能测试用例
-- ----------------------------
CREATE TABLE teston_web_perfcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32),
        thread_count integer,
        execute_type integer,
        execute_count integer
);

-- ---------------------------
-- web 性能测试用例
-- 性能测试用例下关联的场景用例
-- ----------------------------
CREATE TABLE teston_web_perf_step(
        id VARCHAR(32) PRIMARY KEY,
        web_scene_id VARCHAR(32),
        web_perf_id VARCHAR(32),
        create_time timestamp
);

-- ---------------------------
-- web 性能测试用例
-- 实例历史
-- ----------------------------
CREATE TABLE teston_web_perf_instance(
        id VARCHAR(32) PRIMARY KEY,
        web_perf_id VARCHAR(32),
        pass_rate varchar(8),
        error_rate varchar(8),
        pass_num integer,
        fail_num integer,
        total integer,
        result integer,
        execute_number int,
        create_time timestamp
);


-- ---------------------------
-- app 场景用例
-- ----------------------------
CREATE TABLE teston_app_scene(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32)
);

-- ---------------------------
-- app 场景用例
-- 场景用例下的步骤
-- ----------------------------
CREATE TABLE teston_app_scene_step(
        id VARCHAR(32) PRIMARY KEY,
        app_scene_id VARCHAR(32),
        name VARCHAR(256),
        location  VARCHAR(32),
        location_value VARCHAR(256),
        action_type varchar(256),
        step_action varchar(64),
        parameter varchar (256),
        expected_result VARCHAR (128),
        create_time timestamp,
        sort int
);

-- ---------------------------
-- app 场景用例
-- 实例历史
-- ----------------------------
CREATE TABLE teston_app_scene_instance(
        id VARCHAR(32) PRIMARY KEY,
        app_scene_id varchar (32),
        result integer,
        execute_number int,
        step_num integer,
        pass_num integer,
        fail_num integer,
        pass_rate varchar(8),
        create_time timestamp
);

-- ---------------------------
-- app 场景用例
-- 场景用例下的步骤实例历史
-- ----------------------------
CREATE TABLE teston_app_scene_instance_step(
    id VARCHAR(32) PRIMARY KEY,
    location_value VARCHAR(256),
    action_type varchar(256),
    step_action varchar(64),
    parameter varchar (256),
    result VARCHAR (128),
    app_scene_instance_id varchar (32),
    location  VARCHAR(32)
);

-- ---------------------------
-- app 性能测试用例
-- ----------------------------
CREATE TABLE teston_app_perfcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32),
        thread_count integer,
        execute_type integer,
        execute_count integer
);

-- ---------------------------
-- app 性能测试用例
-- 性能测试用例下关联的场景用例
-- ----------------------------
CREATE TABLE teston_app_perf_step(
        id VARCHAR(32) PRIMARY KEY,
        app_scene_id VARCHAR(32),
        app_perf_id VARCHAR(32),
        create_time timestamp
);

-- ---------------------------
-- app 性能测试用例
-- 实例历史
-- ----------------------------
CREATE TABLE teston_app_perf_instance(
        id VARCHAR(32) PRIMARY KEY,
        app_perf_id VARCHAR(32),
        pass_rate varchar(8),
        error_rate varchar(8),
        pass_num integer,
        fail_num integer,
        total integer,
        result integer,
        execute_number int,
        create_time timestamp
);

-- ---------------------------
-- 功能测试用例
-- ----------------------------
CREATE TABLE teston_func_unit(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32)
);

-- ---------------------------
-- 功能测试用例
-- 功能测试用例下的步骤
-- ----------------------------
CREATE TABLE teston_func_unit_step(
        id VARCHAR(32) PRIMARY KEY,
        func_unit_id varchar(32),
        expect varchar(512),
        actual varchar(512),
        described varchar(512),
        create_time timestamp,
        update_time timestamp
);

-- ---------------------------
-- api 环境
-- ----------------------------
CREATE TABLE teston_env_api(
        id VARCHAR(32) PRIMARY KEY,
        repository_id varchar (32),
        name VARCHAR(128) ,
        pre_url VARCHAR(128)
);

-- ---------------------------
-- web 环境
-- ----------------------------
CREATE TABLE teston_env_web(
        id VARCHAR(32) PRIMARY KEY,
        repository_id varchar (32),
        name VARCHAR(128),
        web_driver VARCHAR(32)
);

-- ---------------------------
-- app 环境
-- ----------------------------
CREATE TABLE teston_env_app(
        id VARCHAR(32) PRIMARY KEY,
        repository_id VARCHAR(32),
        name VARCHAR(128),
        platform_name VARCHAR(32),
        platform_version VARCHAR(128),
        device_name VARCHAR(128),
        app_package VARCHAR(128),
        app_activity VARCHAR(128),
        appium_sever VARCHAR(128)
);

-- ---------------------------
-- web app 中的操作方法
-- ----------------------------
CREATE TABLE teston_action_type(
    id VARCHAR(32) PRIMARY KEY,
    name VARCHAR(32),
    type VARCHAR(8),
    description VARCHAR(256)
);

-- ---------------------------
-- 测试计划
-- ----------------------------
CREATE TABLE teston_test_plan(
    id VARCHAR(32) PRIMARY KEY,
    name VARCHAR(32),
    start_time timestamp,
    end_time timestamp,
    state integer,
    principals varchar (32),
    repository_id varchar (32) ,
    description VARCHAR (64),
    create_time timestamp,
    update_time timestamp,
    sort int
);

-- ---------------------------
-- 测试计划下关联的测试用例
-- ----------------------------
CREATE TABLE teston_test_plan_detail(
    id VARCHAR(32) PRIMARY KEY,
    test_plan_id varchar (32),
    test_case_id varchar (32),
    status int,
    sort int
);

-- ---------------------------
-- 测试计划实例
-- ----------------------------
CREATE TABLE teston_test_plan_instance(
        id VARCHAR(32) PRIMARY KEY,
        test_plan_id VARCHAR(32),
        repository_id varchar (32) ,
        execute_number int,
        result int,
        total int,
        pass_num int,
        fail_num int,
        pass_rate VARCHAR(8),
        error_rate VARCHAR(8),
        create_time timestamp,
        create_user VARCHAR(128)
);

-- ---------------------------
-- 测试计划下测试用例
-- 实例的中间关联表
-- ----------------------------
CREATE TABLE teston_test_plan_case_instance_bind(
        id VARCHAR(32) PRIMARY KEY,
        test_plan_instance_id VARCHAR(32),
        case_instance_id VARCHAR(32),
        name VARCHAR(128),
        test_type VARCHAR(32),
        case_type VARCHAR(32),
        result int
);

--最近访问的用例
CREATE TABLE teston_testcase_recent(
        id VARCHAR(32) PRIMARY KEY,
        repository_id VARCHAR(32),
        testcase_id VARCHAR(32),
        user_id VARCHAR (32),
        update_time timestamp
);

--绑定的空间表
CREATE TABLE teston_workspace_bind(
        id VARCHAR(32) PRIMARY KEY,
        workspace_id VARCHAR(32),
        repository_id VARCHAR(32),
        create_time TIMESTAMP
);

-- 配置postin 服务端地址
CREATE TABLE teston_integrated_url(
        id VARCHAR(32) PRIMARY KEY,
        user_id VARCHAR(32),
        url VARCHAR(256),
        project_name VARCHAR(64),
        create_time TIMESTAMP
);


-- 绑定的缺陷
CREATE TABLE teston_workitem_bind(
        id VARCHAR(32) PRIMARY KEY,
        workitem_id VARCHAR(32),
        case_id VARCHAR(32),
        create_time TIMESTAMP
);











