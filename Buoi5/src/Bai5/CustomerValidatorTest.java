package Bai5;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class CustomerValidatorTest {
    
    private CustomerValidator validator;
    
    @Before
    public void setUp() {
        validator = new CustomerValidator();
        CustomerValidator.resetExistingData();
    }
    
    // TEST MÃ KHÁCH HÀNG
    @Test
    public void testCustomerIdValid() {
        assertNull(validator.validateCustomerId("KH1234"));
    }
    
    @Test
    public void testCustomerIdEmpty() {
        assertNotNull(validator.validateCustomerId(""));
    }
    
    @Test
    public void testCustomerIdTooShort() {
        assertNotNull(validator.validateCustomerId("KH123"));
    }
    
    @Test
    public void testCustomerIdTooLong() {
        assertNotNull(validator.validateCustomerId("KH12345678901"));
    }
    
    // TEST HỌ VÀ TÊN
    @Test
    public void testFullNameValid() {
        assertNull(validator.validateFullName("Nguyen Van A"));
    }
    
    @Test
    public void testFullNameEmpty() {
        assertNotNull(validator.validateFullName(""));
    }
    
    @Test
    public void testFullNameTooShort() {
        assertNotNull(validator.validateFullName("Nam"));
    }
    
    // TEST EMAIL
    @Test
    public void testEmailValid() {
        assertNull(validator.validateEmail("test@example.com"));
    }
    
    @Test
    public void testEmailInvalid() {
        assertNotNull(validator.validateEmail("invalid.email"));
    }
    
    // TEST SỐ ĐIỆN THOẠI
    @Test
    public void testPhoneValid() {
        assertNull(validator.validatePhoneNumber("0123456789"));
    }
    
    @Test
    public void testPhoneInvalid() {
        assertNotNull(validator.validatePhoneNumber("123456789"));
    }
    
    // TEST MẬT KHẨU
    @Test
    public void testPasswordValid() {
        assertNull(validator.validatePassword("password123"));
    }
    
    @Test
    public void testPasswordTooShort() {
        assertNotNull(validator.validatePassword("pass123"));
    }
    
    // TEST NGÀY SINH
    @Test
    public void testDateOfBirthValid() {
        assertNull(validator.validateDateOfBirth(LocalDate.now().minusYears(20)));
    }
    
    @Test
    public void testDateOfBirthUnder18() {
        assertNotNull(validator.validateDateOfBirth(LocalDate.now().minusYears(17)));
    }
}