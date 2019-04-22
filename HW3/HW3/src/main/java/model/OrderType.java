package model;

public class OrderType {
	private int idOrderType;
	private String name;

	public OrderType(int idOrderType, String name) {
		super();
		this.idOrderType = idOrderType;
		this.name = name;
	}

	public OrderType(int idOrderType) {
		super();
		this.idOrderType = idOrderType;
	}

	/**
	 * @return the idOrderType
	 */
	public int getIdOrderType() {
		return idOrderType;
	}

	/**
	 * @param idOrderType
	 *            the idOrderType to set
	 */
	public void setIdOrderType(int idOrderType) {
		this.idOrderType = idOrderType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String[] toStringVector() {
		String[] a = new String[2];
		a[0] = String.valueOf(idOrderType);
		a[1] = this.name;
		return a;
	}

}
