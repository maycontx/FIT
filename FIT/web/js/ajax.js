$(document).on("click", "div[data-id='like']", function(){   
    $.ajax({
        type: "POST",
        url: "LikeController",
        dataType: "json",
        success: function (data) {
            alert(data);
        },
        error: function(){
            alert("WRONG");
        }
    });   
});