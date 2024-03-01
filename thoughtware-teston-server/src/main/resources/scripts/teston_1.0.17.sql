ALTER TABLE teston_test_plan
ADD COLUMN app_env_id VARCHAR(32);

ALTER TABLE teston_test_plan
DROP COLUMN api_env;

ALTER TABLE teston_test_plan
ADD COLUMN api_env_id VARCHAR(32);