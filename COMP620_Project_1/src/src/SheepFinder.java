package src;

import java.util.ArrayList;
import java.util.Collections;

public class SheepFinder {

	int dim;
	ArrayList<Integer> input;

	/**
	 * My reasoning for defining all these variables
	 * 
	 * Java does not allow for a dynamic way to assign an n dimensional array. Since
	 * i know that it can only be dims 1-4, and there's only 1 slot on the first
	 * dim, I just use one of these at runtime.
	 */
	ArrayList<ArrayList<Integer>> Array2D;
	ArrayList<ArrayList<ArrayList<Integer>>> Array3D;
	ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> Array4D;

	public SheepFinder(ArrayList<Integer> input) {

		this.dim = input.get(0);

		if (this.dim < 0 || this.dim > 4) {
			throw new IllegalArgumentException(
					"The initial value of the input" + " file can not be less than 0 or more than 4!");
		}

		this.input = new ArrayList<Integer>(input.subList(1, input.size()));

		if (this.input.size() != Math.pow(this.dim, this.dim)) {
			throw new IllegalArgumentException("The input array must be size dim ^ dim!");
		}

		Collections.sort(this.input); // ensure in ascending order

		// Initialize based on dimension
		if (this.dim == 2) {
			Array2D = new ArrayList<>();
			fill2D();
		} else if (this.dim == 3) {
			Array3D = new ArrayList<>();
			fill3D();
		} else if (this.dim == 4) {
			Array4D = new ArrayList<>();
			fill4D();
		}
	}

	private void fill2D() {
		int index = 0;
		for (int i = 0; i < dim; i++) { // Columns
			ArrayList<Integer> row = new ArrayList<>();
			for (int j = 0; j < dim; j++) { // Rows
				row.add(input.get(index++));
			}
			Array2D.add(row);
		}
	}

	private void fill3D() {
		int index = 0;
		for (int k = 0; k < dim; k++) { // Layers
			ArrayList<ArrayList<Integer>> layer = new ArrayList<>();
			for (int i = 0; i < dim; i++) { // Columns
				ArrayList<Integer> row = new ArrayList<>();
				for (int j = 0; j < dim; j++) { // Rows
					row.add(input.get(index++));
				}
				layer.add(row);
			}
			Array3D.add(layer);
		}

	}

	private void fill4D() {
		int index = 0;
		for (int l = 0; l < dim; l++) { // Blocks
			ArrayList<ArrayList<ArrayList<Integer>>> block = new ArrayList<>();
			for (int k = 0; k < dim; k++) { // Layers
				ArrayList<ArrayList<Integer>> layer = new ArrayList<>();
				for (int i = 0; i < dim; i++) { // Columns
					ArrayList<Integer> row = new ArrayList<>();
					for (int j = 0; j < dim; j++) { // Rows
						row.add(input.get(index++));
					}
					layer.add(row);
				}
				block.add(layer);
			}
			Array4D.add(block);
		}
	}

	public String findSheep(int sheep) {

		String returnString = "";

		int left = 0;
		int right;

		switch (dim) {
		case 1:
			if (input.get(0) == sheep) {
				return ("[0]"); // the only spot it can be.
			} else {
				return ("NF"); // the input was not found, or NF
			}
		case 2:
			left = 0;
			right = 3; // we know there is always max 4 in this instance

			while (left <= right) {

				int midpoint = left + (right - left) / 2;
				int midpointValue = getValueFrom2D(midpoint);

				if (sheep == midpointValue) {
					return getCoordinatesFrom2DIndex(midpoint);
				}

				if (sheep < midpointValue) {
					right = midpoint - 1;
				}
				if (sheep > midpointValue) {
					left = midpoint + 1;
				}
			}
			return "NF";
		case 3:
			left = 0;
			right = 26; // we know this is the maximum of a 3x3x3

			while (left <= right) {

				int midpoint = left + (right - left) / 2;
				int midpointValue = getValueFrom3D(midpoint);

				if (sheep == midpointValue) {
					return getCoordinatesFrom3DIndex(midpoint);
				}

				if (sheep < midpointValue) {
					right = midpoint - 1;
				}
				if (sheep > midpointValue) {
					left = midpoint + 1;
				}
			}
			return "NF";
		case 4:
			left = 0;
			right = (int) Math.pow(4, 4); // we know this is the maximum of a 4x4x4x4

			while (left <= right) {

				int midpoint = left + (right - left) / 2;
				int midpointValue = getValueFrom4D(midpoint);

				if (sheep == midpointValue) {
					return getCoordinatesFrom4DIndex(midpoint);
				}

				if (sheep < midpointValue) {
					right = midpoint - 1;
				}
				if (sheep > midpointValue) {
					left = midpoint + 1;
				}
			}
			return "NF";
		default:
			throw new IllegalArgumentException("Incorrect Dimension!");
		}
	}

	/**
	 * Provides a constant time method for accessing an index in a 2d array.
	 * 
	 * Allows us to break this apart sequentially and treat an n dimensional array
	 * as 1d.
	 * 
	 * @param index The index to search for in the 2d array.
	 * @return Returns the integer value in this slot.
	 */
	public Integer getValueFrom2D(int index) {
		int size = 2; // For a 2x2 array
		int row = index / size;
		int col = index % size;
		return Array2D.get(row).get(col);
	}

	public Integer getValueFrom3D(int index) {
		int size = 3; // For a 3x3x3 array
		int layer = index / (size * size);
		int row = (index % (size * size)) / size;
		int col = index % size;
		return Array3D.get(layer).get(row).get(col);
	}

	public Integer getValueFrom4D(int index) {
		int size = 4; // For a 4x4x4x4 array
		int block = index / (size * size * size);
		int layer = (index % (size * size * size)) / (size * size);
		int row = (index % (size * size)) / size;
		int col = index % size;
		return Array4D.get(block).get(layer).get(row).get(col);
	}

	public String getCoordinatesFrom2DIndex(int index) {
		int size = 2; // For a 2x2 array
		int col = index / size;
		int row = index % size;
		return "[" + row + "][" + col + "]";
	}

	public String getCoordinatesFrom3DIndex(int index) {
		int size = 3; // For a 3x3x3 array
		int layer = index / (size * size);
		int row = (index % (size * size)) / size;
		int col = index % size;
		return "[" + col + "][" + row + "][" + layer + "]";
	}

	public String getCoordinatesFrom4DIndex(int index) {
		int size = 4; // For a 4x4x4x4 array
		int block = index / (size * size * size);
		int layer = (index % (size * size * size)) / (size * size);
		int row = (index % (size * size)) / size;
		int col = index % size;
		return "[" + col + "][" + row + "][" + layer + "][" + block + "]";
	}

}
