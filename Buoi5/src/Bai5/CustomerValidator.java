package Bai5;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class CustomerValidator {
    
    private static Set<String> existingCustomerIds = new HashSet<>();
    private static Set<String> existingEmails = new HashSet<>();
    
    private static final Pattern CUSTOMER_ID_PATTERN = Pattern.compile("^[a-zA-Z0-9]{6,10}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-ZÀ-ỹ\\s]{5,50}$");
    
    public String validateCustomerId(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            return "Mã khách hàng không được để trống";
        }
        
        customerId = customerId.trim();
        
        if (customerId.length() < 6 || customerId.length() > 10) {
            return "Mã khách hàng phải từ 6 đến 10 ký tự";
        }
        
        if (!CUSTOMER_ID_PATTERN.matcher(customerId).matches()) {
            return "Mã khách hàng chỉ được chứa chữ cái và số";
        }
        
        if (existingCustomerIds.contains(customerId.toLowerCase())) {
            return "Mã khách hàng đã tồn tại";
        }
        
        return null;
    }
    
    public String validateFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "Họ và tên không được để trống";
        }
        
        fullName = fullName.trim();
        
        if (fullName.length() < 5 || fullName.length() > 50) {
            return "Họ và tên phải từ 5 đến 50 ký tự";
        }
        
        if (!NAME_PATTERN.matcher(fullName).matches()) {
            return "Họ và tên chỉ được chứa chữ cái và khoảng trắng";
        }
        
        return null;
    }
    
    public String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "Email không được để trống";
        }
        
        email = email.trim();
        
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return "Email không đúng định dạng";
        }
        
        if (existingEmails.contains(email.toLowerCase())) {
            return "Email đã được sử dụng";
        }
        
        return null;
    }
    
    public String validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return "Số điện thoại không được để trống";
        }
        
        phoneNumber = phoneNumber.trim();
        
        if (!phoneNumber.matches("^[0-9]+$")) {
            return "Số điện thoại chỉ được chứa số";
        }
        
        if (!phoneNumber.startsWith("0")) {
            return "Số điện thoại phải bắt đầu bằng số 0";
        }
        
        if (phoneNumber.length() < 10 || phoneNumber.length() > 12) {
            return "Số điện thoại phải từ 10 đến 12 ký tự";
        }
        
        return null;
    }
    
    public String validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            return "Địa chỉ không được để trống";
        }
        
        address = address.trim();
        
        if (address.length() > 255) {
            return "Địa chỉ không được vượt quá 255 ký tự";
        }
        
        return null;
    }
    
    public String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Mật khẩu không được để trống";
        }
        
        if (password.length() < 8) {
            return "Mật khẩu phải có ít nhất 8 ký tự";
        }
        
        return null;
    }
    
    public String validateConfirmPassword(String password, String confirmPassword) {
        if (confirmPassword == null || confirmPassword.isEmpty()) {
            return "Xác nhận mật khẩu không được để trống";
        }
        
        if (!confirmPassword.equals(password)) {
            return "Mật khẩu xác nhận không khớp";
        }
        
        return null;
    }
    
    public String validateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return null; // Không bắt buộc
        }
        
        LocalDate today = LocalDate.now();
        int age = Period.between(dateOfBirth, today).getYears();
        
        if (age < 18) {
            return "Người dùng phải đủ 18 tuổi";
        }
        
        if (dateOfBirth.isAfter(today)) {
            return "Ngày sinh không được là ngày trong tương lai";
        }
        
        return null;
    }
    
    public static void addExistingCustomerId(String customerId) {
        existingCustomerIds.add(customerId.toLowerCase());
    }
    
    public static void addExistingEmail(String email) {
        existingEmails.add(email.toLowerCase());
    }
    
    public static void resetExistingData() {
        existingCustomerIds.clear();
        existingEmails.clear();
    }
}