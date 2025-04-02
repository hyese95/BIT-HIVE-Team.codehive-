function init(){
    const rows = document.querySelectorAll("span[data-profit], td[data-profit]");
    rows.forEach(span => {
        const value = parseFloat(span.dataset.profit);
        if (value > 0){
            span.style.color = "red";
        }else if (value < 0) {
            span.style.color = "blue";
        } else {
            span.style.color = "black";
        }
    })
}
init();

