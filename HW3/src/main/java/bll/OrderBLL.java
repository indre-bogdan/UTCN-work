package bll;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

import bll.validators.IdValidator;
import bll.validators.OrderValidator;
import bll.validators.Validator;
import dao.DAO;
import model.Client;
import model.OrderType;
import model.Orders;
import model.Product;
import model.Stock;

public class OrderBLL {
	private List<Validator<Object>> validators;

	public OrderBLL() {
		validators = new ArrayList<Validator<Object>>();
		validators.add(new IdValidator());
		validators.add(new OrderValidator());
	}

	public Orders findOrderById(int id) {
		Orders a = new Orders(id);
		Orders st = (Orders) DAO.findById(a);
		if (st == null) {
			throw new NoSuchElementException("The order with id =" + id + " was not found!");
		}
		return st;
	}

	public int insertOrder(Orders order) {

		for (Validator<Object> v : validators) {
			v.validate(order);
		}
		int id = DAO.insert(order);
		if (id != -1) {
			int quant = order.getQuantity();
			OrderType s = (OrderType) DAO.findById(new OrderType(order.getIdOrderType()));
			if (s.getName().contains("sell")) {
				quant = -quant;
			}
			Stock a = new Stock(order.getIdProduct());

			a = (Stock) DAO.findById(a);
			a.setQuantity(a.getQuantity() + quant);
			DAO.update(a);
			return 1;
		}
		return -1;
	}

	public int deleteOrder(int id) {
		Orders s = findOrderById(id);
		if (s != null) {
			return DAO.delete(s);
		} else {
			return -1;
		}
	}

	public List<Object> displayOrders() {
		Orders o = new Orders(0);
		return DAO.view(o);
	}

	public int updateOrder(int idO, int idC, int idP, int idOT, float price, int quantity) {
		Orders c = new Orders(idO, idC, idP, idOT, price, quantity);
		return DAO.update(c);
	}

	public int createBill(String id, Orders order) {
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("OrderBill" + id + ".pdf"));
			document.open();
			Client c = (Client) DAO.findById(new Client(order.getIdClient()));
			Font redFont = FontFactory.getFont(FontFactory.COURIER, 30, Font.BOLD, new CMYKColor(40, 40, 40, 100));
			Paragraph title = new Paragraph("Congratulation, " + c.getName() + "!\n\n\n", redFont);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			StringBuilder a = new StringBuilder();

			a.append("        You");
			OrderType ot = (OrderType) DAO.findById(new OrderType(order.getIdOrderType()));
			if (ot.getName().contains("sell")) {
				a.append(" are the proud owner of ");
			} else {
				a.append(" have sold us ");
			}
			a.append(order.getQuantity() + " ");
			Product p = (Product) DAO.findById(new Product(order.getIdProduct()));
			a.append(p.getName() + "s at the humble price of only " + order.getPrice()
					+ " a piece. We hope that you found this trade deal as atractive as we have found it and that you will look forward to other such deals with us.");

			document.add(new Paragraph(a.toString()));
			Paragraph ending = new Paragraph("\n\n\n\nBest regards,\nSimple Warehouse\n" + new Date());
			document.add(ending);
			document.addAuthor("Indre Bogdan");
			document.addCreationDate();
			document.addTitle("OrderBill" + id);
			document.close();
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {

			return -1;
		}
		return 1;
	}

	public void openBill(String id) throws IOException {
		File file = new File("C:\\Users\\Asus\\Desktop\\oop\\HW3\\HW3\\OrderBill" + id + ".pdf");

		Desktop desktop = Desktop.getDesktop();
		if (file.exists())
			desktop.open(file);
	}
}
