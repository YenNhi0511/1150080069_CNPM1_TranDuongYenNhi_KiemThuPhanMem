package Bai4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Form nhập liệu và tính giá vé theo độ tuổi
 */
public class TicketPriceGUI extends JFrame {
    
    private JTextField txtAge;
    private JTextArea txtResult;
    private JButton btnCalculate, btnClear;
    private AgeTicketPrice ticketPrice;
    
    public TicketPriceGUI() {
        ticketPrice = new AgeTicketPrice();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Hệ thống tính giá vé theo độ tuổi");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Panel tiêu đề
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(52, 152, 219));
        JLabel lblTitle = new JLabel("TÍNH GIÁ VÉ THEO ĐỘ TUỔI");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        
        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Bảng giá
        JLabel lblPriceTable = new JLabel("<html><b>Bảng giá:</b><br>" +
                "• Dưới 5 tuổi: Miễn phí<br>" +
                "• Từ 5-14 tuổi: 50,000 VNĐ<br>" +
                "• Từ 15-59 tuổi: 100,000 VNĐ<br>" +
                "• Từ 60 tuổi trở lên: 70,000 VNĐ</html>");
        lblPriceTable.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        inputPanel.add(lblPriceTable, gbc);
        
        // Nhập tuổi
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        JLabel lblAge = new JLabel("Nhập độ tuổi:");
        lblAge.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(lblAge, gbc);
        
        gbc.gridx = 1;
        txtAge = new JTextField(15);
        txtAge.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(txtAge, gbc);
        
        // Panel buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnCalculate = new JButton("Tính giá vé");
        btnCalculate.setFont(new Font("Arial", Font.BOLD, 14));
        btnCalculate.setBackground(new Color(46, 204, 113));
        btnCalculate.setForeground(Color.WHITE);
        btnCalculate.setFocusPainted(false);
        
        btnClear = new JButton("Nhập lại");
        btnClear.setFont(new Font("Arial", Font.BOLD, 14));
        btnClear.setBackground(new Color(231, 76, 60));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        
        buttonPanel.add(btnCalculate);
        buttonPanel.add(btnClear);
        
        // Panel kết quả
        JPanel resultPanel = new JPanel(new BorderLayout(5, 5));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        JLabel lblResult = new JLabel("Kết quả:");
        lblResult.setFont(new Font("Arial", Font.BOLD, 14));
        
        txtResult = new JTextArea(6, 30);
        txtResult.setFont(new Font("Arial", Font.PLAIN, 13));
        txtResult.setEditable(false);
        txtResult.setLineWrap(true);
        txtResult.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtResult);
        
        resultPanel.add(lblResult, BorderLayout.NORTH);
        resultPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Thêm các panel vào frame
        add(titlePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);
        add(resultPanel, BorderLayout.SOUTH);
        
        // Event handlers
        btnCalculate.addActionListener(e -> calculatePrice());
        btnClear.addActionListener(e -> clearForm());
        txtAge.addActionListener(e -> calculatePrice());
    }
    
    private void calculatePrice() {
        try {
            String ageText = txtAge.getText().trim();
            
            if (ageText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Vui lòng nhập độ tuổi!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtAge.requestFocus();
                return;
            }
            
            int age = Integer.parseInt(ageText);
            int price = ticketPrice.calculateTicketPrice(age);
            String description = ticketPrice.getPriceDescription(age);
            
            txtResult.setText(String.format(
                    "═══════════════════════════════════\n" +
                    "           KẾT QUẢ TÍNH GIÁ VÉ\n" +
                    "═══════════════════════════════════\n" +
                    "Độ tuổi: %d tuổi\n" +
                    "Phân loại: %s\n" +
                    "Giá vé: %,d VNĐ\n" +
                    "═══════════════════════════════════",
                    age, description, price
            ));
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Độ tuổi phải là số nguyên!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtAge.requestFocus();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtAge.requestFocus();
        }
    }
    
    private void clearForm() {
        txtAge.setText("");
        txtResult.setText("");
        txtAge.requestFocus();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicketPriceGUI().setVisible(true);
        });
    }
}