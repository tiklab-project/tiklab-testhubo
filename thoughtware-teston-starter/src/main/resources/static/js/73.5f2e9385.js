(window.webpackJsonp=window.webpackJsonp||[]).push([[73],{1048:function(e,t,r){"use strict";r.r(t);var n=r(0),o=r.n(n),a=r(92),l=r(1004),u=r(242),i="D:\\a-dk-web\\thoughtware-teston-ui\\src\\testplan\\common\\planToCase\\planToApiPerformPage.js";function c(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,a=[],l=!0,u=!1;try{for(r=r.call(e);!(l=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);l=!0);}catch(e){u=!0,o=e}finally{try{l||null==r.return||r.return()}finally{if(u)throw o}}return a}}(e,t)||function(e,t){var r;if(e)return"string"==typeof e?s(e,t):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?s(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function s(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}t.default=Object(a.inject)("apiPerfStore","testPlanStore")(Object(a.observer)((function(e){var t=e.apiPerfStore.findApiPerf,r=(e=c(Object(n.useState)(),2))[0],a=e[1],s=sessionStorage.getItem("apiPerfId");return Object(n.useEffect)((function(){t(s).then((function(e){a(e.testCase)}))}),[]),o.a.createElement("div",{className:"content-box-center",__source:{fileName:i,lineNumber:20,columnNumber:9}},o.a.createElement(u.a,{caseType:null==r?void 0:r.caseType,style:{borderBottom:"none"},router:"/plan/case",breadItem:[null==r?void 0:r.name],__source:{fileName:i,lineNumber:21,columnNumber:13}}),o.a.createElement(l.a,{planType:!0,apiPerfId:s,__source:{fileName:i,lineNumber:27,columnNumber:13}}))})))}}]);