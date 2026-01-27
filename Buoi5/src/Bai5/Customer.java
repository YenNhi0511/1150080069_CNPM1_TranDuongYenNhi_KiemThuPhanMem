package Bai5;

import java.time.LocalDate;

public class Customer {
    private String customerId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private LocalDate dateOfBirth;
    private String gender;
    
    public Customer() {}
    
    public Customer(String customerId, String fullName, String email, String phoneNumber,
                    String address, String password, LocalDate dateOfBirth, String gender) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
    
    // Getters
    public String getCustomerId() { return customerId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public String getPassword() { return password; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getGender() { return gender; }
    
    // Setters
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAddress(String address) { this.address = address; }
    public void setPassword(String password) { this.password = password; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setGender(String gender) { this.gender = gender; }
    
    @Override
    public String toString() {
        return String.format("Khách hàng [ID=%s, Tên=%s, Email=%s, SĐT=%s]",
                customerId, fullName, email, phoneNumber);
    }
}