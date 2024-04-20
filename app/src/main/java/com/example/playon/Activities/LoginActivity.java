package com.example.playon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playon.R;


public class LoginActivity extends AppCompatActivity {

    private EditText userEdit,passEdit;
    private TextView registerBtn;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        initView();
    }

    private  void initView() {
        userEdit=findViewById(R.id.editTextText);
        passEdit=findViewById(R.id.editTextpassword);
        loginBtn=findViewById(R.id.registerBtn);
        registerBtn=findViewById(R.id.signinBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                Toast.makeText(LoginActivity.this, "Redirecting to Sign Up Page", Toast.LENGTH_SHORT).show();
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userEdit.getText().toString().isEmpty() || passEdit.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Data can't be empty", Toast.LENGTH_SHORT).show();
                }
                else if(userEdit.getText().toString().equals("test") && passEdit.getText().toString().equals("test")){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}