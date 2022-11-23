package com.example.forest;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BirdAdapter extends RecyclerView.Adapter<BirdAdapter.MyViewHolder> {

    private Context context;
    //private Activity context;
    private List<Bird> uploadList;

    public BirdAdapter(Context context, List<Bird> uploadList) {
        //super(context, R.layout.item_layout,uploadList);
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Bird bird = uploadList.get(i);
        myViewHolder.imageNameTextView.setText(bird.getName());
        myViewHolder.imageDescriptionTextView.setText(bird.getDescription());
        Picasso.with(context)
                .load(bird.getImageUrl())
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(myViewHolder.imageView);


    }

    @Override
    public int getItemCount() {
        return uploadList.size();
        //return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView imageNameTextView,imageDescriptionTextView;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //imageNameTextView = itemView.findViewById(R.id.imageViewId);
            imageNameTextView=itemView.findViewById(R.id.imageNameTextViewId);
            imageDescriptionTextView = itemView.findViewById(R.id.imageDescriptionTextViewId);
            imageView = itemView.findViewById(R.id.imageViewId);

        }
    }
    public void filterList(ArrayList<Bird> filteredList){
        uploadList = filteredList;
        notifyDataSetChanged();
    }
}

