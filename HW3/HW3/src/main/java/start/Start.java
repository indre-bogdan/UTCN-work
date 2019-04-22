package start;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import presentation.GUI;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {

		GUI g = new GUI();
		// Client c = new Client("dsf", "sdfsadf", "dsfsf");
		// int a = DAO.insert(c);
		// System.out.println(a);
		// Product p = new Product("Computer", 100);
		// Stock s = new Stock(1, -20);
		// StockBLL stockBll = new StockBLL();
		// ProductBLL productBll = new ProductBLL();
		// ClientBLL clientBll = new ClientBLL();
		// int id = clientBll.insertClient(c);
		// int id = productBll.insertProduct(p)
		// int id = stockBll.insertStock(s);
		// int id2 = studentBll.insertStudent(student2);
		// if (id > 0) {
			// clientBll.findClientById(id);
		// }
		// stockBll.deleteStock(1);
		// System.out.println(studentBll.deleteStudent(1));
		// studentBll.displayStudents();
		// Generate error

		try {
			// studentBll.findStudentById(1);
			// studentBll.findStudentById(id2);

		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}

		// obtain field-value pairs for object through reflection
		// ReflectionExample.retrieveProperties(c);
	}

}
