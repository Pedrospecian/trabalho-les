<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Meus pedidos</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body class="${cliente ? 'body-front' : ''}">
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>Pedido #${pedido.getId()}</h1>
			Status do pedido: 
			<c:if test = '${pedido.getStatus() == 1}'>
				Em processamento (atualizado em 05/02/2021)
			</c:if>							
			<c:if test = '${pedido.getStatus() == 2}'>
				Aceito (atualizado em 05/02/2021)
			</c:if>
			<c:if test = '${pedido.getStatus() == 3}'>
				Em trânsito (atualizado em 05/02/2021)
			</c:if>
			<c:if test = '${pedido.getStatus() == 4}'>
				Entregue (atualizado em 05/02/2021)
			</c:if>
			<c:if test = '${pedido.getStatus() == 5}'>
				Em troca (atualizado em 05/02/2021)
			</c:if>
			<c:if test = '${pedido.getStatus() == 6}'>
				Troca autorizada (atualizado em 05/02/2021)
			</c:if>
			<c:if test = '${pedido.getStatus() == 7}'>
				Trocado (atualizado em 05/02/2021)
			</c:if>
			<c:if test = '${pedido.getStatus() == 8}'>
				Reprovado (atualizado em 05/02/2021)
			</c:if>
			<h2>Produtos</h2>
			<table class="carrinho-itens" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th>Capa</th>
						<th>Título</th>
						<th>Preço</th>
						<th>Quantidade</th>
						<th>Trocar</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${pedido.getCarrinho().getItensCarrinho()}">
						<tr>
							<td><img src="${item.getLivro().getCapa()}" alt="Alice no país das maravilhas"></td>
							<td>
								<a href="/trabalho-les/produto?id=${item.getLivro().getId()}">
									${item.getLivro().getTitulo()}
								</a>
							</td>
							<td>
								Preço unitário: <span class="js-dinheiro">${item.getLivro().getPreco()}</span>
								<br>
								Subtotal: <span class="js-dinheiro">${item.getLivro().getPreco() * item.getQuantidade()}</span>
							</td>
							<td cypress-quantidadeLivroPedido>
								${item.getQuantidade()}

								<c:if test = '${item.getQuantidadeItensTrocados() > 0}'>
									<br>
									<strong>(${item.getQuantidadeItensTrocados()} -> troca solicitada)</strong>
								</c:if>
							</td>
							<td>
								<c:if test = '${cliente && pedido.getStatus() > 3 && pedido.getStatus() != 8 && item.getQuantidadeItensTrocados() < item.getQuantidade()}'>
									<form method="post" action="/trabalho-les/solicitarTroca">
										<input type="hidden" name="id" value="${item.getId()}">
										<div class="form-group">
											<input type="number" name="trocaQtde" placeholder="Qt. Troca" min="1" max="${item.getQuantidade()}" required data-pristine-required-message="Este campo é obrigatório" data-pristine-number-message="Esse campo precisa ser um número válido" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido" cypress-trocaQtde>
										</div>
										<button type="submit" cypress-submit>Solicitar Troca</button>
									</form>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<!--<tr>
						<td><img src="" alt="Alice no país das maravilhas"></td>
						<td>Alice no país das maravilhas</td>
						<td>R$ 40,00</td>
						<td>4</td>
						<td>
							<form method="post" action="/trabalho-les/solicitarTroca">
								<input type="hidden" name="id" value="1">
								<div class="form-group">
									<input type="number" name="trocaQtde" placeholder="Qt. Troca" min="1" max="4" required data-pristine-required-message="Este campo é obrigatório" data-pristine-number-message="Esse campo precisa ser um número válido" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido">
								</div>
								<button type="submit">Solicitar Troca</button>
							</form>
						</td>
					</tr>
					<tr>
						<td><img src="assets\images\produtos\livro-ficcao.jpg" alt="Alice no país das maravilhas"></td>
						<td>Alice no país das maravilhas</td>
						<td>R$ 40,00</td>
						<td>1</td>
						<td>
							<form method="post" action="/trabalho-les/solicitarTroca">
								<input type="hidden" name="id" value="1">
								<div class="form-group">
									<input type="number" name="trocaQtde" placeholder="Qt. Troca" min="1" max="1" required data-pristine-required-message="Este campo é obrigatório" data-pristine-number-message="Esse campo precisa ser um número válido" pattern="/^[0-9]+$/" data-pristine-pattern-message="Este campo precisa ser um número válido">
								</div>
								<button type="submit">Solicitar Troca</button>
							</form>
						</td>
					</tr>-->
				</tbody>
			</table>
			<h2>Total: <span class="js-dinheiro">${pedido.getValorTotal()}</span></h2>
			<h3>Valor frete: <span class="js-dinheiro">${pedido.getValorFrete()}</span></h3>
			<h1>Total com frete: <span class="js-dinheiro">${pedido.getValorTotal() + pedido.getValorFrete()}</span></h1>
			<h2>
				Tempo estimado para entrega: ${pedido.getPrazo()} dias úteis

				(Entrega via 
				<c:if test = '${pedido.getTipoServico().equals("04014")}'>
					SEDEX)
				</c:if>							
				<c:if test = '${pedido.getTipoServico().equals("04510")}'>
					PAC)
				</c:if>
			</h2>
			<c:if test = '${pedido.getEndereco() != null}'>
				<hr>
				<div>
					<h2>Endereço de entrega:</h2>
					${pedido.getEndereco().getNome()}
					<br><br>
					Tipo de residência: Apartamento
					<br>
					Função do endereço: Cobrança
					<br><br>
					Endereço residencial
					<br><br>
					${pedido.getEndereco().getCep()}
					<br><br>
					${pedido.getEndereco().getLogradouro()} (tipo do logradouro: Rua), n° ${pedido.getEndereco().getNumero()}
					<c:if test = '${pedido.getEndereco().getComplemento() != null && !pedido.getEndereco().getComplemento().equals("")}'>
						- {pedido.getEndereco().getComplemento()}
					</c:if>
					<br>
					${pedido.getEndereco().getBairro().getDescricao()},
					${pedido.getEndereco().getBairro().getCidade().getDescricao()} -
					${pedido.getEndereco().getBairro().getCidade().getEstado().getDescricao()} -
					${pedido.getEndereco().getBairro().getCidade().getEstado().getPais().getDescricao()}
				</div>
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
	<script type="text/javascript" src="assets/js/vendor/pristine.min.js"></script>
	<script type="text/javascript" src="assets/js/main.js"></script>
	<script type="text/javascript" src="assets/js/fieldValidation.js"></script>
</body>
</html>