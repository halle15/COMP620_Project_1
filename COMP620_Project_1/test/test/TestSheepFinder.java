package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import src.InputReader;
import src.SheepFinder;

class TestSheepFinder {

	@Test
	void TestWrongDimensions() {
		InputReader r = new InputReader();

		assertThrows(IllegalArgumentException.class, () -> {
			SheepFinder s = new SheepFinder(r.readFile("test_input_over_dim4"));
		});

		assertThrows(IllegalArgumentException.class, () -> {
			SheepFinder s = new SheepFinder(r.readFile("test_input_under_dim0"));
		});
	}

	@Test
	void testWrongSizes() {
		// TODO: Test wrong size for 1, 2x2, 3x3x3, 4x4x4x4

		assertThrows(IllegalArgumentException.class, () -> {
			SheepFinder s = new SheepFinder(new ArrayList<>(Arrays.asList(1, 10, 50)));
		});

		assertThrows(IllegalArgumentException.class, () -> {
			SheepFinder s = new SheepFinder(new ArrayList<>(Arrays.asList(2, 10)));
		});

		assertThrows(IllegalArgumentException.class, () -> {
			SheepFinder s = new SheepFinder(new ArrayList<>(Arrays.asList(3, 10)));
		});

		assertThrows(IllegalArgumentException.class, () -> {
			SheepFinder s = new SheepFinder(new ArrayList<>(Arrays.asList(4, 10)));
		});
	}

	@Test
	void testRightSizes() {
		// 1D
		assertNotNull(new SheepFinder(new ArrayList<>(Arrays.asList(1, 5))));
		// 2D
		assertNotNull(new SheepFinder(new ArrayList<>(Arrays.asList(2, 1, 2, 3, 4))));
		// 3D
		assertNotNull(new SheepFinder(new ArrayList<>(Arrays.asList(3, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
				15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27))));

		ArrayList<Integer> l = new ArrayList<Integer>();

		for (int i = 0; i < Math.pow(4, 4); i++) {
			l.add(i);
		}

		assertNotNull(l);
	}

	@Test
	void TestGetValueFrom2D() {
		InputReader reader = new InputReader();

		ArrayList<Integer> input = reader.readFile("test_input_2d");

		assertNotNull(input);

		SheepFinder f = new SheepFinder(input);

		assertEquals(f.getValueFrom2D(1), 12); // should correspond to [1][0]
	}

	@Test
	void TestFindSheep1D() {
		InputReader reader = new InputReader();

		ArrayList<Integer> input = reader.readFile("test_input_1d");

		assertNotNull(input);

		SheepFinder f = new SheepFinder(input);

		assertEquals(f.findSheep(27), "[0]");

		assertEquals(f.findSheep(55), "NF"); // Search for nonexistent value
	}

	@Test
	void TestFindSheep2D() {
		InputReader reader = new InputReader();

		ArrayList<Integer> input = reader.readFile("test_input_2d");

		assertNotNull(input);

		SheepFinder f = new SheepFinder(input);

		assertEquals(f.findSheep(111), "[1][1]"); // the highest should be at the end
		assertEquals(f.findSheep(7), "[0][0]"); // lowest value, so should be at start

		assertEquals(f.findSheep(12), "[1][0]"); // expected corresponding location
		assertEquals(f.findSheep(18), "[0][1]"); // expected corresponding location

		assertEquals(f.findSheep(6), "NF"); // this does not exist, we should return an invalid result.
	}

	@Test
	void TestFindSheep3D() {
		InputReader reader = new InputReader();

		ArrayList<Integer> input = reader.readFile("test_input_3d");

		assertNotNull(input);

		SheepFinder f = new SheepFinder(input);

		int counter = 1;

		for (int layer = 0; layer < 3; layer++) { // start with each layer
			for (int column = 0; column < 3; column++) { // then iterate by column
				for (int row = 0; row < 3; row++) { // then by row
					assertEquals(f.findSheep(counter), "[" + row + "][" + column + "][" + layer + "]");
					counter += 1;
				}
			}
		}
	}

	@Test
	void TestGetCoordinatesFrom3DIndex() {
		InputReader reader = new InputReader();

		ArrayList<Integer> input = reader.readFile("test_input_3d");

		assertNotNull(input);

		SheepFinder f = new SheepFinder(input);

		assertEquals(f.getCoordinatesFrom3DIndex(3), "[0][1][0]");

		assertEquals(f.getCoordinatesFrom3DIndex(9), "[0][0][1]");

		assertEquals(f.getCoordinatesFrom3DIndex(26), "[2][2][2]");
	}
	
	@Test
	void TestGetCoordinatesFrom4DIndex() {
		InputReader reader = new InputReader();

		ArrayList<Integer> input = reader.readFile("test_input_4d_in_order");

		assertNotNull(input);

		SheepFinder f = new SheepFinder(input);
		
		assertEquals(f.getCoordinatesFrom4DIndex(0), "[0][0][0][0]");
		
		assertEquals(f.getCoordinatesFrom4DIndex(4), "[0][1][0][0]");

	}

	@Test
	void TestFindSheep4D() {
		InputReader reader = new InputReader();

		ArrayList<Integer> input = reader.readFile("test_input_4d_in_order");

		assertNotNull(input);

		SheepFinder f = new SheepFinder(input);

		int counter = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					for (int l = 0; l < 4; l++) {
						assertEquals(f.findSheep(counter+1), "[" + l + "][" + k + "][" + j + "][" + i + "]");
						counter++;
					}
				}
			}
		}
	}
	
	@Test
	void TestFindSheep4DRandom() {
		InputReader reader = new InputReader();

		ArrayList<Integer> input = reader.readFile("test_input_4d");

		assertNotNull(input);

		SheepFinder f = new SheepFinder(input);

		int counter = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					for (int l = 0; l < 4; l++) {
						assertEquals(f.findSheep(counter), "[" + l + "][" + k + "][" + j + "][" + i + "]");
						counter++;
					}
				}
			}
		}
		
	}

}
