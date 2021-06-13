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
import utils.Log;

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

	public String insert(Cliente cliente, String usuarioResponsavel) {
		try {
			ClienteDAO dao = new ClienteDAO();

			CriptografarSenha cript = new CriptografarSenha();
			cliente.setSenha(cript.processa(cliente.getSenha()));
			dao.insert(cliente);

			String enderecosArr = "";
			String documentosArr = "";
			String cartoesCreditoArr = "";
			String telefonesArr = "";

			for (int i = 0; i < cliente.getEnderecos().length; i++) {
				Endereco e = cliente.getEnderecos()[i];

				enderecosArr += "{id: " + e.getId() + 
								", nome: '" + e.getNome() + "'" +
								", cep: " + e.getCep() + 
								", logradouro: '" + e.getLogradouro() + "'" +
								", numero: " + e.getNumero() + 
								", complemento: '" + e.getComplemento() + "'" +
								", bairro: " + e.getBairro().getDescricao() + 
								", cidade: " + e.getBairro().getCidade().getDescricao() +
								", estado: " + e.getBairro().getCidade().getEstado().getDescricao() + 
								", pais: " + e.getBairro().getCidade().getEstado().getPais().getId() + 
								", tipoEndereco: " + e.getTipoEndereco().getId() + 
								", tipoResidencia: " + e.getTipoResidencia().getId() + 
								", funcaoEndereco: " + e.getFuncaoEndereco().getId() + 
								", tipoLogradouro: " + e.getTipoLogradouro().getId() + 
								", observacoes: '" + e.getObservacoes() + "'}, ";
			}

			for (int i = 0; i < cliente.getDocumentos().length; i++) {
				Documento d = cliente.getDocumentos()[i];

				documentosArr += "{id: " + d.getId() + 
								", codigo: " + d.getCodigo() + 
								", validade: " + d.getValidade() + 
								", tipoDocumento: " + d.getTipoDocumento().getId() + "}, ";
			}

			for (int i = 0; i < cliente.getCartoesCredito().length; i++) {
				CartaoCredito c = cliente.getCartoesCredito()[i];

				cartoesCreditoArr += "{id: " + c.getId() + 
								", nome: " + c.getNome() + 
								", numero: " + c.getNumero() + 
								", cvv: " + c.getCvv() +
								", dataExpiracao: " + c.getDataExpiracao() + 
								", bandeira: " + c.getBandeira().getId() + "}, ";
			}

			for (int i = 0; i < cliente.getTelefones().length; i++) {
				Telefone t = cliente.getTelefones()[i];

				telefonesArr += "{id: " + t.getId() + 
								", ddd: " + t.getDdd() + 
								", numero: " + t.getNumero() + 
								", tipoTelefone: " + t.getTipoTelefone().getId() + "}, ";
			}

			enderecosArr = enderecosArr.substring(0, enderecosArr.length() - 2);
			documentosArr = documentosArr.substring(0, documentosArr.length() - 2);
			cartoesCreditoArr = cartoesCreditoArr.substring(0, cartoesCreditoArr.length() - 2);
			telefonesArr = telefonesArr.substring(0, telefonesArr.length() - 2);

			Log log = new Log(usuarioResponsavel,
							 "Cliente {id: " + cliente.getId() +
							 ", nome: " + cliente.getNome() + 
							 ", email: " + cliente.getEmail() + 
							 ", senha: " + cliente.getSenha() + 
							 ", genero: " + cliente.getGenero() + 
							 ", dataNascimento: " + cliente.getDataNascimento() + 
							 ", status: " + cliente.getStatus() + 
							 ", tipoCliente: " + cliente.getTipoCliente().getId() + 
							 ", enderecos: [" + enderecosArr + "]" +
							 ", documentos: [" + documentosArr + "]" +
							 ", cartoesCredito: [" + cartoesCreditoArr + "]" +
							 ", telefones: [" + telefonesArr + "]" +
							 "}",
							 "Alteração de status");
        	log.registrar();

			return "Cliente inserido com sucesso!";
		}catch(Exception e) {
			e.printStackTrace();
			return "Erro de validação. Tente novamente.";
		}
	}

	public String delete(Cliente cliente, String usuarioResponsavel) {
		ClienteDAO dao = new ClienteDAO();

		dao.delete(cliente.getId());

		return "Cliente excluído com sucesso!";
	}

	public String update(Cliente cliente, String usuarioResponsavel) {
		ClienteDAO dao = new ClienteDAO();
		dao.update(cliente);

		String enderecosArr = "";
			String documentosArr = "";
			String cartoesCreditoArr = "";
			String telefonesArr = "";

			if (cliente.getEnderecos() != null) {
				for (int i = 0; i < cliente.getEnderecos().length; i++) {
					Endereco e = cliente.getEnderecos()[i];

					enderecosArr += "{id: " + e.getId() + 
									", nome: '" + e.getNome() + "'" +
									", cep: " + e.getCep() + 
									", logradouro: '" + e.getLogradouro() + "'" +
									", numero: " + e.getNumero() + 
									", complemento: '" + e.getComplemento() + "'" +
									", bairro: " + e.getBairro().getDescricao() + 
									", cidade: " + e.getBairro().getCidade().getDescricao() +
									", estado: " + e.getBairro().getCidade().getEstado().getDescricao() + 
									", pais: " + e.getBairro().getCidade().getEstado().getPais().getId() + 
									", tipoEndereco: " + e.getTipoEndereco().getId() + 
									", tipoResidencia: " + e.getTipoResidencia().getId() + 
									", funcaoEndereco: " + e.getFuncaoEndereco().getId() + 
									", tipoLogradouro: " + e.getTipoLogradouro().getId() + 
									", observacoes: '" + e.getObservacoes() + "'}, ";
				}

				enderecosArr = enderecosArr.substring(0, enderecosArr.length() - 2);
			}

			if (cliente.getDocumentos() != null) {
				for (int i = 0; i < cliente.getDocumentos().length; i++) {
					Documento d = cliente.getDocumentos()[i];

					documentosArr += "{id: " + d.getId() + 
									", codigo: " + d.getCodigo() + 
									", validade: " + d.getValidade() + 
									", tipoDocumento: " + d.getTipoDocumento().getId() + "}, ";
				}

				documentosArr = documentosArr.substring(0, documentosArr.length() - 2);
			}

			if (cliente.getCartoesCredito() != null) {
				for (int i = 0; i < cliente.getCartoesCredito().length; i++) {
					CartaoCredito c = cliente.getCartoesCredito()[i];

					cartoesCreditoArr += "{id: " + c.getId() + 
									", nome: " + c.getNome() + 
									", numero: " + c.getNumero() + 
									", cvv: " + c.getCvv() +
									", dataExpiracao: " + c.getDataExpiracao() + 
									", bandeira: " + c.getBandeira().getId() + "}, ";
				}

				cartoesCreditoArr = cartoesCreditoArr.substring(0, cartoesCreditoArr.length() - 2);
			}

			if (cliente.getTelefones() != null) {
				for (int i = 0; i < cliente.getTelefones().length; i++) {
					Telefone t = cliente.getTelefones()[i];

					telefonesArr += "{id: " + t.getId() + 
									", ddd: " + t.getDdd() + 
									", numero: " + t.getNumero() + 
									", tipoTelefone: " + t.getTipoTelefone().getId() + "}, ";
				}

				telefonesArr = telefonesArr.substring(0, telefonesArr.length() - 2);
			}

			Log log = new Log(usuarioResponsavel,
							 "Cliente {id: " + cliente.getId() +
							 ", nome: " + cliente.getNome() + 
							 ", email: " + cliente.getEmail() + 
							 ", genero: " + cliente.getGenero() + 
							 ", dataNascimento: " + cliente.getDataNascimento() + 
							 ", status: " + cliente.getStatus() + 
							 ", tipoCliente: " + cliente.getTipoCliente().getId() + 
							 ", enderecosNovos: [" + enderecosArr + "]" +
							 ", documentosNovos: [" + documentosArr + "]" +
							 ", cartoesCreditoNovos: [" + cartoesCreditoArr + "]" +
							 ", telefonesNovos: [" + telefonesArr + "]" +
							 "}",
							 "Alteração de status");
        	log.registrar();

		return "Cliente alterado com sucesso!";
	}
	
	public String deleteDocuments(Documento[] documentos, String usuarioResponsavel) {
		try {	
			ClienteDAO dao = new ClienteDAO();
			dao.deleteDocuments(documentos);

			String documentosIds = "";

			for (int i = 0; i < documentos.length; i++) {
				documentosIds += documentos[i].getId() + ", ";
			}

			documentosIds = documentosIds.substring(0, documentosIds.length() - 2);

			Log log = new Log(usuarioResponsavel,
							 "Livro {"+ 
							 "documentosRemovidos: [" + documentosIds + "]" +
							 "}",
							 "Remoção de documentos");
			log.registrar();
			
			return "Documentos removidos com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar os documentos!";
		}
	}

	public String deleteEnderecos(Endereco[] enderecos, String usuarioResponsavel) {
		try {	
			ClienteDAO dao = new ClienteDAO();
			dao.deleteEnderecos(enderecos);

			String enderecosIds = "";

			for (int i = 0; i < enderecos.length; i++) {
				enderecosIds += enderecos[i].getId() + ", ";
			}

			enderecosIds = enderecosIds.substring(0, enderecosIds.length() - 2);

			Log log = new Log(usuarioResponsavel,
							 "Endereco {"+ 
							 "enderecosRemovidos: [" + enderecosIds + "]" +
							 "}",
							 "Remoção de enderecos");
			log.registrar();
			
			return "Endereços removidos com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar os endereços!";
		}
	}

	public String deleteCartoesCredito(CartaoCredito[] cartoesCredito, String usuarioResponsavel) {
		try {	
			ClienteDAO dao = new ClienteDAO();
			dao.deleteCartoesCredito(cartoesCredito);

			String cartoesCreditoIds = "";

			for (int i = 0; i < cartoesCredito.length; i++) {
				cartoesCreditoIds += cartoesCredito[i].getId() + ", ";
			}

			cartoesCreditoIds = cartoesCreditoIds.substring(0, cartoesCreditoIds.length() - 2);

			Log log = new Log(usuarioResponsavel,
							 "CartaoCredito {"+ 
							 "cartoesCreditoRemovidos: [" + cartoesCreditoIds + "]" +
							 "}",
							 "Remoção de cartões de crédito");
			log.registrar();
			
			return "Cartões de crédito removidos com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar os cartões de crédito!";
		}
	}

	public String deleteTelefones(Telefone[] telefones, String usuarioResponsavel) {
		try {	
			ClienteDAO dao = new ClienteDAO();
			dao.deleteTelefones(telefones);

			String telefonesIds = "";

			for (int i = 0; i < telefones.length; i++) {
				telefonesIds += telefones[i].getId() + ", ";
			}

			telefonesIds = telefonesIds.substring(0, telefonesIds.length() - 2);

			Log log = new Log(usuarioResponsavel,
							 "Telefone {"+ 
							 "telefonesRemovidos: [" + telefonesIds + "]" +
							 "}",
							 "Remoção de telefones");
			log.registrar();
			
			return "Telefones removidos com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar os telefones!";
		}
	}

	public String updateStatus(Cliente cliente, String usuarioResponsavel) {
		ClienteDAO dao = new ClienteDAO();
		dao.updateStatus(cliente);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "Cliente {id: " + cliente.getId() +
							 			  ", status: " + cliente.getStatus() + 
							 "}",
							 "Alteração de status");
        log.registrar();

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

	public String updateSenha(Cliente cliente, String usuarioResponsavel) {
		ClienteDAO dao = new ClienteDAO();
		CriptografarSenha cript = new CriptografarSenha();
		cliente.setSenha(cript.processa(cliente.getSenha()));
		dao.updateSenha(cliente);

		Log log = new Log(usuarioResponsavel,
							 "Cliente {id: " + cliente.getId() +
							 			  ", senha: " + cliente.getSenha() + 
							 "}",
							 "Alteração de senha");
        log.registrar();

		return "Senha de cliente alterada com sucesso!";
	}

	public void setCartaoPreferencial(Cliente cliente, CartaoCredito cartaoCredito, String usuarioResponsavel) {
		ClienteDAO dao = new ClienteDAO();
		dao.setCartaoPreferencial(cliente, cartaoCredito);

		Log log = new Log(usuarioResponsavel,
							 "Cliente {id: " + cliente.getId() +
							 		", cartaoCredito: " + cartaoCredito.getId() + 
							 "}",
							 "Escolha de cartão preferencial");
        log.registrar();
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
