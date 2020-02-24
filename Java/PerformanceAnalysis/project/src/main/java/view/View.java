package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View extends JFrame {
	private JButton startButton;
	public boolean START = false;
	private JTextArea text;
	private JScrollPane sc;

	public View() {

		super("Performance Test");
		setLayout(null);
		this.setBounds(400, 100, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		text = new JTextArea();
		text.setBounds(0, 0, 500, 500);
		sc = new JScrollPane(text);
		text.setEditable(false);
		getContentPane().add(text);

		TheHandler handler = new TheHandler();
		startButton = new JButton("Start Analysis");
		startButton.setBounds(520, 100, 150, 50);
		getContentPane().add(startButton);
		startButton.addActionListener(handler);

		this.setVisible(true);
	}

	public class TheHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == startButton) {
				START = true;
			}
		}
	}

	public void write(String a) {
		text.append(a);
	}


}

