using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lab_Test
{
    
    public partial class Form1: Form
    {
        public class Calculation
        {
            // Hàm tính lũy thừa theo đúng công thức đệ quy đề bài
            public double Power(double x, int n)
            {
                // Case 1: n = 0
                if (n == 0)
                    return 1.0;

                // Case 2: n > 0 -> x^(n-1) * x
                if (n > 0)
                    return Power(x, n - 1) * x;

                // Case 3: n < 0 -> x^(n+1) / x
                // Logic toán học: x^-1 = x^0 / x = 1/x
                return Power(x, n + 1) / x;
            }
        }
        public Form1()
        {
            InitializeComponent();
        }
        private void btnTinh_Click(object sender, EventArgs e)
        {
            try
            {
                // 1. Lấy dữ liệu (Prerequisites & Test Data)
                if (string.IsNullOrWhiteSpace(txtX.Text) || string.IsNullOrWhiteSpace(txtN.Text))
                {
                    MessageBox.Show("Vui lòng nhập đầy đủ dữ liệu");
                    return;
                }

                double x = double.Parse(txtX.Text);
                int n = int.Parse(txtN.Text);

                // 2. Gọi hàm logic (Steps)
                Calculation cal = new Calculation();
                double result = cal.Power(x, n);

                // 3. Hiển thị (Actual Result)
                lblResult.Text = "Kết quả: " + result.ToString();
            }
            catch (FormatException)
            {
                // Xử lý cho TC_04 (Nhập sai định dạng)
                MessageBox.Show("Dữ liệu không hợp lệ (Phải là số)!");
            }
            catch (Exception ex)
            {
                MessageBox.Show("Lỗi: " + ex.Message);
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void txtTestLog_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
