INSERT INTO  testrubo_repository  ( id ,  name ,  description ,  visibility ,  icon_url ,  user_id ) VALUES ('bd26c6ec5c6e', '默认仓库', NULL, 1, '/images/pi1.png', '111111');

INSERT INTO  testrubo_category  VALUES ('3f3dbc6d8eb6', '默认分组', 'bd26c6ec5c6e', NULL, NULL, NULL);

-- INSERT INTO  testrubo_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('133e6ceab301f0803332e9e7b4ae884e', 'APP性能', 'bd26c6ec5c6e', '3f3dbc6d8eb6', 'perform', 'app-perform', '111111', '111111', '2022-07-22 13:13:23', '2023-02-15 03:24:56', NULL, 0);
INSERT INTO  testrubo_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('1614d5d7027b', '接口登录', 'bd26c6ec5c6e', '3f3dbc6d8eb6', 'api', 'api-unit', '111111', '111111', '2022-07-21 00:57:44', '2023-02-15 03:25:21', NULL, NULL);
INSERT INTO  testrubo_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('78846d53e298', '接口性能', 'bd26c6ec5c6e', '3f3dbc6d8eb6', 'perform', 'api-perform', '111111', '111111', '2022-07-12 11:39:32', '2023-02-06 09:32:46', NULL, 0);
-- INSERT INTO  testrubo_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('84680d35804f8f217b3a801706ba2e28', 'Web性能', 'bd26c6ec5c6e', '3f3dbc6d8eb6', 'perform', 'web-perform', '111111', '111111', '2022-07-22 01:31:32', '2023-02-15 03:25:07', NULL, 0);
INSERT INTO  testrubo_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('8c6717ec9741', 'App场景', 'bd26c6ec5c6e', '3f3dbc6d8eb6', 'ui', 'app-scene', '111111', '111111', '2022-07-13 15:11:26', '2023-02-15 03:25:59', NULL, 0);
INSERT INTO  testrubo_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('aea59c50c968', '接口场景', 'bd26c6ec5c6e', '3f3dbc6d8eb6', 'api', 'api-scene', '111111', '111111', '2022-07-12 08:48:38', '2023-02-15 03:26:07', NULL, 0);
INSERT INTO  testrubo_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('b51c0d9b2c9d', 'Web场景', 'bd26c6ec5c6e', '3f3dbc6d8eb6', 'ui', 'web-scene', '111111', '111111', '2022-07-16 09:57:39', '2023-02-15 03:25:39', NULL, 0);
INSERT INTO  testrubo_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('f11a97c27b8f', '功能用例', 'bd26c6ec5c6e', '3f3dbc6d8eb6', 'function', 'function', '111111', '111111', '2022-07-14 15:05:04', '2023-02-15 03:25:51', NULL, 0);

INSERT INTO  testrubo_env_api  VALUES ('8b0829b48e51', 'bd26c6ec5c6e', 'dev', 'http://localhost:8080');

INSERT INTO  testrubo_api_unit  ( id ,  testcase_id ,  path ,  method_type ) VALUES ('1614d5d7027b', '1614d5d7027b', '/eam/auth/login', 'post');
INSERT INTO  testrubo_api_request_body  ( id ,  api_unit_id ,  body_type ) VALUES ('1614d5d7027b', '1614d5d7027b', 'raw');
INSERT INTO  testrubo_api_raw   ( id ,  api_unit_id ,  raw ,  type )  VALUES ('1614d5d7027b', '1614d5d7027b', '{"account": "admin","password": "123456","userType":"1"}', 'json');

INSERT INTO  testrubo_api_scene  VALUES ('aea59c50c968', 'aea59c50c968');
INSERT INTO  testrubo_api_scene_step  VALUES ('771c6a4c212e', 'aea59c50c968', '1614d5d7027b', '2022-7-21 00:59:28');

