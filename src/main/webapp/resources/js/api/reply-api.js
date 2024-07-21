export const API_SERVER_HOST = "http://localhost:8087";

export const addReply = async (formData) => {
  const response = await fetch(`${API_SERVER_HOST}/reply`, {
    method: "POST",
    body  : formData,
  });
  return response.json();
}

export const getReply = async () => {

}