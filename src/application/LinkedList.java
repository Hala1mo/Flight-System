package application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LinkedList<T extends Comparable<T>> {

	Node<T> head;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM/dd ");

	public LinkedList() {

	}

	public String traverse() { // Print the linked list
		Node<T> current = head;
		String x = "";
		while (current != null) {
			x = x + current.getData().toString() + "\n";
			current = current.getNext();
		}
		return x;

	}

	public boolean search(int x) {

		Node<T> current = head;
		while (current != null) {
			if (((Flight) current.getData()).getNumber() == (x)) {
				return true;
			} else
				current = current.getNext();

		}

		return false;

	}

	public void insertion(T x) {
		Node<T> newNode = new Node<T>(x);
		Node<T> previous = null, current = head;
		for (; current != null && current.getData().compareTo(x) < 0; previous = current, current = current.getNext())
			;
		if (head == null) {
			head = newNode;
		}

		else if (current == head) {
			newNode.setNext(head);
			head = newNode;
		} else if (current == null) {
			previous.setNext(newNode);
		} else {
			newNode.setNext(current);
			previous.setNext(newNode);

		}
	}

	public String PrintPassinfo(int Fnum) { // Print passengers information for a specific flight
		String x = "No Passengers in this Flight";
		Node<T> current = head;

		while (current != null) {
			if ((((Flight) current.getData()).getNumber()) == (Fnum)) {
				if (((Flight) current.getData()).getS().traverse().equals("")) {
					return x;
				} else {
					return ((Flight) current.getData()).getS().traverse();
				}
			} else
				current = current.getNext();
		}

		return "";

	}

	public boolean CheckPass(String Name) { // Search for a specific passenger in all the flights,by giving his/her name

		Node<T> current = head;
		while (current != null) {
			if (((Flight) current.getData()).getS().searchPass(Name) == true) {
				return true;
			} else
				current = current.getNext();

		}
		return false;
	}

	public boolean CheckPass(String Name, int Fnum) { // Search for a specific passenger in a specific flight,by giving
														// his/her name

		Node<T> current = head;
		while (current != null) {
			if (((Flight) current.getData()).getNumber() == Fnum) {
				if (((Flight) current.getData()).getS().searchPass(Name) == true) {
					return true;
				}
			} else
				current = current.getNext();

		}
		return false;
	}

	public boolean delete(T x) {

		Node<T> current = head;
		if (head.getData() == x) {
			head = head.getNext();
			return true;
		}
		Node<T> previous = head;
		current = head.getNext();
		while (current != null) {
			if (current.getData() == x) {
				previous.setNext(current.getNext());
				return true;
			}
			previous = current;
			current = current.getNext();

		}
		return false;

	}

	public String returnDes(int x) { // return the destination for a specific flight by giving the flight number

		Node<T> current = head;
		while (current != null) {
			if ((((Flight) current.getData()).getNumber()) == (x)) {
				return (((Flight) current.getData()).getDestination());
			} else
				current = current.getNext();

		}

		return null;

	}

	public String returnSource(int x) { // return the source for a specific flight by giving the flight number

		Node<T> current = head;
		while (current != null) {
			if ((((Flight) current.getData()).getNumber()) == (x)) {
				return (((Flight) current.getData()).getSource());
			} else
				current = current.getNext();

		}

		return null;

	}

	public int returnCapacity(int x) { // return the Capacity for a specific flight by giving the flight number

		Node<T> current = head;
		while (current != null) {
			if ((((Flight) current.getData()).getNumber()) == (x)) {
				return ((Flight) current.getData()).getCapacity();
			} else
				current = current.getNext();

		}

		return 0;

	}

	public String returnName(int x) { // return the Airline Name for a specific flight by giving the flight number

		Node<T> current = head;
		while (current != null) {
			if ((((Flight) current.getData()).getNumber()) == (x)) {
				return ((Flight) current.getData()).getAirlineName();
			} else
				current = current.getNext();

		}

		return null;

	}

	public boolean checkCapacity(int x) { // check weather a flight can have another passenger by checking the number of
											// passengers is less than the number of capacity for the flight
		Node<T> current = head;
		while (current != null) {
			if ((((Flight) current.getData()).getNumber()) == (x)) {
				if ((((Flight) current.getData()).getS().count() < (((Flight) current.getData()).getCapacity()))) {
					return true; // there's a space for another passenger
				} else
					return false; // the flight is full of passengers
			} else
				current = current.getNext();

		}
		return false;
	}

	public boolean searchPass(String x) { // check if a passenger in the flight,by giving his/her name

		Node<T> current = head;
		while (current != null) {
			if (((Passenger) current.getData()).getFullName().compareToIgnoreCase(x) == 0) {
				return true;
			} else
				current = current.getNext();

		}

		return false;

	}

	public String returnName(String x) { // return the name of the passenger
		Node<T> current = head;
		while (current != null) {
			if (((Flight) current.getData()).getS().searchPass(x) == true) {
				return ((Flight) current.getData()).getS().returnPassenger(x).getFullName();

			} else
				current = current.getNext();

		}
		return null;

	}

	public String returnPassNum(String x) { // check if a passenger having this name(X) is on a flight,then return
											// his/her passport number
		Node<T> current = head;
		while (current != null) {
			if (((Flight) current.getData()).getS().searchPass(x) == true) {
				return ((Flight) current.getData()).getS().returnPassenger(x).getPassportNumber();

			} else
				current = current.getNext();

		}
		return null;

	}

	public Passenger returnPassenger(String x) { // return the passenger having this name

		Node<T> current = head;
		while (current != null) {
			if (((Passenger) current.getData()).getFullName().compareToIgnoreCase(x) == 0) {
				return (Passenger) current.getData();
			} else
				current = current.getNext();

		}
		return null;

	}

	public void UpdateFlightNum(int x) { // when editing the flight number for specific flight,we will update the flight
											// number for the passengers on this flight

		Node<T> current = head;
		if (current != null) {
			while (current != null) {
				((Passenger) current.getData()).setFlightNumber(x);
				current = current.getNext();
			}

		} else
			return;
	}

	public int returnTicket(String x) { // check if a passenger having this name(x) is on a flight,then return his/her
										// Ticket number
		Node<T> current = head;
		while (current != null) {
			if (((Flight) current.getData()).getS().searchPass(x) == true) {
				return ((Flight) current.getData()).getS().returnPassenger(x).getTicketNumber();

			} else
				current = current.getNext();

		}

		return 0;

	}

	public String returnNationaltiy(String x) { // check if a passenger having this name(x) is on a flight,then return
												// his/her Nationality

		Node<T> current = head;
		while (current != null) {
			if (((Flight) current.getData()).getS().searchPass(x) == true) {
				return ((Flight) current.getData()).getS().returnPassenger(x).getNationality();

			} else
				current = current.getNext();

		}

		return null;

	}

	public int returnFlightNum(String x) { // check if a passenger having this name(x) is on a flight,then return
											// his/her flight number

		Node<T> current = head;
		while (current != null) {
			if (((Flight) current.getData()).getS().searchPass(x) == true) {
				return ((Flight) current.getData()).getS().returnPassenger(x).getFlightNumber();

			} else
				current = current.getNext();

		}

		return 0;

	}

	public String returnBirth(String x) { // check if a passenger having this name(x) is on a flight,then return his/her
											// BirthDate

		Node<T> current = head;
		while (current != null) {
			if (((Flight) current.getData()).getS().searchPass(x) == true) {
				Date d = ((Flight) current.getData()).getS().returnPassenger(x).getBirthDate().getTime();
				return sdf.format(d.getTime());

			} else
				current = current.getNext();

		}

		return null;

	}

	public int count() { // the length of the linked list

		int x = 0;
		Node<T> current = head;
		if (head == null) {
			return 0;
		}
		while (current != null) {
			x = x + 1;
			current = current.getNext();

		}
		return x;
	}

	public int returnMaximumTicket() { // return the highest ticket on the flight

		Node<T> current = head;
		int max = 0;
		if (head == null) {
			return 1;
		} else {
			max = ((Passenger) current.getData()).getTicketNumber();
			while (current != null) {
				if (max < ((Passenger) current.getData()).getTicketNumber()) {
					max = ((Passenger) current.getData()).getTicketNumber();
				}

				current = current.getNext();
			}
		}

		return max + 1;

	}

}
