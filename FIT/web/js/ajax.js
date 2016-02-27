$(document).on("click", "div[data-id='like']", function (e) {
    e.preventDefault();
    e.stopPropagation();
    var post = $(this).parent().parent(".post").attr("data-info");
    $.ajax({
        type: "GET",
        dataType: "xml",
        data: {
            "post": post
        },
        url: "LikeController",
        success: function (data) {
            $(data).find('result').each(function () {
                var status = $(this).find('status').text();
                var postLikes = $(this).find('postLikes').text();
                
                if (status == "add"){
                    $(".post[data-info='" + post + "']").children().children("div[data-id='like']").addClass("liked");            
                }
                else
                    $(".post[data-info='" + post + "']").children().children("div[data-id='like']").removeClass("liked");
                
                $(".post[data-info='" + post + "']").children(".post-data").children("div:first-child").children("span:last-child").text(postLikes);
                
            });

        },
        error: function () {
            alert("WRONG");
        }
    });
});

$(document).on("keypress", "textarea[name='comment-text']", function (e) {
    var keycode = (event.keyCode ? event.keyCode : event.which);
    if( keycode == "13" ){
        
        var comment = $(this).val();
        
        if (comment.trim("") != ""){
        
            var reply = $(this).attr("addr");
            var post = $(this).attr("post");
            var edit = $(this).attr("_e");


            $.ajax({
                type: "GET",
                dataType: "xml",
                data: {
                    "reply": reply,
                    "post": post,
                    "comment": comment,
                    "edit": edit
                },
                url: "CommentController",
                success: function (data) {
                    $(data).find('result').each(function () {
                        image = $(this).find('name').text();
                        name = $(this).find('name').text();
                        text = $(this).find('comment').text();
                        id = $(this).find('id').text();
                        status = $(this).find('status').text();
                    });
                    
                    commentObj = {
                        AuthorName: name,
                        //AuthorImage: image,
                        text: text,
                        id: id,
                        post: post,
                        reply: reply
                    }
                    
                    if ( status == "new" )                        
                        object.comment(commentObj);
                    else if ( status == "edit" ){ 
                        var commentBody = $("div[data-info='comment-"+commentObj.id+"']").children(".comment-body");
                        commentBody.text(comment);
                        
                        var textarea = commentBody.parent().parent().children("textarea");                        
                        textarea.val("");
                        textarea.attr("rows", 1);
                        
                    }
                        
                    
                    

                    
                },
                error: function () {
                    alert("WRONG");
                }
            });        
        }
    }
});
    
