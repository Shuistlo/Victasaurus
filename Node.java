/**
 * class Node creates a node has parameters
 * T data Node<T> next
 * Node will be able to set following Nodes, check equivalence of nodes,
 * and check which nodes are next in the list
 */
public class Node<T> {
	
	private T data;
	private Node<T> next;
	
	/**
	 * Constructs an object to hold the data
	 * and point to null as the next node.
	 * @param theData
	 */
	public Node(T theData) 
	{
		this.data = theData;
		this.next = null;
	}
	
	/**
	 * Constructs an object to hold the data
	 * and point to another element as the next node.
	 * @param theData			The data portion of this node.
	 * @param another		The node following this node.
	 */
	public Node(T theData, Node<T> another) 
	{
		this.data = theData;
		this.next = another;
	}
	
	/*
	 * gets data
	 */
	public T getData(){
		return this.data;
	}
	
	/*
	 * gets next node
	 */
	public Node<T> getNext(){
		return this.next;
	}
	
	/*
	 * sets data
	 */
	public void setData(T newData) {
		data = newData;
	}
	
	/*
	 * sets next
	 */
	public void setNext(Node<T> another){
		this.next = another;
	}
	
	/*
	 * checks to see if a node is the last node
	 */
	public boolean isLastNode(){
	    if (this.next == null)
	    {
	        return true;
	    }
	    return false;
	}
	
	/*
	 * override
	 * checks to see if a node is equal to another node
	 */
	public boolean equals(Object other) 
	{
	    if (other instanceof Node) 
	    {                           
	        Node<T> current = (Node<T>)other;
	        if (current.data.equals(this.data))
	                return true;        
	    }
	    return false;
	}
	
	/*
	 * returns a string version of a node
	 */
	public String toString(){
		String result = "";
		result += this.data;
		return result;
	}
}
