import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

/**
 * One object of GraphView class creates a graph of ColoredPoints
 * each GraphView object has parameters
 * width, the width of the graph in pixels
 * height, the height of the graph in pixels
 * plottedXmin, the minimum value of the x coordinates
 * plottedXmax, the maximum value of the x coordinates
 * plottedYmin, the minimum value of the y coordinates
 * plottedYmax, the maximum value of the y coordinates
 * color, the color of the point
 * CountryList, the list of data to be turned into a graph
 * colors, the list of colors the points must be in
 * margin, the size of the margin for the graph
 * @author SHU
 */
public class GraphView extends JPanel implements MouseInputListener{
	private int width;
	private int height;
	private Font font;
	private static final int margin = 40;
	private double plottedXmin;
	private double plottedXmax;
	private double plottedYmin;
	private double plottedYmax;
	private LinkedList<Country> CountryList;
	private Color [] colors;
	private LinkedList<ColoredPoint> dataSet;
	private ColoredPoint pointForLabel;
	private String text;
	private String mouseAlive;
	
	/*
	 * default constructor for GraphView
	 */
	public GraphView(int newWidth, int newHeight, LinkedList<Country> Countries){	
		font = new Font("Serif", Font.PLAIN, 11);
		this.width = newWidth;
		this.height = newHeight;
		this.setPreferredSize(new Dimension(width + margin, height));
		this.plottedXmin = margin;
		this.plottedXmax = width - margin;
		this.plottedYmin = height - margin;
		this.plottedYmax = margin;
		CountryList  = Countries;
		pointForLabel = null;
		text = "No point selected.";
		mouseAlive = "Mouse dead";
		
		addMouseListener(this);
        addMouseMotionListener(this);
		
		dataSet = new LinkedList<ColoredPoint>();
	}

	/**
	 * returns highest subscription
	 */
	public static double getHighestSub (LinkedList<Country> countries) {
		double maximumY = -1.0;
		Iterator <Country> itr = countries.iterator();
		while (itr.hasNext()) {
			Country current = itr.next();
			if (getHighestSubLocal(current.getSubscriptions()) > maximumY) {
				maximumY = getHighestSubLocal(current.getSubscriptions());
			}
		}
		return maximumY;
	}
	
	/**
	 * returns double of the highest subscription from LinkedList of SubscriptionYears
	 */
	public static double getHighestSubLocal (LinkedList<SubscriptionYear> subscriptions) {
		Iterator <SubscriptionYear> itr = subscriptions.iterator();
		SubscriptionYear foundSY = itr.next();
		SubscriptionComparator compSY = new SubscriptionComparator();
		while (itr.hasNext()) {
			SubscriptionYear nextSY = itr.next();
			if (compSY.compare(foundSY, nextSY) < 0) {
				foundSY = nextSY;
			}
		}			
		return foundSY.getSubscription();
	}
	
	/**
 	 * draws the graph
 	 */
	public void paintComponent(Graphics newGraphic){
		super.paintComponent(newGraphic);
		Graphics2D newGraphic2d = (Graphics2D) newGraphic;
				
		for(int i = 0; i < CountryList.getSize(); i++) {
			Country newCountry = CountryList.getDataAtIndex(i);
			LinkedList<SubscriptionYear> data = newCountry.getSubscriptions();
			
			Node<SubscriptionYear> Walker = data.getFirstNode();
			
			Color pointColor = colors[i];
			
			while(Walker != null){ //for each country
				double originalX = Walker.getData().getYear();
				double originalY = Walker.getData().getSubscription();
				int mappedX = (int)map(originalX, 1960.0, 2012.0, this.plottedXmin, this.plottedXmax);
				int mappedY = (int)map(originalY, 0.0, GraphView.getHighestSub(CountryList), this.plottedYmin, this.plottedYmax);
				ColoredPoint newPoint = new ColoredPoint(pointColor, originalX, originalY, mappedX, mappedY);
				newPoint.setCountry(newCountry.getName());
				newGraphic2d.setColor(newPoint.getColor());	
				newGraphic2d.fillOval((int)newPoint.getMappedX(), (int)newPoint.getMappedY(), 8, 8);
				newGraphic2d.drawString(newPoint.getLabel(), (int)newPoint.getX(),(int)newPoint.getY());	
				
				dataSet.add(newPoint);
				Walker = Walker.getNext();
			}
		}
		
		newGraphic.setColor(Color.BLACK);
		newGraphic2d.drawLine((int)plottedXmin, (int)plottedYmin, (int)plottedXmax, (int)plottedYmin);
		newGraphic2d.drawLine((int)plottedXmin, (int)plottedYmin, (int)plottedXmin, (int)plottedYmax);
		newGraphic2d.drawString("Year", (int)(width/2), (int)(height - (margin/5)));
		newGraphic2d.drawString("Cellular Graph", (int)(width/2), (int)margin);
		newGraphic2d.drawString("1960", margin, (int)(height - (margin/5)));
		newGraphic2d.drawString("2012", width - margin, (int)(height - (margin/5)));
		newGraphic2d.drawString(this.text, 1, margin/2);
		newGraphic2d.drawString(this.mouseAlive, width - 30, margin/2);

		AffineTransform at = new AffineTransform();
	    at.setToRotation(Math.PI * 3.0 / 2.0);
	    newGraphic2d.setTransform(at);
	    newGraphic2d.drawString("Number of Subscriptions (per 100 people)", -400, 30);
	}
	
