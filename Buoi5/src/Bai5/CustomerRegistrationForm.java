package Bai5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CustomerRegistrationForm extends JFrame {
    
    private JTextField txtCustomerId, txtFullName, txtEmail, txtPhone, txtAddress, txtDOB;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JRadioButton rbMale, rbFemale, rbOther;
    private JCheckBox chkTerms;
    private JButton btnRegister, btnReset;
    private CustomerValidator validator;
    
    public CustomerRegistrationForm() {
        validator = new CustomerValidator();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Đăng Ký Tài Khoản Khách Hàng");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Panel tiêu đề
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(41, 128, 185));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        JLabel lblTitle = new JLabel("ĐĂNG KÝ TÀI KHOẢN KHÁCH HÀNG");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        
        // Panel form
        JPanel formPanel = new JPanel(new GridLayout(11, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        // Mã khách hàng
        formPanel.add(new JLabel("Mã Khách Hàng: *"));
        txtCustomerId = new JTextField();
        formPanel.add(txtCustomerId);
        
        // Họ và tên
        formPanel.add(new JLabel("Họ và Tên: *"));
        txtFullName = new JTextField();
        formPanel.add(txtFullName);
        
        // Email
        formPanel.add(new JLabel("Email: *"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);
        
        // Số điện thoại
        formPanel.add(new JLabel("Số Điện Thoại: *"));
        txtPhone = new JTextField();
        formPanel.add(txtPhone);
        
        // Địa chỉ
        formPanel.add(new JLabel("Địa Chỉ: *"));
        txtAddress = new JTextField();
        formPanel.add(txtAddress);
        
        // Mật khẩu
        formPanel.add(new JLabel("Mật Khẩu: *"));
        txtPassword = new JPasswordField();
        formPanel.add(txtPassword);
        
        // Xác nhận mật khẩu
        formPanel.add(new JLabel("Xác Nhận Mật Khẩu: *"));
        txtConfirmPassword = new JPasswordField();
        formPanel.add(txtConfirmPassword);
        
        // Ngày sinh (dd/MM/yyyy)
        formPanel.add(new JLabel("Ngày Sinh (dd/MM/yyyy):"));
        txtDOB = new JTextField();
        formPanel.add(txtDOB);
        
        // Giới tính
        formPanel.add(new JLabel("Giới Tính:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbMale = new JRadioButton("Nam");
        rbFemale = new JRadioButton("Nữ");
        rbOther = new JRadioButton("Khác");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        genderGroup.add(rbOther);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        genderPanel.add(rbOther);
        formPanel.add(genderPanel);
        
        // Điều khoản
        formPanel.add(new JLabel(""));
        chkTerms = new JCheckBox("Tôi đồng ý với điều khoản dịch vụ *");
        formPanel.add(chkTerms);
        
        // Panel buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        
        btnRegister = new JButton("Đăng Ký");
        btnRegister.setBackground(new Color(39, 174, 96));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setPreferredSize(new Dimension(120, 35));
        
        btnReset = new JButton("Nhập Lại");
        btnReset.setBackground(new Color(231, 76, 60));
        btnReset.setForeground(Color.WHITE);
        btnReset.setPreferredSize(new Dimension(120, 35));
        
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnReset);
        
        // Thêm vào frame
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Event handlers
        btnRegister.addActionListener(e -> handleRegister());
        btnReset.addActionListener(e -> handleReset());
    }
    
    private void handleRegister() {
        try {
            String customerId = txtCustomerId.getText().trim();
            String fullName = txtFullName.getText().trim();
            String email = txtEmail.getText().trim();
            String phone = txtPhone.getText().trim();
            String address = txtAddress.getText().trim();
            String password = new String(txtPassword.getPassword());
            String confirmPassword = new String(txtConfirmPassword.getPassword());
            
            LocalDate dob = null;
            if (!txtDOB.getText().trim().isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dob = LocalDate.parse(txtDOB.getText().trim(), formatter);
            }
            
            String gender = null;
            if (rbMale.isSelected()) gender = "Nam";
            else if (rbFemale.isSelected()) gender = "Nữ";
            else if (rbOther.isSelected()) gender = "Khác";
            
            // Validate
            String error;
            if ((error = validator.validateCustomerId(customerId)) != null) {
                showError(error);
                return;
            }
            if ((error = validator.validateFullName(fullName)) != null) {
                showError(error);
                return;
            }
            if ((error = validator.validateEmail(email)) != null) {
                showError(error);
                return;
            }
            if ((error = validator.validatePhoneNumber(phone)) != null) {
                showError(error);
                return;
            }
            if ((error = validator.validateAddress(address)) != null) {
                showError(error);
                return;
            }
            if ((error = validator.validatePassword(password)) != null) {
                showError(error);
                return;
            }
            if ((error = validator.validateConfirmPassword(password, confirmPassword)) != null) {
                showError(error);
                return;
            }
            if ((error = validator.validateDateOfBirth(dob)) != null) {
                showError(error);
                return;
            }
            if (!chkTerms.isSelected()) {
                showError("Bạn phải đồng ý với điều khoản dịch vụ");
                return;
            }
            
            Customer customer = new Customer(customerId, fullName, email, phone, address, password, dob, gender);
            CustomerValidator.addExistingCustomerId(customerId);
            CustomerValidator.addExistingEmail(email);
            
            JOptionPane.showMessageDialog(this, "Đăng ký tài khoản thành công!\n\n" + customer, 
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
            handleReset();
            
        } catch (Exception ex) {
            showError("Lỗi: " + ex.getMessage());
        }
    }
    
    private void handleReset() {
        txtCustomerId.setText("");
        txtFullName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
        txtDOB.setText("");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbMale);
        bg.add(rbFemale);
        bg.add(rbOther);
        bg.clearSelection();
        chkTerms.setSelected(false);
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi Validation", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomerRegistrationForm().setVisible(true));
    }
}