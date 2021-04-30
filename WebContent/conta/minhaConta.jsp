<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Minha conta</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body class="body-front">
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>Minha conta</h1>
			<div class="notificacoes-wrapper">
				<c:forEach var="notificacao" items="${notificacoes}">
					<div class="notificacao-single" style="color: #${notificacao.getCor()};">
						${notificacao.getDescricao()}
					</div>
				</c:forEach>
			</div>
			<br>
			<a href="/trabalho-les/carrinho" cypress-carrinho>Carrinho</a>
			<br>
			<a href="/trabalho-les/meusPedidos" cypress-meusPedidos>Meus pedidos</a>
			<br>
			<a href="/trabalho-les/meusCuponsTroca" cypress-meusCuponsTroca>Meus cupons de troca</a>
			<br>
			<a href="/trabalho-les/alterarMeusDados" cypress-alterarMeusDados>Alterar Dados</a>
			<br>
			<a href="/trabalho-les/alterarMinhaSenha" cypress-alterarMinhaSenha>Alterar Senha</a>
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
</body>
</html>