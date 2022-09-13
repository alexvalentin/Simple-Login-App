package com.example.simpleloginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.simpleloginapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.emailET.getText().toString();
                String pass = binding.passwordET.getText().toString();

                if (email.isEmpty()) {
                    binding.emailET.setError("Please enter email id");
                    binding.emailET.requestFocus();
                } else if (pass.isEmpty()) {
                    binding.passwordET.setError("Please enter your password");
                    binding.passwordET.requestFocus();
                } else {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Sign Up Unsuccessful. Please try again!", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            }
                        }
                    });
                }
            }
        });

        binding.goToSignInTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}