import java.io.Serializable;
import java.util.ArrayList;

public class ReviewManager implements Serializable {
    
    private static final long serialVersionUID = 205L;

    ArrayList<Restaurant> reviewList;

    // constructor
     public ReviewManager() {
        reviewList = new ArrayList<>();
    }

    /**
     * Add a Restaurant object to the reviewList and return true if such an object
     * is added successfully. Otherwise, return false. Two restaurants are
     * considered duplicated if they have exactly the same restaurant name and
     * cuisine name.
     */
    public boolean addReview(String restaurantName, int stars, String review, String priceRange, String cuisineName, String location, String signatureDish) {
        if (restaurantExists(restaurantName, location) == -1) {
            int price = priceRange.length();
            Cuisine newCuisine = new Cuisine(signatureDish, cuisineName);
            Restaurant newRestaurant = new Restaurant(restaurantName, stars, review, price, location, newCuisine);
            reviewList.add(newRestaurant);
            return true;
        }
        return false;
    }
    
    // check if a restaurant exists in the arraylist based on name and location
    public int restaurantExists(String name, String location)
    {
    	for (int i = 0; i < reviewList.size(); i++)
    	{
    		if (reviewList.get(i).getRestaurantName().equals(name) && 
    				reviewList.get(i).getLocation().equals(location))
    		{
    			return i;
    		}
    	}
    	return -1;
    }
    
    // check if a cuisine exists in the arraylist based on name
    public ArrayList<Integer> cuisineExists(String name)
    {
    	ArrayList<Integer> indexes = new ArrayList<Integer>();
    	for (int i = 0; i < reviewList.size(); i++)
    	{
    		if (reviewList.get(i).getCuisine().getName().equals(name))
    		{
    			indexes.add(i);
    		}
    	}
    	return indexes;
    }
    
    // getter for restaurant
    public Restaurant getRestaurant(int index)
    {
    	return reviewList.get(index);
    }
    
    // method to remove an object from the arraylist
    public boolean removeReview(String name, String location)
    {
    	for (Restaurant res : reviewList)
    	{
    		if (res.getRestaurantName().equals(name) && res.getLocation().equals(location))
    		{
    			reviewList.remove(res);
    			return true;
    		}
    	}
    	return false;
    }
    
    // sort the arraylist by rating
    public void sortByRating()
    {
    	Sorts.sort(reviewList, new ReviewRatingComparator());
    }
    
    // sort the arraylist by cuisine
    public void sortByCuisine()
    {
    	Sorts.sort(reviewList, new ReviewCuisineComparator());
    }
    
    // list all the restaurants
    public String listReviews()
    {
    	String result = "";
    	if (!reviewList.isEmpty())
    	{
    		for (Restaurant res : reviewList)
	    	{
	    		result += res;
	    	}
    	}
    	else
    	{
    		result = "\nNo Reviews available\n\n";
    	}
    	return result;
    }
    
    // clear the arraylist at the end
    public void closeReviewManager()
    {
    	reviewList.clear();
    }

}
