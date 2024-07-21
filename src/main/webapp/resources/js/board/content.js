import {getBoard} from "../api/board-api.js";

let queryParam = new URLSearchParams(location.search);
let size = queryParam.get('size');
let page = queryParam.get('page');
let id = queryParam.get('id')

let data = await getBoard(id)
console.log(data)
let tableData = '';

const drawTable = () => {
  const table = document.querySelector('table');
  tableData += '<tr><td width="30%" colspan="2" align="center"><h2>상세글</h2></td></tr>'
  tableData += `<tr><th>작가</th><td><input text='text' name='nickname' value='${data.nickname}' data-pk='${data.memberFk}'></td></tr>`
  tableData += `<tr><th>제목</th><td><input name='title' value='${data.title}'></td></tr>`
  tableData += `<tr><th>내용</th><td><textarea name='content'>${data.content}</textarea></td></tr>`

  table.innerHTML = tableData;

  if (document.querySelector('input[name="nickname"]').dataset.pk === document.querySelector('input[type="hidden"]').dataset.user) {
    const trTag1 = document.createElement('tr')
    const trTag2 = document.createElement('tr')
    const tdTag = document.createElement('td')
    const inputTag = document.createElement('input')
    const updateTag = document.createElement('button')
    const cancleTag = document.createElement('button')

    tdTag.setAttribute('colspan', 2);
    inputTag.setAttribute('type', 'file')
    inputTag.setAttribute('name', 'fils')
    inputTag.setAttribute('multiple', 'multiple')
    updateTag.innerHTML = '수정'
    cancleTag.innerHTML = '취소'

    tdTag.appendChild(inputTag)
    trTag1.appendChild(tdTag)
    trTag2.appendChild(updateTag)
    trTag2.appendChild(cancleTag)

    table.appendChild(trTag1)
    table.appendChild(trTag2)
  }
}
drawTable();