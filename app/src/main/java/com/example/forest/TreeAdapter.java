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

public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.MyViewHolder> {

    private Context context;
    //private Activity context;
    private List<Tree> uploadList;

    public TreeAdapter(Context context, List<Tree> uploadList) {
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

        Tree tree = uploadList.get(i);
        myViewHolder.imageNameTextView.setText(tree.getName());
        myViewHolder.imageDescriptionTextView.setText(tree.getDescription());
        Picasso.with(context)
                .load(tree.getImageUrl())
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

    //..............search view .........................

    public void filterList(ArrayList<Tree> filteredList){
        uploadList = filteredList;
        notifyDataSetChanged();
    }
    //.......................................
}
