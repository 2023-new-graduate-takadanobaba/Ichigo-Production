// 指定したエレメント(input)が所属する行(tr)を取得
function gyo(obj) {
    return obj.parentElement.parentElement;
}

// 指定したエレメント(input)と同じ行にある単価を取得
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

// 総合計を再計算
function tatekei() {
    prices = Array.from(document.querySelectorAll("input[id^=yokokei]")).map(element => Number(yokokei(element)));
    result = prices.reduce(function (sum, element) {
        return sum + element;
    });
    return result;
}

// 再計算
function reCalc() {
    document.getElementById("total").value = 0; // 合計値を0にリセット

    // 再計算
    document.getElementById("total").value = tatekei();
}


function addForm() {
    var formContainer = document.getElementById("formContainer");

    // 追加するフォームの数
    var numcheck = 1;
    var numFormsToAdd = 1;

    for (var i = 0; i < numFormsToAdd; i++) {
        // 新しい行（tr要素）を作成
        //   document.createElementで中身のないタグを作る
        var newRow = document.createElement("tr");


        //checkbox列を追加
        var checkCell = document.createElement("td");
        var checkInput = document.createElement("input");
        checkInput.type = "checkbox";
        checkInput.onclick = function () {
            changeCheckParameter(this);
        };
        checkCell.appendChild(checkInput);
        newRow.appendChild(checkCell);


        //KaimonoListのDBへ登録するための値
        var hiddenCell = document.createElement("input");
        hiddenCell.type = "hidden";
        hiddenCell.name = "check";
        hiddenCell.value = "0";
        hiddenCell.id = "check";
        checkCell.appendChild(hiddenCell);
        newRow.appendChild(checkCell);

        // 商品名列を追加
        var nameCell = document.createElement("td");
        var nameInput = document.createElement("input");
        nameInput.type = "text";
        nameInput.name = "goodsname";
        nameInput.class="form-control";
        nameInput.placeholder = "商品名";
        nameInput.className="form-control";
        nameCell.appendChild(nameInput);

        //id列を追加
        var idInput = document.createElement("input");
        idInput.type = "hidden";
        idInput.name = "id";
        idInput.value = "0";
        nameCell.appendChild(idInput);
        newRow.appendChild(nameCell);

        // 単価列を追加
        var priceCell = document.createElement("td");
        var priceInput = document.createElement("input");
        priceInput.type = "number";
        priceInput.name = "price";
        priceInput.id = "tanka_" + (i + 2); // ユニークなIDを設定
        priceInput.value = "0";
        priceInput.className="form-control";
        priceInput.onchange = reCalc;
        priceCell.appendChild(priceInput);
        priceCell.appendChild(document.createTextNode(""));
        newRow.appendChild(priceCell);

        // 数量列を追加
        var quantityCell = document.createElement("td");
        var quantityInput = document.createElement("input");
        quantityInput.type = "number";
        quantityInput.name = "amount";
        quantityInput.value = "1";
        quantityInput.id = "suryo_" + (i + 2); // ユニークなIDを設定
        quantityInput.className="form-control";
        quantityInput.onchange = reCalc;
        quantityCell.appendChild(quantityInput);
        quantityCell.appendChild(document.createTextNode(""));
        newRow.appendChild(quantityCell);

        // 合計列を追加
        var totalCell = document.createElement("td");
        var totalInput = document.createElement("input");
        totalInput.type = "number";
        totalInput.name = "yokokei";
        totalInput.value = "0";
        totalInput.id = "yokokei_" + (i + 2); // ユニークなIDを設定
        totalInput.className="form-control";
        totalCell.appendChild(totalInput);
        totalCell.appendChild(document.createTextNode(""));
        newRow.appendChild(totalCell);

        //削除列を追加
        var deleteCell = document.createElement("td");
        var deleteButton = document.createElement("button");
        deleteButton.type = "button";
        deleteButton.id = "button_" + (i + 2);
        deleteButton.className="btn btn-outline-danger";
        deleteButton.textContent = "削除";
        deleteButton.onclick = function () {
            deleteForm(this);
            // this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);
        };
        deleteCell.appendChild(deleteButton);
        newRow.appendChild(deleteCell);



        // 新しい行をフォームコンテナに追加
        formContainer.appendChild(newRow);
    }


}

// 削除ボタンをクリックした時の処理
function deleteForm(button) {
    if (registerTable.rows.length == 3) {
        alert("これ以上削除できません")
        return false;
    }
    // ボタンの親要素である<td>要素を取得
    // 行(<tr>)要素を取得
    var row = button.parentNode.parentNode;
    // 行を削除
    row.parentNode.removeChild(row);

    // 再計算
    reCalc();


}


function changeCheckParameter(check) {
    var cell = check.parentElement;
    var checkParameter = cell.querySelector("input[id=check]").value;
    if (checkParameter == 0) {
        cell.querySelector("input[id^=check]").value = "1"
    } else {
        cell.querySelector("input[id^=check]").value = "0"
    }
}

const deleteItem = (id) => {
    if (registerTable.rows.length == 3) {
        alert("これ以上削除できません")
        return false;
    }
    const xhr = new XMLHttpRequest();
    xhr.open('GET', '/ichigo/deleteItem/' + id.value, true);
    xhr.send();
    deleteForm(id);
}