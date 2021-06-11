<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Editar livro</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>Editar livro</h1>
			<form action="/trabalho-les/alterarLivroAction" method="post" class="js-pristine-validation">
				<input type="hidden" name="id" value="${livro.getId()}">
				<div class="form-group">
					<input type="text" name="titulo" placeholder="Título do Livro" value="${livro.getTitulo()}" required data-pristine-required-message="Este campo é obrigatório" cypress-titulo>
				</div>
				<div class="form-group">
					<input type="text" name="edicao" placeholder="Edição" value="${livro.getEdicao()}" required cypress-edicao>
				</div>
				<div class="form-group">
					<select name="autor" required data-pristine-required-message="Este campo é obrigatório" cypress-autor>
						<option value="">Autor</option>
						<c:forEach var="autor" items="${autores}">
							<option
								value="${autor.getId()}"
								${autor.getId() == livro.getAutor().getId() ? 'selected=\"selected\"' : ''}
							>
								${autor.getNome()}
							</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<select name="editora" required data-pristine-required-message="Este campo é obrigatório" cypress-editora>
						<option value="">Editora</option>
						<c:forEach var="editora" items="${editoras}">
							<option
								value="${editora.getId()}"
								${editora.getId() == livro.getEditora().getId() ? 'selected=\"selected\"' : ''}
							>
								${editora.getNome()}
							</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<input type="text" name="capa" placeholder="URL da capa do livro" value="${livro.getCapa()}" cypress-capa>
					<img src="${livro.getCapa()}">
				</div>
				<h3>Categorias</h3>
				<div class="boxes-wrapper js-boxes-categorias">
					<input type="hidden" name="arrIdCategoria" class="js-arrIdCategoria">
					<input type="hidden" name="removerCategorias" class="js-removerCategorias">
					
					<c:forEach var="categoria" items="${livro.getCategorias()}">
						<div class="box-single">
							<a href="#" class="button-close js-remove-existing-categoria" data-id="${categoria.getId()}">X</a>
							<div class="info-wrapper">
								${categoria.getNome()}
							</div>
						</div>
					</c:forEach>

				</div>
				<select name="categoria" class="js-id-categoria">
					<option value="">Categoria</option>
					<c:forEach var="categoria" items="${categorias}">
						<option value="${categoria.getId()}">
							${categoria.getNome()}
						</option>
					</c:forEach>
				</select>
				<button type="button" class="js-adicionar-categoria">Adicionar categoria</button>
				<br>
				<div class="form-group">
					<input type="number" name="ano" placeholder="Ano" min="0" value="${livro.getAno()}" required data-pristine-required-message="Este campo é obrigatório" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-ano>
				</div>
				<div class="form-group">
					<input type="text" name="isbn" placeholder="ISBN" required data-pristine-required-message="Este campo é obrigatório" value="${livro.getIsbn()}" cypress-isbn>
				</div>
				<div class="form-group">
					<input type="number" name="numeroPaginas" placeholder="Número de Páginas" required data-pristine-required-message="Este campo é obrigatório" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" min="1" value="${livro.getNumeroPaginas()}" cypress-numeroPaginas>
				</div>
				<label for="sinopse">Sinopse</label>
				<div class="form-group">
					<textarea id="sinopse" name="sinopse" required data-pristine-required-message="Este campo é obrigatório" cypress-sinopse>${livro.getSinopse()}</textarea>
				</div>
				<div class="form-group">
					<input type="text" name="altura" placeholder="Altura" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" min="0" step="0.01" value="${livro.getAltura()}" cypress-altura>
				</div>
				<div class="form-group">
					<input type="text" name="largura" placeholder="Largura" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" min="0" step="0.01" value="${livro.getLargura()}" cypress-largura>
				</div>
				<div class="form-group">
					<input type="text" name="peso" placeholder="Peso" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" min="0" step="0.01" value="${livro.getPeso()}" cypress-peso>
				</div>
				<div class="form-group">
					<input type="text" name="profundidade" placeholder="Profundidade" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" min="0" step="0.01" value="${livro.getProfundidade()}" cypress-profundidade>
				</div>
				<div class="form-group">
					<label>Preço</label>
					<input type="text" name="preco" placeholder="Preço de venda" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" min="0" step="0.01" value="${livro.getPreco()}" cypress-preco>
				</div>
				<div class="form-group">
					<input type="text" name="codigoBarras" placeholder="Código de barras" required data-pristine-required-message="Este campo é obrigatório" value="${livro.getCodigoBarras()}" cypress-codigoBarras>
				</div>
				<div class="form-group">
					<select name="grupoPrecificacao" required data-pristine-required-message="Este campo é obrigatório" cypress-grupoPrecificacao>
						<option value="">Grupo de precificação</option>
						<c:forEach var="grupoPrecificacao" items="${gruposPrecificacao}">
							<option
								value="${grupoPrecificacao.getId()}"
								${grupoPrecificacao.getId() == livro.getGrupoPrecificacao().getId() ? 'selected=\"selected\"' : ''}
							>
								${grupoPrecificacao.getNome()} (${grupoPrecificacao.getPorcentagem()}%)
							</option>
						</c:forEach>
					</select>
				</div>
				
				<button type="submit" cypress-submit>Editar</button>
			</form>
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
	<script type="text/javascript" src="assets/js/vendor/pristine.min.js"></script>
	<script type="text/javascript" src="assets/js/main.js"></script>
	<script type="text/javascript" src="assets/js/fieldValidation.js"></script>
</body>
</html>