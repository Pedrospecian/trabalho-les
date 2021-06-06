package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viewHelpers.LoginViewHelper;
import facades.FachadaSelect;
import model.Genero;
import model.TipoCliente;
import model.TipoDocumento;
import model.TipoEndereco;
import model.TipoResidencia;
import model.FuncaoEndereco;
import model.TipoLogradouro;
import model.Bandeira;
import model.TipoTelefone;

public class NovaConta extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		FachadaSelect fachada = new FachadaSelect();

		ArrayList<Genero> generos = fachada.getOpcoesSelect(1);
		ArrayList<TipoCliente> tiposcliente = fachada.getOpcoesSelect(2);
		ArrayList<TipoDocumento> tiposdocumento = fachada.getOpcoesSelect(3);
		ArrayList<TipoEndereco> tiposendereco = fachada.getOpcoesSelect(4);
		ArrayList<TipoResidencia> tiposresidencia = fachada.getOpcoesSelect(5);
		ArrayList<FuncaoEndereco> funcoesendereco = fachada.getOpcoesSelect(6);
		ArrayList<TipoLogradouro> tiposlogradouro = fachada.getOpcoesSelect(7);
		ArrayList<Bandeira> bandeiras = fachada.getOpcoesSelect(8);
		ArrayList<TipoTelefone> tipostelefone = fachada.getOpcoesSelect(9);

		req.setAttribute("actionForm", "novaContaAction");
		req.setAttribute("headerHTML", lvh.getHeader(req, resp, 2));
		req.setAttribute("generos", generos);
		req.setAttribute("tiposcliente", tiposcliente);
		req.setAttribute("tiposdocumento", tiposdocumento);
		req.setAttribute("tiposendereco", tiposendereco);
		req.setAttribute("tiposresidencia", tiposresidencia);
		req.setAttribute("funcoesendereco", funcoesendereco);
		req.setAttribute("tiposlogradouro", tiposlogradouro);
		req.setAttribute("bandeiras", bandeiras);
		req.setAttribute("tipostelefone", tipostelefone);
		req.getRequestDispatcher("cliente/cadastroCliente.jsp").forward(req, resp); 
	}
}
