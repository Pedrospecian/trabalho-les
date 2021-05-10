<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Listagem de Livros</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>Listagem de livros</h1>
			<h2>Buscar livros</h2>
			<form method="get" action="#" class="form-buscar-clientes">
				<input type="text" name="titulo" placeholder="Título" >
				<select name="autor">
					<option value="">Autor</option>
					<option value="1">Fulano</option>
					<option value="2">Beltrano</option>
					<option value="3">Cicrano</option>
				</select>
				<select name="editora">
					<option value="">Editora</option>
					<option value="1">Companhia das Letras</option>
					<option value="2">Aleph</option>
					<option value="3">Suma</option>
					<option value="4">Editora Intrínseca</option>
					<option value="5">Grupo Editorial Record</option>
					<option value="6">Editora Rocco</option>
					<option value="7">Globo Livros</option>
					<option value="8">Darkside Books</option>
					<option value="9">Harper Collins</option>
					<option value="10">Editora Arqueiro</option>
					<option value="11">Somos Educação</option>
					<option value="12">Editora FTD</option>
					<option value="13">Saraiva</option>
					<option value="14">Brinque Book</option>
				</select>
				<input type="text" name="isbn" placeholder="ISBN">
				<input type="number" name="codigoBarras" placeholder="Código de barras">
				<select name="status">
					<option value="">Status</option>
					<option value="1">Ativo</option>
					<option value="0">Inativo</option>
					<option value="2">Fora de mercado</option>
				</select>
				<input type="number" min="1" name="resultadosPorPagina" placeholder="Resultados por página" value="" >
				<button type="submit">Buscar</button>
			</form>
			<c:if test = "${registros.size() > 0}">
				<div class="listagem-livros">
					<table cellpadding="0" cellspacing="0" class=" js-paginated-table" data-itensPorPagina="10">
						<thead>
							<tr>
								<th>Id</th>
								<th>Capa</th>
								<th>Título</th>
								<th>Autor</th>
								<th>Editora</th>
								<th>ISBN</th>
								<th>Código de barras</th>
								<th>Status</th>
								<th>Preço venda</th>
								<th>Preço custo</th>
								<th>Lucro</th>
								<th>Itens no estoque</th>
								<th>Itens já vendidos</th>
								<th>Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="livro" items="${registros}">
								<tr data-id="${livro.getId()}">
									<td>${livro.getId()}</td>
									<td><img src="/trabalho-les/assets/images/produtos/livro-manual.jpg"></td>
									<td>${livro.getTitulo()}</td>
									<td>${livro.getAutor().getNome()}</td>
									<td>${livro.getEditora().getNome()}</td>
									<td>${livro.getIsbn()}</td>
									<td>${livro.getCodigoBarras()}</td>
									<td>
										<c:if test = "${livro.getStatus() == 1}">
											Ativo
										</c:if>
										<c:if test = "${livro.getStatus() == 0}">
											Inativo
										</c:if>
										<c:if test = "${livro.getStatus() == 2}">
											Inativação pendente
										</c:if>
										<c:if test = "${livro.getStatus() == 3}">
											Ativação pendente
										</c:if>
										<c:if test = "${livro.getStatus() == 4}">
											Fora de mercado
										</c:if>
									</td>
									<td><span class="js-dinheiro">${livro.getPreco()}</span></td>
									<td>R$ 15,00</td>
									<td>13,333%</td>
									<td data-estoque>${livro.getEstoque()}</td>
									<td>${livro.getNumeroVendas()}</td>
									<td>
										<a href="/trabalho-les/editarLivro?id=${livro.getId()}" cypress-editarLivro>Editar</a>
										<br>										
										<c:if test = "${livro.getStatus() == 1}">
											<a href="/trabalho-les/justificarInativacaoLivro?id=${livro.getId()}" cypress-justificarInativacaoLivro>Inativar</a>
										</c:if>
										<c:if test = "${livro.getStatus() == 0}">
											<a href="/trabalho-les/justificarAtivacaoLivro?id=${livro.getId()}" cypress-justificarAtivacaoLivro>Ativar</a>
										</c:if>
										<br>							
										<a href="/trabalho-les/listagemEstoque?id=${livro.getId()}" cypress-listagemEstoque>Controle de estoque</a>
									</td>
								</tr>
							</c:forEach>
							<!--<tr>
								<td>1</td>
								<td><img src="/trabalho-les/assets/images/produtos/livro-manual.jpg"></td>
								<td>Como Eu Cheguei Aqui?</td>
								<td>PHILIP BUNTING</td>
								<td>Brinque Book</td>
								<td>9788574126340</td>
								<td>1695211049</td>
								<td>Ativo</td>
								<td>R$ 20,00</td>
								<td>R$ 15,00</td>
								<td>13,333%</td>
								<td>40</td>
								<td>10</td>
								<td>
									<a href="/trabalho-les/editarLivro?id=1" cypress-editarLivro>Editar</a>
									<br>
									<a href="/trabalho-les/justificarInativacaoLivro?id=1" cypress-justificarInativacaoLivro>Inativar</a>
									<br>							
									<a href="/trabalho-les/listagemEstoque?id=1" cypress-listagemEstoque>Controle de estoque</a>
								</td>
							</tr>
							<tr>
								<td>2</td>
								<td><img src="/trabalho-les/assets/images/produtos/livro-manual.jpg"></td>
								<td>Como Eu Cheguei Aqui?</td>
								<td>PHILIP BUNTING</td>
								<td>Brinque Book</td>
								<td>9788574126340</td>
								<td>1695211049</td>
								<td>Ativo</td>
								<td>R$ 20,00</td>
								<td>R$ 15,00</td>
								<td>13,333%</td>
								<td>40</td>
								<td>10</td>
								<td>
									<a href="/trabalho-les/editarLivro?id=1">Editar</a>
									<br>
									<a href="/trabalho-les/justificarInativacaoLivro?id=1">Inativar</a>
									<br>
									<a href="/trabalho-les/listagemEstoque?id=1">Controle de estoque</a>
								</td>
							</tr>
							<tr>
								<td>3</td>
								<td><img src="/trabalho-les/assets/images/produtos/livro-manual.jpg"></td>
								<td>Como Eu Cheguei Aqui?</td>
								<td>PHILIP BUNTING</td>
								<td>Brinque Book</td>
								<td>9788574126340</td>
								<td>1695211049</td>
								<td>Inativo</td>
								<td>R$ 20,00</td>
								<td>R$ 15,00</td>
								<td>13,333%</td>
								<td>40</td>
								<td>10</td>
								<td>
									<a href="#">Ativar</a>
									<br>
									<a href="/trabalho-les/editarLivro?id=1">Editar</a>
									<br>
									<a href="/trabalho-les/listagemEstoque?id=1">Controle de estoque</a>
								</td>
							</tr>
							<tr>
								<td>3</td>
								<td><img src="/trabalho-les/assets/images/produtos/livro-manual.jpg"></td>
								<td>Como Eu Cheguei Aqui?</td>
								<td>PHILIP BUNTING</td>
								<td>Brinque Book</td>
								<td>9788574126340</td>
								<td>1695211049</td>
								<td>Inativação pendente</td>
								<td>R$ 20,00</td>
								<td>R$ 15,00</td>
								<td>13,333%</td>
								<td>40</td>
								<td>10</td>
								<td>
									<a href="/trabalho-les/editarLivro?id=1">Editar</a>
									<br>
									<a href="/trabalho-les/concluirInativacao?aceite=0&urlRedir=listagemLivros" cypress-cancelarInativacao>Cancelar Inativação</a>
									<br>
									<a href="/trabalho-les/listagemEstoque?id=1">Controle de estoque</a>
								</td>
							</tr>
							<tr>
								<td>3</td>
								<td><img src="/trabalho-les/assets/images/produtos/livro-manual.jpg"></td>
								<td>Como Eu Cheguei Aqui?</td>
								<td>PHILIP BUNTING</td>
								<td>Brinque Book</td>
								<td>9788574126340</td>
								<td>1695211049</td>
								<td>Fora de mercado</td>
								<td>R$ 20,00</td>
								<td>R$ 15,00</td>
								<td>13,333%</td>
								<td>40</td>
								<td>10</td>
								<td>
									<a href="/trabalho-les/editarLivro?id=1">Editar</a>
									<br>
									<a href="#" cypress-ativarLivro>Ativar</a>
									<br>
									<a href="/trabalho-les/listagemEstoque?id=1">Controle de estoque</a>
								</td>
							</tr>-->

							
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