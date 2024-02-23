-- 更新pcs_prc_function表中的特定记录
UPDATE pcs_prc_function
SET code = 'repositoryDelete'
WHERE id = '03e4b44977b';

-- 更新pcs_prc_role_function表中的特定记录
UPDATE pcs_prc_role_function
SET role_id = 'custom3'
WHERE id = 'c31825ad3cdd' AND function_id = '03e4b44977b';


