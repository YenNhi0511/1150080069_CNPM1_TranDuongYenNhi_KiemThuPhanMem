package bai1;

public class MathFunc {
	 public long factorial(int n) {
	        if (n < 0) {
	            throw new IllegalArgumentException("Số không được âm");
	        }
	        if (n == 0 || n == 1) {
	            return 1;
	        }
	        long result = 1;
	        for (int i = 2; i <= n; i++) {
	            result *= i;
	        }
	        return result;
	    }
	    
	    public int plus(int a, int b) {
	        return a + b;
	    }
}
