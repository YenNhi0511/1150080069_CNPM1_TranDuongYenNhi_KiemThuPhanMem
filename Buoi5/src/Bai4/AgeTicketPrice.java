package Bai4;

/**
 * Class tính giá vé dựa trên độ tuổi
 */
public class AgeTicketPrice {
    
    public static final int PRICE_UNDER_5 = 0;
    public static final int PRICE_5_TO_14 = 50000;
    public static final int PRICE_15_TO_59 = 100000;
    public static final int PRICE_60_PLUS = 70000;
    
    /**
     * Tính giá vé dựa trên độ tuổi
     * @param age độ tuổi của khách hàng
     * @return giá vé tương ứng
     * @throws IllegalArgumentException nếu tuổi không hợp lệ
     */
    public int calculateTicketPrice(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Tuổi không được âm");
        }
        
        if (age > 150) {
            throw new IllegalArgumentException("Tuổi không hợp lệ (quá 150)");
        }
        
        if (age < 5) {
            return PRICE_UNDER_5;
        } else if (age <= 14) {
            return PRICE_5_TO_14;
        } else if (age <= 59) {
            return PRICE_15_TO_59;
        } else {
            return PRICE_60_PLUS;
        }
    }
    
    /**
     * Lấy thông tin mô tả giá vé theo độ tuổi
     */
    public String getPriceDescription(int age) {
        int price = calculateTicketPrice(age);
        String category;
        
        if (age < 5) {
            category = "Trẻ em dưới 5 tuổi";
        } else if (age <= 14) {
            category = "Trẻ em từ 5-14 tuổi";
        } else if (age <= 59) {
            category = "Người lớn từ 15-59 tuổi";
        } else {
            category = "Người cao tuổi từ 60 tuổi trở lên";
        }
        
        return String.format("%s: %,d VNĐ", category, price);
    }
}