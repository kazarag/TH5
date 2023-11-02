package com.example.th5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {
    private static final String TAG_CALCULATOR = "CALCULATOR";
    // Used to show calculate number and operator.
    private EditText resultEditText = null;
    private EditText inputEditText = null;
    // Record the first number before operator.
    private double firstNumber = Double.MIN_VALUE;
    // Record the second number before = operator.
    private double secondNumber = Double.MIN_VALUE;
    // Record user clicked operator.
    private String operator = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }
    String l = "";
    public void onClick(View view) {
        int id = view.getId();
        inputEditText= findViewById(R.id.input);
        resultEditText=findViewById(R.id.result);
        if (id == R.id.button_1) {
            l+="1";
            inputEditText.setText(l);

        } else if (id == R.id.button_2) {
            l+="2";
            inputEditText.setText(l);
        } else if (id == R.id.button_3) {
            l+="3";
            inputEditText.setText(l);
        } else if (id == R.id.button_4) {
            l+="4";
            inputEditText.setText(l);
        } else if (id == R.id.button_5) {
            l+="5";
            inputEditText.setText(l);
        } else if (id == R.id.button_6) {
            l+="6";
            inputEditText.setText(l);
        } else if (id == R.id.button_7) {
            l+="7";
            inputEditText.setText(l);
        } else if (id == R.id.button_8) {
            l+="8";
            inputEditText.setText(l);
        } else if (id == R.id.button_9) {
            l+="9";
            inputEditText.setText(l);
        }
        else if (id == R.id.button_0) {
            l+="0";
            inputEditText.setText(l);
        } else if (id == R.id.button_del) {
            l="";
            inputEditText.setText(l);
        } else if (id == R.id.button_divide) {
            l+="/";
            inputEditText.setText(l);
        } else if (id == R.id.button_equal) {
            resultEditText.setText(giai(l));
        } else if (id == R.id.button_minus) {
            l+="-";
            inputEditText.setText(l);
        } else if (id == R.id.button_multiple) {
            l+="*";
            inputEditText.setText(l);
        } else if (id == R.id.button_plus) {
            l+="+";
            inputEditText.setText(l);
        }else if (id == R.id.button_c) {
            l+=".";
            inputEditText.setText(l);
        }

    }
    public String giai(String str){
        // Loại bỏ các khoảng trắng trong chuỗi
        str = str.replaceAll("\\s+", "");

        // Tách các số và phép toán ra thành các phần tử trong một mảng
        String[] elements = str.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");

        // Khởi tạo biến lưu trữ kết quả tính toán
        int result = 0;
        int temp = 0;
        String operator = "+";

        // Thực hiện phép toán trên từng phần tử trong mảng
        for (String element : elements) {
            if (element.matches("[+-/*]")) {
                operator = element;
            } else {
                int number = Integer.parseInt(element);

                switch (operator) {
                    case "+":
                        result += number;
                        break;
                    case "-":
                        result -= number;
                        break;
                    case "*":
                        result *= number;
                        break;
                    case "/":
                        result /= number;
                        break;
                }
            }
        }

        // Trả về kết quả dưới dạng chuỗi
        return String.valueOf(result);
    }
}