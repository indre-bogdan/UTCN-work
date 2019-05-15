package presentationL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessL.BaseProduct;
import businessL.CompositeProduct;
import businessL.MenuItem;
import businessL.Restaurant;
import businessL.RestaurantProcessing;
import dataL.RestaurantSerializator;


public class AdministratorGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7671748859189108222L;
	private RestaurantProcessing restaurant;
	private JLabel txt;
	private JLabel txtE;
	private JLabel txtId;
	private JLabel txtComp;
	private JButton addB;
	private JButton deleteB;
	private JButton updateB;
	private DefaultTableModel model;
	public JTable table;
	private List<Object> objects;
	private JTextField[] txtF;
	private JLabel[] txtC;

	public AdministratorGUI(Restaurant restaurant) {
		super("Administrator");
		this.restaurant = restaurant;
		setLayout(null);
		this.setBounds(100, 100, 800, 500);
		txt = new JLabel(
				"                                 Id                                          Name                             Price");
		txt.setBounds(20, 5, 500, 15);
		add(txt);

		objects = restaurant.getMenu();
		table = GUI.createTable(objects, model);
		table.setBounds(20, 20, 500, 200);
		this.add(table);


		TheHandler2 handler2 = new TheHandler2();

		txtF = new JTextField[4];
		for (int i = 0; i < 4; i++) {
			txtF[i] = new JTextField(100);
			txtF[i].setBounds(20 + i * 130, 300, 130, 20);
			getContentPane().add(txtF[i]);
			txtF[i].addActionListener(handler2);
		}

		txtId = new JLabel("Id");
		txtId.setBounds(80, 270, 100, 30);
		add(txtId);

		txtComp = new JLabel("Composition:");
		txtComp.setBounds(430, 270, 100, 30);
		add(txtComp);

		txtC = new JLabel[8];
		for (int i = 0; i < 8; i++) {
			txtC[i] = new JLabel("");
			txtC[i].setBounds(560, 250 + i * 20, 100, 30);
			add(txtC[i]);
		}

		txtE = new JLabel("");
		txtE.setBounds(20, 330, 400, 30);
		add(txtE);

		addB = new JButton("Add");
		addB.setBounds(600, 20, 100, 20);
		getContentPane().add(addB);
		addB.addActionListener(handler2);

		deleteB = new JButton("Delete");
		deleteB.setBounds(600, 60, 100, 20);
		getContentPane().add(deleteB);
		deleteB.addActionListener(handler2);

		updateB = new JButton("Update");
		updateB.setBounds(600, 100, 100, 20);
		getContentPane().add(updateB);
		updateB.addActionListener(handler2);

		setVisible(true);
	}

	public class TheHandler2 implements ActionListener {
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
				if (txtF[3].getText().equals(""))
					try {
						restaurant.addBaseProduct(Integer.parseInt(txtF[0].getText()), txtF[1].getText(),
								Float.parseFloat(txtF[2].getText()));


					} catch (AssertionError | Exception e) {
						txtE.setText(e.getMessage());
					}
				else {
					try {
						restaurant.addCompositeProduct(Integer.parseInt(txtF[0].getText()), txtF[1].getText(),
								composition(txtF[3].getText()));

						if (!txtF[2].getText().equals(""))
							throw new IllegalArgumentException("The price of composite products is the sum of its products!");
					} catch (AssertionError | Exception e) {
						txtE.setText(e.getMessage());
					}
				}
				GUI.updateTable(table, restaurant.getMenu(), model);
				RestaurantSerializator.write(restaurant.getMenu2());
			}
			if (event.getSource() == deleteB) {
				txtE.setText("");
				try {
					int id = Integer.parseInt(txtF[0].getText());
					restaurant.deleteMenuItem(id);
					GUI.updateTable(table, restaurant.getMenu(), model);
					RestaurantSerializator.write(restaurant.getMenu2());
				} catch (AssertionError | Exception e) {
					txtE.setText(e.getMessage());
				}
			}
			if (event.getSource() == updateB) {
				txtE.setText("");
				for (int i = 0; i < 8; i++) {
					txtC[i].setText("");
				}
				try {
					int id = Integer.parseInt(txtF[0].getText());
					MenuItem a = restaurant.selectMenuItem(id);
					assert a != null : "Item does not exist";
					if (a instanceof CompositeProduct) {
						restaurant.editCompositeProduct(a.getId(), txtF[1].getText(), composition(txtF[3].getText()));
						List<MenuItem> items = ((CompositeProduct) a).getItems();
						StringBuilder s = new StringBuilder();
						int i = 0;
						for (MenuItem m : items) {
							s.append(m.getId() + " ");
							txtC[i].setText(m.getName());
							i++;
						}
						txtF[2].setText(String.valueOf(a.computePrice()));
						txtF[3].setText(s.toString());
					}
					if (a instanceof BaseProduct) {
						restaurant.editBaseProduct(a.getId(), txtF[1].getText(), Float.parseFloat(txtF[2].getText()));
					}

				} catch (AssertionError | Exception e) {
					txtE.setText(e.getMessage());
				}
				GUI.updateTable(table, restaurant.getMenu(), model);
				RestaurantSerializator.write(restaurant.getMenu2());
			}
			if (event.getSource() == txtF[0]) {
				txtE.setText("");
				for (int i = 0; i < 8; i++) {
					txtC[i].setText("");
				}
				try {
					int id = Integer.parseInt(txtF[0].getText());
					MenuItem a = restaurant.selectMenuItem(id);
					txtF[0].setText(String.valueOf(id));
					txtF[1].setText(a.getName());
					txtF[2].setText(String.valueOf(a.computePrice()));

					if (a instanceof CompositeProduct) {
						List<MenuItem> items = ((CompositeProduct) a).getItems();
						StringBuilder s = new StringBuilder();
						int i = 0;
						for (MenuItem m : items) {
							s.append(m.getId() + " ");
							txtC[i].setText(m.getName());
							i++;
						}

						txtF[3].setText(s.toString());
					}
				} catch (AssertionError | Exception e) {
					txtE.setText(e.getMessage());
				}

			}

		}
	}

}