INSERT INTO  testrubo_api_perfcase  ( id ,  testcase_id ,  thread_count ,  execute_type ,  execute_count ) VALUES ('78846d53e298', '78846d53e298', 3, 1, 2);
INSERT INTO  testrubo_api_perf_step  VALUES ('927cfaf94f5e', 'aea59c50c968', '78846d53e298', '2022-7-20 13:25:39');

INSERT INTO  testrubo_web_scene  VALUES ('b51c0d9b2c9d', 'b51c0d9b2c9d');
INSERT INTO  testrubo_web_scene_step  ( id ,  web_scene_id ,  name ,  location ,  location_value ,  action_type ,  step_action ,  parameter ,  expected_result ,  create_time ,  sort ) VALUES ('5c15cfd46217', 'b51c0d9b2c9d', '输入值', 'id', 'kw', 'inputkeys', NULL, 'selenium', NULL, '2023-02-04 15:11:53', 1);
INSERT INTO  testrubo_web_scene_step  ( id ,  web_scene_id ,  name ,  location ,  location_value ,  action_type ,  step_action ,  parameter ,  expected_result ,  create_time ,  sort ) VALUES ('dda685ac7a75', 'b51c0d9b2c9d', '打开百度', NULL, NULL, 'openurl', NULL, 'https://www.baidu.com', NULL, '2023-02-04 15:10:38', 0);
INSERT INTO  testrubo_web_scene_step  ( id ,  web_scene_id ,  name ,  location ,  location_value ,  action_type ,  step_action ,  parameter ,  expected_result ,  create_time ,  sort ) VALUES ('e311b69a2c45', 'b51c0d9b2c9d', 'test', 'id', 'su', 'click', NULL, NULL, NULL, '2023-02-04 15:30:08', NULL);

-- INSERT INTO  testrubo_web_perfcase  ( id ,  testcase_id ,  thread_count ,  execute_type ,  execute_count ) VALUES ('84680d35804f8f217b3a801706ba2e28', '84680d35804f8f217b3a801706ba2e28', 2, 1, 3);
-- INSERT INTO  testrubo_web_perf_step  ( id ,  web_scene_id ,  web_perf_id ,  create_time ) VALUES ('04b83a81543bece6293e0377bd298776', 'b51c0d9b2c9d','84680d35804f8f217b3a801706ba2e28', '2023-02-04 15:30:08');

INSERT INTO  testrubo_app_scene  ( id ,  testcase_id ) VALUES ('8c6717ec9741', '8c6717ec9741');
INSERT INTO  testrubo_app_scene_step  ( id ,  app_scene_id ,  name ,  location ,  location_value ,  action_type ,  step_action ,  parameter ,  expected_result ,  create_time ,  sort ) VALUES ('898ce2e76681', '8c6717ec9741', '等待', NULL, NULL, 'implicitlyWait', NULL, '5', NULL, '2023-02-04 17:08:22', 0);
INSERT INTO  testrubo_app_scene_step  ( id ,  app_scene_id ,  name ,  location ,  location_value ,  action_type ,  step_action ,  parameter ,  expected_result ,  create_time ,  sort ) VALUES ('bf2589b59130', '8c6717ec9741', '输入', 'xpath', '//android.widget.EditText[@content-desc=\"请输入QQ号码或手机或邮箱\"]', 'inputkeys', NULL, '550535852', NULL, '2023-02-04 17:10:49', 2);
INSERT INTO  testrubo_app_scene_step  ( id ,  app_scene_id ,  name ,  location ,  location_value ,  action_type ,  step_action ,  parameter ,  expected_result ,  create_time ,  sort ) VALUES ('f4d64919d573', '8c6717ec9741', '点击', 'id', 'com.tencent.mobileqq:id/btn_login', 'click', NULL, NULL, NULL, '2023-02-04 17:10:41', 1);

