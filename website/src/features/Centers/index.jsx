import ListCenter from "./components/ListCenter";
import { Pagination } from "antd";

export default function Centers() {
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
                            />
                        </div>
                        <div className="px-4 py-2 w-full md:w-[18%]">
                            <label className="block mb-2 text-gray-700 text-md">Tỉnh/Thành phố</label>
                            <select className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-teal-300">
                                <option value="Toàn quốc">Toàn quốc</option>
                                {/* Add more options if needed */}
                            </select>
                        </div>
                        <div className="w-full md:w-[18%] flex items-end">
                            <button className="w-full py-2 text-white bg-teal-500 rounded-md hover:bg-teal-400 focus:outline-none focus:ring-2 focus:ring-teal-300">
                                Tìm kiếm
                            </button>
                        </div>
                    </div>
                    <div className="mt-4 text-lg">
                        <span className="font-semibold">{`TÌM THẤY /355 KẾT QUẢ`}</span>
                    </div>
                    <div className="mt-10">
                        <div className="flex items-center justify-center">
                            <ListCenter />
                        </div>
                    </div>
                    <div className="flex items-center justify-center mt-10">
                        <Pagination defaultCurrent={1} total={50} />
                    </div>
                </div>
            </div>
        </div>
    );
}
