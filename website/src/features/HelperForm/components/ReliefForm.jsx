import { useState } from "react";
import { GoogleMap, LoadScript, Marker } from "@react-google-maps/api";

const ReliefForm = () => {
    const [formData, setFormData] = useState({
        fullName: "",
        region: "",
        damageLevel: "",
        address: "",
        nesscessity: "",
    });

    const [coordinates, setCoordinates] = useState({ lat: 10.7769, lng: 106.7009 }); // Default: Ho Chi Minh City

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleAddressChange = async (e) => {
        handleChange(e);
        const address = e.target.value;

        try {
            const response = await fetch(
                `https://rsapi.goong.io/Geocode?address=${encodeURIComponent(address)}&api_key=YOUR_GOONG_API_KEY`,
            );
            const data = await response.json();
            if (data.results.length > 0) {
                const { lat, lng } = data.results[0].geometry.location;
                setCoordinates({ lat, lng });
            }
        } catch (error) {
            console.error("Error fetching coordinates:", error);
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Form Data:", formData);
    };

    return (
        <div className="grid w-full grid-cols-3 gap-6 p-6 mx-aut">
            {/* Form - chiếm 1 cột */}
            <div className="col-span-1 p-6 bg-gray-200 rounded-lg shadow-md">
                <h2 className="mb-6 text-2xl font-bold text-center text-teal-500">Yêu cầu cứu trợ khẩn cấp</h2>
                <form onSubmit={handleSubmit} className="space-y-6">
                    <div>
                        <label className="block mb-2 text-gray-700">Họ tên người nhận:</label>
                        <input
                            type="text"
                            name="fullName"
                            value={formData.fullName}
                            onChange={handleChange}
                            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                        />
                    </div>

                    <div>
                        <label className="block mb-2 text-gray-700">Khu vực nhận cứu trợ:</label>
                        <input
                            type="text"
                            name="region"
                            value={formData.region}
                            onChange={handleChange}
                            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                        />
                    </div>

                    <div>
                        <label className="block mb-2 text-gray-700">Mức độ thiệt hại:</label>
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
                        <label className="block mb-2 text-gray-700">Địa chỉ người nhận:</label>
                        <input
                            type="text"
                            name="address"
                            value={formData.address}
                            onChange={handleAddressChange}
                            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                        />
                    </div>
                    <div>
                        <label className="block mb-2 text-gray-700">Nhu yếu phẩm cần thiết:</label>
                        <textarea
                            type="text"
                            name="address"
                            value={formData.nesscessity}
                            onChange={handleAddressChange}
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
                <LoadScript googleMapsApiKey="YOUR_GOOGLE_MAPS_API_KEY">
                    <GoogleMap mapContainerStyle={{ height: "700px", width: "100%" }} center={coordinates} zoom={15}>
                        <Marker position={coordinates} />
                    </GoogleMap>
                </LoadScript>
            </div>
        </div>
    );
};

export default ReliefForm;
