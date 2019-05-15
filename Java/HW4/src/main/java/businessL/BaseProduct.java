package businessL;

public class BaseProduct extends MenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6460648406433965831L;
	private int id;
	private String name;
	private float price;

	public BaseProduct(int id, String name, float price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float computePrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void addItem(MenuItem a) {
		// does nothing
	}

	public void deleteItem(String item) {
		// does nothing
	}

	public String[] toStringVector() {
		String[] a = new String[3];
		a[0] = String.valueOf(id);
		a[1] = this.name;
		a[2] = Float.toString(price);
		return a;
	}

}
