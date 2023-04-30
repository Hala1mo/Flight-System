package application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Passenger implements Comparable<Passenger> {
	int FlightNumber;
	int TicketNumber;
	String FullName;
	String PassportNumber;
	String Nationality;
	Calendar BirthDate = new GregorianCalendar();
	Passenger Next;
	Passenger head;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM/dd ");

	public Passenger() {
		// TODO Auto-generated constructor stub
	}

	public Passenger(int flightNumber, int ticketNumber, String fullName, String passportNumber, String nationality,
			Calendar birthDate) {
		super();
		FlightNumber = flightNumber;
		TicketNumber = ticketNumber;
		FullName = fullName;
		PassportNumber = passportNumber;
		Nationality = nationality;
		BirthDate = birthDate;
	}

	public int getFlightNumber() {
		return FlightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		FlightNumber = flightNumber;
	}

	public int getTicketNumber() {
		return TicketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		TicketNumber = ticketNumber;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getPassportNumber() {
		return PassportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		PassportNumber = passportNumber;
	}

	public String getNationality() {
		return Nationality;
	}

	public void setNationality(String nationality) {
		Nationality = nationality;
	}

	public Calendar getBirthDate() {

		return BirthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		BirthDate = birthDate;
	}

	public Passenger getNext() {
		return Next;
	}

	public void setNext(Passenger next) {
		Next = next;
	}

	@Override
	public String toString() {
		return "FlightNumber=" + FlightNumber + ", TicketNumber=" + TicketNumber + ", FullName=" + FullName
				+ ", PassportNumber=" + PassportNumber + ", Nationality=" + Nationality + ", BirthDate="
				+ sdf.format(BirthDate.getTime()) + "";
	}

	public String toString2() {
		int day = BirthDate.get(Calendar.DAY_OF_MONTH);
		int month = BirthDate.get(Calendar.MONTH);
		int year = BirthDate.get(Calendar.YEAR);
		if (month == 0) {
			month = 12;
		}
		if (day < 10) {
			String d = "0" + day;
			return FlightNumber + "," + TicketNumber + "," + FullName + "," + PassportNumber + "," + Nationality + ","
					+ d + "/" + month + "/" + year;
		} else {
			return FlightNumber + "," + TicketNumber + "," + FullName + "," + PassportNumber + "," + Nationality + ","
					+ day + "/" + month + "/" + year;
		}
	}

	@Override
	public int compareTo(Passenger o) {
		return FullName.compareTo(o.FullName);
	}

}
