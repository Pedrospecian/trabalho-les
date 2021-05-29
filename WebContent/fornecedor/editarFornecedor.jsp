<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Editar Fornecedor</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Editar fornecedor</h1>
			<form action="/trabalho-les/alteraFornecedorAction" class="js-pristine-validation">
				<!--<input type="hidden" name="id" value="1">-->
				<input type="hidden" name="id" value="${fornecedor.getId()}">
				<div class="form-group">
					<input type="text" name="nome" placeholder="Nome" value="${fornecedor.getNome()}" required data-pristine-required-message="Este campo é obrigatório" cypress-nome>
				</div>
				<div class="form-group">
					<input type="email" name="email" placeholder="E-mail" value="${fornecedor.getEmail()}" required data-pristine-required-message="Este campo é obrigatório" data-pristine-email-message="Este campo precisa ser um e-mail válido" cypress-email>
				</div>
				<div class="form-group">
					<select name="status" required data-pristine-required-message="Este campo é obrigatório" cypress-status>
						<option value="">Status</option>						
						<option value="1" ${1 == fornecedor.getStatus() ? 'selected=\"selected\"' : ''}>Ativo</option>
						<option value="0" ${0 == fornecedor.getStatus() ? 'selected=\"selected\"' : ''}>Inativo</option>
					</select>
			</div>
				<h3>Documento</h3>
				Tipo de documento: ${fornecedor.getDocumento().getTipoDocumento().getNome()}
				<br>
				Documento: ${fornecedor.getDocumento().getCodigo()}
				<br>
				Documento: ${fornecedor.getDocumento().getValidade()}
				<br>
				<h3>Endereço</h3>
				Nome: ${fornecedor.getEndereco().getNome()}
				<br>
				Tipo de endereço: ${fornecedor.getEndereco().getTipoEndereco().getNome()}
				<br>
				Tipo de residência: ${fornecedor.getEndereco().getTipoResidencia().getNome()}
				<br>
				Função do endereço: ${fornecedor.getEndereco().getFuncaoEndereco().getNome()}
				<br>
				País: ${fornecedor.getEndereco().getBairro().getCidade().getEstado().getPais().getDescricao()}
				<br>
				CEP: ${fornecedor.getEndereco().getCep()}
				<br>
				Tipo de logradouro: ${fornecedor.getEndereco().getTipoLogradouro().getNome()}
				<br>
				Logradouro: ${fornecedor.getEndereco().getLogradouro()}
				<br>
				Número: ${fornecedor.getEndereco().getNumero()}
				<br>
				Complemento: ${fornecedor.getEndereco().getComplemento()}
				<br>
				Bairro: ${fornecedor.getEndereco().getBairro().getDescricao()}
				<br>
				Cidade: ${fornecedor.getEndereco().getBairro().getCidade().getDescricao()}
				<br>
				Estado: ${fornecedor.getEndereco().getBairro().getCidade().getEstado().getDescricao()}
				<br>
				Observações: ${fornecedor.getEndereco().getObservacoes()}
				<br>
				<button cypress-submit>Cadastrar</button>
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