import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * CSVReader reads a CSV file and creates
 * an array of String countryNames based on the countries provided by the CSV file
 * an Array of Ints yearLabels based on the years in the CSV File
 * a 2D aray of Doubles cellularDataTable that has the subscriptions
 * CSVReader will use parsing read and create the arrays
 * @author aredp
 */
public class CSVReader {
	private int numberOfCountries;
	private String countryNames[];
	private int yearLabels[];
	private int numberOfYears;
	private double cellularDataTable[][];
	private Scanner myScanner;
	
	
	/*
	 * Default Constructor for CSVREader class
	 * Parameter newFile is the file CSVReader will create yearLabels, countryNames,
	 * cellularDataTable from. Constructor will use scanner listed as a member
	 * variable to read the file by line. Constructor will convert most of the lines
	 * of the CSV file into arrays of Strings split by "," to be read into their
	 * respective arrays.
	 * Constructor will catch exception if file is not found
	 * Constructor will catch exception if file is improperly formated
	 */
	public CSVReader(String newFile) { 
		File inFile = new File(newFile);
		try {
			myScanner = new Scanner(inFile);
			
			//determine yearLabels size
			numberOfYears = 2012-1960+1;
			yearLabels = new int[numberOfYears];
			myScanner.nextLine(); //SKIP TITLE
			
			//set size of countryNames
			String lineWithCC = myScanner.nextLine();
			String[] arrayWithCC = lineWithCC.split(",");
			numberOfCountries = Integer.parseInt(arrayWithCC[1]);
			countryNames = new String[numberOfCountries];
			
			String lineWithYears = myScanner.nextLine();
			String[] arrayWithYears = lineWithYears.split(",");
			
			int tempYearList[] = convertStringtoInt(arrayWithYears);
			System.arraycopy(tempYearList, 0, yearLabels, 0, tempYearList.length);
			
			//set size of cellularDataTable
			cellularDataTable = new double[numberOfCountries][numberOfYears];
			
			int currentRowIndex = 0;
			while(myScanner.hasNextLine()==true){ //fix this
				//makes the country list
				String currentLine = myScanner.nextLine();
				String[] currentLineArray = currentLine.split(",");
				countryNames[currentRowIndex] = currentLineArray[0];
				
				//fills the cellular table
				double tempArray[];
				tempArray = new double[currentLineArray.length - 1];
				tempArray = convertStringtoDouble(currentLineArray, currentRowIndex);
				System.arraycopy(tempArray, 0, cellularDataTable[currentRowIndex], 0, tempArray.length);
				
				currentRowIndex++;
			}
		}
		catch (FileNotFoundException ex){
			System.out.println("ERROR : File cannot be found!");
		}
		catch (InputMismatchException ex) {
			System.out.println("ERROR : you are attempting to add a line without valid data!");
		}
	}
	
	/*
	 * convertStringtoDouble will convert an array of strings in format
	 * {string, potential double, potential double.....}
	 * into an array with only the potential doubles
	 * parameter currentLineArray is an array of Strings with the above format
	 * parameter lineIndex is the index of the line to be converted into
	 * an array of doubles
	 * convertStringtoDouble returns an array of Doubles
	 */
	public double[] convertStringtoDouble(String[] currentLineArray, int lineIndex) {
		int doubleArraySize = currentLineArray.length - 1;
		double currentArrayDouble[];
		currentArrayDouble = new double[doubleArraySize];
		int arrayStart = 0;
		for(int i = 1; i < currentLineArray.length; i++) {
			currentArrayDouble[arrayStart] = Double.parseDouble(currentLineArray[i]);
			arrayStart++;
		}
		return currentArrayDouble;
	}
	
	/*
	 * convertStringtoInt will convert an array of strings in format
	 * {string, potential int, potential int.....}
	 * into an array with only the potential int
	 * parameter currentLineArray is an array of Strings with the above format
	 * convertStringtoInt returns an array of Ints
	 */
	public int[] convertStringtoInt(String[] currentLineArray) {
		int intArraySize = currentLineArray.length -1;
		int currentArrayInt[];
		currentArrayInt = new int[intArraySize];
		int arrayStart = 0;
		for(int i = 1; i < currentLineArray.length; i++) {
			currentArrayInt[arrayStart] = Integer.parseInt(currentLineArray[i]);
			arrayStart++;
		}
		return currentArrayInt;
	}
	
	/*
	 * gets yearLabels
	 */
	public int[] getYearLabels() {
		return yearLabels;
	}
	
	/*
	 * gets countryNames
	 */
	public String[] getCountryNames() {
		return countryNames;
	}
	
	/*
	 * gets numberOfCountries
	 */
	public int getNumberOfCountries() {
		return numberOfCountries;
	}
	
	/*
	 * gets numberOfYears
	 */
	public int getNumberOfYears() {
		return numberOfYears;
	}
	
	/*
	 * gets cellularDataTable
	 */
	public double[][] getParsedTable() {
		return cellularDataTable;
	}
}
