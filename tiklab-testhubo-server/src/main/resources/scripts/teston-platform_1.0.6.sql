UPDATE pcs_mec_message_notice
SET message_send_type_id = 'email,site'
WHERE id = 'MESSAGE_NOTICE_ID'
AND message_type_id = 'RP_MS_TYPE'
AND type = 1
AND bgroup = 'teston';