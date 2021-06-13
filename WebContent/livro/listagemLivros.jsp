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
			<form method="get" action="#" class="form-buscar-clientes form-buscar-big">
				<input type="text" name="titulo" placeholder="Título" value="${campos[0].getValor()}">
				<input type="text" name="edicao" placeholder="Edição" value="${campos[6].getValor()}">
				<select name="autor">
					<option value="">Autor</option>
					<c:forEach var="opt" items="${autores}">
						<option value="${opt.getId()}" ${opt.getId() == campos[1].getValor() ? 'selected=\"selected\"' : ''}>
							${opt.getNome()}
						</option>
					</c:forEach>
				</select>
				<select name="editora">
					<option value="">Editora</option>
					<c:forEach var="opt" items="${editoras}">
						<option value="${opt.getId()}" ${opt.getId() == campos[2].getValor() ? 'selected=\"selected\"' : ''}>
							${opt.getNome()}
						</option>
					</c:forEach>
				</select>
				<input type="text" name="isbn" placeholder="ISBN" value="${campos[3].getValor()}">
				<input type="number" name="codigoBarras" placeholder="Código de barras" value="${campos[4].getValor()}">
				<select name="status">
					<option value="">Status</option>
					<option value="1" ${1 == campos[5].getValor() ? 'selected=\"selected\"' : ''}>Ativo</option>
					<option value="0" ${campos[5].getValor().equals("0") ? 'selected=\"selected\"' : ''}>Inativo</option>
					<option value="2" ${2 == campos[5].getValor() ? 'selected=\"selected\"' : ''}>Inativação pendente</option>
					<option value="3" ${3 == campos[5].getValor() ? 'selected=\"selected\"' : ''}>Ativação pendente</option>
					<option value="4" ${4 == campos[5].getValor() ? 'selected=\"selected\"' : ''}>Fora de mercado</option>
				</select>
				<input type="text" name="largura" placeholder="Largura" step="0.01" value="${campos[14].getValor()}">
				<input type="text" name="altura" placeholder="Altura" step="0.01" value="${campos[9].getValor()}">
				<input type="text" name="profundidade" placeholder="Profundidade" step="0.01" value="${campos[11].getValor()}">
				<input type="text" name="peso" placeholder="Peso" step="0.01" value="${campos[10].getValor()}">
				<input type="text" name="preco" placeholder="Preço de venda" step="0.01" value="${campos[12].getValor()}">
				<input type="text" name="numeroPaginas" placeholder="Número de páginas" value="${campos[8].getValor()}">
				<select name="grupoPrecificacao">
					<option value="">Grupo de Precificação</option>
					<c:forEach var="opt" items="${gruposPrecificacao}">
						<option value="${opt.getId()}" ${opt.getId() == campos[13].getValor() ? 'selected=\"selected\"' : ''}>
							${opt.getNome()}
						</option>
					</c:forEach>
				</select>
				<input type="number" min="1" name="resultadosPorPagina" placeholder="Resultados por página" value="${campos[15].getValor()}" >
				<button type="submit">Buscar</button>
			</form>
			<c:if test = "${registros.size() > 0}">
				<div class="listagem-livros">
					<table cellpadding="0" cellspacing="0" class=" js-paginated-table" data-itensPorPagina="${campos[15].getValor()}">
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
								<th>Preço ideal mínimo</th>
								<th>Itens no estoque</th>
								<th>Itens já vendidos</th>
								<th>Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="livro" items="${registros}">
								<tr data-id="${livro.getId()}">
									<td>${livro.getId()}</td>
									<td><img src="${livro.getCapa()}"></td>
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
									<td><span class="js-dinheiro">${livro.getPrecoCusto()}</span></td>
									<td>
										<span class='js-dinheiro ${livro.getPreco() < livro.getPrecoCusto() * (1 + (livro.getGrupoPrecificacao().getPorcentagem() / 100)) ? "precoAbaixo" : ""}'>
											${livro.getPrecoCusto() * (1 + (livro.getGrupoPrecificacao().getPorcentagem() / 100))}
										</span>
									</td>
									<td data-estoque>${livro.getEstoque()}</td>
									<td>${livro.getNumeroVendas()}</td>
									<td>
										<a href="/trabalho-les/editarLivro?id=${livro.getId()}" cypress-editarLivro>Editar</a>
										<c:if test='${isGerenteVendas.equals("false")}'>
											<br>
											<c:if test = "${livro.getStatus() == 1}">
												<a href="/trabalho-les/justificarInativacaoLivro?id=${livro.getId()}" cypress-justificarInativacaoLivro>Inativar</a>
											</c:if>
											<c:if test = "${livro.getStatus() == 0 || livro.getStatus() == 4}">
												<a href="/trabalho-les/justificarAtivacaoLivro?id=${livro.getId()}" cypress-justificarAtivacaoLivro>Ativar</a>
											</c:if>
											<br>
											<a href="/trabalho-les/listagemEstoque?id=${livro.getId()}" cypress-listagemEstoque>Controle de estoque</a>
										</c:if>
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