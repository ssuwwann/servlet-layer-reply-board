import {API_SERVER_HOST} from "../api/board-api.js";
import {saveFile} from "../api/file-api.js";
import {addBoard} from "../api/board-api.js";

let queryParam = new URLSearchParams(location.search);
let size = queryParam.get('size');
let page = queryParam.get('page');

const submitBtn = document.querySelector('#submitBtn')
const fileInputTag = document.querySelector('input[type="file"]')
let files = [];

document.querySelector('input[name="name"]').value = document.querySelector('h2').innerText.split(' ')[0];

fileInputTag.addEventListener('change', async (e) => {
  let formData = new FormData();
  for (let item of e.target.files) {
    formData.append("files", item)
  }

  let fileData = await saveFile(formData);
  for (let item of fileData) {
    files.push(item);
    let extention = item.originalName.substring(item.originalName.lastIndexOf(".") + 1);
    if (extention.startsWith("jp") || extention.startsWith("png")) {
      let filePathUri = encodeURI(item.filePath);
      let saveNameUri = encodeURI(item.saveName);
      const imgTag = document.createElement('img');
      const spanTag = document.createElement('span');

      imgTag.src = `${API_SERVER_HOST}/file?filepath=${filePathUri}&filename=${saveNameUri}`;
      document.querySelector('#imgWrapper').append(imgTag);

      spanTag.innerText = '삭제';
      imgTag.after(spanTag)
    } else {
      const spanTag = document.createElement('span');
      const hrTag = document.createElement('hr');

      spanTag.innerText = item.originalName;
      document.querySelector('#fileWrwapper').append(spanTag);
      document.querySelector('#fileWrwapper').after(hrTag);
    }
  }
})


submitBtn.addEventListener('click', async (e) => {
  e.preventDefault()
  let formData = new FormData();
  const fk = document.querySelector('input[type="hidden"]').dataset.user;
  const title = document.querySelector('input[name="title"]').value;
  const content = document.querySelector('textarea').value;

  formData.append("fk", fk);
  formData.append("title", title);
  formData.append("content", content);
  formData.append("files", JSON.stringify(files));

  let data = await addBoard(formData);
  console.log(data)
  console.log(typeof data)
  if (data === 1) location.href = `${API_SERVER_HOST}/board/list?size=${size}&page=${page}`;
})