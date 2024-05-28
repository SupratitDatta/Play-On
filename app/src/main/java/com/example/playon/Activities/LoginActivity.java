package com.example.playon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

     EditText userEmail,userPassword;
    private TextView signup_btn;
    private Button logIn_btn;
    private FirebaseAuth mAuth;

    private ProgressBar signin_progressbar;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        initView();
    }
//    private void handleBackNavigation() {
//        // Override back button behavior
//        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                startActivity(new Intent(LoginActivity.this, IntroActivity.class));
//                Toast.makeText(LoginActivity.this, "Redirecting to Intro Page", Toast.LENGTH_SHORT).show();
//            }
//        };
//
//        // Add the callback to the back button dispatcher
//        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
//    }

    private  void initView() {
        userEmail=findViewById(R.id.signinEmail);
        userPassword=findViewById(R.id.signinPassword);
        logIn_btn=findViewById(R.id.logInBtn);
        signup_btn=findViewById(R.id.signupBtn);

        signin_progressbar = findViewById(R.id.signinProgressBar);
        mAuth = FirebaseAuth.getInstance();
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                Toast.makeText(LoginActivity.this, "Redirecting to Sign Up Page", Toast.LENGTH_SHORT).show();
            }
        });

        logIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email,password;
                email = String.valueOf(userEmail.getText());
                password = String.valueOf(userPassword.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Data can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(LoginActivity.this, "Password should be at least 6 digits long", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
//                    Toast.makeText(LoginActivity.this, "Data can't be empty", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                else if(userEmail.getText().toString().equals("test") && userPassword.getText().toString().equals("test")){
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                }
//                else{
//                    Toast.makeText(LoginActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
//                }
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                signin_progressbar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Logged In successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, "Invalid Credentials",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}