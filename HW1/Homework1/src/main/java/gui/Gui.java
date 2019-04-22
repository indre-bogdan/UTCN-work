package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.Controller;

public class Gui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel txt1;
	private JLabel txt2;
	private JLabel txt3;
	private JLabel txt4;
	private JLabel txt5;
	private JLabel txt6;
	private JLabel txtResult0;
	private JLabel txtResult1;
	private JTextField txtfield;
	private JTextField txtfield2;
	private JButton additionB;
	private JButton subtractionB;
	private JButton productB;
	private JButton divisionB;
	private JButton integrationB;
	private JButton differentiationB;
	private Controller c = new Controller();

	public Gui() {
		super("Operations on polynomials");
		setLayout(null);
		this.setBounds(400, 100, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		txt1 = new JLabel("Introduce polynomials");
		txt1.setBounds(100, 10, 150, 15);
		add(txt1);

		txt2 = new JLabel("Choose an operation");
		txt2.setBounds(480, 10, 150, 15);
		add(txt2);

		txt3 = new JLabel("");
		txt3.setBounds(20, 70, 150, 15);
		add(txt3);

		txt4 = new JLabel("");
		txt4.setBounds(20, 140, 150, 15);
		add(txt4);

		txt5 = new JLabel("");
		txt5.setBounds(350, 50, 50, 50);
		txt5.setFont(new Font("Serif", Font.BOLD, 30));
		add(txt5);

		txt6 = new JLabel("Results");
		txt6.setBounds(20, 200, 150, 30);
		txt6.setFont(new Font("Serif", Font.BOLD, 20));
		add(txt6);

		txtResult0 = new JLabel("");
		txtResult0.setBounds(20, 240, 300, 15);
		add(txtResult0);

		txtResult1 = new JLabel("");
		txtResult1.setBounds(20, 270, 300, 15);
		add(txtResult1);

		txtfield = new JTextField(30);
		txtfield.setBounds(20, 30, 300, 30);
		getContentPane().add(txtfield);
		TheHandler handler = new TheHandler();
		txtfield.setToolTipText("Input must be [sign][coeff][x^][power] for each monomial. One space between them");
		txtfield.addActionListener(handler);

		txtfield2 = new JTextField(30);
		txtfield2.setBounds(20, 100, 300, 30);
		getContentPane().add(txtfield2);
		txtfield2.setToolTipText("Input must be [sign][coeff][x^][power] for each monomial. One space between them");
		txtfield2.addActionListener(handler);

		integrationB = new JButton("I");
		integrationB.setBounds(570, 100, 50, 50);
		getContentPane().add(integrationB);
		integrationB.addActionListener(handler);

		additionB = new JButton("+");
		additionB.setBounds(450, 40, 50, 50);
		getContentPane().add(additionB);
		additionB.addActionListener(handler);

		subtractionB = new JButton("-");
		subtractionB.setBounds(510, 40, 50, 50);
		getContentPane().add(subtractionB);
		subtractionB.addActionListener(handler);

		productB = new JButton("*");
		productB.setBounds(570, 40, 50, 50);
		getContentPane().add(productB);
		productB.addActionListener(handler);

		divisionB = new JButton("/");
		divisionB.setBounds(450, 100, 50, 50);
		getContentPane().add(divisionB);
		divisionB.addActionListener(handler);

		differentiationB = new JButton("dx");
		differentiationB.setBounds(510, 100, 50, 50);
		getContentPane().add(differentiationB);
		differentiationB.addActionListener(handler);

	}

	public class TheHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String pol1 = new String();
			String pol2 = new String();
			if (event.getSource() == txtfield) {

				c.getPolynomial(0).getPol().clear();
				pol1 = String.format("%s", event.getActionCommand());
				txtfield.setText("");

				if (c.StringToPoly(pol1, 0)) {
					txt3.setText(c.getPolynomial(0).toString());
				} else
					txt3.setText("Incorrect input");

			}

			if (event.getSource() == txtfield2) {

				c.getPolynomial(1).getPol().clear();
				pol2 = String.format("%s", event.getActionCommand());
				txtfield2.setText("");

				if (c.StringToPoly(pol2, 1)) {
					txt4.setText(c.getPolynomial(1).toString());
				} else
					txt4.setText("Incorrect input");
			}

			if (event.getSource() == productB) {
				c.product();
				txtResult0.setText(c.getResult()[0].toString());
				txtResult1.setText("");
				txt5.setText("*");
			}

			if (event.getSource() == additionB) {
				c.addition();
				txtResult0.setText(c.getResult()[0].toString());
				txtResult1.setText("");
				txt5.setText("+");
			}

			if (event.getSource() == integrationB) {
				try {
					c.integration();
					txtResult0.setText(c.getResult()[0].toString());
					txtResult1.setText(c.getResult()[1].toString());
				} catch (Exception e) {
					txtResult0.setText(e.getMessage());
					txtResult1.setText(e.getMessage());
				}

				txt5.setText("I");
			}

			if (event.getSource() == differentiationB) {
				c.differentiation();
				txtResult0.setText(c.getResult()[0].toString());
				txtResult1.setText(c.getResult()[1].toString());
				txt5.setText("dx");
			}

			if (event.getSource() == subtractionB) {
				c.subtraction();
				txtResult0.setText(c.getResult()[0].toString());
				txtResult1.setText("");
				txt5.setText("-");
			}

			if (event.getSource() == divisionB) {
				try {
					c.division();
				txtResult0.setText("q " + c.getResult()[0].toString());
				txtResult1.setText("r " + c.getResult()[1].toString());
				} catch (Exception e) {
					txtResult0.setText(e.getMessage());
					txtResult1.setText(e.getMessage());
				}
				txt5.setText("/");
			}
		}
	}
}