-- INSERT INTO  testrubo_app_perfcase  ( id ,  testcase_id ,  thread_count ,  execute_type ,  execute_count ) VALUES ('133e6ceab301f0803332e9e7b4ae884e', '133e6ceab301f0803332e9e7b4ae884e', 1, 1, 2);
-- INSERT INTO  testrubo_app_perf_step  ( id ,  app_scene_id ,  app_perf_id ,  create_time ) VALUES ('d8930deea51e8ee1d0d34b2e6d3ee11f', '8c6717ec9741', '133e6ceab301f0803332e9e7b4ae884e', '2022-07-22 13:13:28');

INSERT INTO  testrubo_func_unit  ( id ,  testcase_id ) VALUES ('f11a97c27b8f', 'f11a97c27b8f');
INSERT INTO  testrubo_func_unit_step  ( id ,  func_unit_id ,  expect ,  actual ,  described ,  create_time ,  update_time ) VALUES ('0ac262c53caf', 'f11a97c27b8f', '成功', '成功', '打开postin登录页面', '2023-01-16 07:11:08', '2023-02-15 09:15:50');
INSERT INTO  testrubo_func_unit_step  ( id ,  func_unit_id ,  expect ,  actual ,  described ,  create_time ,  update_time ) VALUES ('a7e296fd3edd', 'f11a97c27b8f', '成功', '成功', '输入账号密码值', '2023-02-15 09:14:28', '2023-02-15 09:16:05');
INSERT INTO  testrubo_func_unit_step  ( id ,  func_unit_id ,  expect ,  actual ,  described ,  create_time ,  update_time ) VALUES ('ddca612c477c', 'f11a97c27b8f', '成功', '成功', '点击登录', '2023-02-15 09:16:18', NULL);

INSERT INTO  testrubo_agent_config  ( id ,  name ,  address ,  status ,  update_time ,  enable ) VALUES ('agent-default_localhost', 'agent-default','localhost', 'online',  null, 1);

