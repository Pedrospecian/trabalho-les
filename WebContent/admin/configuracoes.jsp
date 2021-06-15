<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Configurações</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<div class="configuracoes-d-flex">
				<h1>Configurações</h1>
				<a href="/trabalho-les/resetarConfiguracoes" class="button-finalizar" cypress-restaurarPadrao>Restaurar padrão</a>
			</div>
			<form action="/trabalho-les/alterarConfiguracoes" class="js-pristine-validation">
				<!-- O sistema deve inativar livros sem estoque
				e que não possuem venda com valor inferior a parâmetro predefinido no sistema -->
				Número mínimo de vendas que um livro precisa ter após sua ativação
				<div class="form-group">
					<input type="number" name="numerosVendaInativacaoAutomatica" min="1" required data-pristine-required-message="Este campo é obrigatório" data-pristine-number-message="Esse campo precisa ser um número válido" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-numerosVendaInativacaoAutomatica value="${registros[0].getValor()}">
				</div>
				Prazo (em dias) para inativar livros que não alcançarem o número mínimo de vendas
				<div class="form-group">
					<input type="number" name="diasInativacaoAutomatica" min="1" required data-pristine-required-message="Este campo é obrigatório" data-pristine-number-message="Esse campo precisa ser um número válido" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-diasInativacaoAutomatica value="${registros[1].getValor()}">
				</div>
				<!-- Deve ser apresentado na listagem de itens do carrinho os produtos removidos por atingirem o prazo determinado para finalização da compra (apresentar o tempo conforme parâmetro do sistema). Assim a opção comprar deve ser desabilitada e o itens deverão ser adicionados novamente no carrinho. -->
				Tempo máximo de permanência de carrinho (em dias)
				<div class="form-group">
					<input type="number" name="diasPermanenciaCarrinho" min="1" required data-pristine-required-message="Este campo é obrigatório" data-pristine-number-message="Esse campo precisa ser um número válido" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-diasPermanenciaCarrinho value="${registros[2].getValor()}">
				</div>
				<!-- Ao adicionar o item no carrinho, este deverá ser temporiamente bloqueado para que novas compras não sejam solicitadas. Tal bloqueio só deve ser retirado no caso da compra que gerou tal status não ser efetivada ou aprovada em um prazo parametrizado, o prazo deve levar em consideração o momento do bloqueio. Obs.: O prazo parametrizado deve ser relativo ao último item incluído no carrinho -->
				Prazo para remoção de bloqueio de item adicionado no carrinho (em dias)
				<div class="form-group">
					<input type="number" name="diasPermanenciaBloqueioItemCarrinho" min="1" required data-pristine-required-message="Este campo é obrigatório" data-pristine-number-message="Esse campo precisa ser um número válido" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-diasPermanenciaBloqueioItemCarrinho value="${registros[3].getValor()}">
				</div>
				CEP do endereço de origem (apenas números)
				<div class="form-group">
					<input type="number" name="cepOrigem" min="1" required data-pristine-required-message="Este campo é obrigatório" data-pristine-number-message="Esse campo precisa ser um número válido" pattern="/^[0-9]{8}$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-cepOrigem value="${registros[4].getValor()}">
				</div>
				<button cypress-submit>Alterar configurações</button>
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