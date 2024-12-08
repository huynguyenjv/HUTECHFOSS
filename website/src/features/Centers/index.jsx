import { useEffect, useState } from "react";
import ListCenter from "./components/ListCenter";
import { Pagination } from "antd";

export default function Centers() {
    const [centers, setCenters] = useState([]);
    const [name, setName] = useState(""); // Track the name search value
    const [province, setProvince] = useState(""); // Track the province search value
    const [page, setPage] = useState(1); // Track the current page
    const [searchKey, setSearchKey] = useState(0); // Track search key to trigger re-fetch

    const limit = 10; // Example: limit number of results

    // Fetch data when limit, page, name, province, or searchKey changes
    useEffect(() => {
        const url = `http://localhost:5678/webhook-test/relief-center?limit=${limit}&page=${page}&search[name]=${name}&search[province]=${province}`;

        fetch(url)
            .then((response) => response.json())
            .then((data) => setCenters(data))
            .catch((error) => console.error("Error fetching data:", error));
    }, [limit, page, name, province, searchKey]); // Add searchKey as dependency

    const handleSearch = () => {
        setSearchKey((prevKey) => prevKey + 1); // Increment searchKey to force re-fetch
        setPage(1); // Reset to page 1 when searching
    };

    return (
        <div className="mt-10">
            <div className="flex items-center justify-center">
                <div className="container p-4 mx-auto">
                    <div className="mb-4 text-4xl font-semibold text-center text-teal-500">Trung tâm cứu hộ</div>
                    <div className="flex flex-col items-center justify-center w-full space-y-4 md:flex-row md:space-y-0 md:space-x-4">
                        <div className="px-4 py-2 w-full md:w-[18%]">
                            <label className="block mb-2 text-gray-700 text-md">Trung tâm cứu hộ</label>
                            <input
                                type="text"
                                placeholder="Tìm kiếm trung tâm cứu hộ"
                                className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-teal-300 text-md"
                                value={name}
                                onChange={(e) => setName(e.target.value)} // Update name state on change
                            />
                        </div>
                        <div className="px-4 py-2 w-full md:w-[18%]">
                            <label className="block mb-2 text-gray-700 text-md">Tỉnh/Thành phố</label>
                            <select
                                className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-teal-300"
                                value={province}
                                onChange={(e) => setProvince(e.target.value)} // Update province state on change
                            >
                                <option value="">Chọn tỉnh/thành phố</option>
                                <option value="Toàn quốc">Toàn quốc</option>
                                {/* Add more options if needed */}
                            </select>
                        </div>
                        <div className="w-full md:w-[18%] flex items-end">
                            <button
                                className="w-full py-2 text-white bg-teal-500 rounded-md hover:bg-teal-400 focus:outline-none focus:ring-2 focus:ring-teal-300"
                                onClick={handleSearch} // Trigger search
                            >
                                Tìm kiếm
                            </button>
                        </div>
                    </div>
                    <div className="mt-4 text-lg">
                        <span className="font-semibold">{`TÌM THẤY /355 KẾT QUẢ`}</span>
                    </div>
                    <div className="mt-10">
                        <div className="flex items-center justify-center">
                            <ListCenter data={centers} />
                        </div>
                    </div>
                    <div className="flex items-center justify-center mt-10">
                        <Pagination
                            defaultCurrent={1}
                            total={50}
                            onChange={(page) => setPage(page)} // Update page state on pagination change
                        />
                    </div>
                </div>
            </div>
        </div>
    );
}
