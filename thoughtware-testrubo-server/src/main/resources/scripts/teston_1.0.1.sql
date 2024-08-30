ALTER TABLE teston_api_response_body
ADD COLUMN http_Code INT,
ADD COLUMN create_time TIMESTAMP,
ADD COLUMN name VARCHAR(64),
ADD COLUMN data_type VARCHAR(32),
ADD COLUMN json_text TEXT,
ADD COLUMN raw_text TEXT;

ALTER TABLE teston_api_response_body
DROP COLUMN result_type;