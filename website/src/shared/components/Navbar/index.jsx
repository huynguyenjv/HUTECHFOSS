import { useState } from "react";
import Logo from "../../../assets/imges/logo.png";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars } from "@fortawesome/free-solid-svg-icons";

export default function Navbar() {
    const [mobileSidebarOpen, setMobileSidebarOpen] = useState(false);

    const navigate = useNavigate();

    return (
        <nav className="relative flex items-center justify-between w-full px-6 py-4 bg-[#003061ed] lg:px-20 shadow-2xl">
            <button onClick={() => navigate("/")} className="w-[170px] p-0 border-none bg-transparent cursor-pointer">
                <img src={Logo} alt="logo" className="w-[20%]" />
            </button>

            {/* Desktop Menu */}
            <div className="items-center gap-[20px] text-[1rem] text-white font-bold lg:flex hidden">
                <button
                    className="flex items-center gap-[5px] cursor-pointer hover:text-[#3B9DF8]"
                    onClick={() => navigate("/ban-do-thien-tai")}
                >
                    Bản Đồ Thiên Tai
                </button>
                <button
                    onClick={() => navigate("/theo-doi-bao")}
                    className="flex items-center gap-[5px] cursor-pointer hover:text-[#3B9DF8]"
                >
                    Theo Dõi Bão
                </button>
                <button
                    className="flex items-center gap-[5px] cursor-pointer hover:text-[#3B9DF8]"
                    onClick={() => navigate("/trung-tam-cuu-tro")}
                >
                    Trung Tâm CHCN
                </button>
                <button
                    className="flex items-center gap-[5px] cursor-pointer hover:text-[#3B9DF8]"
                    onClick={() => navigate("/vrelief-AI")}
                >
                    V-RELIEF
                </button>
            </div>

            {/* Mobile Sidebar */}
            <div
                className={`fixed top-0 left-0 h-full w-[70%] bg-[#003061] shadow-lg transform transition-transform duration-300 lg:hidden z-[100] ${
                    mobileSidebarOpen ? "translate-x-0" : "-translate-x-full"
                }`}
            >
                <button
                    className="absolute top-4 right-4 text-white text-[1.5rem]"
                    onClick={() => setMobileSidebarOpen(false)}
                >
                    &times;
                </button>
                <div className="flex flex-col items-start gap-4 p-4 mt-16 font-bold text-white">
                    <button
                        className="hover:text-[#3B9DF8]"
                        onClick={() => {
                            navigate("/ban-do-thien-tai");
                            setMobileSidebarOpen(false);
                        }}
                    >
                        Bản Đồ Thiên Tai
                    </button>
                    <button
                        className="hover:text-[#3B9DF8]"
                        onClick={() => {
                            navigate("/theo-doi-bao");
                            setMobileSidebarOpen(false);
                        }}
                    >
                        Theo Dõi Bão
                    </button>
                    <button
                        className="hover:text-[#3B9DF8]"
                        onClick={() => {
                            navigate("/trung-tam-cuu-tro");
                            setMobileSidebarOpen(false);
                        }}
                    >
                        Trung Tâm CHCN
                    </button>
                    <button
                        className="hover:text-[#3B9DF8]"
                        onClick={() => {
                            navigate("/vrelief-AI");
                            setMobileSidebarOpen(false);
                        }}
                    >
                        V-RELIEF
                    </button>
                </div>
            </div>
            <div className="flex items-center gap-[15px]">
                <FontAwesomeIcon
                    icon={faBars}
                    onClick={() => setMobileSidebarOpen(!mobileSidebarOpen)}
                    className="text-[1.8rem] text-white cursor-pointer lg:hidden flex"
                />
            </div>
        </nav>
    );
}
