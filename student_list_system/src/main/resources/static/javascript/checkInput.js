window.onload = function () {

    const reg = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/;

    btnSubmit.disabled = true;
    inputMail.addEventListener('input', function (event) {
        /*入力値チェック*/
        var message = document.getElementById("message");
        
        if (inputMail.value == "") {
            message.innerHTML = "メールアドレスが未入力です。";
            btnSubmit.disabled = true;
        } else if (!reg.test(inputMail.value)) {
            message.innerHTML = "メールアドレスの形式が不正です。";
            btnSubmit.disabled = true;
        } else {
            message.innerHTML = "";
            btnSubmit.disabled = false;
        }
    });
};