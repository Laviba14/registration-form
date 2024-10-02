package com.example.registrationform;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername, etEmail, etPassword, etConfirmPassword, etMobile;
    private Button btnClear, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etUsername = findViewById(R.id.et_register_username);
        etEmail = findViewById(R.id.et_register_email);
        etPassword = findViewById(R.id.et_register_password);
        etConfirmPassword = findViewById(R.id.et_register_confirm_password);
        etMobile = findViewById(R.id.et_register_mobile);
        btnClear = findViewById(R.id.btn_clear);
        btnRegister = findViewById(R.id.btn_signup_register);


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
                Toast.makeText(MainActivity.this, "Cleared", Toast.LENGTH_SHORT).show();
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private boolean validateInput() {

        String username = etUsername.getText().toString().trim();
        if (TextUtils.isEmpty(username) || !username.matches("^[a-zA-Z\\s]+$")) {
            etUsername.setError("Enter a valid name (letters only)");
            return false;
        }


        String email = etEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter a valid email address");
            return false;
        }


        String mobile = etMobile.getText().toString().trim();
        if (!isValidPhoneNumber(mobile)) {
            etMobile.setError("Enter a valid phone number starting with 017, 018, 019, 016, 015, 013, 014, or +880");
            return false;
        }


        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password) || password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*") || !password.matches(".*[!@#\\$%^&*].*")) {
            etPassword.setError("Password must be at least 8 characters, include upper, lower, number, and special character");
            return false;
        }


        String confirmPassword = etConfirmPassword.getText().toString().trim();
        if (TextUtils.isEmpty(confirmPassword) || !confirmPassword.equals(password)) {
            etConfirmPassword.setError("Passwords do not match");
            return false;
        }

        return true;
    }


    private boolean isValidPhoneNumber(String mobile) {

        String regex = "^(\\+8801[3-9]\\d{8})|(01[3-9]\\d{8})$";
        return mobile.matches(regex);
    }


    private void clearFields() {
        etUsername.setText("");
        etEmail.setText("");
        etPassword.setText("");
        etConfirmPassword.setText("");
        etMobile.setText("");
    }
}
