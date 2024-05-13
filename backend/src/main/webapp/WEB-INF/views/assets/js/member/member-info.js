document.getElementById('joinButton').addEventListener('click', function(event) {
    event.preventDefault();
    let id = document.getElementById('id').value;
    let pw = document.getElementById('pw').value;
    let name = document.getElementById('name').value;
    let tel = document.getElementById('tel').value;
    let address = document.getElementById('address').value;
    addMember(id, pw, name, tel, address);
});

function addMember(id, pw, name, tel, address) {
    let val = localStorage.getItem('member-cnt');
    let member_cnt = 0;

    if (val != null) {
        member_cnt = Number(val);
    } 

    for (let i = 1; i <= member_cnt; i++) {
        var info = JSON.parse(localStorage.getItem(i.toString()));
        if (info == null) {
            continue;
        }
        if (info[0] == id) {
            window.alert('존재하는 아이디 입니다.');
            return;
        }
    }
    
    member_cnt = member_cnt + 1;
    localStorage.setItem(member_cnt.toString(), JSON.stringify([id, pw, name, tel, address]));
    localStorage.setItem('member-cnt', member_cnt);
    window.alert('회원가입이 완료되었습니다. 로그인 후 이용해주세요.');

    location.href = '../../../page/member/login.html';
}

function deleteMember() {
    let now_idx = localStorage.getItem('login-status');
    let result = window.confirm("정말 삭제하시겠습니까?");
    if (result) {
        localStorage.removeItem(now_idx.toString());
        logout();
    }
}

function getMember() {
    let idx = localStorage.getItem('login-status');
    let info = JSON.parse(localStorage.getItem(idx));

    document.getElementById('display-id').textContent = info[0];
    document.getElementById('display-pw').textContent = info[1];
    document.getElementById('display-name').textContent = info[2];
    document.getElementById('display-tel').textContent = info[3];
    document.getElementById('display-loc').textContent = info[4];
}

function updateMember(info_idx) {
    let idx = localStorage.getItem('login-status');
    let info = JSON.parse(localStorage.getItem(idx));

    switch (info_idx) {
        case 1:
            let new_pw = window.prompt("수정할 비밀번호를 입력하세요.");
            document.getElementById('display-pw').textContent = new_pw;
            info[1] = new_pw;
            break;
        case 2:
            let new_name = window.prompt("수정할 이름을 입력하세요.");
            document.getElementById('display-name').textContent = new_name;
            info[2] = new_name;
            break;
        case 3:
            let new_tel = window.prompt("수정할 전화번호를 입력하세요.");
            document.getElementById('display-tel').textContent = new_tel;
            info[3] = new_tel;
            break;
        case 4:
            let new_loc = window.prompt("수정할 거주지역을 입력하세요.");
            document.getElementById('display-loc').textContent = new_loc;
            info[4] = new_loc;
            break;
    }
    localStorage.setItem(idx, JSON.stringify(info));
}


function findPassword() {
    let isFind = false;
    let val = localStorage.getItem('member-cnt');
    let member_cnt = 0;

    if (val != null) {
        member_cnt = Number(val);
    } 
    
    let id = window.prompt("당신의 아이디를 입력하세요.");

    for (let i = 1; i <= member_cnt; i++) {
        var info = JSON.parse(localStorage.getItem(i.toString()));
        if (info == null) {
            continue;
        }
        if (info[0] == id) {
            let msg = "당신의 비밀번호는 " + info[1] + "입니다.";
            window.confirm(msg);
            isFind = true;
        }
    }

    if (!isFind) {
        window.confirm("존재하지 않는 유저 정보입니다.")
    }
}