$(document).on("click", "div[data-id='like']", function(){   
    var post = $(this).attr("data-info");
    $.ajax({
        type: "GET",
        data: {
            "post": post
        },
        url: "LikeController",        
        success: function (data) {
            alert(data);
        },
        error: function(){
            alert("WRONG");
        }
    });   
});