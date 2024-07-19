export const API_SERVER_HOST = "http://localhost:8087";
let queryParam = new URLSearchParams(location.href);
let size = queryParam.get('size');
let page = queryParam.get('size');
console.log(size, page)

export const getList = () => {
  fetch(`${API_SERVER_HOST}/board/all`)
}
getList();
