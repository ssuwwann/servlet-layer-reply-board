import {API_SERVER_HOST} from "../api/board-api.js";

export const saveFile = async (formData) => {
  const response = await fetch(`${API_SERVER_HOST}/file`, {
    method: 'POST',
    body  : formData
  })
  return response.json()
}

export const removeFile = async (id, delData) => {
  console.log(delData)
  const response = await fetch(`${API_SERVER_HOST}/file/${id}`, {
    method: 'POST',
    body  : JSON.stringify(delData)
  })
  return response.json();
}