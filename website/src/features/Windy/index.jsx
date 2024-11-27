import { useEffect, useState } from "react";
import WindyWidget from "./components/WindyWidget";

export default function Windy() {
    const [coords, setCoords] = useState({ lat: null, lon: null });

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
        if (coords.lat && coords.lon) {
            const loadScript = (url, callback) => {
                const script = document.createElement("script");
                script.src = url;
                script.async = true;
                script.onload = callback;
                script.onerror = () => console.error(`Không tải được script: ${url}`);
                document.body.appendChild(script);
            };

            // Tải Leaflet trước
            loadScript("https://unpkg.com/leaflet@1.4.0/dist/leaflet.js", () => {
                console.log("Leaflet loaded");

                // Sau khi Leaflet tải xong, tải Windy
                loadScript("https://api.windy.com/assets/map-forecast/libBoot.js", () => {
                    console.log("Windy loaded");

                    // Đảm bảo Windy và Leaflet đã sẵn sàng
                    if (window.windyInit && window.L) {
                        window.windyInit({
                            key: "UeD1hfHz9sJXWIl3AUcxMF3WSoqk8kkf",
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
        // <div id="windy" style={{ width: "100%", height: "500px" }}>
        //     {!coords.lat && <p>Đang lấy vị trí...</p>}
        // </div>
        <div>
            <WindyWidget />
        </div>
    );
}
