import { useState, useEffect } from "react";
import MapGL, { Marker } from "@goongmaps/goong-map-react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMapMarker, faMarker } from "@fortawesome/free-solid-svg-icons";

// Ví dụ về dữ liệu địa điểm gặp thiên tai
const disasterLocations = [
    { id: 1, name: "Quận 1, TP.HCM", lat: 10.7769, lng: 106.7009 },
    { id: 2, name: "Quận 2, TP.HCM", lat: 10.8011, lng: 106.6938 },
    { id: 3, name: "Quận 3, TP.HCM", lat: 10.7761, lng: 106.6961 },
    // Thêm các địa điểm khác vào đây
];

const DisasterMap = () => {
    const [viewport, setViewport] = useState({
        latitude: 10.7769,
        longitude: 106.7009,
        zoom: 12,
    });

    const [selectedLocation, setSelectedLocation] = useState(null);

    const [searchTerm, setSearchTerm] = useState(""); // Từ khóa tìm kiếm
    const [filteredLocations, setFilteredLocations] = useState(disasterLocations);

    useEffect(() => {
        // Lọc danh sách địa điểm khi người dùng nhập vào ô tìm kiếm
        setFilteredLocations(
            disasterLocations.filter((location) => location.name.toLowerCase().includes(searchTerm.toLowerCase())),
        );
    }, [searchTerm]);

    // Handle click vào địa điểm trong danh sách
    const handleLocationClick = (location) => {
        setSelectedLocation(location);
        setViewport({
            ...viewport,
            latitude: location.lat,
            longitude: location.lng,
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
                                {location.name}
                                <div className="mt-2 text-sm text-gray-500">
                                    <FontAwesomeIcon icon={faMapMarker} className="text-red-500" /> {location.lat},{" "}
                                    {location.lng}
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
                    {disasterLocations.map((location) => (
                        <Marker key={location.id} latitude={location.lat} longitude={location.lng} draggable={false}>
                            <div className="text-2xl text-red-600">📍</div>
                        </Marker>
                    ))}
                </MapGL>
            </div>
        </div>
    );
};

export default DisasterMap;
