(window.webpackJsonp=window.webpackJsonp||[]).push([[83],{1162:function(e,t,r){"use strict";r.r(t),r(125);var n=r(60),l=(r(80),r(19)),a=(r(49),r(22)),i=(r(651),r(650)),u=r(0),m=r.n(u),o=r(63),c=(r(74),r(61)),s=r(89),b="D:\\a-dk-web\\thoughtware-teston-ui\\src\\repository\\setting\\DeleteRepositoryModal.js";function N(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,l,a=[],i=!0,u=!1;try{for(r=r.call(e);!(i=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);i=!0);}catch(e){u=!0,l=e}finally{try{i||null==r.return||r.return()}finally{if(u)throw l}}return a}}(e,t)||function(e,t){var r;if(e)return"string"==typeof e?f(e,t):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?f(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function f(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}function d(e){var t=e.repositoryStore,r=e.repositoryName,l=t.deleteRepository,i=(t=N(m.a.useState(!1),2))[0],o=t[1],f=(t=N(Object(u.useState)(!0),2))[0],d=t[1],y=sessionStorage.getItem("repositoryId");return m.a.createElement(m.a.Fragment,null,m.a.createElement(s.PrivilegeProjectButton,{code:"repositoryDelete",domainId:y,__source:{fileName:b,lineNumber:40,columnNumber:13}},m.a.createElement(n.default,{type:"primary",danger:!0,onClick:function(){o(!0)},__source:{fileName:b,lineNumber:41,columnNumber:17}},"删除仓库")),m.a.createElement(c.default,{destroyOnClose:!0,title:"你确定删除仓库吗？",visible:i,onCancel:function(){o(!1)},footer:!1,width:440,centered:!0,__source:{fileName:b,lineNumber:43,columnNumber:13}},m.a.createElement("div",{className:"ws-delete-box",__source:{fileName:b,lineNumber:52,columnNumber:17}},m.a.createElement("div",{className:"ws-delete-tip",__source:{fileName:b,lineNumber:53,columnNumber:21}},"此操作",m.a.createElement("span",{className:"ws-delete-text-bold",__source:{fileName:b,lineNumber:54,columnNumber:28}},"无法"),"撤消,这将永久删除:",m.a.createElement("span",{className:"ws-delete-text-bold",__source:{fileName:b,lineNumber:55,columnNumber:25}},r)),m.a.createElement("div",{className:"ws-delete-input-title",__source:{fileName:b,lineNumber:57,columnNumber:21}},"请输入  ",m.a.createElement("span",{className:"ws-delete-text-bold",__source:{fileName:b,lineNumber:57,columnNumber:67}},r),"  进行确认。"),m.a.createElement(a.default,{onChange:function(e){e.target.value!==r?d(!0):d(!1)},__source:{fileName:b,lineNumber:59,columnNumber:21}}),m.a.createElement(n.default,{type:"primary",danger:!0,disabled:f,onClick:function(){l(y).then((function(){e.history.push("/repository-page")}))},className:"ws-delete-modal-btn",__source:{fileName:b,lineNumber:60,columnNumber:21}},"我了解后果，删除此仓库"))))}var y=r(169),p=r(199),_="D:\\a-dk-web\\thoughtware-teston-ui\\src\\repository\\setting\\RepositorySetting.js";function v(){return(v=Object.assign?Object.assign.bind():function(e){for(var t=1;t<arguments.length;t++){var r,n=arguments[t];for(r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e}).apply(this,arguments)}function g(e,t){var r,n=Object.keys(e);return Object.getOwnPropertySymbols&&(r=Object.getOwnPropertySymbols(e),t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)),n}function E(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?g(Object(r),!0).forEach((function(t){var n,l;n=e,l=r[t=t],t in n?Object.defineProperty(n,t,{value:l,enumerable:!0,configurable:!0,writable:!0}):n[t]=l})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):g(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function h(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,l,a=[],i=!0,u=!1;try{for(r=r.call(e);!(i=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);i=!0);}catch(e){u=!0,l=e}finally{try{i||null==r.return||r.return()}finally{if(u)throw l}}return a}}(e,t)||function(e,t){var r;if(e)return"string"==typeof e?w(e,t):"Map"===(r="Object"===(r=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:r)||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?w(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function w(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var O=i.a.Panel,j=a.default.TextArea,S={labelCol:{span:0},wrapperCol:{span:1}};t.default=Object(o.inject)("repositoryStore")(Object(o.observer)((function(e){var t,r=e.repositoryStore,o=r.updateRepository,c=r.findRepository,s=(r.deleteRepository,sessionStorage.getItem("repositoryId")),b=h(l.default.useForm(),1)[0],N=(t=h(Object(u.useState)(),2))[0],f=t[1],g=(t=h(Object(u.useState)(),2))[0],w=t[1],x=(t=h(Object(u.useState)(1),2))[0],I=t[1];return Object(u.useEffect)((function(){c(s).then((function(e){f(e),w(e.name),I(e.visibility),b.setFieldsValue({name:e.name,desc:e.desc})}))}),[s]),m.a.createElement("div",{className:"ws-setting-flex",__source:{fileName:_,lineNumber:55,columnNumber:9}},m.a.createElement("div",{className:"ws-setting-box",__source:{fileName:_,lineNumber:56,columnNumber:13}},m.a.createElement("div",{className:"header-box-space-between",__source:{fileName:_,lineNumber:57,columnNumber:17}},m.a.createElement("div",{className:"header-box-title",__source:{fileName:_,lineNumber:58,columnNumber:21}},"仓库信息")),m.a.createElement(i.a,{expandIconPosition:"end",__source:{fileName:_,lineNumber:61,columnNumber:17}},m.a.createElement(O,{header:m.a.createElement(m.a.Fragment,null,m.a.createElement(y.default,{__source:{fileName:_,lineNumber:62,columnNumber:38}})," ",m.a.createElement("span",{style:{padding:"0 5px"},__source:{fileName:_,lineNumber:62,columnNumber:54}},"编辑仓库")),key:"1",__source:{fileName:_,lineNumber:62,columnNumber:21}},m.a.createElement("div",{__source:{fileName:_,lineNumber:63,columnNumber:25}},m.a.createElement(l.default,{className:"ws-edit-modal-form",form:b,preserve:!1,layout:"vertical",onFinish:function(e){e.visibility=x,e=E({id:s,iconUrl:null==N?void 0:N.iconUrl},e),o(e)},labelCol:{span:4},wrapperCol:{span:20},__source:{fileName:_,lineNumber:64,columnNumber:29}},m.a.createElement(l.default.Item,{label:"应用名称",rules:[{required:!0,message:"添加目录名称!"}],name:"name",__source:{fileName:_,lineNumber:73,columnNumber:33}},m.a.createElement(a.default,{style:{height:40},__source:{fileName:_,lineNumber:78,columnNumber:37}})),m.a.createElement(l.default.Item,{label:"可见范围",name:"visibility",__source:{fileName:_,lineNumber:80,columnNumber:33}},m.a.createElement("div",{className:"ws-setting-edit-visibility",__source:{fileName:_,lineNumber:84,columnNumber:37}},m.a.createElement("div",{className:"ws-edit-visibility-item ".concat(0===x?"ws-edit-visibility-action":null),onClick:function(){return I(0)},__source:{fileName:_,lineNumber:85,columnNumber:41}},m.a.createElement("div",{style:{display:"flex",alignItems:"center"},__source:{fileName:_,lineNumber:86,columnNumber:45}},m.a.createElement("svg",{style:{width:20,height:20},"aria-hidden":"true",__source:{fileName:_,lineNumber:87,columnNumber:49}},m.a.createElement("use",{xlinkHref:"#icon-suoding",__source:{fileName:_,lineNumber:88,columnNumber:53}})),m.a.createElement("span",{__source:{fileName:_,lineNumber:90,columnNumber:49}},"公共")),m.a.createElement("div",{className:"ws-edit-visibility-item-desc",__source:{fileName:_,lineNumber:92,columnNumber:45}},"公共项目，全部成员可见")),m.a.createElement("div",{className:"ws-edit-visibility-item  ".concat(1===x?"ws-edit-visibility-action":null),onClick:function(){return I(1)},__source:{fileName:_,lineNumber:95,columnNumber:41}},m.a.createElement("div",{style:{display:"flex",alignItems:"center"},__source:{fileName:_,lineNumber:96,columnNumber:45}},m.a.createElement("svg",{style:{width:20,height:20},"aria-hidden":"true",__source:{fileName:_,lineNumber:97,columnNumber:49}},m.a.createElement("use",{xlinkHref:"#icon-jiesuo",__source:{fileName:_,lineNumber:98,columnNumber:53}})),m.a.createElement("span",{__source:{fileName:_,lineNumber:100,columnNumber:49}},"私密")),m.a.createElement("div",{className:"ws-edit-visibility-item-desc",__source:{fileName:_,lineNumber:102,columnNumber:45}},"私密项目，只有项目成员可见")))),m.a.createElement(l.default.Item,{label:"描述",name:"desc",__source:{fileName:_,lineNumber:107,columnNumber:33}},m.a.createElement(j,{rows:4,__source:{fileName:_,lineNumber:111,columnNumber:37}})),m.a.createElement(l.default.Item,v({},S,{__source:{fileName:_,lineNumber:113,columnNumber:33}}),m.a.createElement(n.default,{type:"primary",htmlType:"submit",style:{width:100,height:36},__source:{fileName:_,lineNumber:114,columnNumber:37}},"  保存 "))))),m.a.createElement(O,{header:m.a.createElement(m.a.Fragment,null,m.a.createElement(p.default,{__source:{fileName:_,lineNumber:122,columnNumber:38}}),"  ",m.a.createElement("span",{style:{padding:"0 5px"},__source:{fileName:_,lineNumber:122,columnNumber:58}},"删除仓库")," "),key:"2",__source:{fileName:_,lineNumber:122,columnNumber:21}},m.a.createElement("div",{__source:{fileName:_,lineNumber:123,columnNumber:25}},m.a.createElement("div",{style:{display:"flex",alignItems:"center",margin:"0 0 10px 0"},__source:{fileName:_,lineNumber:124,columnNumber:29}},m.a.createElement("div",{style:{fontWeight:"bold"},__source:{fileName:_,lineNumber:125,columnNumber:33}},"删除此仓库"),m.a.createElement("div",{className:"ws-setting-delete",__source:{fileName:_,lineNumber:126,columnNumber:33}},"(删除存储库后,将无法返回)")),m.a.createElement(d,v({repositoryStore:r,repositoryName:g},e,{__source:{fileName:_,lineNumber:129,columnNumber:29}})))))))})))}}]);