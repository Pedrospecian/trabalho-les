<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Editar Usuário</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Editar usuário</h1>
			<form action="/trabalho-les/alterarUsuarioAdminAction" class="js-pristine-validation">
				<input type="hidden" name="id" value="${usuario.getId()}">
				<div class="form-group">
					<input type="text" name="nome" placeholder="Nome" required data-pristine-required-message="Este campo é obrigatório" value="${usuario.getNome()}" cypress-nome>				
				</div>
				<div class="form-group">
					<input type="email" name="email" placeholder="E-mail" required data-pristine-required-message="Este campo é obrigatório" data-pristine-email-message="Este campo precisa ser um e-mail válido" value="${usuario.getEmail()}" cypress-email>
				</div>
				<div class="form-group">
					<select name="status" required data-pristine-required-message="Este campo é obrigatório" cypress-status>
						<option value="">Status</option>
						<option value="1" ${1 == usuario.getStatus() ? 'selected=\"selected\"' : ''}>Ativo</option>
						<option value="0" ${0 == usuario.getStatus() ? 'selected=\"selected\"' : ''}>Inativo</option>
					</select>
				</div>
				<div class="form-group">
					<select name="tipoUsuario" required data-pristine-required-message="Este campo é obrigatório" cypress-tipoUsuario>
						<option value="">Tipo de usuário</option>
						<option value="1" ${1 == usuario.getTipoUsuario().getId() ? 'selected=\"selected\"' : ''}>Funcionário</option>
						<option value="2" ${2 == usuario.getTipoUsuario().getId() ? 'selected=\"selected\"' : ''}>Administrador</option>
						<option value="3" ${3 == usuario.getTipoUsuario().getId() ? 'selected=\"selected\"' : ''}>Gerente de vendas</option>
					</select>				
				</div>
				<button cypress-submit>Editar</button>
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