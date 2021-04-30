package viewHelpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;

public class UsuarioViewHelper {
	public static Campo[] getListagemUsuariosCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[5];

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[1] = new Campo(0, req.getParameter("email"), true, "", true, "email");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		campos[3] = new Campo(999, req.getParameter("paginaAtual"), true, "", true, "paginaAtual");
		campos[4] = new Campo(999, req.getParameter("resultadosPorPagina"), true, "", true, "resultadosPorPagina");

		return campos;
	}

	public static Campo[] getCadastroUsuarioActionCampos(HttpServletRequest req) {
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

	public static Campo[] getAlterarUsuarioActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[5]; 

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[2] = new Campo(7, req.getParameter("email"), true, "", true, "email");
		campos[3] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		campos[4] = new Campo(1, req.getParameter("tipoUsuario"), true, "", true, "tipoUsuario");

		return campos;
	}

	public static Campo[] getAlterarUsuarioStatusActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[2];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}
}