import Cookies from "js-cookie";
import Footer from "~/shared/components/Footer";
import Header from "~/shared/components/Header";
import { StorageKeys } from "~/shared/constants/keys.js";
import { Navigate, Outlet, useLocation } from "react-router-dom";
import {ToastContainer } from 'react-toastify';
import "react-toastify/dist/ReactToastify.css";

export default function BasicLayout() {
    const location = useLocation();

    return Cookies.get(StorageKeys.ACCESS_TOKEN) &&
        (location.pathname === "/dang-nhap" || location.pathname === "/dang-ky") ? (
        <Navigate to="/" replace />
    ) : (
        <div className="flex flex-col min-h-screen">
            <ToastContainer />
            <Header />
            <Outlet />
            <Footer />
        </div>
    );
}
