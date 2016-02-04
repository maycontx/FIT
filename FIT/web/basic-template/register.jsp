<div id="finish-register">    
    <div class="page-header">
        <h1>Tipo de conta<small> </small></h1>
    </div>
    <div class="btn-group" data-toggle="buttons">
        <label class="btn btn-primary img-radio">
            <div class="img-recipe">
                <img src="img/atleta2.png">  
            </div>                          
            <input type="radio" name="acctype" value="Atleta" autocomplete="off">
            <div class="radio-title">Atleta</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Publicar conteúdo</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Comentar conteúdo</div>            
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Criação de grupos</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Criação de eventos</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Mensagem privada</div>            
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-down"></span> Realizar consultas</div>
        </label>
        <label class="btn btn-primary img-radio">
            <div class="img-recipe">
                <img src="img/personal.png">  
            </div>    
            <input type="radio" name="acctype" value="Personal" autocomplete="off">
            <div class="radio-title">Personal/Professor</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Publicar conteúdo</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Comentar conteúdo</div>            
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Criação de grupos</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Criação de eventos</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-down"></span> Mensagem privada</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Realizar consultas</div>
        </label>
        <label class="btn btn-primary img-radio">  
            <div class="img-recipe">
                <img src="img/nutri.png">  
            </div>    
            <input type="radio" name="acctype" value="Nutricionista" autocomplete="off">
            <div class="radio-title">Nutricionista</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Publicar conteúdo</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Comentar conteúdo</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Criação de grupos</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Criação de eventos</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-down"></span> Mensagem privada</div>
            <div class="radio-info"><span class="glyphicon glyphicon-thumbs-up"></span> Realizar consultas</div>
        </label>
        <div class="col-lg-12">
            <form action="concluir-cadastro" name="finish-register-form" method="POST">  
                <input type="hidden" name="acctype-ajax" value="0">
                <input type="submit" class="btn btn-success" value="Finalizar cadastro">
            </form>           
        </div>
    </div>    
</div>