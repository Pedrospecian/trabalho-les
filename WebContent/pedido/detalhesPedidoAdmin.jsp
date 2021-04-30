<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Meus pedidos</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>Pedido #124</h1>
			Status do pedido: Em trânsito (atualizado em 12/02/2020)
			<br>
			<h3>Valor frete: R$ 12,00</h3>
			<h2>Produtos</h2>
			<table class="carrinho-itens" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th>Capa</th>
						<th>Título</th>
						<th>Preço</th>
						<th>Quantidade</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><img src="assets\images\produtos\livro-ficcao.jpg" alt="Alice no país das maravilhas"></td>
						<td>Alice no país das maravilhas</td>
						<td>R$ 40,00</td>
						<td>12</td>
						<td>
							Normal
						</td>
					</tr>
					<tr>
						<td><img src="assets\images\produtos\livro-ficcao.jpg" alt="Alice no país das maravilhas"></td>
						<td>Alice no país das maravilhas</td>
						<td>R$ 40,00</td>
						<td>4</td>
						<td>
							Em troca (2 unidades -> <span class="troca-onus">-R$ 80,00</span>)
						</td>
					</tr>
					<tr>
						<td><img src="assets\images\produtos\livro-ficcao.jpg" alt="Alice no país das maravilhas"></td>
						<td>Alice no país das maravilhas</td>
						<td>R$ 40,00</td>
						<td>1</td>
						<td>
							Em troca (1 unidade -> <span class="troca-onus">-R$ 40,00</span>)
						</td>
					</tr>
				</tbody>
			</table>
			<h2>Total: R$ 180,00</h2>
			<h1>Total com frete: R$ 200,99</h1>
			<hr>
			<div>
				<h2>Endereço de entrega:</h2>
				teste
				<br><br>
				Tipo de residência: Apartamento
				<br>
				Função do endereço: Cobrança
				<br><br>
				Endereço residencial
				<br><br>
				11111-111
				<br><br>
				teste (tipo do logradouro: Rua), n° 12
				<br>
				teste, teste - SP - Brasil
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