//회원가입
$(".signup-btn").click(function (event){
    const data ={
        "email" : $("#name").val(),
        "password" : $("#password").val(),
        "username" : $("#username").val(),
        "nickname" : $("#nickname").val(),
        "birth" : $("#birth").val(),
        "job" : $("#job").val(),
        "gender" : $(':radio[name="gender"]:checked').val()
    }

    $.ajax({
        url:"/user/signup",
        method :"post",
        data: JSON.stringify(data),
        contentType : "application/json"
    }).done(function(response){
        alert("가입 완료");
        location.href ="/";
    }).fail(function (response){
        alert("가입 실패");
    });
});

//게시물 등록
$(".post-create-btn").click(function (event) {
   const form =$(".post-create-form")[0];
   const data = new FormData(form);

    $.ajax({
        url: "/post",
        method: "post",
        enctype: 'multipart/form-data',
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 60000
    }).done(function (response) {
        alert("게시물 등록 완료");
        location.href = "/";
    }).fail(function (response) {
        alert("등록 실패");
    });
});

//게시물 삭제
$(".post-delete-btn").click(function (event) {
    const postId =$(event.target).attr('data-postId');
    const userId =$(event.target).attr('data-userId');
    let map = new Map();
    map.set("postId",postId);
    map.set("userId",userId);

    $.ajax({
        url :"/post/delete",
        method:"delete",
        contentType: "application/json",
        data :JSON.stringify(Object.fromEntries(map))
    }).done(function (response){
        alert("삭제");
    }).fail(function(response){
        alert("권한이 없습니다.")
    })

});
// 게시물 모달시 데이터 전송
$('#commentModal').on('show.bs.modal',function(event) {
    const modal = $(this);
    const postId =$(event.relatedTarget).attr('data-id');


    $.ajax({
        "url":'/api/post/'+postId,
        "method":'get',
    }).done(function (response){

        modal.find('h6#comment-username').text(response.username);
        modal.find('p#comment-nickname').text(response.nickname);
        modal.find('a.create-comment-btn').attr('data-postId',postId);
        modal.find('img#comment-img').attr('src','img/'+response.profile_img);
        modal.find('span#comment-modal-like').text(response.postLikeCount);
        modal.find('img.d-block').attr('src','img/'+response.storeFilename);
    });

    $.ajax({
        url: '/api/comment/' + postId,
        // data :JSON.stringify({
        //     "size" :"5",
        //     "page": "0"
        // }),
        contentType : "application/json"
    }).done(function (result) {
        if(result.length >=1){
            result.forEach(function(item){
                console.log(item.username);
                console.log(item.profile_img);
                let str='<div class="d-flex mb-2">';
                str+='<img src="/img/'+item.profile_img+'" class="img-fluid rounded-circle">';
                str+='<div class="ms-2 small">';
                str+='<div class="bg-light px-3 py-2 rounded-4 mb-1 chat-text">';
                str+='<p class="fw-500 mb-0">'+item.username+'</p>';
                str+='<span class="text-muted">'+item.context+'</span>';
                str+='</div>';
                str+='<div class="d-flex align-items-center ms-2">';
                str+='<a href="#" class="small text-muted text-decoration-none">Like</a>';
                str+='<span class="fs-3 text-muted material-icons mx-1">circle</span>';
                str+='<a href="#" class="small text-muted text-decoration-none">Reply</a>';
                str+='<span class="fs-3 text-muted material-icons mx-1">circle</span>';
                str+='<span class="small text-muted">1h</span>';
                str+='</div></div></div>';
                modal.find('div#commentTable').append(str);
            })
        }
    });


});

// 댓글 작성

