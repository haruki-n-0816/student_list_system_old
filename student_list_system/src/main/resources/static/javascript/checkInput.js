// 引数で指定されたパスでサブミットする
function formSubmit(path){
    if(!path){
        alert('パスを指定してください');
        return;
    }
    let form = document.getElementsByTagName('form')[0];
    if(!form){
        alert('フォームが取得できませんでした');
        return;
    }
    form.action=path;
    form.method="post";
    form.submit();
}
// 入力画面の入力値のチェックを行った後で、
// 引数で指定されたパスでサブミットする
function checkInputAndSubmit(path){
    if(!checkInput()){
        return;
    }
    formSubmit(path);
}
// 入力画面の入力値のチェックを行う
function checkInput(){
    //名前の未入力チェック
    let nameElem = document.getElementById('name');
    if(nameElem.value === ''){
        alert('メールアドレスを入力してください。');
        nameElem.focus();
        return false;
    }
    //名前の全角入力チェック
    if(!nameElem.value.match(/[^\x01-\x7E]/)){
        alert('メールアドレスは半角で入力してください。')
        nameElem.focus();
        return false;
    }
}