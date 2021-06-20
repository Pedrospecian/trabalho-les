package viewHelpers;

import javax.servlet.http.HttpServletRequest;
import utils.Campo;

public interface IViewHelper<E> {
	public Campo[] listagemCampos(HttpServletRequest req);

	public Campo[] cadastroCampos(HttpServletRequest req);

	public Campo[] alterarCampos(HttpServletRequest req);

	public Campo[] alterarStatusCampos(HttpServletRequest req);

	public E instancia(Campo[] campos);
}