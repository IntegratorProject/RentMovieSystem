$(document).ready(function(){
   
    var indice = window.location.href.substr(window.location.href.lastIndexOf("/") + 1);
    
    $('.selectMenu').removeClass('is-selected');
    
    if(indice === "Inicio.xhtml" | indice === ""){
        $('#selectMenuInicio').addClass('is-selected');
    }
    
    if(indice === "cliente.xhtml"){
        $('#selectMenuCliente').addClass('is-selected');
    }
    
    if(indice === "dependente.xhtml"){
        $('#selectMenuDependente').addClass('is-selected');
    }
    
    if(indice === "funcionario.xhtml"){
        $('#selectMenuFuncionario').addClass('is-selected');
    }
    
    if(indice === "acervo.xhtml"){
        $('#selectMenuAcervo').addClass('is-selected');
    }
    
    if(indice === "relatorio.xhtml"){
        $('#selectMenuRelatorios').addClass('is-selected');
    }
    
    if(indice === "locacao.xhtml"){
        $('#selectMenuLocacao').addClass('is-selected');
    }
    
});

$(function() {
	$('.js-toggle').bind('click', function(event) {
		$('.js-sidebar, .js-content').toggleClass('is-toggled');
		event.preventDefault();
	});	
});

