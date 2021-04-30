<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Login no admin</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>Login no admin</h1>
			<form method="post" action="/trabalho-les/loginAdminAction" class="js-pristine-validation">
				<div class="form-group">
					<input type="email" name="email" placeholder="E-mail" required data-pristine-required-message="Este campo é obrigatório" data-pristine-email-message="Este campo precisa ser um e-mail válido" cypress-email>
				</div>
				<div class="form-group">
					<input type="password" name="senha" placeholder="Senha" required data-pristine-required-message="Este campo é obrigatório" cypress-senha>
				</div>
				<button type="submit" cypress-submit>Entrar</button>
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