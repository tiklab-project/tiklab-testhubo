-- 添加新列
ALTER TABLE teston_api_perf_step ADD COLUMN thread_count int;
ALTER TABLE teston_api_perf_step ADD COLUMN execute_type int;
ALTER TABLE teston_api_perf_step ADD COLUMN execute_count int;
ALTER TABLE teston_api_perf_step ADD COLUMN case_type VARCHAR(32);
ALTER TABLE teston_api_perf_step ADD COLUMN time_type VARCHAR(12);
ALTER TABLE teston_api_perf_step ADD COLUMN time_count int;
ALTER TABLE teston_api_perf_step RENAME COLUMN api_scene_id TO case_id;

-- 将新列的所有值设置为默认
UPDATE teston_api_perf_step SET thread_count = 1;
UPDATE teston_api_perf_step SET execute_type = 1;
UPDATE teston_api_perf_step SET execute_count = 1;
UPDATE teston_api_perf_step SET case_type = 'api-scene';

-- 删除列
ALTER TABLE teston_api_perfcase DROP COLUMN thread_count;
ALTER TABLE teston_api_perfcase DROP COLUMN execute_type;
ALTER TABLE teston_api_perfcase DROP COLUMN execute_count;


ALTER TABLE teston_api_perf_testdata RENAME COLUMN case_id TO step_id;

-- 性能测试汇总的信息
CREATE TABLE teston_api_perf_step_instance (
    id VARCHAR(32) PRIMARY KEY,
    api_perf_instance_id VARCHAR(255),
    total_requests INT,
    successful_requests INT,
    failed_requests INT,
    avg_elapsed_time DOUBLE PRECISION,
    max_elapsed_time DOUBLE PRECISION,
    min_elapsed_time DOUBLE PRECISION,
    total_elapsed_time DOUBLE PRECISION,
    tps DOUBLE PRECISION,
    error_rate DOUBLE PRECISION,
    percentile90 DOUBLE PRECISION,
    percentile95 DOUBLE PRECISION,
    percentile99 DOUBLE PRECISION
);

-- 性能测试下的单个接口的信息
CREATE TABLE teston_api_perf_step_unit_instance(
    id VARCHAR(32) PRIMARY KEY,
    api_perf_instance_id VARCHAR(32),
    name VARCHAR(256),
    total_requests INT,
    successful_requests INT,
    failed_requests INT,
    avg_elapsed_time DOUBLE PRECISION,
    max_elapsed_time DOUBLE PRECISION,
    min_elapsed_time DOUBLE PRECISION,
    total_elapsed_time DOUBLE PRECISION,
    tps DOUBLE PRECISION,
    error_rate DOUBLE PRECISION,
    percentile90 DOUBLE PRECISION,
    percentile95 DOUBLE PRECISION,
    percentile99 DOUBLE PRECISION
);