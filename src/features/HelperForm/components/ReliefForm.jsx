import { useState, useEffect } from "react";
import MapGL, { Marker } from "@goongmaps/goong-map-react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPaperPlane } from "@fortawesome/free-solid-svg-icons";

const ReliefForm = () => {
    const [coordinates, setCoordinates] = useState({ lat: 10.7769, lng: 106.7009 });
    const [viewport, setViewport] = useState({
        latitude: 10.7769,
        longitude: 106.7009,
        zoom: 15,
    });
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState("");

    useEffect(() => {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    const { latitude, longitude } = position.coords;
                    setCoordinates({ lat: latitude, lng: longitude });
                    setViewport({
                        ...viewport,
                        latitude,
                        longitude,
                    });
                },
                (error) => {
                    console.error("Error getting location", error);
                },
            );
        } else {
            console.error("Geolocation is not supported by this browser.");
        }
    }, []);

    const handleMarkerDragEnd = (event) => {
        const { lngLat } = event;
        setCoordinates({ lat: lngLat.lat, lng: lngLat.lng });
        setViewport({
            ...viewport,
            latitude: lngLat.lat,
            longitude: lngLat.lng,
        });
    };

    const handleSendMessage = async () => {
        if (input.trim()) {
            const userMessage = { text: input, isUser: true };
            setMessages([...messages, userMessage]);

            // Gửi tin nhắn tới Botpress API
            try {
                const response = await fetch(
                    "http://<your-botpress-server>/api/v1/bots/<bot-id>/converse/<user-id>", // Cập nhật URL của bạn
                    {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify({
                            type: "text",
                            text: input,
                        }),
                    },
                );

                if (response.ok) {
                    const data = await response.json();
                    const botMessages = data.responses.map((res) => ({
                        text: res.text || "Bot không trả lời.",
                        isUser: false,
                    }));
                    setMessages((prevMessages) => [...prevMessages, ...botMessages]);
                } else {
                    console.error("Botpress API response error:", response.statusText);
                }
            } catch (error) {
                console.error("Error sending message to Botpress:", error);
            }

            setInput(""); // Xóa input sau khi gửi
        }
    };

    return (
        <div className="grid w-full grid-cols-3 gap-6 p-4 mx-aut">
            <div className="h-screen col-span-1 p-4 bg-gray-200 rounded-lg shadow-md">
                <div className="col-span-3 mt-6">
                    <iframe
                        src="https://cdn.botpress.cloud/webchat/v2.2/shareable.html?configUrl=https://files.bpcontent.cloud/2024/11/30/11/20241130111358-3XZYZKNQ.json"
                        width="100%"
                        height="600"
                        frameBorder="0"
                        title="BotPress Chat"
                    ></iframe>
                </div>
            </div>

            <div className="col-span-2">
                <MapGL
                    latitude={coordinates.lat}
                    longitude={coordinates.lng}
                    zoom={viewport.zoom}
                    width="100%"
                    height="700px"
                    goongApiAccessToken="jBgynItAyXhnxXR1O8JoKBEYqgtL0wo7Z3nPz6VA"
                    onViewportChange={(nextViewport) => setViewport(nextViewport)}
                >
                    <Marker
                        longitude={coordinates.lng || 106.7009}
                        latitude={coordinates.lat || 10.7769}
                        draggable
                        onDragEnd={handleMarkerDragEnd}
                    >
                        <div className="text-2xl text-red-600">📍</div>
                    </Marker>
                </MapGL>
            </div>
        </div>
    );
};

export default ReliefForm;
