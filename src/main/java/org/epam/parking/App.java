package org.epam.parking;
import java.io.IOException;
import java.util.Scanner;

import org.epam.admin.Admin;
import org.epam.exceptions.InvalidChoiceException;
import org.epam.exceptions.ParkingSpaceException;
import org.epam.fileio.FileOperation;
import org.epam.service.ParkVehicle;
import org.epam.service.UnParkVehicle;
import org.epam.vehicle.Car;
/**
 * provides facitities to user.
 * @author rajendra
 */
public class App {
    /**
     * displays menu to the admin.
     * admin can park, unpark and exit from operations.
     * @throws InvalidChoiceException occurs when wrong choice is choosed.
     * @throws ParkingSpaceException occurs when spacce is full.
     * @throws IOException occurs when file not found.
     * @throws ClassNotFoundException
     */
    private static void proceedToOperations() throws
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
            switch (choice) {
            case 1:
                ParkVehicle park = new ParkVehicle();
                System.out.println("Enter the car Number:");
                if (parkingSpace.getSlotsRemaining() > 0) {
                    park.parkCar(input.next(), parkingSpace);
                } else {
                    throw new ParkingSpaceException("parking Space is full");
                }
                break;
            case 2:
                UnParkVehicle unpark = new UnParkVehicle();
                System.out.println("Enter the car Number");
                unpark.unParkCar(input.next(), parkingSpace);
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
    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws IOException occurs when file not found.
     */
    public static void main(String[] args) throws IOException {
        boolean isAdminValid = false;
        Admin admin = new Admin();
        try {
        isAdminValid = admin.validateAdmin(args[0]
              , args[1]);
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
}
