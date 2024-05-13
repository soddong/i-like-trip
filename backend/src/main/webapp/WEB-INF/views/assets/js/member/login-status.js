document.getElementById('loginButton').addEventListener('click', function(event) {
    event.preventDefault();
    var id = document.getElementById('id').value;
    var pw = document.getElementById('pw').value;
    login(id, pw);
});

function login(id, pw) {
    let val = localStorage.getItem('member-cnt');
    let member_cnt = 0;

    if (val != null) {
        member_cnt = Number(val);
    } 

    let isFinded = false;
    for (let i = 1; i <= member_cnt; i++) {
        var info = JSON.parse(localStorage.getItem(i.toString()));
        if (info == null) {
            continue;
        }
        if (info[0] == id && info[1] == pw) {
            toggleLoginStatus(i, info);
            isFinded = true;
            break;
        } else if (info[0] == id && info[1] != pw) {
            window.alert("비밀번호가 일치하지 않습니다.");
            isFinded = true;
            return;
        }
    }

    if (!isFinded) {
        window.alert("존재하지 않는 아이디 입니다.");
    } else {
        window.alert("로그인 하였습니다.");
    }
}

function logout() {
    toggleLoginStatus(-1, null);
    window.alert("로그아웃 하였습니다.");
    location.href = '../../index.html';
}

function toggleLoginStatus(loginStatus, info) {
    localStorage.setItem('login-status', loginStatus.toString());
    localStorage.setItem('login-info', info);

    updateHeaderDisplay(loginStatus);
    location.href = '../../index.html';
}

function updateHeaderDisplay(loginStatus) {
    let visitorMenu = document.getElementById(`visitor-menu`);
    let memberMenu = document.getElementById(`member-menu`);

    if (loginStatus) {
        visitorMenu.style.display = "none";
        memberMenu.style.display = "block";
    }
    else {
        visitorMenu.style.display = "block";
        memberMenu.style.display = "none";
    }
}