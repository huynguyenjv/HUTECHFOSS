import lawHero from "../../../../assets/lottie/logo.json";
import Lottie from "lottie-react";

const HeroSection = () => {
    return (
        <div className="w-full bg-[#fff] rounded-md relative px-6 py-8 sm:px-10 sm:py-12">
            {/* Header */}
            <header className="flex flex-col items-center justify-between gap-8 px-6 sm:gap-12 lg:flex-row lg:gap-0">
                <div className="w-[80px] h-[80px] sm:w-[100px] sm:h-[100px] bg-[#008DDA] blur-[90px] absolute bottom-[50px] right-[50px] lg:bottom-[80px] lg:right-[80px]"></div>
                <div className="w-full lg:w-[45%] text-center lg:text-left">
                    <p className="text-[1rem]">Chào mọi người!</p>
                    <h1 className="text-[20px] sm:text-[40px] font-semibold leading-[30px] sm:leading-[50px] lg:leading-[70px]">
                        <span className="text-[#003161]">HUTECHFOSS</span> Cung Cấp Nhu Yếu Phẩm Và Cứu Trợ
                    </h1>
                    <p className="mt-2 text-[0.9rem] sm:text-[1rem]">
                        Đây là hệ thống hỗ trợ cung cấp và cứu trợ nhu yếu phẩm sau thiên tai
                    </p>
                </div>

                <div className="w-full lg:w-[55%] flex justify-center">
                    <Lottie style={{ width: "100%", maxWidth: "600px", height: "auto" }} animationData={lawHero} />
                </div>
            </header>

            {/* Features Section */}
            <section className="px-6 sm:px-8 pb-[30px]">
                <h1 className="text-[1.1rem] sm:text-[1.3rem] font-semibold text-center lg:text-left">
                    Chức năng của chúng tôi
                </h1>
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-[20px] mt-8 w-full lg:w-[70%] mx-auto lg:mx-0">
                    <div className="text-center lg:text-left">
                        <img
                            src="https://i.ibb.co/GcsvXxk/Product.png"
                            alt="Vector"
                            className="w-[30px] mx-auto lg:mx-0"
                        />
                        <h4 className="text-[1rem] sm:text-[1.1rem] mt-3">Tra cứu</h4>
                        <p className="text-[0.8rem] sm:text-[0.9rem] text-gray-500 mt-1">
                            Tra cứu các địa phương gặp thiên tai
                        </p>
                    </div>
                    <div className="text-center lg:text-left">
                        <img
                            src="https://i.ibb.co/Qn78BRJ/Ui-Design.png"
                            alt="Vector"
                            className="w-[30px] mx-auto lg:mx-0"
                        />
                        <h4 className="text-[1rem] sm:text-[1.1rem] mt-3">Trung tâm cứu trợ</h4>
                        <p className="text-[0.8rem] sm:text-[0.9rem] text-gray-500 mt-1">
                            Danh sách các trung tâm cứu trợ theo tỉnh thành
                        </p>
                    </div>
                    <div className="text-center lg:text-left">
                        <img
                            src="https://i.ibb.co/GcsvXxk/Product.png"
                            alt="Vector"
                            className="w-[30px] mx-auto lg:mx-0"
                        />
                        <h4 className="text-[1rem] sm:text-[1.1rem] mt-3">Map</h4>
                        <p className="text-[0.8rem] sm:text-[0.9rem] text-gray-500 mt-1">Bản đồ di chuyển của bão</p>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default HeroSection;
