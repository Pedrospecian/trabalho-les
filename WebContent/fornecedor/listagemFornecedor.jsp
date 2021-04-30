<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Listagem de fornecedor</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Listagem de fornecedor</h1>
			<h2>Buscar fornecedores</h2>
			<form method="get" action="#" class="form-buscar-clientes">
				<input type="text" name="nome" placeholder="Nome">
				<input type="email" name="email" placeholder="E-mail">
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
							<th>E-mail</th>
							<th>Endereço</th>
							<th>Documento</th>
							<th>Status</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="registro" items="${registros}">
							<tr>
								<td>4</td>
								<td>
									Teste
								</td>
								<td>
									teste@teste.com
								</td>
								<td>
									Rua teste, nº 1
								</td>
								<td>
									11111111111 (CPF)
								</td>
								<td>Ativo</td>
								<td>
									<a href="/trabalho-les/editarFornecedor?id=4" cypress-editarFornecedor>Editar</a>
									<br>
									<a href="/trabalho-les/alterarFornecedorStatusAction?id=${registro.getId()}&status=${1 == registro.getStatus() ? '0' : '1'}" cypress-alteraStatusFornecedor>
										Inativar
									</a>
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
</body>
</html>