/**
 * class Country presents a row in CellularData.CSV
 * Country has parameters
 * name, name of Country (String)
 * tableIndex, integer used to keep track of how many values are in Country
 * subscriptions[], an array holding the subscriptions for country (SubscriptionYear)
 * 
 */
public class Country {
	private LinkedList<SubscriptionYear> subscriptions;
	private String name; 
	private int minYear;
	private int maxYear;

	
	/*
	 * default constructor
	 */
	Country(String countryName, int numberOfYears) {
		name = countryName;
		subscriptions = new LinkedList<SubscriptionYear>();
	}
	
	/*
	 * constructor used for CountryNode and CountryList class
	 * only takes name
	 */
	Country(String countryName) {
		name = countryName;
		minYear = 9999;
		maxYear = 0;
		subscriptions = new LinkedList<SubscriptionYear>();
	}

	/*
	 * gets country name
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * sets country name
	 */
	public void setName(String newName){
		name = newName;
	}
	
	public LinkedList<SubscriptionYear> getSubscriptions(){
		return this.subscriptions;
	}
	
	/*
	 * adds first subscription
	 */
	public void addFirstSubscription(int newYear, double countryData){
		SubscriptionYear current = new SubscriptionYear(newYear, countryData);
		if(subscriptions.isEmpty() == true){
			subscriptions.addFirstNode(current);
			this.minYear = current.getYear();
		}
	}
	
	/*
	 * adds a subscription to the list
	 */
	public void addSubscriptionYear(int newYear, double countryData){
		if(subscriptions.isEmpty() == true){
			addFirstSubscription(newYear, countryData);
		}
		else{
			SubscriptionYear current = new SubscriptionYear(newYear, countryData);
			subscriptions.add(current);
			this.maxYear = current.getYear();
		}
	}
	
	/*
	 *gets the index of a year in subscriptions linkedlist
	 */
	public int getYearIndex(int yearOfSubscription) {
		int result = -1;
		Node<SubscriptionYear> walker = subscriptions.getFirstNode();
		for(int index = 0; index < subscriptions.getSize(); index++){
			if(walker.getData().getYear() == yearOfSubscription){
				result = index;
			}
			walker = walker.getNext();
		}
		return result;
	}
	
	/*
	 * sums all subscriptions between the two years specified by the parameters
	 * parameter startYear specifies the start year 
	 * parameter endYear specifes the end year
	 * will catch exception if endYear is over 2012
	 * will catch exception if startYear is under 1960
	 */
	public double getNumSubscriptionsForPeriod(int startYear, int endYear) {
		if(startYear < minYear){
			System.out.println("ERROR : Invalid Starting Year!");
		}
		if(endYear > maxYear){
			System.out.println("ERROR : Invalid Ending Year!");
		}
		int startIndex;
		startIndex = getYearIndex(startYear);
		int endIndex;
		endIndex = getYearIndex(endYear);
		double totalSubscriptions = 0.00;
		for (int i = startIndex; i < (endIndex +1); i++) {
			totalSubscriptions = totalSubscriptions + subscriptions.getDataAtIndex(i).getSubscription();
		}
		return totalSubscriptions;
	}
	
	/*
	 * override
	 * checks to see if a country is equal to another country
	 */
	public boolean equals(Object other) 
	{
	    if (other instanceof Country) 
	    {                           
	        Country current = (Country)other;
	        if(current.getName().equalsIgnoreCase(this.name)){
	        	return true;
	        }
	        /*
	        if (current.subscriptions.equals(this.subscriptions))
	                return true;  
	                */      
	    }
	    return false;
	}
	
	/*
	 * creates a string representation of subscriptions
	 * toString will append the name of the country before the data points
	 */
	public String toString() {
		StringBuilder  stringBuilder = new StringBuilder("");
		stringBuilder.append(name);
		stringBuilder.append("   ");
		stringBuilder.append(subscriptions.toString());
		String subscriptionString = stringBuilder.toString();
		return subscriptionString;
	}

}