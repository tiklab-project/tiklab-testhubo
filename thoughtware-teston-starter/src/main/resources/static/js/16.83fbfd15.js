(window.webpackJsonp=window.webpackJsonp||[]).push([[16],{630:function(e,t,r){"use strict";function n(e,t){return i.createElement(u.a,Object(o.a)(Object(o.a)({},e),{},{ref:t,icon:a}))}var o=r(6),i=r(0),a={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M890.5 755.3L537.9 269.2c-12.8-17.6-39-17.6-51.7 0L133.5 755.3A8 8 0 00140 768h75c5.1 0 9.9-2.5 12.9-6.6L512 369.8l284.1 391.6c3 4.1 7.8 6.6 12.9 6.6h75c6.5 0 10.3-7.4 6.5-12.7z"}}]},name:"up",theme:"outlined"},u=r(34);n.displayName="UpOutlined",t.a=i.forwardRef(n)},993:function(e,t,r){"use strict";var n=r(21),o=r(19);function i(e){return(i="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function a(){a=function(){return e};var e={},t=Object.prototype,r=t.hasOwnProperty,n=(d="function"==typeof Symbol?Symbol:{}).iterator||"@@iterator",o=d.asyncIterator||"@@asyncIterator",u=d.toStringTag||"@@toStringTag";function c(e,t,r){return Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{c({},"")}catch(t){c=function(e,t,r){return e[t]=r}}function l(e,t,r,n){var o,i,a,u;t=t&&t.prototype instanceof p?t:p,t=Object.create(t.prototype),n=new S(n||[]);return t._invoke=(o=e,i=r,a=n,u="suspendedStart",function(e,t){if("executing"===u)throw new Error("Generator is already running");if("completed"===u){if("throw"===e)throw t;return{value:void 0,done:!0}}for(a.method=e,a.arg=t;;){var r=a.delegate;if(r&&(r=function e(t,r){var n=t.iterator[r.method];if(void 0===n){if(r.delegate=null,"throw"===r.method){if(t.iterator.return&&(r.method="return",r.arg=void 0,e(t,r),"throw"===r.method))return s;r.method="throw",r.arg=new TypeError("The iterator does not provide a 'throw' method")}return s}return"throw"===(n=f(n,t.iterator,r.arg)).type?(r.method="throw",r.arg=n.arg,r.delegate=null,s):(n=n.arg)?n.done?(r[t.resultName]=n.value,r.next=t.nextLoc,"return"!==r.method&&(r.method="next",r.arg=void 0),r.delegate=null,s):n:(r.method="throw",r.arg=new TypeError("iterator result is not an object"),r.delegate=null,s)}(r,a))){if(r===s)continue;return r}if("next"===a.method)a.sent=a._sent=a.arg;else if("throw"===a.method){if("suspendedStart"===u)throw u="completed",a.arg;a.dispatchException(a.arg)}else"return"===a.method&&a.abrupt("return",a.arg);if(u="executing","normal"===(r=f(o,i,a)).type){if(u=a.done?"completed":"suspendedYield",r.arg===s)continue;return{value:r.arg,done:a.done}}"throw"===r.type&&(u="completed",a.method="throw",a.arg=r.arg)}}),t}function f(e,t,r){try{return{type:"normal",arg:e.call(t,r)}}catch(e){return{type:"throw",arg:e}}}e.wrap=l;var s={};function p(){}function h(){}function m(){}var d,b,y=((b=(b=(c(d={},n,(function(){return this})),Object.getPrototypeOf))&&b(b(N([]))))&&b!==t&&r.call(b,n)&&(d=b),m.prototype=p.prototype=Object.create(d));function v(e){["next","throw","return"].forEach((function(t){c(e,t,(function(e){return this._invoke(t,e)}))}))}function w(e,t){var n;this._invoke=function(o,a){function u(){return new t((function(n,u){!function n(o,a,u,c){var l;if("throw"!==(o=f(e[o],e,a)).type)return(a=(l=o.arg).value)&&"object"==i(a)&&r.call(a,"__await")?t.resolve(a.__await).then((function(e){n("next",e,u,c)}),(function(e){n("throw",e,u,c)})):t.resolve(a).then((function(e){l.value=e,u(l)}),(function(e){return n("throw",e,u,c)}));c(o.arg)}(o,a,n,u)}))}return n=n?n.then(u,u):u()}}function g(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function x(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function S(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(g,this),this.reset(!0)}function N(e){if(e){var t,o=e[n];if(o)return o.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length))return t=-1,(o=function n(){for(;++t<e.length;)if(r.call(e,t))return n.value=e[t],n.done=!1,n;return n.value=void 0,n.done=!0,n}).next=o}return{next:P}}function P(){return{value:void 0,done:!0}}return c(y,"constructor",h.prototype=m),c(m,"constructor",h),h.displayName=c(m,u,"GeneratorFunction"),e.isGeneratorFunction=function(e){return!!(e="function"==typeof e&&e.constructor)&&(e===h||"GeneratorFunction"===(e.displayName||e.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,m):(e.__proto__=m,c(e,u,"GeneratorFunction")),e.prototype=Object.create(y),e},e.awrap=function(e){return{__await:e}},v(w.prototype),c(w.prototype,o,(function(){return this})),e.AsyncIterator=w,e.async=function(t,r,n,o,i){void 0===i&&(i=Promise);var a=new w(l(t,r,n,o),i);return e.isGeneratorFunction(r)?a:a.next().then((function(e){return e.done?e.value:a.next()}))},v(y),c(y,u,"Generator"),c(y,n,(function(){return this})),c(y,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t,r=[];for(t in e)r.push(t);return r.reverse(),function t(){for(;r.length;){var n=r.pop();if(n in e)return t.value=n,t.done=!1,t}return t.done=!0,t}},e.values=N,S.prototype={constructor:S,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(x),!e)for(var t in this)"t"===t.charAt(0)&&r.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function n(r,n){return a.type="throw",a.arg=e,t.next=r,n&&(t.method="next",t.arg=void 0),!!n}for(var o=this.tryEntries.length-1;0<=o;--o){var i=this.tryEntries[o],a=i.completion;if("root"===i.tryLoc)return n("end");if(i.tryLoc<=this.prev){var u=r.call(i,"catchLoc"),c=r.call(i,"finallyLoc");if(u&&c){if(this.prev<i.catchLoc)return n(i.catchLoc,!0);if(this.prev<i.finallyLoc)return n(i.finallyLoc)}else if(u){if(this.prev<i.catchLoc)return n(i.catchLoc,!0)}else{if(!c)throw new Error("try statement without catch or finally");if(this.prev<i.finallyLoc)return n(i.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;0<=n;--n){var o=this.tryEntries[n];if(o.tryLoc<=this.prev&&r.call(o,"finallyLoc")&&this.prev<o.finallyLoc){var i=o;break}}var a=(i=i&&("break"===e||"continue"===e)&&i.tryLoc<=t&&t<=i.finallyLoc?null:i)?i.completion:{};return a.type=e,a.arg=t,i?(this.method="next",this.next=i.finallyLoc,s):this.complete(a)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),s},finish:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r=this.tryEntries[t];if(r.finallyLoc===e)return this.complete(r.completion,r.afterLoc),x(r),s}},catch:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r,n,o=this.tryEntries[t];if(o.tryLoc===e)return"throw"===(r=o.completion).type&&(n=r.arg,x(o)),n}throw new Error("illegal catch attempt")},delegateYield:function(e,t,r){return this.delegate={iterator:N(e),resultName:t,nextLoc:r},"next"===this.method&&(this.arg=void 0),s}},e}function u(e,t,r,n,o,i,a){try{var u=e[i](a),c=u.value}catch(e){return void r(e)}u.done?t(c):Promise.resolve(c).then(n,o)}function c(e){return function(){var t=this,r=arguments;return new Promise((function(n,o){var i=e.apply(t,r);function a(e){u(i,n,o,a,c,"next",e)}function c(e){u(i,n,o,a,c,"throw",e)}a(void 0)}))}}function l(e,t,r,n){r&&Object.defineProperty(e,t,{enumerable:r.enumerable,configurable:r.configurable,writable:r.writable,value:r.initializer?r.initializer.call(n):void 0})}function f(e,t){for(var r=0;r<t.length;r++){var n=t[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}function s(e,t,r,n,o){var i={};return Object.keys(n).forEach((function(e){i[e]=n[e]})),i.enumerable=!!i.enumerable,i.configurable=!!i.configurable,("value"in i||i.initializer)&&(i.writable=!0),i=r.slice().reverse().reduce((function(r,n){return n(e,t,r)||r}),i),o&&void 0!==i.initializer&&(i.value=i.initializer?i.initializer.call(o):void 0,i.initializer=void 0),void 0===i.initializer&&(Object.defineProperty(e,t,i),i=null),i}var p=s((r=function(e,t,r){return t&&f(e.prototype,t),r&&f(e,r),Object.defineProperty(e,"prototype",{writable:!1}),e}((function e(){if(!(this instanceof e))throw new TypeError("Cannot call a class as a function");l(this,"webPerfTestResult",p,this),l(this,"responseResult",h,this),l(this,"agentId",m,this),l(this,"webPerfExecute",d,this),l(this,"exeResult",b,this),l(this,"getAgent",y,this)}))).prototype,"webPerfTestResult",[n.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:null}),h=s(r.prototype,"responseResult",[n.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:null}),m=s(r.prototype,"agentId",[n.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:null}),d=s(r.prototype,"webPerfExecute",[n.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(){var t=c(a().mark((function t(r){var n;return a().wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return n={webPerfCase:{id:r}},t.next=3,o.Axios.post("/webPerfTestDispatch/execute",n);case 3:if(0===(n=t.sent).code)return e.webPerfTestResult=n.data,t.abrupt("return",n.data);t.next=7;break;case 7:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()}}),b=s(r.prototype,"exeResult",[n.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=c(a().mark((function e(t){var r;return a().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return r={webPerfCase:{id:t}},e.next=3,o.Axios.post("/webPerfTestDispatch/exeResult",r);case 3:if(0===(r=e.sent).code)return e.abrupt("return",r.data);e.next=6;break;case 6:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),y=s(r.prototype,"getAgent",[n.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(t){e.agentId=t}}});n=new r;t.a=n},998:function(e,t,r){"use strict";r(475);var n=r(371),o=r(0),i=r.n(o),a=(r(124),r(87)),u=(r(370),r(337)),c=(r(159),r(129)),l=r(78),f=r(927);function s(e){return(s="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}var p="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\web\\perf\\components\\webPerformBindScene.js";function h(e,t){var r,n=Object.keys(e);return Object.getOwnPropertySymbols&&(r=Object.getOwnPropertySymbols(e),t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)),n}function m(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?h(Object(r),!0).forEach((function(t){var n,o;n=e,o=r[t=t],t in n?Object.defineProperty(n,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):n[t]=o})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):h(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function d(){d=function(){return e};var e={},t=Object.prototype,r=t.hasOwnProperty,n=(m="function"==typeof Symbol?Symbol:{}).iterator||"@@iterator",o=m.asyncIterator||"@@asyncIterator",i=m.toStringTag||"@@toStringTag";function a(e,t,r){return Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{a({},"")}catch(t){a=function(e,t,r){return e[t]=r}}function u(e,t,r,n){var o,i,a,u;t=t&&t.prototype instanceof f?t:f,t=Object.create(t.prototype),n=new S(n||[]);return t._invoke=(o=e,i=r,a=n,u="suspendedStart",function(e,t){if("executing"===u)throw new Error("Generator is already running");if("completed"===u){if("throw"===e)throw t;return{value:void 0,done:!0}}for(a.method=e,a.arg=t;;){var r=a.delegate;if(r&&(r=function e(t,r){var n=t.iterator[r.method];if(void 0===n){if(r.delegate=null,"throw"===r.method){if(t.iterator.return&&(r.method="return",r.arg=void 0,e(t,r),"throw"===r.method))return l;r.method="throw",r.arg=new TypeError("The iterator does not provide a 'throw' method")}return l}return"throw"===(n=c(n,t.iterator,r.arg)).type?(r.method="throw",r.arg=n.arg,r.delegate=null,l):(n=n.arg)?n.done?(r[t.resultName]=n.value,r.next=t.nextLoc,"return"!==r.method&&(r.method="next",r.arg=void 0),r.delegate=null,l):n:(r.method="throw",r.arg=new TypeError("iterator result is not an object"),r.delegate=null,l)}(r,a))){if(r===l)continue;return r}if("next"===a.method)a.sent=a._sent=a.arg;else if("throw"===a.method){if("suspendedStart"===u)throw u="completed",a.arg;a.dispatchException(a.arg)}else"return"===a.method&&a.abrupt("return",a.arg);if(u="executing","normal"===(r=c(o,i,a)).type){if(u=a.done?"completed":"suspendedYield",r.arg===l)continue;return{value:r.arg,done:a.done}}"throw"===r.type&&(u="completed",a.method="throw",a.arg=r.arg)}}),t}function c(e,t,r){try{return{type:"normal",arg:e.call(t,r)}}catch(e){return{type:"throw",arg:e}}}e.wrap=u;var l={};function f(){}function p(){}function h(){}var m,b,y=((b=(b=(a(m={},n,(function(){return this})),Object.getPrototypeOf))&&b(b(N([]))))&&b!==t&&r.call(b,n)&&(m=b),h.prototype=f.prototype=Object.create(m));function v(e){["next","throw","return"].forEach((function(t){a(e,t,(function(e){return this._invoke(t,e)}))}))}function w(e,t){var n;this._invoke=function(o,i){function a(){return new t((function(n,a){!function n(o,i,a,u){var l;if("throw"!==(o=c(e[o],e,i)).type)return(i=(l=o.arg).value)&&"object"==s(i)&&r.call(i,"__await")?t.resolve(i.__await).then((function(e){n("next",e,a,u)}),(function(e){n("throw",e,a,u)})):t.resolve(i).then((function(e){l.value=e,a(l)}),(function(e){return n("throw",e,a,u)}));u(o.arg)}(o,i,n,a)}))}return n=n?n.then(a,a):a()}}function g(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function x(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function S(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(g,this),this.reset(!0)}function N(e){if(e){var t,o=e[n];if(o)return o.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length))return t=-1,(o=function n(){for(;++t<e.length;)if(r.call(e,t))return n.value=e[t],n.done=!1,n;return n.value=void 0,n.done=!0,n}).next=o}return{next:P}}function P(){return{value:void 0,done:!0}}return a(y,"constructor",p.prototype=h),a(h,"constructor",p),p.displayName=a(h,i,"GeneratorFunction"),e.isGeneratorFunction=function(e){return!!(e="function"==typeof e&&e.constructor)&&(e===p||"GeneratorFunction"===(e.displayName||e.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,h):(e.__proto__=h,a(e,i,"GeneratorFunction")),e.prototype=Object.create(y),e},e.awrap=function(e){return{__await:e}},v(w.prototype),a(w.prototype,o,(function(){return this})),e.AsyncIterator=w,e.async=function(t,r,n,o,i){void 0===i&&(i=Promise);var a=new w(u(t,r,n,o),i);return e.isGeneratorFunction(r)?a:a.next().then((function(e){return e.done?e.value:a.next()}))},v(y),a(y,i,"Generator"),a(y,n,(function(){return this})),a(y,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t,r=[];for(t in e)r.push(t);return r.reverse(),function t(){for(;r.length;){var n=r.pop();if(n in e)return t.value=n,t.done=!1,t}return t.done=!0,t}},e.values=N,S.prototype={constructor:S,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(x),!e)for(var t in this)"t"===t.charAt(0)&&r.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function n(r,n){return a.type="throw",a.arg=e,t.next=r,n&&(t.method="next",t.arg=void 0),!!n}for(var o=this.tryEntries.length-1;0<=o;--o){var i=this.tryEntries[o],a=i.completion;if("root"===i.tryLoc)return n("end");if(i.tryLoc<=this.prev){var u=r.call(i,"catchLoc"),c=r.call(i,"finallyLoc");if(u&&c){if(this.prev<i.catchLoc)return n(i.catchLoc,!0);if(this.prev<i.finallyLoc)return n(i.finallyLoc)}else if(u){if(this.prev<i.catchLoc)return n(i.catchLoc,!0)}else{if(!c)throw new Error("try statement without catch or finally");if(this.prev<i.finallyLoc)return n(i.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;0<=n;--n){var o=this.tryEntries[n];if(o.tryLoc<=this.prev&&r.call(o,"finallyLoc")&&this.prev<o.finallyLoc){var i=o;break}}var a=(i=i&&("break"===e||"continue"===e)&&i.tryLoc<=t&&t<=i.finallyLoc?null:i)?i.completion:{};return a.type=e,a.arg=t,i?(this.method="next",this.next=i.finallyLoc,l):this.complete(a)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),l},finish:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r=this.tryEntries[t];if(r.finallyLoc===e)return this.complete(r.completion,r.afterLoc),x(r),l}},catch:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r,n,o=this.tryEntries[t];if(o.tryLoc===e)return"throw"===(r=o.completion).type&&(n=r.arg,x(o)),n}throw new Error("illegal catch attempt")},delegateYield:function(e,t,r){return this.delegate={iterator:N(e),resultName:t,nextLoc:r},"next"===this.method&&(this.arg=void 0),l}},e}function b(e,t,r,n,o,i,a){try{var u=e[i](a),c=u.value}catch(e){return void r(e)}u.done?t(c):Promise.resolve(c).then(n,o)}function y(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,i=[],a=!0,u=!1;try{for(r=r.call(e);!(a=(n=r.next()).done)&&(i.push(n.value),!t||i.length!==t);a=!0);}catch(e){u=!0,o=e}finally{try{a||null==r.return||r.return()}finally{if(u)throw o}}return i}}(e,t)||function(e,t){var r;if(e)return"string"==typeof e?v(e,t):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?v(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function v(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var w,g=Object(l.inject)("webSceneStore")(Object(l.observer)((function(e){var t=e.webSceneStore,r=e.webPerfStepStore,n=e.webPerfId,o=t.findWebSceneList,u=(e=t.webSceneList,r.bindWebScene),l=r.findWebPerfStepList,s=(r=(t=y(i.a.useState(!1),2))[0],t[1]),h=(t=y(i.a.useState([]),2))[0],v=t[1],w=sessionStorage.getItem("repositoryId");t=function(){e=d().mark((function e(){return d().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:u(h).then((function(){return l(n)})),s(!1);case 2:case"end":return e.stop()}}),e)}));var e,t=function(){var t=this,r=arguments;return new Promise((function(n,o){var i=e.apply(t,r);function a(e){b(i,n,o,a,u,"next",e)}function u(e){b(i,n,o,a,u,"throw",e)}a(void 0)}))};return function(){return t.apply(this,arguments)}}();return i.a.createElement(i.a.Fragment,null,i.a.createElement(f.a,{className:"pi-icon-btn-grey",name:"关联场景",onClick:function(){o({repositoryId:w,caseType:"web-scene",testType:"ui"}),s(!0)},__source:{fileName:p,lineNumber:60,columnNumber:13}}),i.a.createElement(c.default,{destroyOnClose:!0,title:"关联场景",visible:r,onCancel:function(){s(!1)},onOk:t,okText:"提交",cancelText:"取消",centered:!0,width:1e3,__source:{fileName:p,lineNumber:65,columnNumber:13}},i.a.createElement(a.default,{columns:[{title:"场景名称",dataIndex:["testCase","name"],key:"name"},{title:"类型",dataIndex:["testCase","testType"],key:"testType"},{title:"创建时间",dataIndex:["testCase","createTime"],key:"createTime"}],dataSource:e,rowKey:function(e){return e.id},rowSelection:m({},{onChange:function(e,t){v(e)}}),pagination:!1,__source:{fileName:p,lineNumber:77,columnNumber:17}})))}))),x=r(203),S=r(21),N=r(19);function P(e){return(P="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function E(){E=function(){return e};var e={},t=Object.prototype,r=t.hasOwnProperty,n=(h="function"==typeof Symbol?Symbol:{}).iterator||"@@iterator",o=h.asyncIterator||"@@asyncIterator",i=h.toStringTag||"@@toStringTag";function a(e,t,r){return Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{a({},"")}catch(t){a=function(e,t,r){return e[t]=r}}function u(e,t,r,n){var o,i,a,u;t=t&&t.prototype instanceof f?t:f,t=Object.create(t.prototype),n=new g(n||[]);return t._invoke=(o=e,i=r,a=n,u="suspendedStart",function(e,t){if("executing"===u)throw new Error("Generator is already running");if("completed"===u){if("throw"===e)throw t;return{value:void 0,done:!0}}for(a.method=e,a.arg=t;;){var r=a.delegate;if(r&&(r=function e(t,r){var n=t.iterator[r.method];if(void 0===n){if(r.delegate=null,"throw"===r.method){if(t.iterator.return&&(r.method="return",r.arg=void 0,e(t,r),"throw"===r.method))return l;r.method="throw",r.arg=new TypeError("The iterator does not provide a 'throw' method")}return l}return"throw"===(n=c(n,t.iterator,r.arg)).type?(r.method="throw",r.arg=n.arg,r.delegate=null,l):(n=n.arg)?n.done?(r[t.resultName]=n.value,r.next=t.nextLoc,"return"!==r.method&&(r.method="next",r.arg=void 0),r.delegate=null,l):n:(r.method="throw",r.arg=new TypeError("iterator result is not an object"),r.delegate=null,l)}(r,a))){if(r===l)continue;return r}if("next"===a.method)a.sent=a._sent=a.arg;else if("throw"===a.method){if("suspendedStart"===u)throw u="completed",a.arg;a.dispatchException(a.arg)}else"return"===a.method&&a.abrupt("return",a.arg);if(u="executing","normal"===(r=c(o,i,a)).type){if(u=a.done?"completed":"suspendedYield",r.arg===l)continue;return{value:r.arg,done:a.done}}"throw"===r.type&&(u="completed",a.method="throw",a.arg=r.arg)}}),t}function c(e,t,r){try{return{type:"normal",arg:e.call(t,r)}}catch(e){return{type:"throw",arg:e}}}e.wrap=u;var l={};function f(){}function s(){}function p(){}var h,m,d=((m=(m=(a(h={},n,(function(){return this})),Object.getPrototypeOf))&&m(m(x([]))))&&m!==t&&r.call(m,n)&&(h=m),p.prototype=f.prototype=Object.create(h));function b(e){["next","throw","return"].forEach((function(t){a(e,t,(function(e){return this._invoke(t,e)}))}))}function y(e,t){var n;this._invoke=function(o,i){function a(){return new t((function(n,a){!function n(o,i,a,u){var l;if("throw"!==(o=c(e[o],e,i)).type)return(i=(l=o.arg).value)&&"object"==P(i)&&r.call(i,"__await")?t.resolve(i.__await).then((function(e){n("next",e,a,u)}),(function(e){n("throw",e,a,u)})):t.resolve(i).then((function(e){l.value=e,a(l)}),(function(e){return n("throw",e,a,u)}));u(o.arg)}(o,i,n,a)}))}return n=n?n.then(a,a):a()}}function v(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function w(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function g(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(v,this),this.reset(!0)}function x(e){if(e){var t,o=e[n];if(o)return o.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length))return t=-1,(o=function n(){for(;++t<e.length;)if(r.call(e,t))return n.value=e[t],n.done=!1,n;return n.value=void 0,n.done=!0,n}).next=o}return{next:S}}function S(){return{value:void 0,done:!0}}return a(d,"constructor",s.prototype=p),a(p,"constructor",s),s.displayName=a(p,i,"GeneratorFunction"),e.isGeneratorFunction=function(e){return!!(e="function"==typeof e&&e.constructor)&&(e===s||"GeneratorFunction"===(e.displayName||e.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,p):(e.__proto__=p,a(e,i,"GeneratorFunction")),e.prototype=Object.create(d),e},e.awrap=function(e){return{__await:e}},b(y.prototype),a(y.prototype,o,(function(){return this})),e.AsyncIterator=y,e.async=function(t,r,n,o,i){void 0===i&&(i=Promise);var a=new y(u(t,r,n,o),i);return e.isGeneratorFunction(r)?a:a.next().then((function(e){return e.done?e.value:a.next()}))},b(d),a(d,i,"Generator"),a(d,n,(function(){return this})),a(d,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t,r=[];for(t in e)r.push(t);return r.reverse(),function t(){for(;r.length;){var n=r.pop();if(n in e)return t.value=n,t.done=!1,t}return t.done=!0,t}},e.values=x,g.prototype={constructor:g,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(w),!e)for(var t in this)"t"===t.charAt(0)&&r.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function n(r,n){return a.type="throw",a.arg=e,t.next=r,n&&(t.method="next",t.arg=void 0),!!n}for(var o=this.tryEntries.length-1;0<=o;--o){var i=this.tryEntries[o],a=i.completion;if("root"===i.tryLoc)return n("end");if(i.tryLoc<=this.prev){var u=r.call(i,"catchLoc"),c=r.call(i,"finallyLoc");if(u&&c){if(this.prev<i.catchLoc)return n(i.catchLoc,!0);if(this.prev<i.finallyLoc)return n(i.finallyLoc)}else if(u){if(this.prev<i.catchLoc)return n(i.catchLoc,!0)}else{if(!c)throw new Error("try statement without catch or finally");if(this.prev<i.finallyLoc)return n(i.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;0<=n;--n){var o=this.tryEntries[n];if(o.tryLoc<=this.prev&&r.call(o,"finallyLoc")&&this.prev<o.finallyLoc){var i=o;break}}var a=(i=i&&("break"===e||"continue"===e)&&i.tryLoc<=t&&t<=i.finallyLoc?null:i)?i.completion:{};return a.type=e,a.arg=t,i?(this.method="next",this.next=i.finallyLoc,l):this.complete(a)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),l},finish:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r=this.tryEntries[t];if(r.finallyLoc===e)return this.complete(r.completion,r.afterLoc),w(r),l}},catch:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var r,n,o=this.tryEntries[t];if(o.tryLoc===e)return"throw"===(r=o.completion).type&&(n=r.arg,w(o)),n}throw new Error("illegal catch attempt")},delegateYield:function(e,t,r){return this.delegate={iterator:x(e),resultName:t,nextLoc:r},"next"===this.method&&(this.arg=void 0),l}},e}function L(e,t,r,n,o,i,a){try{var u=e[i](a),c=u.value}catch(e){return void r(e)}u.done?t(c):Promise.resolve(c).then(n,o)}function _(e){return function(){var t=this,r=arguments;return new Promise((function(n,o){var i=e.apply(t,r);function a(e){L(i,n,o,a,u,"next",e)}function u(e){L(i,n,o,a,u,"throw",e)}a(void 0)}))}}function O(e,t,r,n){r&&Object.defineProperty(e,t,{enumerable:r.enumerable,configurable:r.configurable,writable:r.writable,value:r.initializer?r.initializer.call(n):void 0})}function j(e,t){for(var r=0;r<t.length;r++){var n=t[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}function k(e,t,r,n,o){var i={};return Object.keys(n).forEach((function(e){i[e]=n[e]})),i.enumerable=!!i.enumerable,i.configurable=!!i.configurable,("value"in i||i.initializer)&&(i.writable=!0),i=r.slice().reverse().reduce((function(r,n){return n(e,t,r)||r}),i),o&&void 0!==i.initializer&&(i.value=i.initializer?i.initializer.call(o):void 0,i.initializer=void 0),void 0===i.initializer&&(Object.defineProperty(e,t,i),i=null),i}var I=k((w=function(e,t,r){return t&&j(e.prototype,t),r&&j(e,r),Object.defineProperty(e,"prototype",{writable:!1}),e}((function e(){if(!(this instanceof e))throw new TypeError("Cannot call a class as a function");O(this,"webPerfStepList",I,this),O(this,"webPerfStepInfo",T,this),O(this,"webPerfId",C,this),O(this,"bindWebScene",A,this),O(this,"findWebPerfStepPage",z,this),O(this,"findWebPerfStepList",W,this),O(this,"findWebPerfStep",G,this),O(this,"createWebPerfStep",F,this),O(this,"updateWebPerfStep",D,this),O(this,"deleteWebPerfStep",R,this)}))).prototype,"webPerfStepList",[S.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return[]}}),T=k(w.prototype,"webPerfStepInfo",[S.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return{}}}),C=k(w.prototype,"webPerfId",[S.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:null}),A=k(w.prototype,"bindWebScene",[S.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(){var t=_(E().mark((function t(r){var n,o;return E().wrap((function(t){for(;;)switch(t.prev=t.next){case 0:for(n=[],o=0;o<r.length;o++)n.push({webPerf:{id:e.webPerfId},webScene:{id:r[o]}});return t.next=4,N.Axios.post("/webPerfStep/bindWebScene",n);case 4:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()}}),z=k(w.prototype,"findWebPerfStepPage",[S.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(){var t=_(E().mark((function t(r){var n;return E().wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return e.webPerfId=r,n={webPerfId:r,orderParams:[{name:"createTime",orderType:"asc"}]},t.next=4,N.Axios.post("/webPerfStep/findWebPerfStepPage",n);case 4:if(0===(n=t.sent).code)return e.webPerfStepList=n.data.dataList,t.abrupt("return",n.data.dataList);t.next=8;break;case 8:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()}}),W=k(w.prototype,"findWebPerfStepList",[S.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(){var t=_(E().mark((function t(r){var n;return E().wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return e.webPerfId=r,n={webPerfId:r,orderParams:[{name:"createTime",orderType:"asc"}]},t.next=4,N.Axios.post("/webPerfStep/findWebPerfStepList",n);case 4:if(0===(n=t.sent).code)return e.webPerfStepList=n.data,t.abrupt("return",n.data);t.next=8;break;case 8:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()}}),G=k(w.prototype,"findWebPerfStep",[S.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(){var t=_(E().mark((function t(r){var n;return E().wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return(n=new FormData).append("id",r),t.next=4,N.Axios.post("/webPerfStep/findWebPerfStep",n);case 4:if(0===(n=t.sent).code)return e.webPerfStepInfo=n.data,t.abrupt("return",n.data);t.next=8;break;case 8:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()}}),F=k(w.prototype,"createWebPerfStep",[S.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=_(E().mark((function e(t){return E().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,N.Axios.post("/webPerfStep/createWebPerfStep",t);case 2:return e.abrupt("return",e.sent);case 3:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),D=k(w.prototype,"updateWebPerfStep",[S.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=_(E().mark((function e(t){return E().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,N.Axios.post("/webPerfStep/updateWebPerfStep",t);case 2:return e.abrupt("return",e.sent);case 3:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),R=k(w.prototype,"deleteWebPerfStep",[S.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=_(E().mark((function e(t){var r;return E().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return(r=new FormData).append("id",t),e.next=4,N.Axios.post("/webPerfStep/deleteWebPerfStep",r);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),Y=new w,K="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\web\\perf\\components\\webPerfStepList.js",M=Object(l.observer)((function(e){var t=Y.findWebPerfStepList,r=Y.webPerfStepList,n=Y.deleteWebPerfStep,c=[{title:"场景名称",dataIndex:["webScene","testCase","name"],key:"name",render:function(t,r){return e.type?i.a.createElement("a",{onClick:function(){return f(r.webScene.id)},__source:{fileName:K,lineNumber:18,columnNumber:30}},t):i.a.createElement("span",{__source:{fileName:K,lineNumber:18,columnNumber:98}},t)}},{title:"创建时间",dataIndex:"createTime",key:"createTime"},{title:"操作",dataIndex:"operation",key:"operation",width:"120",render:function(e,r){return i.a.createElement(u.default,{title:"确定删除？",onConfirm:function(){return n(r.id).then((function(){return t(l)}))},okText:"确定",cancelText:"取消",__source:{fileName:K,lineNumber:32,columnNumber:17}},i.a.createElement(x.a,{className:"icon-s edit-icon",icon:"shanchu3",__source:{fileName:K,lineNumber:38,columnNumber:21}}))}}],l=sessionStorage.getItem("webPerfId"),f=(Object(o.useEffect)((function(){t(l)}),[l]),function(t){sessionStorage.setItem("webSceneId",t),e.history.push("/repository/web-perform-to-scene")});return i.a.createElement(i.a.Fragment,null,i.a.createElement("div",{className:"title-space-between",__source:{fileName:K,lineNumber:60,columnNumber:13}},i.a.createElement(g,{webPerfStepStore:Y,webPerfId:l,__source:{fileName:K,lineNumber:61,columnNumber:17}})),i.a.createElement("div",{className:"table-list-box",__source:{fileName:K,lineNumber:66,columnNumber:13}},i.a.createElement(a.default,{columns:c,dataSource:r,rowKey:function(e){return e.id},pagination:!1,__source:{fileName:K,lineNumber:67,columnNumber:17}})))})),U=(r(372),r(185)),B=(r(898),r(894)),J=(r(173),r(46)),$=r(993),V="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\web\\perf\\components\\webPerfConfig.js";function q(){return(q=Object.assign?Object.assign.bind():function(e){for(var t=1;t<arguments.length;t++){var r,n=arguments[t];for(r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e}).apply(this,arguments)}function H(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,i=[],a=!0,u=!1;try{for(r=r.call(e);!(a=(n=r.next()).done)&&(i.push(n.value),!t||i.length!==t);a=!0);}catch(e){u=!0,o=e}finally{try{a||null==r.return||r.return()}finally{if(u)throw o}}return i}}(e,t)||function(e,t){var r;if(e)return"string"==typeof e?Q(e,t):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?Q(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function Q(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var X={labelCol:{span:2},wrapperCol:{span:22}},Z=Object(l.inject)("webPerfStore","agentConfigStore")(Object(l.observer)((function(e){var t=e.webPerfStore,r=e.agentConfigStore,n=t.findWebPerf,a=t.updateWebPerf,c=(r.findAgentConfigList,$.a.getAgent,H(J.default.useForm(),1)[0]),l=(r=(t=H(Object(o.useState)(),2))[0],t[1]),f=((t=H(Object(o.useState)(),2))[0],t[1],sessionStorage.getItem("webPerfId"));return sessionStorage.getItem("repositoryId"),Object(o.useEffect)((function(){n(f).then((function(e){c.setFieldsValue({threadCount:e.threadCount,executeType:e.executeType,executeCount:e.executeCount})}))}),[f]),i.a.createElement("div",{style:{margin:"20px 0 0"},__source:{fileName:V,lineNumber:118,columnNumber:9}},i.a.createElement(J.default,q({form:c,preserve:!1},X,{labelAlign:"left",__source:{fileName:V,lineNumber:119,columnNumber:13}}),i.a.createElement(J.default.Item,{label:"并发数",name:"threadCount",__source:{fileName:V,lineNumber:125,columnNumber:17}},i.a.createElement(B.a,{min:1,max:10,onChange:function(e){a({id:f,threadCount:e,testCase:{id:f}})},__source:{fileName:V,lineNumber:129,columnNumber:21}})),i.a.createElement(J.default.Item,{label:"执行次数",name:"executeCount",__source:{fileName:V,lineNumber:135,columnNumber:17}},i.a.createElement(B.a,{min:1,max:10,onChange:function(e){a({id:f,executeCount:e,testCase:{id:f}})},__source:{fileName:V,lineNumber:139,columnNumber:21}})),i.a.createElement(J.default.Item,{label:"执行方式",name:"executeType",__source:{fileName:V,lineNumber:145,columnNumber:17}},i.a.createElement(U.default.Group,{onChange:function(e){return e=e.target.value,l(e),void a({id:f,executeType:e,testCase:{id:f}})},value:r,__source:{fileName:V,lineNumber:149,columnNumber:21}},i.a.createElement(U.default,{value:1,__source:{fileName:V,lineNumber:150,columnNumber:25}},"循环"),i.a.createElement(U.default,{value:2,__source:{fileName:V,lineNumber:151,columnNumber:25}},"随机"))),i.a.createElement(J.default.Item,{label:"节点",name:"agent",__source:{fileName:V,lineNumber:154,columnNumber:17}},i.a.createElement(u.default,{title:"确定离开？",onConfirm:function(){e.history.push("/repository/setting/agent")},okText:"确定",cancelText:"取消",__source:{fileName:V,lineNumber:161,columnNumber:21}},i.a.createElement("div",{style:{width:"140px",color:"#0078d6",cursor:"pointer"},__source:{fileName:V,lineNumber:167,columnNumber:25}},"前往Agent管理页管理")))))}))),ee="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\web\\perf\\components\\webPerformDetailCommon.js";function te(){return(te=Object.assign?Object.assign.bind():function(e){for(var t=1;t<arguments.length;t++){var r,n=arguments[t];for(r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e}).apply(this,arguments)}var re=n.default.TabPane;t.a=function(e){var t=e.type;return i.a.createElement(n.default,{defaultActiveKey:"1",__source:{fileName:ee,lineNumber:10,columnNumber:9}},i.a.createElement(re,{tab:"场景配置",key:"1",__source:{fileName:ee,lineNumber:11,columnNumber:13}},i.a.createElement(M,te({type:t},e,{__source:{fileName:ee,lineNumber:12,columnNumber:17}}))),i.a.createElement(re,{tab:"压力配置",key:"2",__source:{fileName:ee,lineNumber:14,columnNumber:13}},i.a.createElement(Z,te({},e,{__source:{fileName:ee,lineNumber:15,columnNumber:17}}))))}}}]);