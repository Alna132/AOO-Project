package ie.gmit.sw;

import java.util.*;

/**
 * ListClasses generates a list to which classes can be added. 
 * It also holds the methods for handling the list, add, clear, getIndex, contains, isEmpty, remove and size.
 */

public class ListClasses {
	private List <Class> list  = new  ArrayList<Class> ();

	public boolean add(Class arg0) {
		return list.add(arg0);
	}//- End of add

	public void clear() {
		list.clear();
	}//- End of clear

	public boolean contains(Object arg0) {
		return list.contains(arg0);
	}//- End of contains

	public Class getIndex(int index) {
		return list.get(index);
	}//- End of getTheClass

	public boolean isEmpty() {
		return list.isEmpty();
	}//- End of isEmpty

	public boolean remove(Object arg0) {
		return list.remove(arg0);
	}//- End of remove

	public int size() {
		return list.size();
	}//- End of size	
}//- End of ListClasses
