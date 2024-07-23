import {API_SERVER_HOST} from "../api/board-api.js";

const user = document.querySelector('input[type="hidden"]').dataset.user;

export const drawTable = (data, fileUpdate, boardUpdate) => {
  console.log(data)
  let tableData = '';
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

export const draw = (fileData) => {
  for (let item of fileData) {
    console.log("item", item)
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
