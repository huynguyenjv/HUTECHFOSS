import NavigatorCard from "./components/NavigatorCard";
import logo from "../../assets/lottie/logo.json";
import Searching from "../../assets/lottie/searching.json";
import Map from "../../assets/lottie/map.json";
import Notify from "../../assets/lottie/notify.json";
import VRelief from "../../assets/lottie/bot.json";
const NavigatorCardData = [
    { index: 1, title: "Theo dõi bão", animationData: Map, link: "/theo-doi-bao" },
    { index: 2, title: "Bản đồ thiên tai", animationData: Searching, link: "/ban-do-thien-tai" },
    { index: 3, title: "Trung tâm CNCH", animationData: logo, link: "/trung-tam-cuu-tro" },
    { index: 4, title: "Thông báo", animationData: Notify, link: "/dang-ky-nhan-thong-bao" },
    { index: 5, title: "V-RELIEF", animationData: VRelief, link: "/vrelief-AI" },
];
export default function Home() {
    return (
        <main>
            <div className="mx-20 mt-5 mb-10">
                <div className="flex justify-center grid-cols-1 gap-4 md:grid-cols-3">
                    <div className="text-center md:col-span-2">
                        <h1 className="mb-3 text-2xl font-bold tracking-normal uppercase text-blue-gray-900">
                            Các chức năng của ứng dụng
                        </h1>
                    </div>
                </div>
                <div className="grid grid-cols-1 gap-4 md:grid-cols-5">
                    {NavigatorCardData.map((item) => (
                        <NavigatorCard key={item.index} item={item} />
                    ))}
                </div>
            </div>
        </main>
    );
}
