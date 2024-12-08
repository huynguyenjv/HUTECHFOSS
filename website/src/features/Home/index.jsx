import { Container } from "@mui/material";
import NavigatorCard from "./components/NavigatorCard";
import logo from "../../assets/lottie/logo.json";
const NavigatorCardData = [
    { index: 1, title: "Theo dõi bão", animationData: logo, link: "/theo-doi-bao" },
    { index: 2, title: "Trung tâm cứu trợ", animationData: logo, link: "/trung-tam-cuu-tro" },
    { index: 3, title: "Đăng ký nhận thông báo", animationData: logo, link: "/dang-ky-nhan-thong-bao" },
];
export default function Home() {
    return (
        <main>
            <Container className="mt-5">
                <div className="flex justify-center grid-cols-1 gap-4 md:grid-cols-3">
                    <div className="text-center md:col-span-2">
                        <h1 className="mb-3 text-2xl font-bold tracking-normal text-blue-gray-900">
                            Các chức năng của ứng dụng
                        </h1>
                    </div>
                </div>
                <div className="grid grid-cols-1 gap-4 md:grid-cols-3">
                    {NavigatorCardData.map((item) => (
                        <NavigatorCard key={item.index} item={item} />
                    ))}
                </div>
            </Container>
        </main>
    );
}
