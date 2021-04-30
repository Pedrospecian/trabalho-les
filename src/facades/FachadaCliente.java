package facades;

import dao.ClienteDAO;
import model.Cliente;

import java.util.ArrayList;

import model.Documento;
import model.Endereco;
import model.Telefone;
import model.CartaoCredito;
import strategies.ValidarCampos;
import utils.Campo;
import utils.ResultadosBusca;
import strategies.ValidacaoDocumentos;
import strategies.ValidacaoEnderecos;
import strategies.VerificarCamposCpf;
import strategies.CriptografarSenha;
import strategies.ValidacaoCartoesCredito;
import strategies.ValidacaoTelefones;
import strategies.ValidacaoEnderecosCadastro;

public class FachadaCliente implements IFachada<Cliente, Campo[]> {

	public boolean validarSenha(Campo[] campos) {
		if(!campos[0].getValor().equals(campos[1].getValor())) {
			System.out.println("Eh necessario digitar a nova senha duas vezes.");
			return false;
		}

		return true;
	}

	public boolean validarSenhaExistente(String senha, long idUsuario) {
		ClienteDAO dao = new ClienteDAO();
		CriptografarSenha cript = new CriptografarSenha();
		return dao.validarSenhaExistente(cript.processa(senha), idUsuario);
	}

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

	public boolean validaEmailExistente(String email) {
		ClienteDAO dao = new ClienteDAO();
		return !dao.validarEmailExistente(email);
	}

	public ResultadosBusca select(Campo[] campos) {
		ClienteDAO dao = new ClienteDAO();

		dao.select(campos);
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public Cliente selectSingle(long id, boolean checkout) {
		ClienteDAO dao = new ClienteDAO();
		dao.selectSingle(id, checkout);
		return dao.selectSingleVal;
	}

	public boolean validarDocumentos(Documento[] documentos, boolean precisaValidarCpf) {
		ValidacaoDocumentos val = new ValidacaoDocumentos();
		ClienteDAO dao = new ClienteDAO();
		VerificarCamposCpf ver = new VerificarCamposCpf();
		boolean cpfValido = !precisaValidarCpf || ver.processa(documentos);

		boolean retorno = val.processa(documentos) && !dao.documentosExistem(documentos) && cpfValido;

		System.out.println("sera que deu certo os documento?");
		System.out.println(retorno);
		return retorno;
	}

	public boolean validarEnderecos(Endereco[] enderecos, boolean cadastro) {

		if (cadastro) {
			ValidacaoEnderecosCadastro val = new ValidacaoEnderecosCadastro();

			boolean retorno = val.processa(enderecos);
			return retorno;
		} else {
			ValidacaoEnderecos val = new ValidacaoEnderecos();

			boolean retorno = val.processa(enderecos);
			return retorno;
		}		
	}

	public boolean validarCartoesCredito(CartaoCredito[] cartoesCredito) {
		ValidacaoCartoesCredito val = new ValidacaoCartoesCredito();

		boolean retorno = val.processa(cartoesCredito);
		return retorno;
	}

	public boolean validarTelefones(Telefone[] telefones) {
		ValidacaoTelefones val = new ValidacaoTelefones();

		boolean retorno = val.processa(telefones);

		return retorno;
	}

	public String insert(Cliente cliente) {
		try {
			ClienteDAO dao = new ClienteDAO();

			CriptografarSenha cript = new CriptografarSenha();
			cliente.setSenha(cript.processa(cliente.getSenha()));
			dao.insert(cliente);

			return "Cliente inserido com sucesso!";
		}catch(Exception e) {
			e.printStackTrace();
			return "Erro de validação. Tente novamente.";
		}
	}

	public String delete(Cliente cliente) {
		ClienteDAO dao = new ClienteDAO();

		dao.delete(cliente.getId());

		return "Cliente excluído com sucesso!";
	}

	public String update(Cliente cliente) {
		ClienteDAO dao = new ClienteDAO();
		dao.update(cliente);

		return "Cliente alterado com sucesso!";
	}
	
	public String deleteDocuments(Documento[] documentos) {
		try {	
			ClienteDAO dao = new ClienteDAO();
			dao.deleteDocuments(documentos);
			
			return "Documentos removidos com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar os documentos!";
		}
	}

	public String deleteEnderecos(Endereco[] enderecos) {
		try {	
			ClienteDAO dao = new ClienteDAO();
			dao.deleteEnderecos(enderecos);
			
			return "Endereços removidos com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar os endereços!";
		}
	}

	public String deleteCartoesCredito(CartaoCredito[] cartoesCredito) {
		try {	
			ClienteDAO dao = new ClienteDAO();
			dao.deleteCartoesCredito(cartoesCredito);
			
			return "Cartões de crédito removidos com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar os cartões de crédito!";
		}
	}

	public String deleteTelefones(Telefone[] telefones) {
		try {	
			ClienteDAO dao = new ClienteDAO();
			dao.deleteTelefones(telefones);
			
			return "Telefones removidos com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar os telefones!";
		}
	}

	public String updateStatus(Cliente cliente) {
		ClienteDAO dao = new ClienteDAO();
		dao.updateStatus(cliente);

		return "Status de cliente alterado com sucesso!";
	}

	public Endereco selectEnderecoSingle(long id) {
		ClienteDAO dao = new ClienteDAO();
		dao.selectEnderecoSingle(id);
		return dao.selectSingleEnderecoVal;
	}

	public Cliente recuperaClienteLogin(String email, String senha) {
		ClienteDAO dao = new ClienteDAO();
		CriptografarSenha cript = new CriptografarSenha();
		dao.recuperaClienteLogin(email, cript.processa(senha));
		return dao.selectSingleVal;
	}

	public String updateSenha(Cliente cliente) {
		ClienteDAO dao = new ClienteDAO();
		CriptografarSenha cript = new CriptografarSenha();
		cliente.setSenha(cript.processa(cliente.getSenha()));
		dao.updateSenha(cliente);

		return "Senha de cliente alterada com sucesso!";
	}

	public void setCartaoPreferencial(Cliente cliente, CartaoCredito cartaoCredito) {
		ClienteDAO dao = new ClienteDAO();
		dao.setCartaoPreferencial(cliente, cartaoCredito);
	}

	public ArrayList selectNotificacao(long idCliente) {
		ClienteDAO dao = new ClienteDAO();

		ArrayList notificacoes = dao.selectNotificacao(idCliente);

		return dao.selectVals;
	}

	public void deleteNotificacoes(long idCliente) {
		ClienteDAO dao = new ClienteDAO();
		dao.deleteNotificacoes(idCliente);
	}
}
