# Test Strategy - Dự án ShopEasy E-commerce

## 1. Giới thiệu

### 1.1 Mục đích
Tài liệu này xác định chiến lược kiểm thử tổng thể cho dự án **ShopEasy** - một nền tảng thương mại điện tử hiện đại. Chiến lược bao gồm phạm vi kiểm thử, phương pháp tiếp cận, công cụ sử dụng, môi trường kiểm thử, và tiêu chí chất lượng cần đạt được trước khi release.

### 1.2 Bối cảnh dự án
ShopEasy là ứng dụng web thương mại điện tử phục vụ thị trường Việt Nam, tích hợp thanh toán qua nhiều cổng thanh toán bao gồm VPBank Installment Payment (trả góp). Dự án phát triển theo mô hình Agile Scrum với sprint 2 tuần, đội ngũ gồm 8 developers, 3 QA engineers, và 1 QA Lead.

---

## 2. Phạm vi kiểm thử (Test Scope)

### 2.1 Trong phạm vi (In-Scope)

| # | Module | Mô tả | Mức ưu tiên |
|---|--------|-------|-------------|
| 1 | **User Authentication** | Đăng ký, đăng nhập, quên mật khẩu, OAuth (Google/Facebook) | P1 - Critical |
| 2 | **Product Catalog** | Tìm kiếm, lọc, sắp xếp, phân trang, chi tiết sản phẩm | P1 - Critical |
| 3 | **Shopping Cart** | Thêm/xóa/sửa số lượng, tính tổng, áp dụng voucher | P1 - Critical |
| 4 | **Payment Processing** | Thanh toán COD, VPBank installment, MoMo, ZaloPay, VNPay | P1 - Critical |
| 5 | **Order Management** | Đặt hàng, theo dõi, hủy đơn, lịch sử mua hàng | P2 - High |

### 2.2 Ngoài phạm vi (Out-of-Scope)

| # | Module | Lý do |
|---|--------|-------|
| 1 | **Admin Dashboard** | Sẽ test ở phase 2, hiện đang phát triển |
| 2 | **Mobile App (iOS/Android)** | Dự án mobile riêng biệt, đội QA mobile phụ trách |

---

## 3. Phương pháp kiểm thử (Test Approach)

### 3.1 Các loại kiểm thử

| Loại kiểm thử | Tỷ lệ tự động | Công cụ | Trách nhiệm |
|---------------|---------------|---------|-------------|
| **Unit Testing** | 90% | JUnit 5, Mockito | Developer |
| **API Testing** | 85% | REST Assured, Postman | QA + Dev |
| **UI Testing** | 70% | Selenium 4, TestNG | QA Team |
| **Performance Testing** | 100% | JMeter, Gatling | QA Lead |
| **Security Testing** | 60% | OWASP ZAP, SonarQube | Security Team + QA |

### 3.2 Kim tự tháp kiểm thử
```
        /   UI    \      ← 20% (Selenium - E2E critical flows)
       / API Tests \     ← 30% (REST Assured - integration)
      / Unit Tests  \    ← 50% (JUnit - business logic)
```

### 3.3 Automation Strategy
- **Framework**: Selenium 4 + TestNG + Page Object Model (POM)
- **Data-Driven**: Apache POI (Excel) + Jackson (JSON) + Java Faker
- **CI/CD**: GitHub Actions với matrix strategy (Chrome + Firefox)
- **Distributed Testing**: Selenium Grid 4 qua Docker
- **Reporting**: Allure Report + GitHub Pages

---

## 4. Môi trường kiểm thử (Test Environment)

| Môi trường | URL | Mục đích | Database |
|-----------|-----|---------|----------|
| **DEV** | dev.shopeasy.vn | Unit + Integration test | MySQL (mock data) |
| **STAGING** | staging.shopeasy.vn | System + UAT test | MySQL (clone prod) |
| **PRE-PROD** | preprod.shopeasy.vn | Performance + Security test | MySQL (prod mirror) |
| **PROD** | www.shopeasy.vn | Smoke test sau deploy | MySQL (live) |

