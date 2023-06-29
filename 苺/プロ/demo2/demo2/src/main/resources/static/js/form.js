function viewChange(){
    if(document.getElementById('overall-category')){
        id = document.getElementById('overall-category').value;
        if(id == 'food'){
            document.getElementById('foodBox').style.display = "";
            document.getElementById('useBox').style.display = "none";
            document.getElementById('travelBox').style.display = "none";
        }else if(id == 'dailyuse'){
            document.getElementById('foodBox').style.display = "none";
            document.getElementById('useBox').style.display = "";
            document.getElementById('travelBox').style.display = "none";
        }
        else if(id == 'travelcost'){
            document.getElementById('foodBox').style.display = "none";
            document.getElementById('useBox').style.display = "none";
            document.getElementById('travelBox').style.display = "";
        }
    }
    window.onload = viewChange;
}
    function total() {
        prices = Array.from(document.querySelectorAll("td[id^=total]"));
        result = prices.reduce(function (sum, element) {
            return sum + element;
        });
        document.getElementById("aaaaaa").value = result; // 合計値を0にリセット
    }