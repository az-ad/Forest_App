 package com.example.forest;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import android.graphics.Bitmap;
 import android.graphics.BitmapFactory;
 import android.os.Bundle;
 import android.text.Editable;
 import android.text.TextWatcher;
 import android.view.View;
 import android.widget.EditText;
 import android.widget.ImageView;
 import android.widget.ProgressBar;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.google.firebase.database.DataSnapshot;
 import com.google.firebase.database.DatabaseError;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.ValueEventListener;

 import java.io.IOException;
 import java.net.MalformedURLException;
 import java.net.URL;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;

public class birdSpeciesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BirdAdapter birdAdapter;
    private List<Bird>uploadList;
    DatabaseReference databaseReference;
    private ProgressBar recyclerProgressbar;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_species);

        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerProgressbar = findViewById(R.id.recyclerProgressbarId);
        searchEditText=findViewById(R.id.searchEditTextId);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

        uploadList = new ArrayList<>();
        birdAdapter = new BirdAdapter(birdSpeciesActivity.this,uploadList);
        recyclerView.setAdapter(birdAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Bird");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        Bird bird = dataSnapshot1.getValue(Bird.class);
                        uploadList.add(bird);
                    }
                    birdAdapter = new BirdAdapter(birdSpeciesActivity.this,uploadList);
                    recyclerView.setAdapter(birdAdapter);

                }

                recyclerProgressbar.setVisibility(View.INVISIBLE);
            }



        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(getApplicationContext(),"Error : "+databaseError.getMessage(),Toast.LENGTH_LONG).show();
            recyclerProgressbar.setVisibility(View.INVISIBLE);
        }
    });

    }

    private void filter(String text)
    {
        ArrayList<Bird>filteredList = new ArrayList<>();
        for(Bird item : uploadList){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        birdAdapter.filterList(filteredList);
    }
}