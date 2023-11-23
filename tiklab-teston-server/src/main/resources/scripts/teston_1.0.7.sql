CREATE TABLE teston_case_step_common (
    id VARCHAR(32) PRIMARY KEY,
    case_id VARCHAR(32),
    create_time timestamp,
    type VARCHAR(32),
    sort int
);

CREATE TABLE teston_case_step_if (
    id VARCHAR(32) PRIMARY KEY,
    case_id VARCHAR(32),
    relation VARCHAR(8)
);

CREATE TABLE teston_case_step_if_variable (
    id VARCHAR(32) PRIMARY KEY,
    step_id VARCHAR(32),
    variable VARCHAR(128),
    compare int,
    expect VARCHAR(128)
);

INSERT INTO teston_case_step_common (id,case_id,sort, create_time)
SELECT id,web_scene_id,sort, create_time
FROM teston_web_scene_step;

ALTER TABLE teston_web_scene_step
DROP COLUMN sort,
DROP COLUMN create_time;

INSERT INTO teston_case_step_common (id,case_id,sort, create_time)
SELECT id,app_scene_id,sort, create_time
FROM teston_app_scene_step;

ALTER TABLE teston_app_scene_step
DROP COLUMN sort,
DROP COLUMN create_time;

INSERT INTO teston_case_step_common (id,case_id,sort, create_time)
SELECT id,func_unit_id,sort, create_time
FROM teston_func_unit_step;

ALTER TABLE teston_func_unit_step
DROP COLUMN create_time,
DROP COLUMN sort,
DROP COLUMN update_time;

INSERT INTO teston_case_step_common (id,case_id,sort, create_time)
SELECT id,api_scene_id,sort, create_time
FROM teston_api_scene_step;

ALTER TABLE teston_api_scene_step
DROP COLUMN create_time,
DROP COLUMN sort;


CREATE TABLE teston_case_step_instance_common (
    id VARCHAR(32) PRIMARY KEY,
    instance_id VARCHAR(32),
    sort int,
    type VARCHAR(32),
    result int
);

CREATE TABLE teston_case_step_instance_if (
    id VARCHAR(32) PRIMARY KEY,
    step_instance_id VARCHAR(32),
    relation VARCHAR(12)
);

CREATE TABLE teston_case_step_instance_if_variable (
    id VARCHAR(32) PRIMARY KEY,
    step_instance_id VARCHAR(32),
    variable VARCHAR(128),
    compare int,
    expect VARCHAR(128),
    result int
);

ALTER TABLE teston_web_scene_instance_step
ADD COLUMN name VARCHAR(256);

ALTER TABLE teston_web_scene_instance_step
DROP COLUMN result;


ALTER TABLE teston_app_scene_instance_step
ADD COLUMN name VARCHAR(256);

ALTER TABLE teston_app_scene_instance_step
DROP COLUMN result;


UPDATE teston_case_step_common
SET type = 'api-scene'
FROM teston_api_scene_step
WHERE teston_case_step_common.id = teston_api_scene_step.id;

UPDATE teston_case_step_common
SET type = 'web'
FROM teston_web_scene_step
WHERE teston_case_step_common.id = teston_web_scene_step.id;

UPDATE teston_case_step_common
SET type = 'app'
FROM teston_app_scene_step
WHERE teston_case_step_common.id = teston_app_scene_step.id;

UPDATE teston_case_step_common
SET type = 'function'
FROM teston_func_unit_step
WHERE teston_case_step_common.id = teston_func_unit_step.id;

UPDATE teston_case_step_common
SET type = 'if'
FROM teston_case_step_if
WHERE teston_case_step_common.id = teston_case_step_if.id;




