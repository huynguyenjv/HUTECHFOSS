import { useEffect } from "react";

const Chatbot = () => {
    useEffect(() => {
        const script = document.createElement("script");
        script.src = "https://cdn.botpress.cloud/webchat/v1/inject.js";
        script.async = true;
        document.body.appendChild(script);
        script.onload = () => {
            window.botpressWebChat.init({
                botId: "<botID>",
                hostUrl: "https://cdn.botpress.cloud/webchat/v1",
                messagingUrl: "https://messaging.botpress.cloud",
                clientId: "<clientID>",
            });
        };

        // Style the chatbot container to be full screen
        const chatContainer = document.getElementById("webchat");
        chatContainer.style.position = "fixed";
        chatContainer.style.top = "0";
        chatContainer.style.left = "0";
        chatContainer.style.width = "100%";
        chatContainer.style.height = "100%";
        chatContainer.style.zIndex = "9999"; // Make sure it's on top
        chatContainer.style.backgroundColor = "transparent"; // Optional: transparent background

        return () => {
            // Cleanup when the component is unmounted
            if (script) {
                document.body.removeChild(script);
            }
        };
    }, []);

    return <div id="webchat" />;
};

export default Chatbot;
