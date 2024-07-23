import {API_SERVER_HOST} from "../api/board-api.js";

export const draw = (fileData) => {
  for (let item of fileData) {
    console.log("item",item)
    const delTag = document.createElement("span");
    delTag.innerText = '삭제'
    let extention = item.originalName.substring(item.originalName.lastIndexOf(".") + 1);
    let filePathUri = encodeURI(item.filePath);
    let saveNameUri = encodeURI(item.saveName);
    if (extention.startsWith("jp") || extention.startsWith("png")) {
      const imgTagParent = document.createElement('div');
      const imgTag = document.createElement('img');
      imgTag.src = `${API_SERVER_HOST}/file?filepath=${filePathUri}&filename=${saveNameUri}`;
      imgTagParent.append(imgTag)
      imgTagParent.append(delTag)
      document.querySelector('#imgWrapper').append(imgTagParent)
    } else {
      const fileTagParent = document.createElement('div');
      const aTag = document.createElement('a');
      const hrTag = document.createElement('hr');
      aTag.innerText = item.originalName;
      aTag.href = `${API_SERVER_HOST}/file?filepath=${filePathUri}&filename=${saveNameUri}`;
      aTag.setAttribute('download', item.originalName)
      fileTagParent.append(aTag);
      fileTagParent.append(delTag);
      document.querySelector('#documentWrapper').append(fileTagParent);
      document.querySelector('#documentWrapper').after(hrTag);
    }
  }
}
