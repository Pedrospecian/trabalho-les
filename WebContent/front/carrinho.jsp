<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Carrinho</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body class="body-front">
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Carrinho</h1>
			<c:if test = '${carrinho != null && carrinho.getItensCarrinho() != null}'>
				<c:if test = '${carrinho.getStatus() == 1}'>
					<div class="js-carrinho-ativo">
						<!-- <form class="js-pristine-validation form-cart" action="/trabalho-les/checkout"> -->
							<table class="carrinho-itens" cellpadding="0" cellspacing="0">
								<c:forEach var="item" items="${carrinho.getItensCarrinho()}">
									<tr>
										<td><img src="${item.getLivro().getCapa()}" alt="${item.getLivro().getTitulo()}"></td>
										<td>${item.getLivro().getTitulo()}</td>
										<td>
											Preço unitário: <span class="js-dinheiro">${item.getLivro().getPreco()}</span>
											<br>
											Preço total: <span class="js-dinheiro">${item.getLivro().getPreco() * item.getQuantidade()}</span>
										</td>
										<td>
											<div class="form-group">
												<input type="number" name="quantidade" min="0" max="${item.getLivro().getEstoque()}" placeholder="Quantidade" required data-pristine-required-message="Este campo é obrigatório" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" value="${item.getQuantidade()}" cypress-quantidade data-id="${item.getId()}" class="js-quantidade">
											</div>
										</td>
										<td><a href="/trabalho-les/removerItemCarrinho?id=${item.getId()}" cypress-remover>Remover</a></td>
									</tr>
								</c:forEach>
							</table>				
							<h1>Total: <span class="js-dinheiro">${valorTotal}</span></h1>
							<a class="button-finalizar" href="/trabalho-les/checkout" cypress-finalizar>Checkout</a>
						<!-- </form> -->
					</div>
				</c:if>
				<c:if test = '${carrinho.getStatus() == 0}'>
					<div class="js-carrinho-inativo">
						<h2>Este carrinho foi desativado por ter levado tempo demais para finalizar a compra. Para comprar esses produtos, insira-os em um novo carrinho. Isso irá apagar o carrinho atual.</h2>
						<table class="carrinho-itens cancelado" cellpadding="0" cellspacing="0">
							<c:forEach var="item" items="${carrinho.getItensCarrinho()}">
								<tr>
									<td><img src="${item.getLivro().getCapa()}" alt="${item.getLivro().getTitulo()}"></td>
									<td>${item.getLivro().getTitulo()}</td>
									<td>
										Preço unitário: <span class="js-dinheiro">${item.getLivro().getPreco()}</span>
										<br>
										Preço total: <span class="js-dinheiro">${item.getLivro().getPreco() * item.getQuantidade()}</span>
									</td>
									<td>
										<div class="form-group">
											<input type="number" name="quantidade" min="0" placeholder="Quantidade" required data-pristine-required-message="Este campo é obrigatório" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" value="${item.getQuantidade()}" cypress-quantidade data-id="${item.getId()}" class="js-quantidade" disabled>
										</div>
									</td>
									<td><a href="/trabalho-les/removerItemCarrinho?id=${item.getId()}" cypress-remover>Remover</a></td>
								</tr>
							</c:forEach>
						</table>
						<h1 class="cancelado">Total: <span class="js-dinheiro">${valorTotal}</span></h1>
					</div>
				</c:if>
			</c:if>
			<c:if test = '${carrinho == null || carrinho.getItensCarrinho() == null}'>
				Este carrinho está vazio.
			</c:if>
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
	<script>
		var quantidades = document.querySelectorAll('.js-quantidade');
		for (var i = 0; i<quantidades.length;i++) {
			quantidades[i].addEventListener('change', function(e){
				window.location.href = "/trabalho-les/alteraQteItemCarrinho?id=" + e.target.attributes['data-id'].value + "&quantidade=" + e.target.value;
			});
		}
	</script>
</body>
</html>