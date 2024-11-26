import { Suspense, useEffect } from "react";
import AOS from "aos";
import "aos/dist/aos.css";
import loadable from "@loadable/component";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AuthLayout from "./shared/layouts/AuthLayout";
import BasicLayout from "./shared/layouts/BasicLayout";
import ContentLayout from "./shared/layouts/ContentLayout";
import { CircularProgress } from "@mui/material";

const Home = loadable(() => import("~/features/Home"));
const Login = loadable(() => import("~/features/Login"));
const Centers = loadable(() => import("~/features/Centers"));

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
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
