<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Listagem de grupos de precificação</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Listagem de grupos de precificação</h1>
			<h2>Buscar grupo de precificação</h2>
			<form method="get" action="#" class="form-buscar-clientes">
				<input type="text" name="nome" placeholder="Nome" value="${campos[0].getValor()}">
				<input type="number" min="1" name="porcentagem" placeholder="Porcentagem de lucro" step="0.01" value="${campos[1].getValor()}">
				<select name="status">
					<option value="" ${"" == campos[2].getValor() ? 'selected=\"selected\"' : ''}>Status</option>
					<option value="1" ${1 == campos[2].getValor() ? 'selected=\"selected\"' : ''}>Ativo</option>
					<option value="0" ${campos[2].getValor().equals("0") ? 'selected=\"selected\"' : ''}>Inativo</option>
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
							<th>Porcentagem de lucro</th>
							<th>Status</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="registro" items="${registros}">
							<tr>
								<td>${registro.getId()}</td>
								<td>
									${registro.getNome()}
								</td>
								<td>
									${registro.getPorcentagem()}%
								</td>
								<td>
									<c:if test = "${registro.getStatus() == 0}">
										Inativo
									</c:if>
									<c:if test = "${registro.getStatus() == 1}">
										Ativo
									</c:if>
								</td>
								<td>
									<a href="/trabalho-les/editarGrupoPrecificacao?id=${registro.getId()}" cypress-editarGrupoPrecificacao>Editar</a>
									<br>
									<a href="/trabalho-les/alterarGrupoPrecificacaoStatusAction?id=${registro.getId()}&status=${1 == registro.getStatus() ? '0' : '1'}" cypress-alterarStatusGrupoPrecificacao>
										<c:if test = "${registro.getStatus() == 0}">
											Ativar
										</c:if>
										<c:if test = "${registro.getStatus() == 1}">
											Inativar
										</c:if>
									</a>
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