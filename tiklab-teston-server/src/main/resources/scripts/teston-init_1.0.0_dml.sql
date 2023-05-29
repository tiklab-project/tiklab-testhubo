INSERT INTO  teston_repository  ( id ,  name ,  description ,  visibility ,  icon_url ,  user_id ) VALUES ('bd26c6ec5c6e', '默认仓库', NULL, 1, '/images/pi1.png', '111111');

INSERT INTO  teston_category  VALUES ('3f3dbc6d8eb622c1ee1e464d0a65bbb0', '默认分组', 'bd26c6ec5c6e', NULL, NULL, NULL);

-- INSERT INTO  teston_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('133e6ceab301f0803332e9e7b4ae884e', 'APP性能', 'bd26c6ec5c6e', '3f3dbc6d8eb622c1ee1e464d0a65bbb0', 'perform', 'app-perform', '111111', '111111', '2022-07-22 13:13:23', '2023-02-15 03:24:56', NULL, 0);
INSERT INTO  teston_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('1614d5d7027bfc289eb191c6d7402831', '接口登录', 'bd26c6ec5c6e', '3f3dbc6d8eb622c1ee1e464d0a65bbb0', 'api', 'api-unit', '111111', '111111', '2022-07-21 00:57:44', '2023-02-15 03:25:21', NULL, NULL);
INSERT INTO  teston_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('78846d53e2981e02f924716dd64a74c2', '接口性能', 'bd26c6ec5c6e', '3f3dbc6d8eb622c1ee1e464d0a65bbb0', 'perform', 'api-perform', '111111', '111111', '2022-07-12 11:39:32', '2023-02-06 09:32:46', NULL, 0);
-- INSERT INTO  teston_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('84680d35804f8f217b3a801706ba2e28', 'Web性能', 'bd26c6ec5c6e', '3f3dbc6d8eb622c1ee1e464d0a65bbb0', 'perform', 'web-perform', '111111', '111111', '2022-07-22 01:31:32', '2023-02-15 03:25:07', NULL, 0);
INSERT INTO  teston_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('8c6717ec9741435df4d8aced2948758b', 'App场景', 'bd26c6ec5c6e', '3f3dbc6d8eb622c1ee1e464d0a65bbb0', 'ui', 'app-scene', '111111', '111111', '2022-07-13 15:11:26', '2023-02-15 03:25:59', NULL, 0);
INSERT INTO  teston_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('aea59c50c968b575042a2e8739cb05e7', '接口场景', 'bd26c6ec5c6e', '3f3dbc6d8eb622c1ee1e464d0a65bbb0', 'api', 'api-scene', '111111', '111111', '2022-07-12 08:48:38', '2023-02-15 03:26:07', NULL, 0);
INSERT INTO  teston_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('b51c0d9b2c9d8f80582d9979bfb98eb0', 'Web场景', 'bd26c6ec5c6e', '3f3dbc6d8eb622c1ee1e464d0a65bbb0', 'ui', 'web-scene', '111111', '111111', '2022-07-16 09:57:39', '2023-02-15 03:25:39', NULL, 0);
INSERT INTO  teston_testcase  ( id ,  name ,  repository_id ,  category_id ,  test_type ,  case_type ,  create_user ,  update_user ,  create_time ,  update_time ,  description ,  sort ) VALUES ('f11a97c27b8f2dcb8b3df308cb82fdae', '功能用例', 'bd26c6ec5c6e', '3f3dbc6d8eb622c1ee1e464d0a65bbb0', 'function', 'function', '111111', '111111', '2022-07-14 15:05:04', '2023-02-15 03:25:51', NULL, 0);

INSERT INTO  teston_env_api  VALUES ('8b0829b48e51e4678c84a90b60725d85', 'bd26c6ec5c6e', 'test', 'http://teston-ce.tiklab.net');

