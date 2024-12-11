import { useEffect, useState, useRef } from "react";

export default function Windy() {
    const [coords, setCoords] = useState({ lat: null, lon: null });
    const windyRef = useRef(null); // Tham chiếu tới div chứa bản đồ

    useEffect(() => {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    const { latitude, longitude } = position.coords;
                    setCoords({ lat: latitude, lon: longitude });
                },
                (error) => {
                    console.error("Lỗi xác định vị trí:", error);
                },
            );
        } else {
            console.error("Trình duyệt không hỗ trợ Geolocation.");
        }
    }, []);

    useEffect(() => {
        if (coords.lat && coords.lon && !windyRef.current) {
            const loadScript = (url, callback) => {
                const script = document.createElement("script");
                script.src = url;
                script.async = true;
                script.onload = callback;
                script.onerror = () => console.error(`Không tải được script: ${url}`);
                document.body.appendChild(script);
            };

            loadScript("https://unpkg.com/leaflet@1.4.0/dist/leaflet.js", () => {
                loadScript("https://api.windy.com/assets/map-forecast/libBoot.js", () => {
                    if (window.windyInit && window.L) {
                        window.windyInit({
                            key: "UeD1hfHz9sJXWll3AUcxMF3WSoqk8kkf",
                            lat: coords.lat,
                            lon: coords.lon,
                            zoom: 8,
                        });
                    } else {
                        console.error("Windy hoặc Leaflet chưa được tải đúng cách.");
                    }
                });
            });
        }
    }, [coords]);

    return (
        <div>
            {!coords.lat && (
                <div className="flex flex-col items-center justify-center h-screen bg-blue-50">
                    <div className="w-16 h-16 border-t-4 border-blue-500 rounded-full animate-spin border-opacity-70"></div>
                    <p className="mt-5 text-xl font-semibold text-blue-700">Đang lấy vị trí...</p>
                </div>
            )}
            <div id="windy" style={{ width: "100%", height: "1000px" }} />
        </div>
    );
}
