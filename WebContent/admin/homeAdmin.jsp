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
				<div class="admin-block">
					<h2>Livros</h2>
					<a href="/trabalho-les/cadastroLivro" cypress-cadastroLivro>Cadastrar livros</a>
					<br>
					<a href="/trabalho-les/listagemLivros" cypress-listagemLivros>Listagem de livros</a>
					<br>
					<a href="/trabalho-les/livrosPendentesInativacao" cypress-livrosPendentesInativacao>Livros pendentes de inativação</a>
				</div>
				<div class="admin-block">
					<h2>Cupons de desconto</h2>
					<a href="/trabalho-les/cadastroCupom" cypress-cadastroCupom>Cadastrar cupom</a>
					<br>
					<a href="/trabalho-les/listagemCupons" cypress-listagemCupons>Listagem de cupons</a>
				</div>
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

				<div class="admin-block">
					<h2>Grupos de precificação</h2>
					<a href="/trabalho-les/cadastroGrupoPrecificacao" cypress-cadastroGrupoPrecificacao>Cadastrar grupos de precificação</a>
					<br>
					<a href="/trabalho-les/listagemGruposPrecificacao" cypress-listagemGruposPrecificacao>Listagem de grupos de precificação</a>
				</div>
			</div>
 
			<div>
				<h2>Configurações</h2>
				<a href="/trabalho-les/configuracoes" cypress-configuracoes>Editar Configurações</a>
				<br>
				<a href="/trabalho-les/implantarDominio" onclick="alert('Tabelas de domínio implantadas com sucesso!')" title="Insere autores, editoras etc." cypress-implantarDominio>Implantar tabelas de domínio</a>
			</div>
			<h1>Gráficos de vendas</h1>
			<canvas id="myChart" width="600" height="300"></canvas>
			<form>
				<br><br>
				<select class="js-change-chart-view" name="month">
					<option value="1">Janeiro</option>
					<option value="2">Fevereiro</option>
					<option value="3">Março</option>
					<option value="4">Abril</option>
					<option value="5">Maio</option>
					<option value="6">Junho</option>
					<option value="7">Julho</option>
					<option value="8">Agosto</option>
					<option value="9">Setembro</option>
					<option value="10">Outubro</option>
					<option value="11">Novembro</option>
					<option value="12">Dezembro</option>
				</select>
				<select class="js-change-chart-view" name="">
					<option value="categoria">Por categoria</option>
					<option value="livro">Por livro</option>
				</select>
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
	<script type="text/javascript" src="assets/js/vendor/Chart.bundle.min.js"></script>
	<script type="text/javascript" src="assets/js/main.js"></script>
	<script type="text/javascript">
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
	</script>
</body>
</html>