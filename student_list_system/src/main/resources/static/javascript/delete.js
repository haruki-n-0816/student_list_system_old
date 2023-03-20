function check(id,name,mailAddress) {
    // const dialogConf = document.querySelectorAll("overflow .conf");
    // dialogConf.style.cssText = 'background: #FFF; padding: 20px; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);';
    
    let targetElements = document.getElementById("overflow");
    let dialog = '<div class="conf" style="text-align:center"><p>ID:<block id="userId"><block></p><p>名前:<block id="userName"></block></p><p>メールアドレス:<block id="userMailAddress"></block></p><p>こちらのデータを削除します。本当によろしいですか?</p><div class="btns" style="text-align:center"><input type="button" class="btn btn-danger rounded-pill btn-lg" value="削除" th:onClick="next()"><input type="button" class="btn btn-secondary rounded-pill btn-lg" value="キャンセル" onClick="cansel();"></div></div>';

    targetElements.insertAdjacentHTML('afterend',dialog);

    let userId = document.getElementById("userId");
    let userName = document.getElementById("userName");
    let userMailAddress = document.getElementById("userMailAddress");

    userId.innerHTML = id;
    userName.innerHTML = name;
    userMailAddress.innerHTML = mailAddress;

    $("#overflow").show();
}
function next(id) {
    console.log(id);
    let request = new XMLHttpRequest();
    request.open("post","/delet_complete",true,id);
    request.send();
    if (request.readyState === 4 && request.status === 200) {
        if(!request.responseText){
            alert("該当するデータはありませんでした");
            return;
        }
    } else{
        alert("エラー発生 削除失敗");
    }

    $("#overflow").hide(); // 確認ボックスを消す
}
function cansel() {
    $("#overflow").hide();
    // ok後の処理を書く
}