INSERT INTO testrubo_action_type values   ('click1234567','click','WEB','点击对象'),
                                        ('clean1234567','clean','WEB','清除输入框'),
                                        ('inputkeys123','inputkeys','WEB','输入'),
                                        ('jumpframe123','jumpframe','WEB','跳转框架'),
                                        ('isusbale1234','isenabled','WEB','判断对象是否可用'),
                                        ('isvisible123','isdisplayed','WEB','判断对象是否可见'),
                                        ('exjsob123456','exjsob','WEB','针对对象执行js脚本'),
                                        ('gettext12345','gettext','WEB','获取对象文本属性'),
                                        ('gettagname12','gettagname','WEB','获取对象标签属性'),
                                        ('getauthcode1','getauthcode','WEB','获取对象中的验证码'),
                                        ('selectbyvisi','selectbyvisibletext','WEB','通过下拉框文本进行选择'),
                                        ('selectbyvalu','selectbyvalue','WEB','通过下拉框的value进行选着'),
                                        ('selectbyinde','selectbyindex','WEB','通过下拉框的index进行选择'),
                                        ('isselect1234','isselect','WEB','判断是否被选择r'),
                                        ('openurl12345','openurl','WEB','打开url'),
                                        ('exjs12345678','exjs','WEB','执行js'),
                                        ('gotodefaultc','gotodefaultcontent','WEB','切换到默认页面'),
                                        ('gettitle1234','gettitle','WEB','获取当前页标题'),
                                        ('getWindowHan','getWindowHandle','WEB','获取窗口句柄'),
                                        ('gotoWindowHa','gotoWindowHandle','WEB','切换窗口句柄'),
                                        ('timeout12345','timeout','WEB','设置全局隐式等待时间 (s)'),
                                        ('alertaccept1','alertaccept','WEB','弹出框点确认'),
                                        ('alertcancel1','alertcancel','WEB','弹出框点取消'),
                                        ('alertgettext','alertgettext','WEB','获取弹出框text'),
                                        ('mouselkclick','mouselkclick','WEB','模拟鼠标左键点击'),
                                        ('mouserkclick','mouserkclick','WEB','模拟鼠标右键点击'),
                                        ('mousedclick1','mousedclick','WEB','模拟鼠标双击'),
                                        ('mouseclkhold','mouselkclickhold','WEB','模拟鼠标左键点击后不释放'),
                                        ('mousedrag123','mousedrag','WEB','模拟鼠标拖拽'),
                                        ('elementisexi','elementisexist','WEB','判断元素是否存在'),
                                        ('mouseto12345','mouseto','WEB','模拟鼠标移到指定坐标'),
                                        ('mouserelease','mouserelease','WEB','模拟鼠标释放'),
                                        ('keyboartab12','keyboartab','WEB','模拟键盘tab键'),
                                        ('keyboarspace','keyboarspace','WEB','模拟键盘space键'),
                                        ('keyboarctrl1','keyboarctrl','WEB','模拟键盘ctrl键'),
                                        ('keyboarshift','keyboarshift','WEB','模拟鼠标shift键'),
                                        ('keyboarenter','keyboarenter','WEB','模拟键盘enter键'),
                                        ('runcase12345','runcase','WEB','获取指定接口'),
                                        ('getattribute','getattribute','WEB','获取对象指定属性'),
                                        ('getcssvalue1','getcssvalue','WEB','获取对象指定css属性值'),
                                        ('jumpparentfr','jumpparentframe','WEB','跳转回上一级'),
                                        ('scrollto1234','scrollto','WEB','滚动到目标对象'),
                                        ('scrolltoview','scrolltoview','WEB','将目标对象滚动到可视'),
                                        ('closewindow1','closewindow','WEB','关闭当前窗口'),
                                        ('addcookie123','addcookie','WEB','添加浏览cookie'),
                                        ('refreshpage1','refreshpage','WEB','刷新当前页面'),
                                        ('forwordPage1','forwordPage','WEB','前进当前页面'),
                                        ('backpage1234','backpage','WEB','回退当前页面'),
                                        ('waitUrlToBe','waitUrlToBe','WEB','等待地址');

                                        ('appselectext','selectbyvisibletext','APP','通过下拉框文本进行选择'),
                                        ('appselectbyv','selectbyvalue','APP','通过下拉框的value进行选着'),
                                        ('appselectbyi','selectbyindex','APP','通过下拉框的index进行选择'),
                                        ('appisselect1','isselect','APP','判断是否被选择'),
                                        ('appgettext12','gettext','app','获取对象文本属性'),
                                        ('appgettagnam','gettagname','APP','获取对象标签属性'),
                                        ('appgetattrib','getattribute','APP','获取对象指定属性'),
                                        ('appgetcssval','getcssvalue','APP','获取对象指定css属性值'),
                                        ('appclick1234','click','APP','点击对象'),
                                        ('appinputkeys','inputkeys','APP','输入'),
                                        ('appclean1234','clean','APP','清除输入框'),
                                        ('appisusbale1','isenabled','APP','判断对象是否可用'),
                                        ('applondement','longpresselement','APP','长按指定页面对象'),
                                        ('appalertacce','alertaccept','APP','弹出框点确认'),
                                        ('appalertcanc','alertcancel','APP','弹出框点取消'),
                                        ('appalertgett','alertgettext','APP','获取弹出框text'),
                                        ('appage90nte','getcontexthandles','APP','获取当前所有上下文句柄'),
                                        ('appex8ob123','exjsob','APP','针对对象执行js脚本'),
                                        ('appandro0dke','androidkeycode','APP','安卓模拟手机按键'),
                                        ('appgoto8onte','gotocontext','APP','跳转指定的context'),
                                        ('appgetti0le1','gettitle','APP','获取当前页标题'),
                                        ('appgetcojtex','getcontext','APP','获取当前页context'),
                                        ('appswipe8age','swipepageup','APP','页面向上滑动(参数：持续时间(秒),滚动次数)'),
                                        ('appswipedoen','swipepagedown','APP','页面向下滑动(参数：持续时间（秒）,滚动次数)'),
                                        ('appswpe9left','swipepageleft','APP','页面向左滑动(参数：持续时间（秒）,滚动次数)'),
                                        ('appswipright','swipepageright','APP','页面向右滑动(参数：持续时间（秒）,滚动次数)'),
                                        ('applongpress','longpressxy','APP','长按屏幕(参数：x坐标,y坐标,持续时间（选填）)'),
                                        ('apppressxy12','pressxy','APP','点击屏幕(参数：x坐标,y坐标)'),
                                        ('appmoveto123','moveto','APP','拖动对象(参数：startx坐标,starty坐标;x,y;x,y)'),
                                        ('appcompel123','compel','APP','强制等待时间s'),
                                        ('appscreensho','screenshot','APP','保存截图(参数：x坐标,y坐标)'),
                                        ('apphide9eybo','hideKeyboard','APP','隐藏手机系统键盘'),
                                        ('apprunca0e12','runcase','APP','调用指定接口用例'),
                                        ('appexecu0eAd','executeAdb','APP','执行安卓adb命令'),
                                        ('appswipe9p12','swipeup','APP','手指向上滑动(参数：持续时间（秒），滚动次数)'),
                                        ('appswipe8own','swipedown','APP','手指向下滑动(参数：持续时间秒，滚动次数)'),
                                        ('appswipeleft','swipeleft','APP','手指向左滑动(参数：持续时间（秒），滚动次数)'),
                                        ('appswipe8igh','swiperight','APP','手指向右滑动(参数：持续时间(秒)，滚动次数)'),
                                        ('appimpli0itl','implicitlyWait','APP','隐式等待(参数：秒)');



