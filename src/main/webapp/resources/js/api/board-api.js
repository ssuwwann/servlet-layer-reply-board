export const API_SERVER_HOST = "http://localhost:8087";

export const getList = async (size, page) => {
  const response = await fetch(`${API_SERVER_HOST}/board?size=${size}&page=${page}`);
  return response.json();
}