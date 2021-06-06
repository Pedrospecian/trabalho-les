<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Editar cliente</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body ${actionForm.equals("alterarMeusDadosAction") ? "class='body-front'" : ""}>
	<header>
		${headerHTML}
	</header>
	<main>
		<div class="container">
			<h1>Editar cliente</h1>
			<form action="/trabalho-les/${actionForm}" method="post" class="js-editar-cliente js-pristine-validation">
				<input type="hidden" name="id" value="${cliente.getId()}">
				<div class="form-group">
					<input type="text" name="nome" placeholder="Nome" required data-pristine-required-message="Este campo é obrigatório" value="${cliente.getNome()}" cypress-nome>
				</div>
				<div class="form-group">
					<input type="email" name="email" placeholder="E-mail" required data-pristine-required-message="Este campo é obrigatório" data-pristine-email-message="Este campo precisa ser um e-mail válido" cypress-email value="${cliente.getEmail()}">
				</div>
				<div class="form-group">
					<select name="genero" required data-pristine-required-message="Este campo é obrigatório" cypress-genero>
						<option value="">Gênero</option>
						<c:forEach var="opt" items="${generos}">
							<option value="${opt.getId()}" ${opt.getId() == cliente.getGenero() ? 'selected=\"selected\"' : ''}>
								${opt.getNome()}
							</option>
						</c:forEach>
						<!--<option value="1" ${1 == cliente.getGenero() ? 'selected=\"selected\"' : ''}>Masculino</option>
						<option value="2" ${2 == cliente.getGenero() ? 'selected=\"selected\"' : ''}>Feminino</option>-->
					</select>
				</div>
				<div class="form-group">
					<select name="tipoCliente" required data-pristine-required-message="Este campo é obrigatório"="" cypress-tipoCliente>
						<option value="">Tipo de Cliente</option>
						<c:forEach var="opt" items="${tiposcliente}">
							<option value="${opt.getId()}" ${opt.getId() == cliente.getTipoCliente().getId() ? 'selected=\"selected\"' : ''}>
								${opt.getNome()}
							</option>
						</c:forEach>
						<!--<option value="1" ${1 == cliente.getTipoCliente().getId() ? 'selected=\"selected\"' : ''}>Comprador</option>
						<option value="2" ${2 == cliente.getTipoCliente().getId() ? 'selected=\"selected\"' : ''}>Revendedor</option>
						<option value="3" ${3 == cliente.getTipoCliente().getId() ? 'selected=\"selected\"' : ''}>Parceiro</option>-->
					</select>
				</div>
				<div class="form-group">
					<input type="date" name="dataNascimento" placeholder="Data de nascimento" required data-pristine-required-message="Este campo é obrigatório" value="${cliente.getDataNascimento()}" data-pristine-date-message="Este campo precisa ser uma data válida" cypress-dataNascimento>
				</div>
				<c:if test='${!actionForm.equals("alterarMeusDadosAction")}'>
					<div class="form-group">
						<select name="status" required data-pristine-required-message="Este campo é obrigatório" cypress-status>
							<option value="">Status</option>
							<option value="1" ${1 == cliente.getStatus() ? 'selected=\"selected\"' : ''}>Ativo</option>
							<option value="0" ${0 == cliente.getStatus() ? 'selected=\"selected\"' : ''}>Inativo</option>
						</select>
					</div>
				</c:if>
				<h3>Documentos</h3>
				<div class="boxes-wrapper js-boxes-documentos">
					<input type="hidden" name="arrTipoDocumento" class="js-arrTipoDocumento">
					<input type="hidden" name="arrNumeroDocumento" class="js-arrNumeroDocumento">
					<input type="hidden" name="arrValidadeDocumento" class="js-arrValidadeDocumento">

					<input type="hidden" name="removerDocumentos" class="js-removerDocumentos">

					<c:forEach var="documento" items="${cliente.getDocumentos()}">
						<div class="box-single">
							<div class="info-wrapper">
								<strong>${documento.getTipoDocumento().getNome()}</strong>
								<br>
								${documento.getCodigo()}
								<br><br>
								validade: <span class="js-date-value">${documento.getValidade()}</span>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="form-add js-form-add-document">
					<select name="tipoDocumento" class="js-tipo-documento">
						<option value="">Tipo de Documento</option>
						<c:forEach var="opt" items="${tiposdocumento}">
							<c:if test="${opt.getId() != 1}">
								<option value="${opt.getId()}">
									${opt.getNome()}
								</option>
							</c:if>
						</c:forEach>
						<!--<option value="2">CNPJ</option>
						<option value="3">RG</option>
						<option value="4">Social Security Card</option>-->
					</select>
					<input type="text" name="documento" placeholder="Documento (sem caracteres especiais)" class="js-numero-documento">
					<input type="date" name="dataValidade" placeholder="Data de validade" class="js-validade-documento">
				</div>
				<button type="button" class="js-adicionar-documento">Adicionar documento</button>

				<h3>Endereços</h3>
				<div class="boxes-wrapper js-boxes-enderecos">
					<input type="hidden" name="arrTipoEndereco" class="js-arrTipoEndereco">
					<input type="hidden" name="arrNomeEndereco" class="js-arrNomeEndereco">
					<input type="hidden" name="arrTipoResidencia" class="js-arrTipoResidencia">
					<input type="hidden" name="arrTipoLogradouro" class="js-arrTipoLogradouro">
					<input type="hidden" name="arrFuncaoEndereco" class="js-arrFuncaoEndereco">
					<input type="hidden" name="arrCep" class="js-arrCep">
					<input type="hidden" name="arrLogradouro" class="js-arrLogradouro">
					<input type="hidden" name="arrNumero" class="js-arrNumero">
					<input type="hidden" name="arrComplemento" class="js-arrComplemento">
					<input type="hidden" name="arrBairro" class="js-arrBairro">
					<input type="hidden" name="arrCidade" class="js-arrCidade">
					<input type="hidden" name="arrUf" class="js-arrUf">
					<input type="hidden" name="arrPais" class="js-arrPais">
					<input type="hidden" name="arrObservacoes" class="js-arrObservacoes">

					<input type="hidden" name="removerEnderecos" class="js-removerEnderecos">

					<c:forEach var="endereco" items="${cliente.getEnderecos()}">
						<div class="box-single">
							<a href="#" class="button-close js-remove-existing-endereco" data-id="${endereco.getId()}">X</a>
							<div class="info-wrapper">							
								${endereco.getNome()}
								<br><br>
								Tipo de residência: ${endereco.getTipoResidencia().getNome()}
								<br>
								Função do endereço: ${endereco.getFuncaoEndereco().getNome()}
								<br><br>
								Endereço <span class="text-lowercase">${endereco.getTipoEndereco().getNome()}</span>
								<br><br>
								${endereco.getCep()}
								<br><br>
								${endereco.getLogradouro()} (tipo do logradouro: ${endereco.getTipoLogradouro().getNome()}),
								n° ${endereco.getNumero()}
								${endereco.getComplemento()}
								<br>
								${endereco.getBairro().getDescricao()},
								${endereco.getBairro().getCidade().getDescricao()} -
								${endereco.getBairro().getCidade().getEstado().getDescricao()} -
								${endereco.getBairro().getCidade().getEstado().getPais().getDescricao()}
								<br>
								Observações: ${endereco.getObservacoes()}
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="form-add js-form-add-address">
					<input type="text" name="nomeEndereco" placeholder="Nome do Endereço" class="js-nome-endereco">
					<select name="tipoEndereco" class="js-tipo-endereco">
						<option value="">Tipo de endereço</option>
						<c:forEach var="opt" items="${tiposendereco}">
							<option value="${opt.getId()}">
								${opt.getNome()}
							</option>
						</c:forEach>
						<!--<option value="1">Residencial</option>
						<option value="2">Comercial</option>-->
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
							<option value="${opt.getId()}">
								${opt.getNome()}
							</option>
						</c:forEach>
						<!--<option value="1">Endereço de Cobrança</option>
						<option value="2">Endereço de Entrega</option>
						<option value="3">Endereço de Cobrança e Entrega</option>-->
					</select>
					<select name="pais" class="js-pais">
						<option value="">País</option>
						<option value="1">Brasil</option>
						<option value="2">EUA</option>
					</select>

					<input type="text" name="cep" placeholder="CEP"  class="js-cep-api js-cep-mask js-cep">
					<select name="tipoLogradouro" class="js-tipo-logradouro">
						<option value="">Tipo de logradouro</option>
						<c:forEach var="opt" items="${tiposlogradouro}">
							<option value="${opt.getId()}">
								${opt.getNome()}
							</option>
						</c:forEach>
						<!--<option value="1">Rua</option>
						<option value="2">Avenida</option>
						<option value="3">Viela</option>
						<option value="4">Outro...</option>-->
					</select>
					<input type="text" name="logradouro" placeholder="Logradouro" class="js-logradouro">
					<input type="text" name="numero" placeholder="Número" class="js-numero">
					<input type="text" name="complemento" placeholder="Complemento" class="js-complemento">
					<input type="text" name="bairro" placeholder="Bairro" class="js-bairro">
					<input type="text" name="cidade" placeholder="Cidade" class="js-cidade">
					<input type="text" name="estado" placeholder="Estado" class="js-uf">
					Observações
					<textarea name="observacoes" class="js-observacoes"></textarea>
				</div>
				<button type="button" class="js-adicionar-endereco">Adicionar endereço</button>
				<h3>Cartões de Crédito</h3>
				<div class="boxes-wrapper js-boxes-cartoes">					
					<input type="hidden" name="arrNumeroCartao" class="js-arrNumeroCartao">
					<input type="hidden" name="arrNomeCartao" class="js-arrNomeCartao">
					<input type="hidden" name="arrBandeiraCartao" class="js-arrBandeiraCartao">
					<input type="hidden" name="arrCodigoCartao" class="js-arrCodigoCartao">
					<input type="hidden" name="arrValidadeCartao" class="js-arrValidadeCartao">
					<input type="hidden" name="removerCartoes" class="js-removerCartoes">

					<c:forEach var="cartaoCredito" items="${cliente.getCartoesCredito()}">
						<div class="box-single"><a href="#" class="button-close js-remove-existing-cartao" data-id="${cartaoCredito.getId()}">X</a>
			                <input 
			                	name="cartaoPreferencial"
			                	type="radio"
			                	value="${cartaoCredito.getId()}"
			                	${cartaoCredito.getPreferencial() ? 'checked=\"checked\"' : ''}
			                > Cartão preferencial
			                <div class="info-wrapper">
			                  <strong>${cartaoCredito.getBandeira().getNome()}</strong>
			                  <br>
			                  ${cartaoCredito.getNome()}
			                  <br>
			                  ${cartaoCredito.getNumero()}
			                  <br>
			                  Cód. Segurança: ${cartaoCredito.getCvv()}
			                  <br><br>
			                  validade: <span class="js-date-value">${cartaoCredito.getDataExpiracao()}</span>
			                </div>
			            </div>
			        </c:forEach>
		            <!--<div class="box-single"><a href="#" class="button-close js-remove-existing-cartao" data-id="2">X</a>
		                <input name="cartaoPreferencial" type="radio" value="sdsd"> Cartão preferencial
		                <div class="info-wrapper">
		                  <strong>Visa</strong>
		                  <br>
		                  sdsd
		                  <br>
		                  sdsd
		                  <br>
		                  Cód. Segurança: sdsds
		                  <br><br>
		                  validade: <span class="js-date-value">22/02/0222</span>
		                </div>
		            </div>-->
				</div>
				<div class="form-add js-form-add-card">
					<input type="text" name="cartaoNumero" placeholder="Número do cartão" class="js-numero-cartao">
					<input type="text" name="cartaoNome" placeholder="Nome impresso no cartão" class="js-nome-cartao">
					<select name="cartaoBandeira" class="js-bandeira-cartao">
						<option value="">Bandeira do Cartão</option>
						<c:forEach var="opt" items="${bandeiras}">
							<option value="${opt.getId()}">
								${opt.getNome()}
							</option>
						</c:forEach>
						<!--<option value="1">Visa</option>
						<option value="2">MasterCard</option>-->
					</select>
					<input type="text" name="cartaoCodigo" placeholder="Código de segurança" class="js-cvv-cartao">
					<input type="date" name="cartaoValidade" placeholder="Data de validade do cartão" class="js-validade-cartao">
				</div>
				<button type="button" class="js-adicionar-cartao">Adicionar cartão de crédito</button>

				<h3>Telefones</h3>
				<div class="boxes-wrapper js-boxes-telefones">					
					<input type="hidden" name="arrTipoTelefone" class="js-arrTipoTelefone">
					<input type="hidden" name="arrDDDTelefone" class="js-arrDDDTelefone">
					<input type="hidden" name="arrNumeroTelefone" class="js-arrNumeroTelefone">
					<input type="hidden" name="removerTelefones" class="js-removerTelefones">

					<c:forEach var="telefone" items="${cliente.getTelefones()}">
						<div class="box-single">
							<a href="#" class="button-close js-remove-existing-telefone" data-id="${telefone.getId()}">X</a>
			                <div class="info-wrapper">
			                  <strong>${telefone.getTipoTelefone().getNome()}</strong>
			                  <br>
			                  (${telefone.getDdd()})
			                  ${telefone.getNumero()}
			                </div>
			            </div>
			        </c:forEach>

		            <!--<div class="box-single">
		            	<a href="#" class="button-close js-remove-existing-telefone" data-id="2">X</a>
		                <div class="info-wrapper">
		                  <strong>Celular</strong>
		                  <br>
		                  (12)
		                  1254576
		                </div>
		            </div>-->
				</div>
				<div class="form-add js-form-add-phone">				
					<select name="tipoTelefone" class="js-tipo-telefone">
						<option value="">Tipo do telefone</option>
						<c:forEach var="opt" items="${tipostelefone}">
							<option value="${opt.getId()}">
								${opt.getNome()}
							</option>
						</c:forEach>
						<!--<option value="1">Residencial</option>
						<option value="2">Celular</option>-->
					</select>
					<input type="text" name="dddTelefone" placeholder="DDD" class="js-ddd-telefone">
					<input type="text" name="numeroTelefone" placeholder="Número" class="js-numero-telefone">
				</div>
				<button type="button" class="js-adicionar-telefone">Adicionar telefone</button>

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
	<script type="text/javascript" src="assets/js/clienteValidation.js"></script>
</body>
</html>