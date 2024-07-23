export const API_SERVER_HOST = "http://localhost:8087";

export const getList = async (size, page) => {
  const response = await fetch(`${API_SERVER_HOST}/board?size=${size}&page=${page}`);
  return response.json();
}

export const getBoard = async (id) => {
  const response = await fetch(`${API_SERVER_HOST}/board/content/${id}`);
  return response.json();
}

export const addBoard = async (formData, id) => {
  let req = `${API_SERVER_HOST}/board`;
  if (id) req = `${API_SERVER_HOST}/board/${id}`;

  const response = await fetch(req, {
    method: 'POST',
    body  : formData
  });
  return response.json();
}