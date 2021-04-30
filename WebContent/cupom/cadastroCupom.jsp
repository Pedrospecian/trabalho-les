<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Cadastro de Cupom</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Cadastro de cupom</h1>
			<form action="/trabalho-les/cadastroCupomDescontoAction" class="js-pristine-validation">
				<div class="form-group">
					<input type="text" name="nome" placeholder="Nome" required data-pristine-required-message="Este campo é obrigatório" cypress-nome>
				</div>
				<div class="form-group">
					<input type="number" name="valor" placeholder="Valor" step="0.01" min="0.01" required data-pristine-required-message="Este campo é obrigatório" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-valor>
				</div>
				<div class="form-group">
					<select name="status" required data-pristine-required-message="Este campo é obrigatório" cypress-status>
						<option value="">Status</option>
						<option value="1">Ativo</option>
						<option value="0">Inativo</option>
					</select>
				</div>
				<div class="form-group">
					<input type="date" name="dataInicio" placeholder="Data de início" required data-pristine-required-message="Este campo é obrigatório" cypress-dataInicio>
				</div>
				<div class="form-group">
					<input type="date" name="dataFim" placeholder="Data de fim" required data-pristine-required-message="Este campo é obrigatório" cypress-dataFim>
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