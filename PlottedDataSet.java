import java.awt.Color;

/**
 * creates points for one country
 */
public class PlottedDataSet {
	private LinkedList<ColoredPoint> dataSet;
	
	/*
	 * constuctor for this class
	 */
	public PlottedDataSet(LinkedList<Country> countryList){
		LinkedList<ColoredPoint> dataSet = new LinkedList<ColoredPoint>();
		
		for(int i = 0; i < countryList.getSize(); i++) {
			Country newCountry = countryList.getDataAtIndex(i);
			LinkedList<SubscriptionYear> data = newCountry.getSubscriptions();
			
			Node<SubscriptionYear> Walker = data.getFirstNode();
			Color pointColor = new Color((int)(Math.random() * 0x1000000));
			
			while(Walker.isLastNode() != true){ //for each country
				double originalX = Walker.getData().getYear();
				double originalY = Walker.getData().getSubscription();
				int mappedX = (int)map(originalX, 0.0, 300.0, 1960.0, 2012.0);
				int mappedY = (int)map(originalY, 0.0, 300.0, 1960.0, 2012.0);
				ColoredPoint newPoint = new ColoredPoint(pointColor, originalX, originalY, mappedX, mappedY);
				
				dataSet.add(newPoint);
			}
		}
	}
	
	static public final double map(double value,
		    double dataMin, double dataMax,
		    double plottedMin, double plottedMax)
	{
	    return plottedMin + (plottedMax - plottedMin) * ((value - dataMin) / (dataMax - dataMin));
	}
	
	/*
	 * gets dataSet
	 */
	public LinkedList<ColoredPoint> getDataSet(){
		return dataSet;
	}
}
