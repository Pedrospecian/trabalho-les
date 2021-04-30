package dao;

import java.util.List;

import model.EntidadeDominio;

public interface IDAO<E, T> {

	public abstract List<EntidadeDominio> select(T campos);
	public abstract void insert(E entidade);
	public abstract void update(E entidade);
	public abstract void delete(long id);

}
