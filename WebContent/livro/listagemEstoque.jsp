<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Controle de estoque de Lorem ipsum</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>Controle de estoque de ${livro.getTitulo()}, de ${livro.getAutor().getNome()}</h1>
			<a href="/trabalho-les/cadastroEstoque?id=${livro.getId()}" cypress-cadastroEstoque>Cadastrar entrada</a>
			<br><br>
			<form method="get" action="#" class="form-buscar-clientes">
				<input type="hidden" name="id" value="${livro.getId()}">
				<input type="date" name="dataEntrada" placeholder="Data de Entrada" value="${campos[2].getValor()}">
				<select name="usuarioResponsavel">
					<option value="">Usuário Responsável</option>
					<c:forEach var="opt" items="${usuarios}">
						<option value="${opt.getId()}" ${opt.getId() == campos[1].getValor() ? 'selected=\"selected\"' : ''}>
							${opt.getNome()}
						</option>
					</c:forEach>
				</select>
				<select name="clienteResponsavel">
					<option value="">Cliente</option>
					<c:forEach var="opt" items="${clientes}">
						<option value="${opt.getId()}" ${opt.getId() == campos[6].getValor() ? 'selected=\"selected\"' : ''}>
							${opt.getNome()}
						</option>
					</c:forEach>
				</select>
				<select name="fornecedor">
					<option value="">Fornecedor</option>
					<c:forEach var="opt" items="${fornecedores}">
						<option value="${opt.getId()}" ${opt.getId() == campos[0].getValor() ? 'selected=\"selected\"' : ''}>
							${opt.getNome()}
						</option>
					</c:forEach>
				</select>
				<select name="tipoMovimentacao">
					<option value="">Tipo</option>
					<option value="1" ${1 == campos[3].getValor() ? 'selected=\"selected\"' : ''}>Entrada (cadastro no admin)</option>
					<option value="2" ${2 == campos[3].getValor() ? 'selected=\"selected\"' : ''}>Saída (venda)</option>
					<option value="3" ${3 == campos[3].getValor() ? 'selected=\"selected\"' : ''}>Entrada (troca)</option>
				</select>
				<input type="number" name="custo" placeholder="Custo" step="0.01" value="${campos[4].getValor()}">
				<input type="number" min="1" name="resultadosPorPagina" placeholder="Resultados por página" value="${resultadosPorPagina}" >
				<button type="submit">Buscar</button>
			</form>
			<c:if test = "${registros.size() > 0}">
			<table cellpadding="0" cellspacing="0" class="js-paginated-table listagem-livros" data-itensPorPagina="${resultadosPorPagina}">
				<thead>
					<tr>
						<th>Id</th>
						<th>Data de Entrada</th>
						<th>Quantidade</th>
						<th>Usuário Responsável</th>
						<th>Cliente Responsável</th>
						<th>Fornecedor</th>
						<th>Tipo</th>
						<th>Valor custo (unidade)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="entrada" items="${registros}">
						<tr>
							<td>${entrada.getId()}</td>
							<td><span class="js-date-value">${entrada.getDataEntrada()}</span></td>
							<td>${entrada.getQuantidade()}</td>
							<td>${entrada.getUsuarioResponsavel().getNome()}</td>
							<td>${entrada.getCliente().getNome()}</td>
							<td>${entrada.getFornecedor().getNome()}</td>
							<td>
								<c:if test = '${entrada.getTipoMovimentacao() == 1}'>
									Entrada (cadastro no admin)
								</c:if>
								<c:if test = '${entrada.getTipoMovimentacao() == 2}'>
									Saída (venda)
								</c:if>
								<c:if test = '${entrada.getTipoMovimentacao() == 3}'>
									Entrada (troca)
								</c:if>
							</td>
							<td><span class="js-dinheiro">${entrada.getCusto()}</span></td>
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