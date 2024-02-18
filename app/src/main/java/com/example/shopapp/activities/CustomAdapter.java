package com.example.shopapp.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.shopapp.R;
import com.example.shopapp.data.ItemDataModel;
import com.example.shopapp.fragments.ShopFragment;

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
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemNameCardView);
            itemDescription = itemView.findViewById(R.id.descriptionCardTextView);
            itemAmount = itemView.findViewById(R.id.amountCardTextView);
            cardView=itemView.findViewById(R.id.itemCardView);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_layout,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder) holder;
        TextView textViewItemName=myViewHolder.itemName;
        TextView textViewDescription=myViewHolder.itemDescription;
        TextView textViewAmount=myViewHolder.itemAmount;

        textViewItemName.setText(dataSet.get(position).getName());
        textViewDescription.setText(dataSet.get(position).getDescription());
        textViewAmount.setText(String.valueOf(dataSet.get(position).getAmount()));

        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(dataSet.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
