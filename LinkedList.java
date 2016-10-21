import java.util.Iterator;

/**
 * class LinkedList creates a linkedlist of Node objects
 * Node has parameters
 * Node head, the first node in the linked list
 * int length, the length of the linked list
 * CountryList will be able to
 * check if the list is empty, add nodes to different parts of the list
 * search for nodes based on index or country name
 * search to see if the linked list contains a node
 */
public class LinkedList<T> implements Iterable<T>{

	private Node<T> head;
	private int length;

	/*
	 * default constructor
	 */
	public LinkedList(){
		this.head = null;
		this.length = 0;
	}

	/*
	 * checks to see if the list is empty
	 */
	public boolean isEmpty(){
		if (this.head == null)
			return true;
		return false;
	}

	/*
	 * adds a country into the first spot of the linked list
	 * if linked list already has something in it
	 * addFirstNode will do nothing
	 */
	public void addFirstNode(T someData){
		if (this.isEmpty()){
			Node<T> current = new Node<T>(someData);
			head = current;
			this.length++;
		}
	}

	/*
	 * adds a country to the front of the list
	 */
	public void addInFront(T someData) {	
		Node<T> current = new Node<T>(someData);
		if ( this.isEmpty() ){
			head = current;
			this.length++;
			return;
		}
		current.setNext(head);
		head = current;
		this.length++;
	}
	
	/*
	 * adds a country to the back of the list
	 */
	public void addBehind(T someData) {
		Node<T> walker = head;
		while(walker.getNext() != null) {
			walker = walker.getNext();
		}
		this.length++;
		Node<T> current = new Node<T>(someData);
		walker.setNext(current);
	}
	
	/*
	 * adds a country to the list
	 * if the list is empty will add first node
	 * if the list is filled will add to the back
	 */
	public void add(T someData) {
		if (length == 0) {
			addFirstNode(someData);
		}
		else{
			addBehind(someData);
		}
	}
	
	/*
	 * gets a node at a certain index
	 */
	public Node<T> getNodeAtIndex(int index){
	    Node<T> walker = head;
	    int i = 0;
	    try {
		    while((walker != null) && (i < index)){
		        if (i == index){
		            return walker;
		        }
		        walker = walker.getNext();
		        i++;
		    }
	    }
	    catch(java.lang.NullPointerException exe){
	    	System.out.println("ERROR : Invalid index!");
	    }
	    return walker;
	}
	
	/*
	 * gets a country at a specified index
	 * invalid index will return error message
	 */
	public T getDataAtIndex(int index){
		Node<T> nodeInQuestion = getNodeAtIndex(index);
	    T dataInQuestion = null;
	    try{ dataInQuestion = nodeInQuestion.getData(); }
	    catch(java.lang.NullPointerException exe) {
	    	System.out.println("ERROR : Invalid index!");
	    }
	    return dataInQuestion;
	}
	
	/*
	 * gets the size of the countryList
	 */
    public int getSize(){
        return this.length;
    }

	/*
	 * returns the first node
	 */
	public Node<T> getFirstNode() {
		return this.head;
	}
	
	/*
	 * sets head
	 */
	public void setHead(Node<T> newHead){
		head = newHead;
	}

	/*
	 * returns the last node
	 */
	public Node<T> getLastNode() {
		Node<T> potentialLast = head;
		int i = 0;
		while(potentialLast != null && i < length)
	    {
	        if (potentialLast.getNext() == null){
	            return potentialLast;
	        }
	        potentialLast = potentialLast.getNext();
	        i++;
	    }
		return null;
	}
	
	/*
	 * inserts a country at a specified index
	 * if index exceeds list length country will just be added to the end of the list
	 */
	public void insertAtIndex(int index, T theData) {
		Node<T> insertedData = new Node<T>(theData);
		if((index > 0) && (index < (length-1))) {
			Node<T> before = getNodeAtIndex(index -1);
			Node<T> after = getNodeAtIndex(index);
			before.setNext(insertedData);
			insertedData.setNext(after);
		}
		else {
			if(index == 0) {
				addInFront(theData);
			}
			else{
				addBehind(theData);
			}
		}
	}
	
	/**
	 * checks if a data is in the linked list of T object and returns object of T
	 * @param tmpCountry
	 * @return Country
	 */
	public T contains(T newData) {
		Node<T> walker = this.head;
		while (walker != null) {
			if (walker.getData().equals(newData)) {
				return walker.getData();
			}
			walker = walker.getNext();			
		}
		return null;
	}

	
	/**
	 * override
	 * creates and returns a new ListIterator object
	 */
	public ListIterator iterator(){
		return new ListIterator();
	}
	
	/*
	 * to string method
	 */
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		Node<T> walker = head;
		
		while(walker != null){
			stringBuilder.append(walker);
			stringBuilder.append("\n");
			walker = walker.getNext();
		}
		return stringBuilder.toString();
	}
	
	private class ListIterator implements Iterator<T> 
	{
		private Node<T> current;
		
		/*
		 * iterator constructor
		 */
		public ListIterator(){	
			current = head; 
		}
		
		/*
		 * checks if next exists
		 */
		public boolean hasNext() {
			if (current == null)
				return false;
			return true;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#next()
		 * sets next
		 */
		public T next() {	
			if (current == null){
				throw new java.util.NoSuchElementException();
			}
			T data = current.getData();
			current = current.getNext();
			return data;  
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 * throws exception
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
