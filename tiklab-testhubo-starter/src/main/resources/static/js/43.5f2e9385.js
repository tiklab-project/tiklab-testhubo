(window.webpackJsonp=window.webpackJsonp||[]).push([[43],{1002:function(e,t,n){},1021:function(e,t,n){"use strict";n(1002)},1027:function(e,t,n){"use strict";n(243);var r,i=n(145),a=(n(131),n(73)),o=(n(144),n(54)),c=(n(117),n(80)),l=(n(522),n(381)),u=(n(255),n(83)),s=(n(187),n(72)),f=n(0),m=n.n(f),h=n(17),p=n(225),d=n(931),y=n(504),v=n(932),g=n.p+"images/licence-features.svg",b=n(285),w=n.n(b);b=n(20);function E(e){return(E="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function x(){x=function(){return t};var e,t={},n=Object.prototype,r=n.hasOwnProperty,i=Object.defineProperty||function(e,t,n){e[t]=n.value},a=(b="function"==typeof Symbol?Symbol:{}).iterator||"@@iterator",o=b.asyncIterator||"@@asyncIterator",c=b.toStringTag||"@@toStringTag";function l(e,t,n){return Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{l({},"")}catch(e){l=function(e,t,n){return e[t]=n}}function u(t,n,r,a){var o,c,l,u;n=n&&n.prototype instanceof y?n:y,n=Object.create(n.prototype),a=new _(a||[]);return i(n,"_invoke",{value:(o=t,c=r,l=a,u=f,function(t,n){if(u===h)throw new Error("Generator is already running");if(u===p){if("throw"===t)throw n;return{value:e,done:!0}}for(l.method=t,l.arg=n;;){var r=l.delegate;if(r&&(r=function t(n,r){var i=r.method,a=n.iterator[i];return a===e?(r.delegate=null,"throw"===i&&n.iterator.return&&(r.method="return",r.arg=e,t(n,r),"throw"===r.method)||"return"!==i&&(r.method="throw",r.arg=new TypeError("The iterator does not provide a '"+i+"' method")),d):"throw"===(i=s(a,n.iterator,r.arg)).type?(r.method="throw",r.arg=i.arg,r.delegate=null,d):(a=i.arg)?a.done?(r[n.resultName]=a.value,r.next=n.nextLoc,"return"!==r.method&&(r.method="next",r.arg=e),r.delegate=null,d):a:(r.method="throw",r.arg=new TypeError("iterator result is not an object"),r.delegate=null,d)}(r,l))){if(r===d)continue;return r}if("next"===l.method)l.sent=l._sent=l.arg;else if("throw"===l.method){if(u===f)throw u=p,l.arg;l.dispatchException(l.arg)}else"return"===l.method&&l.abrupt("return",l.arg);if(u=h,"normal"===(r=s(o,c,l)).type){if(u=l.done?p:m,r.arg===d)continue;return{value:r.arg,done:l.done}}"throw"===r.type&&(u=p,l.method="throw",l.arg=r.arg)}})}),n}function s(e,t,n){try{return{type:"normal",arg:e.call(t,n)}}catch(e){return{type:"throw",arg:e}}}t.wrap=u;var f="suspendedStart",m="suspendedYield",h="executing",p="completed",d={};function y(){}function v(){}function g(){}var b,w,N=((w=(w=(l(b={},a,(function(){return this})),Object.getPrototypeOf))&&w(w(k([]))))&&w!==n&&r.call(w,a)&&(b=w),g.prototype=y.prototype=Object.create(b));function O(e){["next","throw","return"].forEach((function(t){l(e,t,(function(e){return this._invoke(t,e)}))}))}function L(e,t){var n;i(this,"_invoke",{value:function(i,a){function o(){return new t((function(n,o){!function n(i,a,o,c){var l;if("throw"!==(i=s(e[i],e,a)).type)return(a=(l=i.arg).value)&&"object"==E(a)&&r.call(a,"__await")?t.resolve(a.__await).then((function(e){n("next",e,o,c)}),(function(e){n("throw",e,o,c)})):t.resolve(a).then((function(e){l.value=e,o(l)}),(function(e){return n("throw",e,o,c)}));c(i.arg)}(i,a,n,o)}))}return n=n?n.then(o,o):o()}})}function j(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function S(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function _(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(j,this),this.reset(!0)}function k(t){if(t||""===t){var n,i=t[a];if(i)return i.call(t);if("function"==typeof t.next)return t;if(!isNaN(t.length))return n=-1,(i=function i(){for(;++n<t.length;)if(r.call(t,n))return i.value=t[n],i.done=!1,i;return i.value=e,i.done=!0,i}).next=i}throw new TypeError(E(t)+" is not iterable")}return i(N,"constructor",{value:v.prototype=g,configurable:!0}),i(g,"constructor",{value:v,configurable:!0}),v.displayName=l(g,c,"GeneratorFunction"),t.isGeneratorFunction=function(e){return!!(e="function"==typeof e&&e.constructor)&&(e===v||"GeneratorFunction"===(e.displayName||e.name))},t.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,g):(e.__proto__=g,l(e,c,"GeneratorFunction")),e.prototype=Object.create(N),e},t.awrap=function(e){return{__await:e}},O(L.prototype),l(L.prototype,o,(function(){return this})),t.AsyncIterator=L,t.async=function(e,n,r,i,a){void 0===a&&(a=Promise);var o=new L(u(e,n,r,i),a);return t.isGeneratorFunction(n)?o:o.next().then((function(e){return e.done?e.value:o.next()}))},O(N),l(N,c,"Generator"),l(N,a,(function(){return this})),l(N,"toString",(function(){return"[object Generator]"})),t.keys=function(e){var t,n=Object(e),r=[];for(t in n)r.push(t);return r.reverse(),function e(){for(;r.length;){var t=r.pop();if(t in n)return e.value=t,e.done=!1,e}return e.done=!0,e}},t.values=k,_.prototype={constructor:_,reset:function(t){if(this.prev=0,this.next=0,this.sent=this._sent=e,this.done=!1,this.delegate=null,this.method="next",this.arg=e,this.tryEntries.forEach(S),!t)for(var n in this)"t"===n.charAt(0)&&r.call(this,n)&&!isNaN(+n.slice(1))&&(this[n]=e)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(t){if(this.done)throw t;var n=this;function i(r,i){return c.type="throw",c.arg=t,n.next=r,i&&(n.method="next",n.arg=e),!!i}for(var a=this.tryEntries.length-1;0<=a;--a){var o=this.tryEntries[a],c=o.completion;if("root"===o.tryLoc)return i("end");if(o.tryLoc<=this.prev){var l=r.call(o,"catchLoc"),u=r.call(o,"finallyLoc");if(l&&u){if(this.prev<o.catchLoc)return i(o.catchLoc,!0);if(this.prev<o.finallyLoc)return i(o.finallyLoc)}else if(l){if(this.prev<o.catchLoc)return i(o.catchLoc,!0)}else{if(!u)throw new Error("try statement without catch or finally");if(this.prev<o.finallyLoc)return i(o.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;0<=n;--n){var i=this.tryEntries[n];if(i.tryLoc<=this.prev&&r.call(i,"finallyLoc")&&this.prev<i.finallyLoc){var a=i;break}}var o=(a=a&&("break"===e||"continue"===e)&&a.tryLoc<=t&&t<=a.finallyLoc?null:a)?a.completion:{};return o.type=e,o.arg=t,a?(this.method="next",this.next=a.finallyLoc,d):this.complete(o)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),d},finish:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var n=this.tryEntries[t];if(n.finallyLoc===e)return this.complete(n.completion,n.afterLoc),S(n),d}},catch:function(e){for(var t=this.tryEntries.length-1;0<=t;--t){var n,r,i=this.tryEntries[t];if(i.tryLoc===e)return"throw"===(n=i.completion).type&&(r=n.arg,S(i)),r}throw new Error("illegal catch attempt")},delegateYield:function(t,n,r){return this.delegate={iterator:k(t),resultName:n,nextLoc:r},"next"===this.method&&(this.arg=e),d}},t}function N(e,t,n,r,i,a,o){try{var c=e[a](o),l=c.value}catch(e){return void n(e)}c.done?t(l):Promise.resolve(l).then(r,i)}function O(e){return function(){var t=this,n=arguments;return new Promise((function(r,i){var a=e.apply(t,n);function o(e){N(a,r,i,o,c,"next",e)}function c(e){N(a,r,i,o,c,"throw",e)}o(void 0)}))}}function L(e,t,n,r){n&&Object.defineProperty(e,t,{enumerable:n.enumerable,configurable:n.configurable,writable:n.writable,value:n.initializer?n.initializer.call(r):void 0})}function j(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,S(r.key),r)}}function S(e){return e=function(e,t){if("object"!==E(e)||null===e)return e;var n=e[Symbol.toPrimitive];if(void 0===n)return String(e);if("object"!==E(n=n.call(e,t)))return n;throw new TypeError("@@toPrimitive must return a primitive value.")}(e,"string"),"symbol"===E(e)?e:String(e)}function _(e,t,n,r,i){var a={};return Object.keys(r).forEach((function(e){a[e]=r[e]})),a.enumerable=!!a.enumerable,a.configurable=!!a.configurable,("value"in a||a.initializer)&&(a.writable=!0),a=n.slice().reverse().reduce((function(n,r){return r(e,t,n)||n}),a),i&&void 0!==a.initializer&&(a.value=a.initializer?a.initializer.call(i):void 0,a.initializer=void 0),void 0===a.initializer&&(Object.defineProperty(e,t,a),a=null),a}var k=_((r=function(e,t,n){return t&&j(e.prototype,t),n&&j(e,n),Object.defineProperty(e,"prototype",{writable:!1}),e}((function e(){var t,n,r;if(!(this instanceof e))throw new TypeError("Cannot call a class as a function");L(this,"getVersions",k,this),L(this,"findUseLicence",T,this),t=this,n="findAllLicence",r=O(x().mark((function e(){return x().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,h.Axios.post("/licence/findAllLicence");case 2:return e.abrupt("return",e.sent);case 3:case"end":return e.stop()}}),e)}))),(n=S(n))in t?Object.defineProperty(t,n,{value:r,enumerable:!0,configurable:!0,writable:!0}):t[n]=r}))).prototype,"getVersions",[b.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return O(x().mark((function e(){return x().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,h.Axios.post("/version/getVersion");case 2:return e.abrupt("return",e.sent);case 3:case"end":return e.stop()}}),e)})))}}),T=_(r.prototype,"findUseLicence",[b.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return O(x().mark((function e(){return x().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,h.Axios.post("/licence/findUseLicence");case 2:return e.abrupt("return",e.sent);case 3:case"end":return e.stop()}}),e)})))}}),A=new r;function Y(){return(Y=Object.assign?Object.assign.bind():function(e){for(var t=1;t<arguments.length;t++){var n,r=arguments[t];for(n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e}).apply(this,arguments)}function P(e){return function(e){if(Array.isArray(e))return C(e)}(e)||function(e){if("undefined"!=typeof Symbol&&null!=e[Symbol.iterator]||null!=e["@@iterator"])return Array.from(e)}(e)||F(e)||function(){throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function I(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,i,a,o,c=[],l=!0,u=!1;try{if(a=(n=n.call(e)).next,0===t){if(Object(n)!==n)return;l=!1}else for(;!(l=(r=a.call(n)).done)&&(c.push(r.value),c.length!==t);l=!0);}catch(e){u=!0,i=e}finally{try{if(!l&&null!=n.return&&(o=n.return(),Object(o)!==o))return}finally{if(u)throw i}}return c}}(e,t)||F(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function F(e,t){var n;if(e)return"string"==typeof e?C(e,t):"Map"===(n="Object"===(n=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:n)||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?C(e,t):void 0}function C(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}n(1002),t.a=function(e){var t=e.bgroup,n=(e=e.children,A.getVersions),r=A.findUseLicence,b=A.findAllLicence,E=(G=I(Object(f.useState)(null),2))[0],x=G[1],N=(G=I(Object(f.useState)(null),2))[0],O=G[1],L=(G=I(Object(f.useState)([]),2))[0],j=G[1],S=(G=I(Object(f.useState)({}),2))[0],_=G[1],k=(G=I(Object(f.useState)(!1),2))[0],T=G[1],F=(G=I(Object(f.useState)(!1),2))[0],C=G[1],z=(G=I(Object(f.useState)(!0),2))[0],M=G[1],G=window.location.origin,D=(Object(f.useEffect)((function(){D()}),[]),function(){n().then((function(e){0===e.code&&(_(e.data),localStorage.setItem(h.LOCALSTORAGE_KEY.VERSION_INFO,JSON.stringify(e.data)),e.data.expired||(H(),U())),M(!1)}))}),H=function(){r().then((function(e){0===e.code&&e.data&&O(e.data),6e3===e.code&&s.default.error(e.msg)}))},U=function(){b().then((function(e){0===e.code&&x(e.data)}))},V=(G={name:"file",action:G+("/"===G.substring(G.length-1,G.length)?"licence/import":"/licence/import"),onChange:function(e){var t=P(e.fileList);return t=(t=t.slice(-1)).map((function(e){return e.response&&(e.url=e.response.url),e})),j(t),e.file.status,"done"===e.file.status?0===(t=e.file).response.code?(setTimeout((function(){j([]),D()}),1e3),s.default.success("licence导入成功")):s.default.error("licence导入失败：".concat(t.response.msg)):"error"===e.file.status?s.default.error("licence导入失败，请重新上传"):void 0}},[{title:"生效时间",dataIndex:"expiryTime",key:"expiryTime",width:"32%",ellipsis:!0,render:function(e){return w()(e).format("YYYY-MM-DD HH:mm:ss")}},{title:"过期时间",dataIndex:"issuedTime",key:"issuedTime",width:"30%",ellipsis:!0,render:function(e){return w()(e).format("YYYY-MM-DD HH:mm:ss")}},{title:"用户数",dataIndex:"userNum",key:"userNum",width:"32%",ellipsis:!0,render:function(e){return 0<e?e:"无限制"}},{title:"状态",dataIndex:"state",key:"state",width:"16%",ellipsis:!0,render:function(e){return m.a.createElement(m.a.Fragment,null,1===e&&"已失效",2===e&&m.a.createElement("span",{className:"licence-history-modal-blue"},"生效中"),3===e&&"未生效")}}]);return m.a.createElement(i.a,{spinning:z,tip:"导入licence中..."},m.a.createElement(a.default,{className:"thoughtware_version"},m.a.createElement(o.default,{sm:{span:24},xs:{span:24},md:{span:24},lg:{span:"16",offset:"4"},xl:{span:"14",offset:"5"}},m.a.createElement("div",{className:"thoughtware_version_container"},m.a.createElement(d.a,{firstItem:"版本与许可证"},m.a.createElement("div",{className:"thoughtware_version_up_btn"},2===(null==S?void 0:S.release)&&m.a.createElement(m.a.Fragment,null,m.a.createElement(l.a,Y({},G,{fileList:L}),m.a.createElement(y.a,{isMar:!S.expired,type:"primary"},"导入licence")),!S.expired&&m.a.createElement(m.a.Fragment,null,m.a.createElement(y.a,{onClick:function(){return C(!0)}},"历史"),m.a.createElement(v.a,{title:m.a.createElement("div",{className:"licence-history-modal-title"},m.a.createElement("div",null,"历史"),m.a.createElement(y.a,{type:"text",onClick:function(){return C(!1)},title:m.a.createElement(p.a,null)})),width:800,visible:F,onCancel:function(){return C(!1)},footer:m.a.createElement(m.a.Fragment,null)},m.a.createElement("div",{className:"licence-history-modal"},m.a.createElement(c.default,{dataSource:E,columns:V,pagination:!1,rowKey:function(e,t){return t},locale:{emptyText:m.a.createElement("div",{className:"licence-history-modal-empty"},"暂无历史")}}))))))),m.a.createElement("div",{className:"thoughtware_version-info"},m.a.createElement("div",{className:"thoughtware_version-info-item"},m.a.createElement("span",{className:"info-item-title"},"应用名称"),h.productTitle[t]),m.a.createElement("div",{className:"thoughtware_version-info-item"},m.a.createElement("div",{className:"info-item-title"},"版本类型"),1===(null==S?void 0:S.release)?m.a.createElement(m.a.Fragment,null,m.a.createElement("div",{className:"info-item-info"},"社区版",m.a.createElement("span",{className:"info-item-title-te",onClick:function(){return T(!0)}},m.a.createElement("img",{src:g,alt:"企业版特性",height:14,width:14}))),m.a.createElement("div",{onClick:function(){window.open("https://thoughtware.cn/download/".concat(t,"/ee"))},className:"info-item-action"},"升级"),m.a.createElement(v.a,{width:800,visible:k,onCancel:function(){return T(!1)},footer:m.a.createElement(m.a.Fragment,null),title:m.a.createElement("div",{className:"licence-version-feat-modal-title"},m.a.createElement("div",null,"企业版特性"),m.a.createElement(y.a,{type:"text",onClick:function(){return T(!1)},title:m.a.createElement(p.a,null)}))},m.a.createElement("div",{className:"licence-version-feat-modal"},e))):S.expired?m.a.createElement(m.a.Fragment,null,m.a.createElement("div",{className:"info-item-info"},"企业版"),m.a.createElement("div",{className:"info-item-action",onClick:function(){window.open("https://thoughtware.cn/account/subscribe/info/".concat(t))}},"免费试用"),m.a.createElement("div",{className:"info-item-action",onClick:function(){window.open("https://thoughtware.cn/account/subscribe")}},"订阅")):m.a.createElement(m.a.Fragment,null,"企业版",m.a.createElement("div",{className:"info-item-action"},S.expired?null!=N&&N.tryApply?m.a.createElement(u.default,{color:"#ff4d4f"},"试用已过期"):m.a.createElement(u.default,{color:"#ff4d4f"},"订阅已过期"):null!=N&&N.tryApply?m.a.createElement(u.default,{color:"#5d70ea"},"试用中"):m.a.createElement(u.default,{color:"#5d70ea"},"订阅中")))),m.a.createElement("div",{className:"thoughtware_version-info-item"},m.a.createElement("span",{className:"info-item-title"},"用户数"),1===S.release||S.expired?"不限制":0<(null==N?void 0:N.userNum)?N.userNum+"人":"无限制"),m.a.createElement("div",{className:"thoughtware_version-info-item"},m.a.createElement("span",{className:"info-item-title"},"生效时间"),null!=N&&N.expiryTime?w()(null==N?void 0:N.expiryTime).format("YYYY-MM-DD HH:mm:ss"):"--"),m.a.createElement("div",{className:"thoughtware_version-info-item"},m.a.createElement("span",{className:"info-item-title"},"过期时间"),null!=N&&N.issuedTime?w()(null==N?void 0:N.issuedTime).format("YYYY-MM-DD HH:mm:ss"):"--"))))))}}}]);