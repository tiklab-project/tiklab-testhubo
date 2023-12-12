DELETE FROM pcs_op_log;
DELETE FROM pcs_op_log_type;

INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('CREATE_REPOSITORY_TYPE', '新增了仓库', 'teston');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('DELETE_REPOSITORY_TYPE', '删除了仓库', 'teston');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('UPDATE_REPOSITORY_TYPE', '更新了仓库', 'teston');


