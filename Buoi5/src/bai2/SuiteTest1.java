package bai2;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test Suite 1 - Kiểm thử xuất chuỗi ký tự
 */
public class SuiteTest1 {

    
    private String message;
    
    @Before
    public void setUp() {
        message = "Hello JUnit Test Suite 1";
        System.out.println("SuiteTest1: Khởi tạo");
    }
    
    @After
    public void tearDown() {
        message = null;
        System.out.println("SuiteTest1: Dọn dẹp\n");
    }
    
    @Test
    public void testMessage() {
        System.out.println("SuiteTest1: Test message");
        assertNotNull("Message không được null", message);
        assertEquals("Hello JUnit Test Suite 1", message);
    }
    
    @Test
    public void testMessageLength() {
        System.out.println("SuiteTest1: Test message length");
        assertTrue("Độ dài message phải > 0", message.length() > 0);
        assertEquals(24, message.length());
    }
    
    @Test
    public void testMessageContains() {
        System.out.println("SuiteTest1: Test message contains");
        assertTrue("Message phải chứa 'JUnit'", message.contains("JUnit"));
        assertTrue("Message phải chứa 'Suite'", message.contains("Suite"));
    }
}