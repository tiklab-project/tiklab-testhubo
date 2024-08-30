ALTER TABLE teston_test_plan_instance
DROP COLUMN execute_number;

ALTER TABLE teston_test_plan_instance
ADD COLUMN status INT DEFAULT 0;


ALTER TABLE teston_instance
ADD COLUMN status VARCHAR(8);


ALTER TABLE teston_api_perf_instance
DROP COLUMN result;

ALTER TABLE teston_api_perf_instance
DROP COLUMN execute_number;


ALTER TABLE teston_web_scene_instance
DROP COLUMN result;

ALTER TABLE teston_web_scene_instance
DROP COLUMN execute_number;


ALTER TABLE teston_app_scene_instance
DROP COLUMN result;

ALTER TABLE teston_app_scene_instance
DROP COLUMN execute_number;