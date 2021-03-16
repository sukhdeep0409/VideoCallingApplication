package com.example.videocallingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    EditText email, password, fullName;
    Button login, signUp;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.emailBox);
        password = findViewById(R.id.passwordBox);
        fullName = findViewById(R.id.nameBox);

        login = findViewById(R.id.login_button);
        signUp = findViewById(R.id.signup_button);

        signUp.setOnClickListener(view -> {
            createAccount(email.getText().toString(), password.getText().toString(), fullName.getText().toString());
        });

    }

    private void createAccount(String email, String password, String fullName) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Account is created", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}