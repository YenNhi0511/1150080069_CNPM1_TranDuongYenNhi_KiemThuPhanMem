package bai2;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test Suite 2 - Kiểm thử xuất chuỗi ký tự khác
 */
public class SuiteTest2 {
    
    private String text;
    
    @Before
    public void setUp() {
        text = "Testing with Suite 2";
        System.out.println("SuiteTest2: Khởi tạo");
    }
    
    @After
    public void tearDown() {
        text = null;
        System.out.println("SuiteTest2: Dọn dẹp\n");
    }
    
    @Test
    public void testText() {
        System.out.println("SuiteTest2: Test text");
        assertNotNull("Text không được null", text);
        assertEquals("Testing with Suite 2", text);
    }
    
    @Test
    public void testTextNotEmpty() {
        System.out.println("SuiteTest2: Test text not empty");
        assertFalse("Text không được rỗng", text.isEmpty());
    }
    
    @Test
    public void testTextStartsWith() {
        System.out.println("SuiteTest2: Test text starts with");
        assertTrue("Text phải bắt đầu bằng 'Testing'", text.startsWith("Testing"));
    }
    
    @Test
    public void testTextEndsWith() {
        System.out.println("SuiteTest2: Test text ends with");
        assertTrue("Text phải kết thúc bằng '2'", text.endsWith("2"));
    }
}