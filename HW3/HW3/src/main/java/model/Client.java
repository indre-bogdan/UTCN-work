package model;

/**
 * @author IndreBogdan
 *
 */
public class Client {
	private int idClient;
	private String name;
	private String address;
	private String phone;

	public Client(int idClient, String name, String address, String phone) {
		super();
		this.idClient = idClient;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public Client(int idClient) {
		super();
		this.idClient = idClient;
	}

	public Client(String name, String address, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	/**
	 * @return the idClient
	 */
	public int getIdClient() {
		return idClient;
	}

	/**
	 * @param idClient
	 *            the idClient to set
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
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

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String[] toStringVector() {
		String[] a = new String[4];
		a[0] = String.valueOf(idClient);
		a[1] = this.name;
		a[2] = this.address;
		a[3] = this.phone;
		return a;
	}
}
