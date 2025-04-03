let myAssets = {};

// 1. 내 자산 수량 fetch
async function fetchAssetsAndUpdateMyAsset() {
    try {
        const response = await fetch("/trade/my_assets");
        if (!response.ok) throw new Error("보유 자산 데이터를 불러오는데 실패했습니다.");
        myAssets = await response.json();

        // 자산 수량을 가져온 후 가격을 fetch하여 총합 계산
        await fetchAssetPricesAndUpdateMyAsset();
    } catch (error) {
        console.error(error.message);
    }
}

// 2. 실시간 가격과 자산 총합 계산
async function fetchAssetPricesAndUpdateMyAsset() {
    const markets = Object.keys(myAssets);
    if (markets.length === 0) return;

    let totalValue = 0;

    if (markets.includes("KRW-KRW")) {
        totalValue += myAssets["KRW-KRW"] * 1;
    }

    const realMarkets = markets.filter(m => m !== "KRW-KRW");
    if (realMarkets.length > 0) {
        const url = `/api/proxy/upbit/ticker?markets=${realMarkets.join(",")}`;
        try {
            const response = await fetch(url);
            if (!response.ok) throw new Error("실시간 가격을 불러오는 데 실패했습니다.");
            const priceData = await response.json();
            priceData.forEach(item => {
                totalValue += (myAssets[item.market] || 0) * item.trade_price;
            });
        } catch (error) {
            console.error("가격 데이터 로딩 실패:", error);
        }
    }

    updateMyAssetUI(totalValue);
}

// 3. DOM에 보유 자산 총합 표시
function updateMyAssetUI(totalValue) {
    const myAssetDiv = document.getElementById("myAsset");
    if (!myAssetDiv) return;

    myAssetDiv.innerHTML = `
        <p><span>${new Intl.NumberFormat('ko-KR').format(totalValue)} 원</span></p>
    `;
}

// 4. 실행 트리거 (다른 스크립트에서 수동 실행 가능)
document.addEventListener("DOMContentLoaded", () => {
    if (document.getElementById("myAsset")) {
        fetchAssetsAndUpdateMyAsset();
    }
});