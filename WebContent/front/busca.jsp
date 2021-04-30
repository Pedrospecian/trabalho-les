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
					<option value="">Editora</option>
					<option value="1">Fulano</option>
					<option value="2">Beltrano</option>
					<option value="3">Cicrano</option>
				</select>
				<select name="editora">
					<option value="">Editora</option>
					<option value="1">Companhia das Letras</option>
					<option value="2">Aleph</option>
					<option value="3">Suma</option>
					<option value="4">Editora Intrínseca</option>
					<option value="5">Grupo Editorial Record</option>
					<option value="6">Editora Rocco</option>
					<option value="7">Globo Livros</option>
					<option value="8">Darkside Books</option>
					<option value="9">Harper Collins</option>
					<option value="10">Editora Arqueiro</option>
					<option value="11">Somos Educação</option>
					<option value="12">Editora FTD</option>
					<option value="13">Saraiva</option>
					<option value="14">Brinque Book</option>
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
						<li><a href="">Categoria 1</a></li>
						<li><a href="">Categoria 2</a></li>
						<li><a href="">Categoria 3</a></li>
						<li><a href="">Categoria 4</a></li>
						<li><a href="">Categoria 5</a></li>
						<li><a href="">Categoria 6</a></li>
					</ul>
				</div>
				<div class="search-results">
					<div class="books-wrapper">
						<div class="book-single">
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
						</div>
					</div>
				</div>
			</div>
			<div class="pagination-wrapper">
				<a href="#">1</a>
				<a href="#">2</a>
				<a href="#">3</a>
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