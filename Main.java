import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        // Menu options
        char inputOpt = ' ';
        String inputLine;
        // Restaurant and Cuisine information
        String restaurantName, cuisineName;
        String review = null, location, signatureDish, priceRange;

        int rating;
        // Output information
        String outFilename, inFilename;
        String outMsg, inMsg;
        // Restaurant manager
        ReviewManager reviewManager = new ReviewManager();
        // Operation result   
        
        try {
            printMenu();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader stdin = new BufferedReader(isr);

            do {
                System.out.print("\nWhat action would you like to perform?\n");
                inputLine = stdin.readLine().trim();
                if (inputLine.isEmpty()) {
                    continue;
                }
                inputOpt = inputLine.charAt(0);
                inputOpt = Character.toUpperCase(inputOpt);

                switch (inputOpt) {

                    case 'A': // Add a new Restaurant Review
                        System.out.print("Please enter the restaurant information:\n");
                        System.out.print("Enter the restaurant name:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Enter the review:\n");
                        review = stdin.readLine().trim();
                        System.out.print("Enter the price range:\n");
                        priceRange = stdin.readLine().trim();
                        System.out.print("Enter the rating:\n");
                        rating = Integer.parseInt(stdin.readLine().trim());
                        System.out.print("Enter the cuisine name:\n");
                        cuisineName = stdin.readLine().trim();
                        System.out.print("Enter the location:\n");
                        location = stdin.readLine().trim();
                        System.out.print("Enter the signature dish\n");
                        signatureDish = stdin.readLine().trim();
                        
                        // check if restaurant has been added successfully, if not show a different message
                        if (reviewManager.addReview(restaurantName, rating, review, priceRange, cuisineName, location, signatureDish))
                        	System.out.println("Restaurant added");
                        else
                        	System.out.println("Restaurant NOT added");
                        break;
                        
                    case 'D': // Search a Restaurant
                        System.out.print("Please enter the restaurant name to search:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Please enter the restaurant's location':\n");
                        location = stdin.readLine().trim();

                        // iterate through the arraylist and get the reviews of a specific restaurant
                        String toPrint = "";
                        for (Restaurant res : reviewManager.reviewList)
                        {
                        	if (res.getRestaurantName().equals(restaurantName) && res.getLocation().equals(location))
                        	{
                        		toPrint += "Restaurant found. Here's the review:\n" + res.getReview() + "\n";
                        		break;
                        	}
                        }
                        
                        // if restaurant not found, print this message
                        if (toPrint.isEmpty())
                        	System.out.println("Restaurant not found. Please try again");
                        else
                        	System.out.print(toPrint);
                        break;

                    case 'E': // Search a cuisine
                        System.out.print("Please enter the cuisine name to search:\n");
                        cuisineName = stdin.readLine().trim();
                        
                        int num = 0;
                        toPrint = "";
                        // iterate through the arraylist based on cuisine name, and get the info for that
                        for (Restaurant res : reviewManager.reviewList)
                        {
                        	if (res.getCuisine().getName().equals(cuisineName))
                        	{
                        		num ++;
                        		toPrint += res.toString();
                        	}
                        }
                        
                        // if no restaurant found, print this message
                        if (toPrint.isEmpty())
                        	System.out.printf("Cuisine: %s was NOT found\n", cuisineName);
                        else
                        {
                        	System.out.printf("%d Restaurants matching %s cuisine were found:\n", num, cuisineName);
                        	System.out.println(toPrint);
                        }
                        break;
   
                    case 'L': // List restaurant's reviews
                        System.out.print("\n" + reviewManager.listReviews() + "\n");
                        break;
                        
                    case 'N':
                    	// sort the arraylist by rating
                    	reviewManager.sortByRating();
                    	System.out.println("sorted by rating");
                    	break;
                    
                    case 'P':
                    	// sort the arraylist by cuisine
                    	reviewManager.sortByCuisine();
                    	System.out.print("sorted by cuisine\n");
                    	break;
                 
                    case 'Q': // Quit
                        break;

                    case 'R': // Remove a review
                        System.out.print("Please enter the restaurant name of the review to remove:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Please enter the location to remove:\n");
                        location = stdin.readLine().trim();

                        boolean found = false;
                        // iterate through the arraylist, and remove the review of a specific one
                        for (Restaurant res : reviewManager.reviewList)
                        {
                        	if (res.getRestaurantName().equals(restaurantName) && res.getLocation().equals(location))
                        	{
                        		found = true;
                        		reviewManager.removeReview(res.getRestaurantName(), res.getLocation());
                        		break;
                        	}
                        }
                        
                        // if restaurant not found, print this message
                        if (found)
                        	System.out.println(restaurantName + ", " + location + " was removed");
                        else
                        	System.out.println(restaurantName + ", " + location + " was NOT removed");
                        break;
                        
                    case 'T': // Close reviewList
                        reviewManager.closeReviewManager();
                        System.out.print("Restaurant management system was reset\n");
                        break;

                    case 'U': // Write restaurant names and reviews to a text file
                        System.out.print("Please enter a file name that we will write to:\n");
                        outFilename = stdin.readLine().trim();
                        System.out.print("Please enter the name of the restaurant:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.println("Please enter a review to save locally:\n");
                        review = stdin.readLine().trim();
                        outMsg = restaurantName + "\n" + review + "\n";
                        
                        try
                        {
                        	// try to write to a file using BufferedWriter
                        	BufferedWriter write = new BufferedWriter( new FileWriter(outFilename));
                        	write.write(outMsg);
                        	System.out.println(outFilename + " is written");
                        	write.close();
                        }
                        catch (IOException e)
                        {
                        	System.out.println("IO Exception");
                        }
                        break;

                    case 'V': // Read strings from a text file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();
                        
                        String content = "";
                        try
                        {
                        	// try to read from a file using BufferedReader
                        	inMsg = "";
                        	BufferedReader read = new BufferedReader(new FileReader(inFilename));
                        	content = read.readLine();
                        	while (content != null)
                        	{
                        		inMsg += content + "\n";
                        		content = read.readLine();
                        	}
                        	System.out.println(inFilename + " was read");
                        	System.out.println("The contents of the file are:\n" + inMsg + "\n");
                        	read.close();
                        }
                        // catch some exceptions
                        catch (FileNotFoundException e)
                        {
                        	System.out.println(inFilename + " was not found");
                        }
                        catch (IOException e)
                        {
                        	System.out.println("IO Exception");
                        }
                        break;
 
                    case 'W': // Serialize ReviewManager to a data file
                        System.out.print("Please enter a file name to write:\n");
                        outFilename = stdin.readLine().trim();
                        
                        try
                        {
                        	// write an object to a file using ObjectOutputStream
                        	FileOutputStream file = new FileOutputStream(outFilename);
                        	ObjectOutputStream output = new ObjectOutputStream(file);
                        	output.writeObject(reviewManager);
                        	output.close();
                        	file.close();
                        }
                        // catch some exceptions
                        catch (NotSerializableException e)
                        {
                        	System.out.println("Not serializable exception");
                        }
                        catch (IOException e)
                        {
                        	System.out.println("IO Exception");
                        }
                        break;

                    case 'X': // Deserialize ReviewManager from a data file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();
                        
                        try
                        {
                        	// read an object from a file using ObjectInputStream
                        	FileInputStream file = new FileInputStream(inFilename);
                        	ObjectInputStream input = new ObjectInputStream(file);
                        	reviewManager = (ReviewManager) input.readObject();
                        	input.close();
                        	file.close();
                        	System.out.println(inFilename + " was read");
                        }
                        // catch some exceptions
                        catch (ClassNotFoundException e)
                        {
                        	System.out.println("Class not found exception");
                        }
                        catch (NotSerializableException e)
                        {
                        	System.out.println("Not serializable exception");
                        }
                        catch (IOException e)
                        {
                        	System.out.println("IO Exception");
                        }
                        break;

                    case '?': // Display help
                        printMenu();
                        break;

                    default:
                        System.out.print("Unknown action\n");
                        break;
                }

            } while (inputOpt != 'Q' || inputLine.length() != 1); // iterate until user enters 'Q'
        }
        catch (IOException exception) {
            System.out.print("IO Exception\n");
        }
    }

    // method for printing the menu and user options
    public static void printMenu() {
        System.out.println("Welcome to Kelp! ");
        System.out.println("Find or post reviews for your favorite (and not so favorite) restaurants.");

        System.out.print("Choice\t\tAction\n" + "------\t\t------\n" + "A\t\tAdd a review\n"
                + "D\t\tSearch for a restaurant\n" + "E\t\tSearch for a cuisine\n"
                + "L\t\tList all reviews\n" + "N\t\tSort by stars\n" + "P\t\tSort by cuisine\n"
                + "Q\t\tQuit\n" + "R\t\tRemove a review\n"
                + "U\t\tAdd personal review to a local file\n" + "V\t\tRetrieve personal review from a local file\n"
                + "W\t\tSave reviews to a file\n"
                + "X\t\tUpload reviews from a file\n"
                + "T\t\t(admin) reset database\n"
                + "?\t\tDisplay Help\n");
    }
}
