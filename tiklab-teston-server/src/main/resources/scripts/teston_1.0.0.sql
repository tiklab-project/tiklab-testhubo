CREATE TABLE teston_repository(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        description VARCHAR(64),
        visibility int,
        icon_url VARCHAR(128),
        user_id VARCHAR(32)
);
CREATE TABLE teston_repository_recent(
        id VARCHAR(32) PRIMARY KEY,
        repository_id VARCHAR(32) NOT NULL,
        user_id VARCHAR(32),
        update_time TIMESTAMP
);

CREATE TABLE teston_repository_follow(
        id VARCHAR(32) PRIMARY KEY,
        repository_id VARCHAR(32) NOT NULL,
        user_id VARCHAR(32),
        create_time TIMESTAMP
);

CREATE TABLE teston_agent_config(
        id VARCHAR(32) PRIMARY KEY,
        repository_id VARCHAR(32),
        name VARCHAR(64),
        url VARCHAR(256),
        status int,
        number int,
        create_time TIMESTAMP
);

CREATE TABLE teston_category(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        repository_id VARCHAR(32),
        parent_category_id VARCHAR(32),
        sort int,
        description VARCHAR(64)
);

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
        sort int
);

CREATE TABLE teston_api_unit(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32),
        path VARCHAR(256) NOT NULL,
        method_type VARCHAR (64)
);

CREATE TABLE teston_api_request_header(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

CREATE TABLE teston_api_query(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

CREATE TABLE teston_api_request_body(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32),
        body_type VARCHAR(32)
);

CREATE TABLE teston_api_form(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

CREATE TABLE teston_api_form_urlencoded(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

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

CREATE TABLE teston_api_raw(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        raw VARCHAR(2048) NOT NULL,
        type VARCHAR(32) NOT NULL
);

CREATE TABLE teston_api_response_header(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

CREATE TABLE teston_api_response_body(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32),
        result_type VARCHAR(32)
);

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

CREATE TABLE teston_api_response_raw(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id  VARCHAR(32) NOT NULL,
        raw VARCHAR(2048) NOT NULL,
        type VARCHAR(32) NOT NULL
);

CREATE TABLE teston_api_after_script(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        script VARCHAR(2048)
);

CREATE TABLE teston_api_pre_script(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32) NOT NULL,
        script VARCHAR(2048)
);

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

CREATE TABLE teston_api_unit_instance_bind(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32),
        api_unit_instance_id VARCHAR(32),
        create_time timestamp
);

CREATE TABLE teston_api_unit_instance(
        id VARCHAR(32) PRIMARY KEY,
        status_code varchar (32),
        api_unit_id varchar (32),
        result int,
        execute_number int,
        err_message varchar (2048),
        elapsed_time double,
        create_user VARCHAR(32),
        create_time timestamp
);

CREATE TABLE teston_api_request_instance(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_instance_id VARCHAR(32),
        request_url VARCHAR(2048),
        request_type varchar (32),
        request_header VARCHAR(2048),
        request_param VARCHAR(2048)
);

CREATE TABLE teston_api_response_instance(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_instance_id VARCHAR(32),
        response_header VARCHAR(2048),
        response_body VARCHAR(2048)
);


CREATE TABLE teston_api_scene(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32)
);

CREATE TABLE teston_api_scene_step(
        id VARCHAR(32) PRIMARY KEY,
        api_scene_id VARCHAR(32),
        api_unit_id VARCHAR(32),
        create_time timestamp
);


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

CREATE TABLE teston_api_scene_step_instance_bind(
        id VARCHAR(32) PRIMARY KEY,
        api_scene_instance_id VARCHAR(32),
        api_unit_instance_id VARCHAR(32)
);

CREATE TABLE teston_api_perfcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32),
        thread_count integer,
        execute_type integer,
        execute_count integer
);

CREATE TABLE teston_api_perf_step(
        id VARCHAR(32) PRIMARY KEY,
        api_scene_id VARCHAR(32),
        api_perf_id VARCHAR(32),
        create_time timestamp
);

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

CREATE TABLE teston_web_scene(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32)
);

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

