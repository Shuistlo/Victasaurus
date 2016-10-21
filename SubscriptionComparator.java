import java.util.Comparator;

public class SubscriptionComparator implements Comparator<SubscriptionYear>{

	@Override
	public int compare(SubscriptionYear sub1, SubscriptionYear sub2) {
		if(sub1.getSubscription() < sub2.getSubscription()) {
			return -1;
		}
		if(sub1.getSubscription() > sub2.getSubscription()){
			return 1;
		}
		
		return 0;
	}

}
