package server;

public class Client {
	private int arrivalTime;
	private int name;
	private int processingTime;
	private int waitingTime;
	private int timeLeft;

	public Client(int arrivalTime, int processingTime, int name) {
		super();
		this.arrivalTime = arrivalTime;
		this.name = name;
		this.processingTime = processingTime;
		this.waitingTime = 0;
		this.timeLeft = processingTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(int processingTime) {
		this.processingTime = processingTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getName() {
		return this.name;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.append("Client ").append(name).append(" arrivalTime: ").append(this.arrivalTime)
				.append(" procesingTime: ").append(processingTime).append(" Waiting time: ").append(waitingTime)
				.append("\n").toString();
	}

	public String toString2() {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.append("Client ").append(name).append(" Processing: ").append(this.timeLeft).append("\n")
				.toString();
	}

	public String toString3() {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.append("Client ").append(name).append(" ArrivalTime: ").append(this.arrivalTime)
				.append("\n").toString();
	}
}
