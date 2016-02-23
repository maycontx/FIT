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
    },
    showReply: function(comment, btn){
        $("div[data-info='reply-" + comment + "']").show();
        btn.children("span").removeClass("glyphicon-chevron-down");
        btn.children("span").addClass("glyphicon-chevron-up");
        $("div[data-info='comment-" + comment + "']").attr("comment", "true");
    },
    hideReply: function(comment, btn){
        $("div[data-info='reply-" + comment + "']").hide();
        btn.children("span").removeClass("glyphicon-chevron-up");
        btn.children("span").addClass("glyphicon-chevron-down");;
        $("div[data-info='comment-" + comment + "']").attr("comment", "false");
    },
    reply: function(reply, area, btn){        
        var name = $("div[data-info='comment-" + reply + "']").children(".comment-header").children("div").children("a").text(); 
        area.attr("addr", reply);        
        area.attr("placeholder", "Respondendo comentário de " + name + "");
        area.focus();
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
    
    // GATILHO PARA MOSTRAR REPOSTAS DOS COMENTÁRIOS
    $(document).on("click", "div[data-id='oa']", function(){    
        var commentId = $(this).closest('.comment-box').data('info');
        commentId = commentId.split("-");        
        var status = $(this).parent().parent().attr("comment");
        if ( status == "false" )
            comment.showReply(commentId[1], $(this));
        else
            comment.hideReply(commentId[1], $(this));
    });
    
    // GATILHO DE RESPOSTA
    $(document).on("click", "div[data-id='reply']", function(){ 
        var reply = $(this).closest('.comment-box').data('info');
        var area = $(this).parent().parent().parent().children("textarea");        
        reply = reply.split("-");
        comment.reply(reply[1], area, $(this));        
    });
    
});