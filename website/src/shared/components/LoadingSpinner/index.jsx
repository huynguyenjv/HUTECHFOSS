import { Loader2 } from "lucide-react";

const LoadingSpinner = () => {
    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-100">
            <div className="flex flex-col items-center space-y-4">
                <Loader2 className="w-12 h-12 text-blue-500 animate-spin" strokeWidth={2} />
                <p className="text-lg font-semibold text-gray-700">Đang tải...</p>
            </div>
        </div>
    );
};

export default LoadingSpinner;
