package presentationL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businessL.Restaurant;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton txtW;
	private JButton txtA;
	private JButton txtC;
	private Restaurant restaurant;

	public GUI(Restaurant restaurant) {
		super("Restaurant Management");
		setLayout(null);
		this.setBounds(500, 100, 350, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		TheHandler handler = new TheHandler();
		this.restaurant = restaurant;
		txtW = new JButton("Waiter");
		txtW.setBounds(110, 20, 120, 20);
		getContentPane().add(txtW);
		txtW.addActionListener(handler);

		txtA = new JButton("Administrator");
		txtA.setBounds(110, 60, 120, 20);
		getContentPane().add(txtA);
		txtA.addActionListener(handler);

		txtC = new JButton("Chef");
		txtC.setBounds(110, 100, 120, 20);
		getContentPane().add(txtC);
		txtC.addActionListener(handler);

		this.setVisible(true);

	}

	/**
	 * Creates a table with modelC to visualize the elements from the objects
	 * 
	 * @param objects
	 * @param modelC
	 * @return A JTable ready to be displayed
	 */
	public static JTable createTable(List<Object> objects, DefaultTableModel modelC) {
		JTable t;
		modelC = new DefaultTableModel();
		if (!objects.isEmpty()) {
			for (Field field : objects.get(0).getClass().getDeclaredFields()) {
				if (!field.getName().equals("serialVersionUID"))
					modelC.addColumn(field.getName());
			}
			for (Object o : objects) {
				try {
					modelC.addRow((String[]) o.getClass().getMethod("toStringVector", null).invoke(o, null));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		t = new JTable(modelC);
		return t;

	}

	/**
	 * Updates t with the new modelC and the list of objects
	 * 
	 * @param t
	 * @param objects
	 * @param modelC
	 */
	public static void updateTable(JTable t, List<Object> objects, DefaultTableModel modelC) {
		modelC = new DefaultTableModel();
		if (!objects.isEmpty()) {
			for (Field field : objects.get(0).getClass().getDeclaredFields()) {
				if (!field.getName().equals("serialVersionUID"))
					modelC.addColumn(field.getName());
			}
			for (Object o : objects) {
				try {
					modelC.addRow((String[]) o.getClass().getMethod("toStringVector", null).invoke(o, null));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		t.setModel(modelC);

	}

	public class TheHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == txtW) {
				new WaiterGUI(restaurant);
			}
			if (event.getSource() == txtA) {
				new AdministratorGUI(restaurant);
			}
			if (event.getSource() == txtC) {
				new ChefGUI(restaurant);
			}

		}
	}

}