### 4.1 Browser Matrix

| Browser | Version | OS | Ưu tiên |
|---------|---------|-----|---------|
| Chrome | Latest, Latest-1 | Windows 10/11, macOS | P1 |
| Firefox | Latest | Windows 10/11 | P2 |
| Safari | Latest | macOS, iOS | P2 |
| Edge | Latest | Windows 10/11 | P3 |

---

## 5. Quản lý lỗi (Defect Management)

### 5.1 Quy trình
- **Tool**: Jira với workflow: New → In Progress → Resolved → Verified → Closed
- **Bug Triage**: Hàng ngày lúc 9:00 AM (QA Lead + Tech Lead + PM)
- **SLA**: Critical bugs fix trong 4h, High trong 24h, Medium trong sprint hiện tại

### 5.2 Phân loại mức độ nghiêm trọng

| Severity | Mô tả | Ví dụ |
|----------|-------|-------|
| **Blocker** | Không thể sử dụng hệ thống | Crash khi mở app, mất dữ liệu |
| **Critical** | Chức năng chính bị hỏng | Không thể thanh toán, đăng nhập lỗi |
| **Major** | Chức năng phụ bị ảnh hưởng | Lọc sản phẩm sai, voucher không áp dụng |
| **Minor** | Lỗi giao diện, typo | Sai font, alignment lệch |

---

## 6. Rủi ro và giảm thiểu (Risk Management)

| # | Rủi ro | Xác suất | Ảnh hưởng | Giảm thiểu |
|---|--------|---------|-----------|-----------|
| 1 | VPBank Sandbox không ổn định | Cao | Cao | Sử dụng mock API cho regression, chỉ test sandbox khi cần |
| 2 | Thay đổi yêu cầu giữa sprint | Trung bình | Cao | Sprint planning kỹ, buffer 20% capacity cho changes |
| 3 | Thiếu test data cho payment | Trung bình | Trung bình | Tạo data generator script, dùng Java Faker |
| 4 | Browser compatibility issues | Thấp | Trung bình | Matrix testing trên CI/CD, Selenium Grid |
| 5 | Performance degradation | Trung bình | Cao | Load test mỗi sprint, monitoring với Grafana |

---

## 7. Tiêu chí hoàn thành (Exit Criteria / Definition of Done)

### 7.1 Tiêu chí chất lượng

| Tiêu chí | Ngưỡng yêu cầu |
|----------|----------------|
| Test case pass rate | ≥ 95% |
| Critical/Blocker bugs | 0 open |
| Code coverage (Unit) | ≥ 80% |
| API test coverage | ≥ 85% |
| UI automation coverage | ≥ 70% critical flows |
| Performance (P95 response time) | ≤ 3 giây |
| Security vulnerabilities (High) | 0 open |

### 7.2 Definition of Done (DoD) cho mỗi User Story
- [ ] Unit tests viết và pass
- [ ] API tests pass
- [ ] UI automation tests pass (nếu có UI)
- [ ] Code review approved
- [ ] Smoke test trên Staging pass
- [ ] Không có Critical/Blocker bugs mở
- [ ] Documentation cập nhật

---

## 8. Lịch trình và Deliverables

| Sprint | Thời gian | Focus |
|--------|-----------|-------|
| Sprint 4 | Tuần 1-2 | Authentication + Product Catalog |
| Sprint 5 | Tuần 3-4 | Shopping Cart + VPBank Payment |
| Sprint 6 | Tuần 5-6 | Order Management + Integration |
| Sprint 7 | Tuần 7-8 | Performance + Security + UAT |

---

*Tài liệu được phê duyệt bởi: QA Lead - [Tên sinh viên]*
*Ngày: [Ngày nộp bài]*
*Version: 1.0*
