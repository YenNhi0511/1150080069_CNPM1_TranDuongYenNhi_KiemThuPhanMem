package Bai5;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Class thực thi test cases cho Customer Validator
 */
public class TestRunner {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  BÀI 5: KIỂM THỬ FORM ĐĂNG KÝ KHÁCH HÀNG             ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
        
        Result result = JUnitCore.runClasses(CustomerValidatorTest.class);
        
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                 KẾT QUẢ KIỂM THỬ                      ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║ Tổng số test:        " + result.getRunCount());
        System.out.println("║ Số test thành công:  " + (result.getRunCount() - result.getFailureCount()));
        System.out.println("║ Số test thất bại:    " + result.getFailureCount());
        System.out.println("║ Thời gian chạy:      " + result.getRunTime() + " ms");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        
        if (result.getFailureCount() > 0) {
            System.out.println("║ CHI TIẾT CÁC LỖI:                                     ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            for (Failure failure : result.getFailures()) {
                System.out.println("║ " + failure.toString());
            }
        }
        
        if (result.wasSuccessful()) {
            System.out.println("║ ✓ TẤT CẢ TEST CASES ĐỀU PASS!                        ║");
        } else {
            System.out.println("║ ✗ MỘT SỐ TEST CASES BỊ FAIL!                         ║");
        }
        
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
}