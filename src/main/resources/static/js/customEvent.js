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

$('#commentModal').on('show.bs.modal',function(event) {
    const modal = $(this);
    const id =$(event.relatedTarget).attr('data-id');

    $.ajax({
        "url":'/api/post/'+id,
        "method":'get',

    }).done(function (response){

        modal.find('h6#comment-username').text(response.username);
        modal.find('p#comment-nickname').text(response.nickname);
        modal.find('img#comment-img').attr('src','img/'+response.profile_img);
        modal.find('span.post-thumbs-up').text(response.postLikes);
        modal.find('img.d-block').attr('src','img/'+response.storeFilename);
    })


});

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