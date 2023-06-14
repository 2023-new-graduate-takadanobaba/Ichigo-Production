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
function addForm()
{
    var form = "テスト";
    document.querySelectorAll("#formContainer").innerHTML = form;
}