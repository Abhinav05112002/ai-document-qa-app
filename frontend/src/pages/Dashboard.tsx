import { useState } from "react";

import FileUpload
from "../components/FileUpload";

import ChatBox
from "../components/ChatBox";

import VideoPlayer
from "../components/VideoPlayer";

import TimestampSearch
from "../components/TimestampSearch";

const Dashboard = () => {

    const [fileId, setFileId] =
        useState<number | null>(null);

    const [videoUrl, setVideoUrl] =
        useState<string | null>(null);

    const [seekTime, setSeekTime] =
        useState<number>(0);

    return (

        <div className="min-h-screen bg-gray-100">

            <div className="bg-black text-white p-4">

                <h1 className="text-3xl font-bold">

                    AI Multimedia Assistant

                </h1>

            </div>

            <div className="grid grid-cols-12 gap-4 p-4">

                <div className="col-span-3 space-y-4">

                    <FileUpload
                        setFileId={setFileId}
                        setVideoUrl={setVideoUrl}
                    />

                    <TimestampSearch
                        fileId={fileId}
                        setSeekTime={setSeekTime}
                    />

                </div>

                <div className="col-span-9 space-y-4">

                    <VideoPlayer
                        videoUrl={videoUrl}
                        seekTime={seekTime}
                    />

                    <ChatBox
                        fileId={fileId}
                    />

                </div>

            </div>

        </div>
    );
};

export default Dashboard;