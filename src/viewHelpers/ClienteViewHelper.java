package viewHelpers;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import utils.Campo;
import model.Pais;
import model.Estado;
import model.Cidade;
import model.Bandeira;
import model.CartaoCredito;
import model.Bairro;
import model.Endereco;
import model.Documento;
import model.TipoDocumento;
import model.TipoEndereco;
import model.TipoTelefone;
import model.TipoLogradouro;
import model.TipoResidencia;
import model.Telefone;
import model.FuncaoEndereco;
import utils.ResultadosBusca;

public class ClienteViewHelper {
	public static Campo[] getListagemClientesCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[9];

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", true, "clientes.nome");
		campos[1] = new Campo(1, req.getParameter("genero"), true, "", true, "clientes.genero");
		campos[2] = new Campo(3, req.getParameter("dataNascimento"), true, "", true, "clientes.dataNascimento");
		campos[3] = new Campo(1, req.getParameter("tipoCliente"), true, "", true, "clientes.idTipoCliente");
		campos[4] = new Campo(1, req.getParameter("status"), true, "", true, "clientes.status");
		campos[5] = new Campo(1, req.getParameter("tipoDocumento"), true, "", true, "documentos.idTipoDocumento");
		campos[6] = new Campo(0, req.getParameter("documento"), true, "", true, "documentos.codigo");
		campos[7] = new Campo(999, req.getParameter("paginaAtual"), true, "", true, "paginaAtual");
		campos[8] = new Campo(999, req.getParameter("resultadosPorPagina"), true, "", true, "resultadosPorPagina");

