/**
 * class SubscriptionYear is a single data point in CellularData.CSV
 * SubscriptionYear has parameters
 * year, the year of the subscription (int)
 * subscription, the value of the subscription (double)
 * @author SHU
 */
public class SubscriptionYear { 
	private int year;
	private double subscription; 
	
	/*
	 * default constructor
	 */
	SubscriptionYear(int newYear, double newSubscription) {
		year = newYear;
		subscription = newSubscription;
		
	}
	
	/*
	 * gets year
	 */
	public int getYear() {
		return year;
	}
	
	/*
	 * gets subscription
	 */
	public double getSubscription() {
		return subscription;
	}
	
	/*
	 * sets new year
	 */
	public void setYear(int newYear) {
		year = newYear;
	}
	
	/*
	 * sets new subscription
	 */
	public void setSubscriptions(double newSubscription) {
		subscription = newSubscription;
	}
	
	/*
	 * returns a subscription based on the year
	 */
	public double getSubscriptionBasedOnYear(int year) {
		return subscription;
	}

	
	/*
	 * override
	 * checks to see if a subscriptionyear is equal to another subscriptionyear
	 */
	public boolean equals(Object other) 
	{
	    if (other instanceof SubscriptionYear) 
	    {                           
	        SubscriptionYear current = (SubscriptionYear)other;
	        if ((current.getSubscription() == this.subscription) && (current.getYear() == this.year))
	                return true;        
	    }
	    return false;
	}
	
	/*
	 * returns a string of the number of subscriptions in a year
	 */
	public String toString(){ 
		String strSubscriptions = Double.toString(subscription);
		return strSubscriptions;
	}
}
