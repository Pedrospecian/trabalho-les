<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Cadastro de livro</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>Cadastro de livro</h1>
			<form action="/trabalho-les/cadastroLivroAction" method="post" class="js-pristine-validation">
				<div class="form-group">
					<input type="text" name="titulo" placeholder="Título do Livro" required data-pristine-required-message="Este campo é obrigatório" cypress-titulo>
				</div>
				<div class="form-group">
					<input type="text" name="edicao" placeholder="Edição" required cypress-edicao>
				</div>
				<div class="form-group">
					<select name="autor" required data-pristine-required-message="Este campo é obrigatório" cypress-autor>
						<option value="">Autor</option>
						<c:forEach var="autor" items="${autores}">
							<option value="${autor.getId()}">
								${autor.getNome()}
							</option>
						</c:forEach>
						<!--<option value="1">Fulano</option>
						<option value="2">Beltrano</option>
						<option value="3">Cicrano</option>-->
					</select>
				</div>
				<div class="form-group">
					<select name="editora" required data-pristine-required-message="Este campo é obrigatório" cypress-editora>
						<option value="">Editora</option>
						<c:forEach var="editora" items="${editoras}">
							<option value="${editora.getId()}">
								${editora.getNome()}
							</option>
						</c:forEach>
						<!--<option value="1">Companhia das Letras</option>
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
						<option value="14">Brique Book</option>-->
					</select>
				</div>
				<label for="capa">Capa do livro</label>
				<br>
				<div class="form-group">
					<input type="file" name="capa" id="capa">
				</div>
				<h3>Categorias</h3>
				<div class="boxes-wrapper js-boxes-categorias">
					<input type="hidden" name="arrIdCategoria" class="js-arrIdCategoria">
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
					<input type="number" name="ano" placeholder="Ano" min="0" required data-pristine-required-message="Este campo é obrigatório" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-ano>
				</div>
				<div class="form-group">
					<input type="text" name="isbn" placeholder="ISBN" required data-pristine-required-message="Este campo é obrigatório" cypress-isbn>
				</div>
				<div class="form-group">
					<input type="number" name="numeroPaginas" placeholder="Número de Páginas" min="1" required data-pristine-required-message="Este campo é obrigatório" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-numeroPaginas>
				</div>
				<label for="sinopse">Sinopse</label>
				<div class="form-group">
					<textarea id="sinopse" name="sinopse" required data-pristine-required-message="Este campo é obrigatório" cypress-sinopse></textarea>
				</div>
				<div class="form-group">
					<input type="text" name="altura" placeholder="Altura" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-altura>
				</div>
				<div class="form-group">
					<input type="text" name="largura" placeholder="Largura" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-largura>
				</div>
				<div class="form-group">
					<input type="text" name="peso" placeholder="Peso" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-peso>
				</div>
				<div class="form-group">
					<input type="text" name="profundidade" placeholder="Profundidade" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-profundidade>
				</div>
				<div class="form-group">
					<input type="text" name="preco" placeholder="Preço de venda" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-preco>
				</div>
				<div class="form-group">
					<input type="text" name="codigoBarras" placeholder="Código de barras" required data-pristine-required-message="Este campo é obrigatório" cypress-codigoBarras>
				</div>
				<div class="form-group">
					<select name="status" required data-pristine-required-message="Este campo é obrigatório" cypress-status>
						<option value="">Status</option>
						<option value="1">Ativo</option>
						<option value="0">Inativo</option>
					</select>
				</div>
				<div class="form-group">
					<select name="grupoPrecificacao" required data-pristine-required-message="Este campo é obrigatório" cypress-grupoPrecificacao>
						<option value="">Grupo de precificação</option>
						<c:forEach var="grupoPrecificacao" items="${gruposPrecificacao}">
							<option value="${grupoPrecificacao.getId()}">
								${grupoPrecificacao.getNome()} (${grupoPrecificacao.getPorcentagem()}%)
							</option>
						</c:forEach>
					</select>
				</div>
				<button type="submit" cypress-submit>Cadastrar</button>
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