-- 系统级
-- 初始话管理员角色表
INSERT INTO  pcs_prc_role  ( id ,  name ,  description ,  grouper ,  type ,   scope ,  default_role , business_type ) VALUES ('1', '管理员角色', NULL, 'system', '1', 1, 0, 1);
-- 初始话普通色表
INSERT INTO  pcs_prc_role  ( id ,  name ,  description ,  grouper ,  type ,   scope ,  default_role , business_type ) VALUES ('2', '普通角色', NULL, 'system', '1', 1,  1, 0);
-- 管理员角色金和admin用户绑定 表
-- INSERT INTO  pcs_prc_role_user  ( id ,  role_id ,  user_id ) VALUES ('2e2222', '1', '111111');

-- 项目级
-- 初始话管理员角色表
INSERT INTO  pcs_prc_role  ( id ,  name ,  description ,  grouper ,  type ,   scope ,  default_role , business_type ) VALUES ('3', '项目管理员', NULL, 'system', '2', 1, 0, 1);
-- 初始话普通色表
INSERT INTO  pcs_prc_role  ( id ,  name ,  description ,  grouper ,  type ,   scope ,  default_role , business_type ) VALUES ('4', '项目普通角色', NULL, 'system', '2', 1, 1, 0);
-- 项目管理员角色金和admin用户绑定 表
-- INSERT INTO  pcs_prc_role_user  ( id ,  role_id ,  tag , tag_value ) VALUES ('2', '3', 1, '111111');

-- 初始化admin角色平台的功能点关联数据
-- 用户模块 和 admin用户
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('1de76c3f04a', '1', '5fb7863b09a8');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('c88134f7af2', '1', '57a3bcd1e5fe');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('640892e368a', '1', '428be660dea3');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('4d62ceac4ea', '1', 'dd81bdbb52bc');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('6ae50d02d4e', '1', '9633d9475886');

-- 部门模块 和 admin用户
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('35a204b61b6', '1', '6b61fbe5091a8');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('6d56ee9b251', '1', '9c99b8a096c87');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('95418992b59', '1', 'cb954a7c0be3b');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('0ca3c862527', '1', '325c2503007fd');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('f8438284b4a', '1', 'e8bf9843bc9da');

