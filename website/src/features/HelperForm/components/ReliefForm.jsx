import { useState } from "react";
import MapGL, { Marker } from "@goongmaps/goong-map-react";

const ReliefForm = () => {
    const [formData, setFormData] = useState({
        fullName: "",
        region: "",
        damageLevel: "",
        address: "",
        necessity: "",
    });

    const [coordinates, setCoordinates] = useState({ lat: 10.7769, lng: 106.7009 }); // Default: Ho Chi Minh City

    const [viewport, setViewport] = useState({
        latitude: 10.7769,
        longitude: 106.7009,
        zoom: 12,
    });

    // Handle form data changes
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    // Handle address input and fetch coordinates
    const handleAddressChange = async (e) => {
        handleChange(e);
        const address = e.target.value;

        if (address.length > 3) {
            // Avoid unnecessary API calls
            try {
                const response = await fetch(
                    `https://rsapi.goong.io/Geocode?address=${encodeURIComponent(
                        address,
                    )}&api_key=jBgynItAyXhnxXR1O8JoKBEYqgtL0wo7Z3nPz6VA`,
                );
                const data = await response.json();
                if (data.results.length > 0) {
                    const { lat, lng } = data.results[0].geometry.location;
                    setCoordinates({ lat, lng });
                    setViewport({ ...viewport, latitude: lat, longitude: lng }); // Update map viewport
                }
            } catch (error) {
                console.error("Error fetching coordinates:", error);
            }
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Form Data:", formData);
    };
    const handleMarkerDragEnd = (event) => {
        const { lngLat } = event;
        setCoordinates({ lat: lngLat.lat, lng: lngLat.lng });
        setViewport({
            ...viewport,
            latitude: lngLat.lat,
            longitude: lngLat.lng,
        });
    };
    return (
        <div className="grid w-full grid-cols-3 gap-6 p-6 mx-aut">
            {/* Form - chiếm 1 cột */}
            <div className="col-span-1 p-6 bg-gray-200 rounded-lg shadow-md">
                <h2 className="mb-6 text-2xl font-bold text-center text-teal-500">Yêu cầu cứu trợ khẩn cấp</h2>
                <form onSubmit={handleSubmit} className="space-y-6">
                    <div>
                        <label htmlFor="fullName" className="block mb-2 text-gray-700">
                            Họ tên người nhận:
                        </label>
                        <input
                            type="text"
                            id="fullName"
                            name="fullName"
                            value={formData.fullName}
                            onChange={handleChange}
                            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                        />
                    </div>

                    <div>
                        <label htmlFor="region" className="block mb-2 text-gray-700">
                            Khu vực nhận cứu trợ:
                        </label>
                        <input
                            type="text"
                            id="region"
                            name="region"
                            value={formData.region}
                            onChange={handleChange}
                            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                        />
                    </div>

                    <div>
                        <label htmlFor="damageLevel" className="block mb-2 text-gray-700">
                            Mức độ thiệt hại:
                        </label>
                        <select
                            name="damageLevel"
                            value={formData.damageLevel}
                            onChange={handleChange}
                            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                        >
                            <option value="">Chọn mức độ</option>
                            <option value="Nhẹ">Nhẹ</option>
                            <option value="Trung bình">Trung bình</option>
                            <option value="Nghiêm trọng">Nghiêm trọng</option>
                        </select>
                    </div>

                    <div>
                        <label htmlFor="address" className="block mb-2 text-gray-700">
                            Địa chỉ người nhận:
                        </label>
                        <input
                            type="text"
                            id="address"
                            name="address"
                            value={formData.address}
                            onChange={handleAddressChange}
                            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                        />
                    </div>
                    <div>
                        <label htmlFor="nesscessity" className="block mb-2 text-gray-700">
                            Nhu yếu phẩm cần thiết:
                        </label>
                        <textarea
                            id="nesscessity"
                            name="nesscessity"
                            value={formData.nesscessity}
                            onChange={handleChange}
                            className="w-full h-32 px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                        />
                    </div>

                    <button
                        type="submit"
                        className="w-full py-2 text-white bg-teal-500 rounded-md hover:bg-teal-600 focus:outline-none focus:ring-2 focus:ring-teal-400"
                    >
                        Xác nhận
                    </button>
                </form>
            </div>

            {/* Bản đồ - chiếm 2 cột */}
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
