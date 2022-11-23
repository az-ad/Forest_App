package com.example.forest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class logInActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView signUpTextView;
    private EditText loginEmailEditText,loginPasswordEditText;
    private Button loginButton;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        this.setTitle("Login Activity");

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressbarId);
        signUpTextView = findViewById(R.id.needAccountId );
        loginEmailEditText = findViewById(R.id.email_Id);
        loginPasswordEditText = findViewById(R.id.passwordId);
        loginButton = findViewById(R.id.loginButtonId);

        signUpTextView.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.loginButtonId:
                userLogin();
                break;
            case R.id.needAccountId:
                Intent intent = new Intent(getApplicationContext(),signUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void userLogin() {
        String email= loginEmailEditText.getText().toString().trim();
        String password= loginPasswordEditText.getText().toString().trim();

        //checking the validity of the email
        if(email.isEmpty())
        {
            loginEmailEditText.setError("Enter an email address");
            loginEmailEditText.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            loginEmailEditText.setError("Enter a valid email address");
            loginEmailEditText.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            loginPasswordEditText.setError("Enter a password");
            loginPasswordEditText.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            loginPasswordEditText.setError("Minimum length of a password should be 6");
            loginPasswordEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            finish();
                            Intent intent = new Intent(getApplicationContext(),speciesClassActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                            //pass email to user profile
                            /*Intent intent1 = new Intent(getApplicationContext(),profileActivity.class);
                            intent1.putExtra("email",email);
                            startActivity(intent1);*/

//                            finish();
//                            Intent intent = new Intent(getApplicationContext(),speciesActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//                            Intent intent = new Intent(getApplicationContext(),speciesClassActivity.class);
//                            startActivity(intent);
                               }
                        else {
                            Toast.makeText(getApplicationContext(),"Login unsuccessful",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}