-- 用户目录 和 admin用户
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('e64e932f43a', '1', '585d26bcbdf3');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('ae443de5001', '1', '043e412151db');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('d026551cfc3', '1', '925371be8ec6');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('c5fe246887f', '1', '890e7d41decf');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('70978ef5436', '1', '447d9998fc00');

-- pcs ---end

--功能点
INSERT INTO  pcs_prc_function  ( id ,  name ,  code ,  parent_function_id ,  sort ,  type ) VALUES ('03e4b44977b', '删除仓库', 'repositoryDelete', NULL, 31, '2');
INSERT INTO  pcs_prc_function  ( id ,  name ,  code ,  parent_function_id ,  sort ,  type ) VALUES ('06efc60a2f9', '消息管理', 'MSG_Notice', '305864b7559', 33, '1');
INSERT INTO  pcs_prc_function  ( id ,  name ,  code ,  parent_function_id ,  sort ,  type ) VALUES ('2a0018f77b4', '发送方式', 'MSG_SendType', '305864b7559', 32, '1');
INSERT INTO  pcs_prc_function  ( id ,  name ,  code ,  parent_function_id ,  sort ,  type ) VALUES ('305864b7559', '消息中心', 'MessageCenter', NULL, 4, '1');
INSERT INTO  pcs_prc_function  ( id ,  name ,  code ,  parent_function_id ,  sort ,  type ) VALUES ('3ece7f7c704', '安全', 'security', NULL, 12, '1');
INSERT INTO  pcs_prc_function  ( id ,  name ,  code ,  parent_function_id ,  sort ,  type ) VALUES ('4372581c839', '代办管理', 'TODO', NULL, 1, '1');
INSERT INTO  pcs_prc_function  ( id ,  name ,  code ,  parent_function_id ,  sort ,  type ) VALUES ('613e2055f04', '插件管理', 'plugin', NULL, 8, '1');
INSERT INTO  pcs_prc_function  ( id ,  name ,  code ,  parent_function_id ,  sort ,  type ) VALUES ('6d584487ba0', '系统权限中心', 'systemPrivilege', NULL, 9, '1');
INSERT INTO  pcs_prc_function  ( id ,  name ,  code ,  parent_function_id ,  sort ,  type ) VALUES ('957cb2bb99c', '项目权限中心', 'projectPrivilege', NULL, 11, '1');
INSERT INTO  pcs_prc_function  ( id ,  name ,  code ,  parent_function_id ,  sort ,  type ) VALUES ('a70e6efaafb', '日志', 'log', '3ece7f7c704', 13, '1');
INSERT INTO  pcs_prc_function  ( id ,  name ,  code ,  parent_function_id ,  sort ,  type ) VALUES ('c1fe847f511', '我的待办', 'myTodo', '4372581c839', 1, '1');
INSERT INTO  pcs_prc_function  ( id ,  name ,  code ,  parent_function_id ,  sort ,  type ) VALUES ('cced804414d', '代办列表', 'todoList', '4372581c839', 1, '1');

INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('12a9d8f5a8a7', '1', '3ece7f7c704');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('1ae8800b8d14', '1', 'cced804414d');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('40c042bcc344', '1', 'c1fe847f511');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('543e535f14db', '1', '4372581c839');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('5b56bc252750', '1', '305864b7559');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('5c5c647d3028', '1', '06efc60a2f9');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('7a86c353f526', '1', '2a0018f77b4');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('b9026e176ce8', '1', '957cb2bb99c');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('c2a6385e01b6', '1', 'a70e6efaafb');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('cf517ede114b', '1', '6d584487ba0');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('eebeab283759', '1', '613e2055f04');
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('c31825ad3cdd', 'custom3', '03e4b44977b');

INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('8af6e8988989', '111111', '9633d9475886');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('6e29214891f0', '111111', 'dd81bdbb52bc');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('510ca04fe2b4', '111111', '57a3bcd1e5fe');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('44c6150c542c', '111111', '428be660dea3');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('cca3f24422b1', '111111', '5fb7863b09a8');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('4985aa5222a8', '111111', 'e8bf9843bc9d');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('15a96c1f5ee9', '111111', 'cb954a7c0be3');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('b800b57ede88', '111111', '9c99b8a096c8');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('6d2a0f4c97cf', '111111', '325c2503007f');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('556b9438f64e', '111111', '6b61fbe5091a');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('9f1bb7e12bde', '111111', '043e412151db');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('5e0520d888e7', '111111', '925371be8ec6');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('e7b5b84c7534', '111111', '447d9998fc00');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('5bdf451f4fd2', '111111', '890e7d41decf');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('3ab457ff07c0', '111111', '585d26bcbdf3');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('3c36003ed644', '111111', 'wqre9998fc00');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('62a4ba4eff7f', '111111', '43e7d41decf7');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('8170d3c0f03c', '111111', 'hfg5371be8ec');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('d877a613667d', '111111', 'oug5371be8ec');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('9c448b13b453', '111111', 'hf43e412151e');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('eba2de64c4b0', '111111', '4235d2624bdf');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('10002b0d3945', '111111', 'e5b34be19fab');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('3cdf115ac16c', '111111', '4cc4e67319a0');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('b9d90000bf40', '111111', 'cb6c8c3f4048');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('d5f26142967b', '111111', '64bdf62686a4');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('fe8e5ca68fab', '111111', '2a0018f77b4');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('c1fae3a317c0', '111111', '06efc60a2f9');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('763271a8417b', '111111', '305864b7559');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('87ba2db22ed9', '111111', 'a70e6efaafb');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('6b9046b185c2', '111111', '3ece7f7c704');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('472e2db1bf05', '111111', 'cced804414d');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('ec2fa64cd62d', '111111', 'c1fe847f511');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('cb7361496bac', '111111', '4372581c839');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('a1b479729653', '111111', '613e2055f04');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('51174754c565', '111111', '6d584487ba0');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('36edafdc77e4', '111111', '957cb2bb99c');
INSERT INTO "pcs_prc_role_function" ("id", "role_id", "function_id") VALUES ('c3cd5020013b', 'pro_111111', '03e4b44977b');


--日志--
INSERT INTO  pcs_op_log_template  ( id ,  title ,  content ,  link ,  bgroup ,  abstract_content ) VALUES ('LOG_CODE', '日志通用模板', '<div style="padding: 10px;"><div style="color: #333;">${mode}</div><div style="margin-top: 5px;"><span style="color: #0075de;">${user}</span><span style="margin: 0 5px;">${actionType}</span><span style="font-weight: bold; color: #0075de;">${name}</span></div></div>', '/repositoryPage', 'testrubo','管理员创建了默认仓库');

INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('CREATE_REPOSITORY_TYPE', '新增了仓库', 'testrubo');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('DELETE_REPOSITORY_TYPE', '删除了仓库', 'testrubo');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('UPDATE_REPOSITORY_TYPE', '更新了仓库', 'testrubo');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('CREATE_CASE_TYPE', '新增了用例', 'testrubo');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('DELETE_CASE_TYPE', '删除了用例', 'testrubo');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('UPDATE_CASE_TYPE', '更新了用例', 'testrubo');

