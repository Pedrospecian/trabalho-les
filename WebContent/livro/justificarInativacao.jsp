<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Justificar inativação</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Justificar inativação de Alice no país das maravilhas</h1>
			<p>Após enviar a justificativa, o administrador poderá inativar o livro ou não.</p>
			<form action="/trabalho-les/listagemLivros" class="js-pristine-validation">
				<input type="hidden" name="id" value="1">
				<div class="form-group">
					<select name="categoriaInativacao" required data-pristine-required-message="Este campo é obrigatório" cypress-categoriaInativacao>
						<option value="">Categoria de inativação</option>
						<option value="1">Categoria 1</option>
						<option value="2">Categoria 2</option>
					</select>
				</div> 
				<div class="form-group">
					<textarea id="justificativa" name="justificativa" required data-pristine-required-message="Este campo é obrigatório" cypress-justificativa></textarea>
				</div>
				<button type="submit" cypress-submit>Justificar</button>
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