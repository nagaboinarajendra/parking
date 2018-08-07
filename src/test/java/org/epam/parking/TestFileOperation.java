package org.epam.parking;

import java.io.IOException;

import org.epam.fileio.FileOperation;
import org.epam.service.ParkVehicle;
import org.epam.vehicle.Car;

import junit.framework.TestCase;

public class TestFileOperation extends TestCase {
public void testWriteToFileOperation() throws IOException {
	ParkingSpace parkingSpace = new ParkingSpace(30);
	Slot.car = new Car[parkingSpace.getTotalSlots() + 1];
	Slot.intime =new InTime[parkingSpace.getTotalSlots() + 1];
	ParkVehicle park = new ParkVehicle();
	park.parkCar("ap29cb1118", parkingSpace);
	FileOperation fo = new FileOperation();
	fo.writeToFile(parkingSpace.queue);
	fo.ReadFromFile(parkingSpace);
}
/*
@Test
public void testUpdate(){
	FileOperation fo = new FileOperation();
     fo.out = Mockito.spy(new PrintStream(...));

     // mock a call with an expected input
     doNothing().when(myClass.out).println("expected command");

     myClass.updateGreen();

     // test that there was a call
     Mockito.verify(myClass.out, Mockito.times(1)).println("expected command");
}
*/
}
