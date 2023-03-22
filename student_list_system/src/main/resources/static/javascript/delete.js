function check(id, name, mailAddress) {
    const overflowTargetElements = document.getElementById("overflow");
    const overflow = `
        <div id="conf" style="text-align:center; z-index: 10;">
            <p>ID:<block id="userId"></block></p>
            <p>名前:<block id="userName"></block></p>
            <p>メールアドレス:<block id="userMailAddress"></block></p>
            <p>こちらのデータを削除します。本当によろしいですか?</p>
            <div class="btns" style="text-align:center">
                <input type="button" class="btn btn-danger rounded-pill btn-lg" value="削除" onclick="next(${id});">
                <input type="button" class="btn btn-secondary rounded-pill btn-lg" value="キャンセル" onclick="cancel();">
            </div>
        </div>`;

    overflowTargetElements.insertAdjacentHTML('afterbegin', overflow);
    overflowTargetElements.style.zIndex = "10";

    const overflowCss = document.querySelectorAll("#overflow");
    overflowCss.forEach(el => {
        Object.assign(el.style, {
            width: "100vw",
            height: "100vh",
            backgroundColor: "rgba(0, 0, 0, 0.2)",
            top: "0%",
            left: "0%",
            position: "absolute",
            display: "none",
        });
    });

    const confCss = document.getElementById("conf");
    Object.assign(confCss.style, {
        zIndex: "10",
        background: "#FFF",
        padding: "20px",
        position: "absolute",
        top: "50%",
        left: "50%",
        transform: "translate(-50%, -50%)",
    });

    const userId = document.getElementById("userId");
    const userName = document.getElementById("userName");
    const userMailAddress = document.getElementById("userMailAddress");

    userId.textContent = id;
    userName.textContent = name;
    userMailAddress.textContent = mailAddress;

    $("#overflow").show();
}

function next() {
    let id = document.getElementById("userId").innerText;
    let request = new XMLHttpRequest();
    request.open("post", "/delete_complete", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function () {
        if(request.readyState === 1){
            console.log("openメソッドが呼ばれました");
        } else if(request.readyState === 2){
            console.log("sendメソッドが呼ばれました");
        } else if(request.readyState === 3){
            console.log("レスポンスの受信中");
        }
        else if (request.readyState === 4 && request.status === 200) {
            if (!request.responseText) {
                alert("該当するデータはありませんでした");
                return;
            }
            location.reload(true);
            $("#conf").remove();
            $("#overflow").hide();
        }
    };
    request.send("id=" + encodeURIComponent(id));
}

function cancel() {
    $("#conf").remove();
    $("#overflow").hide();
}