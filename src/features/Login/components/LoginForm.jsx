import { useEffect, useState } from "react";
// import Logo from "~/assets/images/logo/logo2.png";
import Login from "../../../assets/lottie/loginLogo.json";
import Lottie from "lottie-react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Divider } from "antd";
import { faArrowAltCircleLeft } from "@fortawesome/free-regular-svg-icons";

export default function LoginForm() {
    const [showResent, setShowResent] = useState(false);
    const [timeLeft, setTimeLeft] = useState(60);

    useEffect(() => {
        if (timeLeft <= 0) return;

        const timer = setInterval(() => {
            setTimeLeft((prev) => prev - 1);
        }, 1000);

        return () => clearInterval(timer);
    }, [timeLeft]);

    const handleSubmit = () => {
        setShowResent(true);
    };
    const handleBack = () => {
        setShowResent(false);
    };

    return (
        <div className="flex items-center justify-center w-full h-screen ">
            <div className="grid w-full h-full grid-cols-1 gap-4 md:grid-cols-2">
                <div className="flex flex-col items-center justify-center px-6 py-10 ">
                    {/* <img loading="lazy" className="w-[30%]" src={Logo} alt="Logo" /> */}
                    <p className="mt-4 text-2xl font-bold text-center text-blue-gray-800">
                        Hệ Thống Cung Cấp Nhu Yếu Phẩm <br />
                        Và Cứu Trợ Sau Thiên Tai
                    </p>
                    <span className="mt-2 text-sm text-center text-blue-gray-800">
                        Đăng nhập để sử dụng hệ thống hỗ trợ cứu trợ sau thiên tai
                    </span>
                    <div
                        className={`flex-col items-center justify-center w-[90%] max-w-md p-5 mt-6 bg-white border rounded-xl gap-4 shadow-md ${
                            showResent ? "hidden" : "flex"
                        }`}
                    >
                        <button className="flex items-center justify-center w-full h-12 px-4 text-black bg-slate-200 rounded-xl hover:bg-slate-300">
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                className="w-5 h-5 me-2"
                                viewBox="0 0 48 48"
                                fill="none"
                            >
                                <path
                                    fill="#EA4335"
                                    d="M24 9.5c3.67 0 6.98 1.22 9.6 3.22l7.1-7.1C35.68 2.19 30.14 0 24 0 14.64 0 6.58 5.74 2.92 13.92l8.32 6.45C13.03 13.26 17.28 9.5 24 9.5Z"
                                />
                                <path
                                    fill="#34A853"
                                    d="M48 24c0-1.57-.15-3.09-.44-4.56H24v9.44h13.44c-.58 2.89-2.23 5.3-4.68 6.95l7.4 5.72C44.42 37.33 48 31.1 48 24Z"
                                />
                                <path
                                    fill="#4A90E2"
                                    d="M6.24 14.52C3.89 19.03 3.89 24.12 6.24 28.64l8.3-6.46c-1.26-2.38-1.26-5.34 0-7.72l-8.3-6.45Z"
                                />
                                <path
                                    fill="#FBBC05"
                                    d="M24 48c6.14 0 11.68-2.19 15.7-5.92l-7.4-5.72c-2.04 1.36-4.63 2.15-8.3 2.15-6.72 0-11.97-4.76-13.76-11.4l-8.31 6.46C6.58 42.26 14.64 48 24 48Z"
                                />
                            </svg>
                            Đăng nhập bằng Google
                        </button>
                        <button className="flex items-center justify-center w-full h-12 px-4 text-black bg-slate-200 rounded-xl hover:bg-slate-300">
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                className="w-5 h-5 me-2"
                                viewBox="0 0 48 48"
                                fill="#1877F2"
                            >
                                <path d="M36 2H12C6.48 2 2 6.48 2 12v24c0 5.52 4.48 10 10 10h12V29h-6v-7h6v-5.5c0-6.08 3.34-9.5 8.5-9.5 1.67 0 3.5.5 3.5.5v6h-3c-2.25 0-3 1.12-3 2.83V22h6l-1 7h-5v17h6c5.52 0 10-4.48 10-10V12c0-5.52-4.48-10-10-10Z" />
                            </svg>
                            Đăng nhập bằng Facebook
                        </button>
                        <Divider className="text-gray-500 text-md">Hoặc</Divider>
                        <input
                            type="email"
                            className="w-full h-12 px-4 text-gray-700 border border-gray-300 rounded-xl focus:outline-none focus:border-blue-400"
                            placeholder="Nhập email của bạn"
                        />
                        <button
                            className="flex items-center justify-center w-full h-12 px-4 mt-3 text-white bg-green-600 rounded-xl hover:bg-green-700"
                            onClick={handleSubmit}
                        >
                            Đăng nhập / Đăng ký
                        </button>
                    </div>
                    <div
                        className={` flex-col items-center justify-center w-[90%] max-w-md p-5 mt-6 bg-white border rounded-xl gap-4 shadow-md  ${
                            showResent ? "flex" : "hidden"
                        }`}
                    >
                        <span className="text-lg font-bold text-center">Kiểm tra mail của bạn</span>
                        <span className="mt-6 text-lg text-center">
                            Vui lòng truy cập vào mail nguyenmaibaohuy@gmail.com để xác thực
                        </span>
                        <div className="flex flex-row gap-2">
                            <button
                                className="flex items-center justify-center h-12 px-4 text-black bg-slate-200 rounded-xl hover:bg-slate-300"
                                onClick={handleBack}
                            >
                                <FontAwesomeIcon icon={faArrowAltCircleLeft} className="text-lg text-gray-400" />
                            </button>
                            <div className="flex items-center justify-center px-8 bg-slate-200 rounded-xl">
                                {timeLeft > 0 ? (
                                    <span className="font-bold text-gray-400">Gửi lại mail trong ( {timeLeft}s ) </span>
                                ) : (
                                    <span className="font-bold text-blue-500">Gửi lại mail</span>
                                )}
                            </div>
                        </div>
                    </div>
                    <span className="mt-4 text-sm text-center text-gray-400">
                        Bằng việc đăng ký, bạn đồng ý với Chính sách và <br /> Điều khoản của pháp luật Việt Nam
                    </span>
                    <span className="mt-10 text-sm text-center text-gray-400">
                        Đơn vị vận hành và triển khai: <br />
                        HUTECHFOSS
                    </span>
                </div>

                <div className="flex items-center justify-center rounded-lg bg-gradient-to-tr from-green-100 via-white to-green-300">
                    <Lottie
                        style={{ width: "70%", maxWidth: "900px", height: "auto", maxHeight: "100%" }}
                        animationData={Login}
                    />
                </div>
            </div>
        </div>
    );
}
