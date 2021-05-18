package io.avi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathUtilsTest {

	MathUtils mathUtils;
	
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This needs to be run before all");
	}
	
	@BeforeEach
	void init() {
		mathUtils = new MathUtils();
	}
	
	@AfterEach
	void cleanUp() {
		System.out.println("Cleaning up ...");
	}
	@Test
	void test() {
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("This is for display only")
	void testCalculateCircleArea() {
		assertEquals(314.1592653589793, mathUtils.calculateCircleArea(10), "This mathod should calculate the area of a circle");
	}
	
	@Test
	@Disabled
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "Divide should throw exception");
	}
}
