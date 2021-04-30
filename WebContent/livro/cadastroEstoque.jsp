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
			<h1>Cadastro de estoque</h1>
			<form action="/trabalho-les/cadastrarEstoqueAction" class="js-pristine-validation">
				<input type="hidden" name="idLivro" value="${idLivro}">
				<div class="form-group">
					<input type="text" name="quantidade" placeholder="Quantidade" required min="1" required data-pristine-required-message="Este campo é obrigatório" pattern="/^[0-9]+$/" step="1" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-quantidade>
				</div>
				<div class="form-group">
					<input type="text" name="custo" placeholder="Custo unitário" step="0.01" min="0.01" required data-pristine-required-message="Este campo é obrigatório" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-custo>
				</div>
				<div class="form-group">
					<select name="fornecedor" required data-pristine-required-message="Este campo é obrigatório" cypress-fornecedor>
						<option value="">Fornecedor</option>
						<option value="1">Fornecedor Exemplo LTDA</option>
						<option value="2">Livros e Revistas Teste</option>
						<option value="3">Editora Ininap</option>
					</select>
				</div>
				<div class="form-group">
					<input type="date" name="dataEntrada" placeholder="Data de entrada" required data-pristine-required-message="Este campo é obrigatório" cypress-dataEntrada>
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