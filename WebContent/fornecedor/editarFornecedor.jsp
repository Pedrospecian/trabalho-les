<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Editar Fornecedor</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Editar fornecedor</h1>
			<form action="/trabalho-les/alteraFornecedorAction" class="js-pristine-validation">
				<input type="hidden" name="id" value="1">
				<div class="form-group">
					<input type="text" name="nome" placeholder="Nome" value="teste" required data-pristine-required-message="Este campo é obrigatório" cypress-nome>
				</div>
				<div class="form-group">
					<input type="email" name="email" placeholder="E-mail" value="teste@teste.com" required data-pristine-required-message="Este campo é obrigatório" data-pristine-email-message="Este campo precisa ser um e-mail válido" cypress-email>
				</div>
				<div class="form-group">
					<select name="status" required data-pristine-required-message="Este campo é obrigatório" cypress-status>
						<option value="">Status</option>
						<option value="1" selected>Ativo</option>
						<option value="0">Inativo</option>
					</select>
			</div>
				<h3>Documento</h3>
				<div class="form-group">
					<select name="tipoDocumento" required data-pristine-required-message="Este campo é obrigatório" cypress-tipoDocumento>
						<option value="">Tipo de Documento</option>
						<option value="2">CNPJ</option>
						<option value="3">RG</option>
						<option value="4">Social Security Card</option>
					</select>
				</div>
				<div class="form-group">
					<input type="text" name="documento" placeholder="Documento (sem caracteres especiais)" required data-pristine-required-message="Este campo é obrigatório" cypress-documento>
				</div>
				<div class="form-group">
					<input type="date" name="dataValidade" required data-pristine-required-message="Este campo é obrigatório" cypress-dataValidade>
				</div>
				<h3>Endereço</h3>
				<div class="form-group">
					<input type="text" name="nomeEndereco" placeholder="Nome do Endereço" required data-pristine-required-message="Este campo é obrigatório" cypress-nomeEndereco>
				</div>
				<div class="form-group">
					<select name="tipoEndereco" required data-pristine-required-message="Este campo é obrigatório" cypress-tipoEndereco>
						<option value="">Tipo de endereço</option>
						<option value="1">Residencial</option>
						<option value="2">Comercial</option>
					</select>
				</div>
				<div class="form-group">
					<select name="tipoResidencia" required data-pristine-required-message="Este campo é obrigatório" cypress-tipoResidencia>
						<option value="">Tipo de residência</option>
						<option value="1">Casa</option>
						<option value="2">Apartamento</option>
						<option value="3">Outro...</option>
					</select>
				</div>
				<div class="form-group">
					<select name="funcaoEndereco" required data-pristine-required-message="Este campo é obrigatório" cypress-funcaoEndereco>
						<option value="">Função do endereço</option>
						<option value="1">Endereço de Cobrança</option>
						<option value="2">Endereço de Entrega</option>
						<option value="3">Endereço de Cobrança e Entrega</option>
					</select>
				</div>
				<div class="form-group">
					<select name="pais" required data-pristine-required-message="Este campo é obrigatório" cypress-pais>
						<option value="">País</option>
						<option value="1">Brasil</option>
						<option value="2">EUA</option>
					</select>
				</div>

				<div class="form-group">
					<input type="text" name="cep" placeholder="CEP"  class="js-cep-api js-cep-mask" required data-pristine-required-message="Este campo é obrigatório" cypress-cep>
				</div>
				<div class="form-group">
					<select name="tipoLogradouro" class="js-tipo-logradouro" cypress-tipoLogradouro>
						<option value="">Tipo de logradouro</option>
						<option value="1">Rua</option>
						<option value="2">Avenida</option>
						<option value="3">Viela</option>
						<option value="4">Outro...</option>
					</select>
				</div>
				<div class="form-group">
					<input type="text" name="logradouro" placeholder="Logradouro" required data-pristine-required-message="Este campo é obrigatório" class="js-logradouro" cypress-logradouro>
				</div>
				<div class="form-group">
					<input type="text" name="numero" placeholder="Número" required data-pristine-required-message="Este campo é obrigatório" cypress-numero>
				</div>
				<div class="form-group">
					<input type="text" name="complemento" placeholder="Complemento" cypress-complemento>
				</div>
				<div class="form-group">
					<input type="text" name="bairro" placeholder="Bairro" required data-pristine-required-message="Este campo é obrigatório" class="js-bairro" cypress-bairro>
				</div>
				<div class="form-group">
					<input type="text" name="cidade" placeholder="Cidade" required data-pristine-required-message="Este campo é obrigatório" class="js-cidade" cypress-cidade>
				</div>
				<div class="form-group">
					<input type="text" name="estado" placeholder="Estado" required data-pristine-required-message="Este campo é obrigatório" class="js-uf" cypress-estado>
				</div>
				Observações
				<div class="form-group">
					<textarea name="observacoes" cypress-observacoes></textarea>
				</div>
				<button cypress-submit>Cadastrar</button>
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
</body>
</html>