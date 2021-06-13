package facades;

import dao.FornecedorDAO;
import model.Fornecedor;

import java.util.ArrayList;

import model.Documento;
import model.Endereco;
import strategies.ValidarCampos;
import utils.Campo;
import utils.Log;
import utils.ResultadosBusca;
import strategies.ValidacaoDocumentos;
import strategies.ValidacaoEnderecos;

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

	public Fornecedor selectSingle(long id) {
		FornecedorDAO dao = new FornecedorDAO();
		dao.selectSingle(id);
		return dao.selectSingleVal;
	}

	public boolean validarDocumentos(Documento[] documentos) {
		ValidacaoDocumentos val = new ValidacaoDocumentos();
		FornecedorDAO dao = new FornecedorDAO();

		return val.processa(documentos) && !dao.documentosExistem(documentos);
	}

	public boolean validarEnderecos(Endereco[] enderecos) {
		ValidacaoEnderecos val = new ValidacaoEnderecos();

		return val.processa(enderecos);
	}

	public String insert(Fornecedor fornecedor, String usuarioResponsavel) {
		try {
			FornecedorDAO dao = new FornecedorDAO();
			dao.insert(fornecedor);

			String strEndereco = "id: " + fornecedor.getEndereco().getId() + 
								", nome: '" + fornecedor.getEndereco().getNome() + "'" +
								", cep: " + fornecedor.getEndereco().getCep() + 
								", logradouro: '" + fornecedor.getEndereco().getLogradouro() + "'" +
								", numero: " + fornecedor.getEndereco().getNumero() + 
								", complemento: '" + fornecedor.getEndereco().getComplemento() + "'" +
								", bairro: " + fornecedor.getEndereco().getBairro().getDescricao() + 
								", cidade: " + fornecedor.getEndereco().getBairro().getCidade().getDescricao() +
								", estado: " + fornecedor.getEndereco().getBairro().getCidade().getEstado().getDescricao() + 
								", pais: " + fornecedor.getEndereco().getBairro().getCidade().getEstado().getPais().getId() + 
								", tipoEndereco: " + fornecedor.getEndereco().getTipoEndereco().getId() + 
								", tipoResidencia: " + fornecedor.getEndereco().getTipoResidencia().getId() + 
								", funcaoEndereco: " + fornecedor.getEndereco().getFuncaoEndereco().getId() + 
								", tipoLogradouro: " + fornecedor.getEndereco().getTipoLogradouro().getId() + 
								", observacoes: '" + fornecedor.getEndereco().getObservacoes() + "'";

			String strDocumento = "id: " + fornecedor.getDocumento().getId() + 
								", codigo: " + fornecedor.getDocumento().getCodigo() + 
								", validade: " + fornecedor.getDocumento().getValidade() + 
								", tipoDocumento: " + fornecedor.getDocumento().getTipoDocumento().getId();

			Log log = new Log(usuarioResponsavel + " (admin)",
							 "Fornecedor {"+ 
							 "id: " + fornecedor.getId() +
							 ", nome: " + fornecedor.getNome() +
							 ", email: " + fornecedor.getEmail() +
							 ", status: " + fornecedor.getStatus() +
							 ", endereco: {" + strEndereco +"}" +
							 ", documento: {" + strDocumento +"}" +
							 "}",
							 "Alteração");
		log.registrar();

			return "Fornecedor inserido com sucesso!";
		}catch(Exception e) {
			e.printStackTrace();
			return "Erro de validação. Tente novamente.";
		}
	}

	public String delete(Fornecedor fornecedor, String usuarioResponsavel) {
		FornecedorDAO dao = new FornecedorDAO();

		dao.delete(fornecedor.getId());

		return "Fornecedor excluído com sucesso!";
	}

	public String update(Fornecedor fornecedor, String usuarioResponsavel) {
		FornecedorDAO dao = new FornecedorDAO();
		dao.update(fornecedor);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "Fornecedor {"+ 
							 "id: " + fornecedor.getId() +
							 ", nome: " + fornecedor.getNome() +
							 ", email: " + fornecedor.getEmail() +
							 ", status: " + fornecedor.getStatus() +
							 "}",
							 "Alteração");
		log.registrar();

		return "Fornecedor alterado com sucesso!";
	}

	public String updateStatus(Fornecedor fornecedor, String usuarioResponsavel) {
		FornecedorDAO dao = new FornecedorDAO();
		dao.updateStatus(fornecedor);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "Fornecedor {"+ 
							 "id: " + fornecedor.getId() +
							 ", status: " + fornecedor.getStatus() +
							 "}",
							 "Alteração de status");
		log.registrar();

		return "Status de fornecedor alterado com sucesso!";
	}
}
