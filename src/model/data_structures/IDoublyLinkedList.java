package model.data_structures;

/**
 * Abstract Data Type for a doubly-linked list of generic objects
 * This ADT should contain the basic operations to manage a list
 * add, addAtEnd, AddAtK, getElement, getCurrentElement, getSize, delete, deleteAtK
 * next, previous
 * @param <T>
 */
public interface IDoublyLinkedList<T> extends Iterable<T> {

	boolean addAtBeginning(T e);
	boolean addAtEnd(T e);
	void addAtK(int index, T element);
	T getElement(int e);
	int size();
	boolean deleteAtBeginning();
	boolean delete(Object o);
	T deleteAtK(int index);	
	Node<T> getNode(int index);

}
