/**
 * @author zhong6688
 * This class examines a map and finds if there is a path for electricity
 * to get from the power company to a customers house. If there is a path(or multiple)
 * it will highlight the shortest path
 */

import java.io.FileNotFoundException;
import java.io.IOException;

public class ShortestPath {
	/**
	 * "cityMap" is a Map object created when a map file is given
	 */
	
	Map cityMap;
	
	/**
	 * 
	 * A ShortestPath object is constructed from the constructor 
	 * @param filename is the name of the desired map file
	 * @throws InvalidMapException for invalid map file
	 * @throws FileNotFoundException for a not found map file
	 * @throws IOException for an invalid input
	 */

	public ShortestPath(String filename) throws InvalidMapException, FileNotFoundException, IOException {
		try {
			this.cityMap = new Map(filename);
		}

		catch (InvalidMapException e) {
			System.out.println("Invalid map selected.");
		} catch (FileNotFoundException e) {
			System.out.println("File that was inputed not found!");
		} catch (IOException e) {
			System.out.println("Invalid input.");
		}
	}

	// Exit the program if no file was inputed
	public static void main(String[] args) throws InvalidMapException, FileNotFoundException, IOException {

		if (args.length < 1) {
			System.out.println("You must provide the name of the input file");
			System.exit(0);
		}

		// Initialize variables and a doubly linked list consisting map cell objects
		ShortestPath shortestPath = new ShortestPath(args[0]);
		DLList<MapCell> pathWay = new DLList<MapCell>();
		int distance = 0;
		int position = 0;

		// Initialize the starting cell as the first cell(power cell) in the map and helper cells
		MapCell initialCell = shortestPath.cityMap.getStart();
		MapCell small;
		MapCell current;
		boolean found = false;
		
		//insert the first cell into the linked list with a value of 0
		pathWay.insert(initialCell, 0);
		initialCell.markInList();

		//while the boolean representing if the customer found is false and the DLL is not empty run through this while loop
		while (found == false && !pathWay.isEmpty()) {

			//get the map object with the smallest data value and mark it out of the list
			small = pathWay.getSmallest();
			small.markOutList();

			//if the map object is the customer break from the loop
			if (small.isCustomer()) {
				found = true;
				break;
			}

			else {
				//while the next cell is not null run through this while loop
				while (shortestPath.nextCell(small) != null) {
					//set the current cell to be the next cell
					current = shortestPath.nextCell(small);
					
					//set the distance
					distance = 1 + small.getDistanceToStart();

					//if the current cells distance is greater than the distance, set its distance to distance
					//and set its predecessor to the small cell
					if (current.getDistanceToStart() > distance) {
						current.setDistanceToStart(distance);
						current.setPredecessor(small);
					}

					//make the variable position equal to the current cells new distance from the start
					position = current.getDistanceToStart();

					//if the current cell is marked in the list and position is less than currents vale
					//change the current node cell's value to position
					if (current.isMarkedInList() && position < pathWay.getDataValue(current)) {
						pathWay.changeValue(current, position);
					}

					//if current has not been marked in the list insert it and mark it
					if (!current.isMarkedInList()) {
						pathWay.insert(current, position);
						current.markInList();
					}
				}
			}
		}
		//prints how many steps were taken to the customer if a path is found
		//if path is not found print that it was not found
		if (found == true) {
			System.out.println("It took " + position + " steps to customer's house!");
		} else
			System.out.println("No path was found. The customer can not be helped!");
	}
	/**
	 * This method determines the possible neighbouring cells of a current cell to get the shortest path (if there is a path)
	 * @param cell the current map cell that determines which neighbours the cell can move to
	 * @return a neighbouring cell that must be added to the list
	 */
	private MapCell nextCell(MapCell cell) {

		for (int i = 0; i < 4; i++) {
			try {
				
				//make sure that the neighbour of the cell is not null and has not been marked
				if (cell.getNeighbour(i) != null && !cell.getNeighbour(i).isMarked()) {

					//if the cell is an omni or power switch set restrictions to what it's neighbouring cells can be
					if (cell.isOmniSwitch() || cell.isPowerStation()) {

						if (cell.getNeighbour(i).isCustomer()) {
							return cell.getNeighbour(i);
						} else if (cell.getNeighbour(i).isOmniSwitch()) {
							return cell.getNeighbour(i);
						} else if (cell.getNeighbour(i).isVerticalSwitch() && (i == 0 || i == 2)) {
							return cell.getNeighbour(i);
						} else if (cell.getNeighbour(i).isHorizontalSwitch() && (i == 1 || i == 3)) {
							return cell.getNeighbour(i);
						}
					}

					//if the cell is a vertical switch set restrictions to what it's neighbouring cells can be
					if (cell.isVerticalSwitch() && (i == 0 || i == 2)) {

						if (cell.getNeighbour(i).isCustomer()) {
							return cell.getNeighbour(i);
						} else if (cell.getNeighbour(i).isOmniSwitch()) {
							return cell.getNeighbour(i);
						} else if (cell.getNeighbour(i).isVerticalSwitch()) {
							return cell.getNeighbour(i);
						}
					}

					//if the cell is a horizontal switch set restrictions to what it's neighbouring cells can be
					if (cell.isHorizontalSwitch() && (i == 1 || i == 3)) {

						if (cell.getNeighbour(i).isCustomer()) {
							return cell.getNeighbour(i);
						} else if (cell.getNeighbour(i).isOmniSwitch()) {
							return cell.getNeighbour(i);
						} else if (cell.getNeighbour(i).isHorizontalSwitch()) {
							return cell.getNeighbour(i);
						}
					}
				}
			}

			catch (NullPointerException e) {
				System.out.println("");
			}
		}
		//if a neighbour is not found
		return null;
	}
}