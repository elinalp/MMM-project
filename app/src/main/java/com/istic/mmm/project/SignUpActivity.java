package com.istic.mmm.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @BindView(R.id.emailField) EditText inputEmail;
    @BindView(R.id.passwordField) EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        // Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.signupButton) void signup() {

        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        // Handle errors
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), getString(R.string.signup_error_message_email), Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            Toast.makeText(getApplicationContext(), getString(R.string.signup_error_message_password), Toast.LENGTH_LONG).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), getString(R.string.signup_error_message), Toast.LENGTH_SHORT).show();
                        System.out.println("SignUpActivity : " + task.getException());
                    } else {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }
            });
    }
}

