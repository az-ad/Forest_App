package com.example.forest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class speciesClassActivity extends AppCompatActivity implements View.OnClickListener  {
    private CardView treeCardview ,animalCardview,birdCardview,reptileCardview;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_class);

        mAuth=FirebaseAuth.getInstance();

        treeCardview=findViewById(R.id.treeCardViewId);
        animalCardview=findViewById(R.id.animalCardViewId);
        birdCardview=findViewById(R.id.birdCardViewId);
        reptileCardview=findViewById(R.id.reptileCardViewId);

        treeCardview.setOnClickListener(this);
        animalCardview.setOnClickListener(this);
        birdCardview.setOnClickListener(this);
        reptileCardview.setOnClickListener(this);

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
            Intent intent=new Intent(speciesClassActivity.this,MainActivity.class);
            startActivity(intent);
        }
       /* else if(item.getItemId()==R.id.profileMenuId)
        {
            Intent intent=new Intent(speciesClassActivity.this,profileActivity.class);
            startActivity(intent);
        }*/
        else if(item.getItemId()==R.id.treeId)
        {
            Intent intent=new Intent(speciesClassActivity.this,treeActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.animalId)
        {
            Intent intent=new Intent(speciesClassActivity.this,animalActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.birdId)
        {
            Intent intent=new Intent(speciesClassActivity.this,birdActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.reptileId)
        {
            Intent intent=new Intent(speciesClassActivity.this,reptileActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.sundarbanId)
        {
            Intent intent=new Intent(speciesClassActivity.this,sundorbanActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.mapId)
        {
            Intent intent=new Intent(speciesClassActivity.this,GoogleMapActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.treeCardViewId)
        {
            Intent intent=new Intent(speciesClassActivity.this,treeActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.animalCardViewId)
        {
            Intent intent=new Intent(speciesClassActivity.this,animalActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.birdCardViewId)
        {
            Intent intent=new Intent(speciesClassActivity.this,birdActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.reptileCardViewId)
        {
            Intent intent=new Intent(speciesClassActivity.this,reptileActivity.class);
            startActivity(intent);
        }

    }
}