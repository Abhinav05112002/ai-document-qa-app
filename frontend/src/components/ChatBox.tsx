import { useState } from "react";
import api from "../api/axios";

interface Props {
    fileId: number | null;
}

interface Message {
    role: string;
    content: string;
}

const ChatBox = ({
    fileId
}: Props) => {

    const [question, setQuestion] =
        useState("");

    const [messages, setMessages] =
        useState<Message[]>([]);

    const [loading, setLoading] =
        useState(false);

    const askQuestion = async () => {

        if (!fileId) {

            alert("Upload file first");

            return;
        }

        if (!question.trim()) {
            return;
        }

        const userMessage = {
            role: "user",
            content: question,
        };

        setMessages((prev) => [
            ...prev,
            userMessage,
        ]);

        try {

            setLoading(true);

            const response =
                await api.post(
                    "/api/chat",
                    {
                        fileId,
                        question,
                    }
                );

            const aiMessage = {
                role: "assistant",
                content:
                    response.data.answer,
            };

            setMessages((prev) => [
                ...prev,
                aiMessage,
            ]);

            setQuestion("");

        } catch (error) {

            console.error(error);

            alert("Chat failed");

        } finally {

            setLoading(false);
        }
    };

    return (

        <div className="bg-white rounded shadow h-[80vh] flex flex-col">

            <div className="flex-1 overflow-y-auto p-4">

                {
                    messages.map(
                        (message, index) => (

                        <div
                            key={index}
                            className={
                                message.role === "user"
                                ? "text-right mb-4"
                                : "text-left mb-4"
                            }
                        >

                            <div
                                className={
                                    message.role === "user"
                                    ? "inline-block bg-black text-white p-3 rounded"
                                    : "inline-block bg-gray-200 p-3 rounded"
                                }
                            >

                                {message.content}

                            </div>

                        </div>
                    ))
                }

            </div>

            <div className="p-4 border-t flex gap-2">

                <input
                    type="text"
                    placeholder="Ask question..."
                    value={question}
                    onChange={(e) =>
                        setQuestion(
                            e.target.value
                        )
                    }
                    className="flex-1 border p-2 rounded"
                />

                <button
                    onClick={askQuestion}
                    className="bg-black text-white px-4 rounded"
                >

                    {
                        loading
                        ? "Thinking..."
                        : "Send"
                    }

                </button>

            </div>

        </div>
    );
};

export default ChatBox;