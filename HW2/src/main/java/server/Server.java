package server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import controller.Controller;
import gui.GUI;

public class Server implements Runnable {
	private int name;
	private int[] servedClients = new int[100];
	private int[] serviceTime = new int[100];
	private int[] waitingQueueTime = new int[100];
	private int[] emptyQueueTime = new int[100];
	private int[] nrOfClients = new int[100];
	private Client currentClient;
	private ArrayBlockingQueue<Client> clients;
	private AtomicInteger waitingTime;

	public Server(int name) {
		super();
		this.clients = new ArrayBlockingQueue<Client>(1000);
		this.waitingTime = new AtomicInteger(0);
		this.name = name;
	}

	public void addClient(Client c) {
		try {
			c.setWaitingTime(waitingTime.get());
			waitingTime.addAndGet(c.getProcessingTime());
			this.clients.put(c);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		while (Controller.currentTime < Controller.timeLimit || !this.clients.isEmpty()) {
			try {
				if (clients.isEmpty())
					this.emptyQueueTime[Controller.currentTime] = 1;
				else {
					this.emptyQueueTime[Controller.currentTime] = 0;
					currentClient = clients.poll();
				for (int i = 1; i <= currentClient.getProcessingTime(); i++) {
					Thread.sleep(1000);
					currentClient.setTimeLeft(currentClient.getTimeLeft() - 1);
					waitingTime.decrementAndGet();
						nrOfClients[Controller.currentTime] = clients.size();
					if (currentClient.getTimeLeft() == 0) {

						this.servedClients[Controller.currentTime] = 1;
						waitingQueueTime[Controller.currentTime] = currentClient.getWaitingTime();
						this.serviceTime[Controller.currentTime] = currentClient.getProcessingTime();
						GUI.modelL.addElement("Server " + this.name + " done with Client " + currentClient.getName()
								+ " waitingTime: " + currentClient.getWaitingTime());
				
					}

				}
					currentClient = null;
				}


			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}

		}
	}

	public ArrayBlockingQueue<Client> getClients() {
		return clients;
	}

	public int gerNrOfClients() {
		return this.clients.size();
	}
	public int getWaitingTime() {
		return waitingTime.get();
	}

	public int getName() {
		return this.name;
	}

	public int[] getServedClients() {
		return servedClients;
	}

	public Client getCurrentClient() {
		return currentClient;
	}

	public void setCurrentClient(Client currentClient) {
		this.currentClient = currentClient;
	}

	public int[] getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int[] serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int[] getWaitingQueueTime() {
		return waitingQueueTime;
	}

	public void setWaitingQueueTime(int[] waitingQueueTime) {
		this.waitingQueueTime = waitingQueueTime;
	}

	public int[] getEmptyQueueTime() {
		return emptyQueueTime;
	}

	public void setEmptyQueueTime(int[] emptyQueueTime) {
		this.emptyQueueTime = emptyQueueTime;
	}

	public void setServedClients(int[] servedClients) {
		this.servedClients = servedClients;
	}

	public int[] getNrOfClients() {
		return nrOfClients;
	}

	public void setNrOfClients(int[] nrOfClients) {
		this.nrOfClients = nrOfClients;
	}

}
