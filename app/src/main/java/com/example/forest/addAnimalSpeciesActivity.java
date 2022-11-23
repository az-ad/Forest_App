package com.example.forest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class addAnimalSpeciesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button chooseImageButton,saveButton;
    private EditText nameEditText,descriptionEditText;
    private ImageView addImage;
    private ProgressBar progressbar;
    private Uri imageUri;
    // DatabaseReference databaseReference;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    StorageTask uploadTask;

    private static final int IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal_species);


        databaseReference = FirebaseDatabase.getInstance().getReference("Animal");
        storageReference = FirebaseStorage.getInstance().getReference("Animal");

        chooseImageButton = findViewById(R.id.chooseImageButtonId);
        saveButton = findViewById(R.id.saveButtonId);
        nameEditText = findViewById(R.id.nameEditTextId);
        descriptionEditText = findViewById(R.id.descriptionEditTextId);
        addImage = findViewById(R.id.addImageId);
        progressbar = findViewById(R.id.progressbarId);

        chooseImageButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.chooseImageButtonId:
                openFileChooser();
                break ;


            case R.id.saveButtonId:
                if(uploadTask!=null && uploadTask.isInProgress())
                {
                    Toast.makeText(getApplicationContext(),"Upload in progress",Toast.LENGTH_LONG).show();
                }
                else {
                    saveData();
                }

                break ;

        }

    }

    void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent,IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imageUri = data.getData();
            Picasso.with(this).load(imageUri).into(addImage);
        }
    }
    // getting the extension of image
    public String getFileExtension(Uri imageUri)
    {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));

    }

    private void saveData() {

        String name = nameEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        if(name.isEmpty()){
            nameEditText.setError("Enter the image name");
            nameEditText.requestFocus();
            return ;
        }
        StorageReference ref = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));

        ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(),"Image is stored successfully",Toast.LENGTH_LONG).show();

                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!urlTask.isSuccessful());
                        Uri downloadUrl = urlTask.getResult();
                        //Tree tree = new Tree(name,description,taskSnapshot.getStorage().getDownloadUrl().toString() );
                        //Tree tree = new Tree(name,description,downloadUrl.toString() );
                        Animal animal = new Animal(name,description,downloadUrl.toString() );
                        //Tree tree = new Tree (description,taskSnapshot.getStorage().getDownloadUrl().toString());
                        String animalId = databaseReference.push().getKey();
                        databaseReference.child(animalId).setValue(animal);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(),"Image is not stored successfully",Toast.LENGTH_LONG).show();

                    }
                });
    }

}