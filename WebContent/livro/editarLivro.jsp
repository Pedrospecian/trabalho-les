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
			<form action="/trabalho-les/listagemLivros" method="post" class="js-pristine-validation">
				<input type="hidden" name="id" value="1">
				<div class="form-group">
					<input type="text" name="titulo" placeholder="Título do Livro" value="Teste" required data-pristine-required-message="Este campo é obrigatório" cypress-titulo>
				</div>
				<div class="form-group">
					<select name="autor" required data-pristine-required-message="Este campo é obrigatório" cypress-autor>
						<option value="">Autor</option>
						<option value="1">Fulano</option>
						<option value="2" selected>Beltrano</option>
						<option value="3">Cicrano</option>
					</select>
				</div>
				<div class="form-group">
					<select name="editora" required data-pristine-required-message="Este campo é obrigatório" cypress-editora>
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
						<option value="10" selected>Editora Arqueiro</option>
						<option value="11">Somos Educação</option>
						<option value="12">Editora FTD</option>
						<option value="13">Saraiva</option>
						<option value="14">Brique Book</option>
					</select>
				</div>
				<label for="capa">Capa do livro</label>
				<br>
				<div class="js-remove-capa-wrapper">
					<img src="/trabalho-les/assets/images/produtos/livro-manual.jpg">
					<a href="#" class="js-remove-capa">Remover capa</a>
				</div>
				<div class="js-muda-capa" style="display: none;">
					<input type="file" name="capa" id="capa">
				</div>
				<h3>Categorias</h3>
				<div class="boxes-wrapper js-boxes-categorias">
					<input type="hidden" name="arrIdCategoria" class="js-arrIdCategoria">
					<input type="hidden" name="removerCategorias" class="js-removerCategorias">
					<div class="box-single">
						<a href="#" class="button-close js-remove-existing-categoria" data-id="1">X</a>
		                <div class="info-wrapper">
		                  Técnicos e Profissionais
		                </div>
		            </div>
		            <div class="box-single">
						<a href="#" class="button-close js-remove-existing-categoria" data-id="2">X</a>
		                <div class="info-wrapper">
		                  Literatura
		                </div>
		            </div>
				</div>
				<select name="categoria" class="js-id-categoria">
					<option value="">Categoria</option>
					<option value="1">Literatura</option>
					<option value="2">Técnicos e Profissionais</option>
					<option value="3">Equilíbrio Pessoal</option>
					<option value="4">Periódicos</option>
					<option value="5">Literatura Infantil</option>
				</select>
				<button type="button" class="js-adicionar-categoria">Adicionar categoria</button>
				<br>
				<div class="form-group">
					<input type="number" name="ano" placeholder="Ano" min="0" value="2000" required data-pristine-required-message="Este campo é obrigatório" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-ano>
				</div>
				<div class="form-group">
					<input type="text" name="isbn" placeholder="ISBN" required data-pristine-required-message="Este campo é obrigatório" value="124546" cypress-isbn>
				</div>
				<div class="form-group">
					<input type="number" name="numeroPaginas" placeholder="Número de Páginas" required data-pristine-required-message="Este campo é obrigatório" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" min="1" value="400" cypress-numeroPaginas>
				</div>
				<label for="sinopse">Sinopse</label>
				<div class="form-group">
					<textarea id="sinopse" name="sinopse" required data-pristine-required-message="Este campo é obrigatório" cypress-sinopse>Lorem ipsum dolor sit amet.</textarea>
				</div>
				<div class="form-group">
					<input type="text" name="altura" placeholder="Altura" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" min="0" step="0.01" value="40.12" cypress-altura>
				</div>
				<div class="form-group">
					<input type="text" name="largura" placeholder="Largura" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" min="0" step="0.01" value="40.12" cypress-largura>
				</div>
				<div class="form-group">
					<input type="text" name="peso" placeholder="Peso" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" min="0" step="0.01" value="40.12" cypress-peso>
				</div>
				<div class="form-group">
					<input type="text" name="profundidade" placeholder="Profundidade" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" min="0" step="0.01" value="40.12" cypress-profundidade>
				</div>
				<div class="form-group">
					<input type="text" name="preco" placeholder="Preço de venda" required data-pristine-required-message="Este campo é obrigatório" min="0" step="0.01" pattern="/^[0-9]+((\.|,)[0-9]+)?$/" data-pristine-pattern-message="Este campo precisa ser um número válido" min="0" step="0.01" value="40.12" cypress-preco>
				</div>
				<div class="form-group">
					<input type="text" name="codigoBarras" placeholder="Código de barras" required data-pristine-required-message="Este campo é obrigatório" value="1245654766878" cypress-codigoBarras>
				</div>
				<div class="form-group">
					<select name="status" required data-pristine-required-message="Este campo é obrigatório" cypress-status>
						<option value="">Status</option>
						<option value="1" selected>Ativo</option>
						<option value="0">Inativo</option>
					</select>
				</div>
				<div class="form-group">
					<select name="grupoPrecificacao" required data-pristine-required-message="Este campo é obrigatório" cypress-grupoPrecificacao>
						<option value="">Grupo de precificação</option>
						<option value="1">Basic (15% de lucro)</option>
						<option value="2">Standard (30% de lucro)</option>
						<option value="3" selected>Premium (50% de lucro)</option>
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