function getMonth(){
    var month = new Array();
    for(var i=1; i<13; i++){
        var data = new Object();
        var _month = i
        if(i < 10){
            _month = "0" + i;
        }
        data.id = _month
        data.text = _month
        month.push(data);
    }

    return month;
}

function getYear(){
    var year = new Array();
    for(var i=2020; i<2500; i++){
        var data = new Object();

        data.id = i
        data.text = i
        year.push(data);
    }

    return year;
}

function getMeetingType(){
    const meetingType = new Array();
    meetingType.push(
        {id:'REGULAR', text:'정기 모임'}, {id:'INSTANT', text:'즉석 모임'}
    )

    return meetingType;
}

function getMemberRole(){
    const memberRole = new Array();
    memberRole.push(
        {id:'NONE', text:'비회원'},
        {id:'USER', text:'회원'},
        {id:'NONE', text:'관리자'}
    )

    return memberRole;
}

function bs_input_file() {
    $(".input-file").before(
        function() {
            if ( ! $(this).prev().hasClass('input-ghost') ) {
                var element = $("<input type='file' class='input-ghost' style='visibility:hidden; height:0' multiple data-show-upload='true' data-show-caption='true'>");
                element.attr("name",$(this).attr("name"));
                element.change(function(){
                    element.next(element).find('input').val((element.val()).split('\\').pop());
                });
                $(this).find("button.btn-choose").click(function(){
                    element.click();
                });
                $(this).find("button.btn-reset").click(function(){
                    element.val(null);
                    $(this).parents(".input-file").find('input').val('');
                });
                $(this).find('input').css("cursor","pointer");
                $(this).find('input').mousedown(function() {
                    $(this).parents('.input-file').prev().click();
                    return false;
                });
                return element;
            }
        }
    );
}

