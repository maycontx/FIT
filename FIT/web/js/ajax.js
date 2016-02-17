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
        
        var reply = $(this).attr("addr");
        var post = $(this).attr("post");
        var comment = $(this).val();
        
        $.ajax({
            type: "GET",           
            data: {
                "reply": reply,
                "post": post,
                "comment": comment
            },
            url: "CommentController",
            success: function (data) {
                alert("fds");
            },
            error: function () {
                alert("WRONG");
            }
        });        
    }
});
    
