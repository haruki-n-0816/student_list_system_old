function check(id,name,mailAddress) {
    var userId = document.getElementById("userId");
    var userName = document.getElementById("userName");
    var userMailAddress = document.getElementById("userMailAddress");

    userId.innerHTML = id;
    userName.innerHTML = name;
    userMailAddress.innerHTML = mailAddress;

    $("#overflow").show();
}
function next() {
    $("#overflow").hide(); // 確認ボックスを消す
}
function cansel() {
    $("#overflow").hide();
    // ok後の処理を書く
}