INSERT INTO  teston_api_unit  ( id ,  testcase_id ,  path ,  method_type ) VALUES ('1614d5d7027bfc289eb191c6d7402831', '1614d5d7027bfc289eb191c6d7402831', '/eam/auth/login', 'post');
INSERT INTO  teston_api_request_body  ( id ,  api_unit_id ,  body_type ) VALUES ('1614d5d7027bfc289eb191c6d7402831', '1614d5d7027bfc289eb191c6d7402831', 'raw');
INSERT INTO  teston_api_raw   ( id ,  api_unit_id ,  raw ,  type )  VALUES ('1614d5d7027bfc289eb191c6d7402831', '1614d5d7027bfc289eb191c6d7402831', '{\n	\"account\": \"admin\",\n	\"password\": \"123456\",\n	\"userType\": \"1\"\n}', 'json');

INSERT INTO  teston_api_scene  VALUES ('aea59c50c968b575042a2e8739cb05e7', 'aea59c50c968b575042a2e8739cb05e7');
INSERT INTO  teston_api_scene_step  VALUES ('771c6a4c212e175eeb1e88af6c7a13cd', 'aea59c50c968b575042a2e8739cb05e7', '1614d5d7027bfc289eb191c6d7402831', '2022-7-21 00:59:28');

INSERT INTO  teston_api_perfcase  ( id ,  testcase_id ,  thread_count ,  execute_type ,  execute_count ) VALUES ('78846d53e2981e02f924716dd64a74c2', '78846d53e2981e02f924716dd64a74c2', 3, 1, 2);
INSERT INTO  teston_api_perf_step  VALUES ('927cfaf94f5ee6235dac35b0bbfd92cf', 'aea59c50c968b575042a2e8739cb05e7', '78846d53e2981e02f924716dd64a74c2', '2022-7-20 13:25:39');

INSERT INTO  teston_web_scene  VALUES ('b51c0d9b2c9d8f80582d9979bfb98eb0', 'b51c0d9b2c9d8f80582d9979bfb98eb0');
INSERT INTO  teston_web_scene_step  ( id ,  web_scene_id ,  name ,  location ,  location_value ,  action_type ,  step_action ,  parameter ,  expected_result ,  create_time ,  sort ) VALUES ('5c15cfd46217c3626e9bddf529bd177e', 'b51c0d9b2c9d8f80582d9979bfb98eb0', '输入值', 'id', 'kw', 'inputkeys', NULL, 'selenium', NULL, '2023-02-04 15:11:53', 1);
INSERT INTO  teston_web_scene_step  ( id ,  web_scene_id ,  name ,  location ,  location_value ,  action_type ,  step_action ,  parameter ,  expected_result ,  create_time ,  sort ) VALUES ('dda685ac7a7519d4132309367c603435', 'b51c0d9b2c9d8f80582d9979bfb98eb0', '打开百度', NULL, NULL, 'openurl', NULL, 'https://www.baidu.com', NULL, '2023-02-04 15:10:38', 0);
INSERT INTO  teston_web_scene_step  ( id ,  web_scene_id ,  name ,  location ,  location_value ,  action_type ,  step_action ,  parameter ,  expected_result ,  create_time ,  sort ) VALUES ('e311b69a2c453f3046551563c39799f4', 'b51c0d9b2c9d8f80582d9979bfb98eb0', 'test', 'id', 'su', 'click', NULL, NULL, NULL, '2023-02-04 15:30:08', NULL);

-- INSERT INTO  teston_web_perfcase  ( id ,  testcase_id ,  thread_count ,  execute_type ,  execute_count ) VALUES ('84680d35804f8f217b3a801706ba2e28', '84680d35804f8f217b3a801706ba2e28', 2, 1, 3);
-- INSERT INTO  teston_web_perf_step  ( id ,  web_scene_id ,  web_perf_id ,  create_time ) VALUES ('04b83a81543bece6293e0377bd298776', 'b51c0d9b2c9d8f80582d9979bfb98eb0','84680d35804f8f217b3a801706ba2e28', '2023-02-04 15:30:08');

