package presentationL;



import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import businessL.Order;
import businessL.Restaurant;

public class ChefGUI extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel txt;

	public ChefGUI(Restaurant restaurant) {
		super("Chef");
		restaurant.setChef(this);

		setLayout(null);
		this.setBounds(100, 100, 300, 300);

		txt = new JLabel("Waiting for orders");
		txt.setBounds(20, 20, 200, 15);
		add(txt);

		setVisible(true);
	}

	public void update(Observable o, Object arg) {
		txt.setText("We have a new order with id = " + ((Order) arg).getOrderId());
	}

}
