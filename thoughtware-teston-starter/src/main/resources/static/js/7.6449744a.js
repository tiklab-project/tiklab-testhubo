(window.webpackJsonp=window.webpackJsonp||[]).push([[7],{684:function(e,t,n){"use strict";n(644);var a=n(643),r=n(0),c=n.n(r),o=n(679),u=(n(685),n(752)),i=n(50),l=(n(705),"D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\CaseBread.js");t.a=function(e){var t=e.icon,n=e.style,r=e.toggleCase,s=e.setOpen,m=e.breadItem,b=e.right,f=e.router,N=Object(i.g)();return c.a.createElement("div",{className:"breadcrumb-title_between",style:n,__source:{fileName:l,lineNumber:43,columnNumber:9}},c.a.createElement("div",{className:"breadcrumb-left",__source:{fileName:l,lineNumber:44,columnNumber:13}},t?c.a.createElement(o.a,{icon:t,className:"icon-s ",style:{margin:"3px 5px 0"},__source:{fileName:l,lineNumber:17,columnNumber:17}}):c.a.createElement(u.a,{onClick:function(){return N.push(f)},style:{cursor:"pointer",fontSize:"20px"},__source:{fileName:l,lineNumber:24,columnNumber:20}}),c.a.createElement(a.a,{style:{fontWeight:"bold"},__source:{fileName:l,lineNumber:48,columnNumber:17}},m.map((function(e,t){return c.a.createElement(a.a.Item,{key:t,__source:{fileName:l,lineNumber:38,columnNumber:20}},e)}))),r),b?c.a.createElement(c.a.Fragment,null,b):null,s?c.a.createElement(o.a,{className:"icon-s edit-icon",icon:"shanchu2",onClick:function(){return s(!1)},__source:{fileName:l,lineNumber:60,columnNumber:22}}):null)}},702:function(e,t,n){"use strict";var a=n(0),r=n.n(a),c=n(141),o=n(123),u="D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\pagination\\Page.js";t.a=function(e){var t=e.currentPage,n=e.changePage,a=e.totalPage;return r.a.createElement("div",{className:"pagination-box",__source:{fileName:u,lineNumber:12,columnNumber:12}},r.a.createElement("span",{className:"".concat(1===t?"pagination-box-ban":"pagination-box-allow"),onClick:function(){return 1===t?null:n(t-1)},__source:{fileName:u,lineNumber:13,columnNumber:17}},r.a.createElement(c.default,{__source:{fileName:u,lineNumber:17,columnNumber:21}})),r.a.createElement("span",{className:"pagination-box-current",__source:{fileName:u,lineNumber:19,columnNumber:17}},t),r.a.createElement("span",{__source:{fileName:u,lineNumber:20,columnNumber:17}}," / ",a),r.a.createElement("span",{className:"".concat(t===a?"pagination-box-ban":"pagination-box-allow"),onClick:function(){return t===a?null:n(t+1)},__source:{fileName:u,lineNumber:21,columnNumber:17}},r.a.createElement(o.default,{__source:{fileName:u,lineNumber:25,columnNumber:21}})))}},705:function(e,t,n){"use strict";var a=n(0),r=n.n(a),c=n(685),o=n(63),u=n(702),i=n(709),l=n(7),s=n(50),m=n(679),b="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\testcase\\components\\ToggleCase.js";function f(e,t){var n,a=Object.keys(e);return Object.getOwnPropertySymbols&&(n=Object.getOwnPropertySymbols(e),t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)),a}function N(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?f(Object(n),!0).forEach((function(t){var a,r;a=e,r=n[t=t],t in a?Object.defineProperty(a,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):a[t]=r})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):f(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function p(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var a,r,c=[],o=!0,u=!1;try{for(n=n.call(e);!(o=(a=n.next()).done)&&(c.push(a.value),!t||c.length!==t);o=!0);}catch(e){u=!0,r=e}finally{try{o||null==n.return||n.return()}finally{if(u)throw r}}return c}}(e,t)||function(e,t){var n;if(e)return"string"==typeof e?d(e,t):"Map"===(n="Object"===(n=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:n)||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?d(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function d(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,a=new Array(t);n<t;n++)a[n]=e[n];return a}t.a=Object(o.inject)("testcaseStore")(Object(o.observer)((function(e){function t(e){e=N({pageParam:{pageSize:P,currentPage:1},repositoryId:j},e),_(e).then((function(e){y(e.dataList),O(e.totalPage)}))}function n(e,t){var n={repository:{id:j},user:{id:Object(l.getUser)().userId},testCase:{id:t.id}};E(n),sessionStorage.setItem("".concat(e),t.id),I.push("/repository/".concat(t.caseType,"/").concat(t.id)),g(!d)}var o=e.testcaseStore,f=e.caseId,d=(e=p(Object(a.useState)(!1),2))[0],g=e[1],_=o.findTestCaseList,E=(o.testcaseList,o.testCaseRecent),y=(o=(e=p(Object(a.useState)([]),2))[0],e[1]),v=(e=p(Object(a.useState)(),2))[0],O=e[1],P=p(Object(a.useState)(10),1)[0],w=(e=p(Object(a.useState)(1),2))[0],h=e[1],j=sessionStorage.getItem("repositoryId"),I=Object(s.g)(),S=Object(a.useRef)(null);return Object(a.useEffect)((function(){function e(e){S.current&&!S.current.contains(e.target)&&g(!1)}return document.addEventListener("click",e),function(){document.removeEventListener("click",e)}}),[]),r.a.createElement("div",{className:"case-toggle",ref:S,__source:{fileName:b,lineNumber:129,columnNumber:9}},r.a.createElement("div",{onClick:function(){g(!d),t()},style:{cursor:"pointer",margin:"5px 0 0"},__source:{fileName:b,lineNumber:130,columnNumber:13}},r.a.createElement(m.a,{className:"icon-s",icon:"xiala",__source:{fileName:b,lineNumber:134,columnNumber:17}})),r.a.createElement("div",{className:"case-toggle-title ".concat(!1===d?"teston-hide":"teston-show"),__source:{fileName:b,lineNumber:140,columnNumber:13}},o&&o.map((function(e,t){return r.a.createElement("div",{key:e.id,className:"\n                            display-flex-between \n                            toggle-case-item \n                            ".concat(f===e.id?"toggle-case-item-selected":"","\n                            "),onClick:function(){var t=e;switch(t.caseType){case i.a.API_UNIT:n("apiUnitId",t);break;case i.a.API_SCENE:n("apiSceneId",t);break;case i.a.API_PERFORM:n("apiPerfId",t);break;case i.a.WEB_SCENE:n("webSceneId",t);break;case i.a.WEB_PERFORM:n("webPerfId",t);break;case i.a.APP_SCENE:n("appSceneId",t);break;case i.a.APP_PERFORM:n("appPerfId",t);break;case i.a.FUNCTION:n("functionId",t)}},__source:{fileName:b,lineNumber:144,columnNumber:29}},r.a.createElement("span",{className:"text-ellipsis",__source:{fileName:b,lineNumber:153,columnNumber:33}},e.name),Object(c.a)(e.caseType))})),r.a.createElement(u.a,{currentPage:w,totalPage:v,changePage:function(e){h(e),t({pageParam:{pageSize:P,currentPage:e}})},__source:{fileName:b,lineNumber:161,columnNumber:17}})))})))},709:function(e,t,n){"use strict";n.d(t,"a",(function(){return a}));var a={API_UNIT:"api-unit",API_SCENE:"api-scene",API_PERFORM:"api-perform",WEB_SCENE:"web-scene",WEB_PERFORM:"web-perform",APP_SCENE:"app-scene",APP_PERFORM:"app-perform",FUNCTION:"function"}},725:function(e,t,n){"use strict";n.d(t,"a",(function(){return c})),n(101);var a=n(37),r=(t=n(0),n.n(t)),c=function(e,t){var n={content:t||"保存失败",className:"pi-message-error",duration:1};return r.a.createElement(r.a.Fragment,null,void("success"===e?a.default.success({content:t||"保存成功",className:"pi-message-success",duration:1}):a.default.error(n)))}},752:function(e,t,n){"use strict";function a(e,t){return c.createElement(u.a,Object(r.a)(Object(r.a)({},e),{},{ref:t,icon:o}))}var r=n(3),c=n(0),o={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M872 474H286.9l350.2-304c5.6-4.9 2.2-14-5.2-14h-88.5c-3.9 0-7.6 1.4-10.5 3.9L155 487.8a31.96 31.96 0 000 48.3L535.1 866c1.5 1.3 3.3 2 5.2 2h91.5c7.4 0 10.8-9.2 5.2-14L286.9 550H872c4.4 0 8-3.6 8-8v-60c0-4.4-3.6-8-8-8z"}}]},name:"arrow-left",theme:"outlined"},u=n(11);a.displayName="ArrowLeftOutlined",t.a=c.forwardRef(a)},768:function(e,t,n){},769:function(e,t,n){},770:function(e,t,n){},829:function(e,t,n){"use strict";n(292);var a=n(291),r=n(0),c=n.n(r),o="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\common\\CaseContentCommon.js";t.a=function(e){var t=e.breadcrumb,n=e.tabBarExtraContent;e=e.tabItem;return c.a.createElement(c.a.Fragment,null,c.a.createElement("div",{className:" case-tabs-box",__source:{fileName:o,lineNumber:9,columnNumber:13}},t?c.a.createElement("div",{className:"breadcrumb-title_between",__source:{fileName:o,lineNumber:12,columnNumber:22}},t):null,c.a.createElement(a.default,{defaultActiveKey:"1",items:e,tabBarExtraContent:n,__source:{fileName:o,lineNumber:18,columnNumber:17}})))}}}]);