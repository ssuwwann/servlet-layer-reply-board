import {API_SERVER_HOST} from "../api/board-api.js";

export const saveFile = async (formData) => {
  const response = await fetch(`${API_SERVER_HOST}/file`, {
    method: 'POST',
    body  : formData
  })
  return response.json()
}

export const getFile = async () => {

}