package com.istic.mmm.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @BindView(R.id.emailField) EditText inputEmail;
    @BindView(R.id.passwordField) EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        // Checked if user is already connected
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }

        // Set the view after
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signUpText) void signup() {
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    @OnClick(R.id.loginButton) void login() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        // Handle errors
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), getString(R.string.login_error_message_email), Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            Toast.makeText(getApplicationContext(), getString(R.string.login_error_message_password), Toast.LENGTH_LONG).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
                            System.out.println("LogInActivity : " + task.getException());
                        } else {
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            finish();
                        }
                    }
                });
    }
}
