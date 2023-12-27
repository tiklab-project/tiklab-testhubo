ALTER TABLE teston_api_assert_instance
ADD COLUMN actual_result VARCHAR(256);

ALTER TABLE teston_api_assert_instance
DROP COLUMN property_name;

ALTER TABLE teston_api_assert_instance
DROP COLUMN data_type;