package bcu.cmp5332.bookingsystem.commands;
import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;


public class CancelBooking implements Command {
	private final int customerId;
	private final int flightId;
	private final LocalDate bookingDate;
	
	public CancelBooking(int customerId, int flightId, LocalDate bookingDate ) {
		this.customerId = customerId;
		this.flightId = flightId;
		this.bookingDate = bookingDate;
	}
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		if(flightBookingSystem.getFlightByID(flightId) != null) {
			Flight flight = flightBookingSystem.getFlightByID(flightId);

			if(flightBookingSystem.getCustomerByID(customerId) != null) {
				Customer customer = flightBookingSystem.getCustomerByID(customerId);
				if(flight.getPassengers().contains(customer)) {
					customer.removeBooking(flight,bookingDate);
				}
			}
		}
	}

}
