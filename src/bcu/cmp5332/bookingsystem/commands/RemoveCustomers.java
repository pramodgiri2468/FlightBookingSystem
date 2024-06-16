package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * @author Pramod Giri and Roshan Shrestha.
 */

public class RemoveCustomers implements Command {
	
	private final int customerId;
	
	public RemoveCustomers(int customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		try{
			if(flightBookingSystem.getCustomerByID(customerId) != null) {
				Customer customer = flightBookingSystem.getCustomerByID(customerId);
				if(!customer.getBookings().isEmpty()) {

					System.out.println("This customer currently has bookings so can not be removed");

				}else {
					customer.setRemoved(true);
					System.out.println("The customer #" + customer.getName() +" has been removed");
				}

			}else {
				throw new FlightBookingSystemException("The customer does not exist in the system");
			}

		}catch (FlightBookingSystemException e){
			System.out.println(e.getMessage());
		}

	}

}
