import {API_SERVER_HOST, getList} from "../api/board-api.js";

let queryParam = new URLSearchParams(location.search);

const selectTag = document.querySelector("select")
let size = queryParam.get('size');
let page = queryParam.get('page');
let data = await getList(size, page);

// 초기 size 설정
localStorage.setItem('size', size);

const changeSelectIndex = (size) => {
  if (size === '3')
    selectTag.selectedIndex = 0
  if (size === '5')
    selectTag.selectedIndex = 1
  if (size === '10')
    selectTag.selectedIndex = 2
}
changeSelectIndex(size);


selectTag.addEventListener('change', (e) => {
  localStorage.setItem('size', document.querySelector('select').selectedOptions[0].innerHTML);
  size = localStorage.getItem('size');
  location.href = `${API_SERVER_HOST}/board/list?size=${size}&page=${page}`;
})

const drawTable = () => {
  const tbody = document.querySelector('tbody');
  const pagination = document.querySelector('#pagination > span');
  let tbodyData = '';
  let paginationData = '';
  for (let item of data.list) {
    tbodyData += '<tr>'
    tbodyData += `<td>${item.id}</td>`
    tbodyData += `<td><a href="${API_SERVER_HOST}/board/content?id=${item.id}&size=${size}&page=${page}">${item.title}</a></td>`
    tbodyData += `<td>${item.content}</td>`
    tbodyData += `<td>${item.viewCount}</td>`
    tbodyData += `<td>${item.likeCount}</td>`
    tbodyData += `<td>${item.writeDate}</td>`
    tbodyData += '<tr>'
  }
  tbody.innerHTML = tbodyData;

  if (data.previousPage) paginationData += `<span><a href="${API_SERVER_HOST}/board/list?size=${size}&page=${data.currentPage - 1}">이전</a></span>`

  for (let i = data.startPage; i <= data.endPage; i++) {
    paginationData += `<span><a href="${API_SERVER_HOST}/board/list?size=${size}&page=${i}">${i}</a></span>`
  }

  if (data.nextPage) paginationData += `<span><a href="${API_SERVER_HOST}/board/list?size=${size}&page=${data.currentPage + 1}">다음</a></span>`

  pagination.innerHTML = paginationData;
}
drawTable();
