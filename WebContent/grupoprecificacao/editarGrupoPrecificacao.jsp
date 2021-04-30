<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Editar Grupo de precificação</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Editar grupo de precificação</h1>
			<form action="/trabalho-les/alteraGrupoPrecificacaoAction" class="js-pristine-validation">
				<input type="hidden" name="id" value="${grupoPrecificacao.getId()}">
				<div class="form-group">
					<input type="text" name="nome" placeholder="Nome" value="${grupoPrecificacao.getNome()}" required data-pristine-required-message="Este campo é obrigatório" cypress-nome>
				</div>
				<div class="form-group">
					<input type="number" name="porcentagem" value="${grupoPrecificacao.getPorcentagem()}" placeholder="Porcentagem de Lucro" min="0" step="0.01" required data-pristine-required-message="Este campo é obrigatório" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-porcentagem>
				</div>
				<div class="form-group">
					<select name="status" required data-pristine-required-message="Este campo é obrigatório" cypress-status>
						<option value="">Status</option>
						<option value="1" ${1 == grupoPrecificacao.getStatus() ? 'selected=\"selected\"' : ''}>Ativo</option>
						<option value="0" ${0 == grupoPrecificacao.getStatus() ? 'selected=\"selected\"' : ''}>Inativo</option>
					</select>
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