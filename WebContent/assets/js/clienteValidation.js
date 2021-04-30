var form = document.querySelector('.js-cadastrar-cliente');

if (form) {
	form.addEventListener('submit', function(e){	
		var documento = document.querySelector('.js-arrTipoDocumento');
		var endereco = document.querySelector('.js-arrTipoEndereco');
		var cartao = document.querySelector('.js-arrNumeroCartao');
		var telefone = document.querySelector('.js-arrTipoTelefone');

		if (!documento.value) {
			document.querySelector('.js-form-varios-documentos').classList.add('error');
			document.querySelector('.js-form-varios-documentos .js-error-message-documento').innerHTML = "É necessário inserir ao menos um documento";
			e.preventDefault();
		} else {
			if (!documento.toUpperCase().includes("CPF")) {
				document.querySelector('.js-form-varios-documentos').classList.add('error');
				document.querySelector('.js-form-varios-documentos .js-error-message-documento').innerHTML = "É necessário que ao menos um CPF seja inserido";
				e.preventDefault();
			} else {
				document.querySelector('.js-form-varios-documentos').classList.remove('error');
			}
		}

		if (!endereco.value) {
			document.querySelector('.js-form-varios-enderecos').classList.add('error');
			e.preventDefault();
		} else {
			document.querySelector('.js-form-varios-enderecos').classList.remove('error');
		}

		if (!cartao.value) {
			document.querySelector('.js-form-varios-cartoes').classList.add('error');
			e.preventDefault();
		} else {
			document.querySelector('.js-form-varios-cartoes').classList.remove('error');
		}

		if (!telefone.value) {
			document.querySelector('.js-form-varios-telefones').classList.add('error');
			e.preventDefault();
		} else {
			document.querySelector('.js-form-varios-telefones').classList.remove('error');
		}
	});
} else {
	form = document.querySelector('.js-editar-cliente');

	if (form) {
		form.addEventListener('submit', function(){

		});
	}
}