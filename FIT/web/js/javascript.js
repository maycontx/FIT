// HOME REGISTER FORM VAR
var homeRegister = {   
    validation: function(){        
        var name = $("input[name='reg-name']");
        var aftername = $("input[name='reg-aftername']");
        var email = $("input[name='reg-email']");
        var pass = $("input[name='reg-pass']");
        var repass = $("input[name='reg-repass']");
        var day = $("select[name='reg-day']");
        var mon = $("select[name='reg-mon']");
        var year = $("select[name='reg-year']");
        var error = [];
        
        if ( name.val() == "" )
            error.push(name);
        
        if ( aftername.val() == "" )
            error.push(aftername);
        
        var mailValidRegex = /^[a-z0-9.]+@[a-z0-9]+\.[a-z]+\.([a-z]+)?$/i;
        if ( !mailValidRegex.test(email.val()) )
            error.push(email);
        
        if ( pass.val().length < 6 )
            error.push(pass);
        
        if ( repass.val() != pass.val() )
            error.push(repass);
        
        if ( (day.val() < 1) || (day.val() > 31) )
            error.push(day);
        
        if ( (mon.val() < 1) || (mon.val() > 12) )
            error.push(mon);
        
        if ( (year.val() < 1902) || (year.val() > 2015) )
            error.push(year);
        
        for ( var i = 0; i < error.length; i++ ){
            
            var errorContent = $("<div>");
            var errorPointer = $("<div>");
            
            errorContent.addClass("error-report");
            errorPointer.addClass("error-pointer");
            errorContent.text("O campo " + error[i].attr("data-name") + " está incorreto");
            
            error[i].parent().append(errorContent);
            errorContent.append(errorPointer);
            
        }    
    }
};

$(document).ready(function(){
    $("form[name='home-register']").submit(function(e){
        if ( $(this).attr("data-react") == "0" ){
           e.preventDefault();
           homeRegister.validation();
        }              
    });
});