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