	/*
	 * turns original data into coordinates usable by the paintComponent() method
	 */
	static public final double map(double value,
		    double dataMin, double dataMax,
		    double plottedMin, double plottedMax)
	{
	    return plottedMin + (plottedMax - plottedMin) * ((value - dataMin) / (dataMax - dataMin));
	}
	
	/*
	 * gets plottedXmin
	 */
	public double getplottedXmin(){
		return plottedXmin;
	}
	
	/*
	 * gets plottedXmax
	 */
	public double getplottedXmax(){
		return plottedXmax;
	}
	
	/*
	 * gets plottedYmin
	 */
	public double getplottedYmin(){
		return plottedYmin;
	}
	
	/*
	 * gets plottedYmax
	 */
	public double getplottedYmax(){
		return plottedYmax;
	}
	
	/*
	 * returns the point for the label
	 */
	public ColoredPoint getPointForLabel(){
		return pointForLabel;
	}
	
	/*
	 * returns the LinkedList dataSet
	 */
	public LinkedList<ColoredPoint> getDataSet(){
		return dataSet;
	}
	
	/*
	 * sets the color list
	 */
	public void setColors(Color [] newColors) {
		this.colors = newColors;
	}
	
	public ColoredPoint findMatch(ColoredPoint newPoint){
		ColoredPoint match = null;

		Iterator<ColoredPoint> itr = dataSet.iterator();
		try{
			ColoredPoint foundPoint = itr.next();
			while(itr.hasNext()){
				foundPoint = itr.next();
				if((Math.abs(foundPoint.getMappedX() - newPoint.getMappedX()) < 5) && (Math.abs(foundPoint.getMappedY() - newPoint.getMappedY()) < 5)){
					match = foundPoint;
				}
			}
		}
		catch(NoSuchElementException ex){
			System.out.println("YOU HAVE NO DATA");
		}
		return match;
	}
	
	/*
	 * updates the text to be the toString method for the pointForLabel
	 */
	 public void updateText(){
	        if (pointForLabel == null) 
	        {
	            text = "No point selected.";
	        } 
	        else{
	        	text = pointForLabel.toString();
	        }
	    }

	@Override
	public void mouseClicked(MouseEvent e) {	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.mouseAlive = "Mouse alive";
		repaint();
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.mouseAlive = "Mouse dead";
		repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
        int y = e.getY();
        ColoredPoint findMe = new ColoredPoint(x,y);
        pointForLabel = findMatch(findMe);
        
        if (pointForLabel != null) 
        {
        	this.text = pointForLabel.toString();
            repaint();
        }
        else{
            this.text = "No point selected.";
            repaint();
        }

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
        int y = e.getY();
        ColoredPoint findMe = new ColoredPoint(x,y);
        pointForLabel = findMatch(findMe);
        
        if (pointForLabel != null) 
        {
        	this.text = pointForLabel.toString();
            repaint();
        }
        else{
            this.text = "No point selected.";
            repaint();
        }

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		//do nothing
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		//do nothing
		
	}
}
