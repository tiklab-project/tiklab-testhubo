UPDATE teston_assert_step_common
SET type = 'web-scene'
WHERE type = 'web';

UPDATE teston_assert_step_common
SET type = 'app-scene'
WHERE type = 'app';

UPDATE teston_test_plan_detail
SET id = floor(random() * 10000 + 1)::int;

CREATE TABLE teston_instance(
      id VARCHAR(32) PRIMARY KEY,
      repository_id VARCHAR(32),
      belong_id VARCHAR(32),
      type VARCHAR(64) NOT NULL,
      name VARCHAR(256),
      create_time timestamp,
      create_user varchar(32),
      content TEXT
);

delete from teston_api_unit_instance;
delete from teston_api_scene_instance;
delete from teston_api_perf_instance;
delete from teston_app_scene_instance;
delete from teston_web_scene_instance;
delete from teston_test_plan_instance;
delete from teston_test_plan_case_instance_bind;


