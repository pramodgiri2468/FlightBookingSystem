package bcu.cmp5332.bookingsystem.commands;

import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ListCustomers implements Command {
	
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException{
		List<Customer> customers = flightBookingSystem.getCustomers();
		for (Customer customer : customers) {
			if(!customer.isRemoved()) {
				System.out.println(customer.getId());
				System.out.println(customer.getName());
				System.out.println(customer.getPhone());
				System.out.println(customer.getEmail());
			}
		}

		System.out.println(customers.size() + " customer(s)");
	}

}
