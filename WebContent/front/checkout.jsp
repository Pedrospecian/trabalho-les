<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Checkout</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body class="body-front">
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Checkout</h1>
			<form class="js-checkout-form-validate" action="/trabalho-les/finalizarCompra?id=33">
				<input type="hidden" name="idCarrinho" class="js-idCarrinho" value="${carrinho.getId()}">
				<h1>Total da compra: <span class="js-dinheiro">${valorTotal}</span></h1>
				<span style="display: none;" class="js-valorTotalDaCompra">${valorTotal}</span>
				<div>
					<h2>Endereço de entrega</h2>
					Escolha um endereço de entrega
					<div class="boxes-wrapper enderecos-wrapper">
						<c:forEach var="endereco" items="${cliente.getEnderecos()}">
							<div class="box-single">
								<input type="radio" name="enderecoEntrega" value="${endereco.getId()}" data-cep="${endereco.getCep()}" cypress-enderecoEntrega class="js-mudaFrete">
								<div class="info-wrapper">							
									${endereco.getNome()}
									<br><br>
									Endereço <span class="text-lowercase">${endereco.getTipoEndereco().getNome()}</span>
									<br><br>
									${endereco.getCep()}
									<br><br>
									${endereco.getLogradouro()},
									n° ${endereco.getNumero()}
									${endereco.getComplemento()}
									<br>
									${endereco.getBairro().getDescricao()},
									${endereco.getBairro().getCidade().getDescricao()} -
									${endereco.getBairro().getCidade().getEstado().getDescricao()} -
									${endereco.getBairro().getCidade().getEstado().getPais().getDescricao()}
									<br>
									Observações:
								</div>
							</div>
						</c:forEach>
					</div>
					<input type="radio" name="enderecoEntrega" value="0" cypress-enderecoEntregaNovo>
					Ou cadastre um novo
					<div class="endereco-novo-wrapper js-checkout-form-novo-endereco" style="display: none;">
						<br>
						<input type="text" name="nomeEndereco" placeholder="Nome do Endereço" class="js-nome-endereco">
						<select name="tipoEndereco" class="js-tipo-endereco">
							<option value="">Tipo de endereço</option>
							<c:forEach var="opt" items="${tiposendereco}">
								<option value="${opt.getId()}">
									${opt.getNome()}
								</option>
							</c:forEach>
						</select>
						<select name="tipoResidencia" class="js-tipo-residencia">
							<option value="">Tipo de residência</option>
							<c:forEach var="opt" items="${tiposresidencia}">
								<option value="${opt.getId()}">
									${opt.getNome()}
								</option>
							</c:forEach>
							<!--<option value="1">Casa</option>
							<option value="2">Apartamento</option>
							<option value="3">Outro...</option>-->
						</select>
						<select name="funcaoEndereco" class="js-funcao-endereco">
							<option value="">Função do endereço</option>
							<c:forEach var="opt" items="${funcoesendereco}">
								<c:if test="${opt.getId() != 1}">
									<option value="${opt.getId()}">
										${opt.getNome()}
									</option>
								</c:if>
							</c:forEach>
							<!-- <option value="2">Endereço de Entrega</option>
							<option value="3">Endereço de Cobrança e Entrega</option> -->
						</select>
						<select name="pais" class="js-pais">
							<option value="">País</option>
							<option value="1">Brasil</option>
							<option value="2">EUA</option>
						</select>

						<input type="text" name="cep" placeholder="CEP"  class="js-cep-api js-cep-mask js-cep js-mudaFreteNovo">
						<br>
						<select name="tipoLogradouro" class="js-tipo-logradouro">
							<option value="">Tipo de logradouro</option>
							<c:forEach var="opt" items="${tiposlogradouro}">
								<option value="${opt.getId()}">
									${opt.getNome()}
								</option>
							</c:forEach>
						</select>
						<input type="text" name="logradouro" placeholder="Logradouro" class="js-logradouro">
						<input type="text" name="numero" placeholder="Número" class="js-numero">
						<input type="text" name="complemento" placeholder="Complemento" class="js-complemento">
						<input type="text" name="bairro" placeholder="Bairro" class="js-bairro">
						<input type="text" name="cidade" placeholder="Cidade" class="js-cidade">
						<input type="text" name="estado" placeholder="Estado" class="js-uf">
						Observações:
						<textarea name="observacoes" id="observacoes"></textarea>
					</div>
				</div>
				<h2>Forma de envio</h2>
				<div class="form-group">
					<input type="radio" name="tipoFrete" id="tipoFrete-sedex" value="04014" class="js-mudaFrete" checked="checked">
					<label for="tipoFrete-sedex">Sedex</label>
				</div>
				<div class="form-group">
					<input type="radio" name="tipoFrete" id="tipoFrete-pac" value="04510" class="js-mudaFrete">
					<label for="tipoFrete-pac">PAC</label>
				</div>
				<h2>Valor do frete: <span class="js-valorFrete"></span></h2>
				<h2>Tempo de entrega: <span class="js-diasEntrega">0</span> dias úteis</h2>
				<hr>
				<div>
					<h2>Forma de pagamento</h2>
					Você pode pagar com quantos cartões quiser...
					<div class="boxes-wrapper cartoes-wrapper-checkout js-cartoes-wrapper-checkout">
						<c:forEach var="cartaoCredito" items="${cliente.getCartoesCredito()}">
							<div class="box-single">
				                <div class="info-wrapper">
									<strong>${cartaoCredito.getBandeira().getNome()}</strong>
									<br>
									${cartaoCredito.getNumero()}
									<br>
									${cartaoCredito.getNome()}

				                	${cartaoCredito.getPreferencial() ? '<br><br><strong>Cartão preferencial</strong>' : ''}
				                </div>
				                <input type="hidden" name="idCartaoCredito" value="${cartaoCredito.getId()}">
				                <input type="number" name="valorPagoCartao_${cartaoCredito.getId()}" min="0" step="0.01" class="js-valorPagamento" placeholder="Insira o valor que este cartão irá pagar" cypress-valorPagoCartao>
				            </div>
				        </c:forEach>
					</div>
		            ...e também pode usar outros cartões!
		            <input type="hidden" name="numeroCartoesNovos" class="js-numeroCartoesNovos" value="0">
					<div class="cartao-novo-wrapper js-cartoes-novos-wrapper">
						<!--<input type="text" name="cartaoNumero" placeholder="Número do cartão" class="js-numero-cartao">
						<input type="text" name="cartaoNome" placeholder="Nome impresso no cartão" class="js-nome-cartao">
						<select name="cartaoBandeira" class="js-bandeira-cartao">
							<option value="">Bandeira do Cartão</option>
							<option value="1">Visa</option>
							<option value="2">MasterCard</option>
						</select>
						<input type="text" name="cartaoCodigo" placeholder="Código de segurança" class="js-cvv-cartao">
						<input type="date" name="cartaoValidade" placeholder="Data de validade do cartão" class="js-validade-cartao">
						<input type="checkbox" name="cadastrarCartao" id="cadastrarCartao">
						<label for="cadastrarCartao">Associar esse cartão à minha conta</label>
			            <input type="number" name="valorPagoCartao" min="0" placeholder="Insira o valor que este cartão irá pagar">-->
					</div>
					<button type="button" class="js-checkout-new-card">Adicionar outro cartão</button>
				</div>

				<h2>Você também pode usar cupons de desconto!</h2>
				<div>
					<input type="text" name="cupomDesconto" class="js-cupomDesconto" placeholder="Cupom promocional" cypress-cupomDesconto>
				</div>
				<div>
					<h3>Cupons de troca</h3>
					<div class="boxes-wrapper js-boxes-cupons-troca boxes-cupons-troca">
						<input type="hidden" name="arrIdCupomTroca" class="js-arrIdCupomTroca">
					</div>
					<input type="text" name="cupomTroca" placeholder="Cupom de troca" class="js-cupom-troca">
					<button type="button" class="js-adicionar-cupom-troca">Adicionar cupom de troca</button>
				</div>
				<h1>Total com frete: <span class="js-totalComFrete">${valorTotal}</span></h1>
				<button type="submit" class="button-finalizar" cypress-submit>Finalizar compra</button>
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
	<script type="text/javascript" src="assets/js/main.js"></script>
	<script type="text/javascript">
		const portApi = "8080";
		var pagamentoTotalCupons = 0;
		var pagamentoTotalCuponsTroca = 0;
		var pagamentoTotalCartoes = 0;
		var valorFrete = 0;
		var valorTotalCompra = parseFloat(document.querySelector('.js-valorTotalDaCompra').innerHTML);

		var xhttp_valorFrete = new XMLHttpRequest();
		xhttp_valorFrete.onreadystatechange = function() {
		  if (this.readyState == 4 && this.status == 200) {
		  	var retorno = JSON.parse(this.responseText);
		  	if (retorno && !retorno.erro && retorno.prazo && retorno.valorFrete) {	  	
			    console.log(retorno);
			    valorFrete = parseFloat(retorno.valorFrete);
			    document.querySelector(".js-valorFrete").innerHTML = "R$ " + parseFloat(valorFrete).toFixed(2);
			    document.querySelector(".js-totalComFrete").innerHTML = "R$ " + parseFloat(valorTotalCompra + valorFrete).toFixed(2);
			    document.querySelector(".js-diasEntrega").innerHTML = retorno.prazo;
			} else {
				valorFrete = 0;
				document.querySelector(".js-totalComFrete").innerHTML = "R$ " + parseFloat(valorTotalCompra).toFixed(2);
				document.querySelector(".js-valorFrete").innerHTML = "CEP inválido";
				document.querySelector(".js-diasEntrega").innerHTML = "---";
			}
		   }
		};

		var mudaFrete = document.querySelectorAll('.js-mudaFrete');

		for (var i = 0; i < mudaFrete.length; i++) {
			mudaFrete[i].addEventListener("click", function(e) {

				let valorDoFrete;

				if (e.target.attributes["data-cep"]) {
					valorDoFrete = e.target.attributes["data-cep"].value;
				} else {
					if (document.querySelector('input[name="enderecoEntrega"]:checked').attributes['value'].value === '0') {
						valorDoFrete = document.querySelector('.js-mudaFreteNovo').value;
					} else {
						valorDoFrete = document.querySelector('input[name="enderecoEntrega"]:checked').attributes['data-cep'].value;
					}
				}

				const tipoFreteValor = document.querySelector('input[name="tipoFrete"]:checked').attributes['value'].value;
				xhttp_valorFrete.open("GET", 'http://localhost:' + portApi + '/trabalho-les/api/calculaFrete?idCarrinho=' + document.querySelector('.js-idCarrinho').value + '&cepDestino=' + valorDoFrete + "&tipoFrete=" + tipoFreteValor, true);
		  		xhttp_valorFrete.send();
			});
		}

		document.querySelector('.js-mudaFreteNovo').addEventListener("change", function(e) {
			console.log(document.querySelector('input[name="enderecoEntrega"]:checked').attributes['value'].value);
			if (document.querySelector('input[name="enderecoEntrega"]:checked').attributes['value'].value === '0') {
				const tipoFreteValor = document.querySelector('input[name="tipoFrete"]:checked').attributes['value'].value;
				xhttp_valorFrete.open("GET", 'http://localhost:' + portApi + '/trabalho-les/api/calculaFrete?idCarrinho=' + document.querySelector('.js-idCarrinho').value + '&cepDestino=' + e.target.value + "&tipoFrete=" + tipoFreteValor , true);
		  		xhttp_valorFrete.send();
			}
		});

		document.querySelector('.js-valorTotalDaCompra').remove();

		console.log(valorTotalCompra);

		var checkoutForm = document.querySelector('.js-checkout-form-validate');
		var cupomDesconto = document.querySelector('.js-cupomDesconto');

		var xhttp_cupomDesconto = new XMLHttpRequest();
		xhttp_cupomDesconto.onreadystatechange = function() {
		  if (this.readyState == 4 && this.status == 200) {
		  	var retorno = JSON.parse(this.responseText);
		  	if (retorno && !retorno.erro) {	  	
			    console.log(retorno);
			    pagamentoTotalCupons = parseFloat(retorno);
			} else {
				pagamentoTotalCupons = 0;
			}
		   }
		};

		var xhttp_cupomTroca = new XMLHttpRequest();
		xhttp_cupomTroca.onreadystatechange = function() {
		  if (this.readyState == 4 && this.status == 200) {
		  	var retorno = JSON.parse(this.responseText);
		  	if (retorno && !retorno.erro) {
		  		  console.log("cupom troca")
			      console.log(retorno);
			      var textoCupomTroca = document.querySelector('.js-cupom-troca').value;
			      var novoCupomTroca = document.createElement("div");
			      novoCupomTroca.classList.add('box-single');
			      novoCupomTroca.innerHTML = '<a href="#" class="button-close js-remove">X</a><div class="info-wrapper">' +
			                '<input type="hidden" value="' +retorno+ '" class="js-valorPagamento">' +
			                  textoCupomTroca
			                  +'<br><br>'+
			                  "R$ " + parseFloat(retorno).toFixed(2)
			                +'</div>';

			      var idCupomTrocaHidden = document.querySelector(".js-arrIdCupomTroca");
			      idCupomTrocaHidden.value = idCupomTrocaHidden.value + document.querySelector('.js-cupom-troca').value + ',';

			      document.querySelector('.boxes-wrapper.js-boxes-cupons-troca').appendChild(novoCupomTroca);

			      const identificador = Math.floor(Math.random() * 100000); 
			      cuponsTrocaArray.push({
			        id: document.querySelector('.js-cupom-troca').value,
			        valor: retorno,
			        identificador: identificador
			      });

			      document.querySelector('.js-cupom-troca').value = '';

			      novoCupomTroca.querySelector('.button-close').addEventListener('click', function(e) {
			        e.preventDefault();
			        let indexRemoved = 0;
			        idCupomTrocaHidden.value = '';
			        for (var i = 0; i < cuponsTrocaArray.length; i++) {
			           if (cuponsTrocaArray[i].identificador === identificador) {
			             indexRemoved = i;
			           } else {
			             idCupomTrocaHidden.value = idCupomTrocaHidden.value + cuponsTrocaArray[i].id + ',';
			           }
			        }

			        console.log(cuponsTrocaArray);
			        cuponsTrocaArray.splice(indexRemoved, 1);
			        console.log(cuponsTrocaArray);
			        novoCupomTroca.remove();
			      });
			} else {

			}
		   }
		};

		cupomDesconto.addEventListener('change', function(e) {
		  	xhttp_cupomDesconto.open("GET", 'http://localhost:' + portApi + '/trabalho-les/api/cupomDesconto?cupomDesconto=' + e.target.value , true);
		  	xhttp_cupomDesconto.send();
		});

		var btnAdicionarCupomTroca = document.querySelector('.js-adicionar-cupom-troca');
			if (btnAdicionarCupomTroca) {
			  btnAdicionarCupomTroca.addEventListener('click', function(e) {
			    if (document.querySelector('.js-cupom-troca').value === '') {
			      alert('Por favor, insira um cupom de troca!');
			    } else {
			      xhttp_cupomTroca.open("GET", 'http://localhost:' + portApi + '/trabalho-les/api/cupomTroca?cupomTroca=' + document.querySelector('.js-cupom-troca').value, true);
	  			  xhttp_cupomTroca.send();
			    }
			  });
			}

		const validateCheckout = function() {
		  var enderecoEntrega = document.querySelector('input[name="enderecoEntrega"]:checked');
		  console.log(enderecoEntrega);

		  if (!enderecoEntrega) {
		    alert("Nenhum endereço de entrega foi escolhido!");
		    return false;
		  } else {
		    if (enderecoEntrega.attributes['value'] === '0') {
		      if (document.querySelector('.js-tipo-endereco').value === ''
		     || document.querySelector('.js-nome-endereco').value === ''
		     || document.querySelector('.js-tipo-residencia').value === ''
		     || document.querySelector('.js-funcao-endereco').value === ''
		     || document.querySelector('.js-tipo-logradouro').value === ''
		     || document.querySelector('.js-cep').value === ''
		     || document.querySelector('.js-logradouro').value === ''
		     || document.querySelector('.js-numero').value === ''
		     || document.querySelector('.js-bairro').value === ''
		     || document.querySelector('.js-cidade').value === ''
		     || document.querySelector('.js-uf').value === ''
		     || document.querySelector('.js-pais').value === '') {
		        alert("Preencha todos os campos do endereço novo!");
		      	return false;
		      }
		    }
		  }

		  var cartoesCredito = document.querySelectorAll('input[name^="valorPagoCartao"].js-valorPagamento');

		  pagamentoTotalCartoes = 0;

		  for (var i = 0; i<cartoesCredito.length; i++) {
		  	console.log(cartoesCredito[i].value);
		  	console.log(cartoesCredito[i]);
		  	if (cartoesCredito[i].value) {
			  	pagamentoTotalCartoes += parseFloat(cartoesCredito[i].value);
			  }
		  }

		  if (pagamentoTotalCartoes > valorTotalCompra + valorFrete) {
		  	alert("A soma dos valores que os cartões irão pagar não pode exceder o valor da compra!");
		  	return false;
		  }

		  console.log(pagamentoTotalCartoes);
		  console.log(pagamentoTotalCupons);

		  pagamentoTotalCuponsTroca = cuponsTrocaArray.reduce((a, b) => {
		  	console.log(b["valor"]);
			  return a + parseFloat(b["valor"]);
		  }, 0);

		  console.log(pagamentoTotalCuponsTroca)

		  if (!(pagamentoTotalCartoes + pagamentoTotalCupons + pagamentoTotalCuponsTroca) || pagamentoTotalCartoes + pagamentoTotalCupons + pagamentoTotalCuponsTroca < valorTotalCompra + valorFrete) {
		  	alert("O valor a ser pago é inferior ao valor total da compra!");
		  	console.log(pagamentoTotalCartoes);
		  	console.log(pagamentoTotalCupons);
		  	console.log(pagamentoTotalCuponsTroca);
		  	console.log(valorTotalCompra);
		  	console.log(valorFrete);
		  	return false;
		  }

		  if (pagamentoTotalCupons > 0 && (pagamentoTotalCartoes - (valorTotalCompra + valorFrete) === 0)) {
		  	alert("Você não pode usar um cupom promocional desnecessariamente!");
		  	alert(pagamentoTotalCupons);
		    alert(pagamentoTotalCartoes);
		    alert(valorTotalCompra + valorFrete);
		  	return false;
		  }

		  return true;
		}

		if (checkoutForm) {
		  checkoutForm.addEventListener('submit', function(e) {
		    e.preventDefault();
		    if (validateCheckout()) {
		      alert('Compra efetuada com sucesso!');

		      checkoutForm.submit();
		    }
		  });
		}
	</script>
</body>
</html>