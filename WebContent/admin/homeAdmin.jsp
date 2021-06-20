<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Página inicial - Admin</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Página inicial - Admin</h1>
			<div class="admin-options-wrapper">
				<c:if test='${isGerenteVendas.equals("false")}'>
					<div class="admin-block">
						<h2>Clientes</h2>
						<a href="/trabalho-les/cadastroCliente" cypress-cadastroCliente>Cadastrar cliente</a>
						<br>
						<a href="/trabalho-les/listagemClientes" cypress-listagemClientes>Listagem de clientes</a>
					</div>
					<div class="admin-block">
						<h2>Usuários do admin</h2>
						<a href="/trabalho-les/cadastroUsuarioAdmin" cypress-cadastroUsuarioAdmin>Cadastrar usuário de admin</a>
						<br>
						<a href="/trabalho-les/listagemUsuariosAdmin" cypress-listagemUsuariosAdmin>Listagem de usuários de admin</a>
					</div>
				</c:if>
				<div class="admin-block">
					<h2>Livros</h2>
					<c:if test='${isGerenteVendas.equals("false")}'>
						<a href="/trabalho-les/cadastroLivro" cypress-cadastroLivro>Cadastrar livros</a>
						<br>
					</c:if>
					<a href="/trabalho-les/listagemLivros" cypress-listagemLivros>Listagem de livros</a>
					<c:if test='${isAdmin.equals("true")}'>
						<br>
						<a href="/trabalho-les/livrosPendentesInativacao" cypress-livrosPendentesInativacao>Livros pendentes de inativação</a>
						<br>
						<a href="/trabalho-les/livrosPendentesAtivacao" cypress-livrosPendentesAtivacao>Livros pendentes de ativação</a>
					</c:if>
				</div>
				<div class="admin-block">
					<h2>Cupons de desconto</h2>
					<a href="/trabalho-les/cadastroCupom" cypress-cadastroCupom>Cadastrar cupom</a>
					<br>
					<a href="/trabalho-les/listagemCupons" cypress-listagemCupons>Listagem de cupons</a>
				</div>
				<c:if test='${isGerenteVendas.equals("false")}'>
					<div class="admin-block">
						<h2>Pedidos</h2>
						<a href="/trabalho-les/todosPedidos" cypress-todosPedidos>Ver todos os pedidos</a>
						<br>
						<a href="/trabalho-les/listagemSolicitacoesTroca" cypress-listagemSolicitacoesTroca>Listagem de solicitações de troca</a>
					</div>

					<div class="admin-block">
						<h2>Fornecedores</h2>
						<a href="/trabalho-les/cadastroFornecedor" cypress-cadastroFornecedor>Cadastrar fornecedor</a>
						<br>
						<a href="/trabalho-les/listagemFornecedores" cypress-listagemFornecedor>Listagem de fornecedores</a>
					</div>
				</c:if>

				<div class="admin-block">
					<h2>Grupos de precificação</h2>
					<a href="/trabalho-les/cadastroGrupoPrecificacao" cypress-cadastroGrupoPrecificacao>Cadastrar grupos de precificação</a>
					<br>
					<a href="/trabalho-les/listagemGruposPrecificacao" cypress-listagemGruposPrecificacao>Listagem de grupos de precificação</a>
				</div>
			</div>
 			<c:if test='${isGerenteVendas.equals("false")}'>
				<div>
					<h2>Configurações</h2>
					<a href="/trabalho-les/configuracoes" cypress-configuracoes>Editar Configurações</a>
					<c:if test='${isGerenteVendas.equals("false")}'>
						<br>
						<a href="/trabalho-les/implantarDominio" onclick="alert('Tabelas de domínio implantadas com sucesso!')" title="Insere autores, editoras etc." cypress-implantarDominio>Implantar tabelas de domínio</a>
					</c:if>
				</div>
			</c:if>
			<h2>Gerar gráfico de vendas</h1>
			<!-- <canvas id="myChart" width="600" height="300"></canvas> -->
			<form  action="/trabalho-les/gerarGrafico" method="post" class="js-pristine-validation">
				<div class="form-group">
					<input type="date" name="dataInicio" placeholder="Data de início" required data-pristine-required-message="Este campo é obrigatório" cypress-dataInicio>
				</div>
				<div class="form-group">
					<input type="date" name="dataFim" placeholder="Data de fim" required data-pristine-required-message="Este campo é obrigatório" cypress-dataFim>
				</div>
				<div class="form-group">
					<select name="tipo" cypress-tipo>
						<option value="categoria">Por categoria</option>
						<option value="livro">Por livro</option>
					</select>
				</div>
				<button type="submit" cypress-submitGrafico>Gerar</button>
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
	<script type="text/javascript" src="assets/js/vendor/Chart.bundle.min.js"></script>
	<script type="text/javascript" src="assets/js/main.js"></script>
	<!--<script type="text/javascript">
		var ctx = document.getElementById('myChart');

		var myChart = new Chart(ctx, {
		    type: 'bar',
		    data: {
		        labels: ['Livro 1', 'Livro 2', 'Livro 3', 'Livro 4', 'Livro 5', 'Livro 6', 'Livro 7', 'Livro 8'],
		        datasets: [{
			        label: 'Vendas',
			        data: [42.4, 43.0, 43.0, 50.3, 49.4, 48.4, 51.2, 51.8],
			        backgroundColor: 'rgba(124, 181, 236, 0.9)',
			        borderColor: 'rgb(124, 181, 236)',
			        borderWidth: 1
			      }
			    ]
		    },
		    options: {
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero: true
		                }
		            }]
		        }
		    }
		});
	</script>-->
</body>
</html>