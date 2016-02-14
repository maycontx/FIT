/*
 * GLOBAL ALERTA
 */
var global = {
    alert: function(text){
        $("div[class='global-alert']").each(function(){
           $(this).remove(); 
        });
        
        var alert = $("<div>");
               
        alert.html("<span class='glyphicon glyphicon-alert'></span>" + text);
        alert.addClass("global-alert");
        
        alert.appendTo("body");
        alert.slideDown(200).delay(3000).slideUp(100);
        
    }
};

/*
 * VARIAVEL DE FINALIZACAO DE CADASTRO
 */
var finishRegister = {
    input: function(acctype){
        $("input[name='acctype-ajax']").val(acctype);        
    },
    validation: function(){
        var acctype = $("input[name='acctype-ajax']").val();        
        if (acctype != "Atleta" && acctype != "Personal" && acctype != "Nutricionista")
            return false;        
        return true;
            
    }
};

/*
 * LOGIN VAR
 */
var homeLogin = {
    show: function(){
        $("div[data-id='login-content']").show();
        $("input[name='log-email']").focus();
    }
};

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
        
        var mailValidRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
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
    
        if ( error.length == 0 ){
            $("form[name='home-register']").attr("data-react", "_zxv0");
            $("form[name='home-register']").submit();
        }
    
    }
};

// GATILHOS ---------------------------------------------------------------------------------- //

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
    
    // GATILHO PARA MOSTRAR O LOGIN
    $("div[data-id='login']").click(function(){
        homeLogin.show();
    });
    
    // GATILHO PARA SUBMETER O FORMULARIO DE LOGIN
    $("div[data-id='submit-home-login']").click(function(){
       $("form[name='login-form']").submit(); 
    });
    
    // GATILHO PARA ALTERAÇÃO DO TIPO DE CONTA NA CONCLUSÃO DE CADASTRO
    $("input[name='acctype']").change(function(){
        finishRegister.input($(this).val());        
    });
    
    // GATILHO PARA SUBMISSAO DO FORMULARIO DE CONCLUSÃO DE CADASTRO
    $("form[name='finish-register-form'] input[type='submit']").click(function(e){
        e.preventDefault();
        if ( finishRegister.validation() == true )
            $(this).parent("form").submit();
        else
            global.alert("Escolha o tipo de conta desejado.");
    });
    
    // GATILHO PARA PASSAR O FOCO DO EMAIL PARA A SENHA NA TELA DE LOGIN
    $("input[name='log-email']").keypress(function(event){	
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if( keycode == "13" ){
		$("input[name='log-pass']").focus();	
	}
    });
    
    // GATILHO PARA SUBMETER O LOGIN SE O FOCO FOR O CAMPO SENHA
    $("input[name='log-pass']").keypress(function(event){	
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if( keycode == "13" ){
		$("form[name='login-form']").submit();	
	}
    });
    
    // EM CASO DE FALHA DE LOGIN MANTER FORM APARENTE
    if ( $(".login-content").attr("fail") == "1" )
        $(".login-content").css("display", "block");
});

