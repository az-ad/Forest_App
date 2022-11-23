package com.example.forest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class profileActivity extends AppCompatActivity {

   private TextView emailTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        emailTextView = findViewById(R.id.emailTextViewId);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
           String mail= bundle.getString("email");
            emailTextView.setText(mail);
        }
    }
}