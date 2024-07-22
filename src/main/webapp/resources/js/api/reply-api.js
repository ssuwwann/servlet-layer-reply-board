export const API_SERVER_HOST = "http://localhost:8087";

export const addReply = async (data) => {
  const response = await fetch(`${API_SERVER_HOST}/reply`, {
    method : "POST",
    headers: {'Content-Type': 'application/json'},
    body   : JSON.stringify(data)
  });
  return response.json();
}

export const getReply = async (boardpk) => {
  const response = await fetch(`${API_SERVER_HOST}/reply/` + boardpk)
  return response.json();
}