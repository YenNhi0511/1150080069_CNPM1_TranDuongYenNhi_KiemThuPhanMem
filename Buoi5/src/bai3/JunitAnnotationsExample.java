package bai3;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Minh họa sử dụng các JUnit Annotations
 */
public class JunitAnnotationsExample {
    
    private String testData;
    
    /**
     * Chạy một lần trước tất cả test cases (phải là static)
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("@BeforeClass: Thiết lập trước khi chạy tất cả test");
        System.out.println("   → Khởi tạo database connection, load config file, v.v.");
        System.out.println();
    }
    
    /**
     * Chạy trước mỗi test case
     */
    @Before
    public void setUp() {
        testData = "JUnit Test Data";
        System.out.println("   @Before: Chuẩn bị dữ liệu cho test case");
    }
    
    /**
     * Test case 1
     */
    @Test
    public void test1() {
        System.out.println("      @Test: Chạy test1()");
        assertNotNull("Test data không được null", testData);
        assertEquals("JUnit Test Data", testData);
    }
    
    /**
     * Test case 2
     */
    @Test
    public void test2() {
        System.out.println("      @Test: Chạy test2()");
        assertTrue("Test data phải chứa 'JUnit'", testData.contains("JUnit"));
    }
    
    /**
     * Test case 3 - Kiểm tra exception
     */
    @Test(expected = ArithmeticException.class)
    public void testException() {
        System.out.println("      @Test: Chạy testException() - mong đợi exception");
        int result = 10 / 0; // Sẽ ném ArithmeticException
    }
    
    /**
     * Test case 4 - Kiểm tra timeout
     */
    @Test(timeout = 1000)
    public void testTimeout() {
        System.out.println("      @Test: Chạy testTimeout() - phải hoàn thành trong 1000ms");
        // Test case này phải hoàn thành trong 1 giây
        for (int i = 0; i < 100000; i++) {
            // Do something
        }
    }
    
    /**
     * Test case bị bỏ qua
     */
    @Ignore("Tính năng này đang được phát triển")
    @Test
    public void testIgnore() {
        System.out.println("      @Test: Test này sẽ bị bỏ qua");
        fail("Test này không nên chạy");
    }
    
    /**
     * Chạy sau mỗi test case
     */
    @After
    public void tearDown() {
        testData = null;
        System.out.println("   @After: Dọn dẹp sau test case");
        System.out.println();
    }
    
    /**
     * Chạy một lần sau tất cả test cases (phải là static)
     */
    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println();
        System.out.println("@AfterClass: Dọn dẹp sau khi chạy tất cả test");
        System.out.println("   → Đóng database connection, giải phóng tài nguyên, v.v.");
    }
}