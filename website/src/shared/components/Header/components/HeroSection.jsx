import lawHero from "../../../../assets/lottie/logo.json";
import Lottie from "lottie-react";
const HeroSection = () => {
    return (
        <div className="w-full bg-[#fff] rounded-md relative px-10 py-12">
            {/* header */}
            <header className="flex flex-col items-center justify-between gap-12 px-8 lg:flex-row lg:gap-0">
                <div className="w-[100px] h-[100px] bg-[#008DDA] blur-[90px] absolute bottom-[80px] right-[80px]"></div>
                <div className="w-full lg:w-[45%]">
                    <p>Chào mọi người!</p>
                    <h1 className="text-[10px] sm:text-[40px] font-semibold leading-[45px] sm:leading-[70px]">
                        <span className="text-[#003161]">HUTECHFOSS</span> Cung Cấp Nhu Yếu Phẩm Và Cứu Trợ
                    </h1>
                    <p className="mt-2 text-[1rem]">
                        Đây là hệ thống hỗ trợ cung cấp và cứu trợ nhu yếu phẩm sau thiên tai
                    </p>
                </div>

                <div className="w-full lg:w-[55%]">
                    <Lottie style={{ width: 900, height: 300, margin: "auto" }} animationData={lawHero} />
                    {/* <img src="https://i.ibb.co/syHFhNy/image.png" alt="image" className="" /> */}
                </div>
            </header>

            <section className="px-8 pb-[30px]">
                <h1 className="text-[1.3rem] font-semibold">Chức năng của chúng tôi</h1>
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-[20px] mt-10 w-[70%]">
                    <div>
                        <img src="https://i.ibb.co/GcsvXxk/Product.png" alt="Vector" className="w-[30px]" />
                        <h4 className="text-[1.1rem] mt-3">Tra cứu</h4>
                        <p className="text-[0.9rem] text-gray-500 mt-1">Tra cứu các địa phương gặp thiên tai</p>
                    </div>
                    <div>
                        <img src="https://i.ibb.co/Qn78BRJ/Ui-Design.png" alt="Vector" className="w-[30px]" />
                        <h4 className="text-[1.1rem] mt-3">Trung tâm cứu trợ</h4>
                        <p className="text-[0.9rem] text-gray-500 mt-1">
                            Danh sách các trung tâm cứu trợ theo tỉnh thành
                        </p>
                    </div>
                    <div>
                        <img src="https://i.ibb.co/GcsvXxk/Product.png" alt="Vector" className="w-[30px]" />
                        <h4 className="text-[1.1rem] mt-3">Map</h4>
                        <p className="text-[0.9rem] text-gray-500 mt-1">Bản đồ di chuyển của bão</p>
                    </div>
                </div>
            </section>

            {/* right blur shadow */}
        </div>
    );
};

export default HeroSection;
