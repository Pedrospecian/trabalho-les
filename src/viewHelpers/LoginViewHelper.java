package viewHelpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import utils.Campo;

public class LoginViewHelper {
	public boolean isAuthorized(HttpServletRequest req, HttpServletResponse resp, int tipoLogin) {
		//redireciona o usuario caso ele nao esteja logado
		Cookie[] cookies = req.getCookies();

		Campo[] campos = new Campo[4];

		if(cookies == null) {
			return false;
		}

		if(cookies.length < 4) {
			return false;
		}

		campos[0] = new Campo(0, "", true, "", true, "JSESSIONID");
		campos[1] = new Campo(0, "", true, "", true, "login_id");
		campos[2] = new Campo(0, "", true, "", true, "nome");
		campos[3] = new Campo(0, "", true, "", true, "tipo");

		for (int i = 0; i < campos.length; i++) {
			campos[i].setValor(cookies[i].getValue());
			if (campos[i].getValor() == null || campos[i].getValor().equals("")) {
				return false;
			}
		}

		if (tipoLogin == 1 && !(campos[3].getValor().equals("funcionario") || campos[3].getValor().equals("admin")) ) {
			return false;
		}

		if (tipoLogin == 2 && !campos[3].getValor().equals("cliente")) {
			return false;
		}

		if (tipoLogin == 3 && !campos[3].getValor().equals("admin")) {
			return false;
		}

		if (tipoLogin == 4 && !campos[3].getValor().equals("gerentevendas")) {
			return false;
		}

		if (tipoLogin == 5 && !(campos[3].getValor().equals("funcionario") || campos[3].getValor().equals("admin") || campos[3].getValor().equals("gerentevendas")) ) {
			return false;
		}

		return true;
	}

	public String getHeader(HttpServletRequest req, HttpServletResponse resp, int tipo) {
		String header = "";
		String username = this.getUsuarioLogadoNome(req, resp);

		boolean auth = this.isAuthorized(req, resp, tipo);

		if (tipo == 2) {
			if (auth) {
				//cliente logado
				header = "<div class='container'><div class='d-flex'><a href='/trabalho-les/home' cypress-headerpaginainicial>Home</a><div class='busca'><form class='busca-form' method='get' action='/trabalho-les/busca'><input type='text' name='term' cypress-term /><button cypress-busca>Pesquisar</button></form></div><div class='user-nome'><p>Logado(a) como " + username + "</p><a href='/trabalho-les/minhaConta' cypress-minhaConta>Minha conta</a><br><a href='/trabalho-les/logout' cypress-logout>Logout</a></div></div></div>";
			} else {
				//cliente nao logado
				header = "<div class='container'><div class='d-flex'><a href='/trabalho-les/home' cypress-headerpaginainicial>Home</a><div class='busca'><form class='busca-form' method='get' action='/trabalho-les/busca'><input type='text' name='term' cypress-term /><button cypress-busca>Pesquisar</button></form></div><a href='/trabalho-les/login' cypress-login>Login</a></div></div>";
			}		
		}

		if (tipo == 1 || tipo == 5) {
			if (auth) {
				//admin logado
				header = "<div class='container'><div class='d-flex'><a href='/trabalho-les/homeAdmin' cypress-homeAdmin>Home do admin</a><div class='user-nome'><p>Logado(a) como " + username + "</p><a href='/trabalho-les/logout' cypress-logout>Logout</a></div></div></div>";
			} else {
				//admin nao logado
				header = "<div class='container'><div class='d-flex'><a href='/trabalho-les/homeAdmin' cypress-homeAdmin>Home do admin</a><a href='/trabalho-les/loginAdmin' cypress-loginAdmin>Login no admin</a></div>";
			}
		}

		return header;
	}

	public long getUsuarioLogadoId(HttpServletRequest req, HttpServletResponse resp) {
		long id = 0;
		Cookie[] cookies = req.getCookies();

		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("login_id") && cookies[i].getValue().matches("^[0-9]+$")) {
					id = Long.parseLong(cookies[i].getValue());
					break;
				}
			}
		}

		return id;
	}

	public String getUsuarioLogadoNome(HttpServletRequest req, HttpServletResponse resp) {
		String nome = "";
		Cookie[] cookies = req.getCookies();

		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("nome")) {
					nome = cookies[i].getValue();
					break;
				}
			}
		}

		return nome;
	}

	public static String getLogInfo(HttpServletRequest req, HttpServletResponse resp) {
		String[] loginfo = new String[3];
		Cookie[] cookies = req.getCookies();

		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("nome")) {
					loginfo[0] = cookies[i].getValue();
				}
				if (cookies[i].getName().equals("login_id")) {
					loginfo[1] = cookies[i].getValue();
				}
				if (cookies[i].getName().equals("tipo")) {
					loginfo[2] = cookies[i].getValue();
				}
			}
		}

		return loginfo[0] + " (id: " + loginfo[1] + ", tipo: " + loginfo[2] + ")";
	}
}