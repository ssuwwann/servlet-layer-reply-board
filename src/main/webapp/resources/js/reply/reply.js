import {addReply, getReply} from '../api/reply-api.js';

const replyData = {};
const submitBtn = document.querySelector('#replyWrite > button')
const boardpk = new URLSearchParams(location.search).get('id');
const content = document.querySelector('#replyWrite > div[contenteditable="true"]');
const user = document.querySelector('input[type="hidden"]').dataset.user;

// get Reply
const data = await getReply(boardpk);

submitBtn.addEventListener('click', async (e) => {
  if (user.length === 0) {
    alert('로그인을 먼저 해주세요');
    return;
  }

  if (content.innerText.trim().replaceAll(" ", "").length === 0) {
    alert("글을 작성해주세요.")
    return;
  }

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
    html += `<hr>`
    html += `<div><span>${item.nickname}</span><span>${item.writeDate}</span></div>`
    html += `<div contenteditable="true">${item.content}</div>`
    if (user.length !== 0) {
      if (Number(user) === item.memberFk) {
        html += `<div><button>수정</button><button>삭제</button></div>`
      }
    }
    replyArea.innerHTML = html;
  }

  // 회원이 아니면 수정 불가

}
drawReply(data);