INSERT INTO  teston_app_scene  ( id ,  testcase_id ) VALUES ('8c6717ec9741435df4d8aced2948758b', '8c6717ec9741435df4d8aced2948758b');
INSERT INTO  teston_app_scene_step  ( id ,  app_scene_id ,  name ,  location ,  location_value ,  action_type ,  step_action ,  parameter ,  expected_result ,  create_time ,  sort ) VALUES ('898ce2e7668107066af821df61e18be0', '8c6717ec9741435df4d8aced2948758b', '等待', NULL, NULL, 'implicitlyWait', NULL, '5', NULL, '2023-02-04 17:08:22', 0);
INSERT INTO  teston_app_scene_step  ( id ,  app_scene_id ,  name ,  location ,  location_value ,  action_type ,  step_action ,  parameter ,  expected_result ,  create_time ,  sort ) VALUES ('bf2589b59130f5ff84c50efbfda4fc9b', '8c6717ec9741435df4d8aced2948758b', '输入', 'xpath', '//android.widget.EditText[@content-desc=\"请输入QQ号码或手机或邮箱\"]', 'inputkeys', NULL, '550535852', NULL, '2023-02-04 17:10:49', 2);
INSERT INTO  teston_app_scene_step  ( id ,  app_scene_id ,  name ,  location ,  location_value ,  action_type ,  step_action ,  parameter ,  expected_result ,  create_time ,  sort ) VALUES ('f4d64919d573a1518f1655bfa10b148f', '8c6717ec9741435df4d8aced2948758b', '点击', 'id', 'com.tencent.mobileqq:id/btn_login', 'click', NULL, NULL, NULL, '2023-02-04 17:10:41', 1);

-- INSERT INTO  teston_app_perfcase  ( id ,  testcase_id ,  thread_count ,  execute_type ,  execute_count ) VALUES ('133e6ceab301f0803332e9e7b4ae884e', '133e6ceab301f0803332e9e7b4ae884e', 1, 1, 2);
-- INSERT INTO  teston_app_perf_step  ( id ,  app_scene_id ,  app_perf_id ,  create_time ) VALUES ('d8930deea51e8ee1d0d34b2e6d3ee11f', '8c6717ec9741435df4d8aced2948758b', '133e6ceab301f0803332e9e7b4ae884e', '2022-07-22 13:13:28');

INSERT INTO  teston_func_unit  ( id ,  testcase_id ) VALUES ('f11a97c27b8f2dcb8b3df308cb82fdae', 'f11a97c27b8f2dcb8b3df308cb82fdae');
INSERT INTO  teston_func_unit_step  ( id ,  func_unit_id ,  expect ,  actual ,  described ,  create_time ,  update_time ) VALUES ('0ac262c53caf20d4efc5119d69b55710', 'f11a97c27b8f2dcb8b3df308cb82fdae', '成功', '成功', '打开postin登录页面', '2023-01-16 07:11:08', '2023-02-15 09:15:50');
INSERT INTO  teston_func_unit_step  ( id ,  func_unit_id ,  expect ,  actual ,  described ,  create_time ,  update_time ) VALUES ('a7e296fd3edde8ccf2f28eaa1ccb1c97', 'f11a97c27b8f2dcb8b3df308cb82fdae', '成功', '成功', '输入账号密码值', '2023-02-15 09:14:28', '2023-02-15 09:16:05');
INSERT INTO  teston_func_unit_step  ( id ,  func_unit_id ,  expect ,  actual ,  described ,  create_time ,  update_time ) VALUES ('ddca612c477cc772d1eda0ac0eab4295', 'f11a97c27b8f2dcb8b3df308cb82fdae', '成功', '成功', '点击登录', '2023-02-15 09:16:18', NULL);

INSERT INTO teston_action_type values   ('click1234567','click','WEB','点击对象'),
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

