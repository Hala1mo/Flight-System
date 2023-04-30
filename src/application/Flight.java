package application;

public class Flight implements Comparable<Flight>{

	int number;
	String airlineName;
	String source;
	String destination;
	int capacity;
	Flight next;
	//Passenger link;
	LinkedList <Passenger> s=new LinkedList<Passenger>();


	public Flight() {
		// TODO Auto-generated constructor stub
	}
	public LinkedList<Passenger> getS() {
		return s;
	}
	public void setS(LinkedList<Passenger> s) {
		this.s = s;
	}
	public Flight(int number, String airlineName, String source, String destination, int capacity) {
		super();
		this.number = number;
		this.airlineName = airlineName;
		this.source = source;
		this.destination = destination;
		this.capacity = capacity;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return "Flight number=" + number + ", airlineName=" + airlineName + ", source=" + source + ", destination="
				+ destination + ", capacity=" + capacity ;
	}

	public Flight getNext() {
		return next;
	}
	public void setNext(Flight next) {
		this.next = next;
	}
	public String toString2() {
		return number + ","+ airlineName + "," + source + ","
				+ destination + "," + capacity ;
	}
	@Override
	public int compareTo(Flight o) {
		// TODO Auto-generated method stub
		if(number>o.number) {
			return 1;
		}
		else if(number==o.number)
			return 0;
		else
			return -1;
	}




}



