package viewHelpers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import model.TipoUsuario;
import model.Usuario;
import utils.Campo;

public class UsuarioViewHelper implements IViewHelper<Usuario> {
	public Campo[] listagemCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[4];

		String resultadosPorPagina = "10";

		if (req.getParameter("resultadosPorPagina") != null && req.getParameter("resultadosPorPagina").matches("^[0-9]+$")) {
			resultadosPorPagina = req.getParameter("resultadosPorPagina");
		}

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[1] = new Campo(0, req.getParameter("email"), true, "", true, "email");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		campos[3] = new Campo(999, resultadosPorPagina, true, "", true, "resultadosPorPagina");

		return campos;
	}

	public Campo[] cadastroCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[6];

		campos[0] = new Campo(8, req.getParameter("senha"), true, "", true, "senha");
		campos[1] = new Campo(0, req.getParameter("senhaConfirmar"), true, "", true, "senhaConfirmar");
		campos[2] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[3] = new Campo(7, req.getParameter("email"), true, "", true, "email");
		campos[4] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		campos[5] = new Campo(1, req.getParameter("tipoUsuario"), true, "", true, "tipoUsuario");
		
		return campos;
	}

	public static Campo[] getAlterarSenhaUsuarioActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[4];

		campos[0] = new Campo(8, req.getParameter("senhaNova"), true, "", true, "senhaNova");
		campos[1] = new Campo(0, req.getParameter("senhaConfirmar"), true, "", true, "senhaConfirmar");
		campos[2] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[3] = new Campo(0, req.getParameter("senhaAtual"), true, "", true, "senhaAtual");
		
		return campos;
	}

	public Campo[] alterarCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[5]; 

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[2] = new Campo(7, req.getParameter("email"), true, "", true, "email");
		campos[3] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		campos[4] = new Campo(1, req.getParameter("tipoUsuario"), true, "", true, "tipoUsuario");

		return campos;
	}

	public Campo[] alterarStatusCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[2];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}
	
	public Usuario instancia(Campo[] campos) {
		String nome = campos[2].getValor();
		String email = campos[3].getValor();
        int status = Integer.parseInt(campos[4].getValor());
        String senha = campos[0].getValor();
        TipoUsuario tipoUsuario = new TipoUsuario(Long.parseLong(campos[5].getValor()), new Date(), "", "");

    	Usuario usuario = new Usuario((long)1, new Date(), nome, email, status, senha, tipoUsuario);
    	return usuario;
	}
}