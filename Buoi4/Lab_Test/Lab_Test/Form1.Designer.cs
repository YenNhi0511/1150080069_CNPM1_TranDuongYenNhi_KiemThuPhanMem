namespace Lab_Test
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.txtX = new System.Windows.Forms.TextBox();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.txtN = new System.Windows.Forms.TextBox();
            this.txtResult = new System.Windows.Forms.TextBox();
            this.btnCalculate = new System.Windows.Forms.Button();
            this.btnRunTests = new System.Windows.Forms.Button();
            this.txtTestLog = new System.Windows.Forms.RichTextBox();
            this.lblX = new System.Windows.Forms.Label();
            this.lblN = new System.Windows.Forms.Label();
            this.lblResult = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // txtX
            // 
            this.txtX.Location = new System.Drawing.Point(154, 33);
            this.txtX.Name = "txtX";
            this.txtX.Size = new System.Drawing.Size(310, 26);
            this.txtX.TabIndex = 0;
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.ImageScalingSize = new System.Drawing.Size(24, 24);
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(61, 4);
            // 
            // txtN
            // 
            this.txtN.Location = new System.Drawing.Point(154, 89);
            this.txtN.Name = "txtN";
            this.txtN.Size = new System.Drawing.Size(310, 26);
            this.txtN.TabIndex = 2;
            // 
            // txtResult
            // 
            this.txtResult.Location = new System.Drawing.Point(154, 150);
            this.txtResult.Name = "txtResult";
            this.txtResult.Size = new System.Drawing.Size(310, 26);
            this.txtResult.TabIndex = 3;
            // 
            // btnCalculate
            // 
            this.btnCalculate.Location = new System.Drawing.Point(47, 218);
            this.btnCalculate.Name = "btnCalculate";
            this.btnCalculate.Size = new System.Drawing.Size(173, 34);
            this.btnCalculate.TabIndex = 4;
            this.btnCalculate.Text = "button1";
            this.btnCalculate.UseVisualStyleBackColor = true;
            // 
            // btnRunTests
            // 
            this.btnRunTests.Location = new System.Drawing.Point(286, 218);
            this.btnRunTests.Name = "btnRunTests";
            this.btnRunTests.Size = new System.Drawing.Size(178, 34);
            this.btnRunTests.TabIndex = 5;
            this.btnRunTests.Text = "button1";
            this.btnRunTests.UseVisualStyleBackColor = true;
            // 
            // txtTestLog
            // 
            this.txtTestLog.Location = new System.Drawing.Point(47, 278);
            this.txtTestLog.Name = "txtTestLog";
            this.txtTestLog.Size = new System.Drawing.Size(417, 52);
            this.txtTestLog.TabIndex = 6;
            this.txtTestLog.Text = "";
            this.txtTestLog.TextChanged += new System.EventHandler(this.txtTestLog_TextChanged);
            // 
            // lblX
            // 
            this.lblX.AutoSize = true;
            this.lblX.Location = new System.Drawing.Point(62, 35);
            this.lblX.Name = "lblX";
            this.lblX.Size = new System.Drawing.Size(62, 20);
            this.lblX.TabIndex = 7;
            this.lblX.Text = "Nhập X";
            // 
            // lblN
            // 
            this.lblN.AutoSize = true;
            this.lblN.Location = new System.Drawing.Point(62, 95);
            this.lblN.Name = "lblN";
            this.lblN.Size = new System.Drawing.Size(62, 20);
            this.lblN.TabIndex = 8;
            this.lblN.Text = "Nhập N";
            // 
            // lblResult
            // 
            this.lblResult.AutoSize = true;
            this.lblResult.Location = new System.Drawing.Point(62, 150);
            this.lblResult.Name = "lblResult";
            this.lblResult.Size = new System.Drawing.Size(64, 20);
            this.lblResult.TabIndex = 9;
            this.lblResult.Text = "Kết quả";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.lblResult);
            this.Controls.Add(this.lblN);
            this.Controls.Add(this.lblX);
            this.Controls.Add(this.txtTestLog);
            this.Controls.Add(this.btnRunTests);
            this.Controls.Add(this.btnCalculate);
            this.Controls.Add(this.txtResult);
            this.Controls.Add(this.txtN);
            this.Controls.Add(this.txtX);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox txtX;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.TextBox txtN;
        private System.Windows.Forms.TextBox txtResult;
        private System.Windows.Forms.Button btnCalculate;
        private System.Windows.Forms.Button btnRunTests;
        private System.Windows.Forms.RichTextBox txtTestLog;
        private System.Windows.Forms.Label lblX;
        private System.Windows.Forms.Label lblN;
        private System.Windows.Forms.Label lblResult;
    }
}

