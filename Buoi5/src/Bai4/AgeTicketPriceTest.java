package Bai4;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test class cho AgeTicketPrice
 * Sử dụng phương pháp Boundary Value Analysis và Equivalence Partitioning
 */
public class AgeTicketPriceTest {
    
    private AgeTicketPrice ticketPrice;
    
    @Before
    public void setUp() {
        ticketPrice = new AgeTicketPrice();
        System.out.println("Khởi tạo test");
    }
    
    @After
    public void tearDown() {
        ticketPrice = null;
        System.out.println("Dọn dẹp test\n");
    }
    
    // ===== TEST TRẺ EM DƯỚI 5 TUỔI (0 VNĐ) =====
    
    @Test
    public void testAge0_MinBoundary() {
        System.out.println("Test: Tuổi = 0 (Min boundary)");
        assertEquals(0, ticketPrice.calculateTicketPrice(0));
    }
    
    @Test
    public void testAge2_NormalCase() {
        System.out.println("Test: Tuổi = 2 (Normal value)");
        assertEquals(0, ticketPrice.calculateTicketPrice(2));
    }
    
    @Test
    public void testAge4_MaxBoundary() {
        System.out.println("Test: Tuổi = 4 (Max boundary)");
        assertEquals(0, ticketPrice.calculateTicketPrice(4));
    }
    
    // ===== TEST TRẺ EM TỪ 5-14 TUỔI (50,000 VNĐ) =====
    
    @Test
    public void testAge5_MinBoundary() {
        System.out.println("Test: Tuổi = 5 (Min boundary)");
        assertEquals(50000, ticketPrice.calculateTicketPrice(5));
    }
    
    @Test
    public void testAge10_NormalCase() {
        System.out.println("Test: Tuổi = 10 (Normal value)");
        assertEquals(50000, ticketPrice.calculateTicketPrice(10));
    }
    
    @Test
    public void testAge14_MaxBoundary() {
        System.out.println("Test: Tuổi = 14 (Max boundary)");
        assertEquals(50000, ticketPrice.calculateTicketPrice(14));
    }
    
    // ===== TEST NGƯỜI LỚN TỪ 15-59 TUỔI (100,000 VNĐ) =====
    
    @Test
    public void testAge15_MinBoundary() {
        System.out.println("Test: Tuổi = 15 (Min boundary)");
        assertEquals(100000, ticketPrice.calculateTicketPrice(15));
    }
    
    @Test
    public void testAge30_NormalCase() {
        System.out.println("Test: Tuổi = 30 (Normal value)");
        assertEquals(100000, ticketPrice.calculateTicketPrice(30));
    }
    
    @Test
    public void testAge59_MaxBoundary() {
        System.out.println("Test: Tuổi = 59 (Max boundary)");
        assertEquals(100000, ticketPrice.calculateTicketPrice(59));
    }
    
    // ===== TEST NGƯỜI CAO TUỔI TỪ 60 TRỞ LÊN (70,000 VNĐ) =====
    
    @Test
    public void testAge60_MinBoundary() {
        System.out.println("Test: Tuổi = 60 (Min boundary)");
        assertEquals(70000, ticketPrice.calculateTicketPrice(60));
    }
    
    @Test
    public void testAge80_NormalCase() {
        System.out.println("Test: Tuổi = 80 (Normal value)");
        assertEquals(70000, ticketPrice.calculateTicketPrice(80));
    }
    
    @Test
    public void testAge100_HighValue() {
        System.out.println("Test: Tuổi = 100 (High value)");
        assertEquals(70000, ticketPrice.calculateTicketPrice(100));
    }
    
    // ===== TEST INVALID CASES =====
    
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAge() {
        System.out.println("Test: Tuổi âm (Invalid)");
        ticketPrice.calculateTicketPrice(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testTooOldAge() {
        System.out.println("Test: Tuổi quá lớn > 150 (Invalid)");
        ticketPrice.calculateTicketPrice(151);
    }
    
    // ===== TEST DESCRIPTION =====
    
    @Test
    public void testPriceDescription() {
        System.out.println("Test: Mô tả giá vé");
        String desc0 = ticketPrice.getPriceDescription(3);
        String desc5 = ticketPrice.getPriceDescription(10);
        String desc15 = ticketPrice.getPriceDescription(25);
        String desc60 = ticketPrice.getPriceDescription(65);
        
        assertTrue(desc0.contains("Trẻ em dưới 5 tuổi"));
        assertTrue(desc5.contains("Trẻ em từ 5-14 tuổi"));
        assertTrue(desc15.contains("Người lớn từ 15-59 tuổi"));
        assertTrue(desc60.contains("Người cao tuổi"));
    }
}