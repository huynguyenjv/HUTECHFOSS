import { Suspense, useEffect, useState } from "react";
import AOS from "aos";
import "aos/dist/aos.css";
import loadable from "@loadable/component";
import { BrowserRouter, Routes, Route, useLocation } from "react-router-dom";
import AuthLayout from "./shared/layouts/AuthLayout";
import BasicLayout from "./shared/layouts/BasicLayout";
import ContentLayout from "./shared/layouts/ContentLayout";
import { CircularProgress } from "@mui/material";
import NoLayout from "./shared/layouts/NoLayout";

const Home = loadable(() => import("~/features/Home"));
const Login = loadable(() => import("~/features/Login"));
const Centers = loadable(() => import("~/features/Centers"));
const HelperForm = loadable(() => import("~/features/HelperForm"));
const Windy = loadable(() => import("~/features/Windy"));
const DisasterMap = loadable(() => import("~/features/DisasterMap"));

const PageTracker = () => {
    const location = useLocation();
    const [response, setResponse] = useState(null); // Lưu phản hồi từ webhook

    useEffect(() => {
        // Gửi dữ liệu đến webhook khi route thay đổi
        fetch("https://your-n8n-instance/webhook/page-visit", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                page: location.pathname, // Gửi path hiện tại
                timestamp: new Date().toISOString(), // Thời gian
            }),
        })
            .then((res) => res.json())
            .then((data) => {
                setResponse(data); // Lưu phản hồi từ webhook
            })
            .catch((err) => console.error("Error sending to webhook:", err));
    }, [location]);

    useEffect(() => {
        if (response) {
            console.log("Webhook response:", response);
        }
    }, [response]);

    return null;
};

function App() {
    useEffect(() => {
        AOS.init();
        AOS.refresh();
    }, []);

    return (
        <BrowserRouter>
            {/* Component theo dõi route */}
            <PageTracker />
            <Routes>
                <Route element={<AuthLayout />}></Route>
                <Route element={<BasicLayout />}>
                    <Route
                        index
                        element={
                            <Suspense fallback={<CircularProgress />}>
                                <Home title="Trang chủ" />
                            </Suspense>
                        }
                    />
                </Route>
                <Route element={<ContentLayout />}>
                    <Route
                        path="/dang-nhap"
                        element={
                            <Suspense fallback={<CircularProgress />}>
                                <Login title="Đăng nhập" />
                            </Suspense>
                        }
                    />
                    <Route
                        path="/trung-tam-cuu-tro"
                        element={
                            <Suspense fallback={<CircularProgress />}>
                                <Centers title="Trung Tâm Cứu Trợ" />
                            </Suspense>
                        }
                    />
                    <Route
                        path="/dang-ky-cuu-tro"
                        element={
                            <Suspense fallback={<CircularProgress />}>
                                <HelperForm title="Đăng Ký Cứu Trợ" />
                            </Suspense>
                        }
                    />
                    <Route
                        path="/theo-doi-bao"
                        element={
                            <Suspense fallback={<CircularProgress />}>
                                <Windy title="Theo Dõi Bão" />
                            </Suspense>
                        }
                    />
                    <Route
                        path="/ban-do-thien-tai"
                        element={
                            <Suspense fallback={<CircularProgress />}>
                                <DisasterMap title="Bản Đồ Thiên Tai" />
                            </Suspense>
                        }
                    />
                </Route>
                <Route element={<NoLayout />}></Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
