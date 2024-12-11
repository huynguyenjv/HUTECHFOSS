import { useState, useEffect } from "react";
import ListCenter from "./components/ListCenter";
import { Pagination } from "antd";
import { useWebhookResponse } from "../../hook/usePageTracker";

export default function Centers() {
    const response = useWebhookResponse(); // Lấy dữ liệu từ webhook
    const [centers, setCenters] = useState(response); // Lưu danh sách trung tâm hiển thị
    const [total, setTotal] = useState(0); // Tổng số kết quả
    const [name, setName] = useState(""); // Tìm kiếm theo tên
    const [province, setProvince] = useState(""); // Tìm kiếm theo tỉnh/thành phố
    const [page, setPage] = useState(1); // Trang hiện tại
    const limit = 10; // Số item mỗi trang

    useEffect(() => {
        if (response && response.results) {
            console.log(response);
            setCenters(response); // Hiển thị tất cả kết quả
        }
    }, [response]);

    // Lọc và phân trang dữ liệu
    const handleFilterAndPaginate = (data) => {
        const filteredData = data.filter((center) => {
            const matchesName = name ? center.name.toLowerCase().includes(name.toLowerCase()) : true;
            const matchesProvince = province ? center.province === province : true;
            return matchesName && matchesProvince;
        });

        setTotal(filteredData.length); // Tổng số kết quả sau lọc
        setCenters(filteredData.slice((page - 1) * limit, page * limit)); // Phân trang
    };

    // Xử lý khi nhấn nút tìm kiếm
    const handleSearch = () => {
        setPage(1); // Reset về trang đầu tiên
        if (response && response.results) {
            handleFilterAndPaginate(response.results);
        }
    };

    // Xử lý khi phân trang
    const handlePageChange = (newPage) => {
        setPage(newPage);
    };

    return (
        <div className="mt-10">
            <div className="flex items-center justify-center">
                <div className="container p-4 mx-auto">
                    <div className="mb-4 text-4xl font-semibold text-center text-teal-500">Trung tâm cứu hộ</div>
                    <div className="flex flex-col items-center justify-center w-full space-y-4 md:flex-row md:space-y-0 md:space-x-4">
                        {/* Input tìm kiếm */}
                        <div className="px-4 py-2 w-full md:w-[18%]">
                            <label className="block mb-2 text-gray-700 text-md">Trung tâm cứu hộ</label>
                            <input
                                type="text"
                                placeholder="Tìm kiếm trung tâm cứu hộ"
                                className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-teal-300 text-md"
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                            />
                        </div>
                        {/* Dropdown tỉnh/thành phố */}
                        <div className="px-4 py-2 w-full md:w-[18%]">
                            <label className="block mb-2 text-gray-700 text-md">Tỉnh/Thành phố</label>
                            <select
                                className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-teal-300"
                                value={province}
                                onChange={(e) => setProvince(e.target.value)}
                            >
                                <option value="">Chọn tỉnh/thành phố</option>
                                <option value="Toàn quốc">Toàn quốc</option>
                                <option value="Hà Nội">Hà Nội</option>
                                <option value="Hồ Chí Minh">Hồ Chí Minh</option>
                                {/* Thêm các tỉnh/thành phố khác nếu cần */}
                            </select>
                        </div>
                        {/* Nút tìm kiếm */}
                        <div className="w-full md:w-[18%] flex items-end">
                            <button
                                className="w-full py-2 text-white bg-teal-500 rounded-md hover:bg-teal-400 focus:outline-none focus:ring-2 focus:ring-teal-300"
                                onClick={handleSearch}
                            >
                                Tìm kiếm
                            </button>
                        </div>
                    </div>
                    {/* Thông tin kết quả */}
                    <div className="mt-4 text-lg">
                        <span className="font-semibold">{`TÌM THẤY ${total} KẾT QUẢ`}</span>
                    </div>
                    {/* Hiển thị danh sách trung tâm */}
                    <div className="mt-10">
                        <div className="flex items-center justify-center">
                            <ListCenter data={centers} />
                        </div>
                    </div>
                    {/* Phân trang */}
                    <div className="flex items-center justify-center mt-10">
                        <Pagination current={page} pageSize={limit} total={total} onChange={handlePageChange} />
                    </div>
                </div>
            </div>
        </div>
    );
}
