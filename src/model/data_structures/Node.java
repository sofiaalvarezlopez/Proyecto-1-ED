package model.data_structures;

public class Node<T> implements DoublyLinkedNode<T>
{
	private T element;
	private Node<T> next;
	private Node<T> previous;

	public Node(T element)
	{
		this.element = element;
	}
	@Override
	public T getElement()
	{
		return element;
	}
	@Override
	public Node<T> getNext()
	{
		return this.next;
	}
	@Override
	public Node<T> getPrevious()
	{
		return this.previous;
	}

	public T changeElement(T newElement)
	{
		element = newElement;
		return element;
	}

	public void changeNext(Node<T> newNext)
	{
		next = newNext;
	}
	
	public void changePrevious(Node<T> newPrevious)
	{
		previous = newPrevious;
	}


}