CREATE TABLE teston_web_scene_instance(
        id VARCHAR(32) PRIMARY KEY,
        web_scene_id varchar (32),
        result integer,
        execute_number int,
        step_num integer,
        pass_num integer,
        fail_num integer,
        pass_rate varchar(8),
        total_duration double,
        create_time timestamp
);

CREATE TABLE teston_web_scene_instance_step(
    id VARCHAR(32) PRIMARY KEY,
    location_value VARCHAR(256),
    action_type varchar(256),
    step_action varchar(64),
    parameter varchar (256),
    result VARCHAR (128),
    web_scene_instance_id varchar (32),
    duration double,
    location  VARCHAR(32)
);


CREATE TABLE teston_web_perfcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32),
        thread_count integer,
        execute_type integer,
        execute_count integer
);


CREATE TABLE teston_web_perf_step(
        id VARCHAR(32) PRIMARY KEY,
        web_scene_id VARCHAR(32),
        web_perf_id VARCHAR(32),
        create_time timestamp
);

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


CREATE TABLE teston_app_scene(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32)
);

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




CREATE TABLE teston_app_perfcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32),
        thread_count integer,
        execute_type integer,
        execute_count integer
);

CREATE TABLE teston_app_perf_step(
        id VARCHAR(32) PRIMARY KEY,
        app_scene_id VARCHAR(32),
        app_perf_id VARCHAR(32),
        create_time timestamp
);

CREATE TABLE teston_app_perf_instance(
        id VARCHAR(32) PRIMARY KEY,
        app_perf_id VARCHAR(32),
        pass_rate varchar(8),
        error_rate varchar(8),
        pass_num integer,
        fail_num integer,
        total integer,
        execute_number int,
        create_time timestamp
);


CREATE TABLE teston_func_unit(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32)
);

CREATE TABLE teston_func_unit_step(
        id VARCHAR(32) PRIMARY KEY,
        func_unit_id varchar(32),
        expect varchar(512),
        actual varchar(512),
        described varchar(512),
        create_time timestamp,
        update_time timestamp
);


CREATE TABLE teston_env_api(
        id VARCHAR(32) PRIMARY KEY,
        repository_id varchar (32),
        name VARCHAR(128) ,
        pre_url VARCHAR(128)
);

CREATE TABLE teston_env_web(
        id VARCHAR(32) PRIMARY KEY,
        repository_id varchar (32),
        name VARCHAR(128),
        web_driver VARCHAR(32)
);

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


CREATE TABLE teston_quartz_master(
    id VARCHAR(32) PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    task_Class_url VARCHAR(32),
    description varchar (32),
    cycle_index int,
    environment_id varchar (32),
    repository_id varchar (32),
    type int not null,
    quartz_type varchar (12),
    period varchar (32),
    weeks varchar (128),
    state int,
    sort int,
    create_time timestamp,
    update_time timestamp
);

CREATE TABLE teston_quartz_task(
    id VARCHAR(32) PRIMARY KEY,
    cron_expression varchar (32),
    end_Time timestamp,
    execution_time varchar(32),
    quartz_master_id varchar (32)

);

CREATE TABLE teston_quartz_testcase(
    id VARCHAR(32) PRIMARY KEY,
    testcase_id varchar (32),
    quartz_master_id varchar (32)
);

CREATE TABLE teston_quartz_testcase(
    id VARCHAR(32) PRIMARY KEY,
    testcase_id varchar (32),
    quartz_master_id varchar (32)
);

CREATE TABLE teston_action_type(
    id VARCHAR(32) PRIMARY KEY,
    name VARCHAR(32),
    type VARCHAR(8),
    description VARCHAR (64)
);

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
CREATE TABLE teston_test_plan_detail(
    id VARCHAR(32) PRIMARY KEY,
    test_plan_id varchar (32),
    test_case_id varchar (32),
    status int,
    sort int
);

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


CREATE TABLE teston_test_plan_case_instance_bind(
        id VARCHAR(32) PRIMARY KEY,
        test_plan_instance_id VARCHAR(32),
        case_instance_id VARCHAR(32),
        name VARCHAR(128),
        test_type VARCHAR(32),
        case_type VARCHAR(32),
        result int
);
















