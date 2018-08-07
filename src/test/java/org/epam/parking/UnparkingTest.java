package org.epam.parking;


import org.epam.exceptions.CarNumberInvalidException;
import org.epam.service.ParkVehicle;
import org.epam.service.UnParkVehicle;
import org.epam.vehicle.Car;

import junit.framework.TestCase;

public class UnparkingTest extends TestCase {
	
	public void testParkCar() {
		ParkingSpace parkingSpace = new ParkingSpace(50);
		Slot.car = new Car[parkingSpace.getTotalSlots() + 1];
		Slot.intime =new InTime[parkingSpace.getTotalSlots() + 1];
		ParkVehicle park = new ParkVehicle();
		assertTrue(park.parkCar("ap29cb1118", parkingSpace));
		assertTrue(park.parkCar("ap29cb1112", parkingSpace));
		assertTrue(park.parkCar("ap29cb1118", parkingSpace));
		UnParkVehicle unpark = new UnParkVehicle();
		assertTrue(unpark.unParkCar("ap29cb11118", parkingSpace));
	}
	public void carAvailabilityTest() throws CarNumberInvalidException {
		ParkingSpace parkingSpace = new ParkingSpace(50);
		Slot.car = new Car[parkingSpace.getTotalSlots() + 1];
		Slot.intime = new InTime[parkingSpace.getTotalSlots() + 1];
		ParkVehicle park = new ParkVehicle();
		park.parkCar("ap29cb1118", parkingSpace);
		UnParkVehicle unpark = new UnParkVehicle();
		unpark.isCarNumberValid("ap29cb1118");
	}
}