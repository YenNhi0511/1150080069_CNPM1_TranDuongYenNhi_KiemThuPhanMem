# Test Plan - Sprint 5: Tính năng Thanh toán Trả góp VPBank

## 1. Thông tin chung

| Mục | Chi tiết |
|-----|---------|
| **Dự án** | ShopEasy E-commerce |
| **Sprint** | Sprint 5 (2 tuần) |
| **Feature** | VPBank Installment Payment (Trả góp qua VPBank) |
| **QA Lead** | [Tên sinh viên] |
| **Ngày bắt đầu** | [Ngày bắt đầu sprint] |
| **Ngày kết thúc** | [Ngày kết thúc sprint] |

## 2. Mục tiêu Sprint

Kiểm thử toàn diện tính năng thanh toán trả góp qua VPBank, đảm bảo:
- Luồng thanh toán trả góp hoạt động end-to-end
- Tích hợp API VPBank sandbox ổn định
- Xử lý đúng các trường hợp lỗi (hết hạn mức, timeout, thẻ không hợp lệ)
- Giao diện responsive trên desktop và mobile web
- Performance đáp ứng SLA (≤ 3s response time)

## 3. Phạm vi kiểm thử

### 3.1 Trong phạm vi
- Chọn phương thức trả góp VPBank tại trang checkout
- Nhập thông tin thẻ tín dụng VPBank
- Chọn kỳ hạn trả góp (3, 6, 9, 12 tháng)
- Xác nhận giao dịch qua OTP
- Hiển thị kết quả thanh toán (thành công / thất bại)
- Email xác nhận đơn hàng trả góp

### 3.2 Ngoài phạm vi
- Các cổng thanh toán khác (MoMo, ZaloPay) - đã test Sprint 4
- Admin dashboard quản lý giao dịch

## 4. Phân tích rủi ro

| Rủi ro | Xác suất | Ảnh hưởng | Mitigation |
|--------|---------|-----------|------------|
| VPBank Sandbox downtime | Cao | Cao | Mock API fallback, test vào giờ thấp điểm |
| OTP timeout ngắn (60s) | Trung bình | Trung bình | Tự động hóa flow OTP bằng API bypass trên sandbox |
| Số thẻ test hết lượt dùng | Trung bình | Thấp | Liên hệ VPBank xin thêm thẻ test, rotate thẻ |
| Tỷ giá/lãi suất thay đổi | Thấp | Thấp | Dùng parameterized test, không hardcode số tiền |

## 5. Tài nguyên

| Vai trò | Người | Trách nhiệm |
|---------|-------|-------------|
| QA Lead | [Tên] | Test plan, review, automation framework |
| QA Engineer 1 | [Tên] | Functional testing, API testing |
| QA Engineer 2 | [Tên] | UI automation, cross-browser testing |
| Dev Support | [Tên] | Bug fix, sandbox configuration |

## 6. Lịch trình

| Ngày | Hoạt động |
|------|-----------|
| Ngày 1-2 | Review requirements, viết test cases |
| Ngày 3-5 | Manual testing happy path + negative |
| Ngày 6-7 | Automation script cho critical flows |
| Ngày 8 | API testing + Integration testing |
| Ngày 9 | Cross-browser testing (Chrome, Firefox, Safari) |
| Ngày 10 | Bug fix verification, regression test, sign-off |

## 7. Danh sách Test Cases (15 cases)

| TC ID | Tên Test Case | Mô tả | Ưu tiên | Loại |
|-------|--------------|-------|---------|------|
| TC-001 | Thanh toán trả góp 3 tháng thành công | Đặt hàng sản phẩm ≥ 3 triệu, chọn trả góp VPBank 3 tháng, nhập thẻ hợp lệ, xác nhận OTP, kiểm tra đơn hàng tạo thành công | P1 | Smoke |
| TC-002 | Thanh toán trả góp 6 tháng thành công | Tương tự TC-001 nhưng chọn kỳ hạn 6 tháng, kiểm tra số tiền mỗi kỳ hiển thị đúng | P1 | Smoke |
| TC-003 | Thanh toán trả góp 12 tháng thành công | Tương tự TC-001 với kỳ hạn 12 tháng, verify lãi suất 0% nếu có chương trình KM | P1 | Smoke |
| TC-004 | Đơn hàng dưới mức tối thiểu trả góp | Đặt hàng ≤ 2 triệu, kiểm tra option trả góp bị ẩn hoặc disable | P1 | Negative |
| TC-005 | Nhập số thẻ không hợp lệ | Nhập số thẻ sai format (thiếu số, chữ cái), kiểm tra thông báo lỗi validation | P1 | Negative |
| TC-006 | Thẻ hết hạn mức tín dụng | Dùng thẻ test có hạn mức 0, kiểm tra thông báo "Insufficient credit limit" | P1 | Negative |
| TC-007 | OTP nhập sai 3 lần | Nhập OTP sai 3 lần liên tiếp, kiểm tra giao dịch bị hủy và thông báo lỗi | P1 | Negative |
| TC-008 | OTP hết hạn (timeout 60s) | Đợi OTP hết hạn, kiểm tra có nút "Gửi lại OTP" và giao dịch không tự động hủy | P2 | Negative |
| TC-009 | Hủy giao dịch giữa chừng | Click "Hủy" trên trang xác nhận VPBank, kiểm tra quay về trang checkout, đơn hàng không tạo | P2 | Regression |
| TC-010 | Network timeout khi gọi API VPBank | Simulate network delay > 30s, kiểm tra hiển thị thông báo "Vui lòng thử lại" | P2 | Regression |
| TC-011 | Verify bảng tính tiền trả góp | Kiểm tra tổng tiền, lãi suất, số tiền mỗi kỳ hiển thị chính xác theo công thức VPBank | P1 | Functional |
| TC-012 | Email xác nhận đơn trả góp | Sau thanh toán thành công, kiểm tra email gửi đúng thông tin: kỳ hạn, số tiền/kỳ, mã đơn | P2 | Functional |
| TC-013 | Responsive trên mobile web | Mở trang thanh toán trả góp trên Chrome mobile emulation (375px), kiểm tra layout không bể | P2 | UI |
| TC-014 | Performance - thời gian load trang thanh toán | Đo thời gian từ click "Thanh toán" đến hiện form VPBank ≤ 3 giây (P95) | P2 | Performance |
| TC-015 | Concurrent payments | 10 users đồng thời thực hiện thanh toán trả góp, verify không có race condition hoặc duplicate order | P2 | Performance |

## 8. Tiêu chí hoàn thành (Exit Criteria)

- [ ] 15/15 test cases được thực thi
- [ ] Pass rate ≥ 95% (≥ 14/15 cases pass)
- [ ] 0 Critical/Blocker bugs mở
- [ ] Automation scripts cover 100% smoke cases (TC-001 đến TC-003)
- [ ] Performance test pass (P95 ≤ 3s)
- [ ] Cross-browser testing completed (Chrome + Firefox)
- [ ] QA Lead sign-off

## 9. Deliverables

| # | Deliverable | Deadline |
|---|------------|----------|
| 1 | Test Cases document | Ngày 2 |
| 2 | Test Execution Report | Ngày 8 |
| 3 | Automation Scripts | Ngày 7 |
| 4 | Bug Report Summary | Ngày 9 |
| 5 | Test Sign-off Report | Ngày 10 |

---

*Phê duyệt bởi: QA Lead - [Tên sinh viên]*
*Ngày tạo: [Ngày nộp bài]*
*Version: 1.0*
