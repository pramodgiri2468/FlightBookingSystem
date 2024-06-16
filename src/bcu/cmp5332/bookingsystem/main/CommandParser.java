package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.LoadGUI;
import bcu.cmp5332.bookingsystem.commands.UpdateBooking;
import bcu.cmp5332.bookingsystem.commands.ListFlights;
import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.Help;
import bcu.cmp5332.bookingsystem.commands.IssueBooking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CommandParser {
    
    public static Command parse(String line) throws IOException, FlightBookingSystemException {
        try {
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];

            
            if (cmd.equals("addflight")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Flight Number: ");
                String flighNumber = reader.readLine();
                System.out.print("Origin: ");
                String origin = reader.readLine();
                System.out.print("Destination: ");
                String destination = reader.readLine();
                System.out.print("Number of Seats: ");
                String noOfSeats = reader.readLine();
                System.out.println("Price");
                Double price = Double.parseDouble(reader.readLine());
                System.out.println("Maximum Number of Passengers: ");
                int maxPassengers = Integer.parseInt(reader.readLine());

                LocalDate departureDate = parseDateWithAttempts(reader);

                return new AddFlight(flighNumber, origin, destination, departureDate, noOfSeats, price, maxPassengers);
            } else if (cmd.equals("addcustomer")) {
            	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            	System.out.print("Name: ");
            	String name = reader.readLine();
            	System.out.print("Phone: ");
            	String phone = reader.readLine();
            	System.out.print("Email: ");
            	String email = reader.readLine();
            	
            	return new AddCustomer(name, phone, email);
                
            } else if (cmd.equals("loadgui")) {
                return new LoadGUI();
            } else if (parts.length == 1) {
                if (line.equals("listflights")) {
                    return new ListFlights();
                } else if (line.equals("listcustomers")) {
                    
                } else if (line.equals("help")) {
                    return new Help();
                }
            } else if (parts.length == 2) {
                int id = Integer.parseInt(parts[1]);

                if (cmd.equals("showflight")) {
                    
                } else if (cmd.equals("showcustomer")) {
                    
                }
            } else if (parts.length == 3) {
            	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                int customerId = Integer.parseInt(parts[1]);
                int flightId = Integer.parseInt(parts[2]);

                if (cmd.equals("addbooking")) {
                	LocalDate bookingDate = parseDateWithAttempts(reader);
                	return new IssueBooking(customerId, flightId, bookingDate );
                	
                    
                } else if (cmd.equals("editbooking")) {
                	System.out.println("Enter the booking date: ");
                	LocalDate bookingDate = parseDateWithAttempts(reader);
                	System.out.println("Enter the new date: ");
                	LocalDate newDate = parseDateWithAttempts(reader);
                	return new UpdateBooking(customerId, flightId, bookingDate, newDate );
                	
                    
                } else if (cmd.equals("cancelbooking")) {
                	LocalDate bookingDate = parseDateWithAttempts(reader, 3);
                	return new CancelBooking(customerId, flightId, bookingDate);
                    
                }
            }
        } catch (NumberFormatException ex) {

        }

        throw new FlightBookingSystemException("Invalid command.");
    }
    
    private static LocalDate parseDateWithAttempts(BufferedReader br, int attempts) throws IOException, FlightBookingSystemException {
        if (attempts < 1) {
            throw new IllegalArgumentException("Number of attempts should be higher that 0");
        }
        while (attempts > 0) {
            attempts--;
            System.out.print("Departure Date (\"YYYY-MM-DD\" format): ");
            try {
                LocalDate departureDate = LocalDate.parse(br.readLine());
                return departureDate;
            } catch (DateTimeParseException dtpe) {
                System.out.println("Date must be in YYYY-MM-DD format. " + attempts + " attempts remaining...");
            }
        }
        
        throw new FlightBookingSystemException("Incorrect departure date provided. Cannot create flight.");
    }
    
    private static LocalDate parseDateWithAttempts(BufferedReader br) throws IOException, FlightBookingSystemException {
        return parseDateWithAttempts(br, 3);
    }
}
