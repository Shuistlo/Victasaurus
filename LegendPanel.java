import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;
import javax.swing.JPanel;

public class LegendPanel extends JPanel {
	private int width;
	private int height;  //the width and height of the JPanel.
	private LinkedList <Country> countries;
	private Font font;
	private Color [] colors;
	
	/**
	 * set the color palette of 252 random color to plot the legend
	 * @param newColors
	 */
	public void setColors(Color [] newColors) {
		this.colors = newColors;
	}
	
	/**
	 * constructor which takes in width and height and data LinkedList of Country
	 */
	public LegendPanel (int newWidth, int newHeight, LinkedList <Country> countries) {
		this.width = newWidth-20;
		this.height = 30;
	    this.setPreferredSize(new Dimension(width, height));
	    this.countries = countries;
		this.font = new Font("San Serif", Font.PLAIN, 12);
	}
	
	/**
	 * draws legend for LinkedList of Country using the eight named colors from Color class
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Iterator<Country> itr = countries.iterator();
		int counter = 1;
		this.height = 30;
		while (itr.hasNext()) {
			Country currentCountry = itr.next();
			g.setColor(colors[(counter-1)%8]);
			g.setColor(colors[(counter-1)]);
	        g.fillRect(15, counter*30, 20, 10);
			g.setColor(Color.BLACK);
			g.setFont(font);
			g.drawString(currentCountry.getName(), 50, counter*30+10);
			counter += 1;
			this.height += 30;
		    this.setPreferredSize(new Dimension(width, height));
		}
	}
}
