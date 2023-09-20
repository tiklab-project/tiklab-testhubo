CREATE TABLE teston_variable (
    id VARCHAR(32) PRIMARY KEY,
    case_id VARCHAR(32),
    name VARCHAR(32),
    value VARCHAR(256),
    create_time TIMESTAMP,
    type VARCHAR(32),
    description VARCHAR(512)
);

DROP TABLE teston_api_json;

CREATE TABLE teston_api_json (
  id VARCHAR(32) PRIMARY KEY,
  apiUnitId VARCHAR(32),
  schema_text VARCHAR(2048)
);

