package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facades.FachadaCliente;
import facades.FachadaSelect;
import utils.Campo;
import model.Cliente;
import model.Genero;
import model.TipoCliente;
import model.TipoDocumento;
import model.TipoEndereco;
import model.TipoResidencia;
import model.FuncaoEndereco;
import model.TipoLogradouro;
import model.Bandeira;
import model.TipoTelefone;
import viewHelpers.LoginViewHelper;

public class AlterarMeusDados extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 2)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			FachadaCliente fachada = new FachadaCliente();
			Campo[] campos = new Campo[1];
			campos[0] = new Campo(1, String.valueOf(lvh.getUsuarioLogadoId(req, resp)), true, "", true, "id");

			if(fachada.validarCampos(campos)) {
				Cliente cliente = fachada.selectSingle(Long.parseLong(campos[0].getValor()), false);

				FachadaSelect fachadaSel = new FachadaSelect();

				ArrayList<Genero> generos = fachadaSel.getOpcoesSelect(1);
				ArrayList<TipoCliente> tiposcliente = fachadaSel.getOpcoesSelect(2);
				ArrayList<TipoDocumento> tiposdocumento = fachadaSel.getOpcoesSelect(3);
				ArrayList<TipoEndereco> tiposendereco = fachadaSel.getOpcoesSelect(4);
				ArrayList<TipoResidencia> tiposresidencia = fachadaSel.getOpcoesSelect(5);
				ArrayList<FuncaoEndereco> funcoesendereco = fachadaSel.getOpcoesSelect(6);
				ArrayList<TipoLogradouro> tiposlogradouro = fachadaSel.getOpcoesSelect(7);
				ArrayList<Bandeira> bandeiras = fachadaSel.getOpcoesSelect(8);
				ArrayList<TipoTelefone> tipostelefone = fachadaSel.getOpcoesSelect(9);

				req.setAttribute("cliente", cliente);
				req.setAttribute("actionForm", "alterarMeusDadosAction");

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
			
				req.getRequestDispatcher("cliente/editarCliente.jsp").include(req, resp); 
			} else {

			}
	}
	}
}
