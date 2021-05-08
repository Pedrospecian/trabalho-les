<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Listagem de solicitações de troca</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Listagem de solicitações de troca</h1>
			<c:if test = "${registros.size() > 0}">
				<table cellspacing="0" cellpadding="0" class="js-paginated-table" data-itensPorPagina="10">
					<thead>
						<tr>
							<th>Id</th>
							<th>Produto</th>
							<th>Quantidade a ser trocada</th>
							<th>Valor</th>
							<th>Cliente</th>
							<th>Status</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="registro" items="${registros}">
							<tr>
								<td>${registro.getId()}</td>
								<td>
									<span class="js-string-value">
										${registro.getItemCarrinho().getLivro().getTitulo()}
									</span>
								</td>
								<td>${registro.getQuantidade()}</td>
								<td><span class="js-dinheiro">${registro.getItemCarrinho().getLivro().getPreco()}</span></td>
								<td>${registro.getItemCarrinho().getCliente().getNome()}</td>
								<td cypress-solicitacoestrocastatus>
									<c:if test = '${registro.getStatus() == 0}'>
										Troca solicitada. Aguardando decisão
									</c:if>
									<c:if test = '${registro.getStatus() == 1}'>
										Troca aceita. Aguardando confirmação de recebimento
									</c:if>
									<c:if test = '${registro.getStatus() == 2}'>
										Recebimento confirmado. Os itens não retornaram ao estoque
									</c:if>
									<c:if test = '${registro.getStatus() == 3}'>
										Recebimento confirmado. Os itens retornaram ao estoque
									</c:if>
									<c:if test = '${registro.getStatus() == 4}'>
										Troca recusada
									</c:if>
								</td>
								<td>
									<!--<a href="#" onClick="alert('Um cupom de troca para esse produto foi gerado.');window.location.reload;">Gerar cupom de troca</a>-->
									<c:if test = '${registro.getStatus() == 0}'>
										<a href="/trabalho-les/decidirPedidoTroca?id=${registro.getId()}&autorizar=1" cypress-autorizarTroca>Autorizar troca</a>
										<br>
										<a href="/trabalho-les/decidirPedidoTroca?id=${registro.getId()}&autorizar=0" cypress-recusarTroca>Recusar troca</a>
									</c:if>
									<c:if test = '${registro.getStatus() == 1}'>
										<form action="/trabalho-les/confirmarRecebimentoTroca">
											<input type="hidden" name="id" value="${registro.getId()}">
											<button type="submit" cypress-confirmarRecebimento>Confirmar recebimento</button>
											<input type="checkbox" name="retornarEstoque" cypress-retornarEstoque> Retornar itens ao estoque
										</form>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination-wrapper">
					<c:forEach var="link" items="${linksPaginacao}" varStatus="loop">
						<a href="?${link}">1</a>
					</c:forEach>
				</div>
				<div class="paginated-table-wrapper"></div>
				<div class="js-pagination-links"></div>
			</c:if>
			<c:if test = "${registros.size() <= 0}">
				<p>Não foi encontrado nenhum registro.</p>
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
	<script type="text/javascript" src="assets/js/main.js"></script>
	<script type="text/javascript" src="assets/js/pagination.js"></script>
</body>
</html>