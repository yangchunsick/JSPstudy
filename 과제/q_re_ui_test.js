//건드리지 말 것.
 $(document).ready(function(){
    $('#id').on('blur', function(){ fnIdCheck(); });
    $('#pwd').on('blur', function(){ fnPwdCheck(); fnRePwdCheck(); });
    $('#re_pwd').on('blur', function(){ fnPwdCheck(); fnRePwdCheck(); });
    $('#name').on('blur', function(){ fnNameCheck(); });
    fnSubmit();
  });

//$(document).ready(function(){
//    fnIdCheck();
//    fnPwdCheck();
//    fnRePwdCheck();
//    fnNameCheck();
//})

// 입력 점검 전역 변수
// true이면 정규식 통과, false이면 정규식 통과 못함
let idPass = false;   /* 아이디 점검 */
let pwPass = false;   /* 비밀번호 점검 */
let pwRePass = false; /* 비밀번호 재입력 점검 */
let namePass = false; /* 이름 점검 */

function fnIdCheck(){
    let regID = /^[a-z0-9][a-z0-9_-]{4,19}$/;
    let id = $('#id');
    let id_check = $('#id_check');

    // 1) 아이디 입력 점검 코드 삽입
    $(id).on('keyup blur', function(){
        if(regID.test($(this).val())){
            $(id_check).text('멋진 아이디네요 !');
            $(id_check).addClass('check_pass');
            $(id_check).removeClass('check_fail');
            idPass = true;
        }else if($(id).val() == ''){
            $(id_check).text('필수 정보입니다.');
            $(id_check).addClass('check_fail');
            $(id_check).removeClass('check_pass');
        }else{
            $(id_check).text('5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.');
            $(id_check).addClass('check_fail');
            $(id_check).removeClass('check_pass');
            
        }
        return idPass;


    });

}


function fnPwdCheck(){
    let regPWD = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9!@#$%^&*]{3,15}$/;
    let pwd = $('#pwd');
    let pwd_check = $('#pwd_check');

    // 2) 비밀번호 입력 점검 코드 삽입
    $(pwd).on('keyup blur', function(){
        if(regPWD.test($(this).val())){
            $(pwd_check).text('통과');
            $(pwd_check).addClass('true');
            $(pwd_check).removeClass('false');
            pwPass = true;
        
        }else if($(pwd).val() == ''){
            $(pwd_check).text('탈락');
            $(pwd_check).addClass('false');
            $(pwd_check).removeClass('true');
           
        }else{
            $(pwd_check).text('탈락');
            $(pwd_check).addClass('false');
            $(pwd_check).removeClass('true');
        }

        return pwPass;

    });

}

function fnRePwdCheck(){
    let pwd = $('#pwd');
    let re_pwd = $('#re_pwd');
    let re_pwd_check = $('#re_pwd_check');
    // 3) 비밀번호 재입력 점검 코드 삽입
    $(re_pwd).on('keyup blur', function(){
        if($(pwd).val() == $(re_pwd).val()){
            $(re_pwd_chekc).text('통과');
            $(re_pwd_check).addClass('check_pass');
            $(re_pwd_check).removeClass('check_fail');
            pwRePass = true;
        } else if($(re_pwd) == ''){
            $(re_pwd_check).text('필수 정보입니다.');
            $(re_pwd_check).addClass('check_fail');
            $(re_pwd_check).removeClass('check_pass');
            
        }else{            
            $(re_pwd_check).text('비밀번호가 일치하지 않습니다.');
            $(re_pwd_check).addClass('check_fail');
            $(re_pwd_check).removeClass('check_pass');
            
        }
        return pwRePass;



       

    });
    
}

function fnNameCheck(){
    let regName = /^[가-힣a-zA-Z]+$/;
    let name = $('#name');
    let name_check = $('#name_check');
    // 4) 이름 입력 점검 코드 삽입
    $(name).on('keyup blur', function(){
        if(regName.test($(this).val())) {
            $(name_check).text('');
            $(name_check).removeClass('check_fail');
            namePass = true;
        } else if($(name).val() == '') {
            $(name_check).text('필수 정보입니다.');
            $(name_check).addClass('check_fail');
            $(name_check).removeClass('check_pass');

        } else{
            $(name_check).text('한글과 영문 대 소문자를 사용하세요.(특수기호, 공백 사용 불가)');
            $(name_check).addClass('check_fail');
            $(name_check).removeClass('check_pass');
        }
        return namePass;
    });

}

function fnSubmit(){
    // 5) 서브밋 점검 코드 삽입
    // Hint) 입력 점검 전역 변수가 모두 true이면 서브밋을 실행
    $('#join_form').on('submit', function(event){
        if(confirm('가입할까요?') == false){
            event.preventDefault();
            return false;
        } 
        if (idPass ==false||pwPass == false||pwRePass == false||namePass == false){
            event.preventDefault();
            return false;
        } else {
            return true;
        } 
        
    });

}