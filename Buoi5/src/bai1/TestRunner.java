package bai1;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestRunner {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   BÀI 1: KIỂM THỬ VỚI TEST FIXTURE        ║");
        System.out.println("╚════════════════════════════════════════════╝\n");
        
        Result result = JUnitCore.runClasses(MathFuncTest.class);
        
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║           KẾT QUẢ KIỂM THỬ                ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║ Tổng số test:        " + result.getRunCount());
        System.out.println("║ Số test thành công:  " + (result.getRunCount() - result.getFailureCount()));
        System.out.println("║ Số test thất bại:    " + result.getFailureCount());
        System.out.println("║ Số test bỏ qua:      " + result.getIgnoreCount());
        System.out.println("╠════════════════════════════════════════════╣");
        
        if (result.wasSuccessful()) {
            System.out.println("║ ✓ TẤT CẢ TEST CASES ĐỀU PASS!            ║");
        } else {
            System.out.println("║ ✗ MỘT SỐ TEST CASES BỊ FAIL!             ║");
        }
        
        System.out.println("╚════════════════════════════════════════════╝");
    }
}
