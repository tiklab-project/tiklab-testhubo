CREATE TABLE teston_statistic_case_trend (
    id VARCHAR(32) PRIMARY KEY,
    repository_id VARCHAR(32),
    record_time VARCHAR(32),
    not_started INT,
    in_progress INT,
    completed INT
);
