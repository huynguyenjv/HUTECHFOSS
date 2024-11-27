import { Suspense, useEffect } from "react";
import AOS from "aos";
import "aos/dist/aos.css";
import loadable from "@loadable/component";
import { BrowserRouter, Routes, Route } from "react-router-dom";
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

function App() {
    useEffect(() => {
        AOS.init();
        AOS.refresh();
    }, []);
    return (
        <BrowserRouter>
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
                                <Windy title="Windy" />
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
