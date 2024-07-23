import {API_SERVER_HOST, getBoard} from "../api/board-api.js";
import {saveFile, removeFile} from "../api/file-api.js";
import {draw} from "./drawFile.js";

const user = document.querySelector('input[type="hidden"]').dataset.user;
const queryParam = new URLSearchParams(location.search);
const id = queryParam.get('id')
const data = await getBoard(id)

let tableData = '';
const newFileOriginalNameList = [];

const fileUpdate = () => {
  // 새로운 이미지 저장
  const inputTag = document.querySelector('input[type="file"]');
  inputTag.addEventListener('change', async (e) => {
    let formData = new FormData();
    for (let item of e.target.files) {
      newFileOriginalNameList.push(item.originalName);
      formData.append("files", item)
    }
    let fileData = await saveFile(formData);
    draw(fileData);
  })
}

const boardUpdate = () => {
  const updateBtn = document.querySelector("tr > button:first-child");
  const formdata = new FormData();
  const fileList = data.attachFileList;
  console.log("fileList => ", fileList)
  updateBtn.addEventListener('click', (e) => {
    e.preventDefault();

  })
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
    inputTag.setAttribute('name', 'files')
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
  draw(data.attachFileList);

  if (user.length !== 0)
    if (data.memberFk === Number(user)) {
      fileUpdate()
      boardUpdate();
    }

}
drawTable();

const getDelFileList = (url) => {
  const queryParam = url.substring(url.indexOf('?') + 1);
  const paramArr = queryParam.split("&");
  const delFileList = [];
  for (let item of paramArr) {
    delFileList.push(item.substring(item.indexOf("=") + 1));
  }

  const delFileObj = {
    filePath: delFileList[0],
    saveName: delFileList[1]
  }

  return delFileObj;
}

const delFile = () => {
  const documentWrapper = document.querySelector('#documentWrapper')
  const imgWrapperDIv = document.querySelectorAll('#imgWrapper div span')

  let delData = {};
  documentWrapper.addEventListener('click', (e) => {
    console.log(e.target)
    if (e.target.innerText === '삭제') {
      const delEle = e.target.previousSibling;
      const aHref = delEle.getAttribute('href');
      e.target.parentNode.style.display = 'none'
      delData = getDelFileList(aHref)
      removeFile(id, delData)
    }
  })

  imgWrapperDIv.forEach(e => {
    e.addEventListener('click', (e) => {
      const delEle = e.target.previousSibling;
      const imgSrc = delEle.getAttribute('src')
      e.target.parentNode.style.display = 'none'
      delData = getDelFileList(imgSrc)
      removeFile(id, delData)
    })
  })
}
delFile();