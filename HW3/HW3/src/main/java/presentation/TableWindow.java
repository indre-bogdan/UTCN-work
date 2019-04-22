package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.OrderTypeBLL;
import bll.ProductBLL;
import bll.StockBLL;
import model.Client;
import model.Orders;
import model.Product;
import model.Stock;

public class TableWindow extends JFrame {


	private static final long serialVersionUID = 1L;
	private int id;
	private JLabel txt;
	private JLabel txtE;
	private JLabel txtId;
	private DefaultTableModel model;
	public JTable table;
	private List<Object> objects;
	private ProductBLL productBll;
	private OrderBLL orderBll;
	private StockBLL stockBll;
	private ClientBLL clientBll;
	private OrderTypeBLL orderTypeBll;
	private int[] size = { 0, 4, 3, 6, 2 };
	private JButton addB;
	private JButton deleteB;
	private JButton updateB;
	private JTextField[] txtF;

	public TableWindow(Object o) {
		super(o.getClass().getSimpleName());
		setLayout(null);
		this.setBounds(100, 100, 800, 500);

		if (o.getClass().getSimpleName().equals("Product")) {
			productBll = new ProductBLL();
			objects = productBll.displayProducts();
			txt = new JLabel(
					"                            Id                                               Name                                       NominalPrice");
			id = 2;
		}
		if (o.getClass().getSimpleName().equals("Stock")) {
			stockBll = new StockBLL();
			objects = stockBll.displayStocks();
			txt = new JLabel(
					"                            Id Product                                                                     Quantity");
			id = 4;
		}
		if (o.getClass().getSimpleName().equals("Orders")) {
			orderBll = new OrderBLL();
			objects = orderBll.displayOrders();
			txt = new JLabel(
					"       Id Order       Id Client            Id Product         Id Order Type         Price         Quantity");
			id = 3;
		}
		if (o.getClass().getSimpleName().equals("Client")) {
			clientBll = new ClientBLL();
			objects = clientBll.displayClients();
			txt = new JLabel(
					"                       Id                            Name                        Adddress                  Phone");
			id = 1;
		}
		if (o.getClass().getSimpleName().equals("OrderType")) {
			orderTypeBll = new OrderTypeBLL();
			objects = orderTypeBll.displayOrderTypes();
			txt = new JLabel(
					"                                 Id                                                                         Name");
			id = 5;
		}

		table = GUI.createTable(objects, model);
		table.setBounds(20, 20, 500, 200);
		this.add(table);


		txt.setBounds(20, 5, 500, 15);
		add(txt);

		txtE = new JLabel("");
		txtE.setBounds(20, 330, 400, 30);
		add(txtE);

		TheHandler2 handler2 = new TheHandler2();
		if (id != 5) {
			txtId = new JLabel("Id");
			txtId.setBounds(70, 270, 100, 30);
			add(txtId);

			txtF = new JTextField[size[id]];
			for (int i = 0; i < size[id]; i++) {
				txtF[i] = new JTextField(100);
				txtF[i].setBounds(20 + i * 100, 300, 100, 20);
				getContentPane().add(txtF[i]);
				txtF[i].addActionListener(handler2);
			}
		}
		if (id != 4 && id != 5) {
		addB = new JButton("Add");
		addB.setBounds(600, 20, 100, 20);
		getContentPane().add(addB);
		addB.addActionListener(handler2);

		deleteB = new JButton("Delete");
		deleteB.setBounds(600, 60, 100, 20);
		getContentPane().add(deleteB);
		deleteB.addActionListener(handler2);
		}
		if (id != 5) {
		updateB = new JButton("Update");
		updateB.setBounds(600, 100, 100, 20);
		getContentPane().add(updateB);
		updateB.addActionListener(handler2);
		}

		setVisible(true);
	}

