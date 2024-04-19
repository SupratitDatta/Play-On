package com.example.playon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.playon.R;

public class RegisterActivity extends AppCompatActivity {
    private Button registerDoneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        registerDoneButton = findViewById(R.id.registerBtn);

        registerDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity to navigate to the signup page
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                // Optionally, you can show a toast message to indicate the action
                Toast.makeText(RegisterActivity.this, "Redirecting to Signup Page", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
