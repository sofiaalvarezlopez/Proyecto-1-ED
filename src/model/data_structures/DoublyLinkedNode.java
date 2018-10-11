package model.data_structures;
/**
 * Abstract Data Type for a doubly-linked node of generic objects
 * This ADT should contain the basic operations to manage a node
 *
 *   next, previous, getCurrentElement
 * @param <E>
 */
public interface DoublyLinkedNode<T> {

	Node<T> getNext();
	Node<T> getPrevious();
	T getElement();
}
