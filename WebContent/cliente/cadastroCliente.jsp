<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Cadastro de cliente</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body ${actionForm.equals("cadastroClienteAction") ? "" : "class='body-front'"} >
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>Cadastro de cliente</h1>
			<c:if test = '${req.getParameter("erro") != null}'>
				<p class="block-error">
					Há erros no formulário
				</p>
			</c:if>
			<form action="/trabalho-les/${actionForm}" method="post" class="form-cadastro-cliente js-cadastrar-cliente js-pristine-validation">
				<div class="form-group">
					<input type="text" name="nome" placeholder="Nome" required data-pristine-required-message="Este campo é obrigatório" cypress-nome>
				</div>
				<div class="form-group">
					<input type="email" name="email" placeholder="E-mail" required data-pristine-required-message="Este campo é obrigatório" data-pristine-email-message="Este campo precisa ser um e-mail válido" cypress-email>
				</div>
				<div class="form-group">
					<select name="sexo" required data-pristine-required-message="Este campo é obrigatório" cypress-sexo>
						<option value="">Sexo</option>
						<option value="1">Masculino</option>
						<option value="2">Feminino</option>
					</select>
				</div>
				<div class="form-group">
					<select name="tipoCliente" required data-pristine-required-message="Este campo é obrigatório" cypress-tipoCliente>
						<option value="">Tipo de Cliente</option>
						<option value="1">Comprador</option>
						<option value="2">Revendedor</option>
						<option value="3">Parceiro</option>
					</select>
				</div>
				<div class="form-group">
					<input type="date" name="dataNascimento" placeholder="Data de nascimento" required data-pristine-required-message="Este campo é obrigatório" data-pristine-date-message="Este campo precisa ser uma data válida" cypress-dataNascimento>
				</div>
				<c:if test='${actionForm.equals("cadastroClienteAction")}'>
					<div class="form-group">
						<select name="status" required data-pristine-required-message="Este campo é obrigatório" cypress-status>
							<option value="">Status</option>
							<option value="1">Ativo</option>
							<option value="0">Inativo</option>
						</select>
					</div>
				</c:if>
				<div class="form-group">
					<input type="password" name="senha" placeholder="Senha" id="campoSenha" required data-pristine-required-message="Este campo é obrigatório" data-pristine-pattern="/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/i" data-pristine-pattern-message="A senha precisa ter pelo menos um número, uma letra maiúscula, uma letra minúscula e um caractere especial" cypress-senha>
				</div>
				<div class="form-group">
					<input type="password" name="senhaConfirmar" placeholder="Confirmar Senha" required data-pristine-required-message="Este campo é obrigatório" data-pristine-equals="#campoSenha" data-pristine-equals-message="As senhas inseridas não coincidem" cypress-senhaConfirmar>
				</div>
				<div class="form-varios js-form-varios-documentos">
					<h3>Documentos</h3>
					<div class="boxes-wrapper js-boxes-documentos">
						<input type="hidden" name="arrTipoDocumento" class="js-arrTipoDocumento" required>
						<input type="hidden" name="arrNumeroDocumento" class="js-arrNumeroDocumento">
						<input type="hidden" name="arrValidadeDocumento" class="js-arrValidadeDocumento">
					</div>
					<div class="form-add js-form-add-document">
						<select name="tipoDocumento" class="js-tipo-documento">
							<option value="">Tipo de Documento</option>
							<option value="1">CPF</option>
							<option value="2">CNPJ</option>
							<option value="3">RG</option>
							<option value="4">Social Security Card</option>
						</select>
						<input type="text" name="documento" placeholder="Documento (sem caracteres especiais)" class="js-numero-documento">
						<input type="date" name="dataValidade" placeholder="Data de validade" class="js-validade-documento">
					</div>
					<button type="button" class="js-adicionar-documento">Adicionar documento</button>
					<p class="varios-error-message js-error-message-documento"></p>
				</div>

				<div class="form-varios js-form-varios-enderecos">
					<h3>Endereços</h3>
					<div class="boxes-wrapper js-boxes-enderecos">
						<input type="hidden" name="arrTipoEndereco" class="js-arrTipoEndereco" required>
						<input type="hidden" name="arrNomeEndereco" class="js-arrNomeEndereco">

						<input type="hidden" name="arrTipoResidencia" class="js-arrTipoResidencia">
						<input type="hidden" name="arrTipoLogradouro" class="js-arrTipoLogradouro">
						<input type="hidden" name="arrFuncaoEndereco" class="js-arrFuncaoEndereco">

						<input type="hidden" name="arrCep" class="js-arrCep">
						<input type="hidden" name="arrLogradouro" class="js-arrLogradouro">
						<input type="hidden" name="arrNumero" class="js-arrNumero">
						<input type="hidden" name="arrComplemento" class="js-arrComplemento">
						<input type="hidden" name="arrBairro" class="js-arrBairro">
						<input type="hidden" name="arrCidade" class="js-arrCidade">
						<input type="hidden" name="arrUf" class="js-arrUf">
						<input type="hidden" name="arrPais" class="js-arrPais">
						<input type="hidden" name="arrObservacoes" class="js-arrObservacoes">
					</div>
					<div class="form-add js-form-add-address">					
						<input type="text" name="nomeEndereco" placeholder="Nome do Endereço" class="js-nome-endereco">
						<select name="tipoEndereco" class="js-tipo-endereco">
							<option value="">Tipo de endereço</option>
							<option value="1">Residencial</option>
							<option value="2">Comercial</option>
						</select>
						<select name="tipoResidencia" class="js-tipo-residencia">
							<option value="">Tipo de residência</option>
							<option value="1">Casa</option>
							<option value="2">Apartamento</option>
							<option value="3">Outro...</option>
						</select>
						<select name="funcaoEndereco" class="js-funcao-endereco">
							<option value="">Função do endereço</option>
							<option value="1">Endereço de Cobrança</option>
							<option value="2">Endereço de Entrega</option>
							<option value="3">Endereço de Cobrança e Entrega</option>
						</select>
						<select name="pais" class="js-pais">
							<option value="">País</option>
							<option value="1">Brasil</option>
							<option value="2">EUA</option>
						</select>

						<input type="text" name="cep" placeholder="CEP" class="js-cep-api js-cep-mask js-cep">
						<br>
						<select name="tipoLogradouro" class="js-tipo-logradouro">
							<option value="">Tipo de logradouro</option>
							<option value="1">Rua</option>
							<option value="2">Avenida</option>
							<option value="3">Viela</option>
							<option value="4">Outro...</option>
						</select>
						<input type="text" name="logradouro" placeholder="Logradouro" class="js-logradouro">
						<input type="text" name="numero" placeholder="Número" class="js-numero">
						<input type="text" name="complemento" placeholder="Complemento" class="js-complemento">
						<input type="text" name="bairro" placeholder="Bairro" class="js-bairro">
						<input type="text" name="cidade" placeholder="Cidade" class="js-cidade">
						<input type="text" name="estado" placeholder="Estado" class="js-uf">					
						Observações
						<textarea name="observacoes" class="js-observacoes"></textarea>
					</div>
					<button type="button" class="js-adicionar-endereco">Adicionar endereço</button>
					<p class="varios-error-message">É necessário inserir ao menos um endereço</p>
				</div>

				<div class="form-varios js-form-varios-cartoes">
					<h3>Cartões de Crédito</h3>
					<div class="boxes-wrapper js-boxes-cartoes">					
						<input type="hidden" name="arrNumeroCartao" class="js-arrNumeroCartao" required>
						<input type="hidden" name="arrNomeCartao" class="js-arrNomeCartao">
						<input type="hidden" name="arrBandeiraCartao" class="js-arrBandeiraCartao">
						<input type="hidden" name="arrCodigoCartao" class="js-arrCodigoCartao">
						<input type="hidden" name="arrValidadeCartao" class="js-arrValidadeCartao">
					</div>
					<div class="form-add js-form-add-card">
						<input type="text" name="cartaoNumero" placeholder="Número do cartão" class="js-numero-cartao">
						<input type="text" name="cartaoNome" placeholder="Nome impresso no cartão" class="js-nome-cartao">
						<select name="cartaoBandeira" class="js-bandeira-cartao">
							<option value="">Bandeira do Cartão</option>
							<option value="1">Visa</option>
							<option value="2">MasterCard</option>
						</select>
						<input type="text" name="cartaoCodigo" placeholder="Código de segurança" class="js-cvv-cartao">
						<input type="date" name="cartaoValidade" placeholder="Data de validade do cartão" class="js-validade-cartao">
					</div>
					<button type="button" class="js-adicionar-cartao">Adicionar cartão de crédito</button>
					<p class="varios-error-message">É necessário inserir ao menos um cartão de crédito</p>
				</div>

				<div class="form-varios js-form-varios-telefones">
					<h3>Telefones</h3>
					<div class="boxes-wrapper js-boxes-telefones">					
						<input type="hidden" name="arrTipoTelefone" class="js-arrTipoTelefone" required>
						<input type="hidden" name="arrDDDTelefone" class="js-arrDDDTelefone">
						<input type="hidden" name="arrNumeroTelefone" class="js-arrNumeroTelefone">
					</div>
					<div class="form-add js-form-add-phone">
						<select name="tipoTelefone" class="js-tipo-telefone">
							<option value="">Tipo do telefone</option>
							<option value="1">Residencial</option>
							<option value="2">Celular</option>
						</select>
						<input type="text" name="dddTelefone" placeholder="DDD" class="js-ddd-telefone">
						<input type="text" name="numeroTelefone" placeholder="Número" class="js-numero-telefone">
					</div>
					<button type="button" class="js-adicionar-telefone">Adicionar telefone</button>
					<p class="varios-error-message">É necessário inserir ao menos um telefone</p>
				</div>

				<button type="submit" cypress-submit>Cadastrar</button>
			</form>
		</div>
	</main>
	<footer>
		<div class="container">
			<p>LES - Laboratório de Engenharia de Software</p>
			<br>
			Aline Laconca - RA: 1840481922014
			<br>
			Pedro Toupitzen Specian - RA: 1840481923023
		</div>
	</footer>	
	<script type="text/javascript" src="assets/js/vendor/imask.js"></script>
	<script type="text/javascript" src="assets/js/vendor/pristine.min.js"></script>
	<script type="text/javascript" src="assets/js/main.js"></script>
	<script type="text/javascript" src="assets/js/fieldValidation.js"></script>
	<!-- <script type="text/javascript" src="assets/js/clienteValidation.js"></script> -->
</body>
</html>