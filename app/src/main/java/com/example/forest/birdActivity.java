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

public class birdActivity extends AppCompatActivity implements View.OnClickListener {
    Button seeBirdButton,addBirdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird);

        seeBirdButton=findViewById(R.id.seeBirdButtonId);
        addBirdButton=findViewById(R.id.addBirdButtonId);

        seeBirdButton.setOnClickListener(this);
        addBirdButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.seeBirdButtonId)
        {
            Intent intent=new Intent(birdActivity.this,birdSpeciesActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addBirdButtonId)
        {
            Intent intent=new Intent(birdActivity.this,addBirdSpeciesActivity.class);
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
            Intent intent=new Intent(birdActivity.this,MainActivity.class);
            startActivity(intent);
        }
       /* else if(item.getItemId()==R.id.profileMenuId)
        {
            Intent intent=new Intent(birdActivity.this,profileActivity.class);
            startActivity(intent);
        }*/
        else if(item.getItemId()==R.id.sundarbanId)
        {
            Intent intent=new Intent(birdActivity.this,sundorbanActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.treeId)
        {
            Intent intent=new Intent(birdActivity.this,treeActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.animalId)
        {
            Intent intent=new Intent(birdActivity.this,animalActivity.class);
            startActivity(intent);
        }

        else if(item.getItemId()==R.id.reptileId)
        {
            Intent intent=new Intent(birdActivity.this,reptileActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.mapId)
        {
            Intent intent=new Intent(birdActivity.this,GoogleMapActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}