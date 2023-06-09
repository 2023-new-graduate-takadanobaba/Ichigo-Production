function viewChange(){
    if(document.getElementById('sample')){
        id = document.getElementById('sample').value;
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