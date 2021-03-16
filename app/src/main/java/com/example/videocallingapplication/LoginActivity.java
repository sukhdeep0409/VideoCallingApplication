package com.example.videocallingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login, signup;

    FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging you in...");

        email = findViewById(R.id.emailBox);
        password = findViewById(R.id.passwordBox);

        login = findViewById(R.id.login_button);
        signup = findViewById(R.id.signup_button);

        signup.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            finish();
        });

        login.setOnClickListener(view -> {
            progressDialog.show();
            signInAccount(email.getText().toString(), password.getText().toString());
        });
    }

    private void signInAccount(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                progressDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}