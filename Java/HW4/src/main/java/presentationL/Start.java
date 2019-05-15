package presentationL;

import java.util.TreeSet;

import businessL.MenuItem;
import businessL.Restaurant;
import dataL.RestaurantSerializator;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeSet<MenuItem> menu = RestaurantSerializator.read();
		Restaurant restaurant = new Restaurant(menu);
		new GUI(restaurant);

	}

}
