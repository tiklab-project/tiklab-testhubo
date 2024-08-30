CREATE TABLE teston_assert_step_common (
    id VARCHAR(32) PRIMARY KEY,
    step_id VARCHAR(32),
    type VARCHAR(12),
    create_time TIMESTAMP
);

CREATE TABLE teston_assert_variable (
    id VARCHAR(32),
    assert_id VARCHAR(32),
    variable VARCHAR(128),
    compare int,
    expect VARCHAR(128)
);

CREATE TABLE teston_assert_element (
    id VARCHAR(32),
    assert_id VARCHAR(32),
    location VARCHAR(32),
    location_value VARCHAR(128),
    element_type int,
    expect VARCHAR(128)
);
