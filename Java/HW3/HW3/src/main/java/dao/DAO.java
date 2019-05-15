package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.Client;
import model.OrderType;
import model.Orders;
import model.Product;
import model.Stock;

/**
 * Data Access Object; Used to access the data from the table; 
 * Reflection is used to know the required table at runtime
 * 
 * @author IndreBogdan
 *
 */
public class DAO {
	public static Object findById(Object o) {
		String findStatementString = "SELECT * FROM ";
		String table = o.getClass().getSimpleName().toLowerCase();
		int id = 0;
		String idName = new String();
		Field f = o.getClass().getDeclaredFields()[0];
		f.setAccessible(true);
		try {
			id = f.getInt(o);
			idName = f.getName();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Object toReturn = null;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		
		findStatementString = findStatementString.concat(table + " where " + idName + " = " + id);
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			rs = findStatement.executeQuery();
			rs.next();

			if (table.equals("client")) {
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				toReturn = new Client(id, name, address, phone);
			}
			if (table.equals("orders")) {
				int idClient = rs.getInt("idClient");
				int idProduct = rs.getInt("idProduct");
				int idOrderType = rs.getInt("idOrderType");
				float price = rs.getFloat("price");
				int quantity = rs.getInt("quantity");
				toReturn = new Orders(id, idClient, idProduct, idOrderType, price, quantity);
			}
			if (table.equals("ordertype")) {
				String name = rs.getString("name");
				toReturn = new OrderType(id, name);
			}

			if (table.equals("product")) {
				String name = rs.getString("name");
				float nominalPrice = rs.getFloat("nominalPrice");
				toReturn = new Product(id, name, nominalPrice);
			}
			if (table.equals("stock")) {
				int quantity = rs.getInt("quantity");
				toReturn = new Stock(id, quantity);
			}
		} catch (SQLException e) {

		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static int insert(Object o) {
		String insertStatementString = "INSERT INTO ";
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		int insertedId = -1;
		String table = o.getClass().getSimpleName().toLowerCase();
		insertStatementString = insertStatementString.concat(table + " (");
		for (Field f : o.getClass().getDeclaredFields()) {
			// if (!f.getName().equals(o.getClass().getDeclaredFields()[0].getName()))
				insertStatementString = insertStatementString.concat(f.getName() + ",");

		}
		insertStatementString = insertStatementString.substring(0, insertStatementString.length() - 1);
		insertStatementString = insertStatementString.concat(") VALUES (");
		for (Field f : o.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				// if (!f.getName().equals(o.getClass().getDeclaredFields()[0].getName())) {
					if (f.getType() == String.class) {
						insertStatementString = insertStatementString.concat("'" + f.get(o) + "'" + ",");
					} else
						insertStatementString = insertStatementString.concat(f.get(o) + ",");
				// }
					
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		insertStatementString = insertStatementString.substring(0, insertStatementString.length() - 1);
		insertStatementString = insertStatementString.concat(")");
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString);
			insertStatement.executeUpdate();

			insertedId = 1;
		} catch (SQLException e) {

		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	public static int delete(Object o) {
		String deleteStatementString = "DELETE FROM ";
		String table = o.getClass().getSimpleName().toLowerCase();
		int id = 0;
		String idName = new String();
		Field f = o.getClass().getDeclaredFields()[0];
		f.setAccessible(true);
		try {
			id = f.getInt(o);
			idName = f.getName();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;

		deleteStatementString = deleteStatementString.concat(table + " where " + idName + " = " + id);
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.executeUpdate();
			return 1;
		} catch (SQLException e) {

			return -1;
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	public static int update(Object o) {
		String updateStatementString = "UPDATE ";
		String table = o.getClass().getSimpleName().toLowerCase();
		int id = 0;
		String idName = new String();
		Field f = o.getClass().getDeclaredFields()[0];
		f.setAccessible(true);
		try {
			id = f.getInt(o);
			idName = f.getName();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;

		updateStatementString = updateStatementString.concat(table + " SET ");

		for (Field fi : o.getClass().getDeclaredFields()) {
			fi.setAccessible(true);
			try {
				if (fi.get(o) != null) {
					if (!(fi.getType().isInstance(new String()) && ((String) fi.get(o)).equals("")))
					updateStatementString = updateStatementString
							.concat(fi.getName() + " = " + "'" + fi.get(o) + "'" + ",");
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		updateStatementString = updateStatementString.substring(0, updateStatementString.length() - 1);
		updateStatementString = updateStatementString.concat(" where " + idName + " = " + id);

		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.executeUpdate();
			return 1;
		} catch (SQLException e) {

			return -1;
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}

	}

	public static List<Object> view(Object o) {
		String viewStatementString = "SELECT * FROM ";
		List<Object> l = new ArrayList<Object>();
		String table = o.getClass().getSimpleName().toLowerCase();
		viewStatementString = viewStatementString.concat(table);

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement viewStatement = null;
		ResultSet rs = null;

		try {
			viewStatement = dbConnection.prepareStatement(viewStatementString);
			rs = viewStatement.executeQuery();

			while (rs.next()) {
				if (table.equals("client")) {
					int id = rs.getInt("idClient");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String phone = rs.getString("phone");
					l.add(new Client(id, name, address, phone));
				}
				if (table.equals("orders")) {
					int id = rs.getInt("idOrder");
					int idClient = rs.getInt("idClient");
					int idProduct = rs.getInt("idProduct");
					int idOrderType = rs.getInt("idOrderType");
					float price = rs.getFloat("price");
					int quantity = rs.getInt("quantity");
					l.add(new Orders(id, idClient, idProduct, idOrderType, price, quantity));
				}
				if (table.equals("ordertype")) {
					int id = rs.getInt("idOrderType");
					String name = rs.getString("name");
					l.add(new OrderType(id, name));
				}

				if (table.equals("product")) {
					int id = rs.getInt("idProduct");
					String name = rs.getString("name");
					float nominalPrice = rs.getFloat("nominalPrice");
					l.add(new Product(id, name, nominalPrice));
				}
				if (table.equals("stock")) {
					int id = rs.getInt("idProduct");
					int quantity = rs.getInt("quantity");
					l.add(new Stock(id, quantity));
				}
			}

		} catch (SQLException e) {

		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(viewStatement);
			ConnectionFactory.close(dbConnection);
		}
		return l;
	}
}
