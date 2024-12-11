import React from "react";
import { AlertTriangle } from "lucide-react";

const ErrorDisplay = ({ error }) => {
    return (
        <div className="flex items-center justify-center min-h-screen bg-red-50">
            <div className="max-w-md p-6 text-center bg-white rounded-lg shadow-lg">
                <div className="flex justify-center mb-4">
                    <AlertTriangle className="w-16 h-16 text-red-500" strokeWidth={1.5} />
                </div>
                <h2 className="mb-3 text-xl font-bold text-red-600">Đã xảy ra lỗi</h2>
                <p className="mb-4 text-gray-700">{error.message || "Có lỗi không xác định đã xảy ra."}</p>
                <button
                    onClick={() => window.location.reload()}
                    className="px-4 py-2 text-white transition-colors bg-red-500 rounded-md hover:bg-red-600"
                >
                    Thử lại
                </button>
            </div>
        </div>
    );
};

export default ErrorDisplay;
