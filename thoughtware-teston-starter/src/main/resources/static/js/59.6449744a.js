(window.webpackJsonp=window.webpackJsonp||[]).push([[59],{684:function(e,t,r){"use strict";r(644);var n=r(643),a=r(0),o=r.n(a),i=r(679),c=(r(685),r(752)),u=r(50),l=(r(705),"D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\CaseBread.js");t.a=function(e){var t=e.icon,r=e.style,a=e.toggleCase,s=e.setOpen,m=e.breadItem,f=e.right,p=e.router,h=Object(u.g)();return o.a.createElement("div",{className:"breadcrumb-title_between",style:r,__source:{fileName:l,lineNumber:43,columnNumber:9}},o.a.createElement("div",{className:"breadcrumb-left",__source:{fileName:l,lineNumber:44,columnNumber:13}},t?o.a.createElement(i.a,{icon:t,className:"icon-s ",style:{margin:"3px 5px 0"},__source:{fileName:l,lineNumber:17,columnNumber:17}}):o.a.createElement(c.a,{onClick:function(){return h.push(p)},style:{cursor:"pointer",fontSize:"20px"},__source:{fileName:l,lineNumber:24,columnNumber:20}}),o.a.createElement(n.a,{style:{fontWeight:"bold"},__source:{fileName:l,lineNumber:48,columnNumber:17}},m.map((function(e,t){return o.a.createElement(n.a.Item,{key:t,__source:{fileName:l,lineNumber:38,columnNumber:20}},e)}))),a),f?o.a.createElement(o.a.Fragment,null,f):null,s?o.a.createElement(i.a,{className:"icon-s edit-icon",icon:"shanchu2",onClick:function(){return s(!1)},__source:{fileName:l,lineNumber:60,columnNumber:22}}):null)}},705:function(e,t,r){"use strict";var n=r(0),a=r.n(n),o=r(685),i=r(63),c=r(702),u=r(709),l=r(7),s=r(50),m=r(679),f="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\testcase\\components\\ToggleCase.js";function p(e,t){var r,n=Object.keys(e);return Object.getOwnPropertySymbols&&(r=Object.getOwnPropertySymbols(e),t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)),n}function h(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?p(Object(r),!0).forEach((function(t){var n,a;n=e,a=r[t=t],t in n?Object.defineProperty(n,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):n[t]=a})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):p(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function d(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,a,o=[],i=!0,c=!1;try{for(r=r.call(e);!(i=(n=r.next()).done)&&(o.push(n.value),!t||o.length!==t);i=!0);}catch(e){c=!0,a=e}finally{try{i||null==r.return||r.return()}finally{if(c)throw a}}return o}}(e,t)||function(e,t){var r;if(e)return"string"==typeof e?b(e,t):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?b(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function b(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}t.a=Object(i.inject)("testcaseStore")(Object(i.observer)((function(e){function t(e){e=h({pageParam:{pageSize:E,currentPage:1},repositoryId:S},e),N(e).then((function(e){g(e.dataList),w(e.totalPage)}))}function r(e,t){var r={repository:{id:S},user:{id:Object(l.getUser)().userId},testCase:{id:t.id}};v(r),sessionStorage.setItem("".concat(e),t.id),j.push("/repository/".concat(t.caseType,"/").concat(t.id)),y(!b)}var i=e.testcaseStore,p=e.caseId,b=(e=d(Object(n.useState)(!1),2))[0],y=e[1],N=i.findTestCaseList,v=(i.testcaseList,i.testCaseRecent),g=(i=(e=d(Object(n.useState)([]),2))[0],e[1]),_=(e=d(Object(n.useState)(),2))[0],w=e[1],E=d(Object(n.useState)(10),1)[0],O=(e=d(Object(n.useState)(1),2))[0],x=e[1],S=sessionStorage.getItem("repositoryId"),j=Object(s.g)(),L=Object(n.useRef)(null);return Object(n.useEffect)((function(){function e(e){L.current&&!L.current.contains(e.target)&&y(!1)}return document.addEventListener("click",e),function(){document.removeEventListener("click",e)}}),[]),a.a.createElement("div",{className:"case-toggle",ref:L,__source:{fileName:f,lineNumber:129,columnNumber:9}},a.a.createElement("div",{onClick:function(){y(!b),t()},style:{cursor:"pointer",margin:"5px 0 0"},__source:{fileName:f,lineNumber:130,columnNumber:13}},a.a.createElement(m.a,{className:"icon-s",icon:"xiala",__source:{fileName:f,lineNumber:134,columnNumber:17}})),a.a.createElement("div",{className:"case-toggle-title ".concat(!1===b?"teston-hide":"teston-show"),__source:{fileName:f,lineNumber:140,columnNumber:13}},i&&i.map((function(e,t){return a.a.createElement("div",{key:e.id,className:"\n                            display-flex-between \n                            toggle-case-item \n                            ".concat(p===e.id?"toggle-case-item-selected":"","\n                            "),onClick:function(){var t=e;switch(t.caseType){case u.a.API_UNIT:r("apiUnitId",t);break;case u.a.API_SCENE:r("apiSceneId",t);break;case u.a.API_PERFORM:r("apiPerfId",t);break;case u.a.WEB_SCENE:r("webSceneId",t);break;case u.a.WEB_PERFORM:r("webPerfId",t);break;case u.a.APP_SCENE:r("appSceneId",t);break;case u.a.APP_PERFORM:r("appPerfId",t);break;case u.a.FUNCTION:r("functionId",t)}},__source:{fileName:f,lineNumber:144,columnNumber:29}},a.a.createElement("span",{className:"text-ellipsis",__source:{fileName:f,lineNumber:153,columnNumber:33}},e.name),Object(o.a)(e.caseType))})),a.a.createElement(c.a,{currentPage:O,totalPage:_,changePage:function(e){x(e),t({pageParam:{pageSize:E,currentPage:e}})},__source:{fileName:f,lineNumber:161,columnNumber:17}})))})))},734:function(e,t,r){"use strict";r(138);var n=r(82),a=r(0),o=r.n(a),i=r(105),c="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\common\\UIResultCommon.js";t.a=function(e){var t=e.spinning,r=e.instanceInfo;e=e.showList;return o.a.createElement("div",{style:{height:"calc(100% - 50px)"},__source:{fileName:c,lineNumber:9,columnNumber:9}},o.a.createElement(n.a,{spinning:t,__source:{fileName:c,lineNumber:10,columnNumber:13}},o.a.createElement("div",{className:"unit-instance-detail",__source:{fileName:c,lineNumber:11,columnNumber:17}},o.a.createElement("div",{className:"header-item",__source:{fileName:c,lineNumber:12,columnNumber:21}},"步骤总详情"),o.a.createElement("div",{style:{padding:"10px 0 "},__source:{fileName:c,lineNumber:13,columnNumber:21}},o.a.createElement("div",{className:"history-detail-all-box",__source:{fileName:c,lineNumber:14,columnNumber:25}},o.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:c,lineNumber:15,columnNumber:29}},"start"===(null==r||null==(t=r.instance)?void 0:t.status)?o.a.createElement(o.a.Fragment,null,o.a.createElement("div",{__source:{fileName:c,lineNumber:19,columnNumber:45}},"状态"),o.a.createElement(n.a,{indicator:o.a.createElement(i.a,{style:{fontSize:24,margin:"15px 40px"},spin:!0,__source:{fileName:c,lineNumber:20,columnNumber:62}}),__source:{fileName:c,lineNumber:20,columnNumber:45}})):o.a.createElement(o.a.Fragment,null,o.a.createElement("div",{__source:{fileName:c,lineNumber:24,columnNumber:45}},"测试结果"),o.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:c,lineNumber:25,columnNumber:45}},"success"===(null==r||null==(t=r.instance)?void 0:t.status)&&"成功","fail"===(null==r||null==(t=r.instance)?void 0:t.status)&&"失败"))),o.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:c,lineNumber:37,columnNumber:29}},o.a.createElement("div",{__source:{fileName:c,lineNumber:38,columnNumber:33}},"步骤数"),o.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:c,lineNumber:39,columnNumber:33}},null==r?void 0:r.stepNum)),o.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:c,lineNumber:41,columnNumber:29}},o.a.createElement("div",{__source:{fileName:c,lineNumber:42,columnNumber:33}},"通过率"),o.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:c,lineNumber:43,columnNumber:33}},null==r?void 0:r.passRate)),o.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:c,lineNumber:45,columnNumber:29}},o.a.createElement("div",{__source:{fileName:c,lineNumber:46,columnNumber:33}},"通过数"),o.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:c,lineNumber:47,columnNumber:33}},null==r?void 0:r.passNum)),o.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:c,lineNumber:49,columnNumber:29}},o.a.createElement("div",{__source:{fileName:c,lineNumber:50,columnNumber:33}},"未通过"),o.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:c,lineNumber:51,columnNumber:33}},null==r?void 0:r.failNum)))),o.a.createElement("div",{className:"header-item",__source:{fileName:c,lineNumber:56,columnNumber:21}},"步骤列表"),o.a.createElement("div",{className:"table-list-box",style:{margin:"10px"},__source:{fileName:c,lineNumber:57,columnNumber:21}},e()))))}},752:function(e,t,r){"use strict";function n(e,t){return o.createElement(c.a,Object(a.a)(Object(a.a)({},e),{},{ref:t,icon:i}))}var a=r(3),o=r(0),i={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M872 474H286.9l350.2-304c5.6-4.9 2.2-14-5.2-14h-88.5c-3.9 0-7.6 1.4-10.5 3.9L155 487.8a31.96 31.96 0 000 48.3L535.1 866c1.5 1.3 3.3 2 5.2 2h91.5c7.4 0 10.8-9.2 5.2-14L286.9 550H872c4.4 0 8-3.6 8-8v-60c0-4.4-3.6-8-8-8z"}}]},name:"arrow-left",theme:"outlined"},c=r(11);n.displayName="ArrowLeftOutlined",t.a=o.forwardRef(n)},819:function(e,t,r){"use strict";r.r(t),r(396);var n,a=r(395),o=(r(72),r(40)),i=(r(137),r(81)),c=(r(73),r(35)),u=(r(645),r(646)),l=(r(146),r(85)),s=r(0),m=r.n(s),f=r(63),p=r(8),h=r(7);function d(e){return(d="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function b(){b=function(){return e};var e={},t=Object.prototype,r=t.hasOwnProperty,n=(p="function"==typeof Symbol?Symbol:{}).iterator||"@@iterator",a=p.asyncIterator||"@@asyncIterator",o=p.toStringTag||"@@toStringTag";function i(e,t,r){return Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{i({},"")}catch(t){i=function(e,t,r){return e[t]=r}}function c(e,t,r,n){var a,o,i,c;t=t&&t.prototype instanceof s?t:s,t=Object.create(t.prototype),n=new w(n||[]);return t._invoke=(a=e,o=r,i=n,c="suspendedStart",function(e,t){if("executing"===c)throw new Error("Generator is already running");if("completed"===c){if("throw"===e)throw t;return{value:void 0,done:!0}}for(i.method=e,i.arg=t;;){var r=i.delegate;if(r&&(r=function e(t,r){var n=t.iterator[r.method];if(void 0===n){if(r.delegate=null,"throw"===r.method){if(t.iterator.return&&(r.method="return",r.arg=void 0,e(t,r),"throw"===r.method))return l;r.method="throw",r.arg=new TypeError("The iterator does not provide a 'throw' method")}return l}return"throw"===(n=u(n,t.iterator,r.arg)).type?(r.method="throw",r.arg=n.arg,r.delegate=null,l):(n=n.arg)?n.done?(r[t.resultName]=n.value,r.next=t.nextLoc,"return"!==r.method&&(r.method="next",r.arg=void 0),r.delegate=null,l):n:(r.method="throw",r.arg=new TypeError("iterator result is not an object"),r.delegate=null,l)}(r,i))){if(r===l)continue;return r}if("next"===i.method)i.sent=i._sent=i.arg;else if("throw"===i.method){if("suspendedStart"===c)throw c="completed",i.arg;i.dispatchException(i.arg)}else"return"===i.method&&i.abrupt("return",i.arg);if(c="executing","normal"===(r=u(a,o,i)).type){if(c=i.done?"completed":"suspendedYield",r.arg===l)continue;return{value:r.arg,done:i.done}}"throw"===r.type&&(c="completed",i.method="throw",i.arg=r.arg)}}),t}function u(e,t,r){try{return{type:"normal",arg:e.call(t,r)}}catch(e){return{type:"throw",arg:e}}}e.wrap=c;var l={};function s(){}function m(){}function f(){}var p,h,y=((h=(h=(i(p={},n,(function(){return this})),Object.getPrototypeOf))&&h(h(E([]))))&&h!==t&&r.call(h,n)&&(p=h),f.prototype=s.prototype=Object.create(p));function N(e){["next","throw","return"].forEach((function(t){i(e,t,(function(e){return this._invoke(t,e)}))}))}function v(e,t){var n;this._invoke=function(a,o){function i(){return new t((function(n,i){!function n(a,o,i,c){var l;if("throw"!==(a=u(e[a],e,o)).type)return(o=(l=a.arg).value)&&"object"==d(o)&&r.call(o,"__await")?t.resolve(o.__await).then((function(e){n("next",e,i,c)}),(function(e){n("throw",e,i,c)})):t.resolve(o).then((function(e){l.value=e,i(l)}),(function(e){return n("throw",e,i,c)}));c(a.arg)}(a,o,n,i)}))}return n=n?n.then(i,i):i()}}function g(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function _(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function w(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(g,this),this.reset(!0)}function E(e){if(e){var t,a=e[n];if(a)return a.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length))return t=-1,(a=function n(){for(;++t<e.length;)if(r.call(e,t))return n.value=e[t],n.done=!1,n;return n.value=void 0,n.done=!0,n}).next=a}return{next:O}}function O(){return{value:void 0,done:!0}}return i(y,"constructor",m.prototype=f),i(f,"constructor",m),m.displayName=i(f,o,"GeneratorFunction"),e.isGeneratorFunction=function(e){return!!(e="function"==typeof e&&e.constructor)&&(e===m||"GeneratorFunction"===(e.displayName||e.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,f):(e.__proto__=f,i(e,o,"GeneratorFunction")),e.prototype=Object.create(y),e},e.awrap=function(e){return{__await:e}},N(v.prototype),i(v.prototype,a,(function(){return this})),e.AsyncIterator=v,e.async=function(t,r,n,a,o){void 0===o&&(o=Promise);var i=new v(c(t,r,n,a),o);return e.isGeneratorFunction(r)?i:i.next().then((function(e){return e.done?e.value:i.next()}))},N(y),i(y,o,"Generator"),i(y,n,(function(){return this})),i(y,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t,r=[];for(t in e)r.push(t);return r.reverse(),function t(){for(;r.length;){var n=r.pop();if(n in e)return t.value=n,t.done=!1,t}return t.done=!0,t}},e.values=E,w.prototype={constructor:w,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(_),!e)for(var t in this)"t"===t.charAt(0)&&r.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function n(r,n){return i.type="throw",i.arg=e,t.next=r,n&&(t.method="next",t.arg=void 0),!!n}for(var a=this.tryEntries.length-1;0<=a;--a){var o=this.tryEntries[a],i=o.completion;if("root"===o.tryLoc)return n("end");if(o.tryLoc<=this.prev){var c=r.call(o,"catchLoc"),u=r.call(o,"finallyLoc");if(c&&u){if(this.prev<o.catchLoc)return n(o.catchLoc,!0);if(this.prev<o.finallyLoc)return n(o.finallyLoc)}else if(c){if(this.prev<o.catchLoc)return n(o.catchLoc,!0)}else{if(!u)throw new Error("try statement without catch or finally");if(this.prev<o.finallyLoc)return n(o.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;0<=n;--n){var a=this.tryEntries[n];if(a.tryLoc<=this.prev&&r.call(a,"finallyLoc")&&this.prev<a.finallyLoc){var o=a;break}}var i=(o=o&&("break"===e||"continue"===e)&&o.tryLoc<=t&&t<=o.finallyLoc?null:o)?o.completion:{};return i.type=e,i.arg=t,o?(this.method="next",this.next=o.finallyLoc,l):this.complete(i)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),l},finish:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r=this.tryEntries[t];if(r.finallyLoc===e)return this.complete(r.completion,r.afterLoc),_(r),l}},catch:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r,n,a=this.tryEntries[t];if(a.tryLoc===e)return"throw"===(r=a.completion).type&&(n=r.arg,_(a)),n}throw new Error("illegal catch attempt")},delegateYield:function(e,t,r){return this.delegate={iterator:E(e),resultName:t,nextLoc:r},"next"===this.method&&(this.arg=void 0),l}},e}function y(e,t){var r,n=Object.keys(e);return Object.getOwnPropertySymbols&&(r=Object.getOwnPropertySymbols(e),t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)),n}function N(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?y(Object(r),!0).forEach((function(t){var n,a;n=e,a=r[t=t],t in n?Object.defineProperty(n,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):n[t]=a})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):y(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function v(e,t,r,n,a,o,i){try{var c=e[o](i),u=c.value}catch(e){return void r(e)}c.done?t(u):Promise.resolve(u).then(n,a)}function g(e){return function(){var t=this,r=arguments;return new Promise((function(n,a){var o=e.apply(t,r);function i(e){v(o,n,a,i,c,"next",e)}function c(e){v(o,n,a,i,c,"throw",e)}i(void 0)}))}}function _(e,t,r,n){r&&Object.defineProperty(e,t,{enumerable:r.enumerable,configurable:r.configurable,writable:r.writable,value:r.initializer?r.initializer.call(n):void 0})}function w(e,t){for(var r=0;r<t.length;r++){var n=t[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}function E(e,t,r,n,a){var o={};return Object.keys(n).forEach((function(e){o[e]=n[e]})),o.enumerable=!!o.enumerable,o.configurable=!!o.configurable,("value"in o||o.initializer)&&(o.writable=!0),o=r.slice().reverse().reduce((function(r,n){return n(e,t,r)||r}),o),a&&void 0!==o.initializer&&(o.value=o.initializer?o.initializer.call(a):void 0,o.initializer=void 0),void 0===o.initializer&&(Object.defineProperty(e,t,o),o=null),o}var O=E((n=function(e,t,r){return t&&w(e.prototype,t),r&&w(e,r),Object.defineProperty(e,"prototype",{writable:!1}),e}((function e(){if(!(this instanceof e))throw new TypeError("Cannot call a class as a function");_(this,"appSceneInstanceList",O,this),_(this,"params",x,this),_(this,"findAppSceneInstancePage",S,this),_(this,"findAppSceneInstance",j,this)}))).prototype,"appSceneInstanceList",[p.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return[]}}),x=E(n.prototype,"params",[p.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:null}),S=E(n.prototype,"findAppSceneInstancePage",[p.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(){var t=g(b().mark((function t(r){var n;return b().wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return n=N(N({},r),{},{type:"app-scene",orderParams:[{name:"createTime",orderType:"desc"}]}),t.next=3,h.Axios.post("/instance/findInstancePage",n);case 3:return 0===(n=t.sent).code&&(e.appSceneInstanceList=n.data.dataList),t.abrupt("return",n);case 6:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()}}),j=E(n.prototype,"findAppSceneInstance",[p.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=g(b().mark((function e(t){var r;return b().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return(r=new FormData).append("id",t),e.next=4,h.Axios.post("/appSceneInstance/findAppSceneInstance",r);case 4:if(0===(r=e.sent).code)return e.abrupt("return",r.data);e.next=7;break;case 7:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),L=new n,P=r(734),I=r(684),k=r(397);function A(e){return(A="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}var T="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\app\\scene\\components\\AppSceneInstanceSinglePage.js";function z(){z=function(){return e};var e={},t=Object.prototype,r=t.hasOwnProperty,n=(p="function"==typeof Symbol?Symbol:{}).iterator||"@@iterator",a=p.asyncIterator||"@@asyncIterator",o=p.toStringTag||"@@toStringTag";function i(e,t,r){return Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{i({},"")}catch(t){i=function(e,t,r){return e[t]=r}}function c(e,t,r,n){var a,o,i,c;t=t&&t.prototype instanceof s?t:s,t=Object.create(t.prototype),n=new g(n||[]);return t._invoke=(a=e,o=r,i=n,c="suspendedStart",function(e,t){if("executing"===c)throw new Error("Generator is already running");if("completed"===c){if("throw"===e)throw t;return{value:void 0,done:!0}}for(i.method=e,i.arg=t;;){var r=i.delegate;if(r&&(r=function e(t,r){var n=t.iterator[r.method];if(void 0===n){if(r.delegate=null,"throw"===r.method){if(t.iterator.return&&(r.method="return",r.arg=void 0,e(t,r),"throw"===r.method))return l;r.method="throw",r.arg=new TypeError("The iterator does not provide a 'throw' method")}return l}return"throw"===(n=u(n,t.iterator,r.arg)).type?(r.method="throw",r.arg=n.arg,r.delegate=null,l):(n=n.arg)?n.done?(r[t.resultName]=n.value,r.next=t.nextLoc,"return"!==r.method&&(r.method="next",r.arg=void 0),r.delegate=null,l):n:(r.method="throw",r.arg=new TypeError("iterator result is not an object"),r.delegate=null,l)}(r,i))){if(r===l)continue;return r}if("next"===i.method)i.sent=i._sent=i.arg;else if("throw"===i.method){if("suspendedStart"===c)throw c="completed",i.arg;i.dispatchException(i.arg)}else"return"===i.method&&i.abrupt("return",i.arg);if(c="executing","normal"===(r=u(a,o,i)).type){if(c=i.done?"completed":"suspendedYield",r.arg===l)continue;return{value:r.arg,done:i.done}}"throw"===r.type&&(c="completed",i.method="throw",i.arg=r.arg)}}),t}function u(e,t,r){try{return{type:"normal",arg:e.call(t,r)}}catch(e){return{type:"throw",arg:e}}}e.wrap=c;var l={};function s(){}function m(){}function f(){}var p,h,d=((h=(h=(i(p={},n,(function(){return this})),Object.getPrototypeOf))&&h(h(_([]))))&&h!==t&&r.call(h,n)&&(p=h),f.prototype=s.prototype=Object.create(p));function b(e){["next","throw","return"].forEach((function(t){i(e,t,(function(e){return this._invoke(t,e)}))}))}function y(e,t){var n;this._invoke=function(a,o){function i(){return new t((function(n,i){!function n(a,o,i,c){var l;if("throw"!==(a=u(e[a],e,o)).type)return(o=(l=a.arg).value)&&"object"==A(o)&&r.call(o,"__await")?t.resolve(o.__await).then((function(e){n("next",e,i,c)}),(function(e){n("throw",e,i,c)})):t.resolve(o).then((function(e){l.value=e,i(l)}),(function(e){return n("throw",e,i,c)}));c(a.arg)}(a,o,n,i)}))}return n=n?n.then(i,i):i()}}function N(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function v(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function g(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(N,this),this.reset(!0)}function _(e){if(e){var t,a=e[n];if(a)return a.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length))return t=-1,(a=function n(){for(;++t<e.length;)if(r.call(e,t))return n.value=e[t],n.done=!1,n;return n.value=void 0,n.done=!0,n}).next=a}return{next:w}}function w(){return{value:void 0,done:!0}}return i(d,"constructor",m.prototype=f),i(f,"constructor",m),m.displayName=i(f,o,"GeneratorFunction"),e.isGeneratorFunction=function(e){return!!(e="function"==typeof e&&e.constructor)&&(e===m||"GeneratorFunction"===(e.displayName||e.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,f):(e.__proto__=f,i(e,o,"GeneratorFunction")),e.prototype=Object.create(d),e},e.awrap=function(e){return{__await:e}},b(y.prototype),i(y.prototype,a,(function(){return this})),e.AsyncIterator=y,e.async=function(t,r,n,a,o){void 0===o&&(o=Promise);var i=new y(c(t,r,n,a),o);return e.isGeneratorFunction(r)?i:i.next().then((function(e){return e.done?e.value:i.next()}))},b(d),i(d,o,"Generator"),i(d,n,(function(){return this})),i(d,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t,r=[];for(t in e)r.push(t);return r.reverse(),function t(){for(;r.length;){var n=r.pop();if(n in e)return t.value=n,t.done=!1,t}return t.done=!0,t}},e.values=_,g.prototype={constructor:g,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(v),!e)for(var t in this)"t"===t.charAt(0)&&r.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function n(r,n){return i.type="throw",i.arg=e,t.next=r,n&&(t.method="next",t.arg=void 0),!!n}for(var a=this.tryEntries.length-1;0<=a;--a){var o=this.tryEntries[a],i=o.completion;if("root"===o.tryLoc)return n("end");if(o.tryLoc<=this.prev){var c=r.call(o,"catchLoc"),u=r.call(o,"finallyLoc");if(c&&u){if(this.prev<o.catchLoc)return n(o.catchLoc,!0);if(this.prev<o.finallyLoc)return n(o.finallyLoc)}else if(c){if(this.prev<o.catchLoc)return n(o.catchLoc,!0)}else{if(!u)throw new Error("try statement without catch or finally");if(this.prev<o.finallyLoc)return n(o.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;0<=n;--n){var a=this.tryEntries[n];if(a.tryLoc<=this.prev&&r.call(a,"finallyLoc")&&this.prev<a.finallyLoc){var o=a;break}}var i=(o=o&&("break"===e||"continue"===e)&&o.tryLoc<=t&&t<=o.finallyLoc?null:o)?o.completion:{};return i.type=e,i.arg=t,o?(this.method="next",this.next=o.finallyLoc,l):this.complete(i)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),l},finish:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r=this.tryEntries[t];if(r.finallyLoc===e)return this.complete(r.completion,r.afterLoc),v(r),l}},catch:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r,n,a=this.tryEntries[t];if(a.tryLoc===e)return"throw"===(r=a.completion).type&&(n=r.arg,v(a)),n}throw new Error("illegal catch attempt")},delegateYield:function(e,t,r){return this.delegate={iterator:_(e),resultName:t,nextLoc:r},"next"===this.method&&(this.arg=void 0),l}},e}function C(e,t,r,n,a,o,i){try{var c=e[o](i),u=c.value}catch(e){return void r(e)}c.done?t(u):Promise.resolve(u).then(n,a)}function F(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,a,o=[],i=!0,c=!1;try{for(r=r.call(e);!(i=(n=r.next()).done)&&(o.push(n.value),!t||o.length!==t);i=!0);}catch(e){c=!0,a=e}finally{try{i||null==r.return||r.return()}finally{if(c)throw a}}return o}}(e,t)||function(e,t){var r;if(e)return"string"==typeof e?G(e,t):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?G(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function G(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}t.default=Object(f.observer)((function(e){var t=e.appSceneInstanceId,r=(e=e.name,L.findAppSceneInstance),n=(v=F(Object(s.useState)(!0),2))[0],f=v[1],p=(v=F(Object(s.useState)([]),2))[0],h=v[1],d=(v=F(Object(s.useState)(),2))[0],b=v[1],y=(v=F(Object(s.useState)(!1),2))[0],N=v[1],v=function(){e=z().mark((function e(){var n;return z().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,r(t);case 2:n=e.sent,b(n),h(n.stepList),f(!1),N(!0);case 7:case"end":return e.stop()}}),e)}));var e,n=function(){var t=this,r=arguments;return new Promise((function(n,a){var o=e.apply(t,r);function i(e){C(o,n,a,i,c,"next",e)}function c(e){C(o,n,a,i,c,"throw",e)}i(void 0)}))};return function(){return n.apply(this,arguments)}}(),g=function(e){return 0===e?m.a.createElement(i.default,{color:"error",__source:{fileName:T,lineNumber:132,columnNumber:21}},"未通过"):1===e?m.a.createElement(i.default,{color:"success",__source:{fileName:T,lineNumber:136,columnNumber:21}},"通过"):2===e?m.a.createElement(i.default,{color:"default",__source:{fileName:T,lineNumber:140,columnNumber:21}},"未执行"):void 0};return m.a.createElement(m.a.Fragment,null,m.a.createElement("span",{className:"link-text",onClick:v,__source:{fileName:T,lineNumber:147,columnNumber:13}},e),m.a.createElement(a.default,{placement:"right",onClose:function(){f(!0),h([]),N(!1)},open:y,width:900,destroyOnClose:!0,maskStyle:{background:"transparent"},contentWrapperStyle:{top:48,height:"calc(100% - 50px)"},closable:!1,__source:{fileName:T,lineNumber:148,columnNumber:13}},m.a.createElement(I.a,{breadItem:["历史详情"],icon:"api1",setOpen:N,__source:{fileName:T,lineNumber:158,columnNumber:17}}),m.a.createElement(P.a,{spinning:n,instanceInfo:d,showList:function(){return m.a.createElement(u.b,{className:"demo-loadmore-list",itemLayout:"horizontal",dataSource:p,locale:{emptyText:m.a.createElement(l.default,{imageStyle:{height:120},description:m.a.createElement("span",{__source:{fileName:T,lineNumber:43,columnNumber:34}},"暂无历史步骤"),image:k.default,__source:{fileName:T,lineNumber:41,columnNumber:28}})},renderItem:function(e){return m.a.createElement(u.b.Item,{style:{padding:0},__source:{fileName:T,lineNumber:48,columnNumber:17}},"app-scene"===e.type?function(e,t){return m.a.createElement(o.default,{className:"step-item-content",style:{width:"100%"},__source:{fileName:T,lineNumber:78,columnNumber:9}},m.a.createElement(c.default,{span:1,__source:{fileName:T,lineNumber:82,columnNumber:13}},m.a.createElement("div",{__source:{fileName:T,lineNumber:83,columnNumber:17}},t.sort)),m.a.createElement(c.default,{span:4,__source:{fileName:T,lineNumber:85,columnNumber:13}},m.a.createElement("div",{__source:{fileName:T,lineNumber:86,columnNumber:17}},null==e?void 0:e.name)),m.a.createElement(c.default,{span:15,__source:{fileName:T,lineNumber:88,columnNumber:13}},null!=e&&e.actionType?m.a.createElement("div",{className:"display-flex-gap",__source:{fileName:T,lineNumber:90,columnNumber:22}},m.a.createElement("div",{style:{fontSize:"12px",color:"#aaa"},__source:{fileName:T,lineNumber:91,columnNumber:25}},"操作: "),m.a.createElement("div",{__source:{fileName:T,lineNumber:92,columnNumber:25}},null==e?void 0:e.actionType),null!=e&&e.parameter?m.a.createElement(m.a.Fragment,null,m.a.createElement("div",{style:{fontSize:"12px",color:"#aaa"},__source:{fileName:T,lineNumber:97,columnNumber:37}},"参数: "),m.a.createElement("div",{__source:{fileName:T,lineNumber:98,columnNumber:37}},null==e?void 0:e.parameter)):null):null,null!=e&&e.location?m.a.createElement("div",{className:"display-flex-gap ",__source:{fileName:T,lineNumber:108,columnNumber:22}},m.a.createElement("div",{style:{fontSize:"12px",color:"#aaa"},__source:{fileName:T,lineNumber:109,columnNumber:25}},"定位: "),m.a.createElement("div",{__source:{fileName:T,lineNumber:110,columnNumber:25}},null==e?void 0:e.location),null!=e&&e.locationValue?m.a.createElement(m.a.Fragment,null,m.a.createElement("div",{style:{fontSize:"12px",color:"#aaa"},__source:{fileName:T,lineNumber:114,columnNumber:37}},"参数: "),m.a.createElement("div",{__source:{fileName:T,lineNumber:115,columnNumber:37}},null==e?void 0:e.locationValue)):null):null),m.a.createElement(c.default,{style:{marginLeft:"auto",height:"20px"},__source:{fileName:T,lineNumber:124,columnNumber:13}},m.a.createElement("div",{__source:{fileName:T,lineNumber:125,columnNumber:17}},g(t.result))))}(e.appSceneInstanceStep,e):function(e){return m.a.createElement(o.default,{className:"step-item-content",style:{width:"100%"},__source:{fileName:T,lineNumber:61,columnNumber:9}},m.a.createElement(c.default,{span:1,__source:{fileName:T,lineNumber:65,columnNumber:13}},m.a.createElement("div",{__source:{fileName:T,lineNumber:66,columnNumber:17}},e.sort)),m.a.createElement(c.default,{span:4,__source:{fileName:T,lineNumber:68,columnNumber:13}}),m.a.createElement(c.default,{span:15,__source:{fileName:T,lineNumber:70,columnNumber:13}},m.a.createElement(i.default,{color:"processing",__source:{fileName:T,lineNumber:70,columnNumber:28}},"if 条件判断")),m.a.createElement(c.default,{style:{marginLeft:"auto",height:"20px"},__source:{fileName:T,lineNumber:71,columnNumber:13}},m.a.createElement("div",{__source:{fileName:T,lineNumber:72,columnNumber:17}},g(e.result))))}(e))},__source:{fileName:T,lineNumber:35,columnNumber:9}})},__source:{fileName:T,lineNumber:163,columnNumber:17}})))}))}}]);