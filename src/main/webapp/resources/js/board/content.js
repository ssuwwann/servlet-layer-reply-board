import {getBoard} from "../api/board-api.js";

let queryParam = new URLSearchParams(location.search);
let size = queryParam.get('size');
let page = queryParam.get('page');
let id = queryParam.get('id')

let data = await getBoard(id)
let tableData = '';

const drawFile = async () => {
  const fileList = data.attachFileList;
  for (let item of fileList) {
    const filepathUri = encodeURI(item.filePath);
    const saveNameUri = encodeURI(item.saveName);
    const extention = item.originalName.substring(item.originalName.lastIndexOf(".") + 1);
    const queryStr = "filepath=" + filepathUri + "&filename=" + saveNameUri;

    const divTag = document.createElement('div');
    const imgTag = document.createElement('img');
    const downloadTag = document.createElement('a');
    const hrTag = document.createElement('hr');

    if (extention.startsWith("jp") || extention.startsWith("png")) {
      imgTag.setAttribute('src', "http://localhost:8087/file?" + queryStr);
      divTag.appendChild(imgTag);
      document.body.append(divTag);
    }

    downloadTag.href = "http://localhost:8087/file?" + queryStr;
    downloadTag.setAttribute('download', item.originalName);
    downloadTag.textContent = item.originalName;
    document.body.append(downloadTag);
    downloadTag.after(hrTag);

  }
}

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

  drawFile();

}
drawTable();

