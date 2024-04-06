package com.example.playon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;



public class LoginActivity extends AppCompatActivity {

    private EditText userEdit,passEdit;
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
        loginBtn=findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userEdit.getText().toString().isEmpty() || passEdit.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Data can't be empty", Toast.LENGTH_SHORT).show();
                }
                else if(userEdit.getText().toString().equals("test") && passEdit.getText().toString().equals("test")){
                    startActivity(new Intent(LoginActivity.this, IntroActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}