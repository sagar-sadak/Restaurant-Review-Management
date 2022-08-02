import java.util.Comparator;

public class ReviewCuisineComparator implements Comparator<Restaurant>
{
	public int compare(Restaurant res1, Restaurant res2)
	{
		if (res1.getCuisine().getName().equals(res2.getCuisine().getName()))
		{
			// if they have same cuisine names
			if (res1.getPriceRange() == res2.getPriceRange())
			{
				// if they have same price ranges
				if (res1.getRestaurantName().equals(res2.getRestaurantName()))
				{
					// if they have same restaurant names
					if (res1.getLocation().equals(res2.getLocation()))
					{
						// if they have same location
						return res1.getReview().compareTo(res2.getReview());
					}
					else
					{
						// if they have different locations
						return res1.getLocation().compareTo(res2.getLocation());
					}
				}
				else
				{
					// if they have different restaurant names
					return res1.getRestaurantName().compareTo(res2.getRestaurantName());
				}
			}
			else
			{
				// if they have different price ranges
				return res1.getPriceRange() - res2.getPriceRange();
			}
		}
		else
		{
			// if they have different cuisine names
			return res1.getCuisine().getName().compareTo(res2.getCuisine().getName());
		}
	}
}