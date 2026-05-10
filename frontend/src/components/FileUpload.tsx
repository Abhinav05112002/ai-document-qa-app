import { useState } from "react";
import api from "../api/axios";

interface Props {

    setFileId: (
        id: number
    ) => void;

    setVideoUrl: (
        url: string
    ) => void;
}

const FileUpload = ({
    setFileId,
    setVideoUrl
}: Props) => {

    const [file, setFile] =
        useState<File | null>(null);

    const [loading, setLoading] =
        useState(false);

    const uploadFile = async () => {

        if (!file) {

            alert("Select a file");

            return;
        }

        try {

            setLoading(true);

            const formData =
                new FormData();

            formData.append(
                "file",
                file
            );

            const response =
                await api.post(
                    "/api/files/upload",
                    formData,
                    {
                        headers: {
                            "Content-Type":
                                "multipart/form-data",
                        },
                    }
                );

            setFileId(response.data.id);

            alert(
                "File uploaded successfully"
            );

            setVideoUrl(response.data.fileUrl);

        } catch (error) {

            console.error(error);

            alert("Upload failed");

        } finally {

            setLoading(false);
        }
    };

    return (

        <div className="bg-white p-4 rounded shadow">

            <h2 className="text-xl font-bold mb-4">
                Upload File
            </h2>

            <input
                type="file"
                className="mb-4"
                onChange={(e) =>
                    setFile(
                        e.target.files?.[0]
                        || null
                    )
                }
            />

            <button
                onClick={uploadFile}
                className="bg-black text-white px-4 py-2 rounded w-full"
            >

                {
                    loading
                    ? "Uploading..."
                    : "Upload"
                }

            </button>

        </div>
    );
};

export default FileUpload;