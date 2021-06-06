package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private Button SignUp;
    private Button SignUpRetailer;
    private ProgressBar PBar;
    private FirebaseAuth fAuth;
    private int attemps = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.editTextTextPersonName);
        Password = (EditText)findViewById(R.id.editTextTextPassword);
        Login = (Button)findViewById(R.id.button);
        SignUp = (Button)findViewById(R.id.button3);
        SignUpRetailer = (Button)findViewById(R.id.button6);
        PBar = (ProgressBar)findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                login(Name.getText().toString(),Password.getText().toString());
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                SigningUp();
            }
        });

        SignUpRetailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                SigningUpRetailer();
            }
        });
    }

    private void login(String userEmail, String userPassword){
        fAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            PBar.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void SigningUp(){
        Intent intent2 = new Intent(MainActivity.this,ThirdActivity.class);
        startActivity(intent2);
    }

    private void SigningUpRetailer(){
        Intent intent3 = new Intent(MainActivity.this,FourthActivity.class);
        startActivity(intent3);
    }
}
