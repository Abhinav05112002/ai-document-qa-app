import { useState } from "react";
import api from "../api/axios";

interface Props {

    fileId: number | null;

    setSeekTime: (
        time: number
    ) => void;
}

const TimestampSearch = ({
    fileId,
    setSeekTime
}: Props) => {

    const [query, setQuery] =
        useState("");

    const [result, setResult] =
        useState<any>(null);

    const searchTimestamp =
        async () => {

        if (!fileId) {

            alert("Upload video first");

            return;
        }

        try {

            const response =
                await api.get(
                    `/api/timestamps/${fileId}?query=${query}`
                );

            setResult(response.data);

        } catch (error) {

            console.error(error);

            alert("Search failed");
        }
    };

    return (

        <div className="bg-white p-4 rounded shadow">

            <h2 className="text-xl font-bold mb-4">

                Timestamp Search

            </h2>

            <div className="flex gap-2 mb-4">

                <input
                    type="text"
                    placeholder="Search topic..."
                    value={query}
                    onChange={(e) =>
                        setQuery(
                            e.target.value
                        )
                    }
                    className="flex-1 border p-2 rounded"
                />

                <button
                    onClick={searchTimestamp}
                    className="bg-black text-white px-4 rounded"
                >

                    Search

                </button>

            </div>

            {
                result && (

                    <div className="border p-4 rounded">

                        <p className="mb-2">

                            {result.text}

                        </p>

                       <button
    onClick={() => {

        console.log(
            "Seeking to:",
            result.startTime
        );

        setSeekTime(
            Number(result.startTime)
        );
    }}
    className="bg-blue-500 text-white px-4 py-2 rounded"
>

    Jump to Timestamp

</button>

                    </div>
                )
            }

        </div>
    );
};

export default TimestampSearch;