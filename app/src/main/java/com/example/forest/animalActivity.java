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

public class animalActivity extends AppCompatActivity implements View.OnClickListener {
    Button seeAnimalButton,addAnimalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        seeAnimalButton=findViewById(R.id.seeAnimalButtonId);
        addAnimalButton=findViewById(R.id.addAnimalButtonId);

        seeAnimalButton.setOnClickListener(this);
        addAnimalButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.seeAnimalButtonId)
        {
            Intent intent=new Intent(animalActivity.this,animalSpeciesActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addAnimalButtonId)
        {
            Intent intent=new Intent(animalActivity.this,addAnimalSpeciesActivity.class);
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
            Intent intent=new Intent(animalActivity.this,MainActivity.class);
            startActivity(intent);
        }
       /* else if(item.getItemId()==R.id.profileMenuId)
        {
            Intent intent=new Intent(animalActivity.this,profileActivity.class);
            startActivity(intent);
        }*/
        else if(item.getItemId()==R.id.sundarbanId)
        {
            Intent intent=new Intent(animalActivity.this,sundorbanActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.treeId)
        {
            Intent intent=new Intent(animalActivity.this,treeActivity.class);
            startActivity(intent);
        }

        else if(item.getItemId()==R.id.birdId)
        {
            Intent intent=new Intent(animalActivity.this,birdActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.reptileId)
        {
            Intent intent=new Intent(animalActivity.this,reptileActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.mapId)
        {
            Intent intent=new Intent(animalActivity.this,GoogleMapActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}