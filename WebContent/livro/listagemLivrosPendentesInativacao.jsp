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
			<h2>Buscar livros</h2>
			<form method="get" action="#" class="form-buscar-clientes">
				<input type="text" name="titulo" placeholder="Título" >
				<select name="idUsuario">
					<option>Usuário responsável</option>
					<option value="1">Fulano</option>
					<option value="2">Cicrano</option>
					<option value="3">Beltrano</option>
				</select>
				<select name="categoriaInativacao">
					<option>Categoria de inativação</option>
					<option value="1">Categoria 1</option>
					<option value="2">Categoria 2</option>
				</select>
				<input type="number" min="1" name="resultadosPorPagina" placeholder="Resultados por página" value="" >
				<button type="submit">Buscar</button>
			</form>
			<table cellpadding="0" cellspacing="0" class="listagem-livros">
				<thead>
					<tr>
						<th>Id</th>
						<th>Capa</th>
						<th>Título</th>
						<th>Justificativa</th>
						<th>Categoria de inativação</th>
						<th>Usuário responsável</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td><img src="/trabalho-les/assets/images/produtos/livro-manual.jpg"></td>
						<td>Como Eu Cheguei Aqui?</td>
						<td>"Não gostei da capa do livro."</td>
						<td>Categoria 1</td>
						<td>Fulano</td>
						<td>
							<a href="/trabalho-les/concluirInativacao?aceite=1" cypress-aceitarInativacao>Aceitar inativação</a>
							<br>
							<a href="/trabalho-les/concluirInativacao?aceite=0" cypress-recusarInativacao>Recusar inativação</a>
						</td>
					</tr>
					<tr>
						<td>1</td>
						<td><img src="/trabalho-les/assets/images/produtos/livro-manual.jpg"></td>
						<td>Como Eu Cheguei Aqui?</td>
						<td>"Não fui com a cara do autor."</td>
						<td>Categoria 1</td>
						<td>Beltrano</td>
						<td>
							<a href="#">Aceitar inativação</a>
							<br>
							<a href="#">Recusar inativação</a>
						</td>
					</tr>
					<tr>
						<td>1</td>
						<td><img src="/trabalho-les/assets/images/produtos/livro-manual.jpg"></td>
						<td>Como Eu Cheguei Aqui?</td>
						<td>"Optamos por não trabalhar mais com esse tipo de livro."</td>
						<td>Categoria 1</td>
						<td>Cicrano</td>
						<td>
							<a href="#">Aceitar inativação</a>
							<br>
							<a href="#">Recusar inativação</a>
						</td>
					</tr>					
				</tbody>
			</table>
			<div class="pagination-wrapper">
				<a href="#"> < </a>
				<a href="#">1</a>
				<a href="#">2</a>
				<a href="#">3</a>
				...			
				<a href="#"> > </a>
			</div>
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
</body>
</html>