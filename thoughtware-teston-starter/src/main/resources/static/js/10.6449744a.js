(window.webpackJsonp=window.webpackJsonp||[]).push([[10],{679:function(e,n,r){"use strict";var t=r(0),a=r.n(t),c="D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\IconCommon.js";n.a=function(e){var n=e.icon,r=e.style,t=e.className;e=e.onClick;return a.a.createElement("svg",{style:r,className:t,"aria-hidden":"true",onClick:e,__source:{fileName:c,lineNumber:10,columnNumber:9}},a.a.createElement("use",{xlinkHref:"#icon-".concat(n),__source:{fileName:c,lineNumber:11,columnNumber:13}}))}},685:function(e,n,r){"use strict";r.d(n,"d",(function(){return o})),r.d(n,"c",(function(){return m})),r.d(n,"a",(function(){return i})),r.d(n,"b",(function(){return s})),r(137);var t=r(81),a=r(0),c=r.n(a),u=(r(3),r(11),r(679)),l="D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\caseCommon\\CaseCommonFn.js",o=function(e){switch(e){case"api":return"接口";case"ui":return"UI";case"perform":return"性能";case"function":return"功能"}},m=function(e){switch(e){case"api-unit":return c.a.createElement(u.a,{icon:"api1",className:"icon-l",__source:{fileName:l,lineNumber:29,columnNumber:21}});case"api-scene":return c.a.createElement(u.a,{icon:"api1",className:"icon-l",__source:{fileName:l,lineNumber:35,columnNumber:21}});case"api-perform":return c.a.createElement(u.a,{icon:"api1",className:"icon-l",__source:{fileName:l,lineNumber:40,columnNumber:21}});case"web-scene":return c.a.createElement(u.a,{icon:"diannao1",className:"icon-l",__source:{fileName:l,lineNumber:46,columnNumber:20}});case"web-perform":return c.a.createElement(u.a,{icon:"diannao1",className:"icon-l",__source:{fileName:l,lineNumber:51,columnNumber:20}});case"app-scene":return c.a.createElement(u.a,{icon:"shouji1",className:"icon-l",__source:{fileName:l,lineNumber:57,columnNumber:20}});case"app-perform":return c.a.createElement(u.a,{icon:"shouji1",className:"icon-l",__source:{fileName:l,lineNumber:62,columnNumber:20}});default:return c.a.createElement(u.a,{icon:"gongneng1",className:"icon-l",__source:{fileName:l,lineNumber:67,columnNumber:20}})}},i=function(e){switch(e){case"api-unit":return c.a.createElement(t.default,{color:"green",__source:{fileName:l,lineNumber:102,columnNumber:20}},"接口单元");case"api-scene":return c.a.createElement(t.default,{color:"blue",__source:{fileName:l,lineNumber:104,columnNumber:20}},"接口场景");case"api-perform":return c.a.createElement(t.default,{color:"orange",__source:{fileName:l,lineNumber:106,columnNumber:20}},"接口性能");case"web-scene":return c.a.createElement(t.default,{color:"blue",__source:{fileName:l,lineNumber:109,columnNumber:20}},"WEB场景");case"web-perform":return c.a.createElement(t.default,{color:"orange",__source:{fileName:l,lineNumber:111,columnNumber:20}},"WEB性能");case"app-scene":return c.a.createElement(t.default,{color:"blue",__source:{fileName:l,lineNumber:114,columnNumber:20}},"APP场景");case"app-perform":return c.a.createElement(t.default,{color:"orange",__source:{fileName:l,lineNumber:116,columnNumber:20}},"APP性能");case"function":return c.a.createElement(t.default,{color:"#2db7f5",__source:{fileName:l,lineNumber:118,columnNumber:20}},"功能用例");default:return}},s=function(e){switch(e){case"api-unit":return"接口单元";case"api-scene":return"接口场景";case"api-perform":return"接口性能";case"web-scene":return"WEB场景";case"web-perform":return"WEB性能";case"app-scene":return"APP场景";case"app-perform":return"APP性能";case"function":return"功能用例"}}},702:function(e,n,r){"use strict";var t=r(0),a=r.n(t),c=r(141),u=r(123),l="D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\pagination\\Page.js";n.a=function(e){var n=e.currentPage,r=e.changePage,t=e.totalPage;return a.a.createElement("div",{className:"pagination-box",__source:{fileName:l,lineNumber:12,columnNumber:12}},a.a.createElement("span",{className:"".concat(1===n?"pagination-box-ban":"pagination-box-allow"),onClick:function(){return 1===n?null:r(n-1)},__source:{fileName:l,lineNumber:13,columnNumber:17}},a.a.createElement(c.default,{__source:{fileName:l,lineNumber:17,columnNumber:21}})),a.a.createElement("span",{className:"pagination-box-current",__source:{fileName:l,lineNumber:19,columnNumber:17}},n),a.a.createElement("span",{__source:{fileName:l,lineNumber:20,columnNumber:17}}," / ",t),a.a.createElement("span",{className:"".concat(n===t?"pagination-box-ban":"pagination-box-allow"),onClick:function(){return n===t?null:r(n+1)},__source:{fileName:l,lineNumber:21,columnNumber:17}},a.a.createElement(u.default,{__source:{fileName:l,lineNumber:25,columnNumber:21}})))}},709:function(e,n,r){"use strict";r.d(n,"a",(function(){return t}));var t={API_UNIT:"api-unit",API_SCENE:"api-scene",API_PERFORM:"api-perform",WEB_SCENE:"web-scene",WEB_PERFORM:"web-perform",APP_SCENE:"app-scene",APP_PERFORM:"app-perform",FUNCTION:"function"}},820:function(e,n,r){"use strict";var t=r(0),a=r.n(t),c="D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\menuSelect\\MenuSelect.js";n.a=function(e){var n=e.selected,r=e.selectFn,t=e.style;e=e.menuItems;return a.a.createElement("div",{className:"select-menu-box",style:t,__source:{fileName:c,lineNumber:24,columnNumber:9}},(t=e)&&t.map((function(e){return a.a.createElement("div",{key:e.key,className:"select-menu-item  ".concat(e.key===n?"select-menu-item-selected":""),onClick:function(){return r(e)},__source:{fileName:c,lineNumber:11,columnNumber:17}},a.a.createElement("span",{__source:{fileName:c,lineNumber:16,columnNumber:21}}," ",e.title," "))})))}},825:function(e,n,r){"use strict";var t=r(0),a=r.n(t),c="D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\select\\SelectItem.js";function u(e,n){return function(e){if(Array.isArray(e))return e}(e)||function(e,n){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var t,a,c=[],u=!0,l=!1;try{for(r=r.call(e);!(u=(t=r.next()).done)&&(c.push(t.value),!n||c.length!==n);u=!0);}catch(e){l=!0,a=e}finally{try{u||null==r.return||r.return()}finally{if(l)throw a}}return c}}(e,n)||function(e,n){var r;if(e)return"string"==typeof e?l(e,n):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?l(e,n):void 0}(e,n)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function l(e,n){(null==n||n>e.length)&&(n=e.length);for(var r=0,t=new Array(n);r<n;r++)t[r]=e[r];return t}function o(e){function n(e){e.stopPropagation(),N?(i(e.target),_(s.includes(r))):(i({label:l,value:r}),b(!1))}var r=e.value,l=e.label,o=e.key,m=e.imgUrl,i=e.onChange,s=e.selectData,N=e.ismult,b=e.setShowDropDown;e.children;var f=(e=u(Object(t.useState)(),2))[0],_=e[1],d=Object(t.useRef)();return Object(t.useEffect)((function(){N&&_(s.includes(r))}),[s]),a.a.createElement("div",{key:o,className:"select-item",onClick:function(e){e.stopPropagation(),d.current.checked=!d.current.checked,N?(i(d.current),_(s.includes(r))):(i({label:l,value:r}),b(!1))},__source:{fileName:c,lineNumber:41,columnNumber:9}},N?a.a.createElement("input",{type:"checkbox",id:"select-check",ref:d,value:r,className:"select-input",onClick:n,checked:f,__source:{fileName:c,lineNumber:43,columnNumber:26}}):a.a.createElement("input",{type:"radio",id:"select-check",ref:d,value:r,className:"select-input",onClick:n,onChange:n,defaultChecked:(null==s?void 0:s.value)===r,__source:{fileName:c,lineNumber:52,columnNumber:21}}),m&&a.a.createElement("img",{className:"img-icon-right",src:"".concat(m),width:"15",height:"15",__source:{fileName:c,lineNumber:64,columnNumber:24}}),a.a.createElement("div",{className:"select-item-text",__source:{fileName:c,lineNumber:65,columnNumber:21}},l))}var m="D:\\a-dk-web\\thoughtware-teston-ui\\src\\common\\select\\Select.js";function i(e,n){return function(e){if(Array.isArray(e))return e}(e)||function(e,n){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var t,a,c=[],u=!0,l=!1;try{for(r=r.call(e);!(u=(t=r.next()).done)&&(c.push(t.value),!n||c.length!==n);u=!0);}catch(e){l=!0,a=e}finally{try{u||null==r.return||r.return()}finally{if(l)throw a}}return c}}(e,n)||function(e,n){var r;if(e)return"string"==typeof e?s(e,n):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?s(e,n):void 0}(e,n)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function s(e,n){(null==n||n>e.length)&&(n=e.length);for(var r=0,t=new Array(n);r<n;r++)t[r]=e[r];return t}function N(e){function n(e){var n,t,a;N?(n=e.value,t=e.checked,-1===(a=C.indexOf(n))&&!0===t&&(C.push(n),P(C)),-1!==a&&!1===t&&(C.splice(a,1),P(C)),w(C.length),r(C)):(P(e),r(e))}var r=e.onChange,c=e.onFocus,u=e.onSearchChange,l=e.onBlur,o=e.onMouseEnter,s=e.onMouseLeave,N=e.ismult,b=e.title,f=e.children,_=e.value,d=e.className,p=e.simpleClassName,E=(e=e.suffixIcon,i(Object(t.useState)(!1),2)),v=E[0],h=E[1],g=(E=i(Object(t.useState)(0),2))[0],w=E[1],y=(E=i(Object(t.useState)(),2))[0],k=E[1],C=(E=i(Object(t.useState)(_||(N?[]:null)),2))[0],P=E[1],S=Object(t.useRef)(),j=Object(t.useRef)(),A=(Object(t.useEffect)((function(){return window.addEventListener("mousedown",A,!1),function(){window.removeEventListener("mousedown",A,!1)}}),[v]),Object(t.useEffect)((function(){_?(w(_.length),P(_)):(w(0),P(N?[]:null))}),[_]),function(e){!S.current||S.current.contains(e.target)||S.current===e.target||(l&&l(),u&&x(),h(!1))}),x=function(){k(null),u&&u(null),j.current.value=""};return a.a.createElement("div",{className:"select-view ".concat(p||""),__source:{fileName:m,lineNumber:119,columnNumber:12}},a.a.createElement("div",{onClick:function(){h(!0),c&&c()},className:"select-content",onMouseEnter:function(){return o&&o()},onMouseLeave:function(){return o&&s()},__source:{fileName:m,lineNumber:120,columnNumber:9}},N?a.a.createElement("div",{style:{color:"#bfbfbf"},__source:{fileName:m,lineNumber:126,columnNumber:21}},b):a.a.createElement("div",{className:"".concat(d," select-view-text"),title:null!=C&&C.label?C.label:b,__source:{fileName:m,lineNumber:130,columnNumber:21}},null!=C&&C.label?C.label:b),a.a.createElement("div",{className:"select-view-icon",__source:{fileName:m,lineNumber:134,columnNumber:13}},N&&0<g&&a.a.createElement("div",{className:"select-number",__source:{fileName:m,lineNumber:136,columnNumber:51}},g),e&&a.a.createElement(a.a.Fragment,null,!N&&C?a.a.createElement("svg",{className:"cancel-svg","aria-hidden":"true",onClick:function(e){(e=e).stopPropagation(),e.preventDefault(),P(N?[]:null),w(0),r(null)},__source:{fileName:m,lineNumber:141,columnNumber:53}},a.a.createElement("use",{xlinkHref:"#icon-cancel",__source:{fileName:m,lineNumber:142,columnNumber:33}})):a.a.createElement("svg",{className:"svg-icon","aria-hidden":"true",__source:{fileName:m,lineNumber:145,columnNumber:33}},a.a.createElement("use",{xlinkHref:"#icon-downdrop",__source:{fileName:m,lineNumber:146,columnNumber:37}}))))),v?a.a.createElement("div",{className:"select-dropdown",ref:S,__source:{fileName:m,lineNumber:158,columnNumber:28}},u&&a.a.createElement("div",{className:"select-search-box",__source:{fileName:m,lineNumber:160,columnNumber:39}},a.a.createElement("input",{className:"select-search-input",ref:j,placeholder:"搜索",onChange:function(e){k((e=e).target.value),u(e.target.value)},__source:{fileName:m,lineNumber:161,columnNumber:25}}),y?a.a.createElement("svg",{className:"cancel-svg","aria-hidden":"true",onClick:function(){return x()},__source:{fileName:m,lineNumber:163,columnNumber:43}},a.a.createElement("use",{xlinkHref:"#icon-cancel",__source:{fileName:m,lineNumber:164,columnNumber:33}})):a.a.createElement("svg",{className:"big-svg","aria-hidden":"true",__source:{fileName:m,lineNumber:167,columnNumber:33}},a.a.createElement("use",{xlinkHref:"#icon-search2",__source:{fileName:m,lineNumber:168,columnNumber:37}}))),a.a.Children.map(f,(function(e,r){return a.a.cloneElement(e,{onChange:n,selectData:C,setShowDropDown:h,ismult:N})})),N&&a.a.createElement("div",{className:"select-dropdown-bottom",__source:{fileName:m,lineNumber:180,columnNumber:31}},a.a.createElement("div",{className:"dropdown-botton clear",onClick:function(){P(N?[]:null),w(0),r(null)},__source:{fileName:m,lineNumber:181,columnNumber:25}},"清空"),a.a.createElement("div",{className:"dropdown-botton submit",onClick:function(){return h(!1)},__source:{fileName:m,lineNumber:182,columnNumber:25}},"确定"))):a.a.createElement(a.a.Fragment,null))}var b="D:\\a-dk-web\\thoughtware-teston-ui\\src\\test\\testcase\\components\\CaseTypeSelect.js";n.a=function(e){var n=e.findPage;e=e.testType;return a.a.createElement(a.a.Fragment,null,"api"===e?a.a.createElement(N,{name:"workStatus",onChange:function(e){return n(e)},title:"api"===e&&"接口类型",ismult:!0,__source:{fileName:b,lineNumber:34,columnNumber:23}},a.a.createElement("div",{className:"select-group-title",__source:{fileName:b,lineNumber:40,columnNumber:25}},"接口"),[{id:"api-unit",name:"接口单元用例"},{id:"api-scene",name:"接口场景用例"}].map((function(e){return a.a.createElement(o,{value:e.id,label:e.name,key:e.id,__source:{fileName:b,lineNumber:43,columnNumber:40}})}))):null,"ui"===e?a.a.createElement(N,{name:"workStatus",onChange:function(e){return n(e)},title:"ui"===e&&"UI类型",ismult:!0,__source:{fileName:b,lineNumber:56,columnNumber:23}},a.a.createElement("div",{className:"select-group-title",__source:{fileName:b,lineNumber:62,columnNumber:25}},"UI"),[{id:"web-scene",name:"WEB场景用例"},{id:"app-scene",name:"APP场景用例"}].map((function(e){return a.a.createElement(o,{value:e.id,label:e.name,key:e.id,__source:{fileName:b,lineNumber:65,columnNumber:40}})}))):null)}}}]);