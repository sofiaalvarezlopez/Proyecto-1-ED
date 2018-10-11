package model.data_structures;

import java.util.Iterator;


// Hecho por Sofia Alvarez para el taller 2 de datos y modificado por Daniel Serrano
public class DoublyLinkedList<T> implements IDoublyLinkedList<T > 
{
	private int amountOfNodes;
	private Node<T> firstNode;
	private Node<T> nextNode;

	/**
	 * Creates an empty list.
	 * <b>post:</b> The first node has been initialized in null.
	 */
	public DoublyLinkedList()
	{
		firstNode = null;
		nextNode = null;
		amountOfNodes = 0;
	}

	/**
	 * Creates a new Linked List with the node in the parameter.
	 * @param firstNode First Node of the Linked List.
	 * @throws NullPointerException if the parameter is null
	 * <b>post:</b> The amount of nodes has been set to 1.
	 */
	public DoublyLinkedList(Node<T> firstNode)
	{
		if(firstNode == null)
		{
			throw new NullPointerException("The element received is null");
		}
		this.firstNode = firstNode;
		amountOfNodes = 1;
	}

	public int getAmountOfNodes()
	{
		return amountOfNodes;
	}


	public Iterator<T> iterator() 
	{
		return new Iter<T>(firstNode);
	}	

	public Iterator<T> listIterator(int k) 
	{
		if(k< 0 || k >= size())
			throw new IndexOutOfBoundsException("The index is out of the bounds of the list");
		return new Iter<T>(getNode(k));
	}


	@Override
	public boolean addAtBeginning(T element)
	{
		if(element == null)
		{
			throw new NullPointerException("Null element received");
		}
		else
		{
			if(firstNode == null)
			{
				firstNode = new Node<T>(element);
				amountOfNodes++;
				return true;
			}
			else
			{				
				Node<T> current = firstNode;
				Node<T> insertFirst = new Node<T>(element);
				insertFirst.changeNext(current);
				current.changePrevious(insertFirst);
				firstNode = insertFirst;
				nextNode = current;
				amountOfNodes++;
				return true;
			}
		}
	}

	@Override
	public boolean addAtEnd(T element) 
	{
		if(element == null)
		{
			throw new NullPointerException("Null element received");
		}
		else
		{
			if(firstNode == null)
			{
				firstNode = new Node<T>(element);
				amountOfNodes++;
				return true;
			}
			else
			{
				Node<T> current = firstNode;
				while(current.getNext() != null)
				{
					current = current.getNext();
				}
				Node<T> temp = new Node<T>(element);
				current.changeNext(temp);
				temp.changePrevious(current);
				amountOfNodes++;
				return true;
			}
		}
	}

	@Override
	public void addAtK(int k, T element) 
	{
		if (element == null)
		{
			throw new NullPointerException("Se recibe un elemento nulo");
		}
		if(k < 0 || k > amountOfNodes)
		{
			throw new IndexOutOfBoundsException("Se estÃƒÂ¡ pidiendo el indice: " + k + " y el tamaÃƒÂ±o de la lista es de " + amountOfNodes);
		}
		Node<T> current = firstNode;
		Node<T> newNode = new Node<T>(element);

		if(firstNode == null && k == 0)
		{
			firstNode = new Node<T>(element);
			amountOfNodes++;
		}

		else if (k == 0)
		{
			newNode.changeNext(current);
			current.changePrevious(newNode);
			firstNode = newNode;
			amountOfNodes++;
		}
		else
		{
			for(int i = 0; i < k; i++)
			{
				current = current.getNext();
			}
			newNode.changePrevious(current.getPrevious());
			current.getPrevious().changeNext(newNode);
			newNode.changeNext(current);
			current.changePrevious(newNode);;
			amountOfNodes++;
		}
		// TODO Auto-generated method stub
	}

	@Override
	public T getElement(int k)
	{
		if(k < 0 || k > amountOfNodes)
		{
			throw new IndexOutOfBoundsException("The requested index: " + k + 
					" is out of the bounds of the list with " + amountOfNodes + " elements.");
		}
		else 
		{
			if(k == 0 && firstNode != null)
			{
				return firstNode.getElement();
			}
			else
			{
				Node<T> current = firstNode;
				if (current != null) 
				{
					for (int i = 0; i < k; i++)
					{
						current = current.getNext();
					}
					return current.getElement();
				}
				else
				{
					return null;
				}
			}

		}
	}


