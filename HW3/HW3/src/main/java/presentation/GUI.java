package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Client;
import model.OrderType;
import model.Orders;
import model.Product;
import model.Stock;

/**
 * The graphic user interface of the application
 * 
 * @author IndreBogdan
 *
 */
public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton txtC;
	private JButton txtP;
	private JButton txtS;
	private JButton txtO;
	private JButton txtOT;


	public GUI() {
		super("Warehouse Management");
		setLayout(null);
		this.setBounds(500, 100, 350, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		TheHandler handler = new TheHandler();

		txtC = new JButton("Clients");
		txtC.setBounds(110, 20, 120, 20);
		getContentPane().add(txtC);
		txtC.addActionListener(handler);

		txtP = new JButton("Products");
		txtP.setBounds(110, 60, 120, 20);
		getContentPane().add(txtP);
		txtP.addActionListener(handler);

		txtO = new JButton("Orders");
		txtO.setBounds(110, 100, 120, 20);
		getContentPane().add(txtO);
		txtO.addActionListener(handler);

		txtS = new JButton("Stock");
		txtS.setBounds(110, 140, 120, 20);
		getContentPane().add(txtS);
		txtS.addActionListener(handler);

		txtOT = new JButton("Order Types");
		txtOT.setBounds(110, 180, 120, 20);
		getContentPane().add(txtOT);
		txtOT.addActionListener(handler);

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
		if (objects.get(0) != null) {
		for (Field field : objects.get(0).getClass().getDeclaredFields()) {
			modelC.addColumn(field.getName());
		}
		for (Object o : objects) {
			try {
				modelC.addRow(
						(String[]) o.getClass().getMethod("toStringVector", null).invoke(o, null));
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
		if (objects.get(0) != null) {
			for (Field field : objects.get(0).getClass().getDeclaredFields()) {
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
			if (event.getSource() == txtC) {
				new TableWindow(new Client(0));
			}
			if (event.getSource() == txtS) {
				new TableWindow(new Stock(0));
			}
			if (event.getSource() == txtO) {
				new TableWindow(new Orders(0));
			}
			if (event.getSource() == txtP) {
				new TableWindow(new Product(0));
			}
			if (event.getSource() == txtOT) {
				new TableWindow(new OrderType(0));
			}
		}
	}

}

