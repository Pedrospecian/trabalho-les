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
import utils.ResultadosBusca;
import viewHelpers.ClienteViewHelper;
import viewHelpers.UsuarioViewHelper;
import viewHelpers.PaginacaoViewHelper;
import viewHelpers.LoginViewHelper;
import model.Genero;
import model.TipoCliente;
import model.TipoDocumento;

public class ListagemClientes extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
        LoginViewHelper lvh = new LoginViewHelper();
        if(!lvh.isAuthorized(req, resp, 1)){
            resp.sendRedirect("/trabalho-les/home");
        }else{
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            Campo[] campos = ClienteViewHelper.getListagemClientesCampos(req);

            FachadaCliente fachada = new FachadaCliente();
            FachadaSelect fachadaS = new FachadaSelect();
            ResultadosBusca resultadosBusca = fachada.select(campos);  

            ArrayList<Genero> generos = fachadaS.getOpcoesSelect(1);
            ArrayList<TipoCliente> tiposcliente = fachadaS.getOpcoesSelect(2);
            ArrayList<TipoDocumento> tiposdocumento = fachadaS.getOpcoesSelect(3);

            System.out.println(campos[8]);                
            System.out.println(campos[8].getValor());
            String[] linksPaginacao = PaginacaoViewHelper.createLinksPaginacao(campos[8], resultadosBusca);

            req.setAttribute("registros", resultadosBusca.getResultados());
            req.setAttribute("total", resultadosBusca.getContagemTotal());
            req.setAttribute("linksPaginacao", linksPaginacao);
            req.setAttribute("campos", campos);

            req.setAttribute("generos", generos);
            req.setAttribute("tiposcliente", tiposcliente);
            req.setAttribute("tiposdocumento", tiposdocumento);

            req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
            req.getRequestDispatcher("cliente/listagemClientes.jsp").forward(req, resp); 
        }
	}
}
