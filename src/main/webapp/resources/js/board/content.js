import {addBoard, API_SERVER_HOST, getBoard} from "../api/board-api.js";
import {saveFile, removeFile} from "../api/file-api.js";
import {drawTable, draw} from "./drawFile.js";

const queryParam = new URLSearchParams(location.search);
const id = queryParam.get('id')
const size = queryParam.get('size')
const page = queryParam.get('page')
let data = await getBoard(id)
let files = [];

const fileUpdate = () => {
  // 새로운 이미지 저장
  const inputTag = document.querySelector('input[type="file"]');
  inputTag.addEventListener('change', async (e) => {
    let formData = new FormData();
    for (let item of e.target.files) {
      formData.append("files", item)
    }
    let fileData = await saveFile(formData);
    for (let item of fileData) files.push(item);
    draw(fileData);
  })
}

const boardUpdate = () => {
  const updateBtn = document.querySelector("tr > button:first-child");
  const fileList = data.attachFileList;
  updateBtn.addEventListener('click', async (e) => {
    e.preventDefault();
    const formData = new FormData();
    const fk = document.querySelector('input[type="hidden"]').dataset.user;
    const title = document.querySelector('input[name="title"]').value;
    const content = document.querySelector('textarea').value;

    formData.append("fk", fk);
    formData.append("title", title);
    formData.append("content", content);
    console.log("why ? ", files)
    console.log("why ? ", JSON.stringify(files))
    formData.append("files", JSON.stringify(files));
    for (let item of formData.values()) {
      console.log(item)
    }

    let data = await addBoard(formData, id);
    if (data === 1) location.href = `${API_SERVER_HOST}/board/list?size=${size}&page=${page}`;
  })
}

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
  const documentWrapper = document.querySelectorAll('#documentWrapper div span')
  const imgWrapper = document.querySelectorAll('#imgWrapper div span')

  let delData = {};

  documentWrapper.forEach(e => {
    e.addEventListener('click', (e) => {
      const delEle = e.target.previousSibling;
      const imgSrc = delEle.getAttribute('src')
      e.target.parentNode.style.display = 'none'
      delData = getDelFileList(imgSrc)
      removeFile(id, delData)
    })
  })

  imgWrapper.forEach(e => {
    e.addEventListener('click', (e) => {
      const delEle = e.target.previousSibling;
      const imgSrc = delEle.getAttribute('src')
      e.target.parentNode.style.display = 'none'
      delData = getDelFileList(imgSrc)
      removeFile(id, delData)
    })
  })
}

drawTable(data, fileUpdate, boardUpdate)
delFile();