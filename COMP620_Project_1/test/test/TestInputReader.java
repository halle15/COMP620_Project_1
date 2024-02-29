package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import src.InputReader;

class TestInputReader {

	@Test
	void testReadFile() {
		InputReader r = new InputReader();
		
		ArrayList<Integer> l = r.readFile("test_input");
		assertNotNull(l);
		
		assertEquals(l.toString(), "[3, 27, 25, 23, 21, 19, 17, 15, 13, 11, 9, 7, 5, 3, 1, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26]");
		
	}

}
