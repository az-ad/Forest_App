package com.example.forest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class reptileActivity extends AppCompatActivity implements View.OnClickListener{
    Button seeReptileButton,addReptileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reptile);

        seeReptileButton=findViewById(R.id.seeReptileButtonId);
        addReptileButton=findViewById(R.id.addReptileButtonId);

        seeReptileButton.setOnClickListener(this);
        addReptileButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.seeReptileButtonId)
        {
            Intent intent=new Intent(reptileActivity.this,reptileSpeciesActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addReptileButtonId)
        {
            Intent intent=new Intent(reptileActivity.this,addReptileSpeciesActivity.class);
            startActivity(intent);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.logoutMenutId)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent=new Intent(reptileActivity.this,MainActivity.class);
            startActivity(intent);
        }
        /*else if(item.getItemId()==R.id.profileMenuId)
        {
            Intent intent=new Intent(reptileActivity.this,profileActivity.class);
            startActivity(intent);
        }*/
        else if(item.getItemId()==R.id.sundarbanId)
        {
            Intent intent=new Intent(reptileActivity.this,sundorbanActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.treeId)
        {
            Intent intent=new Intent(reptileActivity.this,treeActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.animalId)
        {
            Intent intent=new Intent(reptileActivity.this,animalActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.birdId)
        {
            Intent intent=new Intent(reptileActivity.this,birdActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.mapId)
        {
            Intent intent=new Intent(reptileActivity.this,GoogleMapActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}