	public class TheHandler2 implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == addB) {
				txtE.setText("");
				if(id == 1) {
					try {
					clientBll.insertClient(new Client(Integer.parseInt(txtF[0].getText()), txtF[1].getText(),
							txtF[2].getText(), txtF[3].getText()));
					} catch (Exception e) {
						txtE.setText(e.getMessage());
					}
					GUI.updateTable(table, clientBll.displayClients(), model);
				}
				if (id == 2) {
					try {
					productBll.insertProduct(new Product(Integer.parseInt(txtF[0].getText()), txtF[1].getText(),
							Float.parseFloat(txtF[2].getText())));
					} catch (Exception e) {
						txtE.setText(e.getMessage());
					}
					GUI.updateTable(table, productBll.displayProducts(), model);
				}
				if (id == 3) {
					try {
						Orders o = new Orders(Integer.parseInt(txtF[0].getText()), Integer.parseInt(txtF[1].getText()),
								Integer.parseInt(txtF[2].getText()), Integer.parseInt(txtF[3].getText()),
								Float.parseFloat(txtF[4].getText()), Integer.parseInt(txtF[5].getText()));
						orderBll.insertOrder(o);
						orderBll.createBill(txtF[0].getText(), o);
						orderBll.openBill(txtF[0].getText());
					} catch (Exception e) {
						txtE.setText(e.getMessage());
					}
					GUI.updateTable(table, orderBll.displayOrders(), model);
				}

			}
			if (event.getSource() == deleteB) {
				txtE.setText("");
				if (id == 1) {
					try {
					clientBll.deleteClient(Integer.parseInt(txtF[0].getText()));
					} catch (Exception e) {
						txtE.setText(e.getMessage());
					}
					GUI.updateTable(table, clientBll.displayClients(), model);
				}
				if (id == 2) {
					try {
					productBll.deleteProduct(Integer.parseInt(txtF[0].getText()));
					} catch (Exception e) {
						txtE.setText(e.getMessage());
					}
					GUI.updateTable(table, productBll.displayProducts(), model);
				}
				if (id == 3) {
					try {
						orderBll.deleteOrder(Integer.parseInt(txtF[0].getText()));
					} catch (Exception e) {
						txtE.setText(e.getMessage());
					}
					GUI.updateTable(table, orderBll.displayOrders(), model);
				}

			}
			if (event.getSource() == updateB) {
				txtE.setText("");
				if (id == 1) {
					try {
					clientBll.updateClient(Integer.parseInt(txtF[0].getText()), txtF[1].getText(), txtF[2].getText(),
							txtF[3].getText());
					} catch (Exception e) {
						txtE.setText(e.getMessage());
					}
					GUI.updateTable(table, clientBll.displayClients(), model);

				}
				if (id == 2) {
					try {
					productBll.updateProduct(Integer.parseInt(txtF[0].getText()), txtF[1].getText(),
							Float.parseFloat(txtF[2].getText()));
					} catch (Exception e) {
						txtE.setText(e.getMessage());
					}
					GUI.updateTable(table, productBll.displayProducts(), model);

				}
				if (id == 4) {
					try {
					stockBll.updateStock(Integer.parseInt(txtF[0].getText()), Integer.parseInt(txtF[1].getText()));
					} catch (Exception e) {
						txtE.setText(e.getMessage());
					}
					GUI.updateTable(table, stockBll.displayStocks(), model);

				}
			}
			if (event.getSource() == txtF[0]) {
				txtE.setText("");
				int id1 = Integer.parseInt(txtF[0].getText());
				if (id == 1) {
					try {
					Client a = clientBll.findClientById(id1);
					if (a != null) {
						txtF[0].setText(String.valueOf(id1));
						txtF[1].setText(a.getName());
						txtF[2].setText(a.getAddress());
						txtF[3].setText(a.getPhone());
					}
					} catch (Exception e) {
						txtE.setText(e.getMessage());
					}
				}
				if (id == 2) {
					try {
					Product p = productBll.findProductById(id1);
					if (p != null) {
						txtF[0].setText(String.valueOf(id1));
						txtF[1].setText(p.getName());
						txtF[2].setText(String.valueOf(p.getNominalPrice()));
					}
					} catch (Exception e) {
						txtE.setText(e.getMessage());
					}
				}
				if (id == 3) {
					try {
					Orders a = orderBll.findOrderById(id1);
					if (a != null) {
						txtF[0].setText(String.valueOf(id1));
						txtF[1].setText(String.valueOf(a.getIdClient()));
						txtF[2].setText(String.valueOf(a.getIdProduct()));
						txtF[3].setText(String.valueOf(a.getIdOrderType()));
						txtF[4].setText(String.valueOf(a.getPrice()));
						txtF[5].setText(String.valueOf(a.getQuantity()));
					}
					} catch (Exception e) {
						txtE.setText(e.getMessage());
					}
				}
				if (id == 4) {
					try {
					Stock p = stockBll.findStockById(id1);
					if (p != null) {
						txtF[0].setText(String.valueOf(id1));
						txtF[1].setText(String.valueOf(p.getQuantity()));
					}
					} catch (Exception e) {
						txtE.setText(e.getMessage());
					}
				}
			}

		}
	}

}