	public Node<T> getNode(int k) 
	{
		if(k < 0 || k > amountOfNodes)
		{
			throw new IndexOutOfBoundsException("The index " + k + " is out of the bounds of the list with " + 
					amountOfNodes + " elements.");
		}
		else if(k == 0 && firstNode != null)
		{
			return firstNode;
		}
		else
		{
			Node<T> currentNode = firstNode;
			int count = 0;
			while(currentNode != null && count < k)
			{
				currentNode = currentNode.getNext();
				count++;
			}
			return currentNode;
		}
	}


	public int size() {
		return amountOfNodes;
	}

	@Override
	public boolean delete(Object o) throws NullPointerException 
	{
		boolean could = false;
		if(firstNode.getElement().equals(o))
		{
			firstNode = firstNode.getNext();
			amountOfNodes--;
			could = true;
		}
		else
		{
			Node<T> currentNode = firstNode;
			while(currentNode.getNext().getNext() != null && !could)
			{
				if(currentNode.getNext().getElement().equals(o))
				{
					Node<T> newNext = currentNode.getNext().getNext();
					nextNode = currentNode.getNext();
					nextNode.changePrevious(currentNode);
					currentNode.changeNext(currentNode.getNext().getNext());
					newNext.changePrevious(currentNode);
					currentNode.getNext().changeElement(null);
					amountOfNodes--;
					could = true;
				}
				currentNode = currentNode.getNext();
			}
			//In case the element to be erased is in the penultimate position of the list.
			if(currentNode.getElement().equals(o))
			{
				Node<T> newPrevious = currentNode.getPrevious();
				currentNode.getPrevious().changeNext(currentNode.getNext());
				currentNode.getNext().changePrevious(newPrevious);
				amountOfNodes--;
				could = true;
			}
			//In case the element to be erased is the last one in the list.
			else if(currentNode.getNext().getElement().equals(o))
			{
				currentNode.changeNext(null);
				amountOfNodes--;
				could = true;
			}

		}
		return could;
	}

	@Override
	public T deleteAtK(int k) throws IndexOutOfBoundsException 
	{
		Node<T> delete;
		if(k < 0 || k > amountOfNodes)
		{
			throw new IndexOutOfBoundsException("The index " + k + " is out of the bounds "
					+ "of the list with " + amountOfNodes + " elements");
		}
		else if(k == 0 && firstNode != null)
		{
			delete = firstNode;
			firstNode = firstNode.getNext();
			amountOfNodes--;
			return delete.getElement();
		}
		else
		{
			Node<T> current = firstNode;
			int count = 0;
			while(count < k)
			{
				current = current.getNext();
				count++;
			}
			//If it is the last element of the list
			if(current.getNext() == null)
			{
				delete = current;
				current.getPrevious().changeNext(null);
				amountOfNodes--;
				return delete.getElement();
			}
			else
			{
				delete = current;
				Node<T> newPrevious = current.getPrevious();
				current.getPrevious().changeNext(current.getNext());
				current.getNext().changePrevious(newPrevious);
				return delete.getElement();
			}		
		}
	}
	@Override
	public boolean deleteAtBeginning() 
	{
		boolean pude = false;
		Node<T> actual = (Node<T>) firstNode;
		if(actual == null)
		{
			throw new NullPointerException("lista vacia");
		}
		else
		{
			firstNode = (Node<T>) actual.getNext();
			actual.changeElement(null);
			amountOfNodes--;
			pude = true;
		}		
		return pude;
	}

	/**
	 * Creates a lists with the elements between the indexes given
	 * @param start the position of the first element of the sublist, it is included in the sublist.
	 * @param end the position of the last element of the sublist, it is not included in the sublist
	 * @return a list with the elements between the given positions
	 * @throws IndexOutOfBoundsException If start < 0 or end >= size() o end < start
	 */
	public DoublyLinkedList<T> subList(int start, int end) 
	{
		DoublyLinkedList <T> list = new DoublyLinkedList<T>();
		Node<T> actual = firstNode;
		if(start < 0 || end >= amountOfNodes || end < start)
		{
			throw new IndexOutOfBoundsException("Valores de inicio y fin invalidos");
		}
		else
		{
			for(int i = 0; i< start; i++)
			{
				actual = actual.getNext();
			}
			for(int i = start; i <= end; i++)
			{
				list.addAtEnd(actual.getElement());
				actual = actual.getNext();	
			}
		}
		return list;
	}


}
