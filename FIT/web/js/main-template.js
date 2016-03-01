var comment = {
    show: function (post, btn) {
        btn.addClass("liked");
        $("div[data-info='" + post + "']").css("border-bottom", "1px solid #E7E7E7");
        $("div[data-info='post-" + post + "']").show();
        $("div[data-info='post-" + post + "'] textarea").focus();
        $("div[data-info='" + post + "']").attr("comment", "true");
    },
    hide: function (post, btn) {
        btn.removeClass("liked");
        $("div[data-info='" + post + "']").css("border-bottom", "1px solid #ACCAB9");
        $("div[data-info='post-" + post + "']").hide();
        $("div[data-info='" + post + "']").attr("comment", "false");
    },
    showReply: function (comment, btn) {
        $("div[data-info='reply-" + comment + "']").show();
        btn.children("span").removeClass("glyphicon-chevron-down");
        btn.children("span").addClass("glyphicon-chevron-up");
        $("div[data-info='comment-" + comment + "']").attr("comment", "true");
    },
    hideReply: function (comment, btn) {
        $("div[data-info='reply-" + comment + "']").hide();
        btn.children("span").removeClass("glyphicon-chevron-up");
        btn.children("span").addClass("glyphicon-chevron-down");
        ;
        $("div[data-info='comment-" + comment + "']").attr("comment", "false");
    },
    reply: function (reply, area, btn) {
        var name = $("div[data-info='comment-" + reply + "']").children(".comment-header").children("div").children("a").text();
        area.attr("addr", reply);
        area.attr("placeholder", "Respondendo comentário de " + name + "");
        area.focus();
        area.val("");
        area.attr("_e", false);
        
        $("div[data-id='reply-cancel']").each(function(){
            $(this).remove();
        });
        $("div[data-id='edit-cancel']").each(function(){
            $(this).remove();
        });
        var cancel = $("<div>");
        var glyph = $("<span>");       
        glyph.addClass("glyphicon glyphicon-remove"); 
        cancel.append(glyph);
        cancel.append(" Cancelar resposta");
        cancel.addClass("reply-cancel");        
        cancel.attr("data-id", "reply-cancel");
        
        btn.parent().parent().parent().append(cancel);        
    },
    cancelReply: function(btn){
        var textarea = btn.parent().children("textarea"); 
        textarea.attr({            
            placeholder: "Comentar...",
            "addr": 0
        });
        textarea.val("");
        btn.remove();        
    },
    edit: function(btn){
        var commentBox = btn.parent().parent(".comment-box");
        var comment = commentBox.attr("data-info").split("-");
        comment = comment[1];
        var text = commentBox.children(".comment-body").text().trim();
        
        var textarea = commentBox.parent().children("textarea");
        textarea.val(text);
        textarea.attr("_e", true);
        textarea.attr("addr", comment);
        textarea.focus();
        
        $("div[data-id='edit-cancel']").each(function(){
            $(this).remove();
        });
        $("div[data-id='reply-cancel']").each(function(){
            $(this).remove();
        });
        var cancel = $("<div>");
        var glyph = $("<span>");       
        glyph.addClass("glyphicon glyphicon-remove"); 
        cancel.append(glyph);
        cancel.append(" Cancelar edição");
        cancel.addClass("reply-cancel");        
        cancel.attr("data-id", "edit-cancel");
        
        btn.parent().parent().parent().append(cancel); 
    },
    cancelEdit: function(btn){
        var textarea = btn.parent().children("textarea"); 
        textarea.attr({            
            placeholder: "Comentar...",
            "addr": 0
        });
        textarea.val("");
        textarea.attr("_e", false);
        btn.remove(); 
    }
};

$(document).ready(function () {

    // GATILHO PARA MOSTRA O CAMPO DE COMENTÁRIO
    $("div[data-id='comment']").click(function () {
        var post = $(this).parent().parent(".post").attr("data-info");
        var status = $(this).parent().parent(".post").attr("comment");
        if (status == "false") {
            comment.show(post, $(this));
        } else {
            comment.hide(post, $(this));
        }

    });

    // GATILHO PARA MOSTRAR REPOSTAS DOS COMENTÁRIOS
    $(document).on("click", "div[data-id='oa']", function () {
        var commentId = $(this).closest('.comment-box').data('info');
        commentId = commentId.split("-");
        var status = $(this).parent().parent().attr("comment");
        if (status == "false")
            comment.showReply(commentId[1], $(this));
        else
            comment.hideReply(commentId[1], $(this));
    });

    // GATILHO DE RESPOSTA
    $(document).on("click", "div[data-id='reply']", function () {
        var reply = $(this).closest('.comment-box').data('info');
        var area = $(this).parent().parent().parent().children("textarea");
        reply = reply.split("-");
        comment.reply(reply[1], area, $(this));
    });

    // GATILHO DE CANCELAMENTO DA RESPOSTA
    $(document).on("click", "div[data-id='reply-cancel']", function () {
        comment.cancelReply($(this));               
    });
    
    // GATILHO PARA EDIÇÃO DE COMENTÁRIO
    $("[data-id='c-edit']").click(function(){
        comment.edit($(this));
    });
    
    // GATILHO PARA CANCELAR EDIÇÃO DE COMENTÁRIO
    $(document).on("click", "[data-id='edit-cancel']", function () {    
        comment.cancelEdit($(this));
    });
    
    // TEXTAREA MANAGER
    $("textarea[name='comment-text']").keyup(function () {
        var rows = $(this).attr("rows");
        var sHeight = $(this).prop("scrollHeight");
        var oHeight = $(this).prop("offsetHeight");

        if (sHeight > oHeight + 1) {
            $(this).attr("rows", (parseInt(rows) + 1));
            $(this).parent().scrollTop(10000);
        }
    });

});