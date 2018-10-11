package model.data_structures;

//TODO MODIFICAR EL METODO ANTERIOR CADA VEZ QUE SE EMPILE o se Desempile
public class ListStack<T> implements IStack<T> {

	private Node<T> topStack;
	private int size = 0;
	@Override
	public Iter<T> iterator() {
		return new Iter<T>(topStack);
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
	public void push(T t) {
		Node <T> newNode = new Node<>(t);
		if(topStack == null)
		{
			topStack = newNode;
		}
		else
		{
			newNode.changeNext(topStack);
			topStack = newNode;
		}
		size++;
		
	}

	@Override
	public T pop() {
		if(topStack ==  null)
		{
			throw new NullPointerException("lista vacia");
		}
		else
		{
			T element = topStack.getElement();
			Node<T> next = topStack.getNext();
			topStack.changeNext(null);
			topStack = next;
			size--;
			return element;
		}
	}
	
	public Node<T> getTopStack()
	{
		return topStack;
	}


}