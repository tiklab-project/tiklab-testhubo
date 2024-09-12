-- 更改status列的数据类型为VARCHAR(32)
ALTER TABLE teston_agent_config ALTER COLUMN status TYPE VARCHAR(32);

-- 删除repository_id列
ALTER TABLE teston_agent_config DROP COLUMN repository_id;

-- 重命名url列为address
ALTER TABLE teston_agent_config RENAME COLUMN url TO address;

-- 删除number列
ALTER TABLE teston_agent_config DROP COLUMN number;

-- 重命名create_time列为update_time
ALTER TABLE teston_agent_config RENAME COLUMN create_time TO update_time;

-- 添加新列 是否启用 0不启用，1启用
ALTER TABLE teston_agent_config ADD COLUMN enable int;
-- 将新列的所有值设置为 1
UPDATE teston_agent_config SET enable = 1;


-- 添加新列type： function功能，auto自动化
ALTER TABLE teston_test_plan ADD COLUMN type VARCHAR(32);

-- 将新列的所有值设置为 'function'
UPDATE teston_test_plan SET type = 'function';