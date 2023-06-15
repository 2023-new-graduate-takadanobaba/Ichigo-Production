// 指定したエレメント(input)が所属する行(tr)を取得
function gyo(obj)
{
    return obj.parentElement.parentElement ;
}

// 指定したエレメント(input)と同じ行にある単価を取得
function tanka(obj)
{
    return gyo(obj).querySelectorAll("input[id^=tanka]")[0].value ;
}

// 指定したエレメント(input)と同じ行にある数量を取得
function suryo(obj)
{
    return gyo(obj).querySelectorAll("input[id^=suryo]")[0].value ;
}

// 指定したエレメント(input)の横計(単価×数量)を再計算してから取得
function yokokei(obj)
{
    result = Number(tanka(obj)) * Number(suryo(obj));
    gyo(obj).querySelectorAll("input[id^=yokokei]")[0].value = result ;
    return result ;
}

// 総合計を再計算
function tatekei()
{
    prices = Array.from(document.querySelectorAll("input[id^=yokokei]")).map(element => Number(yokokei(element))) ;
    result = prices.reduce(function(sum,element){
        return sum + element ;
    });
    return result;
}

// 再計算
function reCalc()
{
    document.getElementById("total").value = tatekei();
    return;
}

//追加機能
// function addForm()
// {
//     var form = "<p></p>";
//     document.querySelector("#formContainer").innerHTML = form;
// }

function addForm() {
    var formContainer = document.getElementById("formContainer");
  
    // 追加するフォームの数
    var numcheck =1;
    var numFormsToAdd = 1;
  
    for (var i = 0; i < numFormsToAdd; i++) {
      // 新しい行（tr要素）を作成
    //   document.createElementで中身のないタグを作る
      var newRow = document.createElement("tr");


      //checkbox列を追加
      var checkCell = document.createElement("th");
      var checkInput = document.createElement("input");
      checkInput.type = "checkbox";
      checkInput.name = "check";
      checkInput.id = "check_"+(i + 2);
      checkCell.appendChild(checkInput);
      newRow.appendChild(checkCell);


  
      // 商品名列を追加
     
      var nameInput = document.createElement("input");
      nameInput.type = "text";
      nameInput.name = "name";
      nameInput.placeholder = "商品名";
      checkCell.appendChild(nameInput);
  
      // 単価列を追加
      var priceCell = document.createElement("td");
      var priceInput = document.createElement("input");
      priceInput.type = "number";
      priceInput.name = "tanka";
      priceInput.id = "tanka_" + (i + 2); // ユニークなIDを設定
      priceInput.onchange = reCalc;
      priceCell.appendChild(priceInput);
      priceCell.appendChild(document.createTextNode("円"));
      newRow.appendChild(priceCell);
  
      // 数量列を追加
      var quantityCell = document.createElement("td");
      var quantityInput = document.createElement("input");
      quantityInput.type = "number";
      quantityInput.name = "suryo";
      quantityInput.value = "1";
      quantityInput.id = "suryo_" + (i + 2); // ユニークなIDを設定
      quantityInput.onchange = reCalc;
      quantityCell.appendChild(quantityInput);
      quantityCell.appendChild(document.createTextNode("個"));
      newRow.appendChild(quantityCell);
  
      // 合計列を追加
      var totalCell = document.createElement("td");
      var totalInput = document.createElement("input");
      totalInput.type = "number";
      totalInput.name = "yokokei";
      totalInput.id = "yokokei_" + (i + 2); // ユニークなIDを設定
      totalCell.appendChild(totalInput);
      totalCell.appendChild(document.createTextNode("円"));
      newRow.appendChild(totalCell);
  
      // 新しい行をフォームコンテナに追加
      formContainer.appendChild(newRow);
    }
  }