(window.webpackJsonp=window.webpackJsonp||[]).push([[61],{684:function(e,t,r){"use strict";r(644);var n=r(643),o=r(0),i=r.n(o),a=r(679),c=(r(685),r(752)),u=r(50),l=(r(705),"D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\CaseBread.js");t.a=function(e){var t=e.icon,r=e.style,o=e.toggleCase,s=e.setOpen,f=e.breadItem,m=e.right,h=e.router,p=Object(u.g)();return i.a.createElement("div",{className:"breadcrumb-title_between",style:r,__source:{fileName:l,lineNumber:43,columnNumber:9}},i.a.createElement("div",{className:"breadcrumb-left",__source:{fileName:l,lineNumber:44,columnNumber:13}},t?i.a.createElement(a.a,{icon:t,className:"icon-s ",style:{margin:"3px 5px 0"},__source:{fileName:l,lineNumber:17,columnNumber:17}}):i.a.createElement(c.a,{onClick:function(){return p.push(h)},style:{cursor:"pointer",fontSize:"20px"},__source:{fileName:l,lineNumber:24,columnNumber:20}}),i.a.createElement(n.a,{style:{fontWeight:"bold"},__source:{fileName:l,lineNumber:48,columnNumber:17}},f.map((function(e,t){return i.a.createElement(n.a.Item,{key:t,__source:{fileName:l,lineNumber:38,columnNumber:20}},e)}))),o),m?i.a.createElement(i.a.Fragment,null,m):null,s?i.a.createElement(a.a,{className:"icon-s edit-icon",icon:"shanchu2",onClick:function(){return s(!1)},__source:{fileName:l,lineNumber:60,columnNumber:22}}):null)}},705:function(e,t,r){"use strict";var n=r(0),o=r.n(n),i=r(685),a=r(63),c=r(702),u=r(709),l=r(7),s=r(50),f=r(679),m="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\testcase\\components\\ToggleCase.js";function h(e,t){var r,n=Object.keys(e);return Object.getOwnPropertySymbols&&(r=Object.getOwnPropertySymbols(e),t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)),n}function p(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?h(Object(r),!0).forEach((function(t){var n,o;n=e,o=r[t=t],t in n?Object.defineProperty(n,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):n[t]=o})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):h(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function d(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,i=[],a=!0,c=!1;try{for(r=r.call(e);!(a=(n=r.next()).done)&&(i.push(n.value),!t||i.length!==t);a=!0);}catch(e){c=!0,o=e}finally{try{a||null==r.return||r.return()}finally{if(c)throw o}}return i}}(e,t)||function(e,t){var r;if(e)return"string"==typeof e?y(e,t):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?y(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function y(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}t.a=Object(a.inject)("testcaseStore")(Object(a.observer)((function(e){function t(e){e=p({pageParam:{pageSize:E,currentPage:1},repositoryId:j},e),v(e).then((function(e){N(e.dataList),_(e.totalPage)}))}function r(e,t){var r={repository:{id:j},user:{id:Object(l.getUser)().userId},testCase:{id:t.id}};g(r),sessionStorage.setItem("".concat(e),t.id),x.push("/repository/".concat(t.caseType,"/").concat(t.id)),b(!y)}var a=e.testcaseStore,h=e.caseId,y=(e=d(Object(n.useState)(!1),2))[0],b=e[1],v=a.findTestCaseList,g=(a.testcaseList,a.testCaseRecent),N=(a=(e=d(Object(n.useState)([]),2))[0],e[1]),w=(e=d(Object(n.useState)(),2))[0],_=e[1],E=d(Object(n.useState)(10),1)[0],O=(e=d(Object(n.useState)(1),2))[0],P=e[1],j=sessionStorage.getItem("repositoryId"),x=Object(s.g)(),L=Object(n.useRef)(null);return Object(n.useEffect)((function(){function e(e){L.current&&!L.current.contains(e.target)&&b(!1)}return document.addEventListener("click",e),function(){document.removeEventListener("click",e)}}),[]),o.a.createElement("div",{className:"case-toggle",ref:L,__source:{fileName:m,lineNumber:129,columnNumber:9}},o.a.createElement("div",{onClick:function(){b(!y),t()},style:{cursor:"pointer",margin:"5px 0 0"},__source:{fileName:m,lineNumber:130,columnNumber:13}},o.a.createElement(f.a,{className:"icon-s",icon:"xiala",__source:{fileName:m,lineNumber:134,columnNumber:17}})),o.a.createElement("div",{className:"case-toggle-title ".concat(!1===y?"teston-hide":"teston-show"),__source:{fileName:m,lineNumber:140,columnNumber:13}},a&&a.map((function(e,t){return o.a.createElement("div",{key:e.id,className:"\n                            display-flex-between \n                            toggle-case-item \n                            ".concat(h===e.id?"toggle-case-item-selected":"","\n                            "),onClick:function(){var t=e;switch(t.caseType){case u.a.API_UNIT:r("apiUnitId",t);break;case u.a.API_SCENE:r("apiSceneId",t);break;case u.a.API_PERFORM:r("apiPerfId",t);break;case u.a.WEB_SCENE:r("webSceneId",t);break;case u.a.WEB_PERFORM:r("webPerfId",t);break;case u.a.APP_SCENE:r("appSceneId",t);break;case u.a.APP_PERFORM:r("appPerfId",t);break;case u.a.FUNCTION:r("functionId",t)}},__source:{fileName:m,lineNumber:144,columnNumber:29}},o.a.createElement("span",{className:"text-ellipsis",__source:{fileName:m,lineNumber:153,columnNumber:33}},e.name),Object(i.a)(e.caseType))})),o.a.createElement(c.a,{currentPage:O,totalPage:w,changePage:function(e){P(e),t({pageParam:{pageSize:E,currentPage:e}})},__source:{fileName:m,lineNumber:161,columnNumber:17}})))})))},779:function(e,t,r){"use strict";var n=r(736),o=r(815),i=r(878),a=r(881),c=r(883),u=r(882),l=r(884),s=r(879),f=r(0),m=r.n(f);n.a([i.a,a.a,c.a,u.a,s.a,l.a]),t.a=function(e){var t=e.option,r=(e=e.data,Object(f.useRef)(null));return Object(f.useEffect)((function(){var e=o.b(r.current);return e.setOption(t),function(){e.dispose()}}),[e]),m.a.createElement("div",{style:{width:"100%",height:"100%"},ref:r,__source:{fileName:"D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\caseCommon\\PerformInstanceCommon.js",lineNumber:46,columnNumber:13}})}},817:function(e,t,r){"use strict";r.r(t),r(396);var n,o=r(395),i=(r(138),r(82)),a=r(0),c=r.n(a),u=r(63),l=r(779),s=r(8),f=r(7);function m(e){return(m="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function h(){h=function(){return e};var e={},t=Object.prototype,r=t.hasOwnProperty,n=(d="function"==typeof Symbol?Symbol:{}).iterator||"@@iterator",o=d.asyncIterator||"@@asyncIterator",i=d.toStringTag||"@@toStringTag";function a(e,t,r){return Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{a({},"")}catch(t){a=function(e,t,r){return e[t]=r}}function c(e,t,r,n){var o,i,a,c;t=t&&t.prototype instanceof s?t:s,t=Object.create(t.prototype),n=new _(n||[]);return t._invoke=(o=e,i=r,a=n,c="suspendedStart",function(e,t){if("executing"===c)throw new Error("Generator is already running");if("completed"===c){if("throw"===e)throw t;return{value:void 0,done:!0}}for(a.method=e,a.arg=t;;){var r=a.delegate;if(r&&(r=function e(t,r){var n=t.iterator[r.method];if(void 0===n){if(r.delegate=null,"throw"===r.method){if(t.iterator.return&&(r.method="return",r.arg=void 0,e(t,r),"throw"===r.method))return l;r.method="throw",r.arg=new TypeError("The iterator does not provide a 'throw' method")}return l}return"throw"===(n=u(n,t.iterator,r.arg)).type?(r.method="throw",r.arg=n.arg,r.delegate=null,l):(n=n.arg)?n.done?(r[t.resultName]=n.value,r.next=t.nextLoc,"return"!==r.method&&(r.method="next",r.arg=void 0),r.delegate=null,l):n:(r.method="throw",r.arg=new TypeError("iterator result is not an object"),r.delegate=null,l)}(r,a))){if(r===l)continue;return r}if("next"===a.method)a.sent=a._sent=a.arg;else if("throw"===a.method){if("suspendedStart"===c)throw c="completed",a.arg;a.dispatchException(a.arg)}else"return"===a.method&&a.abrupt("return",a.arg);if(c="executing","normal"===(r=u(o,i,a)).type){if(c=a.done?"completed":"suspendedYield",r.arg===l)continue;return{value:r.arg,done:a.done}}"throw"===r.type&&(c="completed",a.method="throw",a.arg=r.arg)}}),t}function u(e,t,r){try{return{type:"normal",arg:e.call(t,r)}}catch(e){return{type:"throw",arg:e}}}e.wrap=c;var l={};function s(){}function f(){}function p(){}var d,y,b=((y=(y=(a(d={},n,(function(){return this})),Object.getPrototypeOf))&&y(y(E([]))))&&y!==t&&r.call(y,n)&&(d=y),p.prototype=s.prototype=Object.create(d));function v(e){["next","throw","return"].forEach((function(t){a(e,t,(function(e){return this._invoke(t,e)}))}))}function g(e,t){var n;this._invoke=function(o,i){function a(){return new t((function(n,a){!function n(o,i,a,c){var l;if("throw"!==(o=u(e[o],e,i)).type)return(i=(l=o.arg).value)&&"object"==m(i)&&r.call(i,"__await")?t.resolve(i.__await).then((function(e){n("next",e,a,c)}),(function(e){n("throw",e,a,c)})):t.resolve(i).then((function(e){l.value=e,a(l)}),(function(e){return n("throw",e,a,c)}));c(o.arg)}(o,i,n,a)}))}return n=n?n.then(a,a):a()}}function N(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function w(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function _(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(N,this),this.reset(!0)}function E(e){if(e){var t,o=e[n];if(o)return o.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length))return t=-1,(o=function n(){for(;++t<e.length;)if(r.call(e,t))return n.value=e[t],n.done=!1,n;return n.value=void 0,n.done=!0,n}).next=o}return{next:O}}function O(){return{value:void 0,done:!0}}return a(b,"constructor",f.prototype=p),a(p,"constructor",f),f.displayName=a(p,i,"GeneratorFunction"),e.isGeneratorFunction=function(e){return!!(e="function"==typeof e&&e.constructor)&&(e===f||"GeneratorFunction"===(e.displayName||e.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,p):(e.__proto__=p,a(e,i,"GeneratorFunction")),e.prototype=Object.create(b),e},e.awrap=function(e){return{__await:e}},v(g.prototype),a(g.prototype,o,(function(){return this})),e.AsyncIterator=g,e.async=function(t,r,n,o,i){void 0===i&&(i=Promise);var a=new g(c(t,r,n,o),i);return e.isGeneratorFunction(r)?a:a.next().then((function(e){return e.done?e.value:a.next()}))},v(b),a(b,i,"Generator"),a(b,n,(function(){return this})),a(b,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t,r=[];for(t in e)r.push(t);return r.reverse(),function t(){for(;r.length;){var n=r.pop();if(n in e)return t.value=n,t.done=!1,t}return t.done=!0,t}},e.values=E,_.prototype={constructor:_,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(w),!e)for(var t in this)"t"===t.charAt(0)&&r.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function n(r,n){return a.type="throw",a.arg=e,t.next=r,n&&(t.method="next",t.arg=void 0),!!n}for(var o=this.tryEntries.length-1;0<=o;--o){var i=this.tryEntries[o],a=i.completion;if("root"===i.tryLoc)return n("end");if(i.tryLoc<=this.prev){var c=r.call(i,"catchLoc"),u=r.call(i,"finallyLoc");if(c&&u){if(this.prev<i.catchLoc)return n(i.catchLoc,!0);if(this.prev<i.finallyLoc)return n(i.finallyLoc)}else if(c){if(this.prev<i.catchLoc)return n(i.catchLoc,!0)}else{if(!u)throw new Error("try statement without catch or finally");if(this.prev<i.finallyLoc)return n(i.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;0<=n;--n){var o=this.tryEntries[n];if(o.tryLoc<=this.prev&&r.call(o,"finallyLoc")&&this.prev<o.finallyLoc){var i=o;break}}var a=(i=i&&("break"===e||"continue"===e)&&i.tryLoc<=t&&t<=i.finallyLoc?null:i)?i.completion:{};return a.type=e,a.arg=t,i?(this.method="next",this.next=i.finallyLoc,l):this.complete(a)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),l},finish:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r=this.tryEntries[t];if(r.finallyLoc===e)return this.complete(r.completion,r.afterLoc),w(r),l}},catch:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r,n,o=this.tryEntries[t];if(o.tryLoc===e)return"throw"===(r=o.completion).type&&(n=r.arg,w(o)),n}throw new Error("illegal catch attempt")},delegateYield:function(e,t,r){return this.delegate={iterator:E(e),resultName:t,nextLoc:r},"next"===this.method&&(this.arg=void 0),l}},e}function p(e,t){var r,n=Object.keys(e);return Object.getOwnPropertySymbols&&(r=Object.getOwnPropertySymbols(e),t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)),n}function d(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?p(Object(r),!0).forEach((function(t){var n,o;n=e,o=r[t=t],t in n?Object.defineProperty(n,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):n[t]=o})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):p(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function y(e,t,r,n,o,i,a){try{var c=e[i](a),u=c.value}catch(e){return void r(e)}c.done?t(u):Promise.resolve(u).then(n,o)}function b(e){return function(){var t=this,r=arguments;return new Promise((function(n,o){var i=e.apply(t,r);function a(e){y(i,n,o,a,c,"next",e)}function c(e){y(i,n,o,a,c,"throw",e)}a(void 0)}))}}function v(e,t,r,n){r&&Object.defineProperty(e,t,{enumerable:r.enumerable,configurable:r.configurable,writable:r.writable,value:r.initializer?r.initializer.call(n):void 0})}function g(e,t){for(var r=0;r<t.length;r++){var n=t[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}function N(e,t,r,n,o){var i={};return Object.keys(n).forEach((function(e){i[e]=n[e]})),i.enumerable=!!i.enumerable,i.configurable=!!i.configurable,("value"in i||i.initializer)&&(i.writable=!0),i=r.slice().reverse().reduce((function(r,n){return n(e,t,r)||r}),i),o&&void 0!==i.initializer&&(i.value=i.initializer?i.initializer.call(o):void 0,i.initializer=void 0),void 0===i.initializer&&(Object.defineProperty(e,t,i),i=null),i}var w=N((n=function(e,t,r){return t&&g(e.prototype,t),r&&g(e,r),Object.defineProperty(e,"prototype",{writable:!1}),e}((function e(){if(!(this instanceof e))throw new TypeError("Cannot call a class as a function");v(this,"apiPerfInstanceList",w,this),v(this,"apiPerfInstanceInfo",_,this),v(this,"apiPerformId",E,this),v(this,"findApiPerfInstancePage",O,this),v(this,"findApiPerfInstance",P,this)}))).prototype,"apiPerfInstanceList",[s.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return[]}}),_=N(n.prototype,"apiPerfInstanceInfo",[s.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:null}),E=N(n.prototype,"apiPerformId",[s.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:null}),O=N(n.prototype,"findApiPerfInstancePage",[s.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(){var t=b(h().mark((function t(r){var n;return h().wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return n=d(d({},r),{},{type:"api-perform",orderParams:[{name:"createTime",orderType:"desc"}]}),t.next=3,f.Axios.post("/instance/findInstancePage",n);case 3:return 0===(n=t.sent).code&&(e.apiPerfInstanceList=n.data.dataList),t.abrupt("return",n);case 6:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()}}),P=N(n.prototype,"findApiPerfInstance",[s.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(){var t=b(h().mark((function t(r){var n;return h().wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return(n=new FormData).append("id",r),t.next=4,f.Axios.post("/apiPerfInstance/findApiPerfInstance",n);case 4:if(0===(n=t.sent).code)return e.apiPerfInstanceInfo=n.data,t.abrupt("return",n.data);t.next=8;break;case 8:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()}}),j=new n,x=r(684);function L(e){return(L="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}var S="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\api\\http\\perf\\components\\ApiPerfInstanceSinglePage.js";function I(){I=function(){return e};var e={},t=Object.prototype,r=t.hasOwnProperty,n=(h="function"==typeof Symbol?Symbol:{}).iterator||"@@iterator",o=h.asyncIterator||"@@asyncIterator",i=h.toStringTag||"@@toStringTag";function a(e,t,r){return Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{a({},"")}catch(t){a=function(e,t,r){return e[t]=r}}function c(e,t,r,n){var o,i,a,c;t=t&&t.prototype instanceof s?t:s,t=Object.create(t.prototype),n=new N(n||[]);return t._invoke=(o=e,i=r,a=n,c="suspendedStart",function(e,t){if("executing"===c)throw new Error("Generator is already running");if("completed"===c){if("throw"===e)throw t;return{value:void 0,done:!0}}for(a.method=e,a.arg=t;;){var r=a.delegate;if(r&&(r=function e(t,r){var n=t.iterator[r.method];if(void 0===n){if(r.delegate=null,"throw"===r.method){if(t.iterator.return&&(r.method="return",r.arg=void 0,e(t,r),"throw"===r.method))return l;r.method="throw",r.arg=new TypeError("The iterator does not provide a 'throw' method")}return l}return"throw"===(n=u(n,t.iterator,r.arg)).type?(r.method="throw",r.arg=n.arg,r.delegate=null,l):(n=n.arg)?n.done?(r[t.resultName]=n.value,r.next=t.nextLoc,"return"!==r.method&&(r.method="next",r.arg=void 0),r.delegate=null,l):n:(r.method="throw",r.arg=new TypeError("iterator result is not an object"),r.delegate=null,l)}(r,a))){if(r===l)continue;return r}if("next"===a.method)a.sent=a._sent=a.arg;else if("throw"===a.method){if("suspendedStart"===c)throw c="completed",a.arg;a.dispatchException(a.arg)}else"return"===a.method&&a.abrupt("return",a.arg);if(c="executing","normal"===(r=u(o,i,a)).type){if(c=a.done?"completed":"suspendedYield",r.arg===l)continue;return{value:r.arg,done:a.done}}"throw"===r.type&&(c="completed",a.method="throw",a.arg=r.arg)}}),t}function u(e,t,r){try{return{type:"normal",arg:e.call(t,r)}}catch(e){return{type:"throw",arg:e}}}e.wrap=c;var l={};function s(){}function f(){}function m(){}var h,p,d=((p=(p=(a(h={},n,(function(){return this})),Object.getPrototypeOf))&&p(p(w([]))))&&p!==t&&r.call(p,n)&&(h=p),m.prototype=s.prototype=Object.create(h));function y(e){["next","throw","return"].forEach((function(t){a(e,t,(function(e){return this._invoke(t,e)}))}))}function b(e,t){var n;this._invoke=function(o,i){function a(){return new t((function(n,a){!function n(o,i,a,c){var l;if("throw"!==(o=u(e[o],e,i)).type)return(i=(l=o.arg).value)&&"object"==L(i)&&r.call(i,"__await")?t.resolve(i.__await).then((function(e){n("next",e,a,c)}),(function(e){n("throw",e,a,c)})):t.resolve(i).then((function(e){l.value=e,a(l)}),(function(e){return n("throw",e,a,c)}));c(o.arg)}(o,i,n,a)}))}return n=n?n.then(a,a):a()}}function v(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function g(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function N(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(v,this),this.reset(!0)}function w(e){if(e){var t,o=e[n];if(o)return o.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length))return t=-1,(o=function n(){for(;++t<e.length;)if(r.call(e,t))return n.value=e[t],n.done=!1,n;return n.value=void 0,n.done=!0,n}).next=o}return{next:_}}function _(){return{value:void 0,done:!0}}return a(d,"constructor",f.prototype=m),a(m,"constructor",f),f.displayName=a(m,i,"GeneratorFunction"),e.isGeneratorFunction=function(e){return!!(e="function"==typeof e&&e.constructor)&&(e===f||"GeneratorFunction"===(e.displayName||e.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,m):(e.__proto__=m,a(e,i,"GeneratorFunction")),e.prototype=Object.create(d),e},e.awrap=function(e){return{__await:e}},y(b.prototype),a(b.prototype,o,(function(){return this})),e.AsyncIterator=b,e.async=function(t,r,n,o,i){void 0===i&&(i=Promise);var a=new b(c(t,r,n,o),i);return e.isGeneratorFunction(r)?a:a.next().then((function(e){return e.done?e.value:a.next()}))},y(d),a(d,i,"Generator"),a(d,n,(function(){return this})),a(d,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t,r=[];for(t in e)r.push(t);return r.reverse(),function t(){for(;r.length;){var n=r.pop();if(n in e)return t.value=n,t.done=!1,t}return t.done=!0,t}},e.values=w,N.prototype={constructor:N,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(g),!e)for(var t in this)"t"===t.charAt(0)&&r.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function n(r,n){return a.type="throw",a.arg=e,t.next=r,n&&(t.method="next",t.arg=void 0),!!n}for(var o=this.tryEntries.length-1;0<=o;--o){var i=this.tryEntries[o],a=i.completion;if("root"===i.tryLoc)return n("end");if(i.tryLoc<=this.prev){var c=r.call(i,"catchLoc"),u=r.call(i,"finallyLoc");if(c&&u){if(this.prev<i.catchLoc)return n(i.catchLoc,!0);if(this.prev<i.finallyLoc)return n(i.finallyLoc)}else if(c){if(this.prev<i.catchLoc)return n(i.catchLoc,!0)}else{if(!u)throw new Error("try statement without catch or finally");if(this.prev<i.finallyLoc)return n(i.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;0<=n;--n){var o=this.tryEntries[n];if(o.tryLoc<=this.prev&&r.call(o,"finallyLoc")&&this.prev<o.finallyLoc){var i=o;break}}var a=(i=i&&("break"===e||"continue"===e)&&i.tryLoc<=t&&t<=i.finallyLoc?null:i)?i.completion:{};return a.type=e,a.arg=t,i?(this.method="next",this.next=i.finallyLoc,l):this.complete(a)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),l},finish:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r=this.tryEntries[t];if(r.finallyLoc===e)return this.complete(r.completion,r.afterLoc),g(r),l}},catch:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r,n,o=this.tryEntries[t];if(o.tryLoc===e)return"throw"===(r=o.completion).type&&(n=r.arg,g(o)),n}throw new Error("illegal catch attempt")},delegateYield:function(e,t,r){return this.delegate={iterator:w(e),resultName:t,nextLoc:r},"next"===this.method&&(this.arg=void 0),l}},e}function k(e,t,r,n,o,i,a){try{var c=e[i](a),u=c.value}catch(e){return void r(e)}c.done?t(u):Promise.resolve(u).then(n,o)}function A(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,i=[],a=!0,c=!1;try{for(r=r.call(e);!(a=(n=r.next()).done)&&(i.push(n.value),!t||i.length!==t);a=!0);}catch(e){c=!0,o=e}finally{try{a||null==r.return||r.return()}finally{if(c)throw o}}return i}}(e,t)||function(e,t){var r;if(e)return"string"==typeof e?C(e,t):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?C(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function C(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}t.default=Object(u.observer)((function(e){var t=e.apiPerfInstanceId,r=(e=e.name,j.findApiPerfInstance),n=(p=A(Object(a.useState)(),2))[0],u=p[1],s=(p=A(Object(a.useState)(!0),2))[0],f=p[1],m=(p=A(Object(a.useState)(!1),2))[0],h=p[1],p=function(){e=I().mark((function e(){var n;return I().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,r(t);case 2:n=e.sent,f(!1),u(n),h(!0);case 6:case"end":return e.stop()}}),e)}));var e,n=function(){var t=this,r=arguments;return new Promise((function(n,o){var i=e.apply(t,r);function a(e){k(i,n,o,a,c,"next",e)}function c(e){k(i,n,o,a,c,"throw",e)}a(void 0)}))};return function(){return n.apply(this,arguments)}}(),d={tooltip:{trigger:"item"},legend:{orient:"vertical",left:"left"},series:[{name:"Access From",type:"pie",radius:"50%",data:[{name:"通过率",value:null==n?void 0:n.passNum},{name:"失败率",value:null==n?void 0:n.failNum}],emphasis:{itemStyle:{shadowBlur:10,shadowOffsetX:0,shadowColor:"rgba(0, 0, 0, 0.5)"}}}]};return c.a.createElement(c.a.Fragment,null,c.a.createElement("span",{className:"link-text",onClick:p,__source:{fileName:S,lineNumber:58,columnNumber:13}},e),c.a.createElement(o.default,{placement:"right",onClose:function(){h(!1)},open:m,width:900,destroyOnClose:!0,maskStyle:{background:"transparent"},contentWrapperStyle:{top:48,height:"calc(100% - 50px)"},closable:!1,__source:{fileName:S,lineNumber:59,columnNumber:13}},c.a.createElement(x.a,{breadItem:["历史详情"],icon:"api1",setOpen:h,__source:{fileName:S,lineNumber:69,columnNumber:17}}),c.a.createElement("div",{className:"result-spin-box",style:{margin:"0 10px",overflow:"hidden",height:"calc( 100% - 52px )"},__source:{fileName:S,lineNumber:74,columnNumber:17}},c.a.createElement(i.a,{spinning:s,__source:{fileName:S,lineNumber:75,columnNumber:17}},c.a.createElement("div",{className:"history-detail history-detail-box",__source:{fileName:S,lineNumber:76,columnNumber:21}},c.a.createElement("div",{className:"history-detail-all",__source:{fileName:S,lineNumber:77,columnNumber:25}},c.a.createElement("div",{className:"history-detail-all-box",__source:{fileName:S,lineNumber:78,columnNumber:29}},c.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:S,lineNumber:79,columnNumber:33}},c.a.createElement("div",{__source:{fileName:S,lineNumber:80,columnNumber:37}},"通过率"),c.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:S,lineNumber:81,columnNumber:37}},null==n?void 0:n.passRate)),c.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:S,lineNumber:83,columnNumber:33}},c.a.createElement("div",{__source:{fileName:S,lineNumber:84,columnNumber:37}},"失败率"),c.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:S,lineNumber:85,columnNumber:37}},null==n?void 0:n.errorRate)),c.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:S,lineNumber:87,columnNumber:33}},c.a.createElement("div",{__source:{fileName:S,lineNumber:88,columnNumber:37}},"总数"),c.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:S,lineNumber:89,columnNumber:37}},null==n?void 0:n.total)),c.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:S,lineNumber:91,columnNumber:33}},c.a.createElement("div",{__source:{fileName:S,lineNumber:92,columnNumber:37}},"通过数"),c.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:S,lineNumber:93,columnNumber:37}},null==n?void 0:n.passNum)),c.a.createElement("div",{className:"history-detail-all-item",__source:{fileName:S,lineNumber:96,columnNumber:33}},c.a.createElement("div",{__source:{fileName:S,lineNumber:97,columnNumber:37}},"未通过数"),c.a.createElement("div",{className:"history-detail-all-item-value",__source:{fileName:S,lineNumber:98,columnNumber:37}},null==n?void 0:n.failNum)))),c.a.createElement("div",{style:{width:"100%",height:"100%"},__source:{fileName:S,lineNumber:102,columnNumber:25}},c.a.createElement(l.a,{option:d,data:n,__source:{fileName:S,lineNumber:104,columnNumber:29}})))))))}))}}]);