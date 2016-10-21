/**
 *  Tests the GraphView class by creating an object of type GraphView and adding components to it.
 *  Creates one container of type JFrame and adds an object of type GraphView.
 *
 * @author Foothill College, Team 2 Shu Lo and Namjun Kim
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;


public class Main {
	private final int FRAME_WIDTH = 1080;
	private final int FRAME_HEIGHT = 640;

    /**
     * Asks the user choice between random plot and plot of one country
     */
	private LinkedList<Country> showUserChoice(Country [] allCountries)
	{		
		final String[] choices = {
				"Plot a graph for a number of random countries",
				"Plot a graph of one country of your choice"};

	    JFrame frame = new JFrame("User Choice 1");
	    String userChoice = (String) JOptionPane.showInputDialog(
	    		frame, 
	    		"What would you like to do?",
		        "A Plot Choice",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        choices, 
		        choices[0]);
	    if (userChoice != null) {
		    if (userChoice.equals("Plot a graph for a number of random countries")) {
		    	return buildRandomCountry(allCountries);
		    } else {
		    	return chooseOneCountry(allCountries);
		    }
	    } else {
	    	return new LinkedList<Country> ();
	    }
	}

    /**
     * returns linked list of a number of random countries based on the user's input
     */
	private LinkedList<Country> buildRandomCountry(Country [] allCountries) {
		JFrame frame = new JFrame("Cellular Graph");

		String userInput = (String)JOptionPane.showInputDialog(
			frame,
			"Enter the number of countries to graph:\n",
			"Random Plot",
			JOptionPane.PLAIN_MESSAGE,
			null,
			null,
			"5");
		
		try{
			int requestedSize = Integer.parseInt(userInput);
	
			Random random = new Random();
			LinkedList<Country> selectedCountries = new LinkedList<Country>();
			for (int i = 0; i < requestedSize; i++)
			{
				int selectedIndex = random.nextInt(allCountries.length);
				selectedCountries.add(allCountries[selectedIndex]);
			}
			return selectedCountries;
		} catch(NullPointerException ex) {
	    	System.out.println("NOTHING ENTERED");
	    } catch(NumberFormatException ex){
	    	System.out.println("YOU MUST ENTER A NUMBER OF COUNTRIES");
	    	//REPLACE WITH DIALOGUE BOX
	    }
		
		return new LinkedList<Country> ();
	}

		/**
		 * returns LinkedList of one country which matches user input
		 * by showing the input dialogue of JOptionPane
		 */
		private LinkedList<Country> chooseOneCountry(Country [] allCountries) {
			JFrame frame = new JFrame("Cellular Graph");

			String userInput = (String)JOptionPane.showInputDialog(
				frame,
				"Enter the name of the country you want to graph:\n",
				"Plot of One Country",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				"type a name of a country");

			if (userInput != null) {
				LinkedList<Country> allCountryLL = new LinkedList<Country>();
				for (int i = 0; i < allCountries.length; i++) {
					allCountryLL.add(allCountries[i]);
				}
	
				String countryNameToFind = userInput;
				Country tmpCountry = new Country(countryNameToFind);
				Country foundCountry = allCountryLL.contains(tmpCountry);
				LinkedList <Country> foundCountryLL = new LinkedList <Country> ();
				if (foundCountry != null) {
					foundCountryLL.add(foundCountry);
				} else {
					JOptionPane.showMessageDialog(null, "Country not found");
				}
				return foundCountryLL;
				
			} else {
				return new LinkedList<Country> ();
	    }

		}
	
    /**
     * Initializes the GUI with two JPanels and populates them.
     * One panel draws the data points, the second draws the legend.
     */
	private void initializeGui(LinkedList<Country> selectedCountries)
	{
		JFrame frame = new JFrame("Cellular Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		FlowLayout layout = new FlowLayout();
		frame.setLayout(layout);
	    
	    //to set the size of graph_panel to about 4/5 whole width
		int graph_panel_size = (FRAME_WIDTH-80)*4/5;
		GraphView myPlots = new GraphView(graph_panel_size, FRAME_HEIGHT-40, selectedCountries);	
		Color [] newColors = ColoredPoint.getColors();
		myPlots.setColors(newColors);
		frame.add(myPlots);
		
	    //to set the size of legend_panel to about 1/5 whole width
		int legend_panel_size = (FRAME_WIDTH-80)*1/5;
		LegendPanel myLegend = new LegendPanel(legend_panel_size, FRAME_HEIGHT-40, selectedCountries);	
		myLegend.setColors(newColors);
		myLegend.setBorder(BorderFactory.createTitledBorder("Legend"));
//		frame.add(myLegend);
				
        // JScrollPane of LegendPanel
        JScrollPane myScrollPane = new JScrollPane(myLegend);
        myScrollPane.setPreferredSize(new Dimension(legend_panel_size, FRAME_HEIGHT-40));
        frame.add(myScrollPane);
        
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
    /**
     * Uses a CSV to parse a CSV file.
     * Adds the data for each country to an array of Country objects.
     * Adds a random selection of countries to a generic LinkedList object.
     * Draws the list of countries to a JFrame.
     */
	public static void main(String[] args) 
	{		
		final String FILENAME = "resources\\cellular.csv";	// Directory path for Windows OS (i.e. Operating System)

		CSVReader parser = new CSVReader(FILENAME);

		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();		

		Country [] countries;

		Country current;
		countries = new Country[countryNames.length];

		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++) {
			int numberOfYears = yearLabels.length;   
			current = new Country(countryNames[countryIndex]);	
			for (int yearIndex = 0; yearIndex < numberOfYears; yearIndex++) {
				double [] allSubscriptions = parsedTable[countryIndex];
				double countryData = allSubscriptions[yearIndex];
				current.addSubscriptionYear(yearLabels[yearIndex], countryData);
			}
			countries[countryIndex] = current;
		}

		Main application = new Main();

		LinkedList<Country> listOfCountries = application.showUserChoice(countries);
		application.initializeGui(listOfCountries);
        System.err.flush();
	}
}