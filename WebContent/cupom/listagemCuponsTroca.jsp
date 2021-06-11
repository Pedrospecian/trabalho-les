<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Listagem de cupons de troca</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body ${cliente != null ? "class='body-front'" : ""}>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Listagem de cupons de troca</h1>
			<form method="get" action="#" class="form-buscar-clientes">
				<input type="hidden" name="id" value="${campos[4].getValor()}" >
				<input type="text" name="nome" placeholder="Nome" value="${campos[0].getValor()}" >
				<input type="number" name="valor" placeholder="Valor" step="0.01" min="0.01" value="${campos[1].getValor()}">
				<select name="status">
					<option value="">Status</option>
					<option value="1" ${1 == campos[2].getValor() ? 'selected=\"selected\"' : ''}>Usável</option>
					<option value="0" ${campos[2].getValor().equals("0") ? 'selected=\"selected\"' : ''}>Já usado</option>
				</select>
				<input type="number" min="1" name="resultadosPorPagina" placeholder="Resultados por página" value="${campos[3].getValor()}" >
				<button type="submit">Buscar</button>
			</form>
			<c:if test = "${registros.size() > 0}">
				<table cellspacing="0" cellpadding="0" class="js-paginated-table" data-itensPorPagina="${campos[3].getValor()}">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nome</th>
							<th>Valor</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="registro" items="${registros}">
							<tr>
								<td>${registro.getId()}</td>
								<td>
									<span class="js-string-value">
										${registro.getNome()}
									</span>
								</td>
								<td><span class="js-dinheiro">${registro.getValor()}</span></td>
								<td>
									<c:if test = "${registro.getPedido().getId() == null || registro.getPedido().getId() == 0}">
										Usável
									</c:if>
									<c:if test = "${registro.getPedido().getId() != null && registro.getPedido().getId() != 0}">
										Já usado (usado no 
										<a href="/trabalho-les/${linkPedido}?id=${registro.getPedido().getId()}">pedido #${registro.getPedido().getId()}</a>)
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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