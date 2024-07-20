export const API_SERVER_HOST = "http://localhost:8087";
let queryParam = new URLSearchParams(location.search);
let size = queryParam.get('size');
let page = queryParam.get('page');

const getList = async () => {
  const tbody = document.querySelector('tbody');
  const response = await fetch(`${API_SERVER_HOST}/board?size=${size}&page=${page}`);
  const data = await response.json();
  let html = '';

  console.log(data)
  for (let item of data.list) {
    html += '<tr>'
    html += `<td>${item.id}</td>`
    html += `<td>${item.title}</td>`
    html += `<td>${item.content}</td>`
    html += `<td>${item.viewCount}</td>`
    html += `<td>${item.likeCount}</td>`
    html += `<td>${item.writeDate}</td>`
    html += '<tr>'
  }
  tbody.innerHTML = html;
}
getList();