$(".create-comment-btn").click(function(event){
   const text =$(this).parent().children('input').val();
   console.log(text);
   const postId =$(this).attr('data-postId');
   let data ={
       "context": text,
       "postId" : postId
   }
   $.ajax({
       url:"/api/comment",
       method :"post",
       data: JSON.stringify(data),
       contentType : "application/json"
   }).done(function (response){
       let str='<div class="d-flex mb-2">';
       str+='<img src="/img/'+response.profile_img+'" class="img-fluid rounded-circle">';
       str+='<div class="ms-2 small">';
       str+='<div class="bg-light px-3 py-2 rounded-4 mb-1 chat-text">';
       str+='<p class="fw-500 mb-0">'+response.username+'</p>';
       str+='<span class="text-muted">'+response.context+'</span>';
       str+='</div>';
       str+='<div class="d-flex align-items-center ms-2">';
       str+='<a href="#" class="small text-muted text-decoration-none">Like</a>';
       str+='<span class="fs-3 text-muted material-icons mx-1">circle</span>';
       str+='<a href="#" class="small text-muted text-decoration-none">Reply</a>';
       str+='<span class="fs-3 text-muted material-icons mx-1">circle</span>';
       str+='<span class="small text-muted">1h</span>';
       str+='</div></div></div>';
       $('#commentTable').append(str);
   }).fail(function(response){
       alert("로그인을 해주세요");
       location.href="/login";
   })

});

// 피드 좋아요 버튼
$(".post_like_btn").click(function (event){
    const postId =$(this).attr('data-id');
    const obj = $(this);
    let ico =obj.children().eq(0);
    let like = obj.children().eq(1);
    $.ajax({
        "url" :"/api/postLike/"+postId,
        "method":"post"
    }).done(function (response){
        console.log(response);
        if(response == true){
            ico.css('color','blue');
            like.css('color','blue');
            like.text(parseInt(like.text())+1);
        }else{
            ico.css('color','#6c757d');
            like.css('color','#6c757d');
            like.text(parseInt(like.text()) ==0 ? 0 : parseInt(like.text())-1);
        }
    }).fail(function(response){
        alert("로그인이 필요합니다");
        location.href="/login";
    })
});

//스크랩
$(".scrap_btn").click(function (event){
    const postId =$(this).attr('data-id');
    const obj = $(this);
    // const isClicked = $(this).attr('data-clicked');

    let ico =obj.children().eq(0);
    let like = obj.children().eq(1);


    $.ajax({
        "url" :"/scrap/"+postId,
        "method":"post"
    }).done(function (response){
        console.log(response);
        if(response === true){
            ico.css('color','blue');
            like.css('color','blue');
            like.text(parseInt(like.text())+1);
        }else{
            ico.css('color','#6c757d');
            like.css('color','#6c757d');
            like.text(parseInt(like.text()) ==0 ? 0 : parseInt(like.text())-1);
        }
    }).fail(function(response){
        alert("로그인이 필요합니다");
        location.href="/login";
    })
});

//메인페이지 팔로우
$(".main_follow_btn").click(function(event){
    const followId = $(this).attr('data-followId');

    $.ajax({
        "url" : "/follow/"+followId,
        "method":"post"
    }).done(function(response){

    }).fail(function(response){
        alert("권한이 없습니다.");
        location.href="/login";

    })
})

//프로필 팔로우
$(".profile_follow_btn").click(function(event){
    const userId = $(this).attr('data-userId');
    $.ajax({
        "url" : "/follow/"+userId,
        "method":"post"
    }).done(function(response){

    }).fail(function(response){
        alert("권한이 없습니다.");
        event.preventDefault();
    })
})
let isRequestPost = false;
window.addEventListener('scroll',() => {
    // $("#mainPost").height() : DOM의 길이
    // $("#mainPost").offset().top : DOM의 최상단 위치
    //window.pageYOffset : 현제 스크롤 최상단 위치
    //window.innerHeight : 창 길이
    const domPositionY = $("#mainPost").height() + $("#mainPost").offset().top;
    const windowPositionY = window.pageYOffset + window.innerHeight;
    if(domPositionY <= windowPositionY && !this.isRequestPost) {
        this.isRequestPost = true;
        setTimeout(() => this.isRequestPost = false, 1000);
    }
});
