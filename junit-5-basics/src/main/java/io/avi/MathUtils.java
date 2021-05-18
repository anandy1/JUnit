package io.avi;

public class MathUtils {
	
	public int add(int a , int b) {
		return a + b;
	}
	
	public int subtract(int a , int b) {
		return a - b;
	}
	
	public int multiply(int a , int b) {
		return a * b;
	}
	
	public int divide(int a , int b) {
		return a / b;
	}
	
	public double calculateCircleArea(double radious) {
		return Math.PI * radious * radious;
	}
}
