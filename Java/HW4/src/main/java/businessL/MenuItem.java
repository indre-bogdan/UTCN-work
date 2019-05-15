package businessL;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 561773816007364595L;

	public String getName() {
		String name = null;
		return name;
	}

	public int getId() {
		int id = 0;
		return id;
	}

	public float computePrice() {
		float price = 0;
		return price;
	}

	public void addItem(MenuItem a) {

	}

	public void deleteItem(String item) {

	}

	public String[] toStringVector() {
		String[] a = new String[3];
		return a;
	}

}
