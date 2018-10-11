package model.data_structures;

import java.util.Iterator;

public class Iter <T> implements Iterator<T>
{
	public Node<T> current;

	public Iter(Node<T> firstNode)
	{
		current = firstNode;
	}

	@Override
	public boolean hasNext() 
	{
		if(current != null)
			{
			return current.getNext() != null;
			}
		else
			
		{
			return false;
		}
	}

	@Override
	public T next() 
	{
		current = current.getNext();
		return current.getElement() ;
	}



}