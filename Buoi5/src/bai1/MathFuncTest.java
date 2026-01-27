package bai1;

import org.junit.*;
import static org.junit.Assert.*;

public class MathFuncTest {
    
    private MathFunc mathFunc;
    private int calls;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("=== Bắt đầu kiểm thử MathFunc ===");
    }
    
    @Before
    public void setUp() {
        mathFunc = new MathFunc();
        calls = 0;
        System.out.println("Khởi tạo test fixture");
    }
    
    @After
    public void tearDown() {
        mathFunc = null;
        System.out.println("Dọn dẹp test fixture\n");
    }
    
    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("=== Kết thúc kiểm thử MathFunc ===");
    }
    
    @Test
    public void testCalls() {
        System.out.println("Test: Kiểm tra biến calls");
        assertEquals("Biến calls phải bằng 0", 0, calls);
        calls++;
        assertEquals("Sau khi tăng, calls phải bằng 1", 1, calls);
    }
    
    @Test
    public void testFactorial() {
        System.out.println("Test: Kiểm tra phương thức factorial");
        assertEquals("0! = 1", 1, mathFunc.factorial(0));
        assertEquals("1! = 1", 1, mathFunc.factorial(1));
        assertEquals("5! = 120", 120, mathFunc.factorial(5));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegative() {
        System.out.println("Test: Kiểm tra factorial với số âm");
        mathFunc.factorial(-5);
    }
    
    @Ignore("Chức năng này chưa hoàn thành")
    @Test
    public void testTodo() {
        System.out.println("Test: Bỏ qua");
    }
    
    @Test
    public void testPlus() {
        System.out.println("Test: Kiểm tra phương thức plus");
        assertEquals("2 + 3 = 5", 5, mathFunc.plus(2, 3));
        assertEquals("0 + 0 = 0", 0, mathFunc.plus(0, 0));
    }
}