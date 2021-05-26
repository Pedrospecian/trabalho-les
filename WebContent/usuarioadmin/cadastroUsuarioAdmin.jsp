<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Cadastro de Usuário</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Cadastro de usuário</h1>
			<form action="/trabalho-les/cadastroUsuarioAction" method="post" class="js-pristine-validation">
				<div class="form-group">
					<input type="text" name="nome" placeholder="Nome" required data-pristine-required-message="Este campo é obrigatório" cypress-nome>
				</div>
				<div class="form-group">
					<input type="email" name="email" placeholder="E-mail" required data-pristine-required-message="Este campo é obrigatório" data-pristine-email-message="Este campo precisa ser um e-mail válido" cypress-email>
				</div>
				<div class="form-group">
					<select name="status" required data-pristine-required-message="Este campo é obrigatório" cypress-status>
						<option value="">Status</option>
						<option value="1">Ativo</option>
						<option value="0">Inativo</option>
					</select>				
				</div>
				<div class="form-group">
					<select name="tipoUsuario" required data-pristine-required-message="Este campo é obrigatório" cypress-tipoUsuario>
						<option value="">Tipo de usuário</option>
						<option value="1">Funcionário</option>
						<option value="2">Administrador</option>
						<option value="3">Gerente de vendas</option>
					</select>
				</div>
				<div class="form-group">
					<input type="password" id="campoSenha" name="senha" placeholder="Senha" required data-pristine-required-message="Este campo é obrigatório" data-pristine-pattern="/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/i" data-pristine-pattern-message="A senha precisa ter pelo menos um número, uma letra maiúscula, uma letra minúscula e um caractere especial" cypress-campoSenha>
				</div>
				<div class="form-group">
					<input type="password" name="senhaConfirmar" placeholder="Confirmar Senha" required data-pristine-required-message="Este campo é obrigatório"  data-pristine-equals="#campoSenha" data-pristine-equals-message="As senhas inseridas não coincidem" cypress-senhaConfirmar>
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