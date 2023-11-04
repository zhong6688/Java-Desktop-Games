/**
 * Class DLNode represents a node in a doubly linked list
 * 
 * @author zhong6688
 *
 * @param <T> generic type element
 */
public class DLNode<T> {
	
	private T dataItem;
	private DLNode<T> next;
	private DLNode<T> previous;
	private int value; 
	
	/**
	 * This method creates a node using desired values
	 * @param data is a desired data item
	 * @param value is a desired value
	 */
	public DLNode (T data, int value) {
		this.dataItem = data;
		this.value = value;
	}
	
	/**
	 * This method gets the value of the node
	 * @return returns the value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * This method gets the data item of the node
	 * @return returns the data item
	 */
	public T getData() {
	return dataItem;
	}
	
	/**
	 * This method gets the node that follows the current one
	 * @return the next node
	 */
	public DLNode<T> getNext() {
		return next;
	}
	
	/**
	 * This method gets the node that precedes the current one
	 * @return the previous node
	 */
	public DLNode<T> getPrevious() {
		return previous;
	}
	
	/**
	 * This method changes the data item in a node to another desired data item
	 * @param newData is the desired data to replace the current data item in the node
	 */
	public void setData(T newData){
		this.dataItem = newData;
	}
	
	/**
	 * This method sets the node that follows the current one
	 * @param a given node to follow the current one
	 */
	public void setNext(DLNode<T> newNext) {
		this.next = newNext;
	}
	
	/**
	 * This method sets the node that precedes the current one
	 * @param a given node to precede the current one
	 */
	public void setPrevious(DLNode<T> newPrevious) {
		this.previous = newPrevious;
	}
	
	/**
	 * This method changes the value in a node to another desired value
	 * @param newValue is the desired value to replace the current value in the node
	 */
	public void setValue(int newValue) {
		this.value = newValue;
	}
}
