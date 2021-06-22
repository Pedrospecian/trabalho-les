package facades;

import dao.UsuarioDAO;
import model.Usuario;

import java.util.ArrayList;

import strategies.ValidarCampos;
import strategies.CriptografarSenha;
import utils.Campo;
import utils.Log;
import utils.ResultadosBusca;

public class FachadaUsuario implements IFachada<Usuario, Campo[]> {
	public boolean validarCampos(Campo[] campos, boolean validarSenha) {
		ValidarCampos validarCampos = new ValidarCampos();

		boolean camposValidos = true;

		for(int i = 0; i < campos.length; i++) {
			System.out.println(i);
			if (validarCampos.processa(campos[i]) == false) {
				System.out.println(campos[i].getValor());
				System.out.println(campos[i].getMensagemErro());
				camposValidos = false;
			}
		}

		if (validarSenha && !campos[0].getValor().equals(campos[1].getValor())) {
			System.out.println("É necessario digitar a nova senha duas vezes.");
			camposValidos = false;
		}
		
		return camposValidos;
	}

	public boolean validarSenhaExistente(String senha, long idUsuario) {
		UsuarioDAO dao = new UsuarioDAO();
		CriptografarSenha cript = new CriptografarSenha();
		return dao.validarSenhaExistente(cript.processa(senha), idUsuario);
	}

	public boolean validaEmailExistente(String email) {
		UsuarioDAO dao = new UsuarioDAO();
		return !dao.validarEmailExistente(email);
	}

	public ResultadosBusca select(Campo[] campos) {
		UsuarioDAO dao = new UsuarioDAO();

		dao.select(campos);
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public Usuario selectSingle(long id) {
		UsuarioDAO dao = new UsuarioDAO();
		dao.selectSingle(id);
		return dao.selectSingleVal;
	}

	public String insert(Usuario usuario, String usuarioResponsavel) {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			CriptografarSenha cript = new CriptografarSenha();
			usuario.setSenha(cript.processa(usuario.getSenha()));
			dao.insert(usuario);

			Log log = new Log(usuarioResponsavel + " (admin)",
							 "Usuario {id: " + usuario.getId() +
							 			  ", nome: " + usuario.getNome() + 
							 			  ", email: " + usuario.getEmail() + 
							 			  ", status: " + usuario.getStatus() + 
							 			  ", senha: " + usuario.getSenha() + 
							 			  ", tipoUsuario: " + usuario.getTipoUsuario().getId() + 
							 "}",
							 "Inserção");
        	log.registrar();

			return "Usuario inserido com sucesso!";
		}catch(Exception e) {
			e.printStackTrace();
			return "Erro de validação. Tente novamente.";
		}
	}

	public String delete(Usuario usuario, String usuarioResponsavel) {
		UsuarioDAO dao = new UsuarioDAO();

		dao.delete(usuario.getId());

		return "Usuario excluído com sucesso!";
	}

	public String update(Usuario usuario, String usuarioResponsavel) {
		UsuarioDAO dao = new UsuarioDAO();
		dao.update(usuario);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "Usuario {id: " + usuario.getId() +
							 			  ", nome: " + usuario.getNome() + 
							 			  ", email: " + usuario.getEmail() + 
							 			  ", status: " + usuario.getStatus() + 
							 			  ", tipoUsuario: " + usuario.getTipoUsuario().getId() + 
							 "}",
							 "Alteração");
        log.registrar();

		return "Usuario alterado com sucesso!";
	}

	public String updateStatus(Usuario usuario, String usuarioResponsavel) {
		UsuarioDAO dao = new UsuarioDAO();
		dao.updateStatus(usuario);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "Usuario {id: " + usuario.getId() +
							 			  ", status: " + usuario.getStatus() +
							 "}",
							 "Alteração de status");
        log.registrar();

		return "Status de usuario alterado com sucesso!";
	}
	
	public String updateSenha(Usuario usuario, String usuarioResponsavel) {
		UsuarioDAO dao = new UsuarioDAO();
		CriptografarSenha cript = new CriptografarSenha();
		usuario.setSenha(cript.processa(usuario.getSenha()));
		dao.updateSenha(usuario);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "Usuario {id: " + usuario.getId() +
							 			  ", senha: " + usuario.getSenha() + 
							 "}",
							 "Alteração de senha");
        log.registrar();

		return "Senha de usuario alterada com sucesso!";
	}

	public Usuario recuperaUsuarioLogin(String email, String senha) {
		UsuarioDAO dao = new UsuarioDAO();
		CriptografarSenha cript = new CriptografarSenha();
		dao.recuperaUsuarioLogin(email, cript.processa(senha));
		return dao.selectSingleVal;
	}
}
