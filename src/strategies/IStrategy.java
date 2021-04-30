package strategies;

public interface IStrategy<E, T> {
	public E processa(T object);
}