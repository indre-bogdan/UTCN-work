package presentationL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessL.MenuItem;
import businessL.Order;
import businessL.Restaurant;
import businessL.RestaurantProcessing;

public class WaiterGUI extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2901186842427302596L;
	private RestaurantProcessing restaurant;
	private JLabel txt;
	private JLabel txtE;
	private JLabel txtT;
	private JLabel txtId;
	private JLabel txtComp;
	private JButton addB;
	private JButton ComputeBillB;
	private DefaultTableModel model2;
	public JTable table2;
	private DefaultTableModel model;
	private JTable table;
	private JTextField[] txtF;
	private JLabel[] txtC;

	public WaiterGUI(Restaurant restaurant) {
		super("Waiter");
		restaurant.setWaiter(this);
		this.restaurant = restaurant;

		setLayout(null);
		this.setBounds(100, 100, 800, 500);
		txt = new JLabel(
				"                      Id                           Table                             Date                                                                              Menu");
		txt.setBounds(20, 5, 700, 15);
		add(txt);


		table2 = GUI.createTable(restaurant.getOrders(), model2);
		table2.setBounds(20, 20, 350, 200);
		this.add(table2);

		table = GUI.createTable(restaurant.getMenu(), model);
		table.setBounds(390, 20, 350, 200);
		this.add(table);

		TheHandler3 handler3 = new TheHandler3();

		txtF = new JTextField[4];
		for (int i = 0; i < 4; i++) {
			txtF[i] = new JTextField(100);
			txtF[i].setBounds(20 + i * 130, 300, 130, 20);
			getContentPane().add(txtF[i]);
			txtF[i].addActionListener(handler3);
		}

		txtId = new JLabel("Id                                     Table                                 Date");
		txtId.setBounds(80, 270, 500, 30);
		add(txtId);

		txtComp = new JLabel("Composition:");
		txtComp.setBounds(430, 270, 100, 30);
		add(txtComp);

		txtT = new JLabel("");
		txtT.setBounds(560, 410, 150, 30);
		add(txtT);

		txtC = new JLabel[8];
		for (int i = 0; i < 8; i++) {
			txtC[i] = new JLabel("");
			txtC[i].setBounds(560, 250 + i * 20, 200, 30);
			add(txtC[i]);
		}

		txtE = new JLabel("");
		txtE.setBounds(20, 330, 400, 30);
		add(txtE);

		addB = new JButton("Add Order");
		addB.setBounds(20, 240, 150, 20);
		getContentPane().add(addB);
		addB.addActionListener(handler3);

		ComputeBillB = new JButton("Compute Bill");
		ComputeBillB.setBounds(200, 240, 150, 20);
		getContentPane().add(ComputeBillB);
		ComputeBillB.addActionListener(handler3);


		setVisible(true);
	}

	public class TheHandler3 implements ActionListener {
		private int[] composition(String integers) throws NumberFormatException {
			String[] integers2 = integers.split(" ");
			int[] products = new int[integers2.length];
			for (int i = 0; i < integers2.length; i++) {
				products[i] = Integer.parseInt(integers2[i]);
			}
			return products;
		}

		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == addB) {
				txtE.setText("");
				for (int i = 0; i < 8; i++) {
					txtC[i].setText("");
				}
				txtT.setText("");
				try {
					restaurant.createNewOrder(Integer.parseInt(txtF[0].getText()), Integer.parseInt(txtF[1].getText()),
							composition(txtF[3].getText()));

					int id = Integer.parseInt(txtF[0].getText());
					Order a = restaurant.selectOrder(id);
					txtF[2].setText(String.valueOf(a.getDate()));

					List<MenuItem> items = restaurant.getOrderItems(a);
					StringBuilder s = new StringBuilder();
					int i = 0;
					for (MenuItem m : items) {
						s.append(m.getId() + " ");
						txtC[i].setText(m.getName() + "..................." + m.computePrice());
						i++;
					}

					txtF[3].setText(s.toString());
					txtT.setText("Total..................." + restaurant.computeTotalPrice(id));



				} catch (AssertionError | Exception e) {
					txtE.setText(e.getMessage());
				}
				GUI.updateTable(table2, restaurant.getOrders(), model2);

			}
			if (event.getSource() == ComputeBillB) {
				txtE.setText("");
				try {
					int id = Integer.parseInt(txtF[0].getText());
					restaurant.generateBill(id);
					GUI.updateTable(table2, restaurant.getOrders(), model2);
				} catch (AssertionError | Exception e) {
					txtE.setText(e.getMessage());
				}
			}

			if (event.getSource() == txtF[0]) {
				txtE.setText("");
				for (int i = 0; i < 8; i++) {
					txtC[i].setText("");
				}
				txtT.setText("");
				try {
					int id = Integer.parseInt(txtF[0].getText());
					Order a = restaurant.selectOrder(id);
					assert a != null : "Order does not exist";
					txtF[0].setText(String.valueOf(id));
					txtF[1].setText(String.valueOf(a.getTable()));
					txtF[2].setText(String.valueOf(a.getDate()));

					List<MenuItem> items = restaurant.getOrderItems(a);
					StringBuilder s = new StringBuilder();
					int i = 0;
					for (MenuItem m : items) {
						s.append(m.getId() + " ");
						txtC[i].setText(m.getName() + "..................." + m.computePrice());
						i++;
					}
				
					txtT.setText("Total..................." + restaurant.computeTotalPrice(id));
					txtF[3].setText(s.toString());
				} catch (AssertionError | Exception e) {
					txtE.setText(e.getMessage());
				}

			}

		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		GUI.updateTable(table, restaurant.getMenu(), model);
	}

}