--消息--
INSERT INTO  pcs_mec_message_template  ( id ,  msg_type_id ,  msg_send_type_id ,  title ,  content ,  link ,  bgroup ) VALUES ('7c8fb5b0', 'RP_MS_TYPE', 'email', '邮箱仓库创建模板', '<div style="display: flex;align-items: center;"> <span style="color: #6c8ca0;margin: 0 5px 0 0;">${userName}</span>创建了:<img src=\"${images}\" alt=\"\" width=\"16px\" height=\"16px\" style=\"\n    margin: 0 5px;\"/><span  style=\"font-weight:600;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;\">${name}</span></div>', NULL, 'testrubo');
INSERT INTO  pcs_mec_message_template  ( id ,  msg_type_id ,  msg_send_type_id ,  title ,  content ,  link ,  bgroup ) VALUES ('RP_CREATE', 'RP_MS_TYPE', 'site', '仓库创建通知', '<div style=\"\n    display: flex;\n    align-items: center;\n\"> <span style=\"\n    color: #6c8ca0;\n    margin: 0 5px 0 0;\n\">${userName}</span>创建了:<img src=\"${images}\" alt=\"\" width=\"16px\" height=\"16px\" style=\"\n    margin: 0 5px;\"/><span  style=\"font-weight:600;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;width: 120px;\">${name}</span></div>', '/workspacePage', 'testrubo');
INSERT INTO  pcs_mec_message_template  ( id ,  msg_type_id ,  msg_send_type_id ,  title ,  content ,  link ,  bgroup ) VALUES ('RP_CREATE_DD', 'RP_MS_TYPE', 'dingding', '钉钉仓库创建', '**仓库创建** \n\n**${userName}**  创建了   **${name}**\n\n![workspaceImg](${images})\n\n', NULL, 'testrubo');
INSERT INTO  pcs_mec_message_template  ( id ,  msg_type_id ,  msg_send_type_id ,  title ,  content ,  link ,  bgroup ) VALUES ('RP_CREATE_WX', 'RP_MS_TYPE', 'qywechat', NULL, '**仓库创建**  \n\n**${userName}**  创建了   **${name}**\n', NULL, 'testrubo');

INSERT INTO  pcs_mec_message_notice  ( id ,  message_type_id ,  type ,  bgroup ,  message_send_type_id ) VALUES ('MESSAGE_NOTICE_ID', 'RP_MS_TYPE', 1, 'testrubo', 'email,site');

INSERT INTO  pcs_mec_message_notice_connect_user  ( id ,  message_notice_id ,  user_id ) VALUES ('afc42e5554', 'MESSAGE_NOTICE_ID', '111111');
INSERT INTO  pcs_mec_message_notice_connect_role  ( id ,  message_notice_id ,  role_id ) VALUES ('df4d545645', 'MESSAGE_NOTICE_ID', '1');

INSERT INTO  pcs_mec_message_type  ( id ,  name ,  description ,  bgroup ) VALUES ('RP_MS_TYPE', '创建仓库通知', NULL, 'testrubo');


--初始化的项目的角色
INSERT INTO  pcs_prc_role  ( id ,  name ,  description ,  grouper ,  type ,  scope ,  business_type ,  default_role ) VALUES ('custom3', '项目管理员', NULL, 'system', '2', 2, 1, 0);
INSERT INTO  pcs_prc_role  ( id ,  name ,  description ,  grouper ,  type ,  scope ,  business_type ,  default_role ) VALUES ('custom4', '项目普通角色', NULL, 'system', '2', 2, 0, 1);
INSERT INTO  pcs_prc_role_function  ( id ,  role_id ,  function_id ) VALUES ('7a06707984c0', 'custom3', '03e4b44977b');

INSERT INTO  pcs_ucc_dm_user   ( id ,  domain_id ,  user_id ,  type )  VALUES ('5c56ca9a24a', 'bd26c6ec5c6e', '111111', 0);
INSERT INTO  pcs_prc_dm_role_user  ( id ,  dmRole_id ,  domain_id ,  user_id ) VALUES ('36388488b', '1bd0aa5c5f', 'bd26c6ec5c6e', '111111');

INSERT INTO  pcs_prc_dm_role  ( id ,  domain_id ,  role_id ,  business_type ) VALUES ('1bd0aa5c5f', 'bd26c6ec5c6e', 'custom3', 1);
INSERT INTO  pcs_prc_dm_role  ( id ,  domain_id ,  role_id ,  business_type ) VALUES ('adf4c4ca69', 'bd26c6ec5c6e', 'custom4', 0);



