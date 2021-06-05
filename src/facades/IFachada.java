package facades;

import utils.ResultadosBusca;

public interface IFachada<E, T> {

	public abstract ResultadosBusca select(T campos);
	public abstract String insert(E entidade, String usuarioResponsavel);
	public abstract String delete(E entidade, String usuarioResponsavel);
	public abstract String update(E entidade, String usuarioResponsavel);

}
