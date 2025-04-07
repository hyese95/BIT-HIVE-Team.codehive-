function showToast(message) {
    const container = document.getElementById("toastContainer");
    const toast = document.createElement("div");

    toast.setAttribute("style",
        "background-color: #333;" +
        "color: white;" +
        "padding: 12px 20px;" +
        "border-radius: 6px;" +
        "opacity: 0.95;" +
        "font-size: 14px;" +
        "position: fixed;" +
        "top: 30%;" +
        "left: 50%;" +
        "transform: translateX(-50%);" +
        "z-index: 9999;" +
        "transition: opacity 0.3s ease;"
    );

    toast.textContent = message;
    container.appendChild(toast);

    setTimeout(() => {
        toast.style.opacity = 0;
        setTimeout(() => toast.remove(), 300); // remove after fade out
    }, 2500);
}