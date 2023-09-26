CREATE TABLE teston_variable (
    id VARCHAR(32) PRIMARY KEY,
    belong_id VARCHAR(32),
    name VARCHAR(32),
    value VARCHAR(256),
    create_time TIMESTAMP,
    type VARCHAR(32),
    description VARCHAR(512)
);

DROP TABLE teston_api_json;

CREATE TABLE teston_api_json (
  id VARCHAR(32) PRIMARY KEY,
  api_unit_id VARCHAR(32),
  schema_text VARCHAR(2048)
);


ALTER TABLE teston_web_scene_step
ADD COLUMN pre_script text,
ADD COLUMN after_script text;

ALTER TABLE teston_app_scene_step
ADD COLUMN pre_script text,
ADD COLUMN after_script text;

CREATE TABLE teston_api_perf_testdata (
  id VARCHAR(32) PRIMARY KEY,
  name VARCHAR(64),
  case_id VARCHAR(32),
  testData text,
  create_time TIMESTAMP,
  type VARCHAR(8)
);

