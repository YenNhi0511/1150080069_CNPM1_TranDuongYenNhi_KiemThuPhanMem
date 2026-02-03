package Bai5;


import java.sql.*;

public class DatabaseManager {
    // Tên file database (nó sẽ tự tạo ra trong thư mục dự án)
    private static final String DB_URL = "jdbc:sqlite:customers.db";

    // 1. Kết nối đến Database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // 2. Tạo bảng (Chạy 1 lần đầu tiên)
    public static void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS customers (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "customer_id TEXT UNIQUE NOT NULL," +
                     "full_name TEXT NOT NULL," +
                     "email TEXT UNIQUE NOT NULL," +
                     "phone_number TEXT," +
                     "address TEXT," +
                     "password TEXT," +
                     "date_of_birth TEXT," +
                     "gender TEXT)";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Kết nối Database thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Kiểm tra Mã KH đã tồn tại chưa
    public static boolean isCustomerIdExists(String customerId) {
        String sql = "SELECT COUNT(*) FROM customers WHERE customer_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu tìm thấy > 0
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. Kiểm tra Email đã tồn tại chưa
    public static boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM customers WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5. Lưu khách hàng mới
    public static void saveCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers(customer_id, full_name, email, phone_number, address, password, date_of_birth, gender) " +
                     "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, customer.getCustomerId());
            pstmt.setString(2, customer.getFullName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhoneNumber());
            pstmt.setString(5, customer.getAddress());
            pstmt.setString(6, customer.getPassword());
            // Chuyển ngày tháng sang chuỗi để lưu
            pstmt.setString(7, customer.getDateOfBirth() != null ? customer.getDateOfBirth().toString() : "");
            pstmt.setString(8, customer.getGender());

            pstmt.executeUpdate();
        }
    }
}