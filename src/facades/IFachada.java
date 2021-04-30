package facades;

import utils.ResultadosBusca;

public interface IFachada<E, T> {

	public abstract ResultadosBusca select(T campos);
	public abstract String insert(E entidade);
	public abstract String delete(E entidade);
	public abstract String update(E entidade);

}
