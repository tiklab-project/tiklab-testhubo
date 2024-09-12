ALTER TABLE teston_integrated_url
ADD COLUMN repository_id VARCHAR(32);

ALTER TABLE teston_integrated_url
DROP COLUMN user_id;
