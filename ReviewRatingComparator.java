import java.util.Comparator;

public class ReviewRatingComparator implements Comparator<Restaurant>
{
	public int compare(Restaurant res1, Restaurant res2)
	{
		if (res1.getStars() == res2.getStars())
		{
			// if they have same number of stars
			if (res1.getRestaurantName().equals(res2.getRestaurantName()))
			{
				// if they have same restaurant names
				if (res1.getLocation().equals(res2.getLocation()))
				{
					// if they have same location names
					return res1.getReview().compareTo(res2.getReview());
				}
				else
				{
					// if they have different location names
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
			// if they have different number of stars
			return (res1.getStars() - res2.getStars());
		}
	}
}