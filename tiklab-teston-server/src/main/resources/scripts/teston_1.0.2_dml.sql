
CREATE TABLE teston_workspace_bind(
        id VARCHAR(32) PRIMARY KEY,
        workspace_id VARCHAR(32),
        repository_id VARCHAR(32),
        create_time TIMESTAMP
);

CREATE TABLE teston_postin_url(
        id VARCHAR(32) PRIMARY KEY,
        user_id VARCHAR(32),
        url VARCHAR(256),
        status int,
        create_time TIMESTAMP
);