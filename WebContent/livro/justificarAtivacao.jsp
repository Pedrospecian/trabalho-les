<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Justificar ativação</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Justificar ativação de ${livro.getTitulo()}</h1>
			<p>Após enviar a justificativa, o administrador poderá ativar o livro ou não.</p>
			<form action="/trabalho-les/cadastrarSolicitacaoAtivacao" class="js-pristine-validation">
				<input type="hidden" name="id" value="${livro.getId()}">
				<div class="form-group">
					<select name="categoriaAtivacao" required data-pristine-required-message="Este campo é obrigatório" cypress-categoriaAtivacao>
						<option value="">Categoria de inativação</option>
						<c:forEach var="categoria" items="${categorias}">
							<option value="${categoria.getId()}">${categoria.getNome()}</option>
						</c:forEach>
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