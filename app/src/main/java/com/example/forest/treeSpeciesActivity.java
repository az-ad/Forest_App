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
import android.widget.SearchView;
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
import java.util.Locale;

public class treeSpeciesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TreeAdapter treeAdapter;
    private List<Tree>uploadList;
    DatabaseReference databaseReference;
    private ProgressBar recyclerProgressbar;
    private EditText searchEditText;
   // private SearchView searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_species);
        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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



        recyclerProgressbar = findViewById(R.id.recyclerProgressbarId);

        uploadList = new ArrayList<>();
        treeAdapter = new TreeAdapter(treeSpeciesActivity.this,uploadList);
        recyclerView.setAdapter(treeAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Tree");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        Tree tree = dataSnapshot1.getValue(Tree.class);
                        uploadList.add(tree);
                    }
                    treeAdapter = new TreeAdapter(treeSpeciesActivity.this,uploadList);
                    recyclerView.setAdapter(treeAdapter);

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
    // ..............................for search view.................................
    private void filter (String text)
    {
        ArrayList<Tree>filteredList = new ArrayList<>();
        for(Tree item : uploadList){
            if(item.getDescription().toLowerCase().contains(text.toLowerCase()) || item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        treeAdapter.filterList(filteredList);
    }
    //...........................................................
}




               /* String name="";
                String Url = "";
                String desc = "";

                if (dataSnapshot.exists()){
                    HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();

                    for (String key : dataMap.keySet()){

                        Object data = dataMap.get(key);

                        try{
                            HashMap<String, Object> userData = (HashMap<String, Object>) data;

                             name = (String) userData.get("name");
                            Url = (String) userData.get("imageUrl");
                            desc = (String) userData.get("description");*/

//                            User mUser = new User((String) userData.get("name"), (int) (long) userData.get("age"));
//                            addTextToView(mUser.getName() + " - " + Integer.toString(mUser.getAge()));

                      /*  }catch (ClassCastException cce){

                        }

                    }
                }*/
/*
                demoTextView.setText(name);
                demoTextView.setText(desc);*/


//
//                URL newurl = null;
//                try {
//                    newurl = new URL(Url);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//                Bitmap mIcon_val = null;
//                try {
//                    mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                demoImageView.setImageBitmap(mIcon_val);


//
//
//                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
//                {
//                     = dataSnapshot1.getValue();
//                    String value = snapshot.getValue(String.class);
//                    demoTextView.setText(tree.getDescription());
//                }
//
