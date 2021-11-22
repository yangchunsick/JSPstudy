function fnInsertMember() {
    $('#insert_btn').on('click', function(){
        if($('#no').val().length != 6){
            alert('게시글 번호는 6자리 입니다.');
            return;
        }
        $.ajax({
            url: 'insertMember.do', // 요청
            type: 'post',
            data: $('#f').serialize(), // 폼의 모든 요소를 파라미터로 보냄.
            dataType: 'json', 		// 받아오는 건 json 타입의 데이터
            success: function(obj) {
                alert(obj.result);
                fnSelectMemberList();
            },
            error: function(xhr){ // 응답 텍스트는 xhr 객체에 responseText 프로퍼티로 전달됨.
                if(xhr.status == 1111){	// response.setStatus(1); 코드로 보낸 값을 받음
                    alert(xhr.responseText);					
                }
            }
        });
    });		
}// end