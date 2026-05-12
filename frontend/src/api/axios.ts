import axios from "axios";

const api = axios.create({
  baseURL: "https://ai-document-backend-89b6.onrender.com",
});

export default api;