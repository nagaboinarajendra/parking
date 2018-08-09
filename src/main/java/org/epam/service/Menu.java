package org.epam.service;
import java.io.IOException;
import java.util.Scanner;
import org.epam.admin.Admin;
import org.epam.exceptions.InvalidChoiceException;
import org.epam.exceptions.ParkingSpaceException;
import org.epam.fileio.FileOperation;
import org.epam.parking.InTime;
import org.epam.parking.ParkingSpace;
import org.epam.parking.Slot;
import org.epam.vehicle.Car;
import org.epam.service.ParkingOperaions;

public class Menu {
	public void validateAdmin(String username, String password) throws IOException {
		boolean isAdminValid = false;
		Admin admin = new Admin();
		try {
		isAdminValid = admin.validateAdmin(username,password);
		} catch (Exception message) {
		    message.printStackTrace();
		}
		if (isAdminValid) {
		    try {
		    proceedToOperations();
		    } catch (InvalidChoiceException | ParkingSpaceException message) {
		        message.printStackTrace();
		    }
		}
	}	
	/**
	 * displays menu to the admin.
	 * admin can park, unpark and exit from operations.
	 * @throws InvalidChoiceException occurs when wrong choice is choosed.
	 * @throws ParkingSpaceException occurs when spacce is full.
	 * @throws IOException occurs when file not found.
	 * @throws ClassNotFoundException
	 */
	 private void proceedToOperations() throws
	    InvalidChoiceException, ParkingSpaceException, IOException {
	    /**
	     * choice of menu.
	     */
	    int choice;
	    /**
	     * continue to display menu.
	     */
	    Scanner input = new Scanner(System.in);
	    boolean willingToContinue = true;
	    System.out.println("Enter the total number of Parking Slots");
	    ParkingSpace parkingSpace =
	            new ParkingSpace(input.nextInt());
	    /**
	     * load cars from transaction file.
	     */
	    intitializeSlots(parkingSpace);
	    while (willingToContinue) {
	        System.out.print("Select an option from the menu:\n1.Park Vehicle"
	                + "\n2.Unpark Vehicle\n3.Display parking Space\n4.Exit");
	        choice = input.nextInt();
	        ParkingOperaions operaion = new ParkingOperaions();
	        switch (choice) {
	        case 1:
	            System.out.println("Enter the car Number:");
	            if (parkingSpace.getSlotsRemaining() > 0) {
	                operaion.parkCar(input.next(), parkingSpace);
	            } else {
	                throw new ParkingSpaceException("parking Space is full");
	            }
	            break;
	        case 2:
	            System.out.println("Enter the car Number");
	            operaion.unParkCar(input.next(), parkingSpace);
	            break;
	        case 3:
	            parkingSpace.displayParkingSpace(parkingSpace);
	            break;
	        case 4:
	            willingToContinue = false;
	            FileOperation write = new FileOperation();
	            write.writeToFile(parkingSpace.queue);
	            System.out.println("Thankyou for visiting!!");
	            break;
	        default: throw new InvalidChoiceException("invalid choice");
	        }
	    }
	    input.close();
	}
	/**
	 * @param parkingSpace of parking area.
	 * @throws IOException occurs when file not found.
	 */
	private static void intitializeSlots(ParkingSpace parkingSpace) throws
	IOException {
	    Slot.car = new Car[parkingSpace.getTotalSlots() + 1];
	    Slot.intime = new InTime[parkingSpace.getTotalSlots() + 1];
	    FileOperation fo = new FileOperation();
	    fo.ReadFromFile(parkingSpace);
	}
}
