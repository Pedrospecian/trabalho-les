<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Listagem de clientes</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>

	<main>
    	<div class="container">
			<h1>Listagem de clientes</h1>
			<h2>Buscar clientes</h2>
			<form method="get" action="#" class="form-buscar-clientes">
				<input type="text" name="nome" placeholder="Nome" value="${campos[0].getValor()}" >
				<select name="genero" >
					<option value="">Gênero</option>
					<c:forEach var="opt" items="${generos}">
						<option value="${opt.getId()}" ${opt.getId() == campos[1].getValor() ? 'selected=\"selected\"' : ''}>
							${opt.getNome()}
						</option>
					</c:forEach>
				</select>
				<select name="tipoCliente">
					<option value="">Tipo de Cliente</option>
					<c:forEach var="opt" items="${tiposcliente}">
						<option value="${opt.getId()}" ${opt.getId() == campos[3].getValor() ? 'selected=\"selected\"' : ''}>
							${opt.getNome()}
						</option>
					</c:forEach>
				</select>
				<input type="date" name="dataNascimento" placeholder="Data de nascimento" value="${campos[2].getValor()}" >
				<select name="tipoDocumento">
					<option value="">Tipo de Documento</option>
					<c:forEach var="opt" items="${tiposdocumento}">
						<option value="${opt.getId()}" ${opt.getId() == campos[5].getValor() ? 'selected=\"selected\"' : ''}>
							${opt.getNome()}
						</option>
					</c:forEach>
				</select>
				<input type="text" name="documento" placeholder="Documento" value="${campos[6].getValor()}" >
				<select name="status">
					<option value="">Status</option>
					<option value="1" ${1 == campos[4].getValor() ? 'selected=\"selected\"' : ''}>Ativo</option>
					<option value="0" ${campos[4].getValor().equals("0") ? 'selected=\"selected\"' : ''}>Inativo</option>
				</select>
				<input type="number" min="1" name="resultadosPorPagina" placeholder="Resultados por página" value="${campos[7].getValor()}" >
				<button type="submit">Buscar</button>
			</form>
			<c:if test = "${registros.size() > 0}">
				<table cellspacing="0" cellpadding="0" class="js-paginated-table" data-itensPorPagina="${campos[7].getValor()}">
					<thead>
						<tr>
							<th>Id</th>
							<th>Data de cadastro</th>
							<th>Nome</th>
							<th>E-mail</th>
							<th>Gênero</th>
							<th>Data de nascimento</th>
							<th>Tipo de cliente</th>
							<th>Perfil de compra</th>
							<th>Status</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="registro" items="${registros}">
							<tr cypress-clienteSingle>
								<td>${registro.getId()}</td>
								<td>
									<span class="js-date-value">
										${registro.getDataCadastro()}
									</span>
								</td>
								<td>
									<span class="js-string-value">
										${registro.getNome()}
									</span>
								</td>
								<td>
									<span class="js-string-value">
										${registro.getEmail()}
									</span>
								</td>
								<td>${1 == registro.getGenero().getId() ? 'Masculino' : 'Feminino'}</td>
								<td>
									<span class="js-date-value">
										${registro.getDataNascimento()}
									</span>
								</td>
								<td>${registro.getTipoCliente().getNome()}</td>
								<td>
									<c:if test = '${registro.getTotalPedidos() < 3}'>
										Iniciante 
									</c:if>
									<c:if test = '${registro.getTotalPedidos() >= 3 && registro.getTotalPedidos() < 10}'>
										Gostou da loja
									</c:if>
									<c:if test = '${registro.getTotalPedidos() >= 10 && registro.getTotalPedidos() < 20}'>
										Comprador dedicado
									</c:if>
									<c:if test = '${registro.getTotalPedidos() >= 20 && registro.getTotalPedidos() < 50}'>
										Fiel
									</c:if>
									<c:if test = '${registro.getTotalPedidos() >= 50}'>
										Grande fã
									</c:if>
									(${registro.getTotalPedidos()} compras)
								</td>
								<td>${1 == registro.getStatus() ? 'Ativo' : 'Inativo'}</td>
								<td>
									<a href="/trabalho-les/todosPedidos?idCliente=${registro.getId()}" cypress-listagemPedidosAdmin>Consultar transações</a>
									<br>
									<a href="/trabalho-les/listagemCuponsTroca?id=${registro.getId()}" cypress-listagemCuponsTroca>Cupons de troca</a>
									<br>
									<a href="/trabalho-les/editarCliente?id=${registro.getId()}" cypress-editarCliente>Editar</a>
									<br>
									<a href="/trabalho-les/alterarSenhaCliente?id=${registro.getId()}" cypress-alterarSenha>Alterar senha</a>
									<br>
									<a href="/trabalho-les/alterarClienteStatusAction?id=${registro.getId()}&status=${1 == registro.getStatus() ? '0' : '1'}" cypress-alterarStatusCliente>
										${1 == registro.getStatus() ? 'Inativar' : 'Ativar'}
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