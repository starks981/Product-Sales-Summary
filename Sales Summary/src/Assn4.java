/*A program written by Chris Starkey
 * 4/25/2018
 */


// Import the necessary packages for the class
import java.io.File;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Assn4 {
	// Main Method of Assn4 class
	public static void main(String[] args) {
		// Open a Try and Catch block for exception handling
		try {
			// Create a counter of Total Products will also be used for array indexing
			int totalProducts = 0;

			// Create arrays to hold the values read from the file
			String[] productNames = new String[100];
			int[] totalUnits = new int [100];
			double[] totalRevenue = new double [100];
			
			// Create arrays to hold the region information
			String[] region = {"North", "South", "East", "West"};
			double[] regionRevenue = new double [4];
			
			// Create a scanner for user input
			Scanner input = new Scanner(System.in);	
			
			// Print out the instructions to the console for the end user
			System.out.println("Enter the full path of the input file:");
			
			// Store the user input in a string
			File filePath = new File (input.nextLine());
			
			// Create a scanner to read the file
			Scanner file = new Scanner(filePath);
			
			// While the file has more data to read
			while (file.hasNextLine()) {
				// Store the next line in a string
				String line = file.nextLine();
				
				// Create a StringTokenizer to read through the string
				StringTokenizer st = new StringTokenizer(line);
				
				// While the tokenizer has more tokens
				while (st.hasMoreTokens()) {
					
					// Place the first item in the product array
					productNames[totalProducts] = st.nextToken();
					
					// Store the second item for use in revenue calculations
					double price = Double.parseDouble(st.nextToken());
					
					// Create an integer to track the number of units
					int units = 0;
					
					// Create a counter for array indexing in the while loop
					int counter = 0;
				
					// While the tokenizer has more tokens in the line
					while (st.hasMoreTokens()) {
						// Store the next token in a variable for use in revenue calculation and unit calculation
						String token = st.nextToken();
						
						// Place the region's revenue into the appropriate region array
						regionRevenue[counter] += Double.parseDouble(token) * price;
						
						// Adding the remaining integers in the line
						units = units + Integer.parseInt(token);
						
						// Increment the counter by 1
						counter++;
					}
					// Store the total units calculated above in the totalUnits array
					totalUnits[totalProducts] = units;
					
					// Calculate the total revenue
					totalRevenue[totalProducts] = units * price;
					
					// Increase the total number of products by 1
					totalProducts++;
				}
			}
			
			//Create a formatter to assist with currency format
			NumberFormat formatter =  NumberFormat.getCurrencyInstance();
			
			// Create a variable to house the region revenue calculations
			double totalRegionRevenue = 0;
			
			// Create a variable to house the total units
			int grandTotalUnits = 0;
			
			//Print out the revenue result calculations
			System.out.println("\n\nRevenue by Region:");
			for(int i=0; i<region.length; i++) {
				totalRegionRevenue += regionRevenue[i];
				System.out.println(region[i] + "\t" + formatter.format(regionRevenue[i]));
			}
			
			// Print out the total Revenue
			System.out.println("Total Revenue:\t" + formatter.format(totalRegionRevenue));
			
			// Calculate the average product revenue
			double averageRevenue = totalRegionRevenue/totalProducts;
			
			// Print out the Product Summary Header and results
			System.out.println("\n\nProduct Summary\nProduct Type\tUnits\tRevenue");
			for(int i=0; i<productNames.length; i++) {
				if(productNames[i] != null) {
					grandTotalUnits += totalUnits[i];
					System.out.println(productNames[i] + "\t" + totalUnits[i] + "\t" + formatter.format(totalRevenue[i]));
				}
			}
			// Print out the grand total and results
			System.out.println("GRAND TOTAL:\t" + grandTotalUnits + "\t" + formatter.format(totalRegionRevenue) + "\nAVERAGE REVENUE:\t" 
					+ formatter.format(averageRevenue));
			
			// Create a new string and check to see if there were any products that generated higher than the average revenue
			String topPerformers = "";
			
			for (int i=0; i<totalRevenue.length; i ++) {
				if(totalRevenue[i] != 0 && totalRevenue[i] > averageRevenue ) {
					topPerformers += "\n\t" + productNames[i];
				}
			}
			
			// Display the top performers if there were any
			if (!topPerformers.equals(null)) {
				System.out.println("\n\nThe following products generated higher than the average revenue:" + topPerformers);
			}
			
			// Close the Scanners
			input.close();
			file.close();
			}
		catch (Exception e) {
			// Print out any exceptions to the console
			System.out.println("Error reading the file: " + e.toString());
		}
	}
}
