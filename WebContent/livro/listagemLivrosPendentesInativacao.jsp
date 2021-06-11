<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Listagem de Livros pendentes para inativação</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>Listagem de livros pendentes para inativação</h1>
			<h2>Buscar solicitações de inativação</h2>
			<form method="get" action="#" class="form-buscar-clientes">
				<input type="text" name="titulo" placeholder="Título" value="${campos[0].getValor()}">
				<select name="idUsuario">
					<option value="">Usuário responsável</option>
					<c:forEach var="opt" items="${usuarios}">
						<option value="${opt.getId()}" ${opt.getId() == campos[2].getValor() ? 'selected=\"selected\"' : ''}>
							${opt.getNome()}
						</option>
					</c:forEach>
				</select>
				<select name="categoria">
					<option value="">Categoria de inativação</option>
					<c:forEach var="opt" items="${categorias}">
						<option value="${opt.getId()}" ${opt.getId() == campos[1].getValor() ? 'selected=\"selected\"' : ''}>
							${opt.getNome()}
						</option>
					</c:forEach>
				</select>
				<input type="number" min="1" name="resultadosPorPagina" placeholder="Resultados por página" value="${campos[3].getValor()}">
				<button type="submit">Buscar</button>
			</form>
			<c:if test = "${registros.size() > 0}">
				<div class="listagem-livros">
					<table cellpadding="0" cellspacing="0" class="listagem-livros js-paginated-table" data-itensPorPagina="${campos[3].getValor()}">
						<thead>
							<tr>
								<th>Id</th>
								<th>Capa</th>
								<th>Título</th>
								<th>Autor</th>
								<th>Categoria de inativação</th>
								<th>Justificativa</th>
								<th>Usuário responsável</th>
								<th>Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="solicitacao" items="${registros}">
								<tr>
									<td>${solicitacao.getId()}</td>
									<td><img src="${solicitacao.getLivro().getCapa()}"></td>
									<td>${solicitacao.getLivro().getTitulo()}</td>
									<td>${solicitacao.getLivro().getAutor().getNome()}</td>
									<td>${solicitacao.getCategoria().getNome()}</td>
									<td>${solicitacao.getJustificativa()}</td>
									<td>${solicitacao.getUsuario().getNome()}</td>
									<td>
										<a href="/trabalho-les/concluirInativacao?id=${solicitacao.getLivro().getId()}&aceite=1" cypress-aceitarInativacao>Aceitar inativação</a>
										<br>
										<a href="/trabalho-les/concluirInativacao?id=${solicitacao.getLivro().getId()}&aceite=0" cypress-recusarInativacao>Recusar inativação</a>
									</td>
								</tr>
							</c:forEach>					
						</tbody>
					</table>
					<div class="paginated-table-wrapper"></div>
					<div class="js-pagination-links"></div>
				</div>
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