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
				<select name="sexo" >
					<option value="">Sexo</option>
					<option value="1" ${1 == campos[1].getValor() ? 'selected=\"selected\"' : ''}>Masculino</option>
					<option value="2" ${2 == campos[1].getValor() ? 'selected=\"selected\"' : ''}>Feminino</option>
				</select>
				<select name="tipoCliente">
					<option value="">Tipo de Cliente</option>
					<option value="1" ${1 == campos[3].getValor() ? 'selected=\"selected\"' : ''}>Comprador</option>
					<option value="2" ${2 == campos[3].getValor() ? 'selected=\"selected\"' : ''}>Revendedor</option>
					<option value="3" ${3 == campos[3].getValor() ? 'selected=\"selected\"' : ''}>Parceiro</option>
				</select>
				<input type="date" name="dataNascimento" placeholder="Data de nascimento" value="${campos[2].getValor()}" >
				<select name="tipoDocumento">
					<option value="">Tipo de Documento</option>
					<option value="1" ${1 == campos[5].getValor() ? 'selected=\"selected\"' : ''}>CPF</option>
					<option value="2" ${2 == campos[5].getValor() ? 'selected=\"selected\"' : ''}>CNPJ</option>
					<option value="3" ${3 == campos[5].getValor() ? 'selected=\"selected\"' : ''}>RG</option>
					<option value="4" ${4 == campos[5].getValor() ? 'selected=\"selected\"' : ''}>Social Security Card</option>
				</select>
				<input type="text" name="documento" placeholder="Documento" value="${campos[6].getValor()}" >
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
							<th>Data de cadastro</th>
							<th>Código único</th>
							<th>Nome</th>
							<th>E-mail</th>
							<th>Sexo</th>
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
									124576
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
								<td>${1 == registro.getSexo() ? 'Masculino' : 'Feminino'}</td>
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
									<a href="/trabalho-les/todosPedidos?cliente=${registro.getId()}" cypress-listagemPedidosAdmin>Consultar transações</a>
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
				<div class="pagination-wrapper">
					<c:forEach var="link" items="${linksPaginacao}" varStatus="loop">
						<a href="?${link}">${loop.index + 1}</a>
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