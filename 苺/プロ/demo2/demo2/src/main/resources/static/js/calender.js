//const定数...値の書き換えを禁止した状態の変数を宣言できるconstというものです。
const week = ["日", "月", "火", "水", "木", "金", "土"];
//Date constructor...引数が与えられなかった場合、新しく生成された Date オブジェクトはインスタンス化された時点の現在の日付と時刻を表します。
const today = new Date();
// 月末だとずれる可能性があるため、1日固定で取得
//%%    var...変数へ代入される値によって自動的に変数の型を判別してくれる機能
//%%	var...をつけないとグローバル変数になってしまう。付けたらローカル変数になってくれる
//%%    var...は巻き上げに注意
var showDate = new Date(today.getFullYear(), today.getMonth(), 1);

// 祝日取得                                                                                                                                                                                                                             
var request;
window.onload = function () {
    request = new XMLHttpRequest();
    request.open('get', 'syukujitsu.csv', true);
    request.send(null);
    request.onload = function () {
        // 初期表示
        showProcess(today, calendar);
    };
};

// 前の月表示
// function キーワードを使用して、prev という名前の関数を定義している
// function(関数)とは、様々な処理を1つにまとめて、名前をつけることができるもの
// prev関数とnext関数は、前の月と次の月のカレンダーを表示するためのもの
// showDateの月を変更し、showProcess関数を呼び出してカレンダーを更新している

function prev(){
    showDate.setMonth(showDate.getMonth() - 1);
    showProcess(showDate);
}
// カレンダーの表示対象の月を1つ前の月に変更し、
// その変更後の日付を引数として showProcess 関数を呼び出してカレンダーを表示
// 次の月表示
function next(){
    showDate.setMonth(showDate.getMonth() + 1);
    showProcess(showDate);
}

// カレンダー表示
function showProcess(date) {
    var year = date.getFullYear();
    var month = date.getMonth(); // 0始まり
    document.querySelector('#header').innerHTML = year + "年 " + (month + 1) + "月";

    var calendar = createProcess(year, month);
    document.querySelector('#calendar').innerHTML = calendar;
}

// カレンダー作成
// document オブジェクトは、Web ページ上の要素にアクセスするためのグローバルオブジェクトであり、
// ブラウザによって自動的に提供される
// 要素を選択する際に document オブジェクトを生成する必要はない
function createProcess(year, month) {
    // 曜日
    var calendar = "<table><tr class='dayOfWeek'>";
    for (var i = 0; i < week.length; i++) {
        calendar += "<th>" + week[i] + "</th>";
    }
    calendar += "</tr>";

    var count = 0;
    var startDayOfWeek = new Date(year, month, 1).getDay();
    var endDate = new Date(year, month + 1, 0).getDate();
    var lastMonthEndDate = new Date(year, month, 0).getDate();
    var row = Math.ceil((startDayOfWeek + endDate) / week.length);

    // 1行ずつ設定
    for (var i = 0; i < row; i++) {
        calendar += "<tr>";
        // 1colum単位で設定
        for (var j = 0; j < week.length; j++) {
            if (i == 0 && j < startDayOfWeek) {
                // 1行目で1日まで先月の日付を設定
                calendar += "<td class='disabled'>" + (lastMonthEndDate - startDayOfWeek + j + 1) + "</td>";
            } else if (count >= endDate) {
                // 最終行で最終日以降、翌月の日付を設定
                count++;
                calendar += "<td class='disabled'>" + (count - endDate) + "</td>";
            } else {
                // 当月の日付を曜日に照らし合わせて設定
                count++;
                var dateInfo = checkDate(year, month, count);
                if(dateInfo.isToday){
                    calendar += "<td class='today'><a href=\"form/"+showDate.getFullYear()+"_"+(showDate.getMonth()+1)+"_"+count+"\">" + count + "</a></td>";
                } else if(dateInfo.isHoliday) {
                    calendar += "<td class='holiday' title='" + dateInfo.holidayName + "'>" + count + "</td>";
                } else {
                    calendar += "<td><a href=\"form/"+showDate.getFullYear()+"_"+(showDate.getMonth()+1)+"_"+count+"\">" + count + "</a></td>";
                }
            }
        }
        calendar += "</tr>";
    }
    return calendar;
}

// 日付チェック
function checkDate(year, month, day) {
    if(isToday(year, month, day)){
        return {
            isToday: true,
            isHoliday: false,
            holidayName: ""
        };
    }

    var checkHoliday = isHoliday(year, month, day);
    return {
        isToday: false,
        isHoliday: checkHoliday[0],
        holidayName: checkHoliday[1],
    };
}

// 当日かどうか
function isToday(year, month, day) {
    return (year == today.getFullYear()
        && month == (today.getMonth())
        && day == today.getDate());
    }

// 祝日かどうか
function isHoliday(year, month, day) {
    var checkDate = year + '/' + (month + 1) + '/' + day;
    var dateList = request.responseText.split('\n');
    // 1行目はヘッダーのため、初期値1で開始
    for (var i = 1; i < dateList.length; i++) {
        if (dateList[i].split(',')[0] === checkDate) {
            return [true, dateList[i].split(',')[1]];
        }
    }
    return [false, ""];
}