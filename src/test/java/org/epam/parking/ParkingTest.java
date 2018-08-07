package org.epam.parking;

import org.epam.service.ParkVehicle;
import org.epam.vehicle.Car;

import junit.framework.TestCase;

public class ParkingTest extends TestCase {
	public void testParkCar() {
		ParkingSpace parkingSpace = new ParkingSpace(50);
		Slot.car = new Car[parkingSpace.getTotalSlots() + 1];
		Slot.intime =new InTime[parkingSpace.getTotalSlots() + 1];
		ParkVehicle park = new ParkVehicle();
		assertTrue(park.parkCar("ap29cb1118", parkingSpace));
		assertFalse(park.parkCar("ap29cb111", parkingSpace));
		assertFalse(park.parkCar("apcb118", parkingSpace));
	}
}
