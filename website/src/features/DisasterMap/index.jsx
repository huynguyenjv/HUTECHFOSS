import { useState, useEffect } from "react";
import MapGL, { Marker } from "@goongmaps/goong-map-react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMapMarker, faMarker } from "@fortawesome/free-solid-svg-icons";
import { useWebhookResponse } from "../../hook/usePageTracker";

const DisasterMap = () => {
    const response = useWebhookResponse(); // Dữ liệu từ webhook
    const [disasterLocations, setDisasterLocations] = useState([]); // Danh sách địa điểm
    const [filteredLocations, setFilteredLocations] = useState([]); // Danh sách lọc theo tìm kiếm
    const [viewport, setViewport] = useState({
        latitude: 10.7769,
        longitude: 106.7009,
        zoom: 12,
    });

    const [selectedLocation, setSelectedLocation] = useState(null);
    const [searchTerm, setSearchTerm] = useState(""); // Từ khóa tìm kiếm

    // Đồng bộ dữ liệu từ webhook vào `disasterLocations`
    useEffect(() => {
        if (response && Array.isArray(response)) {
            setDisasterLocations(response);
            setFilteredLocations(response); // Khởi tạo danh sách lọc ban đầu
        }
    }, [response]);

    // Lọc danh sách địa điểm theo từ khóa tìm kiếm
    useEffect(() => {
        setFilteredLocations(
            disasterLocations.filter((location) =>
                location.TenKhuVuc?.toLowerCase().includes(searchTerm.toLowerCase()),
            ),
        );
    }, [searchTerm, disasterLocations]);

    // Handle click vào địa điểm trong danh sách
    const handleLocationClick = (location) => {
        setSelectedLocation(location);
        setViewport({
            ...viewport,
            latitude: location.ViDo,
            longitude: location.KinhDo,
            zoom: 14, // Zoom vào địa điểm khi click
        });
    };

    return (
        <div className="grid w-full grid-cols-3 gap-6 p-3 mx-auto">
            {/* Form tìm kiếm địa điểm */}
            <div className="flex flex-col col-span-1 p-6 bg-gray-200 rounded-lg shadow-md">
                <h2 className="mb-6 text-2xl font-bold text-center text-teal-500">
                    Tra cứu các địa điểm gặp thiên tai
                </h2>
                <input
                    type="text"
                    placeholder="Tìm kiếm địa điểm..."
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    className="px-4 py-2 mb-4 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-teal-400"
                />
                <div className="overflow-y-auto" style={{ maxHeight: "400px" }}>
                    <ul className="gap-3">
                        {filteredLocations.map((location) => (
                            <li
                                key={location.id}
                                className="p-3 mb-2 bg-white rounded-lg cursor-pointer hover:bg-gray-300"
                                onClick={() => handleLocationClick(location)}
                            >
                                <div className="font-bold">{location.TenKhuVuc}</div>
                                <div className="text-sm text-gray-500">
                                    <FontAwesomeIcon icon={faMarker} className="text-red-500" /> {location.DiaChi}
                                </div>
                                <div className="mt-2 text-sm text-gray-500">
                                    <FontAwesomeIcon icon={faMapMarker} className="text-red-500" /> {location.KinhDo},{" "}
                                    {location.ViDo}
                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>

            {/* Bản đồ */}
            <div className="col-span-2">
                <MapGL
                    latitude={viewport.latitude}
                    longitude={viewport.longitude}
                    zoom={viewport.zoom}
                    width="100%"
                    height="700px"
                    goongApiAccessToken="jBgynItAyXhnxXR1O8JoKBEYqgtL0wo7Z3nPz6VA"
                    onViewportChange={(nextViewport) => setViewport(nextViewport)}
                >
                    {/* Marker cho tất cả các địa điểm gặp thiên tai */}
                    {filteredLocations.map((location) => (
                        <Marker
                            key={location.id}
                            latitude={location.ViDo}
                            longitude={location.KinhDo}
                            draggable={false}
                        >
                            <div className="text-2xl text-red-600">📍</div>
                        </Marker>
                    ))}
                </MapGL>
            </div>
        </div>
    );
};

export default DisasterMap;
