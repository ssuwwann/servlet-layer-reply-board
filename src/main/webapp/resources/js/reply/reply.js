import {addReply, getReply} from '../api/reply-api.js';

const replyData = {};
const submitBtn = document.querySelector('#replyWrite > button')
const boardpk = new URLSearchParams(location.search).get('id');

// get Reply
const data = await getReply(boardpk);

submitBtn.addEventListener('click', async (e) => {
  const content = document.querySelector('#replyWrite > div[contenteditable="true"]');

  replyData.boardFk = boardpk;
  replyData.content = content.innerText;

  const resultData = await addReply(replyData);
  if (Number(resultData.result) > 0) {
    const newData = await getReply(boardpk);
    drawReply(newData);
    content.innerText = "";
  }
})

const drawReply = (data) => {
  const replyArea = document.querySelector("#replyArea");

  let html = '';
  for (let item of data) {
    html += `<div><span>${item.nickname}</span><span>${item.writeDate}</span></div>`
    html += `<div contenteditable="true">${item.content}</div>`
    replyArea.innerHTML = html;
  }
}
drawReply(data);