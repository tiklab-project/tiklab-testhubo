(window.webpackJsonp=window.webpackJsonp||[]).push([[51],{1151:function(e,t,r){"use strict";r.r(t);var n=r(0),o=r.n(n),i=(r(56),r(33)),a=(r(95),r(77)),c=(r(31),r(18)),u=r(63),l=r(7),s=r(679),f=r(50),m=r(1077),p=r(891);function h(e){return(h="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}var y="D:\\a-dk-web\\thoughtware-teston-ui\\src\\repository\\common\\LeftNav.js";function d(){d=function(){return e};var e={},t=Object.prototype,r=t.hasOwnProperty,n=(p="function"==typeof Symbol?Symbol:{}).iterator||"@@iterator",o=p.asyncIterator||"@@asyncIterator",i=p.toStringTag||"@@toStringTag";function a(e,t,r){return Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{a({},"")}catch(t){a=function(e,t,r){return e[t]=r}}function c(e,t,r,n){var o,i,a,c;t=t&&t.prototype instanceof s?t:s,t=Object.create(t.prototype),n=new _(n||[]);return t._invoke=(o=e,i=r,a=n,c="suspendedStart",function(e,t){if("executing"===c)throw new Error("Generator is already running");if("completed"===c){if("throw"===e)throw t;return{value:void 0,done:!0}}for(a.method=e,a.arg=t;;){var r=a.delegate;if(r&&(r=function e(t,r){var n=t.iterator[r.method];if(void 0===n){if(r.delegate=null,"throw"===r.method){if(t.iterator.return&&(r.method="return",r.arg=void 0,e(t,r),"throw"===r.method))return l;r.method="throw",r.arg=new TypeError("The iterator does not provide a 'throw' method")}return l}return"throw"===(n=u(n,t.iterator,r.arg)).type?(r.method="throw",r.arg=n.arg,r.delegate=null,l):(n=n.arg)?n.done?(r[t.resultName]=n.value,r.next=t.nextLoc,"return"!==r.method&&(r.method="next",r.arg=void 0),r.delegate=null,l):n:(r.method="throw",r.arg=new TypeError("iterator result is not an object"),r.delegate=null,l)}(r,a))){if(r===l)continue;return r}if("next"===a.method)a.sent=a._sent=a.arg;else if("throw"===a.method){if("suspendedStart"===c)throw c="completed",a.arg;a.dispatchException(a.arg)}else"return"===a.method&&a.abrupt("return",a.arg);if(c="executing","normal"===(r=u(o,i,a)).type){if(c=a.done?"completed":"suspendedYield",r.arg===l)continue;return{value:r.arg,done:a.done}}"throw"===r.type&&(c="completed",a.method="throw",a.arg=r.arg)}}),t}function u(e,t,r){try{return{type:"normal",arg:e.call(t,r)}}catch(e){return{type:"throw",arg:e}}}e.wrap=c;var l={};function s(){}function f(){}function m(){}var p,y,v=((y=(y=(a(p={},n,(function(){return this})),Object.getPrototypeOf))&&y(y(S([]))))&&y!==t&&r.call(y,n)&&(p=y),m.prototype=s.prototype=Object.create(p));function g(e){["next","throw","return"].forEach((function(t){a(e,t,(function(e){return this._invoke(t,e)}))}))}function b(e,t){var n;this._invoke=function(o,i){function a(){return new t((function(n,a){!function n(o,i,a,c){var l;if("throw"!==(o=u(e[o],e,i)).type)return(i=(l=o.arg).value)&&"object"==h(i)&&r.call(i,"__await")?t.resolve(i.__await).then((function(e){n("next",e,a,c)}),(function(e){n("throw",e,a,c)})):t.resolve(i).then((function(e){l.value=e,a(l)}),(function(e){return n("throw",e,a,c)}));c(o.arg)}(o,i,n,a)}))}return n=n?n.then(a,a):a()}}function w(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function N(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function _(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(w,this),this.reset(!0)}function S(e){if(e){var t,o=e[n];if(o)return o.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length))return t=-1,(o=function n(){for(;++t<e.length;)if(r.call(e,t))return n.value=e[t],n.done=!1,n;return n.value=void 0,n.done=!0,n}).next=o}return{next:E}}function E(){return{value:void 0,done:!0}}return a(v,"constructor",f.prototype=m),a(m,"constructor",f),f.displayName=a(m,i,"GeneratorFunction"),e.isGeneratorFunction=function(e){return!!(e="function"==typeof e&&e.constructor)&&(e===f||"GeneratorFunction"===(e.displayName||e.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,m):(e.__proto__=m,a(e,i,"GeneratorFunction")),e.prototype=Object.create(v),e},e.awrap=function(e){return{__await:e}},g(b.prototype),a(b.prototype,o,(function(){return this})),e.AsyncIterator=b,e.async=function(t,r,n,o,i){void 0===i&&(i=Promise);var a=new b(c(t,r,n,o),i);return e.isGeneratorFunction(r)?a:a.next().then((function(e){return e.done?e.value:a.next()}))},g(v),a(v,i,"Generator"),a(v,n,(function(){return this})),a(v,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t,r=[];for(t in e)r.push(t);return r.reverse(),function t(){for(;r.length;){var n=r.pop();if(n in e)return t.value=n,t.done=!1,t}return t.done=!0,t}},e.values=S,_.prototype={constructor:_,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(N),!e)for(var t in this)"t"===t.charAt(0)&&r.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function n(r,n){return a.type="throw",a.arg=e,t.next=r,n&&(t.method="next",t.arg=void 0),!!n}for(var o=this.tryEntries.length-1;0<=o;--o){var i=this.tryEntries[o],a=i.completion;if("root"===i.tryLoc)return n("end");if(i.tryLoc<=this.prev){var c=r.call(i,"catchLoc"),u=r.call(i,"finallyLoc");if(c&&u){if(this.prev<i.catchLoc)return n(i.catchLoc,!0);if(this.prev<i.finallyLoc)return n(i.finallyLoc)}else if(c){if(this.prev<i.catchLoc)return n(i.catchLoc,!0)}else{if(!u)throw new Error("try statement without catch or finally");if(this.prev<i.finallyLoc)return n(i.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;0<=n;--n){var o=this.tryEntries[n];if(o.tryLoc<=this.prev&&r.call(o,"finallyLoc")&&this.prev<o.finallyLoc){var i=o;break}}var a=(i=i&&("break"===e||"continue"===e)&&i.tryLoc<=t&&t<=i.finallyLoc?null:i)?i.completion:{};return a.type=e,a.arg=t,i?(this.method="next",this.next=i.finallyLoc,l):this.complete(a)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),l},finish:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r=this.tryEntries[t];if(r.finallyLoc===e)return this.complete(r.completion,r.afterLoc),N(r),l}},catch:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r,n,o=this.tryEntries[t];if(o.tryLoc===e)return"throw"===(r=o.completion).type&&(n=r.arg,N(o)),n}throw new Error("illegal catch attempt")},delegateYield:function(e,t,r){return this.delegate={iterator:S(e),resultName:t,nextLoc:r},"next"===this.method&&(this.arg=void 0),l}},e}function v(e,t,r,n,o,i,a){try{var c=e[i](a),u=c.value}catch(e){return void r(e)}c.done?t(u):Promise.resolve(u).then(n,o)}function g(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,i=[],a=!0,c=!1;try{for(r=r.call(e);!(a=(n=r.next()).done)&&(i.push(n.value),!t||i.length!==t);a=!0);}catch(e){c=!0,o=e}finally{try{a||null==r.return||r.return()}finally{if(c)throw o}}return i}}(e,t)||function(e,t){var r;if(e)return"string"==typeof e?b(e,t):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?b(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function b(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var w=Object(u.inject)("repositoryStore","systemRoleStore","testcaseStore")(Object(u.observer)((function(e){var t=e.repositoryStore,r=e.systemRoleStore,u=e.testcaseStore,h=t.findRepository,b=t.repositoryRecent,w=t.findRepositoryRecentList,N=t.findRepositoryJoinList,_=u.setTestType,S=(t=g(Object(n.useState)(!1),2))[0],E=t[1],x=(u=g(Object(n.useState)(),2))[0],j=u[1],L=(u=(t=g(Object(n.useState)([]),2))[0],t[1]),O=Object(l.getUser)().userId,k=sessionStorage.getItem("repositoryId"),I=Object(f.g)(),P=(Object(n.useEffect)((function(){h(k).then((function(e){j(e)})),r.getInitProjectPermissions(O,k)}),[k]),function(){e=d().mark((function e(){var t,r,n;return d().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return E(!S),t=Object(l.getUser)().userId,e.next=4,w(t);case 4:if((r=e.sent).length<5)return e.next=8,N({userId:t});e.next=11;break;case 8:n=(n=e.sent).filter((function(e){return!r.some((function(t){return t.id===e.id}))})),r=r.concat(n.slice(0,5-r.length));case 11:r=r.slice(-5),L(r);case 13:case"end":return e.stop()}}),e)}));var e,t=function(){var t=this,r=arguments;return new Promise((function(n,o){var i=e.apply(t,r);function a(e){v(i,n,o,a,c,"next",e)}function c(e){v(i,n,o,a,c,"throw",e)}a(void 0)}))};return function(){return t.apply(this,arguments)}}()),R=o.a.createElement("div",{className:"ws-hover-box",__source:{fileName:y,lineNumber:108,columnNumber:9}},o.a.createElement("div",{style:{padding:"10px"},__source:{fileName:y,lineNumber:109,columnNumber:13}},o.a.createElement("div",{className:"ws-hover-box-title",__source:{fileName:y,lineNumber:110,columnNumber:17}},"切换仓库"),o.a.createElement("div",{style:{height:"210px"},__source:{fileName:y,lineNumber:111,columnNumber:17}},u&&u.map((function(e,t){if(!(4<t))return o.a.createElement("div",{className:"ws-hover-item ".concat(e.id===k?"ws-toggle-ws-select":""),key:e.id,onClick:function(){return U(e.id)},__source:{fileName:y,lineNumber:116,columnNumber:37}},o.a.createElement(c.default,{__source:{fileName:y,lineNumber:121,columnNumber:41}},o.a.createElement(p.a,{iconUrl:e.iconUrl,className:"ws-img-icon",__source:{fileName:y,lineNumber:122,columnNumber:45}}),e.name))})))),o.a.createElement("a",{className:"ws-toggle-repository_more",onClick:function(){return I.push("/repository-page")},__source:{fileName:y,lineNumber:134,columnNumber:13}},"查看更多")),U=function(t){sessionStorage.setItem("repositoryId",t),localStorage.setItem("leftRouter","/repository/detail"),b({repository:{id:t},userId:O}),e.history.push("/repository/detail/".concat(t)),E(!1)};return o.a.createElement(m.a,{menuData:[{icon:"layers",name:"概况",key:"overview",router:"/repository/detail"},{icon:"test-case-group",name:"测试用例",key:"testcase",router:"/repository/testcase"},{icon:"jihua",name:"测试计划",key:"testplan",router:"/repository/plan"},{icon:"baogao",name:"测试报告",key:"report",router:"/repository/report"}],clickAddRouter:function(e){_(null),localStorage.setItem("leftRouter",e.router),"overview"===e.key?I.push("/repository/detail/".concat(k)):I.push(e.router)},clickSetting:function(){localStorage.setItem("leftRouter","setting"),e.history.push("/repository/setting/detail")},diffHeader:function(){return o.a.createElement("li",{className:"ws-detail-left-nav-item-repository ",__source:{fileName:y,lineNumber:173,columnNumber:9}},o.a.createElement(i.default,{placement:"right",title:null==x?void 0:x.name,__source:{fileName:y,lineNumber:174,columnNumber:13}},o.a.createElement(a.default,{overlay:R,trigger:["click"],visible:S,onOpenChange:P,__source:{fileName:y,lineNumber:175,columnNumber:17}},o.a.createElement("div",{className:"ws-icon-box",__source:{fileName:y,lineNumber:181,columnNumber:21}},o.a.createElement("span",{style:{cursor:"pointer",margin:" 0 0 0 16px"},__source:{fileName:y,lineNumber:182,columnNumber:25}},o.a.createElement(p.a,{iconUrl:null==x?void 0:x.iconUrl,className:"repository-icon",__source:{fileName:y,lineNumber:183,columnNumber:30}})),o.a.createElement(s.a,{style:{cursor:"pointer"},className:"icon-s",icon:"xiala",__source:{fileName:y,lineNumber:185,columnNumber:25}})))))},__source:{fileName:y,lineNumber:197,columnNumber:9}})}))),N=(r(768),r(769),r(770),r(930),r(1021)),_=r(1022),S=r(1023),E=r(919),x=r(1024),j=r(892),L=r(1025),O=r(1026),k=r(1027),I=r(982),P=r(1028),R=r(893),U=r(1029),A=r(1030),G=r(1075),T="D:\\a-dk-web\\thoughtware-teston-ui\\src\\repository\\common\\RepositoryDetailLayout.js";function F(){return(F=Object.assign?Object.assign.bind():function(e){for(var t=1;t<arguments.length;t++){var r,n=arguments[t];for(r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e}).apply(this,arguments)}t.default=function(e){var t={categoryStore:N.a,apiUnitStore:_.a,apiSceneStore:j.a,apiPerfStore:S.a,appSceneStore:E.a,appPerfStore:x.a,funcUnitStore:L.a,webSceneStore:O.a,webPerfStore:k.a,testPlanStore:I.a,workItemStore:P.a,apiEnvStore:R.a,appEnvStore:U.a,webEnvStore:A.a};return o.a.createElement(u.Provider,F({},t,{__source:{fileName:T,lineNumber:53,columnNumber:9}}),o.a.createElement(G.a,F({left:o.a.createElement(w,F({},e,{__source:{fileName:T,lineNumber:55,columnNumber:23}}))},e,{__source:{fileName:T,lineNumber:54,columnNumber:13}})))}},768:function(e,t,r){},769:function(e,t,r){},770:function(e,t,r){},891:function(e,t,r){"use strict";var n=r(0),o=r.n(n),i=r(400),a=r(401),c=r(402),u=r(403),l=r(404);function s(){return(s=Object.assign?Object.assign.bind():function(e){for(var t=1;t<arguments.length;t++){var r,n=arguments[t];for(r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e}).apply(this,arguments)}t.a=function(e){var t=e.iconUrl,r=e.className,n={"pi1.png":i.default,"pi2.png":a.default,"pi3.png":c.default,"pi4.png":u.default,"pi5.png":l.default};return o.a.createElement(o.a.Fragment,null,function(){for(var i in n)if(null!=t&&t.includes(i))return o.a.createElement("img",s({src:n[i],alt:"icon",className:r},e,{__source:{fileName:"D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\RepositoryIcon.js",lineNumber:22,columnNumber:24}}))}())}}}]);