import { createContext, useContext, useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import LoadingSpinner from "../shared/components/LoadingSpinner";
import ErrorDisplay from "../shared/components/ErrorDisplay";

// Context để lưu dữ liệu phản hồi
const WebhookResponseContext = createContext(null);

// Custom hook để sử dụng context
export const useWebhookResponse = () => useContext(WebhookResponseContext);

const PageTracker = ({ children }) => {
    const location = useLocation();
    const [response, setResponse] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        // Bỏ qua trang chủ
        if (location.pathname === "/" || location.pathname === "/theo-doi-bao") {
            console.log("Trang tĩnh: Không cần gửi request");
            setResponse({});
            return;
        }

        // Tạo AbortController để quản lý timeout
        const controller = new AbortController();
        const timeoutId = setTimeout(() => {
            controller.abort();
            setIsLoading(false);
            setError(new Error("Request timed out"));
        }, 5000);

        // Đặt trạng thái loading
        setIsLoading(true);
        setError(null);

        // Gửi request
        fetch("https://qaa.app.n8n.cloud/webhook/615527cc-6031-4506-8a60-721830ee3c80", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                page: location.pathname,
                timestamp: new Date().toISOString(),
            }),
            signal: controller.signal,
        })
            .then((res) => {
                clearTimeout(timeoutId);
                return res.json();
            })
            .then((data) => {
                console.log("Response from webhook:", data);
                setResponse(data);
                setIsLoading(false);
            })
            .catch((err) => {
                clearTimeout(timeoutId);
                console.error("Webhook error:", err);
                setIsLoading(false);
                setError(err);
            });

        // Cleanup
        return () => {
            controller.abort();
            clearTimeout(timeoutId);
        };
    }, [location]);

    // Xử lý trạng thái loading và lỗi
    if (isLoading && location.pathname !== "/") {
        return <LoadingSpinner />;
    }

    if (error) {
        return <ErrorDisplay error={error} />;
    }

    return <WebhookResponseContext.Provider value={response}>{children}</WebhookResponseContext.Provider>;
};

export default PageTracker;
