/**
 * Class DLList represents a doubly linked list that contains T generic type elements
 * and implements the DLListADT
 * 
 * @author zhong6688
 *
 * @param <T> generic type element
 */
public class DLList<T> implements DLListADT<T> {
	private DLNode<T> front;
	private DLNode<T> rear;
	private int count;
	
	/**
	 * Constructs a doubly linked list
	 */
	public DLList() {
		this.front = null;
        this.rear = null;
        this.count = 0;
	}

	/**
	 * Adds one node storing the given dataItem and value to the rear of the 
	 * doubly linked list
	 * 
	 * @param  dataItem and value to be added to the doubly linked list
	 */
	public void insert(T dataItem, int value) {
		DLNode<T> newNode = new DLNode<T>(dataItem,value);
		if(isEmpty()) {
			front = newNode;
			rear = newNode;
			front.setPrevious(null);
			front.setNext(null);
		}
		
		else {
			rear.setNext(newNode);
			newNode.setPrevious(rear);
			rear = newNode;
		}
		count++;
	}

	/**
	 * Returns the value of the given dataItem and throws an InvalidDataItemException if
	 * no node of the list stores the given dataItem
	 * 
	 * @param dataItem whose value is being looked for
	 * @return value of this dataItem
	 */
	public int getDataValue(T dataItem) throws InvalidDataItemException{
		int found = 0;
		DLNode<T> current = front;
		
		while(current != null){
			if (current.getData().equals(dataItem)) {
				found = current.getValue();
				break;
			}
			
			current = current.getNext();
			
		}
		if ((current) == null)
			throw new InvalidDataItemException("The Data item was not found.");
		
		return found;
	}
	
	/**
	 * Changes the value associated to the given dataItem to the new value and throws an  
	 * InvalidDataItemException if no node of the list contains the given dataItem
	 * 
	 * @param dataItem whose value is to be changed
	 * @param newValue new value for this dataItem
	 */
	public void changeValue(T dataItem, int newValue) throws InvalidDataItemException{
		DLNode<T> current = front;
		boolean found = false;
		while(current != null){
			if (current.getData().equals(dataItem)) {
				current.setValue(newValue);
				found = true;
			}
			
			current = current.getNext();
		}
			if (found == false)
				throw new InvalidDataItemException("The Data item was not found.");
	}
	
	/**
	 * This method deletes a node from the list
	 * @param delete is the node to be deleted
	 */
	private void deleteNode(DLNode<T> delete) {
		if (delete.getPrevious() != null)
			(delete.getPrevious()).setNext(delete.getNext());
		else
			front = delete.getNext();

		if (delete.getNext() != null)
			(delete.getNext()).setPrevious(delete.getPrevious());
		else
			rear = delete.getPrevious();
	}
	
	/**
	 * Removes and returns the data item stored in the linked list with smallest
	 * associated value and also throws an EmptyListException if the list is empty
	 * 
	 * @return T data item in the list with smallest associated value
	 */
	public T getSmallest() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("The list is empty!");
		
		DLNode<T> current = front;
		DLNode<T> smallest= front;
		T returnData = null;
		
		while (current != null) {				
				if(current.getValue()<smallest.getValue())
					smallest = current;
				current = current.getNext();
					}
		returnData = smallest.getData();
		deleteNode(smallest);
		count--;	
		return returnData;
	}
	
	/**
	 * Returns true if this list is empty
	 * 
	 * @return true if this list is empty, false otherwise
	 */
	public boolean isEmpty() {
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the number of dataItems in this list
	 * 
	 * @return int number of dataItems in this list
	 */
	public int size() {
		return count;
	}
	
	/**
	 * Returns a string representation of this list
	 * 
	 * @return String representation of this list
	 */
	public String toString() {
	String s = "List: ";
	DLNode<T> current = front;
	
	while (current != null) {
		s = s + current.getData() + "," + current.getValue() + ";";
		current = current.getNext();
	}
	return s;
}
}
