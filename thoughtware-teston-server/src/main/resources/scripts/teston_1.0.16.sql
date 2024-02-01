ALTER TABLE teston_test_plan_instance
DROP COLUMN execute_number;

ALTER TABLE teston_test_plan_instance
ADD COLUMN status INT DEFAULT 0;
