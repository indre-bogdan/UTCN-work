package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gui.GUI;
import server.Client;
import server.Server;
import strategy.SelectionPolicy;

public class Controller implements Runnable {
	public static boolean start = false;
	public static boolean reset = false;
	public static boolean compute = false;
	public static int timeLimit = -1;
	public static int currentTime;
	public static int maxProcessingTime = -1;
	public static int minProcessingTime = -1;
	public static int maxArrivalTime = -1;
	public static int minArrivalTime = -1;
	public static int numberOfServers = -1;
	public static int numberOfClients = -1;
	public static boolean SB0 = false;
	public static boolean SB1 = false;
	public static boolean SB2 = false;
	public static boolean SB3 = false;
	public static boolean SB4 = false;
	public static int startTime = -1;
	public static int finishTime = -1;
	public static boolean queueStrategy = false;

	private GUI gui;
	private Scheduler scheduler;
	private List<Client> generatedClients;
	private List<Server> serv;


	public Controller() {
		gui = new GUI();
		this.generatedClients = new ArrayList<Client>();

	}

	public void generateClients() {
		Random random = new Random();
		int procTime;
		int arrivalTime;
		for (int i = 0; i < numberOfClients; i++) {
			procTime = random.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;
			arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
			Client c = new Client(arrivalTime, procTime, i);
			this.generatedClients.add(c);
		}
		generatedClients.sort(new Comp());

	}

	private boolean closeCondition() {
		if (currentTime < timeLimit)
			return false;
		for (Server s : scheduler.getServers()) {
			if (!s.getClients().isEmpty())
				return false;
			if (s.getCurrentClient() != null)
				return false;
		}
		return true;
	}
	public void run() {
		gui.initSetup(scheduler.getServers());
		for (Thread t : this.scheduler.getThreads()) {
			t.start();
		}
		currentTime = 0;

		while (!closeCondition()) {
			GUI.modelL.addElement(Integer.toString(currentTime));
			gui.updateGeneratedClients(generatedClients);
			for (int i = 0; i <= generatedClients.size() - 1; i++) {
				if (generatedClients.get(i).getArrivalTime() <= currentTime) {
					scheduler.queueClient(generatedClients.get(i));
					generatedClients.remove(i);
					i--;
					
				}
				gui.updateClients(scheduler.getServers(), generatedClients);

			}
			gui.updateClients(scheduler.getServers(), generatedClients);
			currentTime++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

		}
		gui.updateClients(scheduler.getServers(), generatedClients);
		generatedClients.clear();
		gui.updateGeneratedClients(generatedClients);
		Thread.currentThread().interrupt();
	}

	public void Compute() {

		serv = new ArrayList<Server>();
		if (SB0)
			serv.add(scheduler.getServers().get(0));
		if (SB1)
			serv.add(scheduler.getServers().get(1));
		if (SB2)
			serv.add(scheduler.getServers().get(2));
		if (SB3)
			serv.add(scheduler.getServers().get(3));
		if (SB4)
			serv.add(scheduler.getServers().get(4));
		float averageWaitingTime = 0;
		float EmptyQueueTime = 0;
		float averageServiceTime = 0;
		float servedClients = 0;
		int peakTime = 0;
		int maxClients = 0;
		int[] numberOfClients = new int[100];
		for (Server s : serv) {

			for (int i = startTime; i < finishTime; i++) {
				numberOfClients[i] += s.getNrOfClients()[i];
				averageWaitingTime += s.getWaitingQueueTime()[i];
				EmptyQueueTime += s.getEmptyQueueTime()[i];
				averageServiceTime += s.getServiceTime()[i];
				servedClients += s.getServedClients()[i];
			}
		}
		for (int i = startTime; i < finishTime; i++) {
			if (numberOfClients[i] > maxClients) {
				maxClients = numberOfClients[i];
				peakTime = i;
			}
		}
		if (servedClients == 0)
			GUI.modelL.addElement("Average waiting time: " + 0 + " Empty queue time: " + EmptyQueueTime
					+ " Average service time: " + 0 + " Peak time: " + peakTime + " with " + maxClients + " clients");
		else
			GUI.modelL
					.addElement("Average waiting time: " + averageWaitingTime / (servedClients) + " Empty queue time: "
							+ EmptyQueueTime + " Average service time: " 
							+ averageServiceTime / (servedClients) + " Peak time: " + peakTime + " with " + maxClients
							+ " clients");
	}
	public static void main(String[] args) {

		Controller c = new Controller();
		while (true) {
			if (Controller.reset) {
				c.gui.dispose();
				try {
					c.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				c = new Controller();
				Controller.reset = false;
			}

			if (Controller.start && Controller.timeLimit != -1 && Controller.maxProcessingTime != -1
					&& Controller.minProcessingTime != -1 && Controller.maxArrivalTime != -1
					&& Controller.minArrivalTime != -1 && Controller.numberOfServers != -1
					&& Controller.numberOfClients != -1) {
				c.setScheduler(new Scheduler(numberOfServers));
				if (queueStrategy) {
					c.scheduler.changeStrategy(SelectionPolicy.QUEUE);
					queueStrategy = false;
				}
				c.generateClients();
				Thread t = new Thread(c);
				t.start();
				Controller.start = false;
			}
			if (compute && startTime != -1 && finishTime != -1) {
				c.Compute();
				compute = false;
				SB0 = false;
				SB1 = false;
				SB2 = false;
				SB3 = false;
				SB4 = false;
			}
			System.out.print("");
		}

	}

	public List<Client> getClients() {
		return generatedClients;
	}

	public GUI getGui() {
		return this.gui;
	}

	public void setScheduler(Scheduler s) {
		this.scheduler = s;
	}

}

