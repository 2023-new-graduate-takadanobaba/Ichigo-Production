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

function calculateSum() {
    var table = document.getElementById("myTable");  // テーブルの要素を取得
    var rows = table.getElementsByTagName("tr");  // 行の要素を取得
    var sum = 0;  // 合計の初期値

    // 各行の4列目の<input>要素からvalue属性の値を取得し、合計を計算する
    for (var i = 0; i < rows.length; i++) {
      var cells = rows[i].getElementsByTagName("td");  // 列の要素を取得

      if (cells.length >= 4) {
        var inputElement = cells[3].querySelector("input");  // 4列目の<input>要素を取得
        if (inputElement) {
          var value = parseFloat(inputElement.value);  // input要素のvalue属性の値を数値に変換
          if (!isNaN(value)) {
            sum += value;  // 数値の場合は合計に加算
          }
        }
      }
    }
  
        document.getElementById("result").value = sum;  // 結果を表示

        // 指定したエレメント(input)が所属する行(tr)を取得
      function gyo(obj) {
          return obj.parentElement.parentElement;
      }

        function tanka(obj) {
          return gyo(obj).querySelectorAll("input[id^=tanka]")[0].value;
      }
      
      // 指定したエレメント(input)と同じ行にある数量を取得
      function suryo(obj) {
          return gyo(obj).querySelectorAll("input[id^=suryo]")[0].value;
      }
      
      // 指定したエレメント(input)の横計(単価×数量)を再計算してから取得
      function yokokei(obj) {
          result = Number(tanka(obj)) * Number(suryo(obj));
          gyo(obj).querySelectorAll("input[id^=yokokei]")[0].value = result;
          return result;
      }
        
      }