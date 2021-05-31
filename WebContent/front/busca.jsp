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
			<form method="get" action="buscaDetalhada" class="form-buscar-clientes">
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
				<select name="categoria">
					<option value="">Categoria</option>
					<c:forEach var="categoria" items="${categorias}">
						<option value="${categoria.getId()}">
							${categoria.getNome()}
						</option>
					</c:forEach>
				</select>
				<input type="text" name="isbn" placeholder="ISBN">
				<input type="text" name="edicao" placeholder="Edição">
				<input type="number" name="ano" placeholder="Ano">				
				<input type="number" name="preco" placeholder="Preço" step="0.01">
				<input type="number" min="1" name="resultadosPorPagina" placeholder="Resultados por página" value="" >
				<button type="submit">Buscar</button>
			</form>
			<div class="search-content">
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