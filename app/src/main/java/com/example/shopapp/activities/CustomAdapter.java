package com.example.shopapp.activities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.data.ItemDataModel;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter{
    private ArrayList<ItemDataModel> dataSet;
    private SelectListener clickListener;

    public CustomAdapter(ArrayList<ItemDataModel>dataSet,SelectListener clickListener){
        this.dataSet=dataSet;
        this.clickListener=clickListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView itemName;
        private TextView itemDescription;
        private TextView itemAmount;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.);
            itemDescription = itemView.findViewById(R.id.characterDecription);
            imageView = itemView.findViewById(R.id.charPicture);
            cardView = itemView.findViewById(R.id.cardCharacter);
        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
