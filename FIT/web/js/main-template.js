var comment = {
    show: function(post, btn){
        btn.addClass("liked");
        $("div[data-info='"+post+"']").css("border-bottom", "1px solid #E7E7E7");
        $("div[data-info='post-"+post+"']").show();
        $("div[data-info='post-"+post+"'] textarea").focus();
        $("div[data-info='"+post+"']").attr("comment", "true");
    },
    hide: function(post, btn){
        btn.removeClass("liked");
        $("div[data-info='"+post+"']").css("border-bottom", "1px solid #ACCAB9");
        $("div[data-info='post-"+post+"']").hide();        
        $("div[data-info='"+post+"']").attr("comment", "false");
    }
};

$(document).ready(function(){
    
    // GATILHO PARA MOSTRA O CAMPO DE COMENTÁRIO
    $("div[data-id='comment']").click(function(){
        var post = $(this).parent().parent(".post").attr("data-info");
        var status = $(this).parent().parent(".post").attr("comment");
        if ( status == "false" ){
            comment.show(post, $(this));
        }else{
            comment.hide(post, $(this));
        }
        
    });
    
});