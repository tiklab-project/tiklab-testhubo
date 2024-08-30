CREATE TABLE teston_plan_quartz(
      id VARCHAR(32) PRIMARY KEY,
      test_plan_id VARCHAR(32),
      exe_type int,
      create_time timestamp,
      state int
);

CREATE TABLE teston_plan_quartz_time(
      id VARCHAR(32) PRIMARY KEY,
      quartz_plan_id VARCHAR(32),
      cron VARCHAR(128),
      exe_type int,
      time VARCHAR(32),
      week int
);

ALTER TABLE teston_test_plan
ADD COLUMN api_env VARCHAR(256);