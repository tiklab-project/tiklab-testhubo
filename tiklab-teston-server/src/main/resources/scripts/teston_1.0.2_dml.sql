
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


-- 添加字段 用例关联teamWire需求
ALTER TABLE teston_testcase ADD workItem_id VARCHAR(32);


