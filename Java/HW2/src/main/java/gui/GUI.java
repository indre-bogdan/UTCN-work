package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.Controller;
import server.Client;
import server.Server;

/**
 * The graphic user interface
 * 
 * @author IndreBogdan
 *
 */
public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel[] txt;
	private JLabel txtNrOfServers;
	private JLabel txtMaxPT;
	private JLabel txtMinPT;
	private JLabel txtTimeInterval;
	private JLabel txtMinAR;
	private JLabel txtMaxAR;
	private JLabel txtNrOfClients;
	private JLabel txtNrOfServers2;
	private JLabel txtMaxPT2;
	private JLabel txtMinPT2;
	private JLabel txtTimeInterval2;
	private JLabel txtMinAR2;
	private JLabel txtMaxAR2;
	private JLabel txtNrOfClients2;
	private JLabel[] txtS;
	private JLabel txtGC;
	private JLabel txtError;
	private JLabel txtL;
	private JTextField txtfieldNrOfServers;
	private JTextField txtfieldMaxPT;
	private JTextField txtfieldMinPT;
	private JTextField txtfieldTimeInterval;
	private JTextField txtfieldMinAR;
	private JTextField txtfieldMaxAR;
	private JTextField txtfieldNrOfClients;
	private JTextField txtfieldstartT;
	private JTextField txtfieldfinishT;
	private JButton StartB;
	private JButton ResetB;
	private JButton ComputeB;
	private JButton QueueB;
	private JButton[] SB;
	private DefaultListModel<String>[] model;
	private JScrollPane[] scrollPane;
	private JList<String>[] str;
	private DefaultListModel<String> modelC;
	private JScrollPane scrollPaneC;
	private JList<String> strC;
	public static DefaultListModel<String> modelL;
	private JScrollPane scrollPaneL;
	private JList<String> strL;
	private TheHandler handler;

	@SuppressWarnings("unchecked")
	public GUI() {
		super("Queue System");
		setLayout(null);
		this.setBounds(50, 50, 1200, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		txt = new JLabel[5];
		txtS = new JLabel[5];
		model = new DefaultListModel[5];
		str = new JList[5];
		scrollPane = new JScrollPane[5];
		SB = new JButton[5];

		txtNrOfServers = new JLabel("Number of servers");
		txtNrOfServers.setBounds(950, 70, 110, 15);
		add(txtNrOfServers);

		txtMaxPT = new JLabel("Max PT");
		txtMaxPT.setBounds(950, 130, 110, 15);
		add(txtMaxPT);

		txtError = new JLabel("");
		txtError.setBounds(950, 230, 200, 15);
		add(txtError);

		txtMinPT = new JLabel("Min PT");
		txtMinPT.setBounds(950, 110, 110, 15);
		add(txtMinPT);

		txtTimeInterval = new JLabel("Simulation time");
		txtTimeInterval.setBounds(950, 50, 110, 15);
		add(txtTimeInterval);

		txtMinAR = new JLabel("Min AR");
		txtMinAR.setBounds(950, 150, 110, 15);
		add(txtMinAR);

		txtMaxAR = new JLabel("Max AR");
		txtMaxAR.setBounds(950, 170, 110, 15);
		add(txtMaxAR);

		txtNrOfClients = new JLabel("Number of Clients");
		txtNrOfClients.setBounds(950, 90, 110, 15);
		add(txtNrOfClients);

		txtNrOfServers2 = new JLabel("");
		txtNrOfServers2.setBounds(1100, 70, 50, 15);
		add(txtNrOfServers2);

		txtMaxPT2 = new JLabel("");
		txtMaxPT2.setBounds(1100, 130, 50, 15);
		add(txtMaxPT2);

		txtMinPT2 = new JLabel("");
		txtMinPT2.setBounds(1100, 110, 50, 15);
		add(txtMinPT2);

		txtTimeInterval2 = new JLabel("");
		txtTimeInterval2.setBounds(1100, 50, 50, 15);
		add(txtTimeInterval2);

		txtMinAR2 = new JLabel("");
		txtMinAR2.setBounds(1100, 150, 50, 15);
		add(txtMinAR2);

		txtMaxAR2 = new JLabel("");
		txtMaxAR2.setBounds(1100, 170, 50, 15);
		add(txtMaxAR2);

		txtNrOfClients2 = new JLabel("");
		txtNrOfClients2.setBounds(1100, 90, 50, 15);
		add(txtNrOfClients2);

		txtGC = new JLabel("Generated Clients");
		txtGC.setBounds(40, 370, 150, 15);
		add(txtGC);

		txtL = new JLabel("LOG");
		txtL.setBounds(530, 370, 150, 15);
		add(txtL);

		txtfieldNrOfServers = new JTextField(30);
		txtfieldNrOfServers.setBounds(1100, 70, 50, 15);
		getContentPane().add(txtfieldNrOfServers);
		handler = new TheHandler();
		txtfieldNrOfServers
				.setToolTipText("Max 5");
		txtfieldNrOfServers.addActionListener(handler);

		txtfieldTimeInterval = new JTextField(30);
		txtfieldTimeInterval.setBounds(1100, 50, 50, 15);
		getContentPane().add(txtfieldTimeInterval);
		txtfieldTimeInterval
				.setToolTipText("Simulation time");
		txtfieldTimeInterval.addActionListener(handler);

		txtfieldMaxPT = new JTextField(30);
		txtfieldMaxPT.setBounds(1100, 130, 50, 15);
		getContentPane().add(txtfieldMaxPT);
		txtfieldMaxPT
				.setToolTipText("Max ProcessingTime");
		txtfieldMaxPT.addActionListener(handler);

		txtfieldMinPT = new JTextField(30);
		txtfieldMinPT.setBounds(1100, 110, 50, 15);
		getContentPane().add(txtfieldMinPT);
		txtfieldMinPT
				.setToolTipText("Min ProcessingTime");
		txtfieldMinPT.addActionListener(handler);

		txtfieldMinAR = new JTextField(30);
		txtfieldMinAR.setBounds(1100, 150, 50, 15);
		getContentPane().add(txtfieldMinAR);
		txtfieldMinAR
				.setToolTipText("Min ArrivalTime");
		txtfieldMinAR.addActionListener(handler);

		txtfieldNrOfClients = new JTextField(30);
		txtfieldNrOfClients.setBounds(1100, 90, 50, 15);
		getContentPane().add(txtfieldNrOfClients);
		txtfieldNrOfClients
				.setToolTipText("Max number of Clients = 100");
		txtfieldNrOfClients.addActionListener(handler);

		txtfieldMaxAR = new JTextField(30);
		txtfieldMaxAR.setBounds(1100, 170, 50, 15);
		getContentPane().add(txtfieldMaxAR);
		txtfieldMaxAR
				.setToolTipText("Max Arrival Time");
		txtfieldMaxAR.addActionListener(handler);

		txtfieldstartT = new JTextField(30);
		txtfieldstartT.setBounds(890, 480, 50, 30);
		getContentPane().add(txtfieldstartT);
		txtfieldstartT
				.setToolTipText("The start time for the computation");
		txtfieldstartT.addActionListener(handler);

		txtfieldfinishT = new JTextField(30);
		txtfieldfinishT.setBounds(950, 480, 50, 30);
		getContentPane().add(txtfieldfinishT);
		txtfieldfinishT
				.setToolTipText("The finish time");
		txtfieldfinishT.addActionListener(handler);

		StartB = new JButton("Start");
		StartB.setBounds(950, 10, 70, 20);
		getContentPane().add(StartB);
		StartB.addActionListener(handler);

		ResetB = new JButton("Reset");
		ResetB.setBounds(1100, 10, 70, 20);
		getContentPane().add(ResetB);
		ResetB.addActionListener(handler);

		ComputeB = new JButton("Compute");
		ComputeB.setBounds(900, 450, 100, 20);
		getContentPane().add(ComputeB);
		ComputeB.addActionListener(handler);

		QueueB = new JButton("QueueStrategy");
		QueueB.setBounds(950, 200, 200, 20);
		getContentPane().add(QueueB);
		QueueB.addActionListener(handler);

		modelL = new DefaultListModel<String>();
		strL = new JList<String>(modelL);
		scrollPaneL = new JScrollPane(strL);
		scrollPaneL.setBounds(300, 400, 500, 200);
		scrollPaneL.setVisible(true);
		this.add(scrollPaneL);

		this.setVisible(true);
	}

	public void initSetup(List<Server> servers) {
		for (int i = 0; i < servers.size(); i++) {
			txt[i] = new JLabel("");
			txt[i].setBounds(25 + i * 185, 50, 150, 15);
			add(txt[i]);

			txtS[i] = new JLabel("SERVER " + i);
			txtS[i].setBounds(15 + i * 185, 10, 200, 50);
			txtS[i].setFont(new Font("Serif", Font.BOLD, 30));
			add(txtS[i]);

			SB[i] = new JButton(Integer.toString(i));
			SB[i].setBounds(850 + i * 50, 400, 45, 20);
			getContentPane().add(SB[i]);
			SB[i].addActionListener(handler);
		}
	}

	public void updateClients(List<Server> servers, List<Client> generatedClients) {
		for (int i = 0; i < servers.size(); i++) {
			model[i] = new DefaultListModel<String>();
			if (servers.get(i).getCurrentClient() != null) {
				txt[i].setText(servers.get(i).getCurrentClient().toString2());
				if (servers.get(i).getCurrentClient().getTimeLeft() == 0)
					txt[i].setText("");
			}

			for (Client c : servers.get(i).getClients()) {
				model[i].addElement(c.toString2());
			}
			str[i] = new JList<String>(model[i]);
			scrollPane[i] = new JScrollPane(str[i]);
			scrollPane[i].setBounds(20 + i * 185, 70, 150, 200);
			scrollPane[i].setVisible(true);
			this.add(scrollPane[i]);
		}


	}

	public void updateGeneratedClients(List<Client> generatedClients) {
		modelC = new DefaultListModel<String>();
		modelC.clear();
		for (Client c : generatedClients) {
			modelC.addElement(c.toString3());
		}
		strC = new JList<String>(modelC);
		scrollPaneC = new JScrollPane(strC);
		scrollPaneC.setBounds(20, 400, 150, 200);
		scrollPaneC.setVisible(true);
		this.add(scrollPaneC);

	}

	public class TheHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == StartB) {
				Controller.start = true;
			}

			if (event.getSource() == ResetB) {
				Controller.reset = true;
			}

			if (event.getSource() == ComputeB) {
				Controller.compute = true;
			}

			if (event.getSource() == QueueB) {
				Controller.start = true;
			}

			if (event.getSource() == txtfieldfinishT) {
				try {
					if (Integer.parseInt(txtfieldfinishT.getText()) > Controller.startTime)
							Controller.finishTime = Integer.parseInt(txtfieldfinishT.getText());
					else
						throw new IllegalArgumentException("Finish Time must be bigger than start time");

				} catch (Exception e) {
					txtError.setText(e.getMessage());
				}

			}

			if (event.getSource() == txtfieldstartT) {
				try {
					Controller.startTime = Integer.parseInt(txtfieldstartT.getText());

				} catch (Exception e) {
					txtError.setText(e.getMessage());
				}

			}

			if (event.getSource() == SB[0]) {
				Controller.SB0 = true;
			}
			if (event.getSource() == SB[1]) {
				Controller.SB1 = true;
			}
			if (event.getSource() == SB[2]) {
				Controller.SB2 = true;
			}
			if (event.getSource() == SB[3]) {
				Controller.SB3 = true;
			}
			if (event.getSource() == SB[4]) {
				Controller.SB4 = true;
			}

			if (event.getSource() == txtfieldNrOfServers) {
				try {
					if (Integer.parseInt(txtfieldNrOfServers.getText()) < 6) {
						Controller.numberOfServers = Integer.parseInt(txtfieldNrOfServers.getText());
						txtfieldNrOfServers.setVisible(false);
						txtNrOfServers2.setText(Integer.toString(Controller.numberOfServers));
						txtNrOfServers2.setVisible(true);
					} else
						throw new IllegalArgumentException("Max 5 servers");

				} catch (Exception e) {
					txtError.setText(e.getMessage());
				}

			}

			if (event.getSource() == txtfieldNrOfClients) {
				try {
					if (Integer.parseInt(txtfieldNrOfClients.getText()) < 100) {
						Controller.numberOfClients = Integer.parseInt(txtfieldNrOfClients.getText());
						txtfieldNrOfClients.setVisible(false);
						txtNrOfClients2.setText(Integer.toString(Controller.numberOfClients));
						txtNrOfClients2.setVisible(true);
					} else
						throw new IllegalArgumentException("Max 100 clients");

				} catch (Exception e) {
					txtError.setText(e.getMessage());
				}

			}

			if (event.getSource() == txtfieldTimeInterval) {
				try {
					Controller.timeLimit = Integer.parseInt(txtfieldTimeInterval.getText());
					txtfieldTimeInterval.setVisible(false);
					txtTimeInterval2.setText(Integer.toString(Controller.timeLimit));
					txtTimeInterval2.setVisible(true);

				} catch (Exception e) {
					txtError.setText(e.getMessage());
				}

			}

			if (event.getSource() == txtfieldMinPT) {
				try {
					Controller.minProcessingTime = Integer.parseInt(txtfieldMinPT.getText());
					txtfieldMinPT.setVisible(false);
					txtMinPT2.setText(Integer.toString(Controller.minProcessingTime));
					txtMinPT2.setVisible(true);

				} catch (Exception e) {
					txtError.setText(e.getMessage());
				}

			}

			if (event.getSource() == txtfieldMaxPT) {
				try {
					if (Integer.parseInt(txtfieldMaxPT.getText()) > Controller.minProcessingTime) {
						Controller.maxProcessingTime = Integer.parseInt(txtfieldMaxPT.getText());
						txtfieldMaxPT.setVisible(false);
						txtMaxPT2.setText(Integer.toString(Controller.maxProcessingTime));
						txtMaxPT2.setVisible(true);
					} else
						throw new IllegalArgumentException("MaxPT must be bigger than MinPT");

				} catch (Exception e) {
					txtError.setText(e.getMessage());
				}

			}

			if (event.getSource() == txtfieldMaxAR) {
				try {
					if (Integer.parseInt(txtfieldMaxAR.getText()) > Controller.minArrivalTime) {
						Controller.maxArrivalTime = Integer.parseInt(txtfieldMaxAR.getText());
						txtfieldMaxAR.setVisible(false);
						txtMaxAR2.setText(Integer.toString(Controller.maxArrivalTime));
						txtMaxAR2.setVisible(true);
					} else
						throw new IllegalArgumentException("MaxAR must be bigger than MinAR");

				} catch (Exception e) {
					txtError.setText(e.getMessage());
				}

			}

			if (event.getSource() == txtfieldMinAR) {
				try {
					Controller.minArrivalTime = Integer.parseInt(txtfieldMinAR.getText());
					txtfieldMinAR.setVisible(false);
					txtMinAR2.setText(Integer.toString(Controller.minArrivalTime));
					txtMinAR2.setVisible(true);

				} catch (Exception e) {
					txtError.setText(e.getMessage());
				}

			}
		}
	}

}
