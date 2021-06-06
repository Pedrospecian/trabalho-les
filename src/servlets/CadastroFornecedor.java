package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viewHelpers.LoginViewHelper;
import facades.FachadaSelect;

import model.TipoDocumento;
import model.TipoEndereco;
import model.TipoResidencia;
import model.FuncaoEndereco;
import model.TipoLogradouro;

public class CadastroFornecedor extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaSelect fachadaSel = new FachadaSelect();

			ArrayList<TipoDocumento> tiposdocumento = fachadaSel.getOpcoesSelect(3);
			ArrayList<TipoEndereco> tiposendereco = fachadaSel.getOpcoesSelect(4);
			ArrayList<TipoResidencia> tiposresidencia = fachadaSel.getOpcoesSelect(5);
			ArrayList<FuncaoEndereco> funcoesendereco = fachadaSel.getOpcoesSelect(6);
			ArrayList<TipoLogradouro> tiposlogradouro = fachadaSel.getOpcoesSelect(7);

			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));

			req.setAttribute("tiposdocumento", tiposdocumento);
			req.setAttribute("tiposendereco", tiposendereco);
			req.setAttribute("tiposresidencia", tiposresidencia);
			req.setAttribute("funcoesendereco", funcoesendereco);
			req.setAttribute("tiposlogradouro", tiposlogradouro);
				
			req.getRequestDispatcher("fornecedor/cadastroFornecedor.jsp").forward(req, resp);
		}
	}
}
