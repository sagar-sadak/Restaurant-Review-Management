import java.io.Serializable;

public class Restaurant implements Serializable
{
	// declare private variables
	private static final long serialVersionUID = 205L;
	
	private String restaurantName;
	private int stars;
	private String review;
	private int priceRange;
	private String location;
	private Cuisine cuisine;
	
	// constructor
	public Restaurant(String name, int stars, String review, int range, String location, Cuisine cuisine)
	{
		this.restaurantName = name;
		this.stars = stars;
		this.review = review;
		this.priceRange = range;
		this.location = location;
		this.cuisine = cuisine;
	}
	
	// accessor/getter
	public String getRestaurantName()
	{
		return this.restaurantName;
	}
	// accessor/getter
	public int getStars()
	{
		return this.stars;
	}
	// accessor/getter
	public int getPriceRange()
	{
		return this.priceRange;
	}
	// accessor/getter
	public String getLocation()
	{
		return this.location;
	}
	// accessor/getter
	public String getReview()
	{
		return this.review;
	}
	// accessor/getter
	public Cuisine getCuisine()
	{
		return this.cuisine;
	}
	
	// toString method
	public String toString()
	{
		String star = "*".repeat(stars);
		String dollar = "$".repeat(priceRange);
		String result = this.restaurantName + " restaurant\n" + 
						star + "\t\t" + dollar + "\n" + 
						this.cuisine.toString() + "Location: " + this.location + "\n" + 
						"Review:	" + this.review + "\n\n";
		return result;
	}
}