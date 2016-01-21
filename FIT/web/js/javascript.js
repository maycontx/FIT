/* 
 * BIRTHDATE MANIPULATION
 * */
var birthValid = {
    create: function(month){
        var year = $("select[name='reg-year']");
        switch (month){
            case "1":
                var days = 31;
                break;
            case "2":
                if ( year.val() % 4 == 0 )
                    var days = 29;
                else
                    var days = 28;                
                break;
            case "3":
                var days = 31;
                break;
            case "4":
                var days = 30;
                break;
            case "5":
                var days = 31;
                break;
            case "6":
                var days = 30;
                break;
            case "7":
                var days = 31;
                break;
            case "8":
                var days = 31;
                break;
            case "9":
                var days = 30;
                break;
            case "10":
                var days = 31;
                break;
            case "11":
                var days = 30;
                break;
            case "12":
                var days = 31;
                break;
        }
        
        for ( var i = 0; i <= days; i++ ){
            var opt = $("<option>");
            if ( i == 0 )
                opt.text("Dia");
            else
                opt.text(i);
            
            opt.val(i);
            $("select[name='reg-day']").append(opt);            
        }
        
    },    
    validDay: function(month){
        $("select[name='reg-day']").empty();
        birthValid.create(month);
    }
};
/* 
 * HOME REGISTER FORM VAR 
 * */
var homeRegister = {   
    validation: function(){        
        $("div[data-dtl='error-content']").each(function(){
           $(this).remove(); 
        });
        
        var name = $("input[name='reg-name']");
        var aftername = $("input[name='reg-aftername']");
        var email = $("input[name='reg-email']");
        var pass = $("input[name='reg-pass']");
        var repass = $("input[name='reg-repass']");
        var day = $("select[name='reg-day']");
        var mon = $("select[name='reg-mon']");
        var year = $("select[name='reg-year']");
        var sex = $("input[name='reg-sex']:checked");
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
        
        if ( (year.val() < 1905) || (year.val() > 2016) )
            error.push(year);
        
        if ( (mon.val() < 1) || (mon.val() > 12) )
            error.push(mon);
        
        if ( (day.val() < 1) || (day.val() > 31) )
            error.push(day);
        
        if ( sex.val() !== "M" && sex.val() !== "F" )
            error.push($("input[name='reg-sex']"));
        
        var birthdate = false;
        var sexValid = false;   
        
        for ( var i = 0; i < error.length; i++ ){
            
            var errorContent = $("<div>");
            var errorPointer = $("<div>");
            
            errorContent.addClass("error-report");
            errorContent.attr("data-dtl", "error-content");
            errorContent.attr("data-id", error[i].attr("data-name"));
            
            if ( error[i].attr("data-name") == "birthdate" ){
                errorContent.css({
                    left: "320px",
                    top: "22px"
                });
                errorContent.text("Há um erro com sua data de nascimento");
                error[i].parent().parent().append(errorContent);
                if ( birthdate == true )
                    errorContent.remove();
                birthdate = true;
            }else if( error[i].attr("data-name") == "sex" ){
                errorContent.css({
                    left: "304px"                    
                });
                errorContent.text("Selecione seu sexo");
                error[i].parent().parent().append(errorContent);
                if ( sexValid == true )
                    errorContent.remove();
                sexValid = true;
            }else{
                errorContent.text("O campo " + error[i].attr("data-name") + " está incorreto");
                error[i].parent().append(errorContent);   
            }
                                    
            
            errorPointer.addClass("error-pointer");
            errorContent.append(errorPointer);
            
        }    
    }
};

$(document).ready(function(){
    // DISPARO DA VALIDAÇÃO FRONT END
    $("form[name='home-register']").submit(function(e){
        if ( $(this).attr("data-react") == "0" ){
           e.preventDefault();
           homeRegister.validation();
        }              
    });
    
    // REMOÇÃO DOS ERROS
    $("form[name='home-register'] input").click(function(){
        $("div[data-id='" + $(this).attr("data-name") + "']").remove();
    });
    
    $("form[name='home-register'] select").click(function(){
        $("div[data-id='" + $(this).attr("data-name") + "']").remove();
    });
    
     $("form[name='home-register'] label").click(function(){
        $("div[data-id='" + $(this).children("input").attr("data-name") + "']").remove();
    });
    
    $(document).on("click", "[data-dtl='error-content']", function(){
       $(this).remove();
    });
    
    // ORGANIZANDO DATA DE NASCIMENTO
    $("select[name='reg-mon']").change(function(){
        birthValid.validDay($(this).val());
    });
    
    $("select[name='reg-year']").change(function(){
        $("select[name='reg-mon']").val(0);
        $("select[name='reg-day']").val(0);
    });
});

