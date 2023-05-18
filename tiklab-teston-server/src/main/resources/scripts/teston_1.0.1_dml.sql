

CREATE TABLE teston_testcase_recent(
        id VARCHAR(32) PRIMARY KEY,
        repository_id VARCHAR(32),
        testcase_id VARCHAR(32),
        user_id VARCHAR (32),
        update_time timestamp
);
