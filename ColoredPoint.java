import java.awt.Color;
import java.awt.Point;

/**
 * One object of ColoredPoint class is SubscriptionYear of Country
 * each ColoredPoint object has parameters
 * color, the color of the point
 * originalX, the original Year value of the point
 * orignalY, the orignal SubscriptionYear value of the point
 * mappedX, the mapped coordinate of originalX
 * mappedY, the mapped coordinate of originalY
 * countryName, the name of the Country the point is from
 * @author SHU
 */
public class ColoredPoint extends Point{
	private Color color;
	private double originalX;
	private double originalY;
	private double mappedX;
	private double mappedY;
	private String countryName;
	
	/**
	 * generates the colors of random 252 colors
	 */
	public static Color [] getColors () {
		Color [] colors = new Color [252];
		for (int i = 0; i < colors.length; i++) {
			Color color = new Color((int)(Math.random() * 0x1000000));
			colors[i] = color;
		}
		return colors;
	}
	
	/*
	 * default constuctor
	 */
	public ColoredPoint(Color newColor, double newOrignalX, double newOriginalY, int newMappedX, int newMappedY){
		super(newMappedX, newMappedY);
		this.color = newColor;
		this.originalX = newOrignalX;
		this.originalY = newOriginalY;
		this.mappedX = newMappedX;
		this.mappedY = newMappedY;
		getLabel();
	}
	
	/*
	 * constructor to make a ColoredPoint object with only two values
	 */
	public ColoredPoint(int newMappedX, int newMappedY){
		this.mappedX = newMappedX;
		this.mappedY = newMappedY;
	}
	
	/*
	 * gets mappedX
	 */
	public double getMappedX(){
		return mappedX;
	}
	
	/*
	 * gets mappedY
	 */
	public double getMappedY(){
		return mappedY;
	}
	
	/*
	 * gets color
	 */
	public Color getColor(){
		return this.color;
	}
	
	public String getCountry(){
		return this.countryName;
	}
	
	public void setCountry(String newCountryName){
		this.countryName = newCountryName;
	}
	
	/*
	 * returns a lable for the plotted point
	 */
	public String getLabel(){		
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append("(");
		stringBuilder.append(originalX);
		stringBuilder.append(" , ");
		stringBuilder.append(originalY);
		stringBuilder.append(")");
		String Label = stringBuilder.toString();
		return Label;
	}
	
	/*
	 * returns a string interpretation of the ColoredPoint object
	 */
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder("Point ");
		stringBuilder.append(getLabel());
		stringBuilder.append(" of ");
		stringBuilder.append(this.countryName);
		String name = stringBuilder.toString();
		return name;
	}
}
