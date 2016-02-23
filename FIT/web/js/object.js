var object = {
    comment: function(commentObj){
        local = $("div[data-info='post-"+commentObj.post+"']"); 
        
        // BOX
        var commentBox = $("<div>");
        commentBox.addClass("comment-box");
        commentBox.attr({
            "data-info": "comment-" + commentObj.id,
            "comment": "false"
        });
        
        // IMAGE BOX
        var commentImage = $("<div>");
        commentImage.addClass("comment-image");
        // IMAGE
        var image = $("<img>");
        image.attr("src", "https://scontent-gru2-1.xx.fbcdn.net/hphotos-xfl1/v/t1.0-9/12743569_10207011906594326_6648455166315757498_n.jpg?oh=f2c79bb2b0cb6c5d1e46b65a1591150e&oe=57270C52");
        commentImage.append(image);
        
        // HEADER
        var commentHeader = $("<div>");
        commentHeader.addClass("comment-header");
        
        // HEADER DIV
        var hdiv = $("<div>");
        var userLink = $("<a>"); // LINK
        userLink.attr("src", "#"); // LINK ADDRESS
        userLink.text(commentObj.AuthorName); // LINK TEXT
        hdiv.append(userLink);
        hdiv.append(" comentou");
        commentHeader.append(hdiv);
        
        // BODY
        var commentBody = $("<div>");
        commentBody.addClass("comment-body");
        commentBody.text(commentObj.text);
        
        // FOOTER
        var commentFooter = $("<div>");
        commentFooter.addClass("comment-footer");
        // EDIT BTN        
        var fdiv1 = $("<div>");
        var glyph1 = $("<span>");
        glyph1.addClass("glyphicon");
        glyph1.addClass("glyphicon-pencil");
        fdiv1.append(glyph1, " Editar");
        commentFooter.append(fdiv1);        
        
        commentBox.append(commentImage);
        commentBox.append(commentHeader);
        commentBox.append(commentBody);
        commentBox.append(commentFooter);
        
        var textarea = $("div[data-info='post-"+commentObj.post+"']").find("textarea");  
        
        if ( commentObj.reply == "0" ){            
            // REPLY BTN
            var fdiv2 = $("<div>");
            fdiv2.attr("data-id", "reply");
            var glyph2 = $("<span>");        
            glyph2.addClass("glyphicon");
            glyph2.addClass("glyphicon-bullhorn");
            fdiv2.append(glyph2, " Responder");
            commentFooter.append(fdiv2);
            
            commentBox.insertBefore(textarea);            
        }else{
            commentBox.addClass("reply");
            
            var count = 0;
            $("div[data-info=comment-" + commentObj.reply + "] .reply-box").each(function(){
                count++;
            });
            
            if ( count > 0 ){                
                $("div[data-info=comment-" + commentObj.reply + "] .reply-box").append(commentBox);
            }else{                
                // REPLY BTN
                var fdiv3 = $("<div>");
                fdiv3.attr("data-id", "oa");
                var glyph3 = $("<span>");        
                glyph3.addClass("glyphicon");
                glyph3.addClass("glyphicon-chevron-down");
                fdiv3.append(glyph3, " Ver respostas ("+(count+1)+")");
                $("div[data-info=comment-" + commentObj.reply + "]").find(".comment-footer").append(fdiv3); 
                
                var replyBox = $("<div>");
                replyBox.addClass("reply-box");
                replyBox.attr("data-info", "reply-" + commentObj.reply);
                replyBox.append(commentBox);
                $("div[data-info=comment-" + commentObj.reply + "]").append(replyBox);                
            }
            
            if ( $("div[data-info=comment-" + commentObj.reply + "]").attr("comment") == "false" )
                $("div[data-info=comment-" + commentObj.reply + "] .comment-footer div[data-id='oa']").trigger('click');                        
        }      
        
        textarea.val("");
    }
};
