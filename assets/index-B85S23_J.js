import{r,j as t}from"./index-B4A1g6Ej.js";import{I as d,M as g}from"./set-rtl-text-plugin-CjlIq5FM.js";import"./slicedToArray-D24CcagQ.js";import"./ResizeObserver.es-B1PUzC5B.js";const u=()=>{const[s,l]=r.useState({lat:10.7769,lng:106.7009}),[n,a]=r.useState({latitude:10.7769,longitude:106.7009,zoom:15});r.useState([]),r.useState(""),r.useEffect(()=>{navigator.geolocation?navigator.geolocation.getCurrentPosition(e=>{const{latitude:o,longitude:i}=e.coords;l({lat:o,lng:i}),a({...n,latitude:o,longitude:i})},e=>{console.error("Error getting location",e)}):console.error("Geolocation is not supported by this browser.")},[]);const c=e=>{const{lngLat:o}=e;l({lat:o.lat,lng:o.lng}),a({...n,latitude:o.lat,longitude:o.lng})};return t.jsxs("div",{className:"grid w-full grid-cols-3 gap-6 p-4 mx-aut",children:[t.jsx("div",{className:"h-screen col-span-1 p-4 bg-gray-200 rounded-lg shadow-md",children:t.jsx("div",{className:"col-span-3 mt-6",children:t.jsx("iframe",{src:"https://cdn.botpress.cloud/webchat/v2.2/shareable.html?configUrl=https://files.bpcontent.cloud/2024/11/30/11/20241130111358-3XZYZKNQ.json",width:"100%",height:"600",frameBorder:"0",title:"BotPress Chat"})})}),t.jsx("div",{className:"col-span-2",children:t.jsx(d,{latitude:s.lat,longitude:s.lng,zoom:n.zoom,width:"100%",height:"700px",goongApiAccessToken:"jBgynItAyXhnxXR1O8JoKBEYqgtL0wo7Z3nPz6VA",onViewportChange:e=>a(e),children:t.jsx(g,{longitude:s.lng||106.7009,latitude:s.lat||10.7769,draggable:!0,onDragEnd:c,children:t.jsx("div",{className:"text-2xl text-red-600",children:"📍"})})})})]})};function f(){return t.jsx("div",{className:"flex items-center justify-center min-h-screen bg-gray-100",children:t.jsx(u,{})})}export{f as default};
