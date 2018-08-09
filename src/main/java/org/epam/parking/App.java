package org.epam.parking;
import java.io.IOException;
import org.epam.service.Menu;
/**
 * provides facitities to user.
 * @author rajendra
 */
public class App {
    public static void main(String[] args) throws IOException {
    	if(args[2].equals("0")) {
    		
    	} else {
    		Menu menu = new Menu();
    		menu.validateAdmin(args[0], args[1]);
    	}
    }
}