		return campos;
	}

	public static Campo[] getAlterarClienteStatusActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[2];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}

	public static Campo[] getCadastroClienteActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[34];

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[1] = new Campo(1, req.getParameter("genero"), true, "", true, "genero");
		campos[2] = new Campo(3, req.getParameter("dataNascimento"), true, "", true, "dataNascimento");
		campos[3] = new Campo(1, req.getParameter("tipoCliente"), true, "", true, "tipoCliente");
		campos[4] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		campos[30] = new Campo(7, req.getParameter("email"), true, "", true, "email");
		campos[31] = new Campo(8, req.getParameter("senha"), true, "", true, "senha");
		campos[32] = new Campo(0, req.getParameter("senhaConfirmar"), true, "", true, "senhaConfirmar");

		//documentos
		campos[5] = new Campo(6, req.getParameter("arrTipoDocumento"), true, "", true, "tipoDocumentos");
		campos[6] = new Campo(6, req.getParameter("arrNumeroDocumento"), true, "", true, "numeroDocumentos");
		campos[7] = new Campo(6, req.getParameter("arrValidadeDocumento"), true, "", true, "validadeDocumentos");

		//enderecos
		campos[8] = new Campo(6, req.getParameter("arrTipoEndereco"), true, "", true, "tipoEnderecos");
		campos[9] = new Campo(6, req.getParameter("arrCep"), true, "", true, "cepEnderecos");
		campos[10] = new Campo(6, req.getParameter("arrLogradouro"), true, "", true, "logradouroEnderecos");
		campos[11] = new Campo(6, req.getParameter("arrNumero"), true, "", true, "numeroEnderecos");
		campos[12] = new Campo(6, req.getParameter("arrComplemento"), true, "", true, "complementoEnderecos");
		campos[13] = new Campo(6, req.getParameter("arrBairro"), true, "", true, "bairroEnderecos");
		campos[14] = new Campo(6, req.getParameter("arrCidade"), true, "", true, "cidadeEnderecos");
		campos[15] = new Campo(6, req.getParameter("arrUf"), true, "", true, "ufEnderecos");
		campos[16] = new Campo(6, req.getParameter("arrPais"), true, "", true, "paisEnderecos");
		campos[17] = new Campo(6, req.getParameter("arrNomeEndereco"), true, "", true, "nomeEnderecos");
		campos[23] = new Campo(6, req.getParameter("arrTipoResidencia"), true, "", true, "tipoResidenciaEnderecos");
		campos[24] = new Campo(6, req.getParameter("arrFuncaoEndereco"), true, "", true, "funcaoEnderecos");
		campos[25] = new Campo(6, req.getParameter("arrTipoLogradouro"), true, "", true, "tipoLogradouroEnderecos");
		campos[26] = new Campo(6, req.getParameter("arrObservacoes"), true, "", true, "observacoes");

		//cartoes de credito
		campos[18] = new Campo(6, req.getParameter("arrNumeroCartao"), true, "", true, "numeroCartoes");
		campos[19] = new Campo(6, req.getParameter("arrNomeCartao"), true, "", true, "nomeCartoes");
		campos[20] = new Campo(6, req.getParameter("arrBandeiraCartao"), true, "", true, "bandeiraCartoes");
		campos[21] = new Campo(6, req.getParameter("arrCodigoCartao"), true, "", true, "codigoCartoes");
		campos[22] = new Campo(6, req.getParameter("arrValidadeCartao"), true, "", true, "validadeCartoes");
		campos[33] = new Campo(1, req.getParameter("cartaoPreferencial"), true, "", true, "cartaoPreferencial");

		//telefones
		campos[27] = new Campo(6, req.getParameter("arrTipoTelefone"), true, "", true, "tipoTelefones");
		campos[28] = new Campo(6, req.getParameter("arrDDDTelefone"), true, "", true, "dddTelefones");
		campos[29] = new Campo(6, req.getParameter("arrNumeroTelefone"), true, "", true, "numeroTelefones");

		/*System.out.println("=============== os campos ===================");
		for (int i = 0; i < campos.length; i++) {
			System.out.println(campos[i].getNome() + ": " + campos[i].getValor());
		}*/

		return campos;
	}

	public static Campo[] getAlterarClienteActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[37]; 

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[2] = new Campo(1, req.getParameter("genero"), true, "", true, "genero");
		campos[3] = new Campo(3, req.getParameter("dataNascimento"), true, "", true, "dataNascimento");
		campos[4] = new Campo(1, req.getParameter("tipoCliente"), true, "", true, "tipoCliente");
		campos[5] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		campos[35] = new Campo(7, req.getParameter("email"), true, "", true, "email");

		//documentos
		campos[6] = new Campo(6, req.getParameter("arrTipoDocumento"), true, "", false, "tipoDocumentos");
		campos[7] = new Campo(6, req.getParameter("arrNumeroDocumento"), true, "", false, "numeroDocumentos");
		campos[8] = new Campo(6, req.getParameter("arrValidadeDocumento"), true, "", false, "validadeDocumentos");

		//enderecos
		campos[9] = new Campo(6, req.getParameter("arrTipoEndereco"), true, "", false, "tipoEnderecos");
		campos[10] = new Campo(6, req.getParameter("arrCep"), true, "", false, "cepEnderecos");
		campos[11] = new Campo(6, req.getParameter("arrLogradouro"), true, "", false, "logradouroEnderecos");
		campos[12] = new Campo(6, req.getParameter("arrNumero"), true, "", false, "numeroEnderecos");
		campos[13] = new Campo(6, req.getParameter("arrComplemento"), true, "", false, "complementoEnderecos");
		campos[14] = new Campo(6, req.getParameter("arrBairro"), true, "", false, "bairroEnderecos");
		campos[15] = new Campo(6, req.getParameter("arrCidade"), true, "", false, "cidadeEnderecos");
		campos[16] = new Campo(6, req.getParameter("arrUf"), true, "", false, "ufEnderecos");
		campos[17] = new Campo(6, req.getParameter("arrPais"), true, "", false, "paisEnderecos");	
		campos[20] = new Campo(6, req.getParameter("arrNomeEndereco"), true, "", false, "nomeEnderecos");	
		campos[21] = new Campo(6, req.getParameter("arrTipoResidencia"), true, "", false, "tipoResidenciaEnderecos");
		campos[22] = new Campo(6, req.getParameter("arrFuncaoEndereco"), true, "", false, "funcaoEnderecos");
		campos[23] = new Campo(6, req.getParameter("arrTipoLogradouro"), true, "", false, "tipoLogradouroEnderecos");
		campos[24] = new Campo(6, req.getParameter("arrObservacoes"), true, "", false, "observacoes");

		//cartoes de credito
		campos[25] = new Campo(6, req.getParameter("arrNumeroCartao"), true, "", false, "numeroCartoes");
		campos[26] = new Campo(6, req.getParameter("arrNomeCartao"), true, "", false, "nomeCartoes");
		campos[27] = new Campo(6, req.getParameter("arrBandeiraCartao"), true, "", false, "bandeiraCartoes");
		campos[28] = new Campo(6, req.getParameter("arrCodigoCartao"), true, "", false, "codigoCartoes");
		campos[29] = new Campo(6, req.getParameter("arrValidadeCartao"), true, "", false, "validadeCartoes");
		campos[36] = new Campo(0, req.getParameter("cartaoPreferencial"), true, "", true, "cartaoPreferencial");
		//campos[37] = new Campo(0, req.getParameter("cartaoPreferencialNovo"), true, "", true, "cartaoPreferencialNovo");

		//telefones
		campos[30] = new Campo(6, req.getParameter("arrTipoTelefone"), true, "", false, "tipoTelefones");
		campos[31] = new Campo(6, req.getParameter("arrDDDTelefone"), true, "", false, "dddTelefones");
		campos[32] = new Campo(6, req.getParameter("arrNumeroTelefone"), true, "", false, "numeroTelefones");

		//remover
		campos[18] = new Campo(6, req.getParameter("removerDocumentos"), true, "", false, "removerDocumentos");
		campos[19] = new Campo(6, req.getParameter("removerEnderecos"), true, "", false, "removerEnderecos");
		campos[33] = new Campo(6, req.getParameter("removerCartoes"), true, "", false, "removerCartoesCredito");
		campos[34] = new Campo(6, req.getParameter("removerTelefones"), true, "", false, "removerTelefones");

		return campos;
	}

	public static CartaoCredito[] createCartoesCreditoFromStrings(String numero, String nome, String bandeira, String codigo, String validade) throws Exception {
		if (numero.equals("") || nome.equals("") || bandeira.equals("") || codigo.equals("") || validade.equals("")) return null;
		String[] arrCartoesCreditosNumeroStr = numero.split(",");
		String[] arrCartoesCreditosNomeStr = nome.split(",");
		String[] arrCartoesCreditosBandeiraStr = bandeira.split(",");
		String[] arrCartoesCreditosCodigoStr = codigo.split(",");
		String[] arrCartoesCreditosValidadeStr = validade.split(",");

		if (arrCartoesCreditosBandeiraStr.length > 0 
		 && arrCartoesCreditosNumeroStr.length > 0 
		 && arrCartoesCreditosNomeStr.length > 0 
		 && arrCartoesCreditosCodigoStr.length > 0 
		 && arrCartoesCreditosValidadeStr.length > 0 
		 && arrCartoesCreditosNomeStr.length == arrCartoesCreditosNumeroStr.length
		 && arrCartoesCreditosNomeStr.length == arrCartoesCreditosBandeiraStr.length
		 && arrCartoesCreditosNomeStr.length == arrCartoesCreditosCodigoStr.length
		 && arrCartoesCreditosNomeStr.length == arrCartoesCreditosValidadeStr.length
		) {
			CartaoCredito[] cartoesCredito = new CartaoCredito[arrCartoesCreditosNumeroStr.length];

			for (int i = 0; i < cartoesCredito.length; i++) {
				cartoesCredito[i] = new CartaoCredito(
					(long)1,
					new Date(),
					arrCartoesCreditosNomeStr[i],
					arrCartoesCreditosNumeroStr[i],
					arrCartoesCreditosCodigoStr[i],
					new SimpleDateFormat("yyyy-MM-dd").parse(arrCartoesCreditosValidadeStr[i]),
					new Bandeira(Long.parseLong(arrCartoesCreditosBandeiraStr[i]), new Date(), ""));
			}	//long id, Date dataCadastro, String nome, String numero, String cvv, Date dataExpiracao, Bandeira bandeira)

			return cartoesCredito;
		} else {		
			return null;
		}
	}

	public static Documento[] createDocumentosFromStrings(String tipo, String codigo, String validade) throws Exception {
		if (tipo.equals("") || codigo.equals("") || validade.equals("")) return null;
		String[] arrDocumentosTipoStr = tipo.split(",");

		String[] arrDocumentosNumeroStr = codigo.split(",");
		String[] arrDocumentosValidadeStr = validade.split(",");

		if (arrDocumentosTipoStr.length > 0 
		 && arrDocumentosNumeroStr.length > 0 
		 && arrDocumentosValidadeStr.length > 0 
		 && arrDocumentosTipoStr.length == arrDocumentosNumeroStr.length
		 && arrDocumentosTipoStr.length == arrDocumentosValidadeStr.length
		) {
			Documento[] documentos = new Documento[arrDocumentosTipoStr.length];

			for (int i = 0; i < documentos.length; i++) {
				documentos[i] = new Documento((long)1, new Date(), arrDocumentosNumeroStr[i], new SimpleDateFormat("yyyy-MM-dd").parse(arrDocumentosValidadeStr[i]), new TipoDocumento(Long.parseLong(arrDocumentosTipoStr[i]), new Date(), "", ""));
			}

			return documentos;
		} else {		
			return null;
		}
	}

	public static Documento[] createDocumentosRemovidosFromStrings(String ids) {
		if (ids.equals("")) return null;
		String[] arrDocumentosIds = ids.split(",");

		if (arrDocumentosIds.length > 0) {
			Documento[] documentos = new Documento[arrDocumentosIds.length];

			for (int i = 0; i < documentos.length; i++) {
				documentos[i] = new Documento(Long.parseLong(arrDocumentosIds[i]), new Date(), "", new Date(), new TipoDocumento((long)1, new Date(), "", ""));
			}

			return documentos;
		} else {		
			return null;
		}
	}

	public static Endereco[] createEnderecosFromStrings(String tipo, String cep, String logradouro, String numero, String complemento, String bairroStr, String cidadeStr, String ufStr, String paisStr, String nome, String tipoResidencia, String funcaoEndereco, String tipoLogradouro, String observacoes) throws Exception {
		if (tipo.equals("") || cep.equals("") || logradouro.equals("") || numero.equals("") || complemento.equals("") || bairroStr.equals("") || cidadeStr.equals("") || ufStr.equals("") || paisStr.equals("") || nome.equals("") || tipoResidencia.equals("") || funcaoEndereco.equals("") || tipoLogradouro.equals("") || observacoes.equals("")) {
			return null;
		}
		String[] arrEnderecosTipoStr = tipo.split(",");
		String[] arrEnderecosCepStr = cep.split(",");
		String[] arrEnderecosLogradouroStr = logradouro.split(",");
		String[] arrEnderecosNumeroStr = numero.split(",");
		String[] arrEnderecosComplementoStr = complemento.replace(",", "$,").split(",");
		String[] arrEnderecosBairroStr = bairroStr.split(",");
		String[] arrEnderecosCidadeStr = cidadeStr.split(",");
		String[] arrEnderecosUfStr = ufStr.split(",");
		String[] arrEnderecosPaisStr = paisStr.split(",");
		String[] arrEnderecosNomeStr = nome.split(",");
		String[] arrEnderecosTipoResStr = tipoResidencia.split(",");
		String[] arrEnderecosFuncaoStr = funcaoEndereco.split(",");
		String[] arrEnderecosTipoLogrStr = tipoLogradouro.split(",");
		String[] arrEnderecosObservacoesStr = observacoes.replace(",", "$,").split(",");

		if (arrEnderecosTipoStr.length > 0 
		 && arrEnderecosTipoStr.length == arrEnderecosCepStr.length
		 && arrEnderecosTipoStr.length == arrEnderecosLogradouroStr.length
		 && arrEnderecosTipoStr.length == arrEnderecosNumeroStr.length
		 && arrEnderecosTipoStr.length == arrEnderecosComplementoStr.length
		 && arrEnderecosTipoStr.length == arrEnderecosBairroStr.length
		 && arrEnderecosTipoStr.length == arrEnderecosCidadeStr.length
		 && arrEnderecosTipoStr.length == arrEnderecosUfStr.length
		 && arrEnderecosTipoStr.length == arrEnderecosPaisStr.length
		 && arrEnderecosTipoStr.length == arrEnderecosNomeStr.length
		 && arrEnderecosTipoStr.length == arrEnderecosTipoResStr.length
		 && arrEnderecosTipoStr.length == arrEnderecosFuncaoStr.length
		 && arrEnderecosTipoStr.length == arrEnderecosTipoLogrStr.length
		 && arrEnderecosTipoStr.length == arrEnderecosObservacoesStr.length
		) {
			Endereco[] enderecos = new Endereco[arrEnderecosTipoStr.length];

			for (int i = 0; i < enderecos.length; i++) {
				Pais pais = new Pais(Long.parseLong(arrEnderecosPaisStr[i]), new Date(), "");
				Estado estado = new Estado((long)1, new Date(), arrEnderecosUfStr[i], pais);
				Cidade cidade = new Cidade((long)1, new Date(), arrEnderecosCidadeStr[i], estado);
				Bairro bairro = new Bairro((long)1, new Date(), arrEnderecosBairroStr[i], cidade);

				enderecos[i] = new Endereco(
								(long)1,
								new Date(),
								arrEnderecosLogradouroStr[i],
								arrEnderecosNumeroStr[i],
								arrEnderecosCepStr[i],
								arrEnderecosComplementoStr[i].replace("$", ""),
								bairro,
								new TipoEndereco(Long.parseLong(arrEnderecosTipoStr[i]), new Date(), "", ""),
								arrEnderecosNomeStr[i],
								new TipoResidencia(Long.parseLong(arrEnderecosTipoResStr[i]), new Date(), "", ""),
								new FuncaoEndereco(Long.parseLong(arrEnderecosFuncaoStr[i]), new Date(), "", ""),
								new TipoLogradouro(Long.parseLong(arrEnderecosTipoLogrStr[i]), new Date(), "", ""),
								arrEnderecosObservacoesStr[i].replace("$", ""));

				//(long id, Date dataCadastro, String logradouro, String numero, String cep, String complemento, Bairro bairro, TipoEndereco tipoEndereco, String nome, TipoResidencia tipoResidencia, FuncaoEndereco funcaoEndereco, TipoLogradouro tipoLogradouro, String observacoes)

			}

			return enderecos;
		} else {
			return null;
		}
	}

	public static Endereco[] createEnderecosRemovidosFromStrings(String ids) {
		System.out.println(ids);
		if (ids.equals("")) return null;
		String[] arrEnderecosIds = ids.split(",");

		if (arrEnderecosIds.length > 0) {
			Endereco[] enderecos = new Endereco[arrEnderecosIds.length];

			for (int i = 0; i < enderecos.length; i++) {
				enderecos[i] = new Endereco(Long.parseLong(arrEnderecosIds[i]), new Date(), "", "", "", "", null, null, "", null, null, null, "");
			}

			return enderecos;
		} else {		
			return null;
		}
	}

	public static Telefone[] createTelefonesFromStrings(String tipo, String ddd, String numero) throws Exception {
		if (tipo.equals("") || ddd.equals("") || numero.equals("")) return null;
		String[] arrTelefonesTipoStr = tipo.split(",");
		String[] arrTelefonesDddStr = ddd.split(",");
		String[] arrTelefonesNumeroStr = numero.split(",");

		if (arrTelefonesTipoStr.length > 0 
		 && arrTelefonesNumeroStr.length > 0 
		 && arrTelefonesDddStr.length > 0 
		 && arrTelefonesTipoStr.length == arrTelefonesNumeroStr.length
		 && arrTelefonesTipoStr.length == arrTelefonesDddStr.length
		) {
			Telefone[] telefones = new Telefone[arrTelefonesTipoStr.length];

			for (int i = 0; i < telefones.length; i++) {
				telefones[i] = new Telefone(
					(long)1,
					new Date(),
					new TipoTelefone(Long.parseLong(arrTelefonesTipoStr[i]), new Date(), "", ""),
					arrTelefonesDddStr[i],
					arrTelefonesNumeroStr[i]);
			}

			return telefones;
		} else {		
			return null;
		}
	}

	public static CartaoCredito[] createCartoesCreditoRemovidosFromStrings(String ids) {
		System.out.println(ids);
		if (ids.equals("")) return null;
		String[] arrCartoesCreditoIds = ids.split(",");

		if (arrCartoesCreditoIds.length > 0) {
			CartaoCredito[] cartoesCredito = new CartaoCredito[arrCartoesCreditoIds.length];

			for (int i = 0; i < cartoesCredito.length; i++) {
				cartoesCredito[i] = new CartaoCredito(Long.parseLong(arrCartoesCreditoIds[i]), new Date(), "", "", "", null, null);
			}

			return cartoesCredito;
		} else {		
			return null;
		}
	}

	public static Telefone[] createTelefonesRemovidosFromStrings(String ids) {
		System.out.println(ids);
		if (ids.equals("")) return null;
		String[] arrTelefonesIds = ids.split(",");

		if (arrTelefonesIds.length > 0) {
			Telefone[] telefones = new Telefone[arrTelefonesIds.length];

			for (int i = 0; i < telefones.length; i++) {
				telefones[i] = new Telefone(Long.parseLong(arrTelefonesIds[i]), new Date(), null, "", "");
			}

			return telefones;
		} else {		
			return null;
		}
	}

	public static Campo[] getAlterarSenhaClienteActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[4];

		campos[0] = new Campo(8, req.getParameter("senhaNova"), true, "", true, "senhaNova");
		campos[1] = new Campo(0, req.getParameter("senhaConfirmar"), true, "", true, "senhaConfirmar");
		campos[2] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[3] = new Campo(0, req.getParameter("senhaAtual"), true, "", true, "senhaAtual");
		
		return campos;
	}
}