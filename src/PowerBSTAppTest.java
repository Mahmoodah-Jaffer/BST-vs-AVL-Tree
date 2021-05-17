/**
PowerBSTAppTest to read in CSV file and store data items within a BinarySearchTree
@author Mahmoodah Jaffer - JFFMAH001
@since 12 March 2019
*/

/**
	PowerBSTAppTest is an app that returns the power and voltage of a specified date/time given by user according to the data in the file
	cleaned_data.csv. If the date/time cannot be found then the app will return "Date/time not found". If no specific date/time is given 
	then all date/time, power and voltage of all the date/time's will be returned. The data is stored in a BinaryTree as opposed to an 
	array so that it won't take as many comparison operations to find the specified value. 
*/
import java.io.*;

public class PowerBSTAppTest
{
	public static BinarySearchTree powerdata;
	public static String [] tester;

	/**
	The main method takes in the argument which would be the date time and uses the agument to 
	call either the printAllDateTimes, printDateTime or the readFile function
	*/
	public static void main (String [] args) throws IOException
	{

		if (args.length==0){
			printAllDateTimes();
		}
		else if (args[0].equals("subSet1.csv")){
			readFile(args[0]);
		}
		else{
			printDateTime(args[0]);
		}

	}

		/**
		Method fileData reads in CSV file and stores the voltage, power and voltage data required from CSV file into a
		BinarySearchTree to store each element
		@return powerdata BinaryTree of type BinarySearchTree
		@exception FileNotFoundException
		@exception IOException
		@see FileNotFoundException
		@see IOException
		*/
		public static BinarySearchTree fileData (String filename) throws FileNotFoundException, IOException
		{

			BinarySearchTree powerdata = new BinarySearchTree(); //500 element array with PowerStation type to store the required data from CSV file
			String [] tester = new String[53];


			FileReader read = new FileReader(filename); //reads in data from CSV file
			BufferedReader bread = new BufferedReader(read);// read is wraaped in the BufferedReader

			String firstline = bread.readLine(); //reads first line of text file - nothing will be done with this line because it holds no useful data
			String line = bread.readLine(); // second line of CSV file is read 
			int linepos = 0;

			while (line!=null)
			{
				String [] data = line.split(","); //the data from line is split by the commas and is being stored in an array 

				String datetime = data[0]; //sets variable datetime to the first element in data array - Date/Time
				tester[linepos] = data[0]; 
				System.out.println(tester[linepos]);

				String power = data[1];//sets variable power to the second element in data array - Global Active Power
				String voltage = data[3];//sets variable voltage to the fourth element in data array - Voltage

				powerdata.insert(new PowerStation(datetime,power,voltage)); //Powerstation object will store the datetime, power and voltage in element of linepos
				linepos++;

				line = bread.readLine(); //reads next line in CSV file
				
			}

			return powerdata;
		
		}


		/**
		Method searches for datetime given using the find function in the class BinarySeachTree and returns the corresponding voltage and power 
		@param dateTime String
		@exception IOException
		@see IOException
		*/
		public static void printDateTime( String dateTime) throws IOException{
			

			BinarySearchTree bst = fileData("cleaned_data.csv");

			if((bst.find(dateTime))==null){
				System.out.println("Date/time not found" + "\n" + "Comparison Operations for find method: " + bst.opCounter()+ "\n" + "Comparison Operations for insert method: " + bst.insCounter());
			}
			else{
				bst.opCounter();//reset opCount to zero
				System.out.println((bst.find(dateTime)) + "\n" + "Comparison Operations for find method: " + bst.opCounter());
				System.out.println("Comparison Operations for insert method: " + bst.insCounter());
			}

		}

		/**
		Method printAllDateTimes dispalys all elements of the BinarySearchTree using the display function in the BinarySearchTree class
		@exception IOException
		@see IOException
		*/
		public static void printAllDateTimes() throws IOException{

			BinarySearchTree bst = fileData("cleaned_data.csv");

			bst.display();
			System.out.println(bst.opCounter());
			System.out.println(bst.insCounter());
		}

		/**
		Method readFile reads in specific date times on each line and  returns the corresponding information required
		@param file String
		@exception FileNotFoundException
		@exception IOException
		@see FileNotFoundException
		@see IOException
		*/
		public static void readFile(String file) throws FileNotFoundException, IOException{

			BinarySearchTree bst = fileData(file);
			System.out.println(tester);

			for (int i = 0; i < tester.length; i++){
				bst.find(tester[i]);
				System.out.println(bst.insCounter());
				System.out.print(",");
			}

		}



}