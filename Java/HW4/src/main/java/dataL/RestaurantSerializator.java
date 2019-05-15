package dataL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;

import businessL.MenuItem;

public class RestaurantSerializator {

	public static void write(TreeSet<MenuItem> menu) {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("D:\\All kind of stuff\\me java\\Udemy\\HW4\\Menu.ser");
			ObjectOutputStream out;
			try {
				out = new ObjectOutputStream(fileOut);
				out.writeObject(menu);
				out.close();
				fileOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}
		} catch (FileNotFoundException e1) {

		}

	}

	@SuppressWarnings("unchecked")
	public static TreeSet<MenuItem> read() {
		TreeSet<MenuItem> menu = null;

		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("D:\\All kind of stuff\\me java\\Udemy\\HW4\\Menu.ser");
			ObjectInputStream in;
			try {
				in = new ObjectInputStream(fileIn);
				try {
					menu = (TreeSet<MenuItem>) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block

				}

				in.close();
				fileIn.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block

			}
		} catch (FileNotFoundException e) {



		}

		return menu;

	}

}
