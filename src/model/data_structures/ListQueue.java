package model.data_structures;
// TODO MODIFICAR EL METODO ANTERIOR CADA VEZ QUE SE ENCOLE o se desencole

public class ListQueue <T> implements IQueue <T> {

	private Node<T> first;
	private Node<T> last;
	private int size = 0;
	@Override
	public Iter<T> iterator() {
		return new Iter<T>(first);
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void enqueue(T t) {
		Node<T> newNode = new Node<>(t);
		if(isEmpty())
		{
			first = newNode;
			last = newNode;
		}
		else
		{
			Node<T> oldLast = last;
			oldLast.changeNext(newNode);
			last = newNode;
		}
		size++;
		
	}

	@Override
	public T dequeue() {
		if(isEmpty())
		{
			throw new NullPointerException("lista vacia");
		}
		else
		{
			Node<T> oldFirst = first;
			T element = first.getElement();
			first = oldFirst.getNext();
			oldFirst.changeNext(null);
			size--;
			return element;
		}
	}

}
