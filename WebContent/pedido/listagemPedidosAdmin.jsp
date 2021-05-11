<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Pedidos do cliente</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>
				Pedidos
				<c:if test = '${cliente != null}'>
					de ${cliente.getNome()}
				</c:if>
			</h1>
			<h2>Buscar pedidos</h2>
			<form method="get" action="#" class="form-buscar-clientes">
				<c:if test = '${cliente == null}'>
					<select name="idCliente">
						<option value="">Cliente</option>
						<c:forEach var="cliSel" items="${clientes}">
							<option value="${cliSel.getId()}" ${campos[0].getValor().equals(cliSel.getId()) ? 'selected=\"selected\"' : ''}>${cliSel.getNome()}</option>
						</c:forEach>
					</select>
				</c:if>
				<input type="date" name="dataCadastro" placeholder="Data do pedido" value="${campos[1].getValor()}">
				<select name="status">
					<option value="">Status</option>
					<option value="1" ${campos[2].getValor().equals("1") ? 'selected=\"selected\"' : ''}>Em processamento</option>
					<option value="2" ${campos[2].getValor().equals("2") ? 'selected=\"selected\"' : ''}>Aceito</option>
					<option value="8" ${campos[2].getValor().equals("8") ? 'selected=\"selected\"' : ''}>Reprovado</option>
					<option value="3" ${campos[2].getValor().equals("3") ? 'selected=\"selected\"' : ''}>Em trânsito</option>
					<option value="4" ${campos[2].getValor().equals("4") ? 'selected=\"selected\"' : ''}>Entregue</option>
					<option value="5" ${campos[2].getValor().equals("5") ? 'selected=\"selected\"' : ''}>Em troca</option>
					<option value="6" ${campos[2].getValor().equals("6") ? 'selected=\"selected\"' : ''}>Troca autorizada</option>
					<option value="7" ${campos[2].getValor().equals("7") ? 'selected=\"selected\"' : ''}>Trocado</option>
				</select>
				<input type="number" min="1" name="resultadosPorPagina" placeholder="Resultados por página" value="" >
				<button type="submit">Buscar</button>
			</form>
			<table cellspacing="0" cellpadding="0" class="js-paginated-table" data-itensPorPagina="10">
				<thead>
					<tr>
						<th>Id</th>
						<c:if test = '${cliente == null}'>
							<th>Cliente</th>
						</c:if>
						<th>Data do pedido</th>
						<th>Valor total</th>
						<th>Valor frete</th>
						<th>Endereço de entrega</th>
						<th>Prazo estimado</th>
						<th>Status</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="registro" items="${registros}">
					<tr>
						<td>${registro.getId()}</td>
						<c:if test = '${cliente == null}'>
							<td>${registro.getCliente().getNome()}</td>
						</c:if>
						<td>
							<span class="js-date-value">
								${registro.getDataCadastro()}
							</span>
						</td>
						<td><span class="js-dinheiro">${registro.getValorTotal()}</span></td>
						<td><span class="js-dinheiro">${registro.getValorFrete()}</span></td>
						<td>Rua teste, nº 60</td>
						<td>
							${registro.getPrazo()} dias úteis
							(Entrega via 
								<c:if test = '${registro.getTipoServico().equals("04014")}'>
									SEDEX)
								</c:if>							
								<c:if test = '${registro.getTipoServico().equals("04510")}'>
									PAC)
								</c:if>
						</td>
						<td cypress-statusPedido>
							<c:if test = '${registro.getStatus() == 1}'>
								Em processamento (atualizado em 05/02/2021)
							</c:if>							
							<c:if test = '${registro.getStatus() == 2}'>
								Aceito (atualizado em 05/02/2021)
							</c:if>
							<c:if test = '${registro.getStatus() == 3}'>
								Em trânsito (atualizado em 05/02/2021)
							</c:if>
							<c:if test = '${registro.getStatus() == 4}'>
								Entregue (atualizado em 05/02/2021)
							</c:if>
							<c:if test = '${registro.getStatus() == 5}'>
								Em troca (atualizado em 05/02/2021)
							</c:if>
							<c:if test = '${registro.getStatus() == 6}'>
								Troca autorizada (atualizado em 05/02/2021)
							</c:if>
							<c:if test = '${registro.getStatus() == 7}'>
								Trocado (atualizado em 05/02/2021)
							</c:if>
							<c:if test = '${registro.getStatus() == 8}'>
								Reprovado (atualizado em 05/02/2021)
							</c:if>
						</td>
						<td cypress-acoesPedido>
							<a href="/trabalho-les/pedidoAdmin?id=${registro.getId()}&admin" cypress-detalhes-pedido>Ver detalhes</a>
							<br>
							<c:if test = '${registro.getStatus() == 1}'>
								<a href="/trabalho-les/processarPedido?id=${registro.getId()}" cypress-aceite>Processar pedido</a>
							</c:if>
							<c:if test = '${registro.getStatus() == 2}'>
								<a href="/trabalho-les/alterarStatusPedido?id=${registro.getId()}" cypress-despacho>Despachar para entrega</a>
							</c:if>
							<c:if test = '${registro.getStatus() == 3}'>
								<a href="/trabalho-les/alterarStatusPedido?id=${registro.getId()}" cypress-entregaEfetuada>Entrega efetuada</a>
							</c:if>						
						</td>
					</tr>
					</c:forEach>
<!--					<tr>
						<td>1</td>
						<td>04/02/2021</td>
						<td>R$ 40,00</td>
						<td>R$ 2,50</td>
						<td>Rua teste, nº 60</td>
						<td>Em trânsito (atualizado em 05/02/2021)</td>
						<td>
							<a href="/trabalho-les/pedido?id=33&admin">Ver detalhes</a>
							<br>
							
						</td>
					</tr>
					<tr>
						<td>1</td>
						<td>04/02/2021</td>
						<td>R$ 40,00</td>
						<td>R$ 2,50</td>
						<td>Rua teste, nº 60</td>
						<td>Entregue (atualizado em 05/02/2021)</td>
						<td>
							<a href="/trabalho-les/pedido?id=33&admin">Ver detalhes</a>
						</td>
					</tr>
					<tr>
						<td>1</td>
						<td>04/02/2021</td>
						<td>R$ 40,00</td>
						<td>R$ 2,50</td>
						<td>Rua teste, nº 60</td>
						<td>Em troca (atualizado em 05/02/2021)</td>
						<td>
							<a href="/trabalho-les/pedido?id=33&admin">Ver detalhes</a>
							<br>
							
						</td>
					</tr>
					<tr>
						<td>1</td>
						<td>04/02/2021</td>
						<td>R$ 40,00</td>
						<td>R$ 2,50</td>
						<td>Rua teste, nº 60</td>
						<td>Troca autorizada (atualizado em 05/02/2021)</td>
						<td>
							<a href="/trabalho-les/pedido?id=33&admin">Ver detalhes</a>
							<br>
							
						</td>
					</tr>-->
				</tbody>
			</table>
			<div class="paginated-table-wrapper"></div>
			<div class="js-pagination-links"></div>
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
	<script type="text/javascript" src="assets/js/pagination.js"></script>
</body>
</html>