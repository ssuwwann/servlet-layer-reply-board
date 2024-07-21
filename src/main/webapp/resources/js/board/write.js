import {API_SERVER_HOST} from "../api/board-api.js";
import {saveFile} from "../api/file-api.js";

const formTag = document.querySelector('form')
const fileInputTag = document.querySelector('input[type="file"]')
let fileList = [];

fileInputTag.addEventListener('change', async (e) => {
  let formData = new FormData();
  for (let item of e.target.files) {
    formData.append("file", item)
  }
  let fileData = await saveFile(formData);
  for (let item of fileData) {
    let extention = item.originalName.substring(item.originalName.lastIndexOf(".") + 1);
    if (extention.startsWith("jpg") || extention.startsWith("png")) {
      console.log(item)
      let filePathUri = encodeURI(item.filePath);
      let saveNameUri = encodeURI(item.saveName);

      const imgTag = document.createElement('img')
      imgTag.src = `${API_SERVER_HOST}/file?filepath=${filePathUri}&filename=${saveNameUri}`;
      document.querySelector('#imgWrapper').append(imgTag)
    }
  }
})


formTag.addEventListener('click', (e) => {
  //e.preventDefault();
  let formData = new FormData();
  formData.append("author", document.querySelector('input[name="name"]').value);
  formData.append("title", document.querySelector('input[name="title"]').value);
  formData.append("content", document.querySelector('textarea').innerText);

})