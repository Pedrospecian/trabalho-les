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
				<input type="text" name="nome" placeholder="Nome" value="" >
				<input type="number" name="valor" placeholder="Valor" step="0.01" min="0.01" value="20.00">
				<select name="status">
					<option value="" ${"" == campos[4].getValor() ? 'selected=\"selected\"' : ''}>Status</option>
					<option value="1" ${1 == campos[4].getValor() ? 'selected=\"selected\"' : ''}>Ativo</option>
					<option value="0" ${campos[4].getValor().equals("0") ? 'selected=\"selected\"' : ''}>Inativo</option>
				</select>
				<input type="number" min="1" name="resultadosPorPagina" placeholder="Resultados por página" value="${campos[8].getValor()}" >
				<button type="submit">Buscar</button>
			</form>
			<c:if test = "${registros.size() > 0}">
				<table cellspacing="0" cellpadding="0">
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
										<a href="/trabalho-les/pedido?id=${registro.getPedido().getId()}">pedido #${registro.getPedido().getId()}</a>)
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<!--<c:if test = "${registros.size() <= 0}">
				<p>Não foi encontrado nenhum registro.</p>
			</c:if>-->
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