package facades;

import dao.FornecedorDAO;
import model.Fornecedor;

import java.util.ArrayList;

import model.Documento;
import model.Endereco;
import strategies.ValidarCampos;
import utils.Campo;
import utils.ResultadosBusca;
import strategies.ValidacaoDocumentos;
import strategies.ValidacaoEnderecos;
import strategies.VerificarCamposCpf;

public class FachadaFornecedor implements IFachada<Fornecedor, Campo[]> {

	public boolean validarCampos(Campo[] campos) {
		ValidarCampos validarCampos = new ValidarCampos();

		boolean camposValidos = true;

		for(int i = 0; i < campos.length; i++) {
			if (validarCampos.processa(campos[i]) == false) {
				System.out.println("ERRO");
				System.out.println(campos[i].getNome());
				System.out.println(campos[i].getValor());
				System.out.println(campos[i].getMensagemErro());
				camposValidos = false;
			}
		}
		return camposValidos;
	}

	public ResultadosBusca select(Campo[] campos) {
		FornecedorDAO dao = new FornecedorDAO();

		dao.select(campos);
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	//validaemailexistente

	public Fornecedor selectSingle(long id) {
		FornecedorDAO dao = new FornecedorDAO();
		dao.selectSingle(id);
		return dao.selectSingleVal;
	}

	public boolean validarDocumentos(Documento[] documentos) {
		ValidacaoDocumentos val = new ValidacaoDocumentos();
		FornecedorDAO dao = new FornecedorDAO();
		VerificarCamposCpf ver = new VerificarCamposCpf();

		return val.processa(documentos) && !dao.documentosExistem(documentos);
	}

	public boolean validarEnderecos(Endereco[] enderecos) {
		ValidacaoEnderecos val = new ValidacaoEnderecos();

		return val.processa(enderecos);
	}

	public String insert(Fornecedor fornecedor) {
		try {
			FornecedorDAO dao = new FornecedorDAO();
			dao.insert(fornecedor);

			return "Fornecedor inserido com sucesso!";
		}catch(Exception e) {
			e.printStackTrace();
			return "Erro de validação. Tente novamente.";
		}
	}

	public String delete(Fornecedor fornecedores) {
		FornecedorDAO dao = new FornecedorDAO();

		dao.delete(fornecedores.getId());

		return "Fornecedor excluído com sucesso!";
	}

	public String update(Fornecedor fornecedores) {
		FornecedorDAO dao = new FornecedorDAO();
		dao.update(fornecedores);

		return "Fornecedor alterado com sucesso!";
	}
	
	public String deleteDocuments(Documento documento) {
		try {	
			FornecedorDAO dao = new FornecedorDAO();
			dao.deleteDocument(documento);
			
			return "Documentos removidos com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar os documentos!";
		}
	}

	public String deleteEnderecos(Endereco endereco) {
		try {	
			FornecedorDAO dao = new FornecedorDAO();
			dao.deleteEndereco(endereco);
			
			return "Endereços removidos com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar os endereços!";
		}
	}

	public String updateStatus(Fornecedor fornecedor) {
		FornecedorDAO dao = new FornecedorDAO();
		dao.updateStatus(fornecedor);

		return "Status de fornecedor alterado com sucesso!";
	}
}
