import {API_SERVER_HOST, getList} from "./api/board-api.js";

export let queryParam = new URLSearchParams(location.search);

const selectTag = document.querySelector("select")
let size = queryParam.get('size');
let page = queryParam.get('page');

selectTag.addEventListener('change', (e) => {
  localStorage.setItem('size', document.querySelector('select').selectedOptions[0].innerHTML);
  size = localStorage.getItem('size');
  location.href = `${API_SERVER_HOST}/board/list?size=${size}&page=${page}`;
})

const changeSelectIndex = (size) => {
  if (size === '3')
    selectTag.selectedIndex = 0
  if (size === '5')
    selectTag.selectedIndex = 1
  if (size === '10')
    selectTag.selectedIndex = 2
}
changeSelectIndex(size);

getList(size, page);