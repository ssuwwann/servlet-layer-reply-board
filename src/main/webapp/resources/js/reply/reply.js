import {addReply} from '../api/reply-api.js';

const submitBtn = document.querySelector('#replyWrapper > button')

submitBtn.addEventListener('click', async (e) => {
  const userid = document.querySelector('input[type="hidden"]').dataset.user;
  const boardpk = document.querySelector('input[name="nickname"]').dataset.pk;
  const content = document.querySelector('#replyWrapper > textarea').value;
  let formData = new FormData();

  formData.append("userid", userid);
  formData.append("boardpk", boardpk);
  formData.append("content", content);
  const data = await addReply(formData);
})
