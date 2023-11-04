/*
 * Author: zhong6688
 */
public class Pel {
	//location object stored in the Pel object and its colour 
	private Location p;
	private int color; 
	//Pel constructor that initializes the object with the coordinates and specified colour
	public Pel(Location p, int color) {
		//p is the key attribute for the Pel object
		this.p=p; 
		this.color=color; 
	}
	//Accessor method- returns the location of the pel object
	public Location getLocus() {
		return p; 
	}
	//Accessor method- returns the colour of the pel object
	public int getColor() {
		return color; 
	}
}
