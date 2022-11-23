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

public class treeActivity extends AppCompatActivity  implements View.OnClickListener {

    Button seeTreeButton,addTreeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);

        seeTreeButton=findViewById(R.id.seeTreeButtonId);
        addTreeButton=findViewById(R.id.addTreeButtonId);

        seeTreeButton.setOnClickListener(this);
        addTreeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.seeTreeButtonId)
        {
            Intent intent=new Intent(treeActivity.this,treeSpeciesActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addTreeButtonId)
        {
            Intent intent=new Intent(treeActivity.this,addTreeSpeciesActivity.class);
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
            Intent intent=new Intent(treeActivity.this,MainActivity.class);
            startActivity(intent);
        }
        /*else if(item.getItemId()==R.id.profileMenuId)
        {
            Intent intent=new Intent(treeActivity.this,profileActivity.class);
            startActivity(intent);
        }*/
        else if(item.getItemId()==R.id.sundarbanId)
        {
            Intent intent=new Intent(treeActivity.this,sundorbanActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.animalId)
        {
            Intent intent=new Intent(treeActivity.this,animalActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.birdId)
        {
            Intent intent=new Intent(treeActivity.this,birdActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.reptileId)
        {
            Intent intent=new Intent(treeActivity.this,reptileActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.mapId)
        {
            Intent intent=new Intent(treeActivity.this,GoogleMapActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}