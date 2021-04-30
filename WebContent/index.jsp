<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Página inicial</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body class="body-front">
	<header>
		<!--<div class="container">
			<div class="d-flex">
				<a href="/trabalho-les/home">Home</a>
				<a href="/trabalho-les/homeAdmin">Home do admin</a>
				<div class="busca">
					<form class="busca-form" method="get" action="/trabalho-les/busca">
						<input type="text" name="term" cypress-term />
						<button cypress-busca>Pesquisar</button>
					</form>
				</div>
				<a href="/trabalho-les/minhaConta" cypress-minhaConta>Minha conta</a>
				<a href="/trabalho-les/login" cypress-login>Login</a>
				<a href="/trabalho-les/loginAdmin" cypress-loginAdmin>Login no admin</a>
				<a href="/trabalho-les/logout" cypress-logout>Logout</a>
			</div>
		</div>-->
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Veja nossos livros</h1>
			<div class="books-wrapper">
				<c:forEach var="livro" items="${livros}">
					<div class="book-single">
						<a href="/trabalho-les/produto?id=${livro.getId()}" cypress-livro-single>
							<img src="assets/images/produtos/livro-ficcao.jpg" alt="Alice no país das maravilhas">
						</a>
						<p class="book-title">${livro.getTitulo()}</p>
						<p class="book-price"><div class="js-dinheiro">${livro.getPreco()}</div></p>						
					</div>
				</c:forEach>
			</div>
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
</body>
</html>