package viewHelpers;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import utils.Campo;
import model.Pais;
import model.Estado;
import model.FuncaoEndereco;
import model.Cidade;
import model.Bairro;
import model.Endereco;
import model.Documento;
import model.TipoDocumento;
import model.TipoEndereco;
import model.TipoLogradouro;
import model.TipoResidencia;
import utils.ResultadosBusca;

public class FornecedorViewHelper {
	public static Campo[] getListagemFornecedorCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[6];

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[1] = new Campo(1, req.getParameter("email"), true, "", true, "email");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		campos[3] = new Campo(1, req.getParameter("tipoDocumento"), true, "", true, "documentos.idTipoDocumento");
		campos[4] = new Campo(0, req.getParameter("documento"), true, "", true, "documentos.codigo");
		campos[5] = new Campo(999, req.getParameter("paginaAtual"), true, "", true, "paginaAtual");
		campos[6] = new Campo(999, req.getParameter("resultadosPorPagina"), true, "", true, "resultadosPorPagina");

		return campos;
	}

	public static Campo[] getAlterarFornecedorStatusActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[2];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}

	public static Campo[] getCadastroFornecedorActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[20];

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[1] = new Campo(7, req.getParameter("email"), true, "", true, "email");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		campos[3] = new Campo(1, req.getParameter("tipoDocumento"), true, "", true, "tipoDocumento");
		campos[4] = new Campo(0, req.getParameter("documento"), true, "", true, "documento");
		campos[5] = new Campo(3, req.getParameter("dataValidade"), true, "", true, "dataValidade");

		campos[6] = new Campo(1, req.getParameter("tipoEndereco"), true, "", false, "tipoEndereco");
		campos[7] = new Campo(0, req.getParameter("cep"), true, "", false, "cep");
		campos[8] = new Campo(0, req.getParameter("logradouro"), true, "", false, "logradouro");
		campos[9] = new Campo(0, req.getParameter("numero"), true, "", false, "numero");
		campos[10] = new Campo(0, req.getParameter("complemento"), true, "", false, "complemento");
		campos[11] = new Campo(0, req.getParameter("bairro"), true, "", false, "bairro");
		campos[12] = new Campo(0, req.getParameter("cidade"), true, "", false, "cidade");
		campos[13] = new Campo(0, req.getParameter("estado"), true, "", false, "estado");
		campos[14] = new Campo(1, req.getParameter("pais"), true, "", false, "pais");	
		campos[15] = new Campo(0, req.getParameter("nomeEndereco"), true, "", false, "nomeEndereco");	
		campos[16] = new Campo(1, req.getParameter("tipoResidencia"), true, "", false, "tipoResidencia");
		campos[17] = new Campo(1, req.getParameter("funcaoEndereco"), true, "", false, "funcaoEndereco");
		campos[18] = new Campo(1, req.getParameter("tipoLogradouro"), true, "", false, "tipoLogradouro");
		campos[19] = new Campo(0, req.getParameter("observacoes"), true, "", false, "observacoes");

		return campos;
	}

	public static Campo[] getAlterarFornecedorActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[18]; 

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[2] = new Campo(1, req.getParameter("email"), true, "", true, "email");
		campos[3] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		campos[4] = new Campo(6, req.getParameter("arrTipoDocumento"), true, "", false, "tipoDocumentos");
		campos[5] = new Campo(6, req.getParameter("arrNumeroDocumento"), true, "", false, "numeroDocumentos");
		campos[6] = new Campo(6, req.getParameter("arrValidadeDocumento"), true, "", false, "validadeDocumentos");

		campos[7] = new Campo(6, req.getParameter("arrTipoEndereco"), true, "", false, "tipoEnderecos");
		campos[8] = new Campo(6, req.getParameter("arrCep"), true, "", false, "cepEnderecos");
		campos[9] = new Campo(6, req.getParameter("arrLogradouro"), true, "", false, "logradouroEnderecos");
		campos[10] = new Campo(6, req.getParameter("arrNumero"), true, "", false, "numeroEnderecos");
		campos[11] = new Campo(6, req.getParameter("arrComplemento"), true, "", false, "complementoEnderecos");
		campos[12] = new Campo(6, req.getParameter("arrBairro"), true, "", false, "bairroEnderecos");
		campos[13] = new Campo(6, req.getParameter("arrCidade"), true, "", false, "cidadeEnderecos");
		campos[14] = new Campo(6, req.getParameter("arrUf"), true, "", false, "ufEnderecos");
		campos[15] = new Campo(6, req.getParameter("arrPais"), true, "", false, "paisEnderecos");

		campos[16] = new Campo(6, req.getParameter("removerDocumentos"), true, "", false, "removerDocumentos");
		campos[17] = new Campo(6, req.getParameter("removerEnderecos"), true, "", false, "removerEnderecos");
		campos[18] = new Campo(6, req.getParameter("arrNomeEndereco"), true, "", false, "nomeEnderecos");

		return campos;
	}

	/*public static Documento[] createDocumentosFromStrings(String tipo, String codigo, String validade) throws Exception {
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
	}*/
}