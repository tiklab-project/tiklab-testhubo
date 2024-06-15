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