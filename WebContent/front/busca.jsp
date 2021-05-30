<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Busca</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body class="body-front">
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Busca</h1>
			<form method="get" action="#" class="form-buscar-clientes">
				<input type="text" name="titulo" placeholder="Título" >
				<select name="autor">
					<option value="">Autor</option>
					<c:forEach var="autor" items="${autores}">
						<option value="${autor.getId()}">
							${autor.getNome()}
						</option>
					</c:forEach>
				</select>
				<select name="editora">
					<option value="">Editora</option>
					<c:forEach var="editora" items="${editoras}">
						<option value="${editora.getId()}">
							${editora.getNome()}
						</option>
					</c:forEach>
				</select>
				<input type="text" name="isbn" placeholder="ISBN">
				<input type="number" name="codigoBarras" placeholder="Código de barras">
				<select name="status">
					<option value="">Status</option>
					<option value="1">Ativo</option>
					<option value="0">Inativo</option>
				</select>
				<input type="number" min="1" name="resultadosPorPagina" placeholder="Resultados por página" value="" >
				<button type="submit">Buscar</button>
			</form>
			<div class="search-content">
				<div class="search-sidebar">
					<ul>
						<c:forEach var="categoria" items="${categorias}">
							<option value="${categoria.getId()}">
								${categoria.getNome()}
							</option>
						</c:forEach>
					</ul>
				</div>
				<div class="search-results">
					<div class="books-wrapper">
						<c:forEach var="livro" items="${registros}">
							<div class="book-single">
								<a href="/trabalho-les/produto?id=${livro.getId()}" cypress-livro-single>
									<img src="${livro.getCapa()}" alt="${livro.getTitulo()}">
								</a>
								<p class="book-title">${livro.getTitulo()}</p>
								<p class="book-price"><div class="js-dinheiro">${livro.getPreco()}</div></p>
							</div>
						</c:forEach>
						<!--<div class="book-single">
							<a href="/trabalho-les/produto?id=1" cypress-livro-single>
								<img src="assets/images/produtos/livro-ficcao.jpg" alt="Alice no país das maravilhas">
								<p class="book-title">Alice no país das maravilhas</p>
								<p class="book-price">R$ 40,00</p>
							</a>
						</div>
						<div class="book-single">
							<a href="/trabalho-les/produto?id=2">
								<img src="assets/images/produtos/livro-manual.jpg" alt="Alice no país das maravilhas">
								<p class="book-title">Livro de receitas</p>
								<p class="book-price">R$ 145,00</p>
							</a>
						</div>
						<div class="book-single">
							<a href="/trabalho-les/produto?id=3">
								<img src="assets/images/produtos/livro-suspense.jpg" alt="Alice no país das maravilhas">
								<p class="book-title">Sherlock Holmes</p>
								<p class="book-price">R$ 12,99</p>
							</a>
						</div>

						<div class="book-single">
							<a href="/trabalho-les/produto?id=1">
								<img src="assets/images/produtos/livro-ficcao.jpg" alt="Alice no país das maravilhas">
								<p class="book-title">Alice no país das maravilhas</p>
								<p class="book-price">R$ 40,00</p>
							</a>
						</div>
						<div class="book-single">
							<a href="/trabalho-les/produto?id=2">
								<img src="assets/images/produtos/livro-manual.jpg" alt="Alice no país das maravilhas">
								<p class="book-title">Livro de receitas</p>
								<p class="book-price">R$ 145,00</p>
							</a>
						</div>
						<div class="book-single">
							<a href="/trabalho-les/produto?id=3">
								<img src="assets/images/produtos/livro-suspense.jpg" alt="Alice no país das maravilhas">
								<p class="book-title">Sherlock Holmes</p>
								<p class="book-price">R$ 12,99</p>
							</a>
						</div>

						<div class="book-single">
							<a href="/trabalho-les/produto?id=1">
								<img src="assets/images/produtos/livro-ficcao.jpg" alt="Alice no país das maravilhas">
								<p class="book-title">Alice no país das maravilhas</p>
								<p class="book-price">R$ 40,00</p>
							</a>
						</div>
						<div class="book-single">
							<a href="/trabalho-les/produto?id=2">
								<img src="assets/images/produtos/livro-manual.jpg" alt="Alice no país das maravilhas">
								<p class="book-title">Livro de receitas</p>
								<p class="book-price">R$ 145,00</p>
							</a>
						</div>
						<div class="book-single">
							<a href="/trabalho-les/produto?id=3">
								<img src="assets/images/produtos/livro-suspense.jpg" alt="Alice no país das maravilhas">
								<p class="book-title">Sherlock Holmes</p>
								<p class="book-price">R$ 12,99</p>
							</a>
						</div>-->
					</div>
				</div>
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
	<script type="text/javascript" src="assets/js/main.js"></script>
</body>
</html>