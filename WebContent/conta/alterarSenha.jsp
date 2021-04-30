<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>${title}</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body ${actionForm.equals("alterarMinhaSenhaAction") ? "class='body-front'" : ""}>
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>${title}</h1>
			<form action="/trabalho-les/${actionForm}" method="post" class="js-pristine-validation">
				<c:if test = '${actionForm.equals("alterarSenhaClienteAction")}'>
					<input type="hidden" name="id" value="${id}">
				</c:if>
				<div class="form-group">
					<input type="password" name="senhaAtual" placeholder="Digite a senha atual" required data-pristine-required-message="Este campo é obrigatório" cypress-senhaAtual>
				</div>
				<div class="form-group">
					<input type="password" id="campoSenhaNova" name="senhaNova" placeholder="Nova senha" required data-pristine-required-message="Este campo é obrigatório" data-pristine-pattern="/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/i" data-pristine-pattern-message="A senha precisa ter pelo menos um número, uma letra maiúscula, uma letra minúscula e um caractere especial" cypress-senhaNova>
				</div>
				<div class="form-group">
					<input type="password" name="senhaConfirmar" placeholder="Confirme a nova senha" required data-pristine-required-message="Este campo é obrigatório" data-pristine-equals="#campoSenhaNova" data-pristine-equals-message="As senhas inseridas não coincidem" cypress-senhaConfirmar>
				</div>
				<button type="submit" cypress-submit>Alterar Senha</button>
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