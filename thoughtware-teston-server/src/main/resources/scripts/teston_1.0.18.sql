ALTER TABLE teston_api_assert
DROP COLUMN comparator;

ALTER TABLE teston_api_assert
ADD COLUMN comparator int;

ALTER TABLE teston_api_assert_instance
DROP COLUMN comparator;

ALTER TABLE teston_api_assert_instance
ADD COLUMN comparator int;

ALTER TABLE teston_web_scene_instance
ADD COLUMN status varchar(32);

UPDATE teston_web_scene_instance
SET status = teston_instance.status
FROM teston_instance
WHERE teston_web_scene_instance.id = teston_instance.id;

ALTER TABLE teston_app_scene_instance
ADD COLUMN status varchar(32);

UPDATE teston_app_scene_instance
SET status = teston_instance.status
FROM teston_instance
WHERE teston_app_scene_instance.id = teston_instance.id;