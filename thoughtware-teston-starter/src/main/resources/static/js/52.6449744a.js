(window.webpackJsonp=window.webpackJsonp||[]).push([[52,61],{679:function(e,t,r){"use strict";var n=r(0),o=r.n(n),a="D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\IconCommon.js";t.a=function(e){var t=e.icon,r=e.style,n=e.className;e=e.onClick;return o.a.createElement("svg",{style:r,className:n,"aria-hidden":"true",onClick:e,__source:{fileName:a,lineNumber:10,columnNumber:9}},o.a.createElement("use",{xlinkHref:"#icon-".concat(t),__source:{fileName:a,lineNumber:11,columnNumber:13}}))}},684:function(e,t,r){"use strict";r(644);var n=r(643),o=r(0),a=r.n(o),i=r(679),c=(r(685),r(752)),u=r(50),l=(r(705),"D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\CaseBread.js");t.a=function(e){var t=e.icon,r=e.style,o=e.toggleCase,s=e.setOpen,m=e.breadItem,f=e.right,p=e.router,h=Object(u.g)();return a.a.createElement("div",{className:"breadcrumb-title_between",style:r,__source:{fileName:l,lineNumber:43,columnNumber:9}},a.a.createElement("div",{className:"breadcrumb-left",__source:{fileName:l,lineNumber:44,columnNumber:13}},t?a.a.createElement(i.a,{icon:t,className:"icon-s ",style:{margin:"3px 5px 0"},__source:{fileName:l,lineNumber:17,columnNumber:17}}):a.a.createElement(c.a,{onClick:function(){return h.push(p)},style:{cursor:"pointer",fontSize:"20px"},__source:{fileName:l,lineNumber:24,columnNumber:20}}),a.a.createElement(n.a,{style:{fontWeight:"bold"},__source:{fileName:l,lineNumber:48,columnNumber:17}},m.map((function(e,t){return a.a.createElement(n.a.Item,{key:t,__source:{fileName:l,lineNumber:38,columnNumber:20}},e)}))),o),f?a.a.createElement(a.a.Fragment,null,f):null,s?a.a.createElement(i.a,{className:"icon-s edit-icon",icon:"shanchu2",onClick:function(){return s(!1)},__source:{fileName:l,lineNumber:60,columnNumber:22}}):null)}},685:function(e,t,r){"use strict";r.d(t,"d",(function(){return u})),r.d(t,"c",(function(){return l})),r.d(t,"a",(function(){return s})),r.d(t,"b",(function(){return m})),r(137);var n=r(81),o=r(0),a=r.n(o),i=(r(3),r(11),r(679)),c="D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\caseCommon\\CaseCommonFn.js",u=function(e){switch(e){case"api":return"接口";case"ui":return"UI";case"perform":return"性能";case"function":return"功能"}},l=function(e){switch(e){case"api-unit":return a.a.createElement(i.a,{icon:"api1",className:"icon-l",__source:{fileName:c,lineNumber:29,columnNumber:21}});case"api-scene":return a.a.createElement(i.a,{icon:"api1",className:"icon-l",__source:{fileName:c,lineNumber:35,columnNumber:21}});case"api-perform":return a.a.createElement(i.a,{icon:"api1",className:"icon-l",__source:{fileName:c,lineNumber:40,columnNumber:21}});case"web-scene":return a.a.createElement(i.a,{icon:"diannao1",className:"icon-l",__source:{fileName:c,lineNumber:46,columnNumber:20}});case"web-perform":return a.a.createElement(i.a,{icon:"diannao1",className:"icon-l",__source:{fileName:c,lineNumber:51,columnNumber:20}});case"app-scene":return a.a.createElement(i.a,{icon:"shouji1",className:"icon-l",__source:{fileName:c,lineNumber:57,columnNumber:20}});case"app-perform":return a.a.createElement(i.a,{icon:"shouji1",className:"icon-l",__source:{fileName:c,lineNumber:62,columnNumber:20}});default:return a.a.createElement(i.a,{icon:"gongneng1",className:"icon-l",__source:{fileName:c,lineNumber:67,columnNumber:20}})}},s=function(e){switch(e){case"api-unit":return a.a.createElement(n.default,{color:"green",__source:{fileName:c,lineNumber:102,columnNumber:20}},"接口单元");case"api-scene":return a.a.createElement(n.default,{color:"blue",__source:{fileName:c,lineNumber:104,columnNumber:20}},"接口场景");case"api-perform":return a.a.createElement(n.default,{color:"orange",__source:{fileName:c,lineNumber:106,columnNumber:20}},"接口性能");case"web-scene":return a.a.createElement(n.default,{color:"blue",__source:{fileName:c,lineNumber:109,columnNumber:20}},"WEB场景");case"web-perform":return a.a.createElement(n.default,{color:"orange",__source:{fileName:c,lineNumber:111,columnNumber:20}},"WEB性能");case"app-scene":return a.a.createElement(n.default,{color:"blue",__source:{fileName:c,lineNumber:114,columnNumber:20}},"APP场景");case"app-perform":return a.a.createElement(n.default,{color:"orange",__source:{fileName:c,lineNumber:116,columnNumber:20}},"APP性能");case"function":return a.a.createElement(n.default,{color:"#2db7f5",__source:{fileName:c,lineNumber:118,columnNumber:20}},"功能用例");default:return}},m=function(e){switch(e){case"api-unit":return"接口单元";case"api-scene":return"接口场景";case"api-perform":return"接口性能";case"web-scene":return"WEB场景";case"web-perform":return"WEB性能";case"app-scene":return"APP场景";case"app-perform":return"APP性能";case"function":return"功能用例"}}},702:function(e,t,r){"use strict";var n=r(0),o=r.n(n),a=r(141),i=r(123),c="D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\pagination\\Page.js";t.a=function(e){var t=e.currentPage,r=e.changePage,n=e.totalPage;return o.a.createElement("div",{className:"pagination-box",__source:{fileName:c,lineNumber:12,columnNumber:12}},o.a.createElement("span",{className:"".concat(1===t?"pagination-box-ban":"pagination-box-allow"),onClick:function(){return 1===t?null:r(t-1)},__source:{fileName:c,lineNumber:13,columnNumber:17}},o.a.createElement(a.default,{__source:{fileName:c,lineNumber:17,columnNumber:21}})),o.a.createElement("span",{className:"pagination-box-current",__source:{fileName:c,lineNumber:19,columnNumber:17}},t),o.a.createElement("span",{__source:{fileName:c,lineNumber:20,columnNumber:17}}," / ",n),o.a.createElement("span",{className:"".concat(t===n?"pagination-box-ban":"pagination-box-allow"),onClick:function(){return t===n?null:r(t+1)},__source:{fileName:c,lineNumber:21,columnNumber:17}},o.a.createElement(i.default,{__source:{fileName:c,lineNumber:25,columnNumber:21}})))}},705:function(e,t,r){"use strict";var n=r(0),o=r.n(n),a=r(685),i=r(63),c=r(702),u=r(709),l=r(7),s=r(50),m=r(679),f="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\testcase\\components\\ToggleCase.js";function p(e,t){var r,n=Object.keys(e);return Object.getOwnPropertySymbols&&(r=Object.getOwnPropertySymbols(e),t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)),n}function h(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?p(Object(r),!0).forEach((function(t){var n,o;n=e,o=r[t=t],t in n?Object.defineProperty(n,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):n[t]=o})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):p(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function b(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,a=[],i=!0,c=!1;try{for(r=r.call(e);!(i=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);i=!0);}catch(e){c=!0,o=e}finally{try{i||null==r.return||r.return()}finally{if(c)throw o}}return a}}(e,t)||function(e,t){var r;if(e)return"string"==typeof e?d(e,t):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?d(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function d(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}t.a=Object(i.inject)("testcaseStore")(Object(i.observer)((function(e){function t(e){e=h({pageParam:{pageSize:E,currentPage:1},repositoryId:j},e),v(e).then((function(e){g(e.dataList),_(e.totalPage)}))}function r(e,t){var r={repository:{id:j},user:{id:Object(l.getUser)().userId},testCase:{id:t.id}};N(r),sessionStorage.setItem("".concat(e),t.id),x.push("/repository/".concat(t.caseType,"/").concat(t.id)),y(!d)}var i=e.testcaseStore,p=e.caseId,d=(e=b(Object(n.useState)(!1),2))[0],y=e[1],v=i.findTestCaseList,N=(i.testcaseList,i.testCaseRecent),g=(i=(e=b(Object(n.useState)([]),2))[0],e[1]),w=(e=b(Object(n.useState)(),2))[0],_=e[1],E=b(Object(n.useState)(10),1)[0],P=(e=b(Object(n.useState)(1),2))[0],O=e[1],j=sessionStorage.getItem("repositoryId"),x=Object(s.g)(),S=Object(n.useRef)(null);return Object(n.useEffect)((function(){function e(e){S.current&&!S.current.contains(e.target)&&y(!1)}return document.addEventListener("click",e),function(){document.removeEventListener("click",e)}}),[]),o.a.createElement("div",{className:"case-toggle",ref:S,__source:{fileName:f,lineNumber:129,columnNumber:9}},o.a.createElement("div",{onClick:function(){y(!d),t()},style:{cursor:"pointer",margin:"5px 0 0"},__source:{fileName:f,lineNumber:130,columnNumber:13}},o.a.createElement(m.a,{className:"icon-s",icon:"xiala",__source:{fileName:f,lineNumber:134,columnNumber:17}})),o.a.createElement("div",{className:"case-toggle-title ".concat(!1===d?"teston-hide":"teston-show"),__source:{fileName:f,lineNumber:140,columnNumber:13}},i&&i.map((function(e,t){return o.a.createElement("div",{key:e.id,className:"\n                            display-flex-between \n                            toggle-case-item \n                            ".concat(p===e.id?"toggle-case-item-selected":"","\n                            "),onClick:function(){var t=e;switch(t.caseType){case u.a.API_UNIT:r("apiUnitId",t);break;case u.a.API_SCENE:r("apiSceneId",t);break;case u.a.API_PERFORM:r("apiPerfId",t);break;case u.a.WEB_SCENE:r("webSceneId",t);break;case u.a.WEB_PERFORM:r("webPerfId",t);break;case u.a.APP_SCENE:r("appSceneId",t);break;case u.a.APP_PERFORM:r("appPerfId",t);break;case u.a.FUNCTION:r("functionId",t)}},__source:{fileName:f,lineNumber:144,columnNumber:29}},o.a.createElement("span",{className:"text-ellipsis",__source:{fileName:f,lineNumber:153,columnNumber:33}},e.name),Object(a.a)(e.caseType))})),o.a.createElement(c.a,{currentPage:P,totalPage:w,changePage:function(e){O(e),t({pageParam:{pageSize:E,currentPage:e}})},__source:{fileName:f,lineNumber:161,columnNumber:17}})))})))},709:function(e,t,r){"use strict";r.d(t,"a",(function(){return n}));var n={API_UNIT:"api-unit",API_SCENE:"api-scene",API_PERFORM:"api-perform",WEB_SCENE:"web-scene",WEB_PERFORM:"web-perform",APP_SCENE:"app-scene",APP_PERFORM:"app-perform",FUNCTION:"function"}},779:function(e,t,r){"use strict";var n=r(736),o=r(815),a=r(878),i=r(881),c=r(883),u=r(882),l=r(884),s=r(879),m=r(0),f=r.n(m);n.a([a.a,i.a,c.a,u.a,s.a,l.a]),t.a=function(e){var t=e.option,r=(e=e.data,Object(m.useRef)(null));return Object(m.useEffect)((function(){var e=o.b(r.current);return e.setOption(t),function(){e.dispose()}}),[e]),f.a.createElement("div",{style:{width:"100%",height:"100%"},ref:r,__source:{fileName:"D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\caseCommon\\PerformInstanceCommon.js",lineNumber:46,columnNumber:13}})}},817:function(e,t,r){"use strict";r.r(t),r(396);var n,o=r(395),a=(r(138),r(82)),i=r(0),c=r.n(i),u=r(63),l=r(779),s=r(8),m=r(7);function f(e){return(f="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function p(){p=function(){return e};var e={},t=Object.prototype,r=t.hasOwnProperty,n=(b="function"==typeof Symbol?Symbol:{}).iterator||"@@iterator",o=b.asyncIterator||"@@asyncIterator",a=b.toStringTag||"@@toStringTag";function i(e,t,r){return Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{i({},"")}catch(t){i=function(e,t,r){return e[t]=r}}function c(e,t,r,n){var o,a,i,c;t=t&&t.prototype instanceof s?t:s,t=Object.create(t.prototype),n=new _(n||[]);return t._invoke=(o=e,a=r,i=n,c="suspendedStart",function(e,t){if("executing"===c)throw new Error("Generator is already running");if("completed"===c){if("throw"===e)throw t;return{value:void 0,done:!0}}for(i.method=e,i.arg=t;;){var r=i.delegate;if(r&&(r=function e(t,r){var n=t.iterator[r.method];if(void 0===n){if(r.delegate=null,"throw"===r.method){if(t.iterator.return&&(r.method="return",r.arg=void 0,e(t,r),"throw"===r.method))return l;r.method="throw",r.arg=new TypeError("The iterator does not provide a 'throw' method")}return l}return"throw"===(n=u(n,t.iterator,r.arg)).type?(r.method="throw",r.arg=n.arg,r.delegate=null,l):(n=n.arg)?n.done?(r[t.resultName]=n.value,r.next=t.nextLoc,"return"!==r.method&&(r.method="next",r.arg=void 0),r.delegate=null,l):n:(r.method="throw",r.arg=new TypeError("iterator result is not an object"),r.delegate=null,l)}(r,i))){if(r===l)continue;return r}if("next"===i.method)i.sent=i._sent=i.arg;else if("throw"===i.method){if("suspendedStart"===c)throw c="completed",i.arg;i.dispatchException(i.arg)}else"return"===i.method&&i.abrupt("return",i.arg);if(c="executing","normal"===(r=u(o,a,i)).type){if(c=i.done?"completed":"suspendedYield",r.arg===l)continue;return{value:r.arg,done:i.done}}"throw"===r.type&&(c="completed",i.method="throw",i.arg=r.arg)}}),t}function u(e,t,r){try{return{type:"normal",arg:e.call(t,r)}}catch(e){return{type:"throw",arg:e}}}e.wrap=c;var l={};function s(){}function m(){}function h(){}var b,d,y=((d=(d=(i(b={},n,(function(){return this})),Object.getPrototypeOf))&&d(d(E([]))))&&d!==t&&r.call(d,n)&&(b=d),h.prototype=s.prototype=Object.create(b));function v(e){["next","throw","return"].forEach((function(t){i(e,t,(function(e){return this._invoke(t,e)}))}))}function N(e,t){var n;this._invoke=function(o,a){function i(){return new t((function(n,i){!function n(o,a,i,c){var l;if("throw"!==(o=u(e[o],e,a)).type)return(a=(l=o.arg).value)&&"object"==f(a)&&r.call(a,"__await")?t.resolve(a.__await).then((function(e){n("next",e,i,c)}),(function(e){n("throw",e,i,c)})):t.resolve(a).then((function(e){l.value=e,i(l)}),(function(e){return n("throw",e,i,c)}));c(o.arg)}(o,a,n,i)}))}return n=n?n.then(i,i):i()}}function g(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function w(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function _(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(g,this),this.reset(!0)}function E(e){if(e){var t,o=e[n];if(o)return o.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length))return t=-1,(o=function n(){for(;++t<e.length;)if(r.call(e,t))return n.value=e[t],n.done=!1,n;return n.value=void 0,n.done=!0,n}).next=o}return{next:P}}function P(){return{value:void 0,done:!0}}return i(y,"constructor",m.prototype=h),i(h,"constructor",m),m.displayName=i(h,a,"GeneratorFunction"),e.isGeneratorFunction=function(e){return!!(e="function"==typeof e&&e.constructor)&&(e===m||"GeneratorFunction"===(e.displayName||e.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,h):(e.__proto__=h,i(e,a,"GeneratorFunction")),e.prototype=Object.create(y),e},e.awrap=function(e){return{__await:e}},v(N.prototype),i(N.prototype,o,(function(){return this})),e.AsyncIterator=N,e.async=function(t,r,n,o,a){void 0===a&&(a=Promise);var i=new N(c(t,r,n,o),a);return e.isGeneratorFunction(r)?i:i.next().then((function(e){return e.done?e.value:i.next()}))},v(y),i(y,a,"Generator"),i(y,n,(function(){return this})),i(y,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t,r=[];for(t in e)r.push(t);return r.reverse(),function t(){for(;r.length;){var n=r.pop();if(n in e)return t.value=n,t.done=!1,t}return t.done=!0,t}},e.values=E,_.prototype={constructor:_,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(w),!e)for(var t in this)"t"===t.charAt(0)&&r.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function n(r,n){return i.type="throw",i.arg=e,t.next=r,n&&(t.method="next",t.arg=void 0),!!n}for(var o=this.tryEntries.length-1;0<=o;--o){var a=this.tryEntries[o],i=a.completion;if("root"===a.tryLoc)return n("end");if(a.tryLoc<=this.prev){var c=r.call(a,"catchLoc"),u=r.call(a,"finallyLoc");if(c&&u){if(this.prev<a.catchLoc)return n(a.catchLoc,!0);if(this.prev<a.finallyLoc)return n(a.finallyLoc)}else if(c){if(this.prev<a.catchLoc)return n(a.catchLoc,!0)}else{if(!u)throw new Error("try statement without catch or finally");if(this.prev<a.finallyLoc)return n(a.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;0<=n;--n){var o=this.tryEntries[n];if(o.tryLoc<=this.prev&&r.call(o,"finallyLoc")&&this.prev<o.finallyLoc){var a=o;break}}var i=(a=a&&("break"===e||"continue"===e)&&a.tryLoc<=t&&t<=a.finallyLoc?null:a)?a.completion:{};return i.type=e,i.arg=t,a?(this.method="next",this.next=a.finallyLoc,l):this.complete(i)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),l},finish:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r=this.tryEntries[t];if(r.finallyLoc===e)return this.complete(r.completion,r.afterLoc),w(r),l}},catch:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r,n,o=this.tryEntries[t];if(o.tryLoc===e)return"throw"===(r=o.completion).type&&(n=r.arg,w(o)),n}throw new Error("illegal catch attempt")},delegateYield:function(e,t,r){return this.delegate={iterator:E(e),resultName:t,nextLoc:r},"next"===this.method&&(this.arg=void 0),l}},e}function h(e,t){var r,n=Object.keys(e);return Object.getOwnPropertySymbols&&(r=Object.getOwnPropertySymbols(e),t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)),n}function b(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?h(Object(r),!0).forEach((function(t){var n,o;n=e,o=r[t=t],t in n?Object.defineProperty(n,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):n[t]=o})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):h(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function d(e,t,r,n,o,a,i){try{var c=e[a](i),u=c.value}catch(e){return void r(e)}c.done?t(u):Promise.resolve(u).then(n,o)}function y(e){return function(){var t=this,r=arguments;return new Promise((function(n,o){var a=e.apply(t,r);function i(e){d(a,n,o,i,c,"next",e)}function c(e){d(a,n,o,i,c,"throw",e)}i(void 0)}))}}function v(e,t,r,n){r&&Object.defineProperty(e,t,{enumerable:r.enumerable,configurable:r.configurable,writable:r.writable,value:r.initializer?r.initializer.call(n):void 0})}function N(e,t){for(var r=0;r<t.length;r++){var n=t[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}function g(e,t,r,n,o){var a={};return Object.keys(n).forEach((function(e){a[e]=n[e]})),a.enumerable=!!a.enumerable,a.configurable=!!a.configurable,("value"in a||a.initializer)&&(a.writable=!0),a=r.slice().reverse().reduce((function(r,n){return n(e,t,r)||r}),a),o&&void 0!==a.initializer&&(a.value=a.initializer?a.initializer.call(o):void 0,a.initializer=void 0),void 0===a.initializer&&(Object.defineProperty(e,t,a),a=null),a}var w=g((n=function(e,t,r){return t&&N(e.prototype,t),r&&N(e,r),Object.defineProperty(e,"prototype",{writable:!1}),e}((function e(){if(!(this instanceof e))throw new TypeError("Cannot call a class as a function");v(this,"apiPerfInstanceList",w,this),v(this,"apiPerfInstanceInfo",_,this),v(this,"apiPerformId",E,this),v(this,"findApiPerfInstancePage",P,this),v(this,"findApiPerfInstance",O,this)}))).prototype,"apiPerfInstanceList",[s.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return[]}}),_=g(n.prototype,"apiPerfInstanceInfo",[s.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:null}),E=g(n.prototype,"apiPerformId",[s.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:null}),P=g(n.prototype,"findApiPerfInstancePage",[s.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(){var t=y(p().mark((function t(r){var n;return p().wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return n=b(b({},r),{},{type:"api-perform",orderParams:[{name:"createTime",orderType:"desc"}]}),t.next=3,m.Axios.post("/instance/findInstancePage",n);case 3:return 0===(n=t.sent).code&&(e.apiPerfInstanceList=n.data.dataList),t.abrupt("return",n);case 6:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()}}),O=g(n.prototype,"findApiPerfInstance",[s.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(){var t=y(p().mark((function t(r){var n;return p().wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return(n=new FormData).append("id",r),t.next=4,m.Axios.post("/apiPerfInstance/findApiPerfInstance",n);case 4:if(0===(n=t.sent).code)return e.apiPerfInstanceInfo=n.data,t.abrupt("return",n.data);t.next=8;break;case 8:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()}}),j=new n,x=r(684);function S(e){return(S="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}var L="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\api\\http\\perf\\components\\ApiPerfInstanceSinglePage.js";function I(){I=function(){return e};var e={},t=Object.prototype,r=t.hasOwnProperty,n=(p="function"==typeof Symbol?Symbol:{}).iterator||"@@iterator",o=p.asyncIterator||"@@asyncIterator",a=p.toStringTag||"@@toStringTag";function i(e,t,r){return Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{i({},"")}catch(t){i=function(e,t,r){return e[t]=r}}function c(e,t,r,n){var o,a,i,c;t=t&&t.prototype instanceof s?t:s,t=Object.create(t.prototype),n=new g(n||[]);return t._invoke=(o=e,a=r,i=n,c="suspendedStart",function(e,t){if("executing"===c)throw new Error("Generator is already running");if("completed"===c){if("throw"===e)throw t;return{value:void 0,done:!0}}for(i.method=e,i.arg=t;;){var r=i.delegate;if(r&&(r=function e(t,r){var n=t.iterator[r.method];if(void 0===n){if(r.delegate=null,"throw"===r.method){if(t.iterator.return&&(r.method="return",r.arg=void 0,e(t,r),"throw"===r.method))return l;r.method="throw",r.arg=new TypeError("The iterator does not provide a 'throw' method")}return l}return"throw"===(n=u(n,t.iterator,r.arg)).type?(r.method="throw",r.arg=n.arg,r.delegate=null,l):(n=n.arg)?n.done?(r[t.resultName]=n.value,r.next=t.nextLoc,"return"!==r.method&&(r.method="next",r.arg=void 0),r.delegate=null,l):n:(r.method="throw",r.arg=new TypeError("iterator result is not an object"),r.delegate=null,l)}(r,i))){if(r===l)continue;return r}if("next"===i.method)i.sent=i._sent=i.arg;else if("throw"===i.method){if("suspendedStart"===c)throw c="completed",i.arg;i.dispatchException(i.arg)}else"return"===i.method&&i.abrupt("return",i.arg);if(c="executing","normal"===(r=u(o,a,i)).type){if(c=i.done?"completed":"suspendedYield",r.arg===l)continue;return{value:r.arg,done:i.done}}"throw"===r.type&&(c="completed",i.method="throw",i.arg=r.arg)}}),t}function u(e,t,r){try{return{type:"normal",arg:e.call(t,r)}}catch(e){return{type:"throw",arg:e}}}e.wrap=c;var l={};function s(){}function m(){}function f(){}var p,h,b=((h=(h=(i(p={},n,(function(){return this})),Object.getPrototypeOf))&&h(h(w([]))))&&h!==t&&r.call(h,n)&&(p=h),f.prototype=s.prototype=Object.create(p));function d(e){["next","throw","return"].forEach((function(t){i(e,t,(function(e){return this._invoke(t,e)}))}))}function y(e,t){var n;this._invoke=function(o,a){function i(){return new t((function(n,i){!function n(o,a,i,c){var l;if("throw"!==(o=u(e[o],e,a)).type)return(a=(l=o.arg).value)&&"object"==S(a)&&r.call(a,"__await")?t.resolve(a.__await).then((function(e){n("next",e,i,c)}),(function(e){n("throw",e,i,c)})):t.resolve(a).then((function(e){l.value=e,i(l)}),(function(e){return n("throw",e,i,c)}));c(o.arg)}(o,a,n,i)}))}return n=n?n.then(i,i):i()}}function v(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function N(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function g(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(v,this),this.reset(!0)}function w(e){if(e){var t,o=e[n];if(o)return o.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length))return t=-1,(o=function n(){for(;++t<e.length;)if(r.call(e,t))return n.value=e[t],n.done=!1,n;return n.value=void 0,n.done=!0,n}).next=o}return{next:_}}function _(){return{value:void 0,done:!0}}return i(b,"constructor",m.prototype=f),i(f,"constructor",m),m.displayName=i(f,a,"GeneratorFunction"),e.isGeneratorFunction=function(e){return!!(e="function"==typeof e&&e.constructor)&&(e===m||"GeneratorFunction"===(e.displayName||e.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,f):(e.__proto__=f,i(e,a,"GeneratorFunction")),e.prototype=Object.create(b),e},e.awrap=function(e){return{__await:e}},d(y.prototype),i(y.prototype,o,(function(){return this})),e.AsyncIterator=y,e.async=function(t,r,n,o,a){void 0===a&&(a=Promise);var i=new y(c(t,r,n,o),a);return e.isGeneratorFunction(r)?i:i.next().then((function(e){return e.done?e.value:i.next()}))},d(b),i(b,a,"Generator"),i(b,n,(function(){return this})),i(b,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t,r=[];for(t in e)r.push(t);return r.reverse(),function t(){for(;r.length;){var n=r.pop();if(n in e)return t.value=n,t.done=!1,t}return t.done=!0,t}},e.values=w,g.prototype={constructor:g,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(N),!e)for(var t in this)"t"===t.charAt(0)&&r.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function n(r,n){return i.type="throw",i.arg=e,t.next=r,n&&(t.method="next",t.arg=void 0),!!n}for(var o=this.tryEntries.length-1;0<=o;--o){var a=this.tryEntries[o],i=a.completion;if("root"===a.tryLoc)return n("end");if(a.tryLoc<=this.prev){var c=r.call(a,"catchLoc"),u=r.call(a,"finallyLoc");if(c&&u){if(this.prev<a.catchLoc)return n(a.catchLoc,!0);if(this.prev<a.finallyLoc)return n(a.finallyLoc)}else if(c){if(this.prev<a.catchLoc)return n(a.catchLoc,!0)}else{if(!u)throw new Error("try statement without catch or finally");if(this.prev<a.finallyLoc)return n(a.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;0<=n;--n){var o=this.tryEntries[n];if(o.tryLoc<=this.prev&&r.call(o,"finallyLoc")&&this.prev<o.finallyLoc){var a=o;break}}var i=(a=a&&("break"===e||"continue"===e)&&a.tryLoc<=t&&t<=a.finallyLoc?null:a)?a.completion:{};return i.type=e,i.arg=t,a?(this.method="next",this.next=a.finallyLoc,l):this.complete(i)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),l},finish:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r=this.tryEntries[t];if(r.finallyLoc===e)return this.complete(r.completion,r.afterLoc),N(r),l}},catch:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r,n,o=this.tryEntries[t];if(o.tryLoc===e)return"throw"===(r=o.completion).type&&(n=r.arg,N(o)),n}throw new Error("illegal catch attempt")},delegateYield:function(e,t,r){return this.delegate={iterator:w(e),resultName:t,nextLoc:r},"next"===this.method&&(this.arg=void 0),l}},e}function k(e,t,r,n,o,a,i){try{var c=e[a](i),u=c.value}catch(e){return void r(e)}c.done?t(u):Promise.resolve(u).then(n,o)}function A(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,a=[],i=!0,c=!1;try{for(r=r.call(e);!(i=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);i=!0);}catch(e){c=!0,o=e}finally{try{i||null==r.return||r.return()}finally{if(c)throw o}}return a}}(e,t)||function(e,t){var r;if(e)return"string"==typeof e?C(e,t):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?C(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function C(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}t.default=Object(u.observer)((function(e){var t=e.apiPerfInstanceId,r=(e=e.name,j.findApiPerfInstance),n=(h=A(Object(i.useState)(),2))[0],u=h[1],s=(h=A(Object(i.useState)(!0),2))[0],m=h[1],f=(h=A(Object(i.useState)(!1),2))[0],p=h[1],h=function(){e=I().mark((function e(){var n;return I().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,r(t);case 2:n=e.sent,m(!1),u(n),p(!0);case 6:case"end":return e.stop()}}),e)}));var e,n=function(){var t=this,r=arguments;return new Promise((function(n,o){var a=e.apply(t,r);function i(e){k(a,n,o,i,c,"next",e)}function c(e){k(a,n,o,i,c,"throw",e)}i(void 0)}))};return function(){return n.apply(this,arguments)}}(),b={tooltip:{trigger:"item"},legend:{orient:"vertical",left:"left"},series:[{name:"Access From",type:"pie",radius:"50%",data:[{name:"通过率",value:null==n?void 0:n.passNum},{name:"失败率",value:null==n?void 0:n.failNum}],emphasis:{itemStyle:{shadowBlur:10,shadowOffsetX:0,shadowColor:"rgba(0, 0, 0, 0.5)"}}}]};return c.a.createElement(c.a.Fragment,null,c.a.createElement("span",{className:"link-text",onClick:h,__source:{fileName:L,lineNumber:58,columnNumber:13}},e),c.a.createElement(o.default,{placement:"right",onClose:function(){p(!1)},open:f,width:900,destroyOnClose:!0,maskStyle:{background:"transparent"},contentWrapperStyle:{top:48,height:"calc(100% - 50px)"},closable:!1,__source:{fileName:L,lineNumber:59,columnNumber:13}},c.a.createElement(x.a,{breadItem:["历史详情"],icon:"api1",setOpen:p,__source:{fileName:L,lineNumber:69,columnNumber:17}}),c.a.createElement("div",{className:"result-spin-box",style:{margin:"0 10px",overflow:"hidden",height:"calc( 100% - 52px )"},__source:{fileName:L,lineNumber:74,columnNumber:17}},c.a.createElement(a.a,{spinning:s,__source:{fileName:L,lineNumber:75,columnNumber:17}},c.a.createElement("div",{className:"history-detail history-detail-box",__source:{fileName:L,lineNumber:76,columnNumber:21}},c.a.createElement("div",{className:"history-detail-all",__source:{fileName:L,lineNumber:77,columnNumber:25}},c.a.createElement("div",{className:"history-detail-all-box",__source:{fileName:L,lineNumber:78,columnNumber:29}},c.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:L,lineNumber:79,columnNumber:33}},c.a.createElement("div",{__source:{fileName:L,lineNumber:80,columnNumber:37}},"通过率"),c.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:L,lineNumber:81,columnNumber:37}},null==n?void 0:n.passRate)),c.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:L,lineNumber:83,columnNumber:33}},c.a.createElement("div",{__source:{fileName:L,lineNumber:84,columnNumber:37}},"失败率"),c.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:L,lineNumber:85,columnNumber:37}},null==n?void 0:n.errorRate)),c.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:L,lineNumber:87,columnNumber:33}},c.a.createElement("div",{__source:{fileName:L,lineNumber:88,columnNumber:37}},"总数"),c.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:L,lineNumber:89,columnNumber:37}},null==n?void 0:n.total)),c.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:L,lineNumber:91,columnNumber:33}},c.a.createElement("div",{__source:{fileName:L,lineNumber:92,columnNumber:37}},"通过数"),c.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:L,lineNumber:93,columnNumber:37}},null==n?void 0:n.passNum)),c.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:L,lineNumber:96,columnNumber:33}},c.a.createElement("div",{__source:{fileName:L,lineNumber:97,columnNumber:37}},"未通过数"),c.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:L,lineNumber:98,columnNumber:37}},null==n?void 0:n.failNum)))),c.a.createElement("div",{style:{width:"100%",height:"100%"},__source:{fileName:L,lineNumber:102,columnNumber:25}},c.a.createElement(l.a,{option:b,data:n,__source:{fileName:L,lineNumber:104,columnNumber:29}})))))))}))}}]);