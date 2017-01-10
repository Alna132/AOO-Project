package ie.gmit.sw;

import java.io.*;

/**
 * An interface to be used with ListClasses and InputJARFile. 
 * It declares the initialise variable.
 */

public interface Inputable {
	public ListClasses initialise(String jarName) throws ClassNotFoundException, IOException;
}//- End of Inputable
