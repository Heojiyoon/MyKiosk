package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText eusername, epassword;
    Button register;
    boolean isAllFields = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eusername = findViewById(R.id.username);
        epassword = findViewById(R.id.password);
        register = findViewById(R.id.regibutton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFields = register();
                if (isAllFields) {
                    String a = eusername.getText().toString();
                    String b = epassword.getText().toString();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.putExtra("username", a);
                    intent.putExtra("password", b);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean register() {
        String username = eusername.getText().toString().trim();
        String password = epassword.getText().toString().trim();
        if (checkAllFields(username, password)) {
            Toast.makeText(this, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private boolean checkAllFields(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            eusername.setError("이름을 입력해주세요.");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            epassword.setError("알맞은 비밀번호를 입력해주세요.");
            return false;
        }

        return true;
    }
}
