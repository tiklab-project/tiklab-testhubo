(window.webpackJsonp=window.webpackJsonp||[]).push([[38],{1076:function(e,t,n){"use strict";n.r(t);var i=n(0),r=n.n(i),a=(n(905),n(900)),l=n(505),u=n(311),c=n(630),o=n(188),m=n(406),s=(n(972),n(19)),d=n(204),N="D:\\a-dk-web\\thoughtware-teston-ui\\src\\setting\\system\\SysManagMenu.js";function f(){return(f=Object.assign?Object.assign.bind():function(e){for(var t=1;t<arguments.length;t++){var n,i=arguments[t];for(n in i)Object.prototype.hasOwnProperty.call(i,n)&&(e[n]=i[n])}return e}).apply(this,arguments)}function g(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var i,r,a=[],l=!0,u=!1;try{for(n=n.call(e);!(l=(i=n.next()).done)&&(a.push(i.value),!t||a.length!==t);l=!0);}catch(e){u=!0,r=e}finally{try{l||null==n.return||n.return()}finally{if(u)throw r}}return a}}(e,t)||function(e,t){var n;if(e)return"string"==typeof e?b(e,t):"Map"===(n="Object"===(n=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:n)||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?b(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function b(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,i=new Array(t);n<t;n++)i[n]=e[n];return i}function p(e){var t=e.settingMenu,n=Object(m.useSelector)((function(e){return e.pluginStore})),b=e.route.routes,p=g(Object(i.useState)("/setting/systemRole"),2),y=p[0],v=p[1],E=(p=g(Object(i.useState)(),2))[0],w=p[1],k=JSON.parse(localStorage.getItem("authConfig")),S=window.location.hash.substr(1),x=(Object(i.useEffect)((function(){v(S);var e,i=n.filter((function(e){return"settingMenu"===e.point})).filter((function(e){return e.menuTitle}));0<i.length?(i&&i.map((function(t){return e=t.menuTitle.map((function(e){return{title:e.menuTitle,icon:"laptop",id:"/"+e.mount+e.router}}))})),w(t.concat(e))):w(t)}),[S]),["/setting/orga","/setting/user","/setting/dir","/setting/userGroup"]),j=function(t){var n,i;if(!k.authType&&x.includes(t))return n=k.authServiceUrl,i=Object(s.getUser)().ticket,void window.open(n+"#"+t+"?ticket="+i,"_blank");v(t),e.history.push(t)},O=function(e){if(!k.authType&&x.includes(e))return r.a.createElement(d.a,{icon:"dakaixinyemian",className:"icon-s",__source:{fileName:N,lineNumber:76,columnNumber:24}})},C=(p=g(Object(i.useState)(["/setting/system"]),2))[0],T=p[1],z=function(e){return C.some((function(t){return t===e}))},M=function(e){z(e)?T(C.filter((function(t){return t!==e}))):T(C.concat(e))},A=function(e,t,n){return e.purviewCode?r.a.createElement(o.PrivilegeButton,{code:e.purviewCode,key:e.id,__source:{fileName:N,lineNumber:104,columnNumber:17}},r.a.createElement("li",{key:e.id,className:" orga-aside-li ".concat(e.id===y?"orga-aside-select":null),onClick:function(){return j(e.id)},style:{paddingLeft:"".concat(20*t,"px")},__source:{fileName:N,lineNumber:105,columnNumber:21}},r.a.createElement("div",{className:"aside-li",__source:{fileName:N,lineNumber:111,columnNumber:25}},r.a.createElement("div",{__source:{fileName:N,lineNumber:112,columnNumber:29}},n?r.a.createElement("svg",{style:{width:16,height:16,margin:"0 5px 0 0"},"aria-hidden":"true",__source:{fileName:N,lineNumber:115,columnNumber:42}},r.a.createElement("use",{xlinkHref:"#icon-".concat(e.icon),__source:{fileName:N,lineNumber:116,columnNumber:45}})):null,e.title),O(e.id)))):r.a.createElement("li",{key:e.id,className:" orga-aside-li ".concat(e.id===y?"orga-aside-select":null),onClick:function(){return j(e.id)},style:{paddingLeft:"".concat(20*t,"px")},__source:{fileName:N,lineNumber:131,columnNumber:20}},r.a.createElement("div",{className:"aside-li",__source:{fileName:N,lineNumber:137,columnNumber:17}},r.a.createElement("div",{__source:{fileName:N,lineNumber:138,columnNumber:21}},n?r.a.createElement("svg",{style:{width:16,height:16,margin:"0 5px 0 0"},"aria-hidden":"true",__source:{fileName:N,lineNumber:141,columnNumber:34}},r.a.createElement("use",{xlinkHref:"#icon-".concat(e.icon),__source:{fileName:N,lineNumber:142,columnNumber:37}})):null,e.title),O(e.id)))};return r.a.createElement(o.SystemNav,f({},e,{expandedTree:C,setExpandedTree:T,applicationRouters:E,outerPath:"/setting",notFoundPath:"/noaccess",__source:{fileName:N,lineNumber:248,columnNumber:9}}),r.a.createElement(a.a,{className:"sysmana-layout",__source:{fileName:N,lineNumber:256,columnNumber:13}},r.a.createElement(_,{className:"sysmana-sider",width:240,theme:"light",__source:{fileName:N,lineNumber:257,columnNumber:17}},r.a.createElement("div",{className:"thoughtware-orga-aside",__source:{fileName:N,lineNumber:262,columnNumber:21}},r.a.createElement("ul",{style:{padding:0},__source:{fileName:N,lineNumber:263,columnNumber:25}},(p=E)&&p.map((function(e){return e.children&&0<e.children.length?function e(t,n){var i=t.title,a=t.id,l=t.children,m=t.purviewCode;return t=t.icon,m?r.a.createElement(o.PrivilegeButton,{code:m,key:a,__source:{fileName:N,lineNumber:161,columnNumber:17}},r.a.createElement("li",{key:a,title:i,__source:{fileName:N,lineNumber:162,columnNumber:21}},r.a.createElement("div",{className:"orga-aside-item aside-li",onClick:function(){return M(a)},style:{paddingLeft:"".concat(20*n,"px")},__source:{fileName:N,lineNumber:163,columnNumber:25}},r.a.createElement("div",{className:"menu-name-icon",__source:{fileName:N,lineNumber:167,columnNumber:29}},r.a.createElement("svg",{style:{width:16,height:16,margin:"0 5px 0 0"},"aria-hidden":"true",__source:{fileName:N,lineNumber:168,columnNumber:33}},r.a.createElement("use",{xlinkHref:"#icon-".concat(t),__source:{fileName:N,lineNumber:169,columnNumber:37}})),r.a.createElement("span",{key:a,__source:{fileName:N,lineNumber:171,columnNumber:33}}," ",i)),r.a.createElement("div",{className:"orga-aside-item-icon",__source:{fileName:N,lineNumber:173,columnNumber:29}},l?z(a)?r.a.createElement(u.a,{style:{fontSize:"12px"},__source:{fileName:N,lineNumber:177,columnNumber:51}}):r.a.createElement(c.a,{style:{fontSize:"12px"},__source:{fileName:N,lineNumber:178,columnNumber:51}}):"")),r.a.createElement("ul",{key:a,title:i,className:"orga-aside-ul ".concat(z(a)?null:"orga-aside-hidden"),__source:{fileName:N,lineNumber:184,columnNumber:25}},l&&l.map((function(t){return t.children&&t.children.length?e(t,1):A(t,1,!1)}))))):r.a.createElement("li",{key:a,title:i,__source:{fileName:N,lineNumber:199,columnNumber:17}},r.a.createElement("div",{className:"orga-aside-item aside-li",onClick:function(){return M(a)},style:{paddingLeft:"".concat(20*n,"px")},__source:{fileName:N,lineNumber:200,columnNumber:21}},r.a.createElement("div",{className:"menu-name-icon",__source:{fileName:N,lineNumber:204,columnNumber:25}},r.a.createElement("svg",{style:{width:16,height:16,margin:"0 5px 0 0"},"aria-hidden":"true",__source:{fileName:N,lineNumber:205,columnNumber:29}},r.a.createElement("use",{xlinkHref:"#icon-".concat(t),__source:{fileName:N,lineNumber:206,columnNumber:33}})),r.a.createElement("span",{key:a,__source:{fileName:N,lineNumber:208,columnNumber:29}},i)),r.a.createElement("div",{className:"orga-aside-item-icon",__source:{fileName:N,lineNumber:210,columnNumber:25}},l?z(a)?r.a.createElement(u.a,{style:{fontSize:"12px"},__source:{fileName:N,lineNumber:214,columnNumber:47}}):r.a.createElement(c.a,{style:{fontSize:"12px"},__source:{fileName:N,lineNumber:215,columnNumber:47}}):"")),r.a.createElement("ul",{key:a,title:i,className:"orga-aside-ul ".concat(z(a)?null:"orga-aside-hidden"),__source:{fileName:N,lineNumber:221,columnNumber:21}},l&&l.map((function(t){return t.children&&t.children.length?e(t,1):A(t,1,!1)}))))}(e):A(e,null,!0)}))))),r.a.createElement(h,{className:"sysmana-content",__source:{fileName:N,lineNumber:270,columnNumber:17}},Object(l.a)(b))))}var _=a.a.Sider,h=a.a.Content;function y(){return(y=Object.assign?Object.assign.bind():function(e){for(var t=1;t<arguments.length;t++){var n,i=arguments[t];for(n in i)Object.prototype.hasOwnProperty.call(i,n)&&(e[n]=i[n])}return e}).apply(this,arguments)}t.default=function(e){var t=[{title:"用户与权限",icon:"chengyuan",id:"accountMember",children:[{title:"部门",id:"/setting/orga",icon:"modular",purviewCode:"orga"},{title:"用户",id:"/setting/user",icon:"modular",purviewCode:"user"},{title:"用户目录",id:"/setting/dir",icon:"modular",purviewCode:"user_dir"},{title:"用户组",id:"/setting/userGroup",icon:"modular"},{title:"权限",icon:"jiaosequanxian",id:"/setting/systemRole"}]},{title:"消息",icon:"xiaoxi",id:"/setting",children:[{title:"消息发送方式",icon:"rizhijilu",id:"/setting/messageSendType",purviewCode:"MSG_SendType"},{title:"消息通知方案",icon:"rizhijilu",id:"/setting/message-notice",purviewCode:"MSG_Notice"}]},{title:"Agent配置",id:"/setting/agent",icon:"jiqun-mianxing"},{title:"插件",icon:"plugin",id:"/setting/plugin",purviewCode:"plugin"},{title:"安全",icon:"anquan",id:"/setting/log",purviewCode:"security",children:[{title:"操作日志",icon:"rizhijilu",id:"/setting/log",purviewCode:"log"},{title:"备份与恢复",id:"/setting/backups"}]},{title:"应用",icon:"xukezheng",id:"/setting/version",children:[{title:"版本与许可证",id:"/setting/version"},{title:"应用访问权限",id:"/setting/product-auth"}]}],n=[{title:"基础数据",icon:"zu",id:"dev",children:[{title:"系统功能管理",icon:"modular",id:"/setting/systemFeature"},{title:"系统权限",icon:"modular",id:"/setting/baseSystemRole"},{title:"项目功能管理",icon:"modular",id:"/setting/privilege"},{title:"项目权限",icon:"modular",id:"/setting/role"},{title:"消息发送方式",id:"/setting/messageSendTypeBase"},{title:"消息通知方案",id:"/setting/message-notice-base"},{title:"消息类型管理",id:"/setting/messageType"},{title:"日志模板",icon:"modular",id:"/setting/logTemplate"},{title:"日志类型",icon:"modular",id:"/setting/logType"}]}];return r.a.createElement(p,y({settingMenu:function(){try{return IS_DEV?[].concat(t,n):[].concat(t)}catch(e){return[].concat(t)}}},e,{__source:{fileName:"D:\\a-dk-web\\thoughtware-teston-ui\\src\\setting\\system\\SystemContent.js",lineNumber:166,columnNumber:9}}))}},630:function(e,t,n){"use strict";function i(e,t){return a.createElement(u.a,Object(r.a)(Object(r.a)({},e),{},{ref:t,icon:l}))}var r=n(6),a=n(0),l={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M890.5 755.3L537.9 269.2c-12.8-17.6-39-17.6-51.7 0L133.5 755.3A8 8 0 00140 768h75c5.1 0 9.9-2.5 12.9-6.6L512 369.8l284.1 391.6c3 4.1 7.8 6.6 12.9 6.6h75c6.5 0 10.3-7.4 6.5-12.7z"}}]},name:"up",theme:"outlined"},u=n(34);i.displayName="UpOutlined",t.a=a.forwardRef(i)},972:function(e,t,n){}}]);