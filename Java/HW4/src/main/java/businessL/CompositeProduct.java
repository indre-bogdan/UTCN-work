package businessL;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3054262833363977589L;
	int id;
	private String name;
	private List<MenuItem> items;

	public CompositeProduct(int id, String name, List<MenuItem> items) {
		this.id = id;
		this.name = name;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CompositeProduct(String name) {
		this.name = name;
		this.items = new ArrayList<MenuItem>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}

	public float computePrice() {
		float total = 0;
		for (MenuItem item : items) {
			total += item.computePrice();
		}
		return total;
	}

	public void addItem(MenuItem a) {
		items.add(a);

	}

	public void deleteItem(String item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equals(item)) {
				items.remove(i);
				i--;
			}
		}

	}

	public String[] toStringVector() {
		String[] a = new String[3];
		a[0] = String.valueOf(id);
		a[1] = this.name;
		a[2] = Float.toString(computePrice());
		return a;
	}

}
