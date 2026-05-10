import {
    useEffect,
    useRef
} from "react";

interface Props {

    videoUrl: string | null;

    seekTime: number;
}

const VideoPlayer = ({
    videoUrl,
    seekTime
}: Props) => {

    const videoRef =
        useRef<HTMLVideoElement>(null);

    useEffect(() => {

        const video =
            videoRef.current;

        if (!video) return;

        if (!seekTime) return;

        console.log(
            "Moving video to:",
            seekTime
        );

        const seekVideo = () => {

            video.currentTime =
                seekTime;

            console.log(
                "Current Time After Seek:",
                video.currentTime
            );

            video.play()
                .catch((err) =>
                    console.error(err)
                );
        };

        /*
         * VERY IMPORTANT
         * Wait until metadata fully loaded
         */

        if (
            video.readyState >= 2
        ) {

            seekVideo();

        } else {

            video.onloadedmetadata =
                () => {

                seekVideo();
            };
        }

    }, [seekTime]);

    if (!videoUrl) {

        return (

            <div className="bg-white p-4 rounded shadow">

                Upload a video first

            </div>
        );
    }

    return (

        <div className="bg-white p-4 rounded shadow">

            <video
                ref={videoRef}
                controls
                className="w-full rounded"
                width="100%"
            >

                <source
                    src={videoUrl}
                    type="video/mp4"
                />

                Your browser does not support video.

            </video>

        </div>
    );
};

export default VideoPlayer;