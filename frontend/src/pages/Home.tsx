import { useEffect, useState } from "react";
import api from "../api/axios";

const Home = () => {

  const [message, setMessage] = useState("");

  useEffect(() => {

    api.get("/api/health")
      .then((response) => {
        console.log(response.data);
        setMessage(response.data);
      })
      .catch((error) => {
        console.error(error);
      });

  }, []);

  return (
    <div className="p-10">

      <h1 className="text-4xl font-bold mb-4">
        AI Document Q&A App
      </h1>

      <p className="text-xl">
        {message}
      </p>

    </div>
  );
};

export default Home;