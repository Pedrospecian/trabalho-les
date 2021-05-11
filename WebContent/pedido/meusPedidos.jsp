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
<body class="body-front">
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>Meus Pedidos</h1>
			<table cellspacing="0" cellpadding="0" class="js-paginated-table" data-itensPorPagina="10">
				<!--<thead>
					<tr>
						<th>Id</th>
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
						<td>04/02/2021</td>
						<td>R$ 40,00</td>
						<td>R$ 2,50</td>
						<td>Rua teste, nº 60</td>
						<td>Em trânsito (atualizado em 05/02/2021)</td>
						<td>
							<a href="/trabalho-les/pedido?id=33" cypress-detalhesPedido>Ver detalhes</a>
						</td>
					</tr>
					<tr>
						<td>1</td>
						<td>04/02/2021</td>
						<td>R$ 40,00</td>
						<td>R$ 2,50</td>
						<td>Rua teste, nº 60</td>
						<td>Em trânsito (atualizado em 05/02/2021)</td>
						<td>
							<a href="/trabalho-les/pedido?id=33">Ver detalhes</a>
						</td>
					</tr>

					<tr>
						<td>1</td>
						<td>04/02/2021</td>
						<td>R$ 40,00</td>
						<td>R$ 2,50</td>
						<td>Rua teste, nº 60</td>
						<td>Entregue (atualizado em 05/02/2021)</td>
						<td>
							<a href="/trabalho-les/pedido?id=33">Ver detalhes</a>
						</td>
					</tr>
				</tbody>-->
				<thead>
					<tr>
						<th>Id</th>
						<th>Data do pedido</th>
						<th>Valor total</th>
						<th>Valor frete</th>
						<th>Endereço de entrega</th>
						<th>Prazo estimado</th>
						<th>Status</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="registro" items="${registros}">
					<tr data-status="${registro.getStatus()}">
						<td>${registro.getId()}</td>
						<td>
							<span class="js-date-value">
								${registro.getDataCadastro()}
							</span>
						</td>
						<td><span class="js-dinheiro">${registro.getValorTotal()}</span></td>
						<td><span class="js-dinheiro">${registro.getValorFrete()}</span></td>
						<td>Rua teste, nº 60</td>
						<td>
							${registro.getPrazo()} dias úteis
							(Entrega via 
								<c:if test = '${registro.getTipoServico().equals("04014")}'>
									SEDEX)
								</c:if>							
								<c:if test = '${registro.getTipoServico().equals("04510")}'>
									PAC)
								</c:if>
						</td>
						<td cypress-statusPedido>
							<c:if test = '${registro.getStatus() == 1}'>
								Em processamento (atualizado em 05/02/2021)
							</c:if>							
							<c:if test = '${registro.getStatus() == 2}'>
								Aceito (atualizado em 05/02/2021)
							</c:if>
							<c:if test = '${registro.getStatus() == 3}'>
								Em trânsito (atualizado em 05/02/2021)
							</c:if>
							<c:if test = '${registro.getStatus() == 4}'>
								Entregue (atualizado em 05/02/2021)
							</c:if>
							<c:if test = '${registro.getStatus() == 5}'>
								Em troca (atualizado em 05/02/2021)
							</c:if>
							<c:if test = '${registro.getStatus() == 6}'>
								Troca autorizada (atualizado em 05/02/2021)
							</c:if>
							<c:if test = '${registro.getStatus() == 7}'>
								Trocado (atualizado em 05/02/2021)
							</c:if>
							<c:if test = '${registro.getStatus() == 8}'>
								Reprovado (atualizado em 05/02/2021)
							</c:if>
						</td>
						<td>
							<a href="/trabalho-les/pedido?id=${registro.getId()}" cypress-detalhes-pedido>Ver detalhes</a>						
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="paginated-table-wrapper"></div>
			<div class="js-pagination-links"></div>
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
	<script type="text/javascript" src="assets/js/pagination.js"></script>
</body>
</html>