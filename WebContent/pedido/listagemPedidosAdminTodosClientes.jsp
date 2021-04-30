<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Todos os pedidos</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>Todos os pedidos</h1>
			<h2>Buscar pedidos</h2>
			<form method="get" action="#" class="form-buscar-clientes">
				<select name="idCliente">
					<option value="">Cliente</option>
					<c:forEach var="cliSel" items="${clientes}">
						<option value="${cliSel.getId()}">${cliSel.getNome()}</option>
					</c:forEach>
				</select>
				<input type="date" name="dataEntrada" placeholder="Data do pedido">
				<select name="status">
					<option value="">Status</option>
					<option value="1">Em processamento</option>
					<option value="2">Em trânsito</option>
					<option value="3">Entregue</option>
					<option value="4">Em troca</option>
					<option value="5">Troca autorizada</option>
				</select>
				<input type="number" min="1" name="resultadosPorPagina" placeholder="Resultados por página" value="" >
				<button type="submit">Buscar</button>
			</form>
			<table cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th>Id</th>
						<th>Cliente</th>
						<th>Data do pedido</th>
						<th>Valor total</th>
						<th>Valor frete</th>
						<th>Endereço de entrega</th>
						<th>Status</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>Fulano</td>
						<td>04/02/2021</td>
						<td>R$ 40,00</td>
						<td>R$ 2,50</td>
						<td>Rua teste, nº 60</td>
						<td>Em processamento (atualizado em 05/02/2021)</td>
						<td>
							<a href="/trabalho-les/pedido?id=33&admin" cypress-detalhesPedido>Ver detalhes</a>
							<br>
							<a href="/trabalho-les/alterarStatusPedido?id=33" cypress-despacho>Despachar para entrega</a>
						</td>
					</tr>
					<tr>
						<td>1</td>
						<td>Fulano</td>
						<td>04/02/2021</td>
						<td>R$ 40,00</td>
						<td>R$ 2,50</td>
						<td>Rua teste, nº 60</td>
						<td>Em trânsito (atualizado em 05/02/2021)</td>
						<td>
							<a href="/trabalho-les/pedido?id=33&admin">Ver detalhes</a>
							<br>
							<a href="/trabalho-les/alterarStatusPedido?id=33" cypress-entregaEfetuada>Entrega efetuada</a>
						</td>
					</tr>
					<tr>
						<td>1</td>
						<td>Fulano</td>
						<td>04/02/2021</td>
						<td>R$ 40,00</td>
						<td>R$ 2,50</td>
						<td>Rua teste, nº 60</td>
						<td>Entregue (atualizado em 05/02/2021)</td>
						<td>
							<a href="/trabalho-les/pedido?id=33&admin">Ver detalhes</a>
						</td>
					</tr>
					<tr>
						<td>1</td>
						<td>Fulano</td>
						<td>04/02/2021</td>
						<td>R$ 40,00</td>
						<td>R$ 2,50</td>
						<td>Rua teste, nº 60</td>
						<td>Em troca (atualizado em 05/02/2021)</td>
						<td>
							<a href="/trabalho-les/pedido?id=33&admin">Ver detalhes</a>
							<br>
							<a href="/trabalho-les/decidirPedidoTroca?id=33&autorizar=1" cypress-autorizarTroca>Autorizar troca</a>
							<br>
							<a href="/trabalho-les/decidirPedidoTroca?id=33&autorizar=0" cypress-recusarTroca>Recusar troca</a>
						</td>
					</tr>
					<tr>
						<td>1</td>
						<td>Fulano</td>
						<td>04/02/2021</td>
						<td>R$ 40,00</td>
						<td>R$ 2,50</td>
						<td>Rua teste, nº 60</td>
						<td>Troca autorizada (atualizado em 05/02/2021)</td>
						<td>
							<a href="/trabalho-les/pedido?id=33&admin">Ver detalhes</a>
							<br>
							<form action="/trabalho-les/confirmarRecebimentoTroca">
								<input type="hidden" name="id" value="1">
								<button type="submit" cypress-confirmarRecebimento>Confirmar recebimento</button>
								<input type="checkbox" name="retornarEstoque" cypress-retornarEstoque> Retornar itens ao estoque
							</form>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="pagination-wrapper">
				<a href="#"> < </a>
				<a href="#">1</a>
				<a href="#">2</a>
				<a href="#">3</a>
				...			
				<a href="#"> > </a>
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
</body>
</html>