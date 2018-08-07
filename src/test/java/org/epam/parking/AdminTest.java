package org.epam.parking;

import org.epam.admin.Admin;
import org.epam.exceptions.InvalidAdminException;

import junit.framework.TestCase;

public class AdminTest extends TestCase {
	public void testAdmin() {
		Admin admin = new Admin();
		try {
			assertTrue(admin.validateAdmin("admin", "admin"));
		} catch (InvalidAdminException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			assertFalse(admin.validateAdmin("admin", "adfa"));
		} catch (InvalidAdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
