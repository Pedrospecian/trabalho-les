<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Detalhes do produto</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body class="body-front">
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>${registro.getTitulo()}</h1>
			<div class="product-details-wrapper">
				<div class="product-image-wrapper">
					<img src="${registro.getCapa()}" alt="${registro.getTitulo()}">
				</div>
				<div class="product-text-wrapper">
					<c:if test = '${estoque > 0}'>
						<div class="form-carrinho-wrapper">
							<form method="post" action="/trabalho-les/adicionarCarrinho" class="form-carrinho js-pristine-validation">
								<input type="hidden" name="id" value="${registro.getId()}" min="0">
								<div class="form-group">
									<input type="number" name="quantidade" placeholder="Quantidade" required data-pristine-required-message="Este campo é obrigatório" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-quantidade min="0" max="${estoque}">
								</div>
								<button type="submit" cypress-submit>Adicionar ao carrinho</button>
							</form>
						</div>
					</c:if>
					<c:if test = '${estoque <= 0}'>
						Produto indisponível devido à falta de estoque. :(
					</c:if>
					<div>
						<h2><strong>Preço:</strong> <span class="js-dinheiro">${registro.getPreco()}</span></h2>
					</div>
					<div>
						<h2>Dados sobre o livro</h2>
						<ul>
							<li><strong>Autor:</strong> ${registro.getAutor().getNome()}</li>
							<li><strong>Ano:</strong> ${registro.getAno()}</li>
							<li><strong>Editora:</strong> ${registro.getEditora().getNome()}</li>
							<li><strong>Edição:</strong> ${registro.getEdicao()}</li>
							<li><strong>ISBN:</strong> ${registro.getIsbn()}</li>
							<li><strong>Número de páginas:</strong> ${registro.getNumeroPaginas()}</li>
							<li>
								<strong>Dimensões:</strong>
								${registro.getAltura()}cm (altura),
								${registro.getLargura()}cm (largura),
								${registro.getProfundidade()}cm (profundidade),
								${registro.getPeso()}g (peso)
							</li>
							<li>
								<strong>Categorias:</strong> 
								<span class="detalhes-produto-categorias-wrapper">
									<c:forEach var="categoria" items="${registro.getCategorias()}">
										<span>${categoria.getNome()}</span>
									</c:forEach>
								</span>
							</li>
						</ul>
					</div>
					<h2>Sinopse</h2>
					<p>${registro.getSinopse()}</p>
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
	<script type="text/javascript" src="assets/js/vendor/pristine.min.js"></script>
	<script type="text/javascript" src="assets/js/main.js"></script>
	<script type="text/javascript" src="assets/js/fieldValidation.js"></script>
